package mainProgram;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

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
	    	//Look and Feel
	    	try {
		        // Set cross-platform Java L&F (also called "Metal")
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			    } 
			    catch (UnsupportedLookAndFeelException e) {
			    	System.out.println("Look and Feel Error");
			    }
			    catch (ClassNotFoundException e) {
			    	System.out.println("Look and Feel Error");
			    }
			    catch (InstantiationException e) {
			    	System.out.println("Look and Feel Error");
			    }
			    catch (IllegalAccessException e) {
			    	System.out.println("Look and Feel Error");
			    }
	    	
	    	//Frame and Panel
	    	//JFrame window = new JFrame("Restroom Logs Splash");
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BorderLayout());
	    	panel.setVisible(true);
	    	add(panel);
	    	
	    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
	    	ImageIcon webIcon = new ImageIcon("images\\ProgramIcon.png"); //create and icon with the image, "web.png" should be in the root of the project
	        setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

	        
	        //Title
	        JTextArea Title = new JTextArea("Restroom Logs");
	        Title.setLocation(200, 150);
	        Title.setVisible(true);
	        panel.add(Title, BorderLayout.CENTER);

	        System.out.println(Title.getFont());
	        
	        //WINDOW SETTINGS
	        setTitle("Restroom Logs Splash");
	        setSize(400, 300);
	        setResizable(false);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        //window.pack();
	        //window.setUndecorated(true);
	        setVisible(true);
	        
	        //wait 3 secs and createWindow
	        try {
				Thread.sleep(3000);
				//Front_End.createWindow();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        finally {
	        	//window.setVisible(false);
	        	//window.dispose();
	        	
	        }
	    }
}