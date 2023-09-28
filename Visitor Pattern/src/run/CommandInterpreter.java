package run;

import nodes.DirectoryNode;

import java.util.Scanner;

import visitors.*;

public class CommandInterpreter {
    private DirectoryNode root;
    private DirectoryNode currentDir;
    private Scanner scanner;

    public CommandInterpreter(DirectoryNode root, Scanner scanner) {
        this.root = root;
        this.currentDir = root;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.print(currentDir.getName() + "> ");
            String input = scanner.nextLine();

            if (input.equals("list")) {
                ListVisitor visitor = new ListVisitor();
                visitor.visit(currentDir);
            } else if (input.equals("listall")) {
                ListAllVisitor visitor = new ListAllVisitor();
                visitor.visit(currentDir);
            } else if (input.startsWith("chdir ")) {
                String dirName = input.substring(6);
                chdir(dirName);
            } else if (input.equals("up")) {
                up();
            } else if (input.equals("count")) {
                CountVisitor visitor = new CountVisitor();
                visitor.visit(currentDir);
            } else if (input.equals("countall")) {
                CountAllVisitor visitor = new CountAllVisitor();
                visitor.visit(currentDir);
            } else if (input.startsWith("find ")) {
                FindVisitor visitor = new FindVisitor(input.substring(5));
                visitor.visit(currentDir);
            } else if (input.equals("countleaves")) {
                CountLeavesVisitor visitor = new CountLeavesVisitor();
                visitor.visit(currentDir);
            } else if (input.equals("q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("invalid command");
            }
        }

        scanner.close();
    }

    public void chdir(String dirName) {
        for (DirectoryNode child : currentDir.getChildren()) {
            if (child.getName().equals(dirName) && child.getChildren() != null) {
                currentDir = child;
                return;
            }
        }
        System.out.println("no such directory");
    }

    public void up() {
        if (currentDir.getParent() != null) {
            currentDir = currentDir.getParent();
        }
    }

}
