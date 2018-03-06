package mainProgram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

import javax.swing.ImageIcon;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.lgooddatepicker.components.TimePicker;

import javafx.scene.layout.Background;
/**
 * Configuration of the Restroom Logs Program
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
public class config{
	public static void main(String[] args) {		
	/*	long start = 0;
		long stop = 0;
		
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("START");
		
		start = System.nanoTime();
		System.out.println(checkDatabaseForDuplicates().toString());
		stop = System.nanoTime();
		System.out.println(start + ":" + stop + "  -  " + (stop-start));*/
	}
//The following are universal constants
	//Critical Vars
		public static boolean ranBefore = true;
		public static var VersionNumber = new var("config/DoNotTouch.txt", "Release Version");
	//Scren dimensions
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		public static double screenWidth = screenSize.getWidth();
		public static double screenHeight = screenSize.getHeight();
	//Databases
		//Student DB (Not final because file path should be customizable
		public static var StudentDBPath  = new var("config/DoNotTouch.txt", "studentDBPath");
		public static var StudentDBTableName  = new var("config/DoNotTouch.txt", "studentDBTableName");
		public static String rlGPFO = getRlGPFO();
		
		//Logs DB
		public static final String LogsDBPath  = "data/LogsDB.accdb";
		public static final String LogsDBTableName  = "Logs";
		public static final String stillSignedOut = "Still Signed Out";
		//remote DB Credentials (DB contains SecretKey)
		public static final String remoteDBCredFilePath = "data/remoteDB_Cred.txt";
		public static final String remoteDB_User = "username to remote DB";
		public static final String remoteDB_Pass = "password to remove DB";
	//Logs
		//.txt
		public static final String LogsPath  = "data/Logs.txt";
		//PDF
		public static final String PdfLogPath  = "data/"+getPdfName()+".pdf";
		public static final String PdfLogName  = getPdfName();
		//View PDF
		public static final String PdfLogViewPath  = "data/ViewLogsPDF.pdf";
	//Config files
		public static final String DoNotTouchFilePath  = "config/DoNotTouch.txt";
		public static final String GeneralConfigFilePath = "config/config.txt";
		public static final String mailFromFilePath = "config/mailFrom.txt";
		public static final String mailTemplateFilePath = "config/mailTemplate.html";
		public static final String mailToFilePath = "config/mailTo.txt";
	//Website URLs
		public static final String WebsiteHomeURL  = "http://rl.coding2kids.com/";
		public static final String WebsiteRemoteDBURL = "http://rl.coding2kids.com/";
	//Log .txt Priorities
		public static final String SystemPriority = "**** "; //Out of 5
		public static final String ErrorPriority = "*****"; //Out of 5
		public static final String StartUpPriority = "**   "; //Out of 5
		public static final String updateLogsPriority = "     "; //Out of 5
	//Font
		public static final String CairoFilePath = "assets/fonts/Cairo-Regular.ttf";
		public static final String KollektifFilePath = "assets/fonts/Kollektif.ttf";
		public static final String RubikFilePath = "assets/fonts/Rubik-Regular.ttf";
		public static final int minFontSize = 8;
		public static final int maxFontSize = 72;
		public static final double minFontSizeDivider = (screenWidth + screenHeight) / minFontSize;		
		public static final double maxFontSizeDivider = (screenWidth + screenHeight) / maxFontSize;
		public static float scanFontSize = RL.scan.getSize();
		public static final double SCAN_FONT_SIZE_SCROLL_SPEED = 3;
	//Icons
		public static final ImageIcon fileExitIcon = new ImageIcon("assets/images/exit.png");
		public static final ImageIcon helpIcon = new ImageIcon("assets/images/help.png");
		public static final ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
		public static final ImageIcon logsTxtIcon = new ImageIcon("assets/images/logs.png");
	//message center
		public static String defaultOtherMessage = "Welcome To The Restroom Logs Program";
	//stats other info
		public static String defaultOtherInfo = "Insert your Student ID above";
	//Email
		public static String emailSubject = "Restroom "+getPdfName();
		public static String emailBody = "Your PDF logs for "+getDate()+" in "+getTeacherName()+"'s classroom is attached";
		public static final String emailSenderName = "Restroom Logs Program<restroomlogs@gmail.com>";
		public static final String emailSender = "restroomlogs@gmail.com";
		public static boolean dailyEmails = getDailyEmails();
	//Teacher
		public static String teacherName = getTeacherName();
		public static String teacherEmail = getTeacherEmail();
		public static String endOfActiveHours  = getActiveHours();
		
		
	/**
	 * 
	 * @param configName name of config with out '='<br><strong>Example:</strong> ranBefore
	 * @return
	 */
	public static String pullFile(String fileName, String configName) {
		configName = configName + " =";
		
		try {
			int lineCounter = 0;
			File file = new File("/config/" + fileName + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
			   System.out.println(lineCounter + " " + line);
			   
			   if(line.contains(configName)) {
				   String data = (line.substring(line.indexOf(configName+1))).trim();
				   return data;
			   }
			}
			br.close();
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Error while pulling for information from config");
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * Write a value to a specific config in a file 
	 * @param fileName name of file in config directory
	 * @param configName name of config
	 * @param value value to be writen
	 * @return returns true if couldn't write, returns false if successful
	 */
	public static boolean pushFile(String fileName, String configName, String value) {
		configName = configName + " =";
		
		try {
			int lineCounter = 0;
			File file = new File("/config/" + fileName + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String line = "";
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
			   System.out.println(lineCounter + " " + line);
			   
			   if(line.contains(configName)) {
				   String write = configName + " " + value;
				   //FIXME: replace line with var write
				   return false;
			   }
			}
			br.close();
			bw.close();
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Error while writing information to config");
			e.printStackTrace();
		}
		return true;
	}
	
	//TODO: pull info from website
	public static String pullWebsite(String url) {
		URL remoteDBURL = null;
		try {
			remoteDBURL = new URL(WebsiteRemoteDBURL);
		} catch (MalformedURLException e) {
			BackEnd.logs.update.ERROR("Could not create a URL from WebsiteRemoteDBURL (\"" + WebsiteRemoteDBURL + "\")");
			e.printStackTrace();
		}
		
		StringBuilder result = new StringBuilder();

	    URLConnection connection = null;
	    try {
	    	connection = remoteDBURL.openConnection();
	    }
	    catch (IOException ex) {
	    	BackEnd.logs.update.ERROR("Cannot open connection to URL: " + remoteDBURL);
	    }

	    //not all headers come in key-value pairs - sometimes the key is
	    //null or an empty String
	    int headerIdx = 0;
	    String headerKey = null;
	    String headerValue = null;
	    while ( (headerValue = connection.getHeaderField(headerIdx)) != null ) {
	      headerKey = connection.getHeaderFieldKey(headerIdx);
	      if (headerKey != null && headerKey.length()>0) {
	        result.append(headerKey);
	        result.append(" : ");
	      }
	      result.append(headerValue);
	      result.append("n");
	      headerIdx++;
	    }
	    return result.toString();
	}
	
	
	/**
	 * Checks if program has ranBefore
	 */
	public static void checkRanBefore() {
		
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
			    			ranBefore = false;
			    			BackEnd.logs.create();
			    			BackEnd.logs.update.StartUp("\n\n\n----------");
				    		BackEnd.logs.update.StartUp("ranBefore found");
				    		BackEnd.logs.update.StartUp("Config.txt: " + line);
			    		}
			    		else if(lineSub.equalsIgnoreCase("true")) {
			    			ranBefore = true;
			    			BackEnd.logs.update.StartUp("\n\n\n----------");
				    		BackEnd.logs.update.StartUp("ranBefore found");
				    		BackEnd.logs.update.StartUp("Config.txt:  " + line);
			    		}
			    		else {
			    			BackEnd.logs.update.StartUp("\n\n\n----------");
			    			BackEnd.logs.update.ERROR("ranBefore non-valid boolean ("+ line + ") at " + DoNotTouchFilePath);
			    			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			    			ranBefore = true;
			    		}
			    		BackEnd.logs.update.StartUp("ranBefore is now set to: " + ranBefore);
			    	}
			    	break;
			   }
			}    
			br.close(); 
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Not able to read file at  "+DoNotTouchFilePath);
			BackEnd.logs.update.ERROR("Assuming that program has ran before (ranBefore = true");
			ranBefore = true;
			e.printStackTrace();
		}
	}
	
	/**
	 * Should only be used on init run of program. This changes ranBefore=false to ranBefore=true
	 */
	public static void ranBeforeToTrue() {
		File log = new File(DoNotTouchFilePath);
		FileReader fr;
		try {
			fr = new FileReader(log);
			String currentLine;
			String TotalLine = "";
		    BufferedReader br2 = new BufferedReader(fr);
		    
		    int lineCounter2 = 1;
		    while ((currentLine = br2.readLine()) != null) {
		    	if(lineCounter2 == 3){
		    		currentLine = "ranBefore = true";
		    	}
		    	TotalLine += currentLine + "\n";
		    	lineCounter2++;
		    }
		    FileWriter fw = new FileWriter(log);
		    fw.write(TotalLine);
		    fw.close();
		    br2.close();
		} catch (FileNotFoundException e) {
			BackEnd.logs.update.ERROR("Can not find Config.txt");
			e.printStackTrace();
		} catch (IOException e) {
			BackEnd.logs.update.ERROR("Can not access Config.txt");
			e.printStackTrace();
		}
	}
	
	/**
	 * This will update all Var values from .txt files
	 */
	public static void checkAllVars() {
		//TODO: CHECK ALL VARS FROM .TXT FILES and CHECK IF WEBSITES EXIST!
		
		//WebsiteHomeURL
		URL URL_WebsiteHomeURL = null;
		try {
			URL_WebsiteHomeURL = new URL(WebsiteHomeURL);
		} catch (MalformedURLException e) {
			BackEnd.logs.update.ERROR("Could not create a URL from WebsiteRemoteDBURL (\"" + WebsiteHomeURL + "\")");
			e.printStackTrace();
		}
	    try {
	    	URLConnection connection = URL_WebsiteHomeURL.openConnection();
	    }
	    catch (IOException ex) {
	    	BackEnd.logs.update.ERROR("Webpage URL: \"" + URL_WebsiteHomeURL + "\" does not exist");
	    }
	    
	    //WebsiteRemoteDBURL
	    URL URL_WebsiteRemoteDBURL = null;
		try {
			URL_WebsiteRemoteDBURL = new URL(WebsiteRemoteDBURL);
		} catch (MalformedURLException e) {
			BackEnd.logs.update.ERROR("Could not create a URL from WebsiteRemoteDBURL (\"" + WebsiteRemoteDBURL + "\")");
			e.printStackTrace();
		}
	    try {
	    	URLConnection connection = URL_WebsiteRemoteDBURL.openConnection();
	    }
	    catch (IOException ex) {
	    	BackEnd.logs.update.ERROR("Webpage URL: \"" + URL_WebsiteRemoteDBURL + "\" does not exist.  Will not be able to send emails");
	    }
	    
	    
	}
//ACCESSOR AND MUTATOR METHODS
	public static String getVersionNumber() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			 String line = null, versLine = null;
			 int lineCounter =0;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
	
			   switch(lineCounter){  
			    case 10: 
			    	versLine = line.substring(line.lastIndexOf(" ")+1);
			   }
			}
			
				br.close();
			
		    	return versLine;

		
		
		} catch (IOException e) {
				e.printStackTrace();
				BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
				return "Error";
			}
		
	}
	/**
	 * @deprecated Replaced by Var Variable Constructor
	 * @return Student Database Table Path as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
	public static String getStudentDBPath() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				
				switch(lineCounter){  
				case 8: 
					pathLine = line.substring(line.lastIndexOf(" ")+1);
				}
			}
			
			br.close();
			
			return pathLine;
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			return "Error";
		}
		
	}
	public static void setStudentDBPath(String name) {
		try {
		 FileReader fr2 = new FileReader(DoNotTouchFilePath);
		 String TotalLine = "";
		 String currentLine ="";
	    BufferedReader br31 = new BufferedReader(fr2);
	    
	    int lineCounter2 = 1;
	    while ((currentLine = br31.readLine()) != null) {
	    	if(lineCounter2 == 8){
	    		currentLine = "studentDBPath = "+name;
	    	}
	    	TotalLine += currentLine + "\n";
	    	lineCounter2++;
	    }
	    FileWriter fw2;
		
			fw2 = new FileWriter(DoNotTouchFilePath);
		
	    fw2.write(TotalLine);
	    fw2.close();
	    br31.close();
	    } catch (IOException e) {
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			e.printStackTrace();
		}
	}
	/**
	 * @deprecated Replaced by Var Variable Constructor
	 * @return Student Database Table Name as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
	public static String getStudentDBTableName() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				
				switch(lineCounter){  
				case 9: 
					pathLine = line.substring(line.lastIndexOf(" ")+1);
				}
			}
			
			br.close();
			
			return pathLine;
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			return "Error";
		}
		
	}
	public static String getPdfName() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd.yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		String date = dtf.format(now);
		return "Logs-"+date;
	}
	public static void setStudentDBTableName(String name) {
		try {
		 FileReader fr2 = new FileReader(DoNotTouchFilePath);
		 String TotalLine = "";
		 String currentLine ="";
	    BufferedReader br31 = new BufferedReader(fr2);
	    
	    int lineCounter2 = 1;
	    while ((currentLine = br31.readLine()) != null) {
	    	if(lineCounter2 == 9){
	    		currentLine = "studentDBTableName = "+name;
	    	}
	    	TotalLine += currentLine + "\n";
	    	lineCounter2++;
	    }
	    FileWriter fw2;
		
			fw2 = new FileWriter(DoNotTouchFilePath);
		
	    fw2.write(TotalLine);
	    fw2.close();
	    br31.close();
	    } catch (IOException e) {
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			e.printStackTrace();
		}
	}
	public static String getTeacherName() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			 String line = null, versLine = null;
			 int lineCounter =0;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
	
			   switch(lineCounter){  
			    case 5: 
			    	versLine = line.substring(line.lastIndexOf("=")+2);
			   
			   }
			}
			
				br.close();
				BufferedReader br1 = new BufferedReader(new FileReader(DoNotTouchFilePath));
				 
				  lineCounter =0;
				while ((line = br1.readLine()) != null) {  
				   // process the line.  
				   lineCounter++;
		
				   switch(lineCounter){  
				    
					case 6: 
						versLine += line.substring(line.lastIndexOf("=")+2);
				   }
				}
				
					br1.close();
		    	return versLine;

		
		
		} catch (IOException e) {
				e.printStackTrace();
				BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
				return "Error";
			}
	}
	public static void setTeachername(String name) {
		try {
			FileReader fr = new FileReader(DoNotTouchFilePath);
		 String TotalLine = "";
		 String currentLine = "";
	     BufferedReader br2 = new BufferedReader(fr);
	    
	     int lineCounter2 = 1;
	    while ((currentLine = br2.readLine()) != null) {
	    	if(lineCounter2 == 5){
	    		currentLine = "teacherTitle = "+name.substring(0,name.indexOf(".")+1);
	    	}
	    	if(lineCounter2 == 6){
	    		currentLine = "teacherName = "+name.substring(name.indexOf(".")+1);
	    	}
	    	TotalLine += currentLine + "\n";
	    	lineCounter2++;
	    }
	    FileWriter fw;
		
			fw = new FileWriter(DoNotTouchFilePath);
		
	    fw.write(TotalLine);
	    fw.close();
	    br2.close();
	    } catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
		}
	}
	public static void setTeacherEmail(String email) {
		try {
		 FileReader fr2 = new FileReader(DoNotTouchFilePath);
		 String TotalLine = "";
		 String currentLine ="";
	    BufferedReader br31 = new BufferedReader(fr2);
	    
	    int lineCounter2 = 1;
	    while ((currentLine = br31.readLine()) != null) {
	    	if(lineCounter2 == 7){
	    		currentLine = "teacherEmail = "+email;
	    	}
	    	TotalLine += currentLine + "\n";
	    	lineCounter2++;
	    }
	    FileWriter fw2;
		
			fw2 = new FileWriter(DoNotTouchFilePath);
		
	    fw2.write(TotalLine);
	    fw2.close();
	    br31.close();
	    } catch (IOException e) {
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			e.printStackTrace();
		}
	}
	public static String getTeacherEmail() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			 String line = null, versLine = null;
			 int lineCounter =0;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
	
			   switch(lineCounter){  
			    case 7: 
			    	versLine = line.substring(line.lastIndexOf("=")+2);
			   
				}
			}
			
				br.close();
			
		    	return versLine;

		
		
		} catch (IOException e) {
				e.printStackTrace();
				BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
				return "Error";
			}
		
	}
	public static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd.yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		String date = dtf.format(now);
		return date;
	}
	public static boolean getDailyEmails() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			 String line = null, versLine = null;
			 boolean daily;
			 int lineCounter =0;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
	
			   switch(lineCounter){  
			    case 4: 
			    	versLine = line.substring(line.lastIndexOf("=")+2);
			   
				}
			}
			
				br.close();
				
				if(versLine.equals("true"))
					daily = true;
				else
					daily  = false;
				
		    	return daily;

		} catch (IOException e) {
				e.printStackTrace();
				BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
				return true;
			}
	}
	public static String getActiveHours() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				switch(lineCounter){  
				case 11: 
					pathLine = line.substring(line.indexOf(" ")+3);
				}
			}
			
			br.close();
			return pathLine;
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
			return "Error";
		}
	}
	public static void setActiveHours(TimePicker time) {
		 String active = time.getText().substring(0, time.getText().lastIndexOf("0")+1)+" "+(time.getText().substring(time.getText().lastIndexOf("0")+1)).toUpperCase();
		 try {
				FileReader fr = new FileReader(DoNotTouchFilePath);
				 String TotalLine = "";
				 String currentLine = "";
				 BufferedReader br = new BufferedReader(fr);
				 int lineCounter = 1;
			    while ((currentLine = br.readLine()) != null) {
			    	if(lineCounter == 11){
			    		currentLine = "activeHours = "+active;
			    	}
			    	TotalLine += currentLine + "\n";
			    	lineCounter++;
			    }
			    FileWriter fw;
				fw = new FileWriter(DoNotTouchFilePath);
			    fw.write(TotalLine);
			    fw.close();
			    br.close();
				} catch (IOException e) {
					BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
					e.printStackTrace();
				}
	}
	public static void setDailyEmails(boolean set) {
		try {
			FileReader fr = new FileReader(DoNotTouchFilePath);
			 String TotalLine = "";
			 String currentLine = "";
			 BufferedReader br = new BufferedReader(fr);
			 int lineCounter = 1;
		    while ((currentLine = br.readLine()) != null) {
		    	if(lineCounter == 4){
		    		currentLine = "dailyEmails = "+set;
		    	}
		    	TotalLine += currentLine + "\n";
		    	lineCounter++;
		    }
		    FileWriter fw;
			fw = new FileWriter(DoNotTouchFilePath);
		    fw.write(TotalLine);
		    fw.close();
		    br.close();
			} catch (IOException e) {
				BackEnd.logs.update.ERROR("Unable to open/read:"+DoNotTouchFilePath);
				e.printStackTrace();
			}
	}
	/**
	 * 
	 * Checks database to see if there any repeat student IDs
	 * @return Integer arrays lists within the ArrayList shows if and where there are duplicates of student IDs
	 */
	public static ArrayList<ArrayList<Integer>> checkDatabaseForDuplicates() {
		ArrayList<int[]> ids = new ArrayList<int[]>(); //List of student ids that have been seen so far. [studentID, index]
		ArrayList<ArrayList<Integer>> duplicates = new ArrayList<ArrayList<Integer>>(); // { {studentID, index}, {studentId, index, index, index, index}, studentId, index, index} }
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+config.getStudentDBPath());
			Statement s;
			s = conn.createStatement();
			
			ResultSet rs;
			rs = s.executeQuery("SELECT [StudentID] FROM ["+config.getStudentDBTableName()+"]");
			
			int index = 1; //Access DB is 1 index, not 0 index
			while(rs.next()) {
				int currentID = rs.getInt("StudentId");
				
				//runs through previously seen ids to find match
				for(int i = 0; i < ids.size(); i++) {
					if(ids.get(i)[0] == currentID) { //if matching (duplicate)
						int findIndex = -1; //for index of original index
						for(int k = 0; k < duplicates.size(); k++) { //find the index of the original duplicated id
							if(duplicates.get(k).get(0) == currentID) {
								findIndex = k;
							}
						}
						if(findIndex != -1) { //if there was already a duplicate, add the current index to the list of duplicates
							duplicates.get(findIndex).add(new Integer(index));
						} if(findIndex == 1){ //this should never happen (if .contains, it should be able to find the index)
							BackEnd.logs.update.ERROR("Error while checking database for duplicates");
							//System.err.println("error");
						} else { //if this is the first duplicate for the studentID that it has found
							duplicates.add(new ArrayList<Integer> (Arrays.asList(new Integer(currentID), new Integer(ids.get(i)[1]), new Integer(index))));
						}
						break; //break to ensure there is no duplicates that are added more than once
					}
				}
				
				ids.add(new int[] {currentID, index}); //adds the current studentID and index to the ids ArrayList for checking in the future 
				index++;
			}
		} catch (ClassNotFoundException e) {
			BackEnd.logs.update.ERROR("Can't find jdbc Driver");
			return null;
		} catch (SQLException e) {
			BackEnd.logs.update.ERROR("Error while checking database for duplicates");
			e.printStackTrace();
		}
		return duplicates;
	}
	
	public static String getRlGPFO() {
		try {	
			String urlT = "https://rl.coding2kids.com/logs/secure.rlsecure";
	    	String USER_AGENT = "Chrome/63.0.3239.132 ";
	    	HttpClient client = HttpClientBuilder.create().build();
	    	HttpGet request = new HttpGet(urlT);
	
	    	// add request header
	    	request.addHeader("User-Agent", USER_AGENT);
	    	HttpResponse r;
			
				r = client.execute(request);
			
	        HttpEntity entity = r.getEntity();
	            
	            String il ;
	            
	            BufferedReader br1 = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
	            
	                  while ((il = br1.readLine()) != null) {
	                         return il;
	                  }
	                  
	                  br1.close();
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Could not fetch RlGPFO");
		}
		return "Error";
	}
	public static int getActiveHoursHOUR() {
		int hour;
		if(getActiveHours().contains("AM"))
			hour = Integer.parseInt(getActiveHours().substring(0, getActiveHours().indexOf(":")));
		else if(getActiveHours().contains("PM"))
			hour = Integer.parseInt(getActiveHours().substring(0, getActiveHours().indexOf(":"))) + 12;
		else
			hour = 00;
		return hour;
	}
	public static int getActiveHoursMINUTE() {
		return Integer.parseInt(getActiveHours().substring(getActiveHours().indexOf(":")+1, getActiveHours().lastIndexOf(" ")-1));
	}
}

