import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class pascalCustomVisitor extends pascalBaseVisitor<Void> {
    private final Map<String, String> stringVariables = new HashMap<>();
    private final Map<String, Integer> intVariables = new HashMap<>();

    @Override
    public Void visitProgram(pascalParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitCompoundStatement(pascalParser.CompoundStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitVariableDeclaration(pascalParser.VariableDeclarationContext ctx) {
        // Iterate over all declared variables
        for (pascalParser.IdentifierContext identifierCtx : ctx.identifierList().identifier()) {
            String varName = identifierCtx.getText();
            String varType = ctx.type_().getText();
            // System.out.print("begin - varName: " + varName + ", varType: " + varType + "\n\n");

            if (varType.equals("integer")) {
                intVariables.put(varName, 0);
            } else if (varType.equals("string")) {
                stringVariables.put(varName, "");
            }
        }
        return null;
    }

    @Override
    public Void visitAssignmentStatement(pascalParser.AssignmentStatementContext ctx) {
        // Handle variable assignments
        String varName = ctx.variable().getText();
        String value = visit(ctx.expression()) != null ? visit(ctx.expression()).toString() : ctx.expression().getText();
        
        // System.out.print("varName: " + varName + ", value: " + value + "\n");
        
        if (intVariables.containsKey(varName)) {
            try {
                intVariables.put(varName, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.err.println("Invalid integer assignment to " + varName);
            }
        } else if (stringVariables.containsKey(varName)) {
            if (value.startsWith("'") && value.endsWith("'")) {
                stringVariables.put(varName, value.substring(1, value.length() - 1));
            }
        }
        return null;
    }

    @Override
    public Void visitProcedureStatement(pascalParser.ProcedureStatementContext ctx) {
        // Handle writeln and write statements
        String procName = ctx.identifier().getText();
        if (("writeln".equalsIgnoreCase(procName) || "write".equalsIgnoreCase(procName)) && ctx.parameterList() != null) {
            List<pascalParser.ActualParameterContext> params = ctx.parameterList().actualParameter();
            for (pascalParser.ActualParameterContext param : params) {
                String paramText = param.getText().trim();
                // System.out.print("\nparamText: " + paramText + "\n");

                if (paramText.startsWith("'") && paramText.endsWith("'")) {
                    // Handle string literals
                    System.out.print(paramText.substring(1, paramText.length() - 1).replace("#10", "\n"));

                } else if (intVariables.containsKey(paramText)) {
                    // Print integer variables
                    System.out.print(intVariables.get(paramText));

                } else if (stringVariables.containsKey(paramText)) {
                    // Print string variables
                    System.out.print(stringVariables.get(paramText));

                } else {
                    // Handle numeric values directly
                    try {
                        int number = Integer.parseInt(paramText);
                        System.out.print(number);
                    } catch (NumberFormatException e) {
                        System.out.print(paramText); // Default to raw text if not recognized
                    }
                }
            }
            if ("writeln".equalsIgnoreCase(procName)) {
                System.out.println();
            }
        }
        return null;
    }
    
    // evaluateExpression
}
