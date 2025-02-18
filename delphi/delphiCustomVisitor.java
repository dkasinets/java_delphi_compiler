import org.antlr.v4.runtime.tree.*;
import java.util.HashMap;
import java.util.Map;

public class delphiCustomVisitor extends delphiBaseVisitor<Void> {
    private final Map<String, Integer> fieldValues = new HashMap<>();

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
        if (fieldValues.containsKey(value)) {
            System.out.println(fieldValues.get(value));
        } else {
            try {
                System.out.println(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println(fieldValues.getOrDefault(value, 0)); // Default to 0 if not found
            }
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
        String varName = ctx.IDENT().getText();
        String value = ctx.expression().getText();
        
        try {
            fieldValues.put(varName, Integer.parseInt(value));
        } catch (NumberFormatException e) {
            // Handle non-integer values if necessary
        }
        return null;
    }
    
    @Override
    public Void visitVariableAssignment(delphiParser.VariableAssignmentContext ctx) {
        String varName = ctx.IDENT().getText();
        String value = ctx.expression().getText();
        
        try {
            fieldValues.put(varName, Integer.parseInt(value));
        } catch (NumberFormatException e) {
            fieldValues.put(varName, fieldValues.getOrDefault(value, 0));
        }
        return null;
    }
}
