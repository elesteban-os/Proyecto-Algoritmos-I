package List;

import Interface.Interface;
import java.io.IOException;
import List.*;

import javax.swing.*;
import java.lang.Math;

import java.util.Objects;

public class Player implements Runnable {

    private String name;
    private DoubleNode position;
    private JLabel avatar;
    private int casilla;
    private int line;
    private int move;
    private Interface inter;
    private boolean forward;
    private boolean Box;

    /**
     * Constructor that sets the values of the player that'll move
     * @param name name of the player to move
     * @param board DoubleNode containing the square the player is currently in
     * @param avatar label of the player to move
     * @param move number of times to move
     * @param inter frame where the player is going to move, in which all the players' info is stored
     * @param line row in which the player is currently
     * @param casilla column in which the player is
     * @param forward boolean to check move direction
     * @param Box boolean to check if the player is moving because of a dice roll of because of a square he landed in
     */
    public Player(String name, DoubleNode board, JLabel avatar, int move, Interface inter, int line, int casilla, boolean forward, boolean Box){
        this.name = name;
        this.position = board;
        this.avatar = avatar;
        this.move = move;
        this.inter = inter;
        this.line = line;
        this.casilla = casilla;
        this.forward = forward;
        this.Box = Box;
    }

    /**
     * reassigns the current square of the player
     */
    public void moveList(boolean forward){
        this.inter.moveSquare1(forward);
    }

    /**
     * run method of theThread that moves the image of the player
     */
    public void run() {
        if (this.position.getNext() != null) {
            moveImage();
        }
    }

    /**
     * moves the player's label the intended number of times in the expected direction
     */
    public void moveImage() {
        int movexA1 = this.avatar.getX();
        int moveyA1 = this.avatar.getY();

        int cantAvanzar = 92;
        int recorrido = 0;

        if (this.forward) { //movimiento adelante
            for (int i = 0; i != this.move; i++) {
                if (line % 2 == 0) {
                    while (recorrido < (cantAvanzar)) {
                        if (casilla % 4 == 0) {
                            moveyA1 += 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                            line += 1;
                            this.inter.addLine1();
                        } else {
                            movexA1 -= 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    casilla += 1;
                    this.inter.addCasilla1();
                } else {

                    while (recorrido < (cantAvanzar)) {

                        if (casilla % 4 == 0) {
                            moveyA1 += 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                            line += 1;
                            this.inter.addLine1();
                        } else {
                            movexA1 += 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }

                    }
                    casilla += 1;
                    this.inter.addCasilla1();

                }
                moveList(this.forward);
                recorrido = 0;
            }
        } else { //movimiento atras
            for(int i=0; i != this.move; i++) {
                if (line % 2 == 0) {

                    while (recorrido < (cantAvanzar)) {
                        if (casilla % 4 == 0) {
                            moveyA1 -= 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                            line -= 1;
                            this.inter.removeLine1();
                        } else {
                            movexA1 += 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    casilla -= 1;
                    this.inter.removeCasilla1();
                } else {

                    while (recorrido < (cantAvanzar)) {

                        if (casilla % 4 == 0) {
                            moveyA1 -= 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                            line -= 1;
                            this.inter.removeLine1();
                        } else {
                            movexA1 -= 5;
                            this.avatar.setLocation(movexA1, moveyA1);
                            recorrido += 5;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }

                    }
                    casilla -= 1;
                    this.inter.removeCasilla1();

                }
                moveList(this.forward);
                recorrido = 0;
            }
        }
        if (Box) {
            try {
                this.inter.actualBox(1);
            } catch (IOException e){

            }

        }

    }
}
