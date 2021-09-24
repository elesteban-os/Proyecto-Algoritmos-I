package List;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Objects;

public class Box {

    private String type;
    private int[] position;
    private ImageIcon image;
    private JLabel label;

    public Box(String type, int x, int y) {
        this.type = type;
        if (Objects.equals(type, "Goal")) {
            this.image = new ImageIcon(getClass().getResource("/Images/goal.png"));
            this.type = "Tunnel";
        } else if (Objects.equals(type, "Tunnel")) {
            this.image = new ImageIcon(getClass().getResource("/Images/tunnel.png"));
        } else if (Objects.equals(type, "Trap")) {
            this.image = new ImageIcon(getClass().getResource("/Images/trap.png"));
        } else {
            this.image = new ImageIcon(getClass().getResource("/Images/challenge.png"));
        }
        this.position = new int[2];
        this.position[0] = x;
        this.position[1] = y;
        this.label = new JLabel();
        this.label.setBounds(this.position[0], this.position[1], 100, 100);
        this.label.setIcon(this.image);
    }

    public String getType() {
        return this.type;
    }

    public int[] getPosition() {
        return this.position;
    }

    public JLabel getLabel() {
        return this.label;
    }
}
