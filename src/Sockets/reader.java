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
     *
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
                System.out.println("No se pudo leer el texto.");
            }
            StringTokenizer action = new StringTokenizer(message);
            String arg = action.nextToken();
            String num = action.nextToken();
            
            switch(arg) {
                case ("name"):
                    this.interfaz.setEnemyName(num);
                    break;
                case ("next"):
                    this.interfaz.enableDie();
                    this.interfaz.changeName();
                    break;
                case ("dice"):
                    this.toReturn = num;
                    int dice = Integer.parseInt(num);
                    this.interfaz.moveEnemy(dice);
                    break;
                case("board"):
                    this.toReturn = num;
                    this.interfaz.setLastMessage(getToReturn());
                    this.interfaz.makeLabel();
                    break;
                case("problem"):
                    String[] operators = num.split(";");
                    this.interfaz.showProblem(operators[0], operators[1], operators[2]);
                    break;
                case("result"):
                    this.interfaz.checkResult(num);
                    break;
                case("wrong"):
                    this.interfaz.challengeFailed();
                    break;
            }
            this.interfaz.setLastMessage(getToReturn());
        }
    }


}
