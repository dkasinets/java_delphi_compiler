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
	 * Visit a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(delphiParser.ClassDeclarationContext ctx);
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
	 * Visit a parse tree produced by {@link delphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
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
	 * Visit a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(delphiParser.StatementContext ctx);
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
	 * Visit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(delphiParser.ExpressionContext ctx);
}