package Interface;

import Interface.Interface;

import javax.swing.*;

public class Movimiento implements Runnable {

    private JLabel avatar;

    public Movimiento(JLabel imagenPeon){
        this.avatar = imagenPeon;
    }

    @Override
    public void run() {
        move();
    }

    public void move(){

        int movex = this.avatar.getX();
        int movey = this.avatar.getY();
        int cantAvanzar = 80;
        int recorrido = 0;
        boolean avanzar = true;

        while (recorrido < cantAvanzar){
            movex += 5;
            this.avatar.setLocation(movex, movey);
            recorrido += 5;
            try {
                Thread.sleep(100);
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
    }

}
