package lsystem.screen.main;


import lsystem.Main;
import lsystem.engine.Parser;
import lsystem.screen.Constants;
import lsystem.screen.gl3d.AbstractCanvas;
import lsystem.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class MainFrame extends JFrame {

    Thread parserThread = null;
    public int nbTabs;
	boolean helpWindow = false;
	private final JPanel basePanel;
	public final JTabbedPane tabs;
	private final JButton newGen;
    private final JButton example;
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
        newGen.addActionListener(new Listener("Tab",this));
        toolBar.add(newGen);
        example = new JButton("Exemples");
        example.addActionListener(new Listener("Example", this));
        toolBar.add(example);
        help = new JButton("Aide");
        help.addActionListener(new Listener("Help",this));
        toolBar.add(help);

        this.setTitle("L-system interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension windowDimension = new Dimension(Constants.INITIAL_WIDTH, Constants.INITIAL_HEIGHT);
        this.setSize(windowDimension);
        this.setLocationRelativeTo(null);
        this.add(tabs);
        this.add(toolBar, BorderLayout.NORTH);
        this.setPreferredSize(windowDimension);
        newComponent((byte)1);
		renameTabs();
		this.setResizable(false);
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
	 * Checks the name of each tab and change it considering their order.
	 */
	public void renameTabs(){
		for(int i =0;i<nbTabs;i++){
			if(!tabs.getTitleAt(i).equals("Exemples"))
				tabs.setTitleAt(i,("Génération"+(i+1)));
		}
	}
	/**
	 * Call the Tab or the Exemple class to create a new JPanel that will be added to the tabs variable of this class (JTabbedPane).
	 */

	public void newComponent(byte i ){
		if(nbTabs > 2)
			JOptionPane.showMessageDialog(null, "Nombre maximal d'onglets atteint");
		else {
			nbTabs++;
			switch(i){
				case 1:
					tabs.addTab("Génération" + nbTabs,new Tab(this));
					break;
				case 0:
					tabs.addTab("Exemples" ,new Example(this));
					break;
			}
		}
	}


    public void generateLSystem(AbstractListener listener, Parser parser, JButton submitButton) {
        if(Main.joglFrame.frame.isVisible()) {
            listener.openDialog("Veuillez fermer la fenêtre 2D ou 3D avant de lancer une nouvelle génération");
        } else if(Main.joglFrame.parsedState == AbstractCanvas.State.LOAD) {
            listener.openDialog("Une génération est actuellement en cours, impossible d'en relancer un autre");
        } else if (!parser.isCorrect()) {
            listener.openDialog("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
        } else {
            String initialText = submitButton.getText();
            parserThread = new Thread(() -> {
                try {
                    submitButton.setIcon(listener.staticIcon);
                    submitButton.setText("");
                    List<Pair<String, String>> lSystemRules = parser.parseRules();
                    Main.joglFrame.setLSystem(parser.getAxiom(), lSystemRules, parser.getNbIterations());

                    StringBuilder message = new StringBuilder("L-System 3D - {axiom:\"").append(parser.getAxiom()).append("\",rules:[");
                    for(int i = 0; i < lSystemRules.size(); ++i) {
                        Pair<String, String> rule = lSystemRules.get(i);
                        message.append("\"").append(rule.getLeft()).append("=").append(rule.getRight()).append("\"");
                        if(i + 1 != lSystemRules.size())
                            message.append(",");
                    }
                    Main.joglFrame.frame.setTitle(message.append("]} - Nombres d'itérations: ").append(parser.getNbIterations()).toString());

                    Main.joglFrame.setVisible(true);
                } catch (NumberFormatException err) {
                    Main.joglFrame.parsedState = AbstractCanvas.State.FINISH_OR_NULL;
                    listener.openDialog("Une erreur de type " + err.getClass().getSimpleName() + " est survenue lors de l'execution du parser: " + err.getMessage());
                }
                submitButton.setIcon(null);
                submitButton.setText(initialText);
            });

            parserThread.start();
        }
    }

}
