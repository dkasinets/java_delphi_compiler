import org.antlr.v4.runtime.tree.*;

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
            String ruleName = pascalParser.ruleNames[((RuleNode) node).getRuleContext().getRuleIndex()];
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
        if (type >= 0 && type < pascalLexer.ruleNames.length) {
            String ruleName = pascalLexer.ruleNames[type];
            char id = ruleName.startsWith("T__") ? 'P' : ruleName.charAt(0);
            return id + "'" + node.getText() + "'";
        }
        return "'" + node.getText() + "'";
    }
}
