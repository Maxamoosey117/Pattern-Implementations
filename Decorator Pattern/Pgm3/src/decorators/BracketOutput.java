package decorators;

import output.Output;

public class BracketOutput implements Output {
    private Output output;

    public BracketOutput(Output output) {
        this.output = output;
    }

    public void write(Object o) {
        String message = "[" + o.toString() + "]";
        output.write(message);

    }
}


