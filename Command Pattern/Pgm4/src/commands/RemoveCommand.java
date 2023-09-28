package commands;

import database.Database;

public class RemoveCommand implements Command{
    private Database database;
    private String key;
    private String value;
    boolean success;

    public RemoveCommand(Database database, String key) {
        this.database = database;
        this.key = key;
        success = false;
    }

    @Override
    public void execute() {
        value = database.get(key);
        success = database.remove(key);
    }

    @Override
    public void undo() {
        // if not removed, do nothing
        if (!success) {
            System.out.println("Cannot undo command that was not executed");
            return;
        }
        database.add(key, value);
    }

    @Override
    public void printForUndo() {
        System.out.println("Undid RemoveCommand");
        database.printContents();
    }
}
