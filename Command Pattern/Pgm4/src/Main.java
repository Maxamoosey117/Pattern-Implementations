import commands.*;
import database.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static Stack<Command> savedCommands = new Stack<>();
    private static ArrayList<Database> databases = new ArrayList<>();


    public static void main(String[] args) {
        // get commands file from args
        File commandsFile = new File(args[0]);
        System.out.println("Commands file: " + commandsFile.getAbsolutePath());
        Scanner scanner = null;
        ArrayList<String> commands = new ArrayList<>();
        try {
            scanner = new Scanner(commandsFile);
            // split commands by new line
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            commands.add(command);

        }
        runCommands(commands);
        System.out.println();
        printDatbases();
        // undo all commands
        for(int i = savedCommands.size() - 1; i >= 0; i--) {
            Command command = savedCommands.pop();
            command.undo();
            command.printForUndo();
            System.out.println();
        }
        printDatbases();
    }

    /*
     * <command> <database id> <key> [<value>] | B | E
     * Keys contain no spaces but values can.
     * The commands are A (for add), U (for update), and R (for remove).
     * The delimiters B and E begin and end a transaction, respectively,
     * which means their intervening contents form a macro command.
     * Macro commands should nest – i.e., macros can hold other macros.
     */
    public static void runCommands(ArrayList<String> commands) {
        boolean inMacro = false;
        int macroDepth = 0;
        for (String command : commands) {
            String[] commandArgs = command.split(" ");
            String commandType = commandArgs[0];
            if (commandType.equals("B")) {
                inMacro = true;
                macroDepth++;
                MacroCommand macroCommand = new MacroCommand();
                savedCommands.push(macroCommand);
                System.out.println("Beginning a Macro");
                continue;
            } else if (commandType.equals("E")) {
                macroDepth--;
                if (macroDepth == 0) {
                    inMacro = false;
                }
                MacroCommand macroCommand = (MacroCommand) savedCommands.peek();
                macroCommand.execute();
                System.out.println("Ending a Macro");
                continue;
            }
            String databaseId = commandArgs[1];
            String key = commandArgs[2];
            // value can have spaces
            String value = "";
            if (commandArgs.length > 3) {
                for (int i = 3; i < commandArgs.length; i++) {
                    value += commandArgs[i] + " ";
                }
                value = value.substring(0, value.length() - 1);
            }
            Database database = null;
            // check if database exists
            for (Database db : databases) {
                if (db.getId().equals(databaseId)) {
                    database = db;
                    break;
                }
            }
            // if database does not exist, create it
            if (database == null) {
                database = new Database(databaseId);
                databases.add(database);
            }

            switch (commandType) {
                case "A":
                    AddCommand addCommand = new AddCommand(database, key, value);
                    if (inMacro) {
                        MacroCommand macroCommand = (MacroCommand) savedCommands.peek();
                        macroCommand.add(addCommand);
                    } else {
                        addCommand.execute();
                        savedCommands.push(addCommand);
                    }
                    break;
                case "U":
                    UpdateCommand updateCommand = new UpdateCommand(database, key, value);
                    if (inMacro) {
                        MacroCommand macroCommand = (MacroCommand) savedCommands.peek();
                        macroCommand.add(updateCommand);
                    } else {
                        updateCommand.execute();
                        savedCommands.push(updateCommand);
                    }
                    break;
                case "R":
                    RemoveCommand removeCommand = new RemoveCommand(database, key);
                    if (inMacro) {
                        MacroCommand macroCommand = (MacroCommand) savedCommands.peek();
                        macroCommand.add(removeCommand);
                    } else {
                        removeCommand.execute();
                        savedCommands.push(removeCommand);
                    }
                    break;
            }
        }

    }

    private static void printDatbases() {
        System.out.println("Contents of Databases:");
        for (Database database : databases) {
            System.out.println("Database " + database.getId() + ":");
            database.printContents();
            System.out.println();
        }
    }
}

