package Sockets;

import Interface.Interface;

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

    Interface interface1;
    /**
     * @param puerto es el puerto que permitirá unirse a la partida
     * @param inter es la interfaz gráfica
     * @throws IOException detecta excepciones de E/S
     */
    public void start(int puerto, Interface inter) throws IOException {
        this.interface1 = inter;

        ServerSocket server = new ServerSocket(puerto);
        Socket serverS = new Socket("localhost", puerto);
        System.out.println("Servidor iniciado, esperando cliente");
        Socket client = server.accept();
        System.out.println("Cliente conectado");
        Socket client2 = server.accept();
        System.out.println("Cliente conectado");

        inter.setWaitingClose();

        this.input = new DataInputStream(client2.getInputStream());
        this.output = new DataOutputStream(client2.getOutputStream());

        this.read = new reader(input, inter);
        new Thread(this.read).start();

        this.send = new sender(output);

    }
    /**
     * @param message es el mensaje a enviar.
     * @throws IOException detecta excepciones de E/S
     */
    public void startSendServ(String message) throws IOException {
            this.send.startSender(message);
    }

}
