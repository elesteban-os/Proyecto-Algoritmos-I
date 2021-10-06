package Sockets;

import java.io.DataOutputStream;
import java.io.IOException;

public class sender {
    private final DataOutputStream output;

    public sender (DataOutputStream output) {
        this.output = output;
    }

    public void startSender(String message) throws IOException {
        this.output.writeUTF(message);
    }
}
