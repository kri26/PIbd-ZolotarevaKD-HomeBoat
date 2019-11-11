import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormBoat {

    private JFrame frame;

    Boat boat;
    JPanel panel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormBoat window = new FormBoat();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormBoat() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel = new PanelBoat();
        panel.setBounds(0, 0, 900, 500);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton button_Create = new JButton("Создать");
        button_Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((PanelBoat) panel).renovate(panel.getSize().width, panel.getSize().height);
                panel.repaint();
            }
        });

        button_Create.setBounds(0, 0, 89, 23);
        panel.add(button_Create);

        JButton btnNewButton_Up = new JButton("Up");
        btnNewButton_Up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ((PanelBoat) panel).MoveTransport(Direction.Up);
                panel.repaint();
            }
        });
        btnNewButton_Up.setBounds(750, 350, 50, 50);
        panel.add(btnNewButton_Up);

        JButton btnNewButton_Left = new JButton("Right");
        btnNewButton_Left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ((PanelBoat) panel).MoveTransport(Direction.Left);
                panel.repaint();
            }
        });
        btnNewButton_Left.setBounds(700, 400, 50, 50);
        panel.add(btnNewButton_Left);

        JButton btnNewButton_Down = new JButton("Down");
        btnNewButton_Down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ((PanelBoat) panel).MoveTransport(Direction.Down);
                panel.repaint();
            }
        });
        btnNewButton_Down.setBounds(750, 400, 50, 50);
        panel.add(btnNewButton_Down);

        JButton btnNewButton_Right = new JButton("Left");
        btnNewButton_Right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ((PanelBoat) panel).MoveTransport(Direction.Right);
                panel.repaint();
            }
        });
        btnNewButton_Right.setBounds(800, 400, 50, 50);
        panel.add(btnNewButton_Right);
    }

}