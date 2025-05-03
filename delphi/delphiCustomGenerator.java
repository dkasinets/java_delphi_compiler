import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class delphiCustomGenerator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java delphiCustomGenerator <input.pas> <output.ll>");
            return;
        }

        try {
            String inputPath = args[0];
            String outputPath = args[1];

            // Read source
            CharStream input = CharStreams.fromFileName(inputPath);
            delphiLexer lexer = new delphiLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            delphiParser parser = new delphiParser(tokens);

            // Parse tree
            ParseTree tree = parser.program();

            // Save AST
            File inputFile = new File(inputPath);
            String baseName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
            String astFileName = "AST_Tree_" + baseName + ".txt";
            PrintableTree printableTree = new PrintableTree(tree);
            printableTree.saveToFile("./ast", astFileName);

            // Generate LLVM IR
            delphiCustomGeneratorVisitor generator = new delphiCustomGeneratorVisitor();
            String llvmIR = generator.visit(tree);

            // Write to output
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            try (PrintWriter out = new PrintWriter(outputFile)) {
                out.println(llvmIR);
            }

            System.out.println("    LLVM IR written to " + outputPath);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
