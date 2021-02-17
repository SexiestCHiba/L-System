package lsystem.screen;

import lsystem.screen.listener.ClearListener;
import lsystem.screen.listener.FieldListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;

public class Tab {
    MainFrame frame;
    int nbTabs;
    int nbRules;

    public Tab(MainFrame frame,int nbTabs,int nbRules,JTabbedPane tabs) {
        this.frame = frame;
        this.nbRules = nbRules;
        this.nbTabs = nbTabs;

        JPanel tab = new JPanel();

        JTextArea axiomList = textArea("Axiome : \n",nbTabs);
        JTextArea rulesList = textArea("Règles : \n",nbTabs+10);




        JLabel axiome = new JLabel("Axiome :");
        JLabel rules = new JLabel("Règle "+ nbRules+" :");

        JTextField axiomeField = new JTextField();
        axiomeField.addKeyListener(new FieldListener(frame,nbTabs,(byte)0));
        axiomeField.setPreferredSize(new Dimension(120,20));
        frame.addToTextFieldList(axiomeField,nbTabs);

        JTextField rulesField = new JTextField();
        rulesField.addKeyListener(new FieldListener(frame,nbTabs+10,(byte)1));
        rulesField.setPreferredSize(new Dimension(120,20));
        frame.addToTextFieldList(rulesField,nbTabs+10);

        JButton submitButton = new JButton("Générer");
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearListener(frame,nbTabs));
        JPanel southComponents = subPanel(clearButton,submitButton,null);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0.5;

        JPanel eastComponents = subPanel(axiomList,rulesList,gc);
        JPanel centerComponents = subPanel(axiomeField,rulesField,gc);
        JPanel westComponents = subPanel(axiome,rules,gc);


        JPanel aboveComponents = new JPanel();
        aboveComponents.add(westComponents);
        aboveComponents.add(centerComponents);
        aboveComponents.add(eastComponents);

        aboveComponents.setLayout(new GridLayout(1,3));
        tab.add(aboveComponents);
        tab.add(southComponents);
        tab.setLayout(new BoxLayout(tab,1));

        tabs.addTab("Génération"+String.valueOf(nbTabs), tab);
    }
    public JTextArea textArea(String texte,int nb){
        JTextArea res = new JTextArea();
        res.setText(texte);
        res.setEditable(false);
        res.setPreferredSize(new Dimension(120,100));
        frame.addToTextAreaList(res,nb);
        return res;
    }
    public JPanel subPanel(Component a, Component b,GridBagConstraints gc){
        JPanel res = new JPanel();
        if(gc == null){
            res.add(a);
            res.add(b);
            return res;
        }
        res.setLayout(new GridBagLayout());
        res.add(a,gc);
        gc.gridy = 1;
        res.add(b,gc);
        gc.gridy = 0;
        return res;
    }
}
