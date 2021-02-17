package lsystem.screen.listener;

import lsystem.engine.Parser;
import lsystem.engine.Rewrite;
import lsystem.screen.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Listener  implements ActionListener, KeyListener {

    MainFrame frame;
    Integer index;
    String type;
    Integer nbAxioms;

    public Listener(MainFrame frame, Integer index, String type){
        this.frame = frame;
        this.index = index;
        this.type = type;
        nbAxioms = 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case "Help" -> frame.newHelp();
            case "Tab" -> frame.newTab();
            case "Clear" -> {
                frame.textAreaList.get(index).setText("Axiome : \n");
                frame.textAreaList.get(index + 10).setText("Règles : \n");
                frame.textFieldList.get(index).setText("");
                frame.textFieldList.get(index + 10).setText("");
            }
            case "Generate" -> {
                String axiom = frame.getAxiom(index);
                List<String> rules = frame.getRules(index + 10);
                Parser parser = new Parser(axiom, rules, 12);
                if (!parser.isCorrect()) {
                    JOptionPane.showMessageDialog(null, "Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
                    new Listener(frame, index, "Clear");
                } else {
                    Rewrite rewriter = new Rewrite(axiom, parser.parseRules(), 12);
                    final String word = rewriter.rewrite();
                    System.out.println(word);
                }
            }
        }

    }
    @Override
    public void keyTyped(KeyEvent ke) {
        if(nbAxioms==0 && ke.getKeyChar() !='\b')
            frame.changeList(String.valueOf(ke.getKeyChar()), frame.textAreaList.get(index),nbAxioms);
        if(ke.getKeyChar() == '\b'){
            String str = frame.textAreaList.get(index).getText();
            if(str.length()>11) {
                str = str.substring(10, str.length() - 1);
                frame.textAreaList.get(index).setText(type + " : \n" + str);
            }
            else
                frame.textAreaList.get(index).setText(type+" : \n");

        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
            frame.textFieldList.get(index).setText(null);
            String str = ";";
            frame.changeList(str, frame.textAreaList.get(index),nbAxioms);
            if(type.equals("Axiome"))
                nbAxioms ++;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
