package mainProgram;

import java.util.*;
import javax.swing.*;  
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
		
		ImageIcon icon1 = new ImageIcon("assets/images/noIcon.png");
		ImageIcon icon2 = new ImageIcon("assets/images/noIcon.png");
		ImageIcon icon3 = new ImageIcon("assets/images/noIcon.png");
		ImageIcon icon4 = new ImageIcon("assets/images/noIcon.png");
		ImageIcon icon5 = new ImageIcon("assets/images/noIcon.png");
		
		Font f = tabbedPane.getFont();
		Font f2 = new Font(f.getFontName(), Font.BOLD, f.getSize()+15);
		
		JComponent panel1 = new JPanel();
		tabbedPane.addTab("General", icon1, panel1, "General Preferences"); //table title, tab icon, tab content, tab ToolTipText
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		//TITLE
    		JTextArea preferencesGeneralTitle = new JTextArea();
    		//textArea Settings
    		preferencesGeneralTitle.setFont(f2);
    		preferencesGeneralTitle.setOpaque(false);
    		preferencesGeneralTitle.setEditable(false);
    		//CONTENT
    		preferencesGeneralTitle.setText("General");
    		panel1.add(preferencesGeneralTitle);

		JComponent panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5, 1));
		tabbedPane.addTab("Logs", icon2, panel2, "Logs Preferences");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		//TITLE
    		JTextArea preferencesLogsTitle = new JTextArea();
    		//textArea Settings
    		preferencesLogsTitle.setFont(f2);
    		preferencesLogsTitle.setOpaque(false);
    		preferencesLogsTitle.setEditable(false);
    		//CONTENT
    		preferencesLogsTitle.setText("Logs");
    		panel2.add(preferencesLogsTitle);
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
    			Back_End.initStartUp();
    			JOptionPane.showMessageDialog(panel2, "PDF Log has been cleared");
    		}
        });
    	panel2.add(preferencesLogsClearButton);
		//Clear logs (run initStartUp for back_end)
		
		JComponent panel3 = new JPanel();
		tabbedPane.addTab("Email", icon3, panel3, "Email Preferences");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		//TITLE
    		JTextArea preferencesEmailTitle = new JTextArea();
    		//textArea Settings
    		preferencesEmailTitle.setFont(f2);
    		preferencesEmailTitle.setOpaque(false);
    		preferencesEmailTitle.setEditable(false);
    		//CONTENT
    		preferencesEmailTitle.setText("Email");
    		panel3.add(preferencesEmailTitle);
		//clear email history

		JComponent panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Wifi", icon4, panel4, "Wifi Infomation");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		//TITLE
    		JTextArea preferencesWifiTitle = new JTextArea();
    		//textArea Settings
    		preferencesWifiTitle.setFont(f2);
    		preferencesWifiTitle.setOpaque(false);
    		preferencesWifiTitle.setEditable(false);
    		//CONTENT
    		preferencesWifiTitle.setText("Wifi");
    		panel4.add(preferencesWifiTitle);
		//using Ethernet
		//turn on and off wifi (airplane mode), see all wifi info (MAC, IP4, IP6, etc.)
		
		JComponent panel5 = new JPanel();
		panel5.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("DataBase", icon5, panel5, "Student DataBase Configuration");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_5);
		//TITLE
    		JTextArea preferencesDatabaseTitle = new JTextArea();
    		//textArea Settings
    		preferencesDatabaseTitle.setFont(f2);
    		preferencesDatabaseTitle.setOpaque(false);
    		preferencesDatabaseTitle.setEditable(false);
    		//CONTENT
    		preferencesDatabaseTitle.setText("DataBase");
    		panel5.add(preferencesDatabaseTitle);
		//insert database (either remote or file path to local)

		JOptionPane.showMessageDialog(null, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
		

	}
	
	

}
