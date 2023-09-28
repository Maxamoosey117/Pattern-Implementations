package commands;

import database.Database;

public class AddCommand implements Command {
    private Database database;
    private String key;
    private String value;
    boolean success;

    public AddCommand(Database database, String key, String value) {
        this.database = database;
        this.key = key;
        this.value = value;
        success = false;
    }

    @Override
    public void execute() {
        success = database.add(key, value);
    }

    @Override
    public void undo() {
        // if not added, do nothing
        if (!success) {
            System.out.println("Cannot undo command that was not executed");
            return;
        }
        database.remove(key);
    }

    @Override
    public void printForUndo() {
        System.out.println("Undid AddCommand");
        database.printContents();
    }
}
