package lsystem.screen.main;

import lsystem.engine.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Listener implements ActionListener, KeyListener, MouseWheelListener {
    Tab tab;
    MainFrame frame;
    Integer index;
    String type;
    Integer nbAxioms= 0;
    ImageIcon staticIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("./loading-gif.gif")));


    public Listener(MainFrame frame, Integer index, String type, Tab tab){
        this.tab = tab;
        this.frame = frame;
        this.index = index;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case "Close":
                Component selected = frame.tabs.getSelectedComponent();
                if (selected != null) {
                    frame.tabs.remove(selected);
                }
                frame.decreaseTab();
                frame.renameTabs();
                break;

            case "Help":
                frame.newHelp();
                break;

            case "Tab":
                frame.newTab();
                break;
            case "example":
                frame.newExample();
                break;
            case "Clear":
                tab.getTextArea((byte) 0).setText("Axiome : \n");
                tab.getTextArea((byte) 1).setText("Règles : \n");
                tab.getTextField((byte) 0).setText("");
                tab.getTextField((byte) 1).setText("");
                Listener kl = (Listener) tab.getTextField((byte) 0).getKeyListeners()[0];
                kl.resetNbAxioms();
                break;

            case "Generate 3D":
            	String axiom3D = tab.getAxiom();
                List<String> rules3D = tab.getRules();
                Parser parser3D = new Parser(axiom3D, rules3D, tab.getNbIterations());
                frame.generateLSystem(this, parser3D, tab.submitButton);
                break;
        }

    }

    void openDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
        new Listener(null, index, "Clear", tab);
    }

    @Override
    public void keyTyped(KeyEvent ke) {


    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
            byte i = (byte) ((type.equals("Axiome")) ? 0 :  1);
            String text = tab.getTextField(i).getText();
            text = ((text.charAt(text.length()-1)==';') ? "\n"+text : "\n"+text+";");
            tab.getTextField(i).setText(null);
            if(nbAxioms > 0 && i == 0)
                JOptionPane.showMessageDialog(null, "Nombre maximal d'axiomes créés");
            else {
                tab.getTextArea(i).append(text);
                nbAxioms ++;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
    public void resetNbAxioms(){
        nbAxioms = 0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0)
            tab.itSpinner.setValue(tab.itSpinner.getNextValue());
        else
            tab.itSpinner.setValue(tab.itSpinner.getPreviousValue());
    }
}
