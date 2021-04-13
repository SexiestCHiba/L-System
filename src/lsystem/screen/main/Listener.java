package lsystem.screen.main;

import lsystem.Main;
import lsystem.engine.Parser;
import lsystem.screen.gl3d.AbstractCanvas;
import lsystem.utils.Pair;

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
    Thread parserThread = null;
    ImageIcon staticIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("./resources/loading-gif.gif")));


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
                if(Main.joglFrame.frame.isVisible()) {
                    openDialog("Veuillez fermer la fenêtre 2D ou 3D avant de lancer une nouvelle génération");
                } else if(Main.joglFrame.parsedState == AbstractCanvas.State.LOAD) {
                        openDialog("Une génération est actuellement en cours, impossible d'en relancer un autre");
                    openDialog("Une génération est actuellement en cours, impossible d'en relancer un autre");
                } else if (!parser3D.isCorrect()) {
                    openDialog("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
                } else {
                    tab.submitButton3D.setIcon(staticIcon);
                    tab.submitButton3D.setText("");
                    parserThread = new Thread(() -> {
                        try {
                            List<Pair<String, String>> lSystemRules = parser3D.parseRules();
                            Main.joglFrame.setLSystem(axiom3D, lSystemRules, tab.getNbIterations());

                            StringBuilder message = new StringBuilder("L-System 3D - {axiom:\"").append(axiom3D).append("\",rules:[");
                            for(int i = 0; i < lSystemRules.size(); ++i) {
                                Pair<String, String> rule = lSystemRules.get(i);
                                message.append("\"").append(rule.getLeft()).append("=").append(rule.getRight()).append("\"");
                                if(i + 1 != lSystemRules.size())
                                    message.append(",");
                            }
                            Main.joglFrame.frame.setTitle(message.append("]} - Nombres d'itérations: ").append(tab.getNbIterations()).toString());

                            Main.joglFrame.setVisible(true);
                        } catch (NumberFormatException err) {
                            Main.joglFrame.parsedState = AbstractCanvas.State.FINISH_OR_NULL;
                            openDialog("Une erreur de type " + err.getClass().getSimpleName() + " est survenue lors de l'execution du parser: " + err.getMessage());
                        }
                        tab.submitButton3D.setIcon(null);
                        tab.submitButton3D.setText("Générer en 3D");
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

        if(nbAxioms==0 && ke.getKeyChar() != '\b')
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0)
            tab.itSpinner.setValue(tab.itSpinner.getNextValue());
        else
            tab.itSpinner.setValue(tab.itSpinner.getPreviousValue());
    }
}
