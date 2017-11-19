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
    
    public static void createWindow() {
    	EventQueue.invokeLater(() -> {
        	Front_End window = new Front_End();
            window.setVisible(true);
        });
    }
    
	public Front_End() { //this is a constructor, it calls a method to give the window settings
        initUI();
    }
    private void initUI() { //window settings
        
    	//MENU BAR
    	setJMenuBar(MenuBar.createMenuBar());
    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	
    	ImageIcon webIcon = new ImageIcon("assets\\images\\RestroomLogsLOGO.png"); //create and icon with the image, "web.png" should be in the root of the project

        setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

    	
    	//BUTTON
    	JButton quitButton = new JButton("Quit"); //creates button named "Quit"
    	quitButton.setToolTipText("Click this button to quit the program"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
    	
        quitButton.addActionListener((ActionEvent quitButtonEvent) -> { //event listener, if clicked, system.exit(0);
            System.exit(0);
        });

        createLayout(quitButton); //The child components (buttons) need to be placed into containers. 
        
        
        //WINDOW SETTINGS
        setTitle("Restroom Logs");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
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

	public static void createSplashScreen(boolean ranBefore) {
		SplashScreenManager.init(ranBefore);
	}
	
	


}
