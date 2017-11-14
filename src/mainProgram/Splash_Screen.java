package mainProgram;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Splash_Screen extends JFrame {
	   public static void main(String[] args) {
	    	//do not call method here. only use for testing
	    	createWindow();
	    }
	   public static void init() {
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
	    	JFrame window = new JFrame();
	    	
	    	JPanel panel = new JPanel();
	    	
	    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
	    	
	    	ImageIcon webIcon = new ImageIcon("images\\ProgramIcon.png"); //create and icon with the image, "web.png" should be in the root of the project

	        setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

	    	
	        JLabel label = new JLabel("Hello World");
	        window.getContentPane().add(label);
	        
	        //Title
	        JTextArea Title = new JTextArea("Restroom Logs");
	        panel.add(Title);
	        
	        window.getContentPane().add(panel);
	        
	        
	        //WINDOW SETTINGS
	        window.setTitle("Restroom Logs Splash");
	        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        //window.setSize(400, 300);
	        window.pack();
	        //window.setUndecorated(true);
	        window.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
	        //window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    }

}