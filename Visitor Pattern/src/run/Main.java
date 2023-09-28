package run;

import nodes.DirectoryNode;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input file name that contains directory tree: ");
        String fileName = scanner.nextLine();

        DirectoryNode root = null;
        try {
            root = readDirectoryTree(fileName);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            System.exit(1);
        }

        CommandInterpreter interpreter = new CommandInterpreter(root, scanner);
        interpreter.run();

        scanner.close();

    }

    public static DirectoryNode readDirectoryTree(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String rootLine = scanner.nextLine();
        int rootDepth = checkDepth(rootLine);
        String rootName = rootLine.substring(rootDepth, rootLine.indexOf(":"));
        int currentDepth = rootDepth;

        DirectoryNode root = new DirectoryNode(rootName);
        DirectoryNode current = root;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(":")) { // if line contains a colon, it is a directory
                int depth = checkDepth(line);
                String name = line.substring(depth, line.indexOf(":"));

                // if depth is less than current depth, go up
                while (depth <= currentDepth) {
                    current = current.getParent();
                    currentDepth -= 3;
                }
                currentDepth = depth;
                name = name.replaceAll("\\s+", "");
                DirectoryNode node = new DirectoryNode(name);
                current.addChild(node);
                current = node;
            } else { // if line does not contain a colon, it is a file
                int depth = checkDepth(line);
                String name = line.substring(depth);

                // if depth is less than current depth, go up
                while (depth <= currentDepth) {
                    current = current.getParent();
                    currentDepth -= 3;
                }
                name = name.replaceAll("\\s+", "");
                DirectoryNode node = new DirectoryNode(name);
                current.addChild(node);
            }
        }
        scanner.close();
        return root;
    }

    public static int checkDepth(String myString) {
        int spaceCount = 0;

        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == ' ') {
                spaceCount++;
            } else {
                break;
            }
        }
        return spaceCount;
    }


}