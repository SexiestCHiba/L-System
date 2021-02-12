package lsystem.screen.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lsystem.screen.MainFrame;

public class NewGenListener implements ActionListener {
    MainFrame frame;
    public NewGenListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.newTab();
    }
}
