package lsystem.screen.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example extends JPanel {

    private final MainFrame frame;

    public Example(MainFrame frame) {
        this.frame = frame;

        JPanel list = new JPanel();
        list.setLayout(new GridBagLayout());
        JButton a = new JButton("{axiom=\"Y\",rules:[\"Y=X+[[Y]-Y]-X[-XY]+Y\", \"X=XX\"]}");
        a.addActionListener(new ExampleListener("a"));
        JPanel aboveComponents = new JPanel();

    }

    private class ExampleListener implements ActionListener {

        private final String type;

        public ExampleListener(String type) {
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(type) {
                case "a":

            }
        }
    }
}
