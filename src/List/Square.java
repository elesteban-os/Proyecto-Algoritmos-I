package List;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Objects;

public class Square {

    private String kind;
    private ImageIcon image;
    private JLabel label;

    /**
     * Constructor for a new Square
     * @param kind a String for the kind of Square it'll be
     * @param x an int for the horizontal position of the Square
     * @param y an int for the vertical position of the Square
     */
    public Square(String kind, int x, int y) {
        this.kind = kind;
        if (Objects.equals(kind, "Goal")) {
            this.image = new ImageIcon(getClass().getResource("/Images/goal.png"));
            this.kind = "Tunnel";
        } else if (Objects.equals(kind, "Tunnel")) {
            this.image = new ImageIcon(getClass().getResource("/Images/tunnel.png"));
        } else if (Objects.equals(kind, "Trap")) {
            this.image = new ImageIcon(getClass().getResource("/Images/trap.png"));
        } else {
            this.image = new ImageIcon(getClass().getResource("/Images/challenge.png"));
        }
        this.label = new JLabel();
        this.label.setBounds(x, y, 100, 100);
        this.label.setIcon(this.image);
    }

    /**
     * @return the kind of Square this is
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * @return this Square's Label
     */
    public JLabel getLabel() {
        return this.label;
    }
}
