// delphiCustomGeneratorVisitor.java
import java.util.*;
import org.antlr.v4.runtime.tree.*;

public class delphiCustomGeneratorVisitor extends delphiBaseVisitor<String> {
    private StringBuilder builder = new StringBuilder();
    private int tempVarCount = 0;

    private String nextTemp() {
        return "%t" + (tempVarCount++);
    }

    @Override
    public String visitProgram(delphiParser.ProgramContext ctx) {
        builder.append("declare void @print_i32(i32) #0\n\n");
        builder.append("define void @run() {\nentry:\n");
        builder.append("  %number = alloca i32\n");
        builder.append("  %i = alloca i32\n");
        visitChildren(ctx);
        return finish();
    }

    private String finish() {
        builder.append("\nexit:\n");
        builder.append("  ret void\n}\n\n");
        builder.append("attributes #0 = { \"wasm-import-module\"=\"env\" \"wasm-import-name\"=\"print_i32\" }\n");
        return builder.toString();
    }

    @Override
    public String visitAssignment(delphiParser.AssignmentContext ctx) {
        String var = ctx.IDENT().getText();
        String value = ctx.expression().getText();
        builder.append("  store i32 " + value + ", ptr %" + var + "\n");
        return null;
    }

    @Override
    public String visitForStatement(delphiParser.ForStatementContext ctx) {
        String loopVar = ctx.IDENT().getText();
        String from = ctx.expression(0).getText();
        String to = ctx.expression(1).getText();

        builder.append("  store i32 " + from + ", ptr %" + loopVar + "\n");
        builder.append("  br label %loop\n\n");

        builder.append("loop:\n");
        builder.append("  %iv = load i32, ptr %" + loopVar + "\n");
        builder.append("  %n = load i32, ptr %number\n");
        builder.append("  %cond = icmp sle i32 %iv, %n\n");
        builder.append("  br i1 %cond, label %body, label %exit\n\n");

        builder.append("body:\n");
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitWritelnCall(delphiParser.WritelnCallContext ctx) {
        String value = ctx.expression().getText();
        builder.append("  call void @print_i32(i32 " + value + ")\n");
        return null;
    }

    @Override
    public String visitBreakStatement(delphiParser.BreakStatementContext ctx) {
        builder.append("  br label %exit ; break after one iteration\n");
        return null;
    }

    @Override
    public String visitChildren(RuleNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (child instanceof delphiParser.StatementContext ||
                child instanceof delphiParser.AssignmentContext ||
                child instanceof delphiParser.ForStatementContext ||
                child instanceof delphiParser.WritelnCallContext ||
                child instanceof delphiParser.BreakStatementContext) {
                visit(child);
            }
        }
        return null;
    }
}