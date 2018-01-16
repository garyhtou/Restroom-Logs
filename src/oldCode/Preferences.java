package oldCode;

import java.util.*;
import javax.swing.*;

import interfaces.RL_Fonts;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;

public class Preferences {

	public static void main(String[] args) {
		preferences();
	}
	public static void preferences() {
		ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		int displayWidth = (int) (screenWidth/1.3);
		int displayHeight = (int) (screenHeight/1.3);
    	
		JTabbedPane tabbedPane = new JTabbedPane();
		//tabbedPane.setPreferredSize(new Dimension(displayWidth, displayHeight));
		
		ImageIcon blankIcon = new ImageIcon("assets/images/noIcon.png");
		
		Font f = tabbedPane.getFont();
		Font f2 = new Font(f.getFontName(), Font.BOLD, f.getSize()+15);
		
		newPreferencesTab GeneralTab = new newPreferencesTab(tabbedPane, "General", null, "General ToolTip");
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JPanel GeneralTC = new JPanel();
		GeneralTab.add(GeneralTC);
		
		
    		//TODO: add time zone change for logs (Back_End.updateLogs)
		
		

		JComponent panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5, 1));
		tabbedPane.addTab("Logs", blankIcon, panel2, "Logs Preferences");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		/*//TITLE
    		JTextArea preferencesLogsTitle = new JTextArea();
    		//textArea Settings
    		preferencesLogsTitle.setFont(f2);
    		preferencesLogsTitle.setOpaque(false);
    		preferencesLogsTitle.setEditable(false);
    		//CONTENT
    		preferencesLogsTitle.setText("Logs");
    		panel2.add(preferencesLogsTitle);*/
		addTitle(panel2, "LOGS");
		
    	JTextArea preferencesLogsClearText = new JTextArea();
    	preferencesLogsClearText.setOpaque(false);
    	preferencesLogsClearText.setEditable(false);
    	//CONTENT
    	preferencesLogsClearText.setText("Clear PDF Log file");
    	panel2.add(preferencesLogsClearText);
    	
    	JButton preferencesLogsClearButton = new JButton("Clear PDF"); //creates button named "Quit"
    	preferencesLogsClearButton.setToolTipText("Caution, this will clear all student entry and exit logs"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
    	preferencesLogsClearButton.addActionListener((ActionEvent preferencesLogsClearButtonEvent) -> { //event listener, if clicked, system.exit(0);
           	
    		int choice = JOptionPane.showConfirmDialog(panel2, "Are you sure you want to clear the PDF Log file?\nThis will clear all student entry and exit logs");
    		if(choice == JOptionPane.YES_OPTION) {
    			logs.createPDF();
    			logs.updateLogsSystem("LogsPDF Cleared");
    			JOptionPane.showMessageDialog(panel2, "PDF Log has been cleared");
    		}
        });
    	panel2.add(preferencesLogsClearButton);
		//Clear logs (run initStartUp for back_end)
		
		JComponent panel3 = new JPanel();
		tabbedPane.addTab("Email", blankIcon, panel3, "Email Preferences");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		addTitle(panel3, "General");
		
		//clear email history

		JComponent panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Wifi", blankIcon, panel4, "Wifi Infomation");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		addTitle(panel4, "General");
		
		
		//using Ethernet
		//turn on and off wifi (airplane mode), see all wifi info (MAC, IP4, IP6, etc.)
		
		JComponent panel5 = new JPanel();
		panel5.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("DataBase", blankIcon, panel5, "Student DataBase Configuration");
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
		addTitle(panel5, "General");
		
		//insert database (either remote or file path to local)
		
		//TEST COMP
		JComponent panel6 = new JPanel();
		panel5.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("TEST", blankIcon, panel6, "TESTTESTTEST");
		tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
		addTitle(panel6, "TEST");
		
		
		
		JOptionPane.showMessageDialog(null, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
		

	}
	
	/**
	 * 
	 * @param addToComp JComponent
	 * @param comp JTextArea
	 * @param title String
	 */
	public static void addTitle(JComponent addToComp, String title) {
		JTextArea textArea = new JTextArea();
		//textArea Settings
		textArea.setFont(RL_Fonts.preferencesTitle);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		textArea.setText(title);
		addToComp.add(textArea);
	}

}
