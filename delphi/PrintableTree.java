import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintableTree {
    private final ParseTree tree;

    public PrintableTree(ParseTree tree) {
        this.tree = tree;
    }

    @Override
    public String toString() {
        return treeString(tree, "");
    }

    private String treeString(ParseTree node, String prefix) {
        if (node instanceof RuleNode) {
            String ruleName = delphiParser.ruleNames[((RuleNode) node).getRuleContext().getRuleIndex()];
            StringBuilder builder = new StringBuilder(ruleName);

            for (int i = 0; i < node.getChildCount(); i++) {
                boolean atEnd = (i == node.getChildCount() - 1);
                String symbol = atEnd ? "└──" : "├──";

                ParseTree child = node.getChild(i);
                String childSymbol = atEnd ? " " : "│";
                String childStr = treeString(child, prefix + childSymbol + "   ");

                builder.append("\n").append(prefix).append(symbol).append(" ").append(childStr);
            }

            return builder.toString();
        } else if (node instanceof TerminalNode) {
            return visitTerminal((TerminalNode) node);
        }
        return "";
    }

    private String visitTerminal(TerminalNode node) {
        int type = node.getSymbol().getType();
        if (type >= 0 && type < delphiLexer.ruleNames.length) {
            String ruleName = delphiLexer.ruleNames[type];
            char id = ruleName.startsWith("T__") ? 'P' : ruleName.charAt(0);
            return id + "'" + node.getText() + "'";
        }
        return "'" + node.getText() + "'";
    }

    public void saveToFile(String path, String name) {
        String output = toString();
        Path dirPath = Paths.get(path);
        Path filePath = dirPath.resolve(name);

        try {
            // Ensure the directory exists
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Write to the file
            Files.write(filePath, output.getBytes());
            System.out.println("AST tree saved to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing AST tree to file: " + e.getMessage());
        }
    }
}
