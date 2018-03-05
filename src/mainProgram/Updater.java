package mainProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
/**
 * Updater Program to update the Restroom Logs Program
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
public class Updater {
	private static Font Title = loadFont(config.RubikFilePath); //Title Font
	public static  Font preferencesTitle = Title.deriveFont(Font.BOLD, 35);

	public static void main(String[] args) {
			Updater u = new Updater();
	}
	
	public  Updater() {
		final ImageIcon Icon = new ImageIcon("assets/images/RestroomLogsLogoArrows75px.png");
		JFrame frame = new JFrame();
		int resultU = 0;
		File file = new File(config.DoNotTouchFilePath);
    	final String url = "https://rl.coding2kids.com/admin/versions/RestroomlogsProgram.jar";
    	final String urlT = "https://rl.coding2kids.com/admin/info.txt";
		final String  filePath = "RestroomLogsProgram.jar";
    	final String USER_AGENT = "Chrome/63.0.3239.132 ";

		       try {	
		    	ImageIcon webIcon = new ImageIcon("assets/images/RestroomLogsLogoArrows.png"); //create and icon with the image, "web.png" should be in the root of the project
		    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
		    	
		        //WINDOW SETTINGS
		    	frame.setTitle("Restroom Logs Updater");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setLayout(null);
		    	frame.setResizable(true);
		    	frame.setUndecorated(true);
		    	frame.setLocationRelativeTo(null); 
		    	frame.setVisible(true);
		    	
		    	JPanel panel1  = new JPanel(new GridLayout(0,1));
    			addTitle(panel1, "      Updater");
    	        panel1.add(Box.createHorizontalStrut(5)); // a spacer
    			JTextArea title = new JTextArea();
    			title.setOpaque(false);
    			title.setEditable(false);
    	    	//CONTENT
    			title.setText("This is the Updater for the Restroom Logs Program\n\nThis is used to check for and install an Update.");
    	    	panel1.add(title);
    	    	JTextArea caution = new JTextArea();
    	    	caution.setOpaque(false);
    	    	caution.setEditable(false);
    	    	caution.setText("\nPlease close out of the Restroom Logs\nProgram during this proccess.");
    	    	panel1.add(caution);
    	    	
    	    	int result = JOptionPane.showOptionDialog(null, panel1 , "Restroom Logs | Updater", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, Icon, null,null );
    	    	if (result == JOptionPane.OK_OPTION)	
    	    	{
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
		    	        panel.add(new JLabel("Checking Version Number...."), BorderLayout.PAGE_START);
		    	        dialog.add(panel);
		    	        dialog.pack();
		    	       dialog.setVisible(true);
		    	        

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
			    	                         webLine=inputLine;
			    	                  }
			    	                  
										br1.close();
									
			    	                  BufferedReader br21 = new BufferedReader(new FileReader(file));
			    	                  String line = null;
			    	      			  int lineCounter =0;
			    	      			while ((line = br21.readLine()) != null) {  
			    	      			   // process the line.  
			    	      			   lineCounter++;
			    	      	
			    	      			   switch(lineCounter){  
			    	      			    case 10: 
			    	      			    	versLine = line;
			    	      			   }
			    	      			}
			    	      			br21.close();
			    	      			if(versLine.equals(webLine)) {
			    	      				updated = true;
			    	      			}
			    	      			if(!versLine.equals(webLine)) {
			    	      				updated = false;
			    	      			}
			 		    	       dialog.setVisible(false);
			 		    	       dialog.dispose();
			 		    	       
			 		    	       if(updated) {
			 		    	    	   JPanel updatedPanel  = new JPanel(new GridLayout(0,1));
			 		    	    	   updatedPanel.add(new JLabel("The Restroom Logs Program is up to date."));
			 		    	    	   updatedPanel.add(new JLabel(versLine));
			 		    	    	   JOptionPane.showMessageDialog(null, updatedPanel, "Restroom Logs Program | Updater", JOptionPane.INFORMATION_MESSAGE, Icon);
			 		    	       }
			 		    	       if(!updated) {
			 		    	    	  JPanel updatedPanel  = new JPanel(new GridLayout(0,1));
			 		    	    	   updatedPanel.add(new JLabel("There is an update for the Restroom Logs Program."));
				 		    	    	updatedPanel.add(Box.createHorizontalStrut(5)); // a spacer
			 		    	    	   updatedPanel.add(new JLabel("Current "+versLine));
			 		    	    	   updatedPanel.add(new JLabel("Updated "+webLine));
			 		    	    	  JTextArea updateText = new JTextArea();
			 		    	    	 updateText.setOpaque(false);
			 		    	    	updateText.setEditable(false);
			 		     	    	//CONTENT
			 		    	    	updateText.setText("Would you like to download and install the Update?");
			 		    	    	updatedPanel.add(Box.createHorizontalStrut(5)); // a spacer
			 		     	    	updatedPanel.add(updateText);
			 		    	    	   Object[] options = {"Yes, update", "No, not now"};
			 		    	    	    resultU = JOptionPane.showOptionDialog(null, updatedPanel, "Restroom Logs Program | Updater", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, Icon, options,options[0]);
			 		    	       
			 		    	       if(resultU == JOptionPane.YES_OPTION) {
			 		    	    	  final JDialog dialog2 = new JDialog(null, "Downloading Update", ModalityType.MODELESS);
						    	        JProgressBar progressBar2 = new JProgressBar();
						    	        progressBar2.setIndeterminate(true);
						    	        progressBar2.setMinimum(0);
						    	        dialog2.setIconImage(webIcon.getImage());
						    	        dialog2.setLocationRelativeTo(null);
										dialog2.setResizable(false);
										dialog2.setPreferredSize(new Dimension(200, 65));
						    	        
						    	        JPanel panel2 = new JPanel(new BorderLayout());
						    	        panel2.add(progressBar2, BorderLayout.CENTER);
						    	        panel2.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
						    	        
						    	        dialog2.add(panel2);
						    	        dialog2.pack();
						    	        dialog2.setVisible(true);
			 		    	    	  HttpClient client1 = HttpClientBuilder.create().build();
						    	    	HttpGet request1 = new HttpGet(url);
	
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
				    	                FileReader fr2 = new FileReader(file);
					    				String  TotalLine = "";
						    			  String currentLine = "";
					    			    BufferedReader br4 = new BufferedReader(fr2);
					    			      lineCounter = 1;
					    			    while ((currentLine = br4.readLine()) != null) {
					    			    	if(lineCounter== 10){
					    			    		currentLine = webLine;
					    			    	}
					    			    	TotalLine += currentLine + "\n";
					    			    	lineCounter++;
					    			    }
					    			    FileWriter fw3 = new FileWriter(file);
					    			    fw3.write(TotalLine);
					    			    fw3.close();
					    			    br4.close();		
					    			    dialog2.dispose();
					    			    
					    			    JPanel success  = new JPanel(new GridLayout(0,1));
					    			    success.add(new JLabel("The Restroom Logs Program is now up to date."));
					    			    success.add(new JLabel(webLine));
				 		    	    	JOptionPane.showMessageDialog(null, success, "Restroom Logs Program | Updater", JOptionPane.INFORMATION_MESSAGE, Icon);
			 		    	       }
			 		    	       
			 		    	      
			 		    	   }

    	    	}
    	    	frame.dispose();
    	    	} catch (IOException e) {
					e.printStackTrace();
				}
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

}
