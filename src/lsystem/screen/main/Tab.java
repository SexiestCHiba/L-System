package lsystem.screen.main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Tab extends JPanel{

    public JSpinner itSpinner;
    JTextField axiomeField, rulesField;
    JTextArea axiomList, rulesList;
    JButton submitButton;

    /**
     * A method which create a new instance of the class JPanel ready to be added to the MainFrame's tabs variable (JTabbedPane).
     * @param frame the MainFrame instance that is useful to access all the components.
     */
    public Tab(MainFrame frame) {

        axiomList = textArea("Axiome :");
        rulesList = textArea("Règles :");

        JLabel itLabel  = new JLabel("Nombre d'itérations : ");
        itSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        ((JSpinner.DefaultEditor) itSpinner.getEditor()).getTextField().setEditable(false);
        itSpinner.addMouseWheelListener(new Listener("Spinner",null,this));

        JLabel axiome = new JLabel("Axiome :");
        JLabel rules = new JLabel("Règles  :");

        axiomeField = new JTextField();
        axiomeField.addKeyListener(new Listener("Axiome",null,this));
        axiomeField.setPreferredSize(new Dimension(120,20));

        rulesField = new JTextField();
        rulesField.addKeyListener(new Listener("Règles",null,this));
        rulesField.setPreferredSize(new Dimension(120,20));
        submitButton = new JButton("Générer en 3D");
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new Listener("Clear",null,this));
        clearButton.setBackground(Color.GREEN);
        submitButton.addActionListener(new Listener("Generate 3D",frame,this));
        submitButton.setBackground(Color.CYAN);
        JButton close = new JButton("Close");
        close.addActionListener(new Listener("Close",frame));
        close.setBackground(Color.RED);
        JPanel southComponents = subPanel(submitButton,close,null);
        southComponents = subPanel(clearButton, southComponents, null);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0.5;

        JPanel lists = subPanel(axiomList, rulesList,gc);
        JPanel fields = subPanel(axiomeField, rulesField,gc);
        JPanel labels = subPanel(axiome, rules, gc);

        JPanel Iterations = subPanel(itLabel, itSpinner, gc);

        JPanel aboveComponents = new JPanel();
        aboveComponents.add(labels);
        aboveComponents.add(fields);
        aboveComponents.add(lists);
        aboveComponents.add(Iterations);


        aboveComponents.setLayout(new GridLayout(1,4));
        this.add(aboveComponents);
        this.add(southComponents);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Create and return a newly created TextArea component.
     * @param texte the text that will be added in the returned component.
     * @return A JTextArea component.
     */
    public JTextArea textArea(String texte){
        JTextArea res = new JTextArea();
        res.setText(texte);
        res.setEditable(false);
        res.setPreferredSize(new Dimension(120,100));

        return res;
    }

    /**
     * Fuse two components into a JPanel component organized with given GridBagConstraints.
     * @param a the first component to add in the JPanel.
     * @param b the second component to add in the JPanel.
     * @param gc the GridBagConstraints given to organise the two fused components.
     * @return A JPanel component.
     */
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

    /**
     * An accessor to the axiomList and the rulesList
     * @param i a byte which indicates which component to return (axiomList or rulesList).
     * @return A JTextArea component.
     */
    public JTextArea getTextArea(byte i){
        return (i == 0) ? axiomList : rulesList;
    }

    /**
     * An accessor to the axiomField and the rulesField.
     * @param i a byte which indicates which component to return (axiomField or rulesField).
     * @return A JTextField component.
     */
    public JTextField getTextField(byte i){
        return (i == 0) ? axiomeField : rulesField;
    }



    /**
     * @return A string which contains the axiom entered by the user.
     */
    public String getAxiom() {
        String str = axiomList.getText();
        str = str.substring(10).replaceAll(";", "");
        return str;
    }

    /**
     * @return A list of Strings corresponding to the different rules the user has entered.
     */
    public java.util.List<String> getRules(){
        String str = rulesList.getText();
        str = str.substring(10).replaceAll(";", "");
        String[] strsplit =  str.split("\n");
        return Arrays.asList(strsplit);
    }

    /**
     * @return The number of iterations selected by the user thanks to the JSpinner.
     */
    public int getNbIterations(){
        return (int) itSpinner.getValue();
    }

}
