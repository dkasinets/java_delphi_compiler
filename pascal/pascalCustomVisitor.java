import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class pascalCustomVisitor extends pascalBaseVisitor<Void> {

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
    public Void visitProcedureStatement(pascalParser.ProcedureStatementContext ctx) {
        // Handling writeln('Hi');
        String procName = ctx.identifier().getText();
        if ("writeln".equalsIgnoreCase(procName) && ctx.parameterList() != null) {
            List<pascalParser.ActualParameterContext> params = ctx.parameterList().actualParameter();
            for (pascalParser.ActualParameterContext param : params) {
                System.out.println(param.getText().replace("'", "")); // Remove Pascal-style quotes
            }
        }
        return null;
    }
}
