package decorators;

import output.Output;

public class TeeOutput implements Output {
    private Output output1;
    private Output output2;

    public TeeOutput(Output output1, Output output2) {
        this.output1 = output1;
        this.output2 = output2;
    }

    public void write(Object o) {
        output1.write(o);
        output2.write(o);
    }
}

