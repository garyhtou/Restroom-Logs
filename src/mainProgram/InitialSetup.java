package mainProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import oldCode.MenuBar;
import oldCode.Window_Content;
import oldCode.logs;
import oldCode.newPreferencesTab;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interfaces.RL_Fonts;

public class InitialSetup implements ActionListener{
    String prefix = "";
    String path = "";
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
	
	public InitialSetup() throws URISyntaxException {
		String DoNotTouchFilePath = mainProgram.config.DoNotTouchFilePath;
		URI uri = new URI("https://www.rl.coding2kids.com/docs");

   

		ImageIcon Icon = new ImageIcon("assets/images/RestroomLogsLogo.png");
JFrame frame = new JFrame();

    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	ImageIcon webIcon = new ImageIcon("assets/images/RestroomLogsLogoGears.png"); //create and icon with the image, "web.png" should be in the root of the project
    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
    	frame.setResizable(true);
    	frame.setUndecorated(true);
    	frame.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setAlwaysOnTop(true); //DO NOT USE THIS, THIS WILL PREVENT DIALOG FROM TOPPING THIS
    	
    	//MENU BAR
    	//frame.setJMenuBar(MenuBar.createMenuBar());
    	
    	//CONTENT (adding content to JFrame)
    	//Window_Content.content(frame);
    	frame.setVisible(true);
		
 

      
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
			    			
			    			JPanel panel1  = new JPanel(new GridLayout(0,1));
			    			addTitle(panel1, "            Welcome");
			    	        panel1.add(Box.createHorizontalStrut(5)); // a spacer
			    			JTextArea preferencesLogsClearText = new JTextArea();
			    	    	preferencesLogsClearText.setOpaque(false);
			    	    	preferencesLogsClearText.setEditable(false);
			    	    	//CONTENT
			    	    	preferencesLogsClearText.setText("This is the inital setup for the Restroom Logs Program");
			    	    	panel1.add(preferencesLogsClearText);
			    	    	panel1.add(new JLabel("These settings can be changed later in the Preferences tab"));	    	    	
			    	    	
			    	        
			    	        JButton button = new JButton();
			    	        button.setText("<HTML>Click <FONT color=\"#000099\"><U>here</U></FONT>"
			    	            + " for more information.</HTML>");
			    	        button.setHorizontalAlignment(SwingConstants.LEFT);
			    	        button.setBorderPainted(false);
			    	        button.setOpaque(false);
			    	        button.setBackground(Color.WHITE);
			    	        button.setToolTipText(uri.toString());
			    	        button.addActionListener(new OpenUrlAction());
			    	        panel1.add(button);
			    	        frame.setVisible(true);
			    	      

			    	      
			    	    	
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
			    		                 "Restroom Logs | Initial Setup Step 1", JOptionPane.OK_CANCEL_OPTION);
			    		        if (result1 == JOptionPane.OK_OPTION) {
			    		        	//frame.dispose();
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
			    		       
			    		        JPanel panel3  =new JPanel(new GridLayout(0,1));
			    		        
			    		        log = new JTextArea(5,20);
			    		        log.setMargin(new Insets(5,5,0,5));
			    		        log.setEditable(false);
			    		        
			    		        JScrollPane logScrollPane = new JScrollPane(log);
			    		        
			    		        panel3.add(new JLabel("Please select your students' Access Database file:"));
			    		        button.setText("<HTML>Click <FONT color=\"#000099\"><U>here</U></FONT>"
					    	            + " for info on how to make an Access Database.</HTML>");
					    	        //button.setHorizontalAlignment(SwingConstants.LEFT);
			    		        //button.setMargin(new Insets(0,0,0,0));
					    	        button.setBorderPainted(false);
					    	        button.setOpaque(false);
					    	        button.setPreferredSize(new Dimension(400, 40));
					    	        button.setBackground(Color.WHITE);
					    	        button.setToolTipText(uri.toString());
					    	        button.addActionListener(new OpenUrlAction());
					    	        

			    		        //Create a file chooser
			    		        fc = new JFileChooser();
			    		        openButton = new JButton("Open a File...",
		                                 null);
						        openButton.addActionListener(this);
				
						        //Create the save button.  We use the image from the JLF
						        //Graphics Repository (but we extracted it from the jar).
						        
				
						        //For layout purposes, put the buttons in a separate panel
						        JPanel buttonPanel = new JPanel(); //use FlowLayout
						        buttonPanel.add(openButton);
						        
				
						        //Add the buttons and the log to this panel.
						        panel3.add(logScrollPane, BorderLayout.CENTER);
						        panel3.add(buttonPanel, BorderLayout.CENTER);
						        JButton jj = new JButton();
						        buttonPanel.add(button);
						        panel3.add(buttonPanel, BorderLayout.CENTER);
						        int result2 =JOptionPane.showConfirmDialog(null, panel3, "Restroom Logs | Initial Setup Step 2" , JOptionPane.OK_CANCEL_OPTION);
						        
						        if(result2==JOptionPane.OK_OPTION) {
						        	FileReader fr1 = new FileReader(DoNotTouchFilePath);
				    				 
				    				 TotalLine = "";
				    			    BufferedReader br3 = new BufferedReader(fr1);
				    			    
				    			     lineCounter2 = 1;
				    			    while ((currentLine = br3.readLine()) != null) {
				    			    	if(lineCounter2 == 6){
				    			    		currentLine = "studentDBPath = "+path;
				    			    	}
				    			    	TotalLine += currentLine + "\n";
				    			    	lineCounter2++;
				    			    }
				    			    FileWriter fw1 = new FileWriter(DoNotTouchFilePath);
				    			    fw1.write(TotalLine);
				    			    fw1.close();
				    			    br3.close();
				    			    
				    									        }
			    		        }
			    		        JPanel finalPanel = new JPanel(new GridLayout(0,1));
			    		        finalPanel.add(new JLabel("Setup Complete"));
			    		        finalPanel.add(new JLabel("Restroom Logs Program will now open"));
			    			
			    			
			    	    	JOptionPane.showMessageDialog(null, finalPanel, "Restroom Logs | Initial Setup Complete", JOptionPane.INFORMATION_MESSAGE);
		    			    Process proc = Runtime.getRuntime().exec("java -splash:assets/logos/RestroomLogsSplashscreen.png -jar RestroomLogsProgram.jar");

			    	    	//String s = (String)JOptionPane.showInputDialog( null, "Complete the sentence:\n"+ "\"Green eggs and...\"","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null, null);
			    	    	//JOptionPane.showOptionDialog(null, "This is a test of the inital setup" , "Initial Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null );
			    			
			    	    	
			    	    	
			    		}
			    		else if(lineSub.equalsIgnoreCase("true")) {
			    			//FIXME: When called it does nothing and ends program
		    			    Process proc = Runtime.getRuntime().exec("java -splash:assets/logos/RestroomLogsSplashscreen.png -jar RestroomLogsProgram.jar");
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
			frame.dispose();
			
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Not able to read file at  "+DoNotTouchFilePath);
			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) throws URISyntaxException {
		//BackEnd.email.PDF.CreateBlankPDF();

		InitialSetup t = new InitialSetup();
		
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
		if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Selected: " + file.getName() + "." + newline);
                //log.append("Path: " + file.getPath() + newline);
                log.append("Path: " + file.getAbsolutePath() + newline);
                path = file.getAbsolutePath();
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		
		
	}
	private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(uri);
          } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
	}
        
	class OpenUrlAction implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
    		URI uri;
			try {
				uri = new URI("https://www.rl.coding2kids.com/docs");
				open(uri);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

          
        }
      }


	
}
