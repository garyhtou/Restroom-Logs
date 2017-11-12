package mainProgram;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Splash_Screen extends JFrame {
	   public static void main(String[] args) {
	    	//do not call method here. only use for testing
	    	createWindow();
	    }
	   public static void createWindow() {
	    	EventQueue.invokeLater(() -> {
	        	Splash_Screen window = new Splash_Screen();
	            window.setVisible(true);
	        });
	    }
		public Splash_Screen() { //this is a constructor, it calls a method to give the window settings
	        initUI();
	    }
	    private void initUI() { //window settings
	    	JPanel panel = new JPanel();
	    	
	    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
	    	
	    	ImageIcon webIcon = new ImageIcon("images\\ProgramIcon.png"); //create and icon with the image, "web.png" should be in the root of the project

	        setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

	    	
	    	//BUTTON
	    	JButton quitButton = new JButton("Quit"); //creates button named "Quit"
	    	quitButton.setToolTipText("Click this button to quit the program"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
	    	
	        quitButton.addActionListener((ActionEvent quitButtonEvent) -> { //event listener, if clicked, system.exit(0);
	            System.exit(0);
	        });

	        panel.add(quitButton); //The child components (buttons) need to be placed into containers. 
	        
	        
	        //WINDOW SETTINGS
	        setTitle("Restroom Logs Splash");
	        //setExtendedState(JFrame.MAXIMIZED_BOTH);
	        //setUndecorated(true);
	        setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
	        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    }

}