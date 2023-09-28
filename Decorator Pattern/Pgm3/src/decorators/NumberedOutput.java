package decorators;

import output.Output;

public class NumberedOutput implements Output {
    private Output output;
    private int lineNumber = 0;

    public NumberedOutput(Output output) {
        this.output = output;
    }

    public void write(Object o) {
        String lineNumberString = String.format("%5d: ", ++lineNumber);
        String message = lineNumberString + o.toString();
        output.write(message);

    }
}

