import org.antlr.v4.runtime.tree.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class delphiCustomVisitor extends delphiBaseVisitor<Void> {
    private final Map<String, Integer> fieldValues = new HashMap<>();
    private boolean shouldContinue = false;
    private boolean shouldBreak = false;

    @Override
    public Void visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitMethodCall(delphiParser.MethodCallContext ctx) {
        String methodName = ctx.IDENT(1).getText();
        if ("Create".equals(methodName)) {
            return visit(ctx.getParent());
        }
        return null;
    }

    @Override
    public Void visitWritelnCall(delphiParser.WritelnCallContext ctx) {
        if (shouldContinue || shouldBreak) return null;

        String value = ctx.expression().getText();
        if (fieldValues.containsKey(value)) {
            System.out.println(fieldValues.get(value));
        } else {
            try {
                System.out.println(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println(fieldValues.getOrDefault(value, 0));
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
            fieldValues.put(loopVar, i); // update loop variable each time
            shouldContinue = false;
            shouldBreak = false;

            List<delphiParser.StatementContext> statements = ctx.statement();
            for (delphiParser.StatementContext stmt : statements) {
                visit(stmt);
                if (shouldContinue) break;
                if (shouldBreak) break;
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

    @Override
    public Void visitAssignment(delphiParser.AssignmentContext ctx) {
        if (shouldContinue || shouldBreak) return null;

        String varName = ctx.IDENT().getText();
        String value = ctx.expression().getText();

        try {
            fieldValues.put(varName, Integer.parseInt(value));
        } catch (NumberFormatException e) {
            fieldValues.put(varName, fieldValues.getOrDefault(value, 0));
        }

        return null;
    }

    private int getValue(delphiParser.ExpressionContext ctx) {
        String text = ctx.getText();
        if (fieldValues.containsKey(text)) {
            return fieldValues.get(text);
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
