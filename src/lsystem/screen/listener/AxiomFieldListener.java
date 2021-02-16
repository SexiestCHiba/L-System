package lsystem.screen.listener;

import lsystem.screen.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AxiomFieldListener implements KeyListener {
    private MainFrame frame;
    private int index;

    public AxiomFieldListener(MainFrame frame,int index){
        this.frame = frame;
        this.index = index;
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyCode() != 10)
            frame.changeList(String.valueOf(ke.getKeyChar()),(byte)0, (JTextArea) frame.textAreaList.get(index));
        else{
            String str = ";\n";
            frame.changeList(str,(byte)0, (JTextArea) frame.textAreaList.get(index));
            frame.textFieldList.get(index).setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
