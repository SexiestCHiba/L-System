package lsystem.screen.main;


import lsystem.screen.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainFrame extends JFrame {


	private static final long serialVersionUID = -7898079642230075807L;
	private int nbTabs;
	boolean helpWindow = false;
	private JPanel basePanel;
	private JTabbedPane tabs;
	private JButton newGen;
	private JButton help;
	private int nbRules;


	public MainFrame(){

		nbRules = 1;
    	nbTabs = 0;
    	basePanel = new JPanel();
    	basePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	tabs = new JTabbedPane(SwingConstants.TOP);

    	JToolBar toolBar = new JToolBar();
        newGen = new JButton("Nouvelle génération");
        newGen.addActionListener(new Listener(this,null,"Tab",null));
        toolBar.add(newGen);
        help = new JButton("Aide");
        help.addActionListener(new Listener(this,null,"Help",null));
        toolBar.add(help);

        this.setTitle("L-system interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(tabs);
        this.add(toolBar, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(640,600));
        
        nbTabs++;
		new Tab(nbTabs, nbRules, tabs);
    }

	public void newHelp() {
		if(helpWindow){
			JOptionPane.showMessageDialog(null, "Une fenêtre d'aide est déjà ouverte.");
		}
		else {
			helpWindow = true;
			
			JFrame aide = new JFrame();
			
			aide.setTitle("Aide");
			aide.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			aide.setSize(700,500);
			aide.setLocationRelativeTo(null);
			aide.setVisible(true);

			JTextArea helpText = new JTextArea();
			helpText.setText(Constants.HELP);
			helpText.setEditable(false);

			JScrollPane scrollbar = new JScrollPane(helpText);
			scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			aide.add(scrollbar);
			
			aide.addWindowListener(new WindowAdapter() {
			    public void windowClosed(WindowEvent e) {
			    	helpWindow = false;
			    }
			});
		}
	}
	public void newTab() {
		if(nbTabs>2)
			JOptionPane.showMessageDialog(null, "Nombre maximal de générations atteintes");
		else {
			nbTabs++;
			new Tab(nbTabs, nbRules, tabs);
		}

	}
	public void closeTab() {
		//TODO : Pour fermer un onglet, nécessite l'implémentation d'un button fermer grâce à la méthode newTab().
	}




}
