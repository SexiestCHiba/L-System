package lsystem.screen;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;

public class Tab {

    public Tab(MainFrame frame,int nbTabs,int nbRules,JTabbedPane tabs) {
        JPanel tab = new JPanel();

        JTextArea axiomList = new JTextArea();
        axiomList.setText("Axiome : \n");
        axiomList.setEditable(false);

        JTextArea rulesList = new JTextArea();
        rulesList.setText("Règles : \n");
        rulesList.setEditable(false);

        JButton closeButton = new JButton("x");


        JLabel axiome = new JLabel("Axiome");
        JLabel rules = new JLabel("Règle "+ nbRules);
        JTextField axiomeField = new JTextField();
        frame.addToComponentList(axiomeField,nbTabs);
        axiomeField.setPreferredSize(new Dimension(20,20));
        JTextField rulesField = new JTextField();
        frame.addToComponentList(rulesField,nbTabs+10);
        rulesField.setPreferredSize(new Dimension(20,20));
        tab.add(axiome);
        tab.add(axiomeField);
        tab.add(axiomList);
        tab.add(rules);
        tab.add(rulesField);
        tab.add(rulesList);
        tab.setLayout(new GridLayout(2,3));
        tabs.addTab("Génération"+String.valueOf(nbTabs), tab);
    }
}
