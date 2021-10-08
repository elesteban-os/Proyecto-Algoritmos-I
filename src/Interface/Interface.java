package Interface;

import Sockets.Client;
import Sockets.Server;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import List.*;

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
    private JButton die = new JButton();
    private JLabel lastRoll = new JLabel();
    private JLabel Avatar2 = new JLabel();
    private JLabel Avatar1 = new JLabel();
    private JLabel log = new JLabel();

    private JLabel waiting = new JLabel("Esperando a que se una otro usuario.");

    private Server server;
    private Client client;
    private Interface thisInterface;

    private DoubleLinkedList board = new DoubleLinkedList();

    private String lastMessage;

    private Player player;

    private DoubleNode squareP1;
    private DoubleNode squareP2;

    ImageIcon imgPeon1 = new ImageIcon(getClass().getResource("/Images/peon1.png"));
    ImageIcon imgPeon2 = new ImageIcon(getClass().getResource("/Images/peon2.png"));


    private String p1 = this.namePlayer1.getText(); 
    private String p2 = this.namePlayer2.getText();

    private int result;

    /**
     * Función que escucha el botón de crear.
     */
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

    /**
     * Crea el servidor y los labels del juego.
     * @throws IOException
     */
    public void createServer() throws IOException {

        server = new Server();
        server.start(1234, this.thisInterface);

        this.namePlayer1.setText(this.name.getText());
        this.server.startSendServ("name "+this.namePlayer1.getText());


        SquareFactory boardGenerator = new SquareFactory();
        String id = boardGenerator.generateBoardID();

        this.server.startSendServ("board "+id);

        this.board = boardGenerator.createBoard(id);
        DoubleNode squaretemp = this.board.getHead();
        this.squareP1 = squaretemp;
        this.squareP2 = squaretemp;
        while (squaretemp != null) {
            this.principalWindow.add(squaretemp.getSquare().getLabel());
            squaretemp = squaretemp.getNext();
        }
    }

    /**
     * Cierra elementos de la interfaz.
     */
    public void setWaitingClose(){
        this.waiting.setVisible(false);
        this.playerIcon1.setVisible(true);
        this.playerIcon2.setVisible(true);
        this.namePlayer1.setVisible(true);
        this.namePlayer2.setVisible(true);
        this.turnTitle.setVisible(true);
        this.playerTurn.setVisible(true);
        this.die.setVisible(true);
        this.lastRoll.setVisible(true);
        this.Avatar1.setVisible(true);
        this.Avatar2.setVisible(true);
        this.log.setVisible(true);

    }

    /**
     * Función que escucha el botón de unirse e inicializa el socket de cliente.
     */
    ActionListener joinActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            JOptionPane.showMessageDialog(null, "Uniendose a una partida");
            die.setEnabled(false);

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

    /**
     * Función que crea el socket del cliente.
     * @throws IOException
     */
    public void createClient() throws IOException {
        this.client = new Client();
        this.client.startClient(1234, this.thisInterface);

        this.namePlayer1.setText(this.name.getText());
        this.client.startSendCli("name "+this.namePlayer1.getText());
    }

    /**
     * Función que genera los labels aleatorios en la pantalla.
     */
    public void makeLabel() {
        SquareFactory boardGenerator = new SquareFactory();
        this.board = boardGenerator.createBoard(this.lastMessage);
        DoubleNode squaremaker = this.board.getHead();
        this.squareP1 = squaremaker;
        this.squareP2 = squaremaker;

        while (squaremaker != null) {
            this.principalWindow.add(squaremaker.getSquare().getLabel());
            squaremaker = squaremaker.getNext();
        }
        this.principalWindow.repaint();
    }

    /**
     * Función que tira el dado, mueve el jugador y advierte por medio de sockets que el jugador se está moviendo.
     */
    public void rollDie() {
        int die = (int) (Math.random() * 4) + 1;
        this.updateRollLabel(die);
        this.die.setEnabled(false);
        Runnable run = new Player(squareP1, Avatar1, die, true, true, false, thisInterface);

        new Thread(run).start();

        try{
            this.changeName();
            if (server != null){
                server.startSendServ("dice "+ die);
                server.startSendServ("next 0");
            } else {
                client.startSendCli("dice "+ die);
                client.startSendCli("next 0");
            }
        } catch (IOException io) {

        }
    }

    /**
     * Función que mueve al jugador enemigo.
     * @param num número de veces que se mueve el jugador.
     */
    public void moveEnemy(int num) {
        boolean forward = num > 0;
        if (!forward) {
            num *= -1;
        }
        Runnable run = new Player(squareP2, Avatar2, num, forward, false, true, thisInterface);
        new Thread(run).start();
    }

    /**
     * Función que muestra el problema matemático en la interfaz.
     * @param num1 Primer número.
     * @param oper Operador matemático.
     * @param num2 Segundo número.
     * @param result Resultado de la operación.
     */
    public void showProblem(String num1, String oper, String num2, String result) {
        String problem = "Reto: resuelva la siguiente operación: ";
        problem += num1 + " " + oper + " " + num2;
        String input = JOptionPane.showInputDialog(problem);
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (Exception e) {
            num = 999999;
        }
        try{
            this.changeName();
            if (server != null){
                server.startSendServ("result "+ num);
            } else {
                client.startSendCli("result "+ num);
            }
            this.die.setEnabled(true);
        } catch (IOException io) {
            System.out.println("No se pudo enviar el mensaje.");
        }
    }

    /**
     * Función que envía el problema aritmético.
     * @param num1 Primer número.
     * @param oper Operador matemático.
     * @param num2 Segundo número
     * @param result Resultado de la operación matemática.
     * @throws IOException
     */
    public void sendProblem(String num1, String oper, String num2, String result) throws IOException {
        if (server != null){
            server.startSendServ("problem " + num1 + ";" + oper + ";" + num2 + ";" + result);
        } else {
            client.startSendCli("problem " + num1 + ";" + oper + ";" + num2 + ";" + result);
        }
    }

    /**
     * Función que verifica la casilla en la que un jugador cayó.
     * @throws IOException
     */
    public void actualBox() throws IOException {
        int move;
        Runnable run;
        switch (this.squareP1.getSquare().getKind()) {
            case "Challenge":
                String oper = null;
                int result = 0;
                int num1 = (int) (Math.random() * 50) + 1;
                int num2 = (int) (Math.random() * 50) + 1;

                int operation = (int) (Math.random() * 4) + 1;

                if (operation == 1){
                    result = num1 + num2;
                    oper = "+";
                } else if (operation == 2){
                    result = num1 - num2;
                    oper = "-";
                } else if (operation == 3){
                    result = num1 * num2;
                    oper = "*";
                } else if (operation == 4){
                    result = num1 / num2;
                    oper = "/";
                }
                this.result = result;
                this.sendProblem(String.valueOf(num1), oper, String.valueOf(num2));
                setLog("Reto enviado");
                break;
            case "Trap":
                move = (int) (Math.random() * 3) + 1;
                run = new Player(squareP1, Avatar1, move, false, false, false, thisInterface);
                new Thread(run).start();

                move *= -1;

                if (server != null){
                    server.startSendServ("dice "+ move);
                } else {
                    client.startSendCli("dice "+ move);
                }
                System.out.println("se echa pa'tra");
                break;
            case "Tunnel":

                move = (int) (Math.random() * 3) + 1;
                setLog("Avanza " + move);

                run = new Player(squareP1, Avatar1, move, true, false, false, thisInterface);
                new Thread(run).start();


                if (server != null){
                    server.startSendServ("dice "+ move);
                } else {
                    client.startSendCli("dice " + move);
                }
                break;
        }


    }

    /**
     * Función que cambia el texto que hay en el JLabel log
     * @param text recibe el texto por cambiar
     */
    public void setLog(String text){
        this.log.setText(text);
    }

    /**
     * Función que actúa como movimiento para la lista.
     * @param forward booleano para identificar si la lista va a ir hacia delante o hacia atrás.
     * @param here booleano para identificar si es la interfaz actual o del enemigo.
     */
    public void moveSquare(boolean forward, boolean here){
        if (forward) {
            if (here) {
                this.squareP1 = this.squareP1.getNext();
            } else {
                this.squareP2 = this.squareP2.getNext();
            }
        } else {
            if (here) {
                this.squareP1 = this.squareP1.getPrev();
            } else {
                this.squareP2 = this.squareP2.getPrev();
            }
        }
    }

    /**
     * Función que coloca el último mensaje recibido desde el socket en un atributo.
     * @param message Mensaje.
     */
    public void setLastMessage(String message){
        this.lastMessage = message;
    }

    /**
     * Función que coloca el nombre del enemigo en la interfaz gráfica.
     * @param name Nombre del jugador.
     */
    public void setEnemyName(String name){
        this.namePlayer2.setText(name);
    }
    /**
     * Función que intercambia los nombres en el label de los turnos de jugador.
     */
    public void changeName(){
        if(playerTurn.getText() == namePlayer1.getText()){
            this.playerTurn.setText(p2);
        }else{
            this.playerTurn.setText(p1);
        }
    }

    /**
     * Función que inicializa todos los elementos de la interfaz gráfica.
     */
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
        ImageIcon imageDice = new ImageIcon(getClass().getResource("/Images/dice.png"));
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

        log.setBounds(180,80,100,100);

        die.setBounds(215,505,50,50);
        die.setIcon(imageDice);
        die.addActionListener(action -> rollDie());

        lastRoll.setBounds(300,505,50,50);
        lastRoll.setText("0");

        this.principalWindow.add(this.playerIcon1);
        this.principalWindow.add(this.playerIcon2);
        this.principalWindow.add(this.namePlayer1);
        this.principalWindow.add(this.namePlayer2);
        this.principalWindow.add(this.turnTitle);
        this.principalWindow.add(this.playerTurn);
        this.principalWindow.add(this.die);
        this.principalWindow.add(this.lastRoll);
        this.principalWindow.add(this.Avatar1);
        this.principalWindow.add(this.Avatar2);
        this.principalWindow.add(this.log);


        this.playerIcon1.setVisible(false);
        this.playerIcon2.setVisible(false);
        this.namePlayer1.setVisible(false);
        this.namePlayer2.setVisible(false);
        this.turnTitle.setVisible(false);
        this.playerTurn.setVisible(false);
        this.die.setVisible(false);
        this.lastRoll.setVisible(false);
        this.Avatar1.setVisible(false);
        this.Avatar2.setVisible(false);
        this.log.setVisible(false);

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
