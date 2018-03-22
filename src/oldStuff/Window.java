package oldStuff;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread;
import java.text.SimpleDateFormat;
//
//import interfaces.RL_Colors;

//http://zetcode.com/tutorials/javaswingtutorial/firstprograms/

public class Window extends JFrame  {
	
    public static void main(String[] args) {
    	//do not call method here. only use for testing
    	createWindow();
    }
    public static void createWindow() {
    	EventQueue.invokeLater(() -> {
        	initUI();
        });
    }
    
    
    
	private Window() { //this is a constructor, it calls a method to give the window settings
        initUI();
    }
    private static void initUI() { //window settings
    	JFrame frame = new JFrame();
    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project
    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);
    	frame.setUndecorated(true);
    	frame.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setAlwaysOnTop(true); //DO NOT USE THIS, THIS WILL PREVENT DIALOG FROM TOPPING THIS
    	
    	//MENU BAR
    	frame.setJMenuBar(MenuBar.createMenuBar());
    	
    	//CONTENT (adding content to JFrame)
    	Window_Content.content(frame);
    	
    	frame.setVisible(true);
    	frame.setAlwaysOnTop(true); //jumps to top
    	frame.setAlwaysOnTop(false); //allows pop ups to show above frame
    }
}