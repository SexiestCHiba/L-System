package lsystem.screen;

import lsystem.screen.listener.Listener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tab extends JPanel{

    int nbTabs;
    int nbRules;
    JSpinner nbIterations;
    JTextField axiomeField;
    JTextField rulesField;
    JTextArea axiomList;
    JTextArea rulesList;
    JSpinner itSpinner;

    public Tab(int nbTabs,int nbRules,JTabbedPane tabs) {
        this.nbRules = nbRules;
        this.nbTabs = nbTabs;

        JPanel tab = new JPanel();

        axiomList = textArea("Axiome : \n",nbTabs);
        rulesList = textArea("Règles : \n",nbTabs+10);

        nbIterations = new JSpinner();
        nbIterations.setModel(new SpinnerNumberModel(1, 1, 15, 1));

        JLabel itLabel  = new JLabel("Nombre d'itérations : ");
        itSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
        ((JSpinner.DefaultEditor) itSpinner.getEditor()).getTextField().setEditable(false);

        JLabel axiome = new JLabel("Axiome :");
        JLabel rules = new JLabel("Règle "+ nbRules+" :");

        axiomeField = new JTextField();
        axiomeField.addKeyListener(new Listener(null,nbTabs,"Axiome",this));
        axiomeField.setPreferredSize(new Dimension(120,20));

        rulesField = new JTextField();
        rulesField.addKeyListener(new Listener(null,nbTabs+10,"Règles",this));
        rulesField.setPreferredSize(new Dimension(120,20));

        JButton submitButton = new JButton("Générer");
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new Listener(null,nbTabs,"Clear",this));
        submitButton.addActionListener(new Listener(null,nbTabs,"Generate",this));
        JPanel southComponents = subPanel(clearButton,submitButton,null);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0.5;

        JPanel lists = subPanel(axiomList,rulesList,gc);
        JPanel fields = subPanel(axiomeField,rulesField,gc);
        JPanel labels = subPanel(axiome,rules,gc);

        JPanel Iterations = subPanel(itLabel,itSpinner,gc);

        JPanel aboveComponents = new JPanel();
        aboveComponents.add(labels);
        aboveComponents.add(fields);
        aboveComponents.add(lists);
        aboveComponents.add(Iterations);

        aboveComponents.setLayout(new GridLayout(1,4));
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
    public JTextArea getTextArea(byte i){
        return (i == 0) ? axiomList : rulesList;
    }
    public JTextField getTextField(byte i){
        return (i == 0) ? axiomeField : rulesField;
    }
    public void changeList(String stringToAdd, JTextArea list,int nbAxioms) {
        if(nbAxioms>0)
            JOptionPane.showMessageDialog(null, "Nombre maximal d'axiomes créés");
        else {
            list.append(stringToAdd);
            if (stringToAdd == ";")
                nbAxioms++;
        }

    }
    public String getAxiom(){
        String str = axiomList.getText();
        str = str.substring(10).replaceAll(";", "");
        return str;
    }
    public java.util.List<String> getRules(){
        List<String> list = new ArrayList<>();
        String str = rulesList.getText();
        str = str.substring(10).replaceAll(";", "");
        String[] strsplit =  str.split("\n");
        for(int y = 0;y<strsplit.length;y++){
            list.add(strsplit[y]);
        }
        return list;
    }
    public int getNbIterations(){
        return (int) itSpinner.getValue();
    }
}
