package lsystem.screen.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractListener implements ActionListener {

    protected final String type;
    protected ImageIcon staticIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("./loading-gif.gif")));

    public AbstractListener(String type) {
        this.type = type;
    }

    public abstract void openDialog(String message);
}
