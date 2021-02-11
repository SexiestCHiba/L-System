package jogl;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame implements ActionListener{ 


	private static final long serialVersionUID = -7898079642230075807L;
	private int nbTabs;
	private JPanel basePanel;
	private JTabbedPane tabs;
	private JButton newGen;
	private JButton help;
	private JButton close;
	private int nbRules;

	public MainFrame(){ 
		
		
		nbRules = 1;
    	nbTabs = 0;
    	basePanel = new JPanel();
    	basePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	tabs = new JTabbedPane(SwingConstants.TOP);
    	
    	JToolBar toolBar = new JToolBar();
        newGen = new JButton("Nouvelle génération");
        newGen.addActionListener(this);
        toolBar.add(newGen);
        help = new JButton("Aide");
        help.addActionListener(this);
        toolBar.add(help);
    	
        this.setTitle("L-system interface"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400); 
        this.setLocationRelativeTo(null);
        this.add(tabs);
        this.add(toolBar, BorderLayout.NORTH);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGen)
			newTab();
		else if(e.getSource() == help)
			newHelp();
		else if(e.getSource() == close)
			closeTab();
		else
			throw new Error("This event does not trigger any action");

	} 
	public void newHelp() {
		JPanel helpTab = new JPanel();
		JTextArea helpText = new JTextArea();
		helpText.setEditable(false);
		helpText.setText(new Help().getHelp());
		helpTab.add(helpText);
		tabs.addTab("Aide",(new JScrollPane(helpTab)));
		
	}
	public void newTab() {
		
		nbTabs ++;
		JPanel tab = new JPanel();
		
		JTextArea axiomList = new JTextArea();
		axiomList.setEditable(false);
		axiomList.setText("Axiome : \n");
		
		JTextArea rulesList = new JTextArea();
		rulesList.setEditable(false);
		rulesList.setText("Règles : \n");
		
		JLabel axiome = new JLabel("Axiome");
		JLabel rules = new JLabel("Règle "+nbRules);
		JTextField axiomeField = new JTextField();
		JTextField rulesField = new JTextField();
		tab.add(axiome);
		tab.add(axiomeField);
		tab.add(axiomList);
		tab.add(rules);
		tab.add(rulesField);
		tab.add(rulesList);
		tab.setLayout(new GridLayout(2,3));
		tabs.addTab("Génération"+String.valueOf(nbTabs), tab);
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
				throw new Error("Wrong argument given to method changeList");			
			}	
		}else{
			list.append("-> "+stringToAdd + "\n");
		}
	}
	public void sendRule(){
		//TODO : send the string contain into a JTextField into the JTextAre ruleList or axiomList
	}
}

