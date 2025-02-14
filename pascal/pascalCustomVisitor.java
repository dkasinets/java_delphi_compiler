import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class pascalCustomVisitor extends pascalBaseVisitor<Void> {
    // Symbol Table to store variables, arrays, and constants
    private final Map<String, Integer> variables = new HashMap<>();
    private final Map<String, int[]> arrays = new HashMap<>();
    private final Map<String, Integer> constants = new HashMap<>();

    @Override
    public Void visitProgram(pascalParser.ProgramContext ctx) {
        System.out.println("Interpreting Pascal program...");
        return visitChildren(ctx);
    }

    @Override
    public Void visitCompoundStatement(pascalParser.CompoundStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitConstantDefinition(pascalParser.ConstantDefinitionContext ctx) {
        // Store constants
        String constName = ctx.identifier().getText();
        int constValue = Integer.parseInt(ctx.constant().getText().replaceAll("[^0-9]", ""));
        constants.put(constName, constValue);
        return null;
    }

    @Override
    public Void visitVariableDeclaration(pascalParser.VariableDeclarationContext ctx) {
        // Handle variable declarations
        String varName = ctx.identifierList().getText();
        if (ctx.type_().getText().equals("integer")) {
            variables.put(varName, 0);
        } else if (ctx.type_().getText().startsWith("array")) {
            // Handling arrays: array [1..size] of integer
            String[] bounds = ctx.type_().getText().split("\\.\\.");
            int lowerBound = constants.containsKey(bounds[0]) ? constants.get(bounds[0]) : Integer.parseInt(bounds[0].replaceAll("[^0-9]", ""));
            int upperBound = constants.containsKey(bounds[1]) ? constants.get(bounds[1]) : Integer.parseInt(bounds[1].replaceAll("[^0-9]", ""));
            int size = upperBound - lowerBound + 1;
            arrays.put(varName, new int[size]);
        }
        return null;
    }

    @Override
    public Void visitAssignmentStatement(pascalParser.AssignmentStatementContext ctx) {
        // Handle variable and array assignments
        String varName = ctx.variable().getText();
        int value = evaluateExpression(ctx.expression());

        if (varName.contains("[")) { // Array assignment: a[i] := value;
            String arrayName = varName.substring(0, varName.indexOf("["));
            int index = evaluateExpression(ctx.variable().expression(0)) - 1; // Pascal uses 1-based indexing
            if (arrays.containsKey(arrayName) && index >= 0 && index < arrays.get(arrayName).length) {
                arrays.get(arrayName)[index] = value;
            }
        } else {
            variables.put(varName, value);
        }
        return null;
    }

    @Override
    public Void visitForStatement(pascalParser.ForStatementContext ctx) {
        // Handle "for i := start TO end DO statement"
        String varName = ctx.identifier().getText();
        int start = evaluateExpression(ctx.expression(0));
        int end = evaluateExpression(ctx.expression(1));

        for (int i = start; i <= end; i++) {
            variables.put(varName, i);
            visit(ctx.statement());
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
                String paramText = param.getText();
                if (paramText.startsWith("'") && paramText.endsWith("'")) {
                    // Handle string literals
                    System.out.print(paramText.substring(1, paramText.length() - 1) + " ");
                } else {
                    String value = getValue(paramText);
                if (value.isEmpty()) {
                    value = "0"; // Default to 0 for uninitialized variables
                }
                System.out.print(value + " ");
                }
            }
            if ("writeln".equalsIgnoreCase(procName)) {
                System.out.println();
            }
        }
        return null;
    }

    @Override
    public Void visitProcedureDeclaration(pascalParser.ProcedureDeclarationContext ctx) {
        // Skipping actual execution of procedures for now
        System.out.println("Skipping procedure declaration: " + ctx.identifier().getText());
        return null;
    }

    /** Helper method to evaluate an expression */
    private int evaluateExpression(pascalParser.ExpressionContext ctx) {
        if (ctx == null) return 0;
        String text = ctx.getText();
        if (variables.containsKey(text)) {
            return variables.get(text);
        } else if (constants.containsKey(text)) {
            return constants.get(text);
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return constants.getOrDefault(text, 0);
        }
    }

    /** Helper method to get variable/array value */
    private String getValue(String varName) {
        if (varName == null || varName.isEmpty()) {
            return "0"; // Return default value for empty inputs
        }
        if (variables.containsKey(varName)) {
            return String.valueOf(variables.get(varName));
        } else if (constants.containsKey(varName)) {
            return String.valueOf(constants.get(varName));
        } else if (varName.contains("[")) {
            // Handle array access: a[i]
            String arrayName = varName.substring(0, varName.indexOf("["));
            if (!varName.matches(".*\\[\\d+\\].*")) return "0";
        int index = Integer.parseInt(varName.substring(varName.indexOf("[") + 1, varName.indexOf("]")));
            if (arrays.containsKey(arrayName)) {
                return String.valueOf(arrays.get(arrayName)[index]);
            }
        }
        return "0"; // Default value for uninitialized variables or invalid accesses
    }
}
