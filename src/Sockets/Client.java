package Sockets;

import Interface.Interface;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private reader read;
    private sender send;
    private Interface interfaz;

    public void startClient(int port, Interface interfaz) throws IOException {
        this.client = new Socket("localhost", port);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        this.interfaz = interfaz;
        this.read = new reader(this.input, this.interfaz);

        new Thread(this.read).start();

        this.send = new sender(this.output);

    }

    public void startSendCli(String message) throws IOException {
        this.send.startSender(message);
    }

    /*public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startClient(1234);
        } catch(IOException io){

        }

    }*/

}
