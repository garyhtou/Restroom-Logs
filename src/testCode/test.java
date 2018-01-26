package testCode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mainProgram.config;
import oldCode.logs;
import oldCode.newPreferencesTab;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaces.RL_Fonts;
import mainProgram.BackEnd;

public class test implements ActionListener{
    String prefix = "";
	
	public test() {
		String DoNotTouchFilePath = mainProgram.config.DoNotTouchFilePath;
   

		ImageIcon Icon = new ImageIcon("assets/images/RestroomLogsLogo.png");
		
 

      
		try {
			
			int lineCounter = 0;
			File file = new File(DoNotTouchFilePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
			   //System.out.println(lineCounter + " " + line);
	
			   switch(lineCounter){  
			    case 3: //on 3rd line
			    	if(line.contains("ranBefore = ")) {
			    		String lineSub = line.substring(line.lastIndexOf(' ')+1);
			    		if(lineSub.equalsIgnoreCase("false")){
			    			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    			double screenWidth = screenSize.getWidth();
			    			double screenHeight = screenSize.getHeight();
			    			int displayWidth = (int) (screenWidth/1.3);
			    			int displayHeight = (int) (screenHeight/1.3);
			    			
			    			JPanel panel1  = new JPanel(new GridLayout(0,1));
			    			addTitle(panel1, "            Welcome");
			    	        panel1.add(Box.createHorizontalStrut(15)); // a spacer
			    			JTextArea preferencesLogsClearText = new JTextArea();
			    	    	preferencesLogsClearText.setOpaque(false);
			    	    	preferencesLogsClearText.setEditable(false);
			    	    	//CONTENT
			    	    	preferencesLogsClearText.setText("This is the inital setup for the Restroom Logs Program");
			    	    	panel1.add(preferencesLogsClearText);
			    	    	panel1.add(new JLabel("These settings can be changed later in the Preferences tab"));
			    	    	
			    	    	
			    	    	int result = JOptionPane.showOptionDialog(null, panel1 , "Restroom Logs | Initial Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Icon, null,null );
			    	    	if (result == JOptionPane.OK_OPTION)	
			    	    	{

			    			JTextField xField = new JTextField(5);
			    	        //JTextField yField = new JTextField(5);
			    	       
			    	        JPanel myPanel = new JPanel(new GridLayout(0,1));
			    	       JRadioButton mr = new JRadioButton("Mr.");
			    	        JRadioButton mrs = new JRadioButton("Mrs.");
			    	     myPanel.add(new JLabel("Please enter the teacher's last name:"));
			    	     ButtonGroup group = new ButtonGroup();
			    	     		group.add(mr);
			    	     		group.add(mrs);
			    	     	myPanel.add(mr);
			    	     	 mr.setActionCommand("Mr.");
			    	     	 mr.addActionListener(this);
			    	     	myPanel.add(mrs);
			    	     	 mrs.setActionCommand("Mrs.");
			    	     	 mrs.addActionListener(this);
			    	        myPanel.add(xField);
			    	        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    	       
			    			
			    
			    			  int result1 = JOptionPane.showConfirmDialog(null, myPanel, 
			    		                 "Restroom Logs | Initial Setup", JOptionPane.OK_CANCEL_OPTION);
			    		        if (result1 == JOptionPane.OK_OPTION) {
			    		           System.out.println("x value: " + xField.getText());
			    		           //System.out.println("y value: " + yField.getText());
			    		   		FileReader fr = new FileReader(DoNotTouchFilePath);
			    				String currentLine;
			    				String TotalLine = "";
			    			    BufferedReader br2 = new BufferedReader(fr);
			    			    
			    			    int lineCounter2 = 1;
			    			    while ((currentLine = br2.readLine()) != null) {
			    			    	if(lineCounter2 == 5){
			    			    		currentLine = "teacherName = "+prefix+xField.getText();
			    			    	}
			    			    	TotalLine += currentLine + "\n";
			    			    	lineCounter2++;
			    			    }
			    			    FileWriter fw = new FileWriter(DoNotTouchFilePath);
			    			    fw.write(TotalLine);
			    			    fw.close();
			    			    br2.close();
			    		        }
			    			
			    			
			    	    	JOptionPane.showMessageDialog(null, myPanel, "Preferences", JOptionPane.INFORMATION_MESSAGE);
			    	    	String s = (String)JOptionPane.showInputDialog(
			                        null,
			                        "Complete the sentence:\n"
			                        + "\"Green eggs and...\"",
			                        "Customized Dialog",
			                        JOptionPane.PLAIN_MESSAGE,
			                        null,
			                        null,
			                        null);
			    	    	
			    	    	
			    	    	JOptionPane.showOptionDialog(null, "This is a test of the inital setup" , "Initial Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null );
			    			
			    	    	
			    	    	
			    		}
			    		else if(lineSub.equalsIgnoreCase("true")) {
			    			mainProgram.MAIN.startUp();;
			    		}
			    		else {
			    			BackEnd.logs.update.StartUp("\n\n\n----------");
			    			BackEnd.logs.update.ERROR("ranBefore non-valid boolean ("+ line + ") at " + DoNotTouchFilePath);
			    			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			    						    		}
			    		
			    	}
			    	break;
			   }
			}   
			}
			br.close(); 
			
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Not able to read file at  "+DoNotTouchFilePath);
			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		//BackEnd.email.PDF.CreateBlankPDF();
		test t = new test();
		
	}
	/**
	 * 
	 * @param addToComp JComponent
	 * @param comp JTextArea
	 * @param title String
	 */
	public static void addTitle(JComponent addToComp, String title) {
		JTextArea textArea = new JTextArea();
		//textArea Settings
		textArea.setFont(RL_Fonts.preferencesTitle);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		textArea.setText(title);
		addToComp.add(textArea);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		prefix = e.getActionCommand();
		
	}

}
