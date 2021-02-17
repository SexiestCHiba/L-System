package lsystem.screen.listener;

import lsystem.engine.Parser;
import lsystem.engine.Rewrite;
import lsystem.screen.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GenerateListener implements ActionListener {
    MainFrame frame;
    int nbTabs;
    public GenerateListener(MainFrame frame,int nbTabs) {
        this.frame = frame;
        this.nbTabs = nbTabs;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String axiom = frame.getAxiom(nbTabs);
        List<String> rules = frame.getRules(nbTabs+10);
        Parser parser = new Parser(axiom,rules,12);
        if(!parser.isCorrect()) {
            JOptionPane.showMessageDialog(null, "Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
            new ClearListener(frame,nbTabs).forceAction();
        }
        else{
            Rewrite rewriter = new Rewrite(axiom, parser.parseRules(), 12);
            final String word = rewriter.rewrite();
            System.out.println(word);
        }
    }
}
