package lsystem.screen.main;

import lsystem.engine.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Listener extends AbstractListener implements KeyListener, MouseWheelListener {
    Tab tab;
    Example ex;
    MainFrame frame;
    Integer nbAxioms= 0;

    public Listener(String type, MainFrame frame,Tab tab){
        super(type);
        this.frame = frame;
        this.tab = tab;
    }
    public Listener(String type, MainFrame frame, Example ex){
        super(type);
        this.frame = frame;
        this.ex = ex;
    }
    public Listener(String type, MainFrame frame){
        super(type);
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case "Close":
                Component selected = frame.tabs.getSelectedComponent();
                if (selected != null) {
                    frame.tabs.remove(selected);
                }
                frame.nbTabs-=1;
                frame.renameTabs();
                break;

            case "Help":
                frame.newHelp();
                break;

            case "Tab":
                frame.newComponent((byte) 1);
                break;

            case "Example1":
                Parser parser = new Parser("Y", Arrays.asList("Y=X+[[Y]-Y]-X[-XY]+Y", "X=XX"), 5);
                frame.generateLSystem(this, parser, ex.exemple1);
                break;

            case "Example2":
                Parser parser1 = new Parser("X", Arrays.asList("X=XY", "Y=X"), 5);
                frame.generateLSystem(this, parser1 , ex.exemple2);
                break;

            case "Example":
                frame.newComponent((byte)0);
                break;

            case "Clear":
                tab.getTextArea((byte) 0).setText("Axiome :");
                tab.getTextArea((byte) 1).setText("Règles  :");
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

    @Override
    public void openDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
        new Listener("Clear",null, tab);
    }

    @Override
    public void keyTyped(KeyEvent ke) {


    }

    @Override
    public void keyPressed(KeyEvent ke) {
        byte i = (byte) ((type.equals("Axiome")) ? 0 :  1);
        if(ke.getKeyCode() == KeyEvent.VK_ENTER && tab.getTextField(i).getText().length() > 0){

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
