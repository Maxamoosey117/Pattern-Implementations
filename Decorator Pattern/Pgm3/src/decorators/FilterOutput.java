package decorators;

import output.Output;

import java.util.function.Predicate;

public class FilterOutput implements Output {
    private Output output;
    private Predicate<String> filter;

    public FilterOutput(Output output, Predicate<String> filter) {
        this.output = output;
        this.filter = filter;
    }

    public void write(Object o) {
        if (filter.test(o.toString())) {
            output.write(o);
            // System.out.println("Passed filter: " + o.toString());
        }
    }
}

