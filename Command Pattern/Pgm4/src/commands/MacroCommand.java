package commands;

import java.util.Stack;

// Macro commands can nest other macro commands
// Undos are done in reverse order of execution
public class MacroCommand implements Command{
    private Stack<Command> commands = new Stack<>();

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        System.out.println("Begin Undoing Macro");
        System.out.println();
        for (int i = commands.size() - 1; i >= 0; i--) {
            Command command = commands.pop();
            command.undo();
            command.printForUndo();
            System.out.println();
        }
        System.out.println("End Undoing Macro");
    }

    public void add(Command command) {
        commands.push(command);
    }

    public void remove(Command command) {
        commands.remove(command);
    }

    @Override
    public void printForUndo() {
    }

}
