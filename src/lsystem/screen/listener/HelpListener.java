package lsystem.screen.listener;

import lsystem.screen.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpListener implements ActionListener {

    MainFrame frame;
    public HelpListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.newHelp();
    }
}
