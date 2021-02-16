package lsystem.screen;

import lsystem.screen.listener.AxiomFieldListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;

public class Tab {

    public Tab(MainFrame frame,int nbTabs,int nbRules,JTabbedPane tabs) {
        JPanel tab = new JPanel();

        JTextArea axiomList = new JTextArea();
        axiomList.setText("Axiome : \n");
        axiomList.setEditable(false);
        frame.addToTextAreaList(axiomList,nbTabs);

        JTextArea rulesList = new JTextArea();
        rulesList.setText("Règles : \n");
        rulesList.setEditable(false);
        frame.addToTextAreaList(rulesList,nbTabs+10);

        JButton closeButton = new JButton("x");


        JLabel axiome = new JLabel("Axiome");
        JLabel rules = new JLabel("Règle "+ nbRules);

        JTextField axiomeField = new JTextField();
        axiomeField.addKeyListener(new AxiomFieldListener(frame,nbTabs));
        axiomeField.setPreferredSize(new Dimension(20,20));
        frame.addToTextFieldList(axiomeField,nbTabs);

        JTextField rulesField = new JTextField();
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
