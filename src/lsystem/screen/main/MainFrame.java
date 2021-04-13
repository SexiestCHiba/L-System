package lsystem.screen.main;


import lsystem.screen.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainFrame extends JFrame {
	private int nbTabs;
	boolean helpWindow = false;
	private final JPanel basePanel;
	public final JTabbedPane tabs;
	private final JButton newGen;
	private final JButton help;
	private final int nbRules;

	/**
	 * Create a new JFrame on which will be displayed all the GUI elements.
	 */
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
		Dimension windowDimension = new Dimension(Constants.INITIAL_WIDTH, Constants.INITIAL_HEIGHT);
        this.setSize(windowDimension);
        this.setLocationRelativeTo(null);
        this.add(tabs);
        this.add(toolBar, BorderLayout.NORTH);
        this.setPreferredSize(windowDimension);
        newTab();
		renameTabs();
    }

	/**
	 * Decrease the nbTabs variable by one.
	 */
	public void decreaseTab(){
		nbTabs -=1;
	}

	/**
	 * Create and display a new frame (and throws a message if one is already open) which contains a helping text (Uses the Constants class).
	 */
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

	/**
	 * Call the Tab class to create a new JPanel that will be added to the tabs variable of this class (JTabbedPane).
	 */
	public void newTab() {
		if(nbTabs>2)
			JOptionPane.showMessageDialog(null, "Nombre maximal de générations atteintes");
		else {
			nbTabs++;
			tabs.addTab("Génération" + nbTabs,new Tab(nbTabs,this));
		}

	}

	/**
	 * Checks the name of each tab and change it considering their order.
	 */
	public void renameTabs(){
		for(int i =0;i<nbTabs;i++){
			tabs.setTitleAt(i,("Génération"+(i+1)));
		}
	}

}
