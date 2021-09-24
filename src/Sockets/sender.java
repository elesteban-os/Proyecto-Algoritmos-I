package Sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class sender {
    private final DataOutputStream output;
    private final Scanner scan;

    public sender (DataOutputStream output) {
        this.output = output;
        this.scan = new Scanner(System.in);
    }

    public void scanSender() throws IOException {
        String message = this.scan.nextLine();
        this.output.writeUTF(message);
    }

    public void startSender(String message) throws IOException {
        this.output.writeUTF(message);
    }
}
