package reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void createFile(String filename) {
        File outputFile = new File(filename);
        try {
            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File '" + filename + "' already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeLineToFile(String filename, String data) {
        File outputFile = new File(filename);

        FileWriter writer = null;
        try {
            writer = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(data);
            writer.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearFile(String filename) {
        try {
            new FileWriter(filename, false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
