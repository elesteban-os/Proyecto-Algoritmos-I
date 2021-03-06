package List;

import Interface.Interface;
import java.io.IOException;

import javax.swing.*;

public class Player implements Runnable {

    private DoubleNode position;
    private JLabel label;
    private int move;
    private boolean forward;
    private boolean die;
    private boolean enemy;
    private Interface window;

    /**
     * Constructor that sets the values of the player that'll move
     *
     * @param square  DoubleNode containing the square the player is currently in
     * @param avatar  label of the player to move
     * @param move    number of times to move
     * @param window  Interface where the player is going to move, in which all the players' info is stored
     * @param forward boolean to check move direction
     * @param die     boolean to check if the player is moving because of a die roll of because of a square he landed in
     */
    public Player(DoubleNode square, JLabel avatar, int move, boolean forward, boolean die, boolean enemy, Interface window) {
        this.position = square;
        this.label = avatar;
        this.move = move;
        this.forward = forward;
        this.die = die;
        this.enemy = enemy;
        this.window = window;
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
     * pauses the thread to see the animation
     */
    public void waitThread() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * moves the player's label the intended number of times in the expected direction
     */
    public void moveImage() {
        int posX = this.label.getX();
        int posY = this.label.getY();

        int number = 0;
        if (this.enemy) {
            number += 1;
        }
        if (this.forward) {
            for (int i = 0; i < this.move; i++) {
                if (this.position.getNext() != null) {
                    this.position = this.position.getNext();
                    this.window.moveSquare(this.forward, !this.enemy);
                    int pos = this.position.getSquare().getNumber();
                    if (pos / 4 % 2 == 0 && !(pos % 4 == 0)) {
                        while (posX < 85 + ((pos % 4) * 92)) {
                            posX += 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    } else if (pos % 4 == 0) {
                        while (posY < 135 + 35 * number + pos / 4 * 92) {
                            posY += 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    } else {
                        while (posX > 361 - ((pos % 4) * 92)) {
                            posX -= 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    }
                } else {
                    this.window.disableDie();
                    if (!this.enemy){
                        this.window.endMessage("??????ENHORABUENA COMPA!!!");
                    } else {
                        this.window.endMessage("MEJOR SUERTE LA PR??XIMA :c");
                    }
                    i = this.move;
                }
                if (die) {
                    this.window.updateRollLabel(this.move - i - 1);
                } else {
                    this.window.setLog("Avanza " + (this.move - i - 1));
                }
            }
        } else {
            for (int i = 0; i < this.move; i++) {
                if (this.position.getPrev() != null) {
                    this.position = this.position.getPrev();
                    this.window.moveSquare(this.forward, !this.enemy);
                    int pos = this.position.getSquare().getNumber();
                    if ((pos + 1) / 4 % 2 == 0 && !((pos + 1) % 4 == 0)) {
                        while (posX > 85 + ((pos % 4) * 92)) {
                            posX -= 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    } else if ((pos + 1) % 4 == 0) {
                        while (posY > 135 + 35 * number + pos / 4 * 92) {
                            posY -= 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    } else {
                        while (posX < 361 - ((pos % 4) * 92)) {
                            posX += 5;
                            this.label.setLocation(posX, posY);
                            this.waitThread();
                        }
                    }
                }
                this.window.setLog("Retrocede " + (this.move - i - 1));
            }
        }
        if (die) {
            try {
                waitThread();
                this.window.actualBox();
            } catch (IOException e) {
                System.out.println("g");
            }
        }
    }
}