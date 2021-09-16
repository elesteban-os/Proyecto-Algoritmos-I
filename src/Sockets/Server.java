package Sockets;

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

        while (true) {
            try {
            msg = input.readUTF();
            System.out.println(msg);

            output.writeUTF("move 5");

            } catch (IOException io){

            }
        }

    }
    public static void main(String[] args){
        try {
            Server server = new Server();
            server.start(1234);

        } catch (IOException io) {

        }

    }


}
