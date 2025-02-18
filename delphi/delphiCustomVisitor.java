import org.antlr.v4.runtime.tree.*;

public class delphiCustomVisitor extends delphiBaseVisitor<Void> {
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
        String value = ctx.expression().getText();
        try {
            System.out.println(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            System.out.println(value);
        }
        return null;
    }
    
    @Override
    public Void visitStatement(delphiParser.StatementContext ctx) {
        return visitChildren(ctx);
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
        return visitChildren(ctx);
    }
}
