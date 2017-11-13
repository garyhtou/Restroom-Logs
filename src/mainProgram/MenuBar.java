package mainProgram;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class MenuBar {
	public static JMenuBar createMenuBar() { //JmenuBar = Menu Bar, JMenu = new dropdown, JMunuItem = item in a dropdown
    	
    	//MENUBAR
        JMenuBar menubar = new JMenuBar();
        
        //ICONS
        ImageIcon fileExitIcon = new ImageIcon("images/exit.png");
        ImageIcon dataViewIcon = new ImageIcon("images/view.png");
        ImageIcon dataLogsIcon = new ImageIcon("images/logs.png");
        ImageIcon filePreferencesIcon = new ImageIcon("images/preferences.png");
        
        //MENU
        JMenu file = new JMenu("File"); //New dropdown
        file.setMnemonic(KeyEvent.VK_F); //Short cut for "File" drop down
        JMenu data = new JMenu("Data"); //New dropdown
        data.setMnemonic(KeyEvent.VK_D); //Short cut for "File" drop down
        
        //MENU ITEM
        //exit
        JMenuItem fileExit = new JMenuItem("Exit", fileExitIcon); //creates dropdown item "Exit" and it's icon to hte File dropdown
        fileExit.setMnemonic(KeyEvent.VK_E); //short cut for exit
        fileExit.setToolTipText("Exit application");
        fileExit.addActionListener((ActionEvent existButtonEvent) -> { //When clicked, exit
            System.exit(0);
        });
        //view entries
        JMenuItem dataView = new JMenuItem("View Entries", dataViewIcon);
        dataView.setMnemonic(KeyEvent.VK_K);
        dataView.setToolTipText("View Sign in/out");
        dataView.addActionListener((ActionEvent dataViewButtonEvent) -> {
        	//CALL BACKEND TO CREATE VIEW PDF (copy from main pdf)
        	//SHOW VIEW PDF
        	/*if(Back_End.createViewPDF()) {
        		Back_End.createViewPDF();
        	}
        	else {
        		JOptionPane.showMessageDialog(dataView,
					    "Can not create ViewLogPDF for viewing.", //message
					    "ViewLogPDF File Error", //title
					    JOptionPane.ERROR_MESSAGE);
        	}*/
        	
        });
        //log .txt
        JMenuItem dataLogs = new JMenuItem("Logs", dataLogsIcon);
        dataLogs.setMnemonic(KeyEvent.VK_K);
        dataLogs.setToolTipText("View logs");
        dataLogs.addActionListener((ActionEvent dataLogsButtonEvent) -> {
        	//SHOW VIEW .TXT FILE
        	//https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
        	
        	//PROBELM: doesn't auto wrap
        	String fileContent;
			try {
				fileContent = new Scanner(new File("data\\Logs.txt")).useDelimiter("\\Z").next();
	        	//TEST: JOptionPane.showMessageDialog(dataLogs, fileContent);
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				double screenWidth = screenSize.getWidth();
				double screenHeight = screenSize.getHeight();
				//JOptionPane.showMessageDialog(dataLogs, screenWidth + " " + screenHeight);
				
				int displayWidth = (int) (screenWidth/15);
				int displayHeight = (int) (screenHeight/20);
				
				JTextArea textArea = new JTextArea(displayHeight, displayWidth);
			      textArea.setText(fileContent);
			      textArea.setEditable(false);
			      textArea.setLineWrap(true);
			      textArea.setWrapStyleWord(true);
			      textArea.setMargin(new Insets(10,10,10,10));
			      textArea.setCaretPosition(0);
			      
			      // wrap a scrollpane around it
			      JScrollPane scrollPane = new JScrollPane(textArea);
			      
			      // display them in a message dialog
			      JOptionPane.showMessageDialog(dataLogs, scrollPane);
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(dataLogs,
					    "Can not open Log file.", //message
					    "Log File Error", //title
					    JOptionPane.ERROR_MESSAGE);
			}
        });
        
        JMenuItem filePreferences = new JMenuItem("Preferences", filePreferencesIcon);
        filePreferences.setMnemonic(KeyEvent.VK_P);
        filePreferences.setToolTipText("Preferences");
        filePreferences.addActionListener((ActionEvent filePreferencesButonEvent) -> { 
    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double screenWidth = screenSize.getWidth();
			double screenHeight = screenSize.getHeight();
			int displayWidth = (int) (screenWidth/1.3);
			int displayHeight = (int) (screenHeight/1.3);
        	
    		JTabbedPane tabbedPane = new JTabbedPane();
    		//tabbedPane.setPreferredSize(new Dimension(displayWidth, displayHeight));
    		
    		ImageIcon icon1 = new ImageIcon("images/noIcon.png");
    		ImageIcon icon2 = new ImageIcon("images/noIcon.png");
    		ImageIcon icon3 = new ImageIcon("images/noIcon.png");
    		ImageIcon icon4 = new ImageIcon("images/noIcon.png");
    		ImageIcon icon5 = new ImageIcon("images/noIcon.png");
    		
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

    		JOptionPane.showMessageDialog(filePreferences, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
    		
        });
        
        
        
        //DISPLAY, backwards
        //file
        file.add(fileExit); //adds the exit menuitem to the "file" menu
        file.add(filePreferences);
        
        menubar.add(file); //adds the file menu to the menubar
        //data
        data.add(dataView);
        data.add(dataLogs);
        
        menubar.add(data);
        
        return menubar;
        //setJMenuBar(menubar); //displays menubar
    }
}
