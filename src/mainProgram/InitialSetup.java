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
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import oldCode.MenuBar;
import oldCode.Window_Content;
import oldCode.logs;
import oldCode.newPreferencesTab;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import interfaces.RL_Fonts;

public class InitialSetup implements ActionListener{
    String prefix = "";
    String path = "";
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
	
	public InitialSetup()  {
		String DoNotTouchFilePath = mainProgram.config.DoNotTouchFilePath;

   

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
			URI uri = new URI("https://www.rl.coding2kids.com/docs");
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
			    	    		

			    			JTextField Field = new JTextField(5);
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
			    	        myPanel.add(Field);
			    	        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    	       
			    			
			    
			    			  int result1 = JOptionPane.showConfirmDialog(null, myPanel, 
			    		                 "Restroom Logs | Initial Setup Step 1", JOptionPane.DEFAULT_OPTION);
			    		        if (result1 == JOptionPane.OK_OPTION) {
			    		        	//frame.dispose();
			    		           System.out.println("value: " + Field.getText());
			    		   		FileReader fr = new FileReader(DoNotTouchFilePath);
			    				String currentLine;
			    				String TotalLine = "";
			    			    BufferedReader br2 = new BufferedReader(fr);
			    			    
			    			    int lineCounter2 = 1;
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
						        buttonPanel.add(button);
						        panel3.add(buttonPanel, BorderLayout.CENTER);
						        //TODO: add input for table name
						        JPanel panelT = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
						        panel3.add(new JLabel("Enter the name of your Students' Database Table:"));
						        Field.setColumns(0);
						        Field.setPreferredSize(new Dimension(200,20));
						        panelT.add(Field);
						        Field.setText(null);
						        panel3.add(panelT);
						        int result2 =JOptionPane.showConfirmDialog(null, panel3, "Restroom Logs | Initial Setup Step 2" , JOptionPane.DEFAULT_OPTION);
						        
						        if(result2==JOptionPane.OK_OPTION) {
						        	FileReader fr1 = new FileReader(DoNotTouchFilePath);
				    				 String DbName = Field.getText();
				    				 TotalLine = "";
				    			    BufferedReader br3 = new BufferedReader(fr1);
				    			    
				    			     lineCounter2 = 1;
				    			    while ((currentLine = br3.readLine()) != null) {
				    			    	if(lineCounter2 == 7){
				    			    		currentLine = "studentDBPath = "+path;
				    			    	}
				    			    	if(lineCounter2 == 8){
				    			    		currentLine = "studentDBTableName = "+DbName;
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
			    			    frame.dispose();
			    			    JOptionPane.showMessageDialog(null, finalPanel, "Restroom Logs | Initial Setup Complete", JOptionPane.INFORMATION_MESSAGE);
			    	    	
			    	    	//  OTA UPDATES
			    	        final JDialog dialog = new JDialog(null, "Downloading Update", ModalityType.MODELESS);
			    	        JProgressBar progressBar = new JProgressBar();
			    	        progressBar.setIndeterminate(true);
			    	        progressBar.setMinimum(0);
			    	        dialog.setIconImage(webIcon.getImage());
			    	        dialog.setLocationRelativeTo(null);
							dialog.setResizable(false);
							dialog.setPreferredSize(new Dimension(200, 65));
			    	        
			    	        JPanel panel = new JPanel(new BorderLayout());
			    	        panel.add(progressBar, BorderLayout.CENTER);
			    	        panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
			    	        
			    	        dialog.add(panel);
			    	        dialog.pack();
			    	        dialog.setVisible(true);
			    	        
			    	    	String url = "https://rl.coding2kids.com/admin/versions/RestroomLogsProgram.jar";
			    	    	String urlT = "https://rl.coding2kids.com/admin/info.txt";
			    	    	String filePath = "RestroomLogsProgram.txt"; //FIXME: make this realative instead of abs.
			    	    	String USER_AGENT = "Chrome/63.0.3239.132 ";

			    	    	HttpClient client = HttpClientBuilder.create().build();
			    	    	HttpGet request = new HttpGet(urlT);

			    	    	// add request header
			    	    	request.addHeader("User-Agent", USER_AGENT);
			    	    	HttpResponse response = client.execute(request);
			    	        HttpEntity entity = response.getEntity();
			    	            
			    	            String inputLine ;
			    	            boolean updated = false;
			    	            
			    	            BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			    	            String webLine = null,versLine = null;
			    	                  while ((inputLine = br1.readLine()) != null) {
			    	                         System.out.println(inputLine);//debugging
			    	                         webLine=inputLine;
			    	                  }
			    	                  
			    	                  br1.close();
			    	                  BufferedReader br2 = new BufferedReader(new FileReader(file));
			    	      			 line = null;
			    	      			 lineCounter =0;
			    	      			while ((line = br2.readLine()) != null) {  
			    	      			   // process the line.  
			    	      			   lineCounter++;
			    	      	
			    	      			   switch(lineCounter){  
			    	      			    case 9: //on 9th line
			    	                         System.out.println(line); //debugging
			    	      			    	versLine = line;
			    	      			   }
			    	      			}
			    	      			br2.close();
			    	      			if(versLine.equals(webLine)) {
			    	      				System.out.println("They're the same");
			    	      				updated = true;
			    	      			}
			    	      			if(!versLine.equals(webLine)) {
			    	      				updated = false;
			    	      				System.out.println("They're NOT the same");
			    	      			}
			    	            if (entity != null && !updated) {
			    	            	HttpClient client1 = HttpClientBuilder.create().build();
					    	    	HttpGet request1 = new HttpGet(urlT);

					    	    	// add request header
					    	    	request1.addHeader("User-Agent", USER_AGENT);
					    	    	HttpResponse response1 = client1.execute(request);
					    	        HttpEntity entity1 = response1.getEntity();
					    	        
			    	                long len = entity1.getContentLength();
			    	                progressBar.setMaximum((int)len);
			    	                InputStream is = entity1.getContent();
			    	                FileOutputStream fos = new FileOutputStream(new File(filePath));
			    	                int inByte;
			    	               
			    	                while((inByte = is.read()) != -1) {
			    	                	 fos.write(inByte);
			    	                    
			    	                }
			    	                dialog.dispose();
			    	                fos.close();
			    	                is.close();
			    	                
			    	                //Update DoNotTouch.txt with new release number
			    	                FileReader fr = new FileReader(DoNotTouchFilePath);
				    				 String TotalLine = "";
					    			 String currentLine;
				    			    BufferedReader br3 = new BufferedReader(fr);
				    			      lineCounter = 1;
				    			    while ((currentLine = br3.readLine()) != null) {
				    			    	if(lineCounter== 9){
				    			    		currentLine = webLine;
				    			    	}
				    			    	TotalLine += currentLine + "\n";
				    			    	lineCounter++;
				    			    }
				    			    FileWriter fw1 = new FileWriter(DoNotTouchFilePath);
				    			    fw1.write(TotalLine);
				    			    fw1.close();
				    			    br3.close();
			    	            } 
			    	            else {
			    	            	dialog.dispose();
			    	            }
			    	            
		    			   // Process proc = Runtime.getRuntime().exec("java -splash:assets/logos/RestroomLogsSplashscreen.png -jar RestroomLogsProgram.jar");

			    	    	//String s = (String)JOptionPane.showInputDialog( null, "Complete the sentence:\n"+ "\"Green eggs and...\"","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null, null);
			    	    	//JOptionPane.showOptionDialog(null, "This is a test of the inital setup" , "Initial Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null );
			    			
			    	    	
			    	    	
			    		}
			    		else if(lineSub.equalsIgnoreCase("true")) {
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
				e1.printStackTrace();
			}

          
        }
      }


	
}
