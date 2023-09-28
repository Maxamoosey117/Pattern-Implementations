package commands;

public interface Command {
    boolean success = false;
    void execute();
    void undo();
    void printForUndo();


}
