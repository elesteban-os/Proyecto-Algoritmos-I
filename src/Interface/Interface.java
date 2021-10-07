package Interface;

import Sockets.Client;
import Sockets.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import List.*;

/**
 *
 */
public class Interface {
    private JFrame principalWindow = new JFrame("Math Socket");
    private JLabel title = new JLabel("Math Socket");
    private JTextField name = new JTextField();
    private JButton create = new JButton("Crear");
    private JButton join = new JButton("Unirse");

    private JLabel playerIcon1 = new JLabel();
    private JLabel playerIcon2 = new JLabel();
    private JLabel namePlayer1 = new JLabel();
    private JLabel namePlayer2 = new JLabel();
    private JLabel turnTitle = new JLabel();
    private JLabel playerTurn = new JLabel();
    private JButton LanzarDado = new JButton();
    private JLabel lastDice = new JLabel();
    private JLabel Avatar2 = new JLabel();
    private JLabel Avatar1 = new JLabel();

    private JLabel waiting = new JLabel("Esperando a que se una otro usuario.");

    private Server server;
    private Client client;
    private Interface thisInterface;

    private DoubleLinkedList board = new DoubleLinkedList();

    private String lastMessage;

    private Player player;

    private DoubleNode square;
    private DoubleNode square2;

    ImageIcon imgPeon1 = new ImageIcon(getClass().getResource("/Images/peon1.png"));
    ImageIcon imgPeon2 = new ImageIcon(getClass().getResource("/Images/peon2.png"));

    private int casilla1 = 1;
    private int line1 = 1;

    private String p1 = this.namePlayer1.getText(); 
    private String p2 = this.namePlayer2.getText(); 


    ActionListener createActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            title.setVisible(false);
            name.setVisible(false);
            create.setVisible(false);
            join.setVisible(false);

            waiting.setVisible(true);
            JOptionPane.showMessageDialog(null, "Esperando a que se una un usuario");

            try {
                createServer();
            } catch (IOException io){

            }
        }
    };

    public void createServer() throws IOException {

        server = new Server();
        server.start(1234, this.thisInterface);

        this.namePlayer1.setText(this.name.getText());
        this.server.startSendServ("name "+this.namePlayer1.getText());


        SquareFactory boardGenerator = new SquareFactory();
        String id = boardGenerator.generateBoard();

        this.server.startSendServ("board "+id);

        this.board = boardGenerator.createBoard(id);
        DoubleNode squaretemp = this.board.getHead();
        this.square = squaretemp;
        while (squaretemp != null) {
            this.principalWindow.add(squaretemp.getSquare().getLabel());
            squaretemp = squaretemp.getNext();
        }
        System.out.println(this.square.getSquare());


    }

    public void setWaitingClose(){
        this.waiting.setVisible(false);
        this.playerIcon1.setVisible(true);
        this.playerIcon2.setVisible(true);
        this.namePlayer1.setVisible(true);
        this.namePlayer2.setVisible(true);
        this.turnTitle.setVisible(true);
        this.playerTurn.setVisible(true);
        this.LanzarDado.setVisible(true);
        this.lastDice.setVisible(true);
        this.Avatar1.setVisible(true);
        this.Avatar2.setVisible(true);

    }


    ActionListener joinActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            JOptionPane.showMessageDialog(null, "Uniendose a una partida");
            LanzarDado.setEnabled(false);

            try {
                createClient();
            }catch (IOException io){

            }
            setWaitingClose();
            title.setVisible(false);
            name.setVisible(false);
            create.setVisible(false);
            join.setVisible(false);

        }
    };

    public void createClient() throws IOException {
        this.client = new Client();
        this.client.startClient(1234, this.thisInterface);

        this.namePlayer1.setText(this.name.getText());
        this.client.startSendCli("name "+this.namePlayer1.getText());


    }

    public void makeLabel() {
        SquareFactory boardGenerator = new SquareFactory();
        this.board = boardGenerator.createBoard(this.lastMessage);
        DoubleNode squaremaker = this.board.getHead();
        this.square = squaremaker;
        System.out.println(squaremaker.getSquare().getKind());
        while (squaremaker != null) {
            this.principalWindow.add(squaremaker.getSquare().getLabel());
            System.out.println(squaremaker.getSquare().getKind());
            squaremaker = squaremaker.getNext();
        }
        this.principalWindow.repaint();
    }

    ActionListener diceButton = new ActionListener() {
        public void actionPerformed(ActionEvent ae){
            int max = 3;
            int min = 1;
            int range = max - min + 1;
            int dice = (int) (Math.random() * range) + min;
            lastDice.setText(String.valueOf(dice));
            System.out.println(dice);

            Runnable run = new Player(name.getText(), square, Avatar1, dice, thisInterface, line1, casilla1, 1, false);
            new Thread(run).start();

            try{
                server.startSendServ("dice "+dice);
                server.startSendServ("next "+"3");
            } catch (IOException io) {

            }
            
        }
    };

    public void moveEnemy(int num){
        Runnable run = new Player(name.getText(), square, Avatar1, num, thisInterface, line1, casilla1, 1, true);
        new Thread(run).start();
    }

    public void showProblem(String num1, String num2){

    }

    public void actualBox(int squar){
        System.out.println(this.square.getSquare().getKind());
        if (this.square.getSquare().getKind().equals("Challenge")){
            String num = JOptionPane.showInputDialog("Cuánto es 2+2");
            System.out.println("-"+num+"-");
            if (num.equals("4")){
                System.out.println("sies");
            }
        }
        else if (this.square.getSquare().getKind().equals("Trap")){
            System.out.println("se echa para atras");
        }
        else if (this.square.getSquare().getKind().equals("Tunnel")){
            System.out.println("se echa para atras");
        }

    }

    public void moveSquare1(){
        this.square = this.square.getNext();
    }

    public void addLine1() {
        this.line1++;
    }

    public void addCasilla1() {
        this.casilla1++;
    }

    public void setLastMessage(String message){
        this.lastMessage = message;
    }

    public void setEnemyName(String name){
        this.namePlayer2.setText(name);
    }

    public void changeName(){
        if(playerTurn.getText() == namePlayer1.getText()){
            this.playerTurn.setText(p2);
        }else{
            this.playerTurn.setText(p1);
        }
    }

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

        this.principalWindow.add(this.title);
        this.principalWindow.add(this.create);
        this.principalWindow.add(this.join);
        this.principalWindow.add(this.name);
        this.principalWindow.add(this.waiting);


        // GameBoard
        ImageIcon imagenCasilla = new ImageIcon(getClass().getResource("/Images/challenge.png"));
        ImageIcon imagenDado = new ImageIcon(getClass().getResource("/Images/dice.png"));
        ImageIcon imageUser = new ImageIcon(getClass().getResource("/Images/user.png"));
        ImageIcon imgPeon1 = new ImageIcon(getClass().getResource("/Images/peon1.png"));
        ImageIcon imgPeon2 = new ImageIcon(getClass().getResource("/Images/peon2.png"));

        Avatar1.setBounds(85,135,50,50);
        Avatar1.setIcon(imgPeon1);

        Avatar2.setBounds(85,170,50,50);
        Avatar2.setIcon(imgPeon2);

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
        LanzarDado.addActionListener(diceButton);

        lastDice.setBounds(300,505,50,50);
        lastDice.setText("0");

        this.principalWindow.add(this.playerIcon1);
        this.principalWindow.add(this.playerIcon2);
        this.principalWindow.add(this.namePlayer1);
        this.principalWindow.add(this.namePlayer2);
        this.principalWindow.add(this.turnTitle);
        this.principalWindow.add(this.playerTurn);
        this.principalWindow.add(this.LanzarDado);
        this.principalWindow.add(this.lastDice);
        this.principalWindow.add(this.Avatar1);
        this.principalWindow.add(this.Avatar2);


        this.playerIcon1.setVisible(false);
        this.playerIcon2.setVisible(false);
        this.namePlayer1.setVisible(false);
        this.namePlayer2.setVisible(false);
        this.turnTitle.setVisible(false);
        this.playerTurn.setVisible(false);
        this.LanzarDado.setVisible(false);
        this.lastDice.setVisible(false);
        this.Avatar1.setVisible(false);
        this.Avatar2.setVisible(false);

        this.principalWindow.setSize(505, 610);
        this.principalWindow.setLayout(null);
        this.principalWindow.setResizable(false);
        this.principalWindow.setVisible(true);
        this.principalWindow.setDefaultCloseOperation(principalWindow.EXIT_ON_CLOSE);

    }

    public void setInterface(Interface inter){
        this.thisInterface = inter;
    }

    public static void main(String[] args) {
        Interface interface1 = new Interface();
        interface1.setInterface(interface1);
    }

}
