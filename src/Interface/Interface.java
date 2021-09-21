package Interface;

import Sockets.Client;
import Sockets.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Interface {
    private JFrame princialWindow = new JFrame("Math Socket");
    private JLabel title = new JLabel("Math Socket");
    private JTextField name = new JTextField();
    private JButton create = new JButton("Crear");
    private JButton join = new JButton("Unirse");

    private ArrayList<JLabel> labels;

    private JLabel C1 = new JLabel();
    private JLabel C2 = new JLabel();
    private JLabel C3 = new JLabel();
    private JLabel C4 = new JLabel();
    private JLabel C5 = new JLabel();
    private JLabel C6 = new JLabel();
    private JLabel C7 = new JLabel();
    private JLabel C8 = new JLabel();
    private JLabel C9 = new JLabel();
    private JLabel C10 = new JLabel();
    private JLabel C11= new JLabel();
    private JLabel C12 = new JLabel();
    private JLabel C13 = new JLabel();
    private JLabel C14 = new JLabel();
    private JLabel C15 = new JLabel();
    private JLabel C16 = new JLabel();
    private JLabel playerIcon1 = new JLabel();
    private JLabel playerIcon2 = new JLabel();
    private JLabel namePlayer1 = new JLabel();
    private JLabel namePlayer2 = new JLabel();
    private JLabel turnTitle = new JLabel();
    private JLabel playerTurn = new JLabel();
    private JButton LanzarDado = new JButton();

    private JLabel waiting = new JLabel("Esperando a que se una otro usuario.");

    private Server server;
    private Client client;
    private Interface thisInterface;

    ActionListener createActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            title.setVisible(false);
            name.setVisible(false);
            create.setVisible(false);
            join.setVisible(false);

            waiting.setVisible(true);
            JOptionPane.showMessageDialog(null, "Esperando a que se una un usuario");

            createServer();
        }
    };

    public void createServer(){
        try {
            server = new Server();
            server.start(1234, this.thisInterface);
        } catch (IOException e) {

        }
    }

    public void setWaitingClose(){
        this.waiting.setVisible(false);

        for (JLabel lab : this.labels) {
            lab.setVisible(true);
        }

        this.playerIcon1.setVisible(true);
        this.playerIcon2.setVisible(true);
        this.namePlayer1.setVisible(true);
        this.namePlayer2.setVisible(true);
        this.turnTitle.setVisible(true);
        this.playerTurn.setVisible(true);
        this.LanzarDado.setVisible(true);

    }

    ActionListener joinActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            title.setVisible(false);
            name.setVisible(false);
            create.setVisible(false);
            join.setVisible(false);

            waiting.setVisible(true);

        }
    };

    public Interface(){
        this.title.setBounds(95, 150, 300, 100);
        this.title.setFont(new Font("Bahnschrift", Font.PLAIN, 50));

        this.create.addActionListener(createActionListener);
        this.create.setBounds(50, 300, 150, 40);

        this.join.addActionListener(joinActionListener);
        this.join.setBounds(250, 300, 150, 40);

        this.name.setBounds(170, 250, 100, 30);

        this.waiting.setBounds(80, 150, 500, 100);
        this.waiting.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        this.waiting.setVisible(false);

        this.princialWindow.add(this.title);
        this.princialWindow.add(this.create);
        this.princialWindow.add(this.join);
        this.princialWindow.add(this.name);
        this.princialWindow.add(this.waiting);


        // GameBoard
        ImageIcon imagenCasilla = new ImageIcon(getClass().getResource("/Images/casilla.png"));
        ImageIcon imagenDado = new ImageIcon(getClass().getResource("/Images/dice.png"));
        ImageIcon imageUser = new ImageIcon(getClass().getResource("/Images/user.png"));

        C1.setBounds(70,130,100,100);
        C1.setIcon(imagenCasilla);

        C2.setBounds(162,130,100,100);
        C2.setIcon(imagenCasilla);

        C3.setBounds(254,130,100,100);
        C3.setIcon(imagenCasilla);

        C4.setBounds(346,130,100,100);
        C4.setIcon(imagenCasilla);

        C5.setBounds(70,222,100,100);
        C5.setIcon(imagenCasilla);

        C6.setBounds(162,222,100,100);
        C6.setIcon(imagenCasilla);

        C7.setBounds(254,222,100,100);
        C7.setIcon(imagenCasilla);

        C8.setBounds(346,222,100,100);
        C8.setIcon(imagenCasilla);

        C9.setBounds(70,314,100,100);
        C9.setIcon(imagenCasilla);

        C10.setBounds(162,314,100,100);
        C10.setIcon(imagenCasilla);

        C11.setBounds(254,314,100,100);
        C11.setIcon(imagenCasilla);

        C12.setBounds(346,314,100,100);
        C12.setIcon(imagenCasilla);

        C13.setBounds(70,406,100,100);
        C13.setIcon(imagenCasilla);

        C14.setBounds(162,406,100,100);
        C14.setIcon(imagenCasilla);

        C15.setBounds(254,406,100,100);
        C15.setIcon(imagenCasilla);

        C16.setBounds(346,406,100,100);
        C16.setIcon(imagenCasilla);

        playerIcon1.setBounds(40,5,100,100);
        playerIcon1.setIcon(imageUser);

        namePlayer1.setBounds(30,75,100,50);
        namePlayer1.setText("PlayerName1");

        playerIcon2.setBounds(376,5,100,100);
        playerIcon2.setIcon(imageUser);

        namePlayer2.setBounds(366,75,100,50);
        namePlayer2.setText("NamePlayer2");

        turnTitle.setBounds(208,20,100,50);
        turnTitle.setText("TURNO DE:");
        turnTitle.getFont();

        playerTurn.setBounds(215,40,100,50);
        playerTurn.setText("Player1");

        LanzarDado.setBounds(215,505,50,50);
        LanzarDado.setIcon(imagenDado);

        this.princialWindow.add(this.C1);
        this.princialWindow.add(this.C2);
        this.princialWindow.add(this.C3);
        this.princialWindow.add(this.C4);
        this.princialWindow.add(this.C5);
        this.princialWindow.add(this.C6);
        this.princialWindow.add(this.C7);
        this.princialWindow.add(this.C8);
        this.princialWindow.add(this.C9);
        this.princialWindow.add(this.C10);
        this.princialWindow.add(this.C11);
        this.princialWindow.add(this.C12);
        this.princialWindow.add(this.C13);
        this.princialWindow.add(this.C14);
        this.princialWindow.add(this.C15);
        this.princialWindow.add(this.C16);
        this.princialWindow.add(this.playerIcon1);
        this.princialWindow.add(this.playerIcon2);
        this.princialWindow.add(this.namePlayer1);
        this.princialWindow.add(this.namePlayer2);
        this.princialWindow.add(this.turnTitle);
        this.princialWindow.add(this.playerTurn);
        this.princialWindow.add(this.LanzarDado);

        this.labels = new ArrayList<JLabel>();

        this.labels.add(C1);
        this.labels.add(C2);
        this.labels.add(C3);
        this.labels.add(C4);
        this.labels.add(C5);
        this.labels.add(C6);
        this.labels.add(C7);
        this.labels.add(C8);
        this.labels.add(C9);
        this.labels.add(C10);
        this.labels.add(C11);
        this.labels.add(C12);
        this.labels.add(C13);
        this.labels.add(C14);
        this.labels.add(C15);
        this.labels.add(C16);

        for (JLabel lab : this.labels) {
            lab.setVisible(false);
        }

        this.playerIcon1.setVisible(false);
        this.playerIcon2.setVisible(false);
        this.namePlayer1.setVisible(false);
        this.namePlayer2.setVisible(false);
        this.turnTitle.setVisible(false);
        this.playerTurn.setVisible(false);
        this.LanzarDado.setVisible(false);

        this.princialWindow.setSize(505, 610);
        this.princialWindow.setLayout(null);
        this.princialWindow.setResizable(false);
        this.princialWindow.setVisible(true);
        this.princialWindow.setDefaultCloseOperation(princialWindow.EXIT_ON_CLOSE);

    }

    public void setInterface(Interface inter){
        this.thisInterface = inter;
    }

    public static void main(String[] args) {
        Interface interface1 = new Interface();
        interface1.setInterface(interface1);
    }

}