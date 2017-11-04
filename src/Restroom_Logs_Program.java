
import java.util.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;

//http://zetcode.com/tutorials/javaswingtutorial/firstprograms/

public class Restroom_Logs_Program extends JFrame {
	
	
	public Restroom_Logs_Program() { //this is a constructor, it calls a method to give the window settings

        initUI();
    }

    private void initUI() { //window settings
        
    	//MENU BAR
    	createMenuBar(); //calls the createMenuBar method
    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	
    	ImageIcon webIcon = new ImageIcon("images\\UniversalRestroomSign.png"); //create and icon with the image, "web.png" should be in the root of the project

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
        ImageIcon exitIcon = new ImageIcon("images\\exit.png");
        
        //MENU
        JMenu file = new JMenu("File"); //New dropdown
        file.setMnemonic(KeyEvent.VK_F); //Short cut for "File" drop down
        
        //MUNU ITEM
        //exit
        JMenuItem exitMenuItem = new JMenuItem("Exit", exitIcon); //creates dropdown item "Exit" and it's icon to hte File dropdown
        exitMenuItem.setMnemonic(KeyEvent.VK_E); //short cut for exit
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener((ActionEvent event) -> { //When clicked, exit
            System.exit(0);
        });
        

        //DISPLAY
        file.add(exitMenuItem); //adds the exit menuitem to the "file" menu

        menubar.add(file); //adds the file menu to the menubar

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

    public static void main(String[] args) { //Creates the window and makes it visible

        /*
        https://stackoverflow.com/questions/22534356/java-awt-eventqueue-invokelater-explained
        Most likely no need for EventQueue.invokeLater()
        	EventQueue.invokeLater() Allows the gui and calculations to be performed on different tabs.
        	This lets the GUI to be updated why it's calculating
        	
        	"We create an instance of our code example and make it visible on the screen.
        	The invokeLater() method places the application on the Swing Event Queue.
        	It is used to ensure that all UI updates are concurrency-safe.
        	In other words, it is to prevent GUI from hanging in certain situations."
        	
        
        EventQueue.invokeLater(() -> {
        	Restroom_Logs_Program ex = new Restroom_Logs_Program();
            ex.setVisible(true);
        });
        */
    	
    	
    	EventQueue.invokeLater(() -> {
        	Restroom_Logs_Program window = new Restroom_Logs_Program();
            window.setVisible(true);
        });
        
    }

}