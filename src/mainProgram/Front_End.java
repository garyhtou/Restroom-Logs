package mainProgram;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread;
import java.text.SimpleDateFormat;

//http://zetcode.com/tutorials/javaswingtutorial/firstprograms/

public class Front_End extends JFrame {
	
    public static void main(String[] args) {
    	//do not call method here. only use for testing
    	createWindow();
    }
    public static void createWindow() {
    	EventQueue.invokeLater(() -> {
        	initUI();
        });
    }
    
    
    
	public Front_End() { //this is a constructor, it calls a method to give the window settings
        initUI();
    }
    private static void initUI() { //window settings
    	JFrame frame = new JFrame();
    	
    	//MENU BAR
    	frame.setJMenuBar(MenuBar.createMenuBar());

    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	
    	ImageIcon webIcon = new ImageIcon("assets\\images\\RestroomLogsLOGO.png"); //create and icon with the image, "web.png" should be in the root of the project

    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image

    	/*//BUTTON
    	JButton quitButton = new JButton("Quit"); //creates button named "Quit"
    	quitButton.setToolTipText("Click this button to quit the program"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
    	
        quitButton.addActionListener((ActionEvent quitButtonEvent) -> { //event listener, if clicked, system.exit(0);
            System.exit(0);
        });

        createLayout(quitButton); //The child components (buttons) need to be placed into containers. 
*/
        
    	
    	
	    	
    	Container pane = frame.getContentPane();

        JButton button;
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    //FIXME: scaling picture, keep ratio
	    JLabel logo = new JLabel();
	    ImageIcon RestroomLogsLogo = new ImageIcon("assets/RestroomLogsSplashscreen.png");
	    logo.setIcon(RestroomLogsLogo);
	    //logo.setSize(500, 500);
	    /*Dimension prefSize = logo.getPreferredSize();
	    System.out.println("prefSize: " + prefSize);*/
	    BufferedImage img = null;
	    /*int width;
	    int height;*/
	    try {
	        img = ImageIO.read(new File("assets/RestroomLogsSplashscreen.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    /*width = 400;
        height = 250;
	    int ratio = height/width;
	    int displayWidth = prefSize.width;
	    int displayHeight = displayWidth * ratio;
	    logo.setSize(displayWidth, displayHeight);*/
	    Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(),
	            Image.SCALE_SMOOTH);
	    ImageIcon RestroomLogsLogoWide = new ImageIcon(dimg);
	    logo.setIcon(RestroomLogsLogoWide);
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    pane.add(logo, c);
	 
	    String teacherName = "Mr. Sabo";
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
	    String timeStamp = "Current Date: " + dateFormat.format(date);
	    JLabel stats = new JLabel("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		    ActionListener actionListener = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	Date date = new Date();
		            SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
		            String timeStamp = "Current Date: " + dateFormat.format(date);
		            stats.setText("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		            //System.out.println(timeStamp);
		        }
		    };
		    Timer timer = new Timer(1000, actionListener);
		    timer.start();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 0;
	    pane.add(stats, c);
	 
	    button = new JButton("Button 3");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridheight = GridBagConstraints.REMAINDER;
	    c.anchor = GridBagConstraints.LINE_END;
	    pane.add(button, c);
	 
	    button = new JButton("Long-Named Button 4");
	    c.fill = GridBagConstraints.BOTH;
	    //c.ipady = 40;      //make this component tall
	    c.weightx = 0.1;
	    c.weighty = 1;
	    c.gridwidth = 2;
	    c.gridheight = 3;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.anchor = GridBagConstraints.LINE_START;
	    pane.add(button, c);
	 
	    button = new JButton("5");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 0;       //reset to default
	    c.weighty = 0.0;
	    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    c.gridx = 0;       //aligned with button 2
	    c.gridwidth = 2;   //2 columns wide
	    c.gridy = 4;       //third row
	    pane.add(button, c);
	    
	    	
    	
    	
    	
    	
        
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);
    	frame.setUndecorated(true);
    	frame.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setAlwaysOnTop(true); //DO NOT USE THIS, THIS WILL PREVENT DIALOG FROM TOPPING THIS
    	frame.setVisible(true);
    }
}