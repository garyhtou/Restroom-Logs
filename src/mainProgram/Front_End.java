package mainProgram;

import java.util.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;

//http://zetcode.com/tutorials/javaswingtutorial/firstprograms/

public class Front_End extends JFrame {
	
    public static void main(String[] args) {
    	//do not call method here. only use for testing
    	createWindow();
    }
	
	
	
	public Front_End() { //this is a constructor, it calls a method to give the window settings
        initUI();
    }
    private void initUI() { //window settings
        
    	//MENU BAR
    	createMenuBar(); //calls the createMenuBar method
    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	
    	ImageIcon webIcon = new ImageIcon("images\\ProgramIcon.png"); //create and icon with the image, "web.png" should be in the root of the project

        setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

    	
    	//BUTTON
    	JButton quitButton = new JButton("Quit"); //creates button named "Quit"
    	quitButton.setToolTipText("Click this button to quit the program"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
    	
        quitButton.addActionListener((ActionEvent event) -> { //event listener, if clicked, system.exit(0);
            System.exit(0);
        });

        createLayout(quitButton); //The child components (buttons) need to be placed into containers. 
        
        
        //WINDOW SETTINGS
        setTitle("Simple example");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    
    private void createMenuBar() { //JmenuBar = Menu Bar, JMenu = new dropdown, JMunuItem = item in a dropdown
    	
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
        fileExit.addActionListener((ActionEvent event) -> { //When clicked, exit
            System.exit(0);
        });
        //view entries
        JMenuItem dataView = new JMenuItem("View Entries", dataViewIcon);
        dataView.setMnemonic(KeyEvent.VK_K);
        dataView.setToolTipText("View Sign in/out");
        dataView.addActionListener((ActionEvent event) -> {
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
        dataLogs.addActionListener((ActionEvent event) -> {
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
        filePreferences.addActionListener((ActionEvent event) -> { 
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
    		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
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
	    	JTextArea preferencesLogsClear = new JTextArea();
	    	preferencesLogsClear.setOpaque(false);
	    	preferencesLogsClear.setEditable(false);
	    	//CONTENT
	    	preferencesLogsClear.setText("Clear PDF Log file");
	    	panel2.add(preferencesLogsClear);
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
        
        
        setJMenuBar(menubar); //displays menubar
    }
    
    
    private void createLayout(JComponent... arg) { //Method for creating containers for child components

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane); //default is BorderLayout, grouplayout is more powerful.
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true); //container padding, like html

        gl.setHorizontalGroup(gl.createSequentialGroup() //a row of components is called a sequential group
                .addComponent(arg[0]) 
                .addGap(120) //optional, changes gap size
        );
        		//.addComponent added the button to the group (MUST BE ADDED TO BOTH VERTICAL AND HORITZANTAL GROUP). I think 0 is the button #

        gl.setVerticalGroup(gl.createSequentialGroup() //a column of components is called a parallel group
                .addComponent(arg[0])
                .addGap(120) //optional, changes gap size
        );
        
        
        
    }

    
    public static void createWindow() {
    	EventQueue.invokeLater(() -> {
        	Front_End window = new Front_End();
            window.setVisible(true);
        });
    }



	public static void createSplashScreenInitStartUp() {
		// TODO Auto-generated method stub
		
	}



	public static void createSplashScreen() {
		// TODO Auto-generated method stub
		
	}
	
	


}
