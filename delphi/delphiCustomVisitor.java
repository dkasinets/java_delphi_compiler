import org.antlr.v4.runtime.tree.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class delphiCustomVisitor extends delphiBaseVisitor<Void> {
    private final Map<String, Integer> globalValues = new HashMap<>();
    private final Map<String, delphiParser.FunctionImplementationContext> functionDefs = new HashMap<>();
    private boolean shouldContinue = false;
    private boolean shouldBreak = false;

    @Override
    public Void visitFunctionImplementation(delphiParser.FunctionImplementationContext ctx) {
        String functionName = ctx.IDENT().getText();
        functionDefs.put(functionName, ctx);
        return null;
    }

    @Override
    public Void visitAssignment(delphiParser.AssignmentContext ctx) {
        if (shouldContinue || shouldBreak) return null;

        String varName = ctx.IDENT().getText();
        String value = ctx.expression().getText();

        if (functionDefs.containsKey(value)) {
            globalValues.put(varName, executeFunction(value));
        } else {
            try {
                globalValues.put(varName, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                globalValues.put(varName, globalValues.getOrDefault(value, 0));
            }
        }

        return null;
    }

    private int executeFunction(String functionName) {
        delphiParser.FunctionImplementationContext functionCtx = functionDefs.get(functionName);
        if (functionCtx == null) return 0;

        // Create a local scope
        Map<String, Integer> localScope = new HashMap<>(globalValues);
        localScope.put("Result", 0);

        // Handle local variables
        if (functionCtx.variableDeclaration() != null) {
            for (delphiParser.VariableDeclarationContext varDecl : functionCtx.variableDeclaration()) {
                visitVariableDeclarationInScope(varDecl, localScope);
            }
        }

        // Execute function body using local scope
        for (delphiParser.StatementContext stmt : functionCtx.statement()) {
            visitStatementInScope(stmt, localScope);
        }

        return localScope.getOrDefault("Result", 0);
    }

    private void visitVariableDeclarationInScope(delphiParser.VariableDeclarationContext ctx, Map<String, Integer> scope) {
        for (delphiParser.VarDeclContext decl : ctx.varDecl()) {
            for (TerminalNode id : decl.IDENT()) {
                scope.put(id.getText(), 0); // default initialization
            }
        }
    }

    private void visitStatementInScope(delphiParser.StatementContext ctx, Map<String, Integer> scope) {
        if (ctx.assignment() != null) {
            String var = ctx.assignment().IDENT().getText();
            String val = ctx.assignment().expression().getText();
            try {
                scope.put(var, Integer.parseInt(val));
            } catch (NumberFormatException e) {
                scope.put(var, scope.getOrDefault(val, globalValues.getOrDefault(val, 0)));
            }
        } else if (ctx.writelnCall() != null) {
            String expr = ctx.writelnCall().expression().getText();
            if (scope.containsKey(expr)) {
                System.out.println(scope.get(expr));
            } else if (globalValues.containsKey(expr)) {
                System.out.println(globalValues.get(expr));
            } else {
                try {
                    System.out.println(Integer.parseInt(expr));
                } catch (NumberFormatException e) {
                    System.out.println(0);
                }
            }
        }
    }

    @Override
    public Void visitWritelnCall(delphiParser.WritelnCallContext ctx) {
        if (shouldContinue || shouldBreak) return null;

        String value = ctx.expression().getText();
        if (globalValues.containsKey(value)) {
            System.out.println(globalValues.get(value));
        } else {
            try {
                System.out.println(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println(0);
            }
        }
        return null;
    }

    @Override
    public Void visitStatement(delphiParser.StatementContext ctx) {
        if (shouldContinue || shouldBreak) return null;
        return visitChildren(ctx);
    }

    @Override
    public Void visitContinueStatement(delphiParser.ContinueStatementContext ctx) {
        shouldContinue = true;
        return null;
    }

    @Override
    public Void visitBreakStatement(delphiParser.BreakStatementContext ctx) {
        shouldBreak = true;
        return null;
    }

    @Override
    public Void visitForStatement(delphiParser.ForStatementContext ctx) {
        String loopVar = ctx.IDENT().getText();
        int from = getValue(ctx.expression(0));
        int to = getValue(ctx.expression(1));

        for (int i = from; i <= to; i++) {
            globalValues.put(loopVar, i);
            shouldContinue = false;
            shouldBreak = false;

            for (delphiParser.StatementContext stmt : ctx.statement()) {
                visit(stmt);
                if (shouldContinue || shouldBreak) break;
            }

            if (shouldBreak) break;
        }

        return null;
    }

    @Override
    public Void visitProgram(delphiParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    private int getValue(delphiParser.ExpressionContext ctx) {
        String text = ctx.getText();
        if (globalValues.containsKey(text)) {
            return globalValues.get(text);
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
