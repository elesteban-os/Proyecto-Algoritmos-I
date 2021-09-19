package Sockets;

import javax.swing.*;
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
    private JButton but1;
    private JLabel label2;
    private interfaz1clien interfaz;

    public void startClient(int port, interfaz1clien interfez) throws IOException {
        this.client = new Socket("localhost", port);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        this.interfaz = interfez;
        this.read = new reader(this.input, this.interfaz.getRecibiendo(), this.interfaz.getBotonDado());

        new Thread(this.read).start();

        this.send = new sender(this.output);



        while (true) {
            this.send.scanSender();
        }
    }

    public String startReadCli() throws IOException {
        return this.read.getToReturn();
    }

    public void startSendCli(String message) throws IOException {
        this.send.startSender(message);
    }

    public void setLabels(JLabel recibiendo, JButton botonDado) {
        this.label2 = recibiendo;
        this.but1 = botonDado;
    }

    /*public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startClient(1234);
        } catch(IOException io){

        }

    }*/

}
