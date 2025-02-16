import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class pascalCustomInterpreter {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java pascalCustomInterpreter <file.pas>");
            return;
        }

        try {
            // Read Pascal source file
            String fileName = args[0];
            CharStream input = CharStreams.fromFileName(fileName);

            // Lexer and parser
            pascalLexer lexer = new pascalLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            pascalParser parser = new pascalParser(tokens);

            // Parse the input
            ParseTree tree = parser.program(); // Start parsing from 'program' rule

            PrintableTree printableTree = new PrintableTree(tree);
            System.out.println(printableTree);

            // Walk and interpret the AST
            pascalCustomVisitor interpreter = new pascalCustomVisitor();
            interpreter.visit(tree);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
