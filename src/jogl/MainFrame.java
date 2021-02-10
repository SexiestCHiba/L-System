package jogl;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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

	public MainFrame(){ 
		
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

	} 
	public void newHelp() {
		JPanel helpTab = new JPanel();
		JLabel helpText = new JLabel();
		helpText.setText("Aled");
		helpTab.add(helpText);
		tabs.addTab("Oskour",helpTab);
		
	}
	public void newTab() {
		nbTabs ++;
		JPanel tab = new JPanel();
		tabs.addTab("Génération"+String.valueOf(nbTabs), tab);
	}
	public void closeTab() {

	}
}

