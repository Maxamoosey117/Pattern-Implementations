package visitors;

import nodes.DirectoryNode;

public class ListVisitor implements Visitor{
    private String result;

    public ListVisitor() {
        result = "";
    }
    @Override
    public void visit(DirectoryNode node) {
        for (DirectoryNode child : node.getChildren()) {
            result += child.getName() + " ";
        }
        System.out.println(result);
    }
}
