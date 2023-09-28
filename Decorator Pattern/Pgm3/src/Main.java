import decorators.BracketOutput;
import decorators.FilterOutput;
import decorators.NumberedOutput;
import decorators.TeeOutput;
import output.Output;
import output.StreamOutput;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        // get input file check if exists

        Scanner scanner = new Scanner(System.in);
        String inputFileName = "";
        File inputFile = new File(inputFileName);
        while (inputFileName.length() == 0 || !inputFile.exists()) {
            System.out.print("Please choose a file to read from: ");
            inputFileName = scanner.nextLine();
            inputFile = new File(inputFileName);
            if (!inputFile.exists()) {
                System.out.println("File does not exist");
            }
        }

        // menu for decorators
        ArrayList<Integer> savedChoices = new ArrayList<Integer>();
        while (savedChoices.size() == 0) {
            System.out.println("Choose 1 or more decorators:");
            System.out.println("1. TeeOutput");
            System.out.println("2. NumberedOutput");
            System.out.println("3. BracketOutput");
            System.out.println("4. FilterOutput");


            // get user input and check for correctness
            System.out.print("Enter your choice(s): ");
            String choices = scanner.nextLine();


            for (int i = 0; i < choices.length(); i++) {
                int choice = Integer.parseInt(choices.substring(i, i + 1));
                if (choice > 0 && choice < 5) {
                    savedChoices.add(choice);
                }
            }
        }
        savedChoices.sort(Comparator.naturalOrder());
        System.out.println("You chose: " + savedChoices);

        // create decorators, ORDER IS IMPORTANT

        // Create PrintWriter
        Writer writer = new PrintWriter(System.out);
        Output output = new StreamOutput(writer);
        for (int choice : savedChoices) {
            switch (choice) {
                case 1:
                    // get a file to write to
                    System.out.print("Please choose a file to write to: ");
                    String outputFileName = scanner.nextLine();

                    // create writer
                    Writer fileWriter = null;
                    try {
                        fileWriter = new FileWriter(outputFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    output = new TeeOutput(output, new StreamOutput(fileWriter));
                    break;
                case 2:
                    output = new NumberedOutput(output);
                    break;
                case 3:
                    output = new BracketOutput(output);
                    break;
                case 4:
                    // provide options for has digits or has the letter a

                    String filterChoice = "";
                    while (!filterChoice.matches("[1-2]")) {
                        System.out.println("Choose a filter:");
                        System.out.println("1. Has digits");
                        System.out.println("2. Has the letter j");
                        System.out.print("Enter your choice: ");
                        filterChoice = scanner.nextLine();
                    }
                    int filter = Integer.parseInt(filterChoice);
                    Predicate<String> predicate = null;
                    // generate predicates
                    switch (filter) {
                        // has digits
                        case 1:
                            predicate = s -> {
                                for (char c : s.toCharArray()) {
                                    if (Character.isDigit(c))
                                        return true;
                                }
                                return false;
                            };
                            break;
                        // has letter j
                        case 2:
                            predicate = s -> {
                                for (char c : s.toCharArray()) {
                                    if (Character.isLetter(c) && c == 'j')
                                        return true;
                                }
                                return false;
                            };
                            break;
                    }
                    output = new FilterOutput(output, predicate);
                    break;
            }
        }
        // for each line in file, write to output
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                output.write(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}