package mainProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
/**
 * Inital Setup Program that initializes everything for the Restroom Logs Program
 * <div></div>
 *  Copyright (C) 2018  Gary Tou and Michael Schwamborn
 *  <div></div>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * <div></div>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * <div></div>
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 *
 */
public  class InitialSetup implements ActionListener{
    String prefix = "";
    String path = "";
	private static Font Title = loadFont(config.RubikFilePath); //Title Font
	public static  Font preferencesTitle = Title.deriveFont(Font.BOLD, 35);
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    JPanel myPanel,emailPanel;
    JTextField Field, Field2;
    int result1, result2,lineCounter2,result3;
    FileReader fr;
	String currentLine;
	String TotalLine ;
    BufferedReader br2;
    TimePicker timePicker;
    JCheckBox dailyEmail;
    String DoNotTouchFilePath = mainProgram.config.DoNotTouchFilePath;
	public InitialSetup()  {
		
			
   

		ImageIcon Icon = new ImageIcon("assets/images/RestroomLogsLogo.png");
JFrame frame = new JFrame();

    	
    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	ImageIcon webIcon = new ImageIcon("assets/images/RestroomLogsLogoGears.png"); //create and icon with the image, "web.png" should be in the root of the project
    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    	frame.setResizable(true);
    	frame.setUndecorated(true);
    	frame.setLocationRelativeTo(null); 
            	
    	//MENU BAR
    	//frame.setJMenuBar(MenuBar.createMenuBar());
    	
    	//CONTENT (adding content to JFrame)
    	//Window_Content.content(frame);
    	frame.setVisible(true);
		
 

      
		try {
			URI uri = new URI("https://www.rl.coding2kids.com/docs");
			BufferedImage buttonIcon = ImageIO.read(new File("assets/images/help.png"));
			//Might want to make a new variable for the link  to creating the student database (when that page is up)

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
			    			addTitle(panel1, "        Welcome");
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
			    	    		boolean pass1  =false;
			    	    		while(!pass1) {

			    			 Field = new JTextField(5);
			    	        //JTextField yField = new JTextField(5);
			    			 JButton h1  =new JButton(new ImageIcon(buttonIcon));
						        JPanel panelWhole1 = new JPanel(new BorderLayout());
						        JPanel panelHB1 = new JPanel();
						        panelHB1.setPreferredSize(new Dimension(45,45));
						        h1.setPreferredSize(new Dimension(30,30));
						        h1.addActionListener(new OpenUrlAction());
						        h1.setContentAreaFilled(false);
						        panelHB1.add(h1);
						        panelWhole1.add(panelHB1, BorderLayout.EAST);
			    	         myPanel = new JPanel(new GridLayout(0,1));
			    	       JRadioButton mr = new JRadioButton("Mr.");
			    	        JRadioButton mrs = new JRadioButton("Mrs.");
			    	     myPanel.add(new JLabel("Please enter the teacher's last name:"));
			    	     ButtonGroup group = new ButtonGroup();
			    	     		group.add(mr);
			    	     		group.add(mrs);
			    	     	myPanel.add(mr);
			    	     	 mr.setActionCommand("Mr.");
			    	     	 mr.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent e) {
										prefix = e.getActionCommand();
									}
						        });
			    	     	myPanel.add(mrs);
			    	     	 mrs.setActionCommand("Mrs.");
			    	     	 mrs.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent e) {
										prefix = e.getActionCommand();
									}
						        });
			    	        myPanel.add(Field);
			    	        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    	        panelWhole1.add(myPanel);
			    	         result1 = JOptionPane.showConfirmDialog(null, panelWhole1, 
			    		                 "Restroom Logs | Initial Setup Step 1", JOptionPane.DEFAULT_OPTION);
			    	        if(!prefix.equals("")&&!Field.getText().equals(""))
			    	        	pass1 = true;
			    	        else
			    	        	pass1=false;
			    	       
			    			  
			    		       }  if (result1 == JOptionPane.OK_OPTION) {
			    		        	//frame.dispose();
			    		    	   boolean pass2 = false;
			    		    	   JButton h1  =new JButton(new ImageIcon(buttonIcon));
							        JPanel panelWhole1 = new JPanel(new BorderLayout());
							        JPanel panelHB1 = new JPanel();
							        panelHB1.setPreferredSize(new Dimension(45,45));
							        h1.setPreferredSize(new Dimension(30,30));
							        h1.addActionListener(new OpenUrlAction());
							        h1.setContentAreaFilled(false);
							        panelHB1.add(h1);
							        panelWhole1.add(panelHB1, BorderLayout.EAST);
			    		    	   
			    		   		 fr = new FileReader(DoNotTouchFilePath);
			    				 TotalLine = "";
			    			     br2 = new BufferedReader(fr);
			    			    
			    			     lineCounter2 = 1;
			    			    while ((currentLine = br2.readLine()) != null) {
			    			    	if(lineCounter2 == 5){
			    			    	//	currentLine = "teacherTitle = "+prefix.substring(0,prefix.indexOf(".")); //Without period at the end
			    			    		currentLine = "teacherTitle = "+prefix; //With period at the end
			    			    	}
			    			    	if(lineCounter2 == 6){
			    			    		currentLine = "teacherName = "+Field.getText();
			    			    	}
			    			    	TotalLine += currentLine + "\n";
			    			    	lineCounter2++;
			    			    }
			    			    FileWriter fw = new FileWriter(DoNotTouchFilePath);
			    			    fw.write(TotalLine);
			    			    fw.close();
			    			    br2.close();
			    			    
			    		      while(!pass2) {

			    		        JPanel panel3  =new JPanel(new GridLayout(0,1));
			    		        
			    		        log = new JTextArea(5,20);
			    		        log.setMargin(new Insets(5,5,0,5));
			    		        log.setEditable(false);
			    		        
			    		        JScrollPane logScrollPane = new JScrollPane(log);
			    		        panel3.add(new JLabel("Please select your Student Access Database file:"));
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
						        openButton.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent e) {
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
								                log.append("Cancelled by user." + newline);
								            }
								            log.setCaretPosition(log.getDocument().getLength());
										}
									}
						        });
						        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Access Database", "accdb");
						        fc.setFileFilter(filter);
				
						        //Create the save button.  We use the image from the JLF
						        //Graphics Repository (but we extracted it from the jar).
						        
				
						        //For layout purposes, put the buttons in a separate panel
						        JPanel buttonPanel = new JPanel(); //use FlowLayout
						        buttonPanel.add(openButton);
						        
				
						        //Add the buttons and the log to this panel.
						        panel3.add(logScrollPane, BorderLayout.CENTER);
						        panel3.add(buttonPanel, BorderLayout.CENTER);
						        buttonPanel.add(button);
						        panel3.add(buttonPanel, BorderLayout.CENTER);
						        
						        JPanel panelT = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
						        panel3.add(new JLabel("Enter the name of your Students' Database Table:"));
						        Field.setColumns(0);
						        Field.setPreferredSize(new Dimension(200,20));
						        panelT.add(Field);
						        Field.setText(null);
						        panel3.add(panelT);
						        panelWhole1.add(panel3);
						         result2 =JOptionPane.showConfirmDialog(null, panelWhole1, "Restroom Logs | Initial Setup Step 2" , JOptionPane.DEFAULT_OPTION);
						         if(!path.equals("")&&!Field.getText().equals(""))
					    	        	pass2 = true;
					    	        else
					    	        	pass2=false;
			    		    	   } 
			    		      if(result2==JOptionPane.OK_OPTION) {
						        	FileReader fr1 = new FileReader(DoNotTouchFilePath);
				    				 String DbName = Field.getText();
				    				 TotalLine = "";
				    			    BufferedReader br3 = new BufferedReader(fr1);
				    			    
				    			     lineCounter2 = 1;
				    			    while ((currentLine = br3.readLine()) != null) {
				    			    	if(lineCounter2 == 8){
				    			    		currentLine = "studentDBPath = "+path;
				    			    	}
				    			    	if(lineCounter2 == 9){
				    			    		currentLine = "studentDBTableName = "+DbName;
				    			    	}
				    			    	TotalLine += currentLine + "\n";
				    			    	lineCounter2++;
				    			    }
				    			    if(!config.checkDatabaseForDuplicates().isEmpty()) {
				    			    	System.out.print(config.checkDatabaseForDuplicates().toString());
				    			    	JPanel warning = new JPanel();
				    			    	warning.add(new JLabel("Warning! There are duplicate Student IDs in the Student Database!"));
				    			    	warning.add(new JLabel("Please close the Initial Setup and fix the issue"));
				    			    	Object[] options = {"OK", "Exit Program"};
				    			    	int res = JOptionPane.showOptionDialog(null, warning, "Duplicate Student ID Detected", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"),options, options[1]);
					    			    
					    			    if(res == JOptionPane.NO_OPTION)
					    			    	System.exit(0);
				    			    }
				    			    FileWriter fw1 = new FileWriter(DoNotTouchFilePath);
				    			    fw1.write(TotalLine);
				    			    fw1.close();
				    			    br3.close();
				    			    boolean pass3 = false;
				    			    JButton h2  =new JButton(new ImageIcon(buttonIcon));
							        JPanel panelWhole2 = new JPanel(new BorderLayout());
							        JPanel panelHB2 = new JPanel();
							        panelHB2.setPreferredSize(new Dimension(45,45));
							        h2.setPreferredSize(new Dimension(30,30));
							        h2.addActionListener(new OpenUrlAction());
							        h2.setContentAreaFilled(false);
							        panelHB2.add(h2);
							        panelWhole2.add(panelHB2, BorderLayout.EAST);
				    			    while(!pass3){
				    			    
				    			     Field2 = new JTextField(5);
					    	        //JTextField yField = new JTextField(5);
					    	       
					    	         emailPanel = new JPanel(new GridLayout(0,1));
					    	        emailPanel.add(new JLabel("Please enter the teacher's email address:"));
					    	        emailPanel.add(Field2);
					    	        emailPanel.add(Box.createHorizontalStrut(15)); // a spacer
					    	         dailyEmail = new JCheckBox("Daily PDF Log Emails");
					    	        emailPanel.add(dailyEmail);
					    	        emailPanel.add(Box.createHorizontalStrut(15)); // a spacer
					    	        emailPanel.add(new JLabel("Select when Active Hours end:"));
					    	        TimePickerSettings timeSettings = new TimePickerSettings();
					    	        timeSettings.setAllowKeyboardEditing(false);
					    	         timePicker = new TimePicker(timeSettings);
					    	        emailPanel.add(timePicker);
					    	        
					    	        dailyEmail.addActionListener(this);
					    	        panelWhole2.add(emailPanel);
					    			   result3 = JOptionPane.showConfirmDialog(null, panelWhole2, 
					    		                 "Restroom Logs | Initial Setup Step 3", JOptionPane.DEFAULT_OPTION);
					    			   if(!Field2.getText().equals("")&&!timePicker.getText().equals(""))
					    				   pass3 = true;
					    			   else
					    				   pass3 = false;
					    			  
				    			    } if (result3 == JOptionPane.OK_OPTION) {
					    				  FileReader fr2 = new FileReader(DoNotTouchFilePath);
						    				 String tEmail = Field2.getText();
						    				 String active = timePicker.getText().substring(0, timePicker.getText().lastIndexOf("0")+1)+" "+(timePicker.getText().substring(timePicker.getText().lastIndexOf("0")+1)).toUpperCase();
						    				 TotalLine = "";
						    			    BufferedReader br31 = new BufferedReader(fr2);
						    			    
						    			     lineCounter2 = 1;
						    			    while ((currentLine = br31.readLine()) != null) {
						    			    	if(lineCounter2 == 3){
						    			    		currentLine = "ranBefore = true";
						    			    	}
						    			    	TotalLine += currentLine + "\n";
						    			    	lineCounter2++;
						    			    }
						    			    FileWriter fw2 = new FileWriter(DoNotTouchFilePath);
						    			    fw2.write(TotalLine);
						    			    fw2.close();
						    			    br31.close();
						    			    FileReader fr = new FileReader(DoNotTouchFilePath);
						    			    TotalLine = "";
						    			    BufferedReader br4 = new BufferedReader(fr);
						    			    
						    			    lineCounter2 = 1;
						    			    while ((currentLine = br4.readLine()) != null) {
						    			    	if(lineCounter2 == 7){
						    			    		currentLine = "teacherEmail = "+tEmail;
						    			    	}
						    			    	if(lineCounter2 == 11){
						    			    		currentLine = "activeHours = "+active;
						    			    	}
						    			    	TotalLine += currentLine + "\n";
						    			    	lineCounter2++;
						    			    }
						    			    FileWriter fw3 = new FileWriter(DoNotTouchFilePath);
						    			    fw3.write(TotalLine);
						    			    fw3.close();
						    			    br4.close();
					    				  
					    				  
					    			    JPanel finalPanel = new JPanel(new GridLayout(0,1));
				    		        finalPanel.add(new JLabel("Setup Complete"));
				    		        finalPanel.add(new JLabel("The Restroom Logs Program will now open"));
				    			    frame.dispose();
				    			    JOptionPane.showMessageDialog(null, finalPanel, "Restroom Logs | Initial Setup Complete", JOptionPane.INFORMATION_MESSAGE);
				    			    Process proc = Runtime.getRuntime().exec("java -jar RestroomLogsProgram.jar");
				    			    
				    	 }
				    }
			    		        
			  } 
			    	            
			    	            

			    	    	
			    		}
			    		
			    		
			    	}else if(lineSub.equalsIgnoreCase("true")) {
		    			    Process proc = Runtime.getRuntime().exec("java -jar RestroomLogsProgram.jar");
			    		}
			    		else {
			    			BackEnd.logs.update.StartUp("\n\n\n----------");
			    			BackEnd.logs.update.ERROR("ranBefore non-valid boolean ("+ line + ") at " + DoNotTouchFilePath);
			    			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			    						    		}
			    	break;
			   }
			}   
			}
			br.close(); 
			frame.dispose();
			
		}
		catch (IOException  e) {
			BackEnd.logs.update.ERROR("Not able to read file at  "+DoNotTouchFilePath);
			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			e.printStackTrace();
		}
		catch(URISyntaxException e) {
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		//BackEnd.email.PDF.CreateBlankPDF();

		InitialSetup t = new InitialSetup();
		
	}
	/**
	 * 
	 * @param addToComp JComponent
	 * @param comp JTextArea
	 * @param title String
	 */
	public  void addTitle(JComponent addToComp, String title) {
		JTextArea textArea = new JTextArea();
		//textArea Settings
		textArea.setFont(preferencesTitle);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		textArea.setText(title);
		addToComp.add(textArea);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(dailyEmail.isSelected()) {
			 FileReader fr2;
			try {
				fr2 = new FileReader(DoNotTouchFilePath);
			 TotalLine = "";
		    BufferedReader br31 = new BufferedReader(fr2);
		     lineCounter2 = 1;
		    while ((currentLine = br31.readLine()) != null) {
		    	if(lineCounter2 == 4){
		    		currentLine = "dailyEmails = true";
		    	}
		    	TotalLine += currentLine + "\n";
		    	lineCounter2++;
		    }
		    FileWriter fw2 = new FileWriter(DoNotTouchFilePath);
		    fw2.write(TotalLine);
		    fw2.close();
		    br31.close();
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(!dailyEmail.isSelected()) {
			 FileReader fr2;
			try {
				fr2 = new FileReader(DoNotTouchFilePath);
			 TotalLine = "";
		    BufferedReader br31 = new BufferedReader(fr2);
		     lineCounter2 = 1;
		    while ((currentLine = br31.readLine()) != null) {
		    	if(lineCounter2 == 4){
		    		currentLine = "dailyEmails = false";
		    	}
		    	TotalLine += currentLine + "\n";
		    	lineCounter2++;
		    }
		    FileWriter fw2 = new FileWriter(DoNotTouchFilePath);
		    fw2.write(TotalLine);
		    fw2.close();
		    br31.close();
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(uri);
          } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
	}
	public static Font loadFont(String path) {
		Font ttfBase = null;
  		Font ttfReal = null;
		try {
			InputStream myStream = new BufferedInputStream(new FileInputStream(path));
			ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
			ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);
			return ttfReal;
		} catch (Exception ex) {
	        ex.printStackTrace();
	        BackEnd.logs.update.ERROR("Font could not be loaded from  " + path);
	        System.err.println("Font could not be loaded from  " + path);
	        return null;
	    }
    }
	class OpenUrlAction implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
    		URI uri;
			try {
				uri = new URI("https://www.rl.coding2kids.com/docs");
				open(uri);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}

          
        }
      }


	
}
