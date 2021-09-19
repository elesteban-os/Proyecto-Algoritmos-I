package Sockets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class interfaz1clien {
        JFrame ventana = new JFrame("cliente");
        JButton botonDado = new JButton("dado");
        JLabel enviando = new JLabel("Enviando: ");
        JLabel recibiendo = new JLabel("Recibiendo: ");
        JButton next = new JButton("Next");

        Client client;

        ActionListener escuchador = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("Dado");
                try {
                    client.startSendCli("dice 2");
                    enviando.setText("Enviando: dice 2");

                    botonDado.setEnabled(false);
                    next.setEnabled(true);
                } catch(IOException io){

                }

            }

        };

    ActionListener escuchadorNext = new ActionListener(){
        public void actionPerformed(ActionEvent event) {
            System.out.println("Next");
            try {
                client.startSendCli("next 0");
                enviando.setText("Enviando: next 0");

                next.setEnabled(false);
            } catch(IOException io){

            }
        }
    };

        public JLabel getRecibiendo(){
            return this.recibiendo;
        }

        public JButton getBotonDado(){
            return this.botonDado;
        }

        public interfaz1clien(Client client){
            this.client = client;
            abririnterfaz();
        }

        public void abririnterfaz(){

            this.botonDado.setBounds(10, 10, 100, 20);
            this.botonDado.addActionListener(escuchador);

            this.enviando.setBounds(10, 30, 250, 20);
            this.recibiendo.setBounds(10, 50, 250, 20);

            this.next.setBounds(150, 10, 100, 20);
            this.next.addActionListener(escuchadorNext);

            this.botonDado.setEnabled(false);
            this.next.setEnabled(false);

            this.ventana.add(this.next);
            this.ventana.add(this.botonDado);
            this.ventana.add(this.enviando);
            this.ventana.add(this.recibiendo);


            this.ventana.setSize(400, 400);
            this.ventana.setLayout(null);
            this.ventana.setResizable(false);
            this.ventana.setVisible(true);
            this.ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);

        }

        public static void main(String[] args) {
            Client client = new Client();
            interfaz1clien interfaz = new interfaz1clien(client);
            client.setLabels(interfaz.getRecibiendo(), interfaz.getBotonDado());
            try {
                client.startClient(1234, interfaz);
            } catch(IOException io){

            }

        }

}
