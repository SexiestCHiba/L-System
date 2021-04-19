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
    public final JButton exemple1;
    public final JButton exemple2;
    public Example(MainFrame frame) {


        this.frame = frame;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 10;
        gc.weighty = 5;

        JLabel text = new JLabel("Vous pouvez lancer le programme avec les deux exemples de simulation ci-dessous : ");
        this.add(text,gc);

        gc.gridy = 1;
        exemple1 = newButton("{axiom=\"Y\",rules:[\"Y=X+[[Y]-Y]-X[-XY]+Y\", \"X=XX\"]}","Example1");
        this.add(exemple1,gc);

        gc.gridy = 2;
        exemple2 = newButton("{axiom=\"X\",rules:[\"X=XY\",\"Y=X\"]}","Example2");
        this.add(exemple2,gc);

        gc.gridy = 3;
        JButton close = new JButton("Close");
        close.addActionListener(new Listener("Close",frame));
        close.setBackground(Color.RED);
        this.add(close,gc);


    }
    private JButton newButton(String str, String str2){
        JButton res = new JButton(str);
        res.addActionListener(new Listener(str2,frame,this));
        res.setBackground(Color.LIGHT_GRAY);
        return res;
    }
}
