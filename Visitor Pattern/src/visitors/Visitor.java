package visitors;

import nodes.DirectoryNode;

public interface Visitor {
    public void visit(DirectoryNode node);
}
