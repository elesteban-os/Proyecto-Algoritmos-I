package Sockets;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    DataInputStream input;
    DataOutputStream output;
    ServerSocket server;
    Socket serverS;
    Socket client;
    Socket client2;
    reader read;
    sender send;

    JButton but1;
    JLabel label2;

    public void start(int puerto) throws IOException {
        String msg;

        ServerSocket server = new ServerSocket(puerto);
        Socket serverS = new Socket("localhost", puerto);
        System.out.println("Servidor iniciado, esperando cliente");
        Socket client = server.accept();
        System.out.println("Cliente conectado");
        Socket client2 = server.accept();
        System.out.println("Cliente conectado");

        this.input = new DataInputStream(client2.getInputStream());
        this.output = new DataOutputStream(client2.getOutputStream());

        this.read = new reader(input, label2, but1);
        new Thread(this.read).start();

        this.send = new sender(output);

    }

    public String lastRead() throws IOException {
        return this.read.getToReturn();
    }

    public void startSendServ(String message) throws IOException {
            this.send.startSender(message);
    }

    public void setLabels(JLabel lab2, JButton but1){
        this.label2 = lab2;
        this.but1 = but1;
    }

    public static void main(String[] args){
        try {
            Server server = new Server();
            server.start(1234);

        } catch (IOException io) {

        }

    }


}
