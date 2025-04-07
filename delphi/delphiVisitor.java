// Generated from delphi.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link delphiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface delphiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#topLevelDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDeclaration(delphiParser.TopLevelDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(delphiParser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#destructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorImplementation(delphiParser.DestructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(delphiParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#methodImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodImplementation(delphiParser.MethodImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionImplementation(delphiParser.FunctionImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(delphiParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(delphiParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(delphiParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(delphiParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(delphiParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(delphiParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(delphiParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#writelnCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWritelnCall(delphiParser.WritelnCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#objectCreation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectCreation(delphiParser.ObjectCreationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectCreationExpression}
	 * labeled alternative in {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectCreationExpression(delphiParser.ObjectCreationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(delphiParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(delphiParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpression(delphiParser.IntegerExpressionContext ctx);
}