package lsystem.screen.main;

import lsystem.engine.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Example extends JPanel {

    private final MainFrame frame;
    private final JButton a;
    private final JButton b;
    public Example(MainFrame frame) {
        this.frame = frame;

        a = new JButton("{axiom=\"Y\",rules:[\"Y=X+[[Y]-Y]-X[-XY]+Y\", \"X=XX\"]}");
        a.addActionListener(new ExampleListener("a"));
        this.add(a);
        b = new JButton("{axiom=\"X\",rules:[\"X=XY\",\"Y=X\"]}");
        b.addActionListener(new ExampleListener("b"));
        this.add(b);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private class ExampleListener extends AbstractListener  {

        public ExampleListener(String type) {
            super(type);
        }

        @Override
        public void openDialog(String message) {
            JOptionPane.showMessageDialog(null, message);
            new ExampleListener("Clear");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Parser parser;
            switch(type) {
                case "a":
                    parser = new Parser("Y", Arrays.asList("Y=X+[[Y]-Y]-X[-XY]+Y", "X=XX"), 5);
                    frame.generateLSystem(this, parser, a);
                break;
                case "b":
                    parser = new Parser("X", Arrays.asList("X=XY", "Y=X"), 5);
                    frame.generateLSystem(this, parser , b);

            }
        }
    }
}
