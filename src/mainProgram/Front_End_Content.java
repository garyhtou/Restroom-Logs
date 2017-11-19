package mainProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
 
public class Front_End_Content {
    public static void content() {
    	JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        Container pane = frame.getContentPane();
        Color LAVENDER_GRAY = new Color(0xC5C6C7);
        pane.setBackground(LAVENDER_GRAY);
        //pane.setForeground(LAVENDER_GRAY);

        JButton button;
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    
	    button = new JButton("Button 1");
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    pane.add(button, c);
	 
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
	    
	    
	    
	    
	    
	    
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	    
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                content();
                
            }
        });
    }
}