package lsystem.screen;


import lsystem.screen.listener.HelpListener;
import lsystem.screen.listener.NewGenListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;


public class MainFrame extends JFrame {


	private static final long serialVersionUID = -7898079642230075807L;
	private int nbTabs;
	private JPanel basePanel;
	private JTabbedPane tabs;
	private JButton newGen;
	private JButton help;
	private int nbRules;
	public HashMap<Integer,JTextArea> textAreaList;
	public HashMap<Integer,JTextField> textFieldList;

	public MainFrame(){
		textAreaList = new HashMap<>();
		textFieldList = new HashMap<>();
		nbRules = 1;
    	nbTabs = 0;
    	basePanel = new JPanel();
    	basePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	tabs = new JTabbedPane(SwingConstants.TOP);

    	JToolBar toolBar = new JToolBar();
        newGen = new JButton("Nouvelle génération");
        newGen.addActionListener(new NewGenListener(this));
        toolBar.add(newGen);
        help = new JButton("Aide");
        help.addActionListener(new HelpListener(this));
        toolBar.add(help);

        this.setTitle("L-system interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(tabs);
        this.add(toolBar, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(640,600));
    }
    public void addToTextAreaList(JTextArea c ,Integer i){
		textAreaList.put(i,c);
	}
	public void addToTextFieldList(JTextField c ,Integer i){
		textFieldList.put(i,c);
	}

	public void newHelp() {
		JFrame aide = new JFrame();
		aide.setTitle("Aide");
		aide.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aide.setSize(700,800);
		aide.setLocationRelativeTo(null);
		aide.setVisible(true);

		JTextArea helpText = new JTextArea();
        helpText.setText(Constants.HELP);
        helpText.setEditable(false);

        aide.add(helpText);
	}
	public void newTab() {
		if(nbTabs>2)
			JOptionPane.showMessageDialog(null, "Nombre maximal de générations atteintes");
		else {
			nbTabs++;
			new Tab(this, nbTabs, nbRules, tabs);
		}

	}
	public void closeTab() {
		//TODO : Pour fermer un onglet, nécessite l'implémentation d'un button fermer grâce à la méthode newTab().
	}
	public void changeList(String stringToAdd, byte messageType, JTextArea list) {
		if (stringToAdd == null) {
			switch(messageType) {
			case 0:
				list.setText("Axiome : \n");
			case 1:
				list.setText("Règles : \n");
			default:
				throw new IllegalArgumentException("Wrong argument given to method changeList");
			}
		}else{
			list.append(stringToAdd);
		}
	}
	public String getAxiom(){
		String str = "";
		// TODO : return the axiom
		return str;
	}
	public ArrayList<String> getRules(){
		ArrayList<String> list= new ArrayList<>();
		//TODO return the rules
		return list;
	}
	public void incorrect() {
		JFrame alert = new JFrame();
		alert.setTitle("Erreur");
		alert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alert.setSize(60,40);
		alert.setLocationRelativeTo(null);

		JLabel text = new JLabel("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
		alert.add(text);
		alert.setVisible(true);

		changeList(null,(byte) 0,null);
		changeList(null,(byte)1,null);

	}
}
