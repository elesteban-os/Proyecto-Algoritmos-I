package Sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class sender {
    private Scanner scan;
    private DataOutputStream output;

    public sender (DataOutputStream output) {
        this.output = output;
        this.scan = new Scanner(System.in);
    }

    public void startSender() throws IOException {
        this.output.writeUTF(scan.nextLine());
    }


}
