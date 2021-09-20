package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard {
    private JFrame root = new JFrame("TABLERO");
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
    private  JButton LanzarDado = new JButton();

    ActionListener LanzarDadoActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             root.setVisible(false);
        }
    };

    public GameBoard(){

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

        this.root.add(this.C1);
        this.root.add(this.C2);
        this.root.add(this.C3);
        this.root.add(this.C4);
        this.root.add(this.C5);
        this.root.add(this.C6);
        this.root.add(this.C7);
        this.root.add(this.C8);
        this.root.add(this.C9);
        this.root.add(this.C10);
        this.root.add(this.C11);
        this.root.add(this.C12);
        this.root.add(this.C13);
        this.root.add(this.C14);
        this.root.add(this.C15);
        this.root.add(this.C16);
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

    public static void main(String[] args){
        GameBoard GameBoard1 = new GameBoard();
    }

}
