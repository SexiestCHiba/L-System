package lsystem.screen.listener;

import lsystem.screen.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;

public class FieldListener implements KeyListener {

    MainFrame frame;
    int index;
    int nbAxioms;
    byte type;

    public FieldListener(MainFrame frame, int index,byte type){
        this.frame = frame;
        this.index = index;
        nbAxioms = 0;
        this.type = type;
    }
    @Override
    public void keyTyped(KeyEvent ke) {

        if(ke.getKeyCode() != KeyEvent.VK_ENTER && nbAxioms==0)
            frame.changeList(String.valueOf(ke.getKeyChar()), (JTextArea) frame.textAreaList.get(index),nbAxioms);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
            frame.textFieldList.get(index).setText(null);
            String str = ";";
            frame.changeList(str, (JTextArea) frame.textAreaList.get(index),nbAxioms);
            if(type == 0)
                nbAxioms ++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
