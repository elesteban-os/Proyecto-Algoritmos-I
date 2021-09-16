package Sockets;

import java.util.StringTokenizer;

public class Message {

    public Message(){

    }

    public String problem (String param){
        StringTokenizer stringToken = new StringTokenizer(param);
        String action = stringToken.nextToken();
        String arg = stringToken.nextToken();
        String toReturn = "";

        switch(action){
            case ("move"):
                toReturn = "m" + " " + arg;
        }
        return toReturn;
    }
}
