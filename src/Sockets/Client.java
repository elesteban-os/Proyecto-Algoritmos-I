package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private reader read;
    private sender send;

    public void startClient(int port) throws IOException {
        this.client = new Socket("localhost", port);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        this.read = new reader(this.input);
        this.send = new sender(this.output);

        while (true) {
            for (int i = 0; i < 3; i++) {
                this.send.startSender();
            }

            for (int i = 0; i < 3; i++) {
                String message = this.read.startRead();
                System.out.println(message);
            }
        }

    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.startClient(1234);
        } catch(IOException io){

        }

    }


}
