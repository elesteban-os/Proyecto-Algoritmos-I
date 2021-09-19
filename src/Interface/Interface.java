package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    private JFrame princialWindow = new JFrame("Math Socket");
    private JLabel title = new JLabel("Math Socket");
    private JTextField name = new JTextField();
    private JButton create = new JButton("Crear");
    private JButton join = new JButton("Unirse");

    ActionListener createActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            princialWindow.setVisible(false);
        }
    };

    ActionListener joinActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent event){

        }
    };

    public Interface(){
        this.title.setBounds(150, 150, 200, 20);

        this.create.addActionListener(createActionListener);
        this.create.setBounds(50, 300, 150, 40);

        this.join.addActionListener(joinActionListener);
        this.join.setBounds(250, 300, 150, 40);

        this.name.setBounds(170, 250, 100, 30);
        this.name.setText("Nombre");

        this.princialWindow.add(this.title);
        this.princialWindow.add(this.create);
        this.princialWindow.add(this.join);
        this.princialWindow.add(this.name);

        this.princialWindow.setSize(500, 500);
        this.princialWindow.setLayout(null);
        this.princialWindow.setResizable(false);
        this.princialWindow.setVisible(true);
        this.princialWindow.setDefaultCloseOperation(princialWindow.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Interface interface1 = new Interface();
    }

}
