package lsystem.screen;

import javax.swing.*;

public class Help {
    public Help(JTabbedPane tabs){
        JPanel helpTab = new JPanel();
        JTextArea helpText = new JTextArea();
        helpText.setText(Constants.HELP);
        helpText.setEditable(false);
        helpTab.add(helpText);
        tabs.addTab("Aide",(new JScrollPane(helpTab)));

    }
}
