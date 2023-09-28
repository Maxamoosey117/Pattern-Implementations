package nodes;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode {
    private String name;
    private List<DirectoryNode> children;
    private DirectoryNode parent;

    public DirectoryNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public String getName() {
        return name;
    }

    public List<DirectoryNode> getChildren() {
        return children;
    }

    public void addChild(DirectoryNode child) {
        child.setParent(this);
        children.add(child);
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public void setParent(DirectoryNode parent) {
        this.parent = parent;
    }

    public void print() {
        if (!children.isEmpty()) {
            System.out.print(name + ":");
        } else {
            System.out.print(name);
            return;
        }
        for (DirectoryNode child : children) {
            System.out.print("   ");
            child.print();
        }
    }
}

