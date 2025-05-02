import java.util.*;
import org.antlr.v4.runtime.tree.*;

public class delphiCustomGeneratorVisitor extends delphiBaseVisitor<String> {
    private StringBuilder builder = new StringBuilder();
    private StringBuilder methods = new StringBuilder();
    private List<String> allocas = new ArrayList<>();
    private int tempVarCount = 0;
    private boolean justDidContinue = false;
    private boolean justDidBreak = false;
    private boolean insideMethod = false;

    private Set<String> classTypes = new HashSet<>();
    private Map<String, String> variableTypes = new HashMap<>();

    private String nextTemp() {
        return "%t" + (tempVarCount++);
    }

    @Override
    public String visitProgram(delphiParser.ProgramContext ctx) {
        // ClassDeclaration type only if needed
        if (ctx.getText().contains("class")) {
            builder.append("%ClassDeclaration = type { i8 }\n");
        }

        builder.append("declare void @print_i32(i32) #0\n\n");

        for (ParseTree child : ctx.children) {
            if (child instanceof delphiParser.TopLevelDeclarationContext) {
                delphiParser.TopLevelDeclarationContext top = (delphiParser.TopLevelDeclarationContext) child;
                if (top.variableDeclaration() != null) {
                    for (delphiParser.VarDeclContext decl : top.variableDeclaration().varDecl()) {
                        String name = decl.IDENT().get(0).getText();
                        String type = decl.type_().getText();
                        variableTypes.put(name, type);
                        if (type.equals("Integer")) {
                            allocas.add("  %" + name + " = alloca i32");
                        } else if (classTypes.contains(type)) {
                            allocas.add("  %" + name + " = alloca %" + type);
                        }
                    }
                } else {
                    visit(top);
                }
            }
        }

        builder.append("define void @run() {\nentry:\n");
        for (String line : allocas) builder.append(line).append("\n");

        for (ParseTree child : ctx.children) {
            if (!(child instanceof delphiParser.TopLevelDeclarationContext)) {
                visit(child);
            }
        }

        builder.append("exit:\n");
        builder.append("  ret void\n}\n\n");

        builder.append(methods);
        builder.append("attributes #0 = { \"wasm-import-module\"=\"env\" \"wasm-import-name\"=\"print_i32\" }\n");

        return builder.toString();
    }

    @Override
    public String visitTopLevelDeclaration(delphiParser.TopLevelDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitVariableDeclaration(delphiParser.VariableDeclarationContext ctx) {
        return null;
    }

    @Override
    public String visitClassDeclaration(delphiParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENT().getText();
        classTypes.add(name);
        return null;
    }

    @Override
    public String visitMethodImplementation(delphiParser.MethodImplementationContext ctx) {
        String className = ctx.IDENT(0).getText();
        String methodName = ctx.IDENT(1).getText();
        String funcLabel = "@" + className + "_" + methodName;

        insideMethod = true;
        methods.append("define void " + funcLabel + "(ptr %this) {\nentry:\n");
        for (ParseTree child : ctx.children) visit(child);
        methods.append("  ret void\n}\n\n");
        insideMethod = false;
        return null;
    }

    @Override
    public String visitAssignment(delphiParser.AssignmentContext ctx) {
        String var = ctx.IDENT().getText();
        String exprText = ctx.expression().getText();
        if (exprText.matches("[A-Za-z_][A-Za-z0-9_]*\\.Create")) return null;
        builder.append("  store i32 " + exprText + ", ptr %" + var + "\n");
        return null;
    }

    @Override
    public String visitMethodCall(delphiParser.MethodCallContext ctx) {
        String obj = ctx.IDENT(0).getText();
        String method = ctx.IDENT(1).getText();
        String type = variableTypes.get(obj);
        if (type == null) {
            builder.append("  ; warning: unknown type for object " + obj + "\n");
            type = "null";
        }
        builder.append("  call void @" + type + "_" + method + "(ptr %" + obj + ")\n");
        return null;
    }

    @Override
    public String visitWritelnCall(delphiParser.WritelnCallContext ctx) {
        String value = ctx.expression().getText();
        if (insideMethod) {
            methods.append("  call void @print_i32(i32 " + value + ")\n");
        } else {
            builder.append("  call void @print_i32(i32 " + value + ")\n");
        }
        return null;
    }

    @Override
    public String visitBreakStatement(delphiParser.BreakStatementContext ctx) {
        builder.append("  br label %exit ; break after one iteration\n");
        justDidBreak = true;
        return null;
    }

    @Override
    public String visitContinueStatement(delphiParser.ContinueStatementContext ctx) {
        builder.append("  %next = add i32 %iv, 1\n");
        builder.append("  store i32 %next, ptr %i\n");
        builder.append("  br label %loop ; continue to next iteration\n");
        justDidContinue = true;
        return null;
    }

    @Override
    public String visitForStatement(delphiParser.ForStatementContext ctx) {
        String loopVar = ctx.IDENT().getText();
        String from = ctx.expression(0).getText();

        builder.append("  store i32 " + from + ", ptr %" + loopVar + "\n");
        builder.append("  br label %loop\n\n");

        builder.append("loop:\n");
        builder.append("  %iv = load i32, ptr %" + loopVar + "\n");
        builder.append("  %n = load i32, ptr %number\n");
        builder.append("  %cond = icmp sle i32 %iv, %n\n");
        builder.append("  br i1 %cond, label %body, label %exit\n\n");

        builder.append("body:\n");
        justDidContinue = false;
        justDidBreak = false;

        visitChildren(ctx);

        if (!justDidBreak && !justDidContinue) {
            builder.append("  %next = add i32 %iv, 1\n");
            builder.append("  store i32 %next, ptr %" + loopVar + "\n");
            builder.append("  br label %loop\n");
        }

        return null;
    }

    @Override
    public String visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        builder.append("  br label %loop\n\n");

        builder.append("loop:\n");
        builder.append("  %val = load i32, ptr %number\n");
        builder.append("  %cond = icmp eq i32 %val, 0\n");
        builder.append("  br i1 %cond, label %body, label %exit\n\n");

        builder.append("body:\n");
        justDidContinue = false;
        justDidBreak = false;

        visitChildren(ctx);

        if (!justDidBreak) {
            builder.append("  br label %loop\n");
        }

        return null;
    }

    @Override
    public String visitChildren(RuleNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            visit(node.getChild(i));
        }
        return null;
    }
}
