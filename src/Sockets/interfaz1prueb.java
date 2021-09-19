package Sockets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class interfaz1prueb {
        JFrame ventana = new JFrame("prueba");
        JButton botonDado = new JButton("dado");
        JLabel enviando = new JLabel("Enviando: ");
        JLabel recibiendo = new JLabel("Recibiendo: ");
        JButton next = new JButton("Next");

        Server server;

        ActionListener escuchador = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("Dado");
                try {
                    server.startSendServ("dice 2");
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
                    server.startSendServ("next 0");
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

        public interfaz1prueb(Server server){
            this.server = server;
            abririnterfaz();
        }

        public void abririnterfaz(){

            this.botonDado.setBounds(10, 10, 100, 20);
            this.botonDado.addActionListener(escuchador);

            this.enviando.setBounds(10, 30, 250, 20);
            this.recibiendo.setBounds(10, 50, 250, 20);

            this.next.setBounds(150, 10, 100, 20);
            this.next.addActionListener(escuchadorNext);

            this.ventana.add(this.botonDado);
            this.ventana.add(this.enviando);
            this.ventana.add(this.recibiendo);
            this.ventana.add(this.next);


            this.ventana.setSize(400, 400);
            this.ventana.setLayout(null);
            this.ventana.setResizable(false);
            this.ventana.setVisible(true);
            this.ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);

        }

        public static void main(String[] args) {
            Server server = new Server();
            interfaz1prueb interfaz = new interfaz1prueb(server);
            server.setLabels(interfaz.getRecibiendo(), interfaz.getBotonDado());
            try {
                server.start(1234);
            } catch(IOException io){

            }

        }

}
