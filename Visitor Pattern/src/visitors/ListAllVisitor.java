package visitors;

import nodes.DirectoryNode;

public class ListAllVisitor implements Visitor{

    private String prefix = "   ";
    private String result;

    public ListAllVisitor() {
        result = "";
    }

    @Override
    public void visit(DirectoryNode node) {
        visit(node, prefix);
    }

   private void visit(DirectoryNode node, String prefix) {
        if (!node.getChildren().isEmpty()) {
            System.out.println(prefix + node.getName() + ":");
        } else {
            System.out.println(prefix + node.getName());
        }
        for (DirectoryNode child : node.getChildren()) {
            visit(child, prefix + "   ");
        }
    }
}
