package Sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class reader {
    DataInputStream input;

    public reader(DataInputStream input){
        this.input = input;
    }

    public String startRead() throws IOException { // Retorna string
        String message = input.readUTF();
        String toReturn = "";

        StringTokenizer action = new StringTokenizer(message);
        String arg = action.nextToken();
        String num = action.nextToken();

        switch(arg) {
            case ("next"):
                toReturn = "ready";
                break;
            case ("dice"):
                toReturn = num;
                break;
            case ("box"):
                switch (num){
                    case ("1"):
                        toReturn = "challenge";
                        break;
                    case ("2"):
                        toReturn = "nothing";
                        break;
                }
        }
        return toReturn;
    }
}
