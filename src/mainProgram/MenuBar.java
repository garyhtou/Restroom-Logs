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
import javax.swing.text.Document;

import com.teamdev.jxdocument.*;

public class MenuBar {
	public static JMenuBar createMenuBar() { //JmenuBar = Menu Bar, JMenu = new dropdown, JMunuItem = item in a dropdown
    	
    	//MENUBAR
        JMenuBar menubar = new JMenuBar();
        
        //ICONS
        ImageIcon fileExitIcon = new ImageIcon("assets/images/exit.png");
        ImageIcon dataViewIcon = new ImageIcon("assets/images/view.png");
        ImageIcon dataLogsIcon = new ImageIcon("assets/images/logs.png");
        ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
        
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
        fileExit.addActionListener((ActionEvent exitButtonEvent) -> { //When clicked, exit
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
        		JOptionPane.showMessageDialog(dataView,
					    "Can not create ViewLogPDF for viewing.", //message
					    "ViewLogPDF File Error", //title
					    JOptionPane.ERROR_MESSAGE);
        	}*/
        	//else {
        		//FIXME: Use jxdocument to render the ViewPDF
            	//try {
            		//JPanel renderedViewPDF = new JPanel();
            		
            		//RENDER
            		File pdf = new File("data/ViewLogsPDF.pdf");
            		Document document = (Document) pdf;
            		DocumentPresentation presentation= (DocumentPresentation) document;
            		JComponent renderedViewPDF = presentation.getComponent();
            		
            		
            		//added to a JDialog
            		JOptionPane.showMessageDialog(dataView,
            				renderedViewPDF, //message
    					    "Logs PDF", //title
    					    JOptionPane.INFORMATION_MESSAGE);
            	//}
            	//catch (){
            	JOptionPane.showMessageDialog(dataView,
    				"Can not open ViewLogPDF for viewing.", //message
    				"ViewLogPDF File Error", //title
    				JOptionPane.ERROR_MESSAGE);
            	//}
        	//}
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
				fileContent = new Scanner(new File("data/Logs.txt")).useDelimiter("\\Z").next();
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
    		Preferences.preferences();
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
