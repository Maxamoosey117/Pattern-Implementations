package visitors;

import nodes.DirectoryNode;

public class FindVisitor implements Visitor{
    private String name;
    private String result;

    public FindVisitor(String name) {
        this.name = name;
        result = "";
    }

    @Override
    public void visit(DirectoryNode node) {
        if (node.getName().equals(name)) {
            if (node.getChildren().isEmpty()) {
                result = "File found";
            }
            else {
                result = "Directory found";
            }
            System.out.println(result);
            return;
        }
        for (DirectoryNode child : node.getChildren()) {
            visit(child);
        }
    }
}
