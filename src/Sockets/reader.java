package Sockets;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class reader implements Runnable {
    private final DataInputStream input;
    private String toReturn;
    private JLabel label = null;
    private JButton button;


    public reader(DataInputStream input, JLabel label, JButton button){
        this.input = input;
        this.label = label;
        this.button = button;
    }

    public reader(DataInputStream input){
        this.input = input;
    }


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
            }
            System.out.println(getToReturn());
            if (this.label != null) {
                this.label.setText("Recibiendo: " + getToReturn());
                if (this.toReturn == "ready"){
                    this.button.setEnabled(true);
                }
            }
        }
    }


}
