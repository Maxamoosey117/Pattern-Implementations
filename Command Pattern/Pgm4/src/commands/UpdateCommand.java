package commands;

import database.Database;

public class UpdateCommand implements Command{
    private Database database;
    private String key;
    private String value;
    private String newValue;
    boolean success;

    public UpdateCommand(Database database, String key, String newValue) {
        this.database = database;
        this.key = key;
        this.newValue = newValue;
        success = false;
    }

    @Override
    public void execute() {
        value = database.get(key);
        success = database.update(key, newValue);
    }

    @Override
    public void undo() {
        // if not updated, do nothing
        if (!success) {
            System.out.println("Cannot undo command that was not executed");
            return;
        }
        database.update(key, value);
    }

    @Override
    public void printForUndo() {
        System.out.println("Undid UpdateCommand");
        database.printContents();
    }
}
