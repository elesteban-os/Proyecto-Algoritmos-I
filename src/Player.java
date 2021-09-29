import List.*;
import java.lang.Math;

import java.util.Objects;

public class Player {

    private String name;
    private DoubleNode position;

    public Player(String name, DoubleLinkedList board){
        this.name = name;
        this.position = board.getHead();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void rollDice(){
        int max = 3;
        int min = 1;
        int range = max - min + 1;
        int dice = (int) (Math.random() * range) + min;
        move(dice);
    }

    public void move(int times){
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
}
