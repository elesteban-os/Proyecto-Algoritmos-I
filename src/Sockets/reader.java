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
                    this.toReturn = "ready";
                    break;
                case ("dice"):
                    this.toReturn = num;
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
                    break;
            }
            this.interfaz.setLastMessage(getToReturn());
            System.out.println(getToReturn());
        }
    }


}
