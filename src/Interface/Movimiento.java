package Interface;

import Interface.Interface;

import javax.swing.*;

public class Movimiento implements Runnable {

    private JLabel avatar;
    private JLabel avatar2;
    private JLabel Turn;
    private int line1;
    private int line2;
    private int casilla1;
    private int casilla2;


    public Movimiento(JLabel imagenPeon1, JLabel imagenPeon2, JLabel ronda, int linea1, int linea2, int casilla1, int casilla2){
        this.avatar = imagenPeon1;
        this.avatar2 = imagenPeon2;
        this.Turn = ronda;
        this.line1 = linea1;
        this.casilla1 = casilla1;
        this.casilla2 = casilla2;
        this.line2 = linea2;
    }

    @Override
    public void run() {
        move();
    }

    public void move(){

        int movexA1 = this.avatar.getX();
        int moveyA1 = this.avatar.getY();
        int movexA2 = this.avatar2.getX();
        int moveyA2 = this.avatar2.getY();
        int cantAvanzar = 92;
        int recorrido = 0;
        int cantDado;
        //boolean avanzar = true;
        int turno = Integer.parseInt(Turn.getText());

        if(turno == 1){
            if(line1%2 == 0){
                while (recorrido < (cantAvanzar*3)){
                    if(casilla1 % 4 == 0){
                        moveyA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                        line1 += 1;
                    }else{
                        movexA1 -= 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }
                }
                casilla1 += 3;
            }else{
                    
                while (recorrido < (cantAvanzar*3)){

                    if(casilla1 % 4 == 0){
                        moveyA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                        line1 +=1;
                    }else{
                        movexA1 += 5;
                        this.avatar.setLocation(movexA1, moveyA1);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }

                }
                casilla1 += 1;
            } 
            System.out.println("casilla de P1 = "+ casilla1);
            Turn.setText("2");
            
            //MOVIMIENTO DEL SEGUNDO JUGADOR
        }else{
            if(line2%2 == 0){
                while (recorrido < cantAvanzar){
                    if(casilla2 % 4 == 0){
                        moveyA2 += 5;
                        this.avatar2.setLocation(movexA2, moveyA2);
                        recorrido += 5;
                        line2 += 1;

                    }else{
                        movexA2 -= 5;
                        this.avatar2.setLocation(movexA2, moveyA2);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }
                }
                casilla2 += 1;
            }else{
                    
                while (recorrido < cantAvanzar){

                    if(casilla2 % 4 == 0){
                        moveyA2 += 5;
                        this.avatar2.setLocation(movexA2, moveyA2);
                        recorrido += 5;
                        line2 += 1;

                    }else{
                        movexA2 += 5;
                        this.avatar2.setLocation(movexA2, moveyA2);
                        recorrido += 5;
                    }

                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                    }

                }
                casilla2 += 1;
            } 
            System.out.println("casilla de P2 = "+casilla2);
            Turn.setText("1");
        }
    }

}
