package lsystem.screen.listener;

import lsystem.screen.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {
    MainFrame frame;
    int nbTabs;

    public ClearListener(MainFrame frame, int nbTabs){
        this.frame =frame;
        this.nbTabs = nbTabs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.textAreaList.get(nbTabs).setText("Axiome : \n");
        frame.textAreaList.get(nbTabs+10).setText("Règles : \n");
        frame.textFieldList.get(nbTabs).setText("");
        frame.textFieldList.get(nbTabs+10).setText("");
    }
}
