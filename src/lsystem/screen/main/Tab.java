package lsystem.screen.main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Tab extends JPanel{

    int nbTabs;
    int nbRules;
    public JSpinner itSpinner;
    JTextField axiomeField,rulesField;
    JTextArea axiomList,rulesList;;
    JButton submitButton2D, submitButton3D, close;

    public Tab(int nbTabs,int nbRules,JTabbedPane tabs,MainFrame frame) {
        this.nbRules = nbRules;
        this.nbTabs = nbTabs;

        JPanel tab = new JPanel();

        axiomList = textArea("Axiome : \n",nbTabs);
        rulesList = textArea("Règles : \n",nbTabs+10);

        JLabel itLabel  = new JLabel("Nombre d'itérations : ");
        itSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        ((JSpinner.DefaultEditor) itSpinner.getEditor()).getTextField().setEditable(false);
        itSpinner.addMouseWheelListener(new Listener(null,null,"Spinner",this));

        JLabel axiome = new JLabel("Axiome :");
        JLabel rules = new JLabel("Règle "+ nbRules+" :");

        axiomeField = new JTextField();
        axiomeField.addKeyListener(new Listener(null,nbTabs,"Axiome",this));
        axiomeField.setPreferredSize(new Dimension(120,20));

        rulesField = new JTextField();
        rulesField.addKeyListener(new Listener(null,nbTabs+10,"Règles",this));
        rulesField.setPreferredSize(new Dimension(120,20));

        submitButton2D = new JButton("Générer en 2D");
        submitButton3D = new JButton("Générer en 3D");
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new Listener(null,nbTabs,"Clear",this));
        submitButton2D.addActionListener(new Listener(null,nbTabs,"Generate 2D",this));
        submitButton3D.addActionListener(new Listener(null,nbTabs,"Generate 3D",this));
        JPanel southComponents = subPanel(submitButton2D,submitButton3D, null);
        JPanel southComponents2 = subPanel(clearButton, southComponents, null);

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
        tab.add(southComponents2);
        tab.setLayout(new BoxLayout(tab,1));

        close = new JButton("Close");
        close.addActionListener(new Listener(frame,nbTabs,"Close",this));
        tab.add(close);


        tabs.addTab("Génération"+String.valueOf(nbTabs), tab);
    }
    public JTextArea textArea(String texte, int nb){
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
    public void changeList(String stringToAdd, JTextArea list, int nbAxioms) {
        if(nbAxioms > 0)
            JOptionPane.showMessageDialog(null, "Nombre maximal d'axiomes créés");
        else {
            list.append(stringToAdd);
            if (stringToAdd.equals(";"))
                nbAxioms++;
        }

    }
    public String getAxiom(){
        String str = axiomList.getText();
        str = str.substring(10).replaceAll(";", "");
        return str;
    }
    public java.util.List<String> getRules(){
        String str = rulesList.getText();
        str = str.substring(10).replaceAll(";", "");
        String[] strsplit =  str.split("\n");
        return Arrays.asList(strsplit);
    }
    public int getNbIterations(){
        return (int) itSpinner.getValue();
    }
}
