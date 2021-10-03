package List;

import List.*;

import javax.swing.*;
import java.lang.Math;

import java.util.Objects;

public class Player implements Runnable {

    private String name;
    private DoubleNode position;
    private JLabel avatar;
    private int casilla1 = 1;
    private int line1 = 1;
    private int move;

    public Player(String name, DoubleNode board, JLabel avatar, int move){
        this.name = name;
        this.position = board;
        this.avatar = avatar;
        this.move = move;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int rollDice(){
        int max = 3;
        int min = 1;
        int range = max - min + 1;
        int dice = (int) (Math.random() * range) + min;
        moveList(dice);
        return dice;

    }

    public void moveList(int times){
        if (Objects.equals(this.position.getSquare().getKind(), "Tunnel")){
            for (int i = 0; i <= times; i++) {
                if (this.position.getNext() != null) {
                    this.position = this.position.getNext();
                }
            }
        } else if (Objects.equals(this.position.getSquare().getKind(), "Trap")){
            for (int i = 0; i <= times; i++) {
                if (this.position.getPrev() != null) {
                    this.position = this.position.getPrev();
                }
            }
        }
    }

    public void run() {
        moveImage();
    }

    public void moveImage(){
        int movexA1 = this.avatar.getX();
        int moveyA1 = this.avatar.getY();

        int cantAvanzar = 92;
        int recorrido = 0;

        for(int i=0; i != 4; i++) {
            if (line1 % 2 == 0) {
                while (recorrido < (cantAvanzar)) {
                    if (casilla1 % 4 == 0) {
                        moveyA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                        line1 += 1;
                    } else {
                        movexA1 -= 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
                casilla1 += 1;
            } else {

                while (recorrido < (cantAvanzar)) {

                    if (casilla1 % 4 == 0) {
                        moveyA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                        line1 += 1;
                    } else {
                        movexA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }

                }
                casilla1 += 1;
            }
            System.out.println("casilla de P1 = " + casilla1);
            recorrido = 0;
        }
    }
}
