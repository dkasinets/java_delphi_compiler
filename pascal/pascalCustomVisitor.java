import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class pascalCustomVisitor extends pascalBaseVisitor<Void> {
    @Override
    public Void visitProgram(pascalParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitCompoundStatement(pascalParser.CompoundStatementContext ctx) {
        return visitChildren(ctx);
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
                    System.out.print(paramText.substring(1, paramText.length() - 1));
                } else {
                    // Handle numeric values
                    try {
                        int number = Integer.parseInt(paramText);
                        System.out.print(number);
                    } catch (NumberFormatException e) {
                        System.out.print(paramText); // Default to raw text if not a number
                    }
                }
            }
            if ("writeln".equalsIgnoreCase(procName)) {
                System.out.println();
            }
        }
        return null;
    }
}
