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
    private Scanner scan;
    private reader read;
    private sender send;

    public void startClient(int port) throws IOException {
        this.scan = new Scanner(System.in);

        Socket client = new Socket("localhost", port);
        DataInputStream input = new DataInputStream(client.getInputStream());
        DataOutputStream output = new DataOutputStream(client.getOutputStream());



        while (true){
            try{
            String msj = scan.nextLine();
            output.writeUTF(msj);

            } catch (IOException io) {

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
