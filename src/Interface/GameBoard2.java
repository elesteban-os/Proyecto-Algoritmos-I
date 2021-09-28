package Interface;

import List.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard2 {
    private JFrame root = new JFrame("TABLERO");
    private DoubleLinkedList board;
    private JLabel playerIcon1 = new JLabel();
    private JLabel playerIcon2 = new JLabel();
    private JLabel namePlayer1 = new JLabel();
    private JLabel namePlayer2 = new JLabel();
    private JLabel turnTitle = new JLabel();
    private JLabel playerTurn = new JLabel();
    private JButton Dado = new JButton();
    private JLabel Avatar1 = new JLabel();
    private JLabel Avatar2 = new JLabel();

    int linea1 = 1;
    int linea2 = 1;
    int casilla1 =1;
    int casilla2 = 1;
    private Movimiento moveAvatar = new Movimiento(Avatar1, Avatar2, playerTurn, linea1, linea2, casilla1, casilla2);


    ActionListener DadoActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(moveAvatar).start();
        }
    };

    public GameBoard2(){

        ImageIcon imageDado = new ImageIcon(getClass().getResource("/Images/dice.png"));
        ImageIcon imageUser = new ImageIcon(getClass().getResource("/Images/user.png"));
        ImageIcon imgPeon1 = new ImageIcon(getClass().getResource("/Images/peon1.png"));
        ImageIcon imgPeon2 = new ImageIcon(getClass().getResource("/Images/peon2.png"));

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

        playerTurn.setBounds(235,40,100,50);
        playerTurn.setText("1");

        Dado.setBounds(215,505,50,50);
        Dado.setIcon(imageDado);
        Dado.addActionListener(DadoActionListener);

        Avatar1.setBounds(85,135,50,50);
        Avatar1.setIcon(imgPeon1);

        Avatar2.setBounds(85,170,50,50);
        Avatar2.setIcon(imgPeon2);

        this.root.add(this.playerIcon1);
        this.root.add(this.playerIcon2);
        this.root.add(this.namePlayer1);
        this.root.add(this.namePlayer2);
        this.root.add(this.turnTitle);
        this.root.add(this.playerTurn);
        this.root.add(this.Dado);
        this.root.add(this.Avatar1);
        this.root.add(this.Avatar2);

        generateBoard();
        displayBoard();

        this.root.setSize(505, 610);
        this.root.setLayout(null);
        this.root.setResizable(false);
        this.root.setVisible(true);
        this.root.setDefaultCloseOperation(root.EXIT_ON_CLOSE);

    }

    public void generateBoard() {
        this.board = new DoubleLinkedList();
        int min = 1;
        int max = 3;
        int range = max - min + 1;
        int y = 130;
        for (int i = 0; i < 4; i++) {
            int x = 70;
            for (int j = 0; j < 4; j ++) {
                int box = (int) (Math.random() * range) + min;
                if (box == 1) {
                    this.board.addBox(new Box("Tunnel", x, y));
                } else if (box == 2) {
                    this.board.addBox(new Box("Trap", x, y));
                } else {
                    this.board.addBox(new Box("Challenge", x, y));
                }
                x += 92;
            }
            y += 92;
        }
    }

    public void displayBoard() {
        DoubleNode b = this.board.getHead();
        while (b != null) {
            this.root.add(b.getBox().getLabel());
            b = b.getNext();
        }
    }

    public static void main (String[] args){
        GameBoard2 Gameboard2 = new GameBoard2();
    }
}
