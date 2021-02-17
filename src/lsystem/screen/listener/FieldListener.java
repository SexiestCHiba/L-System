package lsystem.screen.listener;

import lsystem.screen.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;

public class FieldListener implements KeyListener {

    MainFrame frame;
    int index;
    byte i;

    public FieldListener(MainFrame frame, int index, byte i){
        this.frame = frame;
        this.index = index;
        this.i = i;


    }
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyCode() != KeyEvent.VK_ENTER)
            frame.changeList(String.valueOf(ke.getKeyChar()),i, (JTextArea) frame.textAreaList.get(index));
        else{
            String str = ";\r\n";
            frame.changeList(str,i, (JTextArea) frame.textAreaList.get(index));
            frame.textFieldList.get(index).setText(null);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER)
            frame.textFieldList.get(index).setText(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
