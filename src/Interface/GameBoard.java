package Interface;

import List.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard {
    private JFrame root = new JFrame("TABLERO");
    private DoubleLinkedList board;
    private JLabel playerIcon1 = new JLabel();
    private JLabel playerIcon2 = new JLabel();
    private JLabel namePlayer1 = new JLabel();
    private JLabel namePlayer2 = new JLabel();
    private JLabel turnTitle = new JLabel();
    private JLabel playerTurn = new JLabel();
    private JButton LanzarDado = new JButton();

    ActionListener LanzarDadoActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             root.setVisible(false);
        }
    };

    public GameBoard(){

        ImageIcon imageDado = new ImageIcon(getClass().getResource("/Images/dice.png"));
        ImageIcon imageUser = new ImageIcon(getClass().getResource("/Images/user.png"));

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
        LanzarDado.setIcon(imageDado);

        generateBoard();
        this.root.add(this.playerIcon1);
        this.root.add(this.playerIcon2);
        this.root.add(this.namePlayer1);
        this.root.add(this.namePlayer2);
        this.root.add(this.turnTitle);
        this.root.add(this.playerTurn);
        this.root.add(this.LanzarDado);

        this.root.setSize(505, 610);
        this.root.setLayout(null);
        this.root.setResizable(false);
        this.root.setVisible(true);
        this.root.setDefaultCloseOperation(root.EXIT_ON_CLOSE);
    }

    public void generateBoard() {
        StringBuilder boardID = new StringBuilder();
        int traps = (int) (Math.random() * 7);
        int tunnels = 8 - traps;
        int challenges = 8;
        for (int i = 0; i < 16; i++) {
            String type = "";
            if (i == 0 || i == 15) {
                type = "Goal";
                tunnels --;
            } else {
                boolean added = false;
                while (!added) {
                    int box = (int) (Math.random() * 3) + 1;
                    if (box == 1 && tunnels > 1) {
                        type = "Tunnel";
                        tunnels--;
                        added = true;
                    } else if (box == 2 && traps > 0) {
                        type = "Trap";
                        traps--;
                        added = true;
                    } else if (challenges > 0) {
                        type = "Challenge";
                        challenges--;
                        added = true;
                    }
                }
            }
            if (i < 15) {
                boardID.append(type).append(",");
            } else {
                boardID.append(type);
            }
        }
        createBoard(boardID.toString());
    }

    public void createBoard(String id) {
        this.board = new DoubleLinkedList();
        String[] boxArray = id.split(",");
        for (int i = 0; i < boxArray.length; i++) {
            int x;
            if (i < 4 || (8 <= i && i < 12)) {
                x = 70 + ((i % 4) * 92);
            } else {
                x = 346 - ((i % 4) * 92);
            }
            int y = 130 + ((i / 4) * 92);
            this.board.addBox(new Box(boxArray[i], x, y));
        }
        DoubleNode box = this.board.getHead();
        while (box != null) {
            this.root.add(box.getBox().getLabel());
            box = box.getNext();
        }
    }

    public static void main (String args[]){
        GameBoard game = new GameBoard();
    }

}
