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
    Socket cliente;

    public void start(int puerto) throws IOException {
        ServerSocket server = new ServerSocket(puerto);

        Socket cliente = server.accept();

    }

}
