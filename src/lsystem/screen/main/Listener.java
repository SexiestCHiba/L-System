package lsystem.screen.main;

import lsystem.Main;
import lsystem.engine.Parser;
import lsystem.screen.AbstractCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Listener implements ActionListener, KeyListener {
    Tab tab;
    MainFrame frame;
    Integer index;
    String type;
    Integer nbAxioms;
    Thread parserThread = null;
    ImageIcon staticIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("loading-gif.gif")));


    public Listener(MainFrame frame, Integer index, String type, Tab tab){
        this.tab = tab;
        this.frame = frame;
        this.index = index;
        this.type = type;
        nbAxioms = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case "Help":
                frame.newHelp();
                break;
            case "Tab":
                frame.newTab();
                break;
            case "Clear":
                tab.getTextArea((byte) 0).setText("Axiome : \n");
                tab.getTextArea((byte) 1).setText("Règles : \n");
                tab.getTextField((byte) 0).setText("");
                tab.getTextField((byte) 1).setText("");
                Listener kl = (Listener) tab.getTextField((byte) 0).getKeyListeners()[0];
                kl.resetNbAxioms();

                break;

            case "Generate":
                String axiom = tab.getAxiom();
                List<String> rules = tab.getRules();
                Parser parser = new Parser(axiom, rules, tab.getNbIterations());
                if(Main.joglFrame.frame.isVisible()) {
                    openDialog("Veuillez fermer la fenêtre 3D avant de lancer une nouvelle génération");
                } else if(Main.joglFrame.parsedState == AbstractCanvas.State.LOAD) {
                        openDialog("Une génération est actuellement en cours, impossible d'en relancer un autre");
                    openDialog("Une génération est actuellement en cours, impossible d'en relancer un autre");
                } else if (!parser.isCorrect()) {
                    openDialog("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
                } else {
                    tab.submitButton.setIcon(staticIcon);
                    tab.submitButton.setText("");
                    parserThread = new Thread(() -> {
                        try {
                            Main.joglFrame.setLSystem(axiom, parser.parseRules(), tab.getNbIterations());
                            Main.joglFrame.setVisible(true);
                        } catch (NumberFormatException err) {
                            Main.joglFrame.parsedState = AbstractCanvas.State.FINISH_OR_NULL;
                            openDialog("Une erreur de type " + err.getClass().getSimpleName() + " est survenue lors de l'execution du parser: " + err.getMessage());
                        }
                        tab.submitButton.setIcon(null);
                        tab.submitButton.setText("Générer");
                    });
                    parserThread.start();
                }
                break;

        }

    }

    private void openDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
        new Listener(null, index, "Clear", tab);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        byte i = (byte) ((type.equals("Axiome")) ? 0 :  1);

        if(nbAxioms==0 && ke.getKeyChar() !='\b')
            tab.changeList(String.valueOf(ke.getKeyChar()), tab.getTextArea(i),nbAxioms);
        if(ke.getKeyChar() == '\b'){
            String str = tab.getTextArea(i).getText();
            if(str.length()>10) {
                if (!(str.endsWith(";\n") || str.endsWith(";"))){
                    str = str.substring(10, str.length() - 1);
                    tab.getTextArea(i).setText(type + " : \n" + str);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        byte i = (byte) ((type.equals("Axiome")) ? 0 :  1);

        if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
            tab.getTextField(i).setText(null);
            String str = ";";
            tab.changeList(str, tab.getTextArea(i),nbAxioms);
            if(i == 0)
                nbAxioms ++;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
    public void resetNbAxioms(){
        nbAxioms = 0;
    }
}
