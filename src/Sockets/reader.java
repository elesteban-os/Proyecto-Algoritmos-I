package Sockets;

import Interface.Interface;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class reader implements Runnable {
    private final DataInputStream input;
    private String toReturn;
    private Interface interfaz;

    /**
     * @param input permite recibir contenido de un socket
     * @param interfaz interfaz de usuario
     */
    public reader(DataInputStream input, Interface interfaz){
        this.input = input;
        this.interfaz = interfaz;
    }

    /**
     * @return retorna un String
     */
    public String getToReturn(){
        return this.toReturn;
    }
    
    public void run(){
    
        while (true){
            String message = "";
            try{
                message = input.readUTF();
            } catch (IOException io){

            }
            
            StringTokenizer action = new StringTokenizer(message);
            String arg = action.nextToken();
            String num = action.nextToken();
            
            switch(arg) {
                case ("name"):
                    this.interfaz.setEnemyName(num);
                    break;
                case ("next"):
                    this.interfaz.changeName();
                    this.toReturn = "ready";
                    break;
                case ("dice"):
                    this.toReturn = num;
                    int dice = Integer.parseInt(num);
                    System.out.println(dice);
                    this.interfaz.moveEnemy(dice);
                    break;
                case ("challenge"):
                    String oper;
                    int result = 0;
                    int max = 50;
                    int min = 1;
                    int range = max - min + 1;
                    int num1Chal = (int) (Math.random() * range) + min;
                    int num2Chal = (int) (Math.random() * range) + min;

                    max = 4;
                    int operation = (int) (Math.random() * range) + min;

                    if (operation == 1){
                        result = num1Chal + num2Chal;
                    } else if (operation == 2){
                        result = num1Chal - num2Chal;
                    } else if (operation == 3){
                        result = num1Chal * num2Chal;
                    } else if (operation == 4){
                        result = num1Chal / num2Chal;
                    }

                    this.toReturn = String.valueOf(result);
                    this.interfaz.showProblem(String.valueOf(num1Chal), String.valueOf(num2Chal));

                case ("box"):
                    switch (num){
                        case ("1"):
                            this.toReturn = "challenge";
                            break;
                        case ("2"):
                            this.toReturn = "nothing";
                            break;
                    }
                case("board"):
                    this.toReturn = num;
                    this.interfaz.setLastMessage(getToReturn());
                    this.interfaz.makeLabel();
                    break;
            }
            this.interfaz.setLastMessage(getToReturn());
            System.out.println(getToReturn());
        }
    }


}
