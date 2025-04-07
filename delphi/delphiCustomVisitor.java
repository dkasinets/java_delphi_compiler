import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;

public class delphiCustomVisitor extends delphiBaseVisitor<Void> {
    private final Map<String, Integer> globalValues = new HashMap<>();
    private final Map<String, delphiParser.FunctionImplementationContext> functionDefs = new HashMap<>();
    private final Map<String, delphiParser.MethodImplementationContext> methodDefs = new HashMap<>();
    private boolean shouldContinue = false;
    private boolean shouldBreak = false;
    private Deque<Map<String, Integer>> scopeStack = new ArrayDeque<>();

    public delphiCustomVisitor() {
        scopeStack.push(globalValues); // global scope is base
    }

    private Map<String, Integer> currentScope() {
        return scopeStack.peek();
    }

    @Override
    public Void visitFunctionImplementation(delphiParser.FunctionImplementationContext ctx) {
        functionDefs.put(ctx.IDENT().getText(), ctx);
        return null;
    }

    @Override
    public Void visitMethodImplementation(delphiParser.MethodImplementationContext ctx) {
        methodDefs.put(ctx.IDENT(1).getText(), ctx);
        return null;
    }

    @Override
    public Void visitMethodCall(delphiParser.MethodCallContext ctx) {
        String methodName = ctx.IDENT(1).getText();
        if (methodDefs.containsKey(methodName)) {
            executeMethod(methodName);
        }
        return null;
    }

    private void executeMethod(String methodName) {
        delphiParser.MethodImplementationContext ctx = methodDefs.get(methodName);
        Map<String, Integer> localScope = new HashMap<>(currentScope());
        scopeStack.push(localScope);

        if (ctx.variableDeclaration() != null) {
            for (delphiParser.VariableDeclarationContext decl : ctx.variableDeclaration()) {
                declareVarsInScope(decl, localScope);
            }
        }

        for (delphiParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }

        scopeStack.pop();
    }

    private int executeFunction(String name) {
        delphiParser.FunctionImplementationContext ctx = functionDefs.get(name);
        if (ctx == null) return 0;

        Map<String, Integer> localScope = new HashMap<>(currentScope());
        localScope.put("Result", 0);
        scopeStack.push(localScope);

        if (ctx.variableDeclaration() != null) {
            for (delphiParser.VariableDeclarationContext decl : ctx.variableDeclaration()) {
                declareVarsInScope(decl, localScope);
            }
        }

        for (delphiParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }

        int result = scopeStack.peek().getOrDefault("Result", 0);
        scopeStack.pop();
        return result;
    }

    private void declareVarsInScope(delphiParser.VariableDeclarationContext ctx, Map<String, Integer> scope) {
        for (delphiParser.VarDeclContext decl : ctx.varDecl()) {
            for (TerminalNode id : decl.IDENT()) {
                scope.put(id.getText(), 0);
            }
        }
    }

    @Override
    public Void visitAssignment(delphiParser.AssignmentContext ctx) {
        if (shouldContinue || shouldBreak) return null;

        String var = ctx.IDENT().getText();
        int evaluated = getValue(ctx.expression());

        for (Map<String, Integer> scope : scopeStack) {
            if (scope.containsKey(var)) {
                scope.put(var, evaluated);
                return null;
            }
        }

        currentScope().put(var, evaluated);
        return null;
    }

    @Override
    public Void visitWritelnCall(delphiParser.WritelnCallContext ctx) {
        if (shouldContinue || shouldBreak) return null;
        System.out.println(getValue(ctx.expression()));
        return null;
    }

    @Override
    public Void visitWhileStatement(delphiParser.WhileStatementContext ctx) {
        while (evaluateCondition(ctx.expression())) {
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

    private boolean evaluateCondition(delphiParser.ExpressionContext ctx) {
        if (ctx instanceof delphiParser.EqualityExpressionContext) {
            delphiParser.EqualityExpressionContext eq = (delphiParser.EqualityExpressionContext) ctx;
            return getValue(eq.expression(0)) == getValue(eq.expression(1));
        }
        return false;
    }

    @Override
    public Void visitForStatement(delphiParser.ForStatementContext ctx) {
        String loopVar = ctx.IDENT().getText();
        int from = getValue(ctx.expression(0));
        int to = getValue(ctx.expression(1));

        for (int i = from; i <= to; i++) {
            currentScope().put(loopVar, i);
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
    public Void visitProgram(delphiParser.ProgramContext ctx) {
        return visitChildren(ctx);
    }

    private int getValue(delphiParser.ExpressionContext ctx) {
        if (ctx instanceof delphiParser.IdentifierExpressionContext) {
            String name = ctx.getText();
            if (functionDefs.containsKey(name)) {
                return executeFunction(name);
            }
            for (Map<String, Integer> scope : scopeStack) {
                if (scope.containsKey(name)) return scope.get(name);
            }
            return 0;
        } else if (ctx instanceof delphiParser.IntegerExpressionContext) {
            return Integer.parseInt(ctx.getText());
        }
        return 0;
    }
}