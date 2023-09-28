package visitors;

import nodes.DirectoryNode;

public class CountVisitor implements Visitor {
    private int count;

    public CountVisitor() {
        count = 0;
    }

    @Override
    public void visit(DirectoryNode currentDir) {

        for (DirectoryNode child : currentDir.getChildren()) {
            if (child.getChildren().isEmpty()) {
                count++;
            }
        }
        System.out.println(count);
    }
}
