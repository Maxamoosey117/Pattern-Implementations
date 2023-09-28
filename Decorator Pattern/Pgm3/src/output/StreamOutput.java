package output;

import java.io.*;

public class StreamOutput implements Output {
    private Writer sink;
    public StreamOutput(Writer stream) {
        sink = stream;
    }
    public void write(Object o) {
        try {
            sink.write(o.toString()+"\n");
            sink.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}