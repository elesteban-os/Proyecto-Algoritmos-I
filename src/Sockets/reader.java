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

    /**
     * metodo run del Thread que lee los mensajes recibidos
     */
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
                    String oper = null;
                    int result = 0;
                    int num1Chal = (int) (Math.random() * 50) + 1;
                    int num2Chal = (int) (Math.random() * 50) + 1;

                    int operation = (int) (Math.random() * 4) + 1;

                    if (operation == 1){
                        result = num1Chal + num2Chal;
                        oper = "+";
                    } else if (operation == 2){
                        result = num1Chal - num2Chal;
                        oper = "-";
                    } else if (operation == 3){
                        result = num1Chal * num2Chal;
                        oper = "*";
                    } else if (operation == 4){
                        result = num1Chal / num2Chal;
                        oper = "/";
                    }

                    this.toReturn = String.valueOf(result);

                    String num1 = String.valueOf(num1Chal);
                    String num2 = String.valueOf(num2Chal);
                    String res = String.valueOf(result);

                    try {
                        this.interfaz.sendProblem(num1, oper, num2, res);
                        System.out.println("enviando problem 2");

                    } catch (IOException io){

                    }
                    break;
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
                case("problem"):
                    String[] operators = num.split(";");
                    this.interfaz.showProblem(operators[0], operators[1], operators[2], operators[3]);
                    break;
            }
            this.interfaz.setLastMessage(getToReturn());
            System.out.println(getToReturn());
        }
    }


}
