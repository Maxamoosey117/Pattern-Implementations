package visitors;

import nodes.DirectoryNode;

public class CountLeavesVisitor implements Visitor{

    private int count;

    public CountLeavesVisitor() {
        count = 0;
    }

    @Override
    public void visit(DirectoryNode node) {
        System.out.println(visit(node, 0));
    }

    private int visit(DirectoryNode node, int count) {
        for (DirectoryNode child : node.getChildren()) {
            if (child.getChildren().isEmpty()) {
                count++;
            }
            else {
                count = visit(child, count);
            }
        }
        return count;
    }

    public int getCount() {
        return count;
    }
}
