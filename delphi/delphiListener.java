// Generated from delphi.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link delphiParser}.
 */
public interface delphiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(delphiParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(delphiParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void enterVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void exitVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(delphiParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(delphiParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 */
	void enterConstructorImplementation(delphiParser.ConstructorImplementationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 */
	void exitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#destructorImplementation}.
	 * @param ctx the parse tree
	 */
	void enterDestructorImplementation(delphiParser.DestructorImplementationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#destructorImplementation}.
	 * @param ctx the parse tree
	 */
	void exitDestructorImplementation(delphiParser.DestructorImplementationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(delphiParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(delphiParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#methodImplementation}.
	 * @param ctx the parse tree
	 */
	void enterMethodImplementation(delphiParser.MethodImplementationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#methodImplementation}.
	 * @param ctx the parse tree
	 */
	void exitMethodImplementation(delphiParser.MethodImplementationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(delphiParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(delphiParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(delphiParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(delphiParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(delphiParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(delphiParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(delphiParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(delphiParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(delphiParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(delphiParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(delphiParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#writelnCall}.
	 * @param ctx the parse tree
	 */
	void enterWritelnCall(delphiParser.WritelnCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#writelnCall}.
	 * @param ctx the parse tree
	 */
	void exitWritelnCall(delphiParser.WritelnCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#objectCreation}.
	 * @param ctx the parse tree
	 */
	void enterObjectCreation(delphiParser.ObjectCreationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#objectCreation}.
	 * @param ctx the parse tree
	 */
	void exitObjectCreation(delphiParser.ObjectCreationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(delphiParser.ExpressionContext ctx);
}