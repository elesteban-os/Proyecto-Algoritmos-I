package List;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Objects;

public class Square {

    private String kind;
    private int num;
    private JLabel label;

    /**
     * Constructor for a new Square
     * @param kind a String for the kind of Square it'll be
     * @param number the position in the list for this Square
     * @param x an int for the horizontal position of the Square
     * @param y an int for the vertical position of the Square
     */
    public Square(String kind, int number, int x, int y) {
        this.kind = kind;
        this.num = number;
        ImageIcon image;
        if (Objects.equals(kind, "Goal")) {
            image = new ImageIcon(getClass().getResource("/Images/goal.png"));
            this.kind = "Tunnel";
        } else if (Objects.equals(kind, "Tunnel")) {
            image = new ImageIcon(getClass().getResource("/Images/tunnel.png"));
        } else if (Objects.equals(kind, "Trap")) {
            image = new ImageIcon(getClass().getResource("/Images/trap.png"));
        } else {
            image = new ImageIcon(getClass().getResource("/Images/challenge.png"));
        }
        this.label = new JLabel();
        this.label.setBounds(x, y, 100, 100);
        this.label.setIcon(image);
    }

    /**
     * @return the kind of Square this is
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * @return this Square's number in the board
     */
    public int getNumber() {
        return this.num;
    }

    /**
     * @return this Square's Label
     */
    public JLabel getLabel() {
        return this.label;
    }
}
