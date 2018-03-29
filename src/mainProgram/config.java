package mainProgram;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		public static boolean ranBefore;
		public static var VersionNumber = new var("config/DoNotTouch.txt", "Release Version");
		public static long ScannerReReadDelay = 333; //this is equivalent to 1/3 of a sec
	//Screen dimensions
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		public static double screenWidth = screenSize.getWidth();
		public static double screenHeight = screenSize.getHeight();
	//Databases
		//Student DB (Not final because file path should be customizable
		public static var StudentDBPath  = new var("config/DoNotTouch.txt", "studentDBPath");
		public static var StudentDBTableName  = new var("config/DoNotTouch.txt", "studentDBTableName");
		/**
		 * @deprecated Should call the Accessor Method, {@link #getRlGPFO()}
		 */
		public static String rlGPFO = getRlGPFO();
		
		//Logs DB
		public static final String LogsDBPath  = "data/LogsDB.accdb";
		public static String LogsDBTableName  = "Logs";
		public static final String stillSignedOut = "Still Signed Out";
	//Logs
		//.txt
		public static final String LogsPath  = "data/Logs.txt";
		//PDF
		public static final String PdfLogPath  = "data/"+"Logs"+".pdf";
		//public static final String PdfLogName  = getPdfName();
	//Config files
		public static final String DoNotTouchFilePath  = "config/DoNotTouch.txt";
		public static final String GeneralConfigFilePath = "config/config.txt";
		public static final String mailFromFilePath = "config/mailFrom.txt";
		public static final String mailTemplateFilePath = "config/mailTemplate.html";
		public static final String mailToFilePath = "config/mailTo.txt";
	//Website URLs
		public static final String WebsiteHomeURL  = "http://rl.coding2kids.com/";
    	public static final String urlT = "https://rl.coding2kids.com/admin/info.txt";
    	public static final String USER_AGENT = "Chrome/63.0.3239.132 "; //TODO: do more research to see if this is what we want

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
		public static final ImageIcon PDFIcon = new ImageIcon("assets/images/PDF.png");
	//message center
		public static String defaultOtherMessage = "Welcome To The Restroom Logs Program.";
	//stats other info
		public static String defaultOtherInfo = "Scan or Type your Student ID above";
	//Email
		public static String emailSubjectPDF = "Restroom "+getPdfName();
		public static String emailBodyPDF = "Your PDF logs for "+getDate()+" in "+getTeacherName()+"'s classroom is attached";
		public static String emailSubjectTXT = "Restroom Text Logs "+getPdfName();
		public static String emailBodyTXT = "Your system txt logs for "+getDate()+" in "+getTeacherName()+"'s classroom is attached";//TODO:Write
		public static final String emailSenderName = "Restroom Logs Program<restroomlogs@gmail.com>";
		public static final String emailSender = "restroomlogs@gmail.com";
		/**
		 * @deprecated Should call the Accessor Method, {@link #getDailyEmails()}
		 */
		public static boolean dailyEmails = getDailyEmails();
	//Teacher
		/**
		 * @deprecated Should call the Accessor Method, {@link #getTeacherName()}
		 */
		public static String teacherName = getTeacherName();
		public static var teacherEmail = new var("config/DoNotTouch.txt","teacherEmail");
		public static var endOfActiveHours  = new var("config/DoNotTouch.txt","activeHours");
		
		public static final String licensePath = "LICENSE";
		
		
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
	
//ACCESSOR AND MUTATOR METHODS
/**
	 * @deprecated Replaced by Var Variable Constructor
	 * @return Version Number as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
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
/**
	 * @deprecated Replaced by Var Variable Constructor
	 *  Sets the Student Database Table Path to @param path as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
	public static void setStudentDBPath(String path) {
		try {
		 FileReader fr2 = new FileReader(DoNotTouchFilePath);
		 String TotalLine = "";
		 String currentLine ="";
	    BufferedReader br31 = new BufferedReader(fr2);
	    
	    int lineCounter2 = 1;
	    while ((currentLine = br31.readLine()) != null) {
	    	if(lineCounter2 == 8){
	    		currentLine = "studentDBPath = "+path;
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
	}/**
	 * @deprecated Replaced by Var Variable Constructor
	 * Sets the Student Database Table Name to @param as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
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
	/**
	 * Accessor Method to get Teacher name in {@link #DoNotTouchFilePath}
	 * @return Teacher Name as specified in {@link #DoNotTouchFilePath} Including Prefix, i.e. Mr.
	 */
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
	/**
	 * Mutator Method to set Teacher Name to parameter in {@link #DoNotTouchFilePath}
	 * @param  name New Teacher Name, including Prefix
	 */
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
/**
	 * @deprecated Replaced by Var Variable Constructor
	 * Sets Teacher Email to @param email as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
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
/**
	 * @deprecated Replaced by Var Variable Constructor
	 * @return Teacher Email as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
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
	/**
	 * Returns Current Date
	 * @return Current System Date with Format MM.DD.YYY
	 */
	public static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}
	/**
	 * Accessor Method to get whether or not the User wants daily emails in {@link #DoNotTouchFilePath}
	 * @return boolean true or false whether the User has selected to haave daily emails or not
	 */
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
/**
	 * @deprecated Replaced by Var Variable Constructor
	 * @return End of Active Hours as specified in {@link #DoNotTouchFilePath}
	 * @see var#Var(int) Var Constructor
	 */
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
/**
	 * 
	 * Sets End of Active Hours to @param time as specified in {@link #DoNotTouchFilePath}
	 * 
	 */
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
	/**
	 * 
	 * Sets the boolean to daily emails  to @param set as specified in {@link #DoNotTouchFilePath}
	 * 
	 */
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

			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+config.StudentDBPath.toString());
			Statement s;
			s = conn.createStatement();
			
			ResultSet rs;
			rs = s.executeQuery("SELECT [StudentID] FROM ["+config.StudentDBTableName.toString()+"]");
			
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
	/**
	 * @return RLGFO from Secure Entity 
	 */
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
	/**
	 * Gets Hour from {@link #endOfActiveHours} in {@link #DoNotTouchFilePath}
	 * @return The Hour part of the End Of Active Hours, converting it from a String to an Integer
	 */
	public static int getActiveHoursHOUR() {
		int hour;
		if(endOfActiveHours.toString().contains("AM"))
			hour = Integer.parseInt(endOfActiveHours.toString().substring(0, endOfActiveHours.toString().indexOf(":")));
		else if(endOfActiveHours.toString().contains("PM"))
			hour = Integer.parseInt(endOfActiveHours.toString().substring(0, endOfActiveHours.toString().indexOf(":"))) + 12;
		else
			hour = 00;
		return hour;
	}
	/**
	 * Gets Minute from {@link #endOfActiveHours} in {@link #DoNotTouchFilePath}
	 * @return The Minute part of the End Of Active Hours, converting it from a String to an Integer
	 */
	public static int getActiveHoursMINUTE() {
		return Integer.parseInt(endOfActiveHours.toString().substring(endOfActiveHours.toString().indexOf(":")+1, endOfActiveHours.toString().lastIndexOf(" ")-1));
	}
	/**
	 * Checks if there is an update from the online servers
	 * @return true/false depending if there is an update from online
	 */
	public static boolean checkForUpdates() {
        boolean update = false;
		try {
			HttpClient client = HttpClientBuilder.create().build();
	    	HttpGet request = new HttpGet(urlT);
	
	    	// add request header
	    	request.addHeader("User-Agent", USER_AGENT);
	    	HttpResponse response = client.execute(request);
	            String inputLine ;
	            BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            String webLine = null;
	                  while ((inputLine = br1.readLine()) != null) {
	                         webLine=inputLine;
	                  }
						
							br1.close();
						
						
							if(VersionNumber.toString().equals(webLine.substring(webLine.lastIndexOf(" ")+1))) {
			      				update = false;
			      			}
			      			if(!VersionNumber.toString().equals(webLine.substring(webLine.lastIndexOf(" ")+1))) {
			      				update = true;
			      			}
	    	    
			} catch (IOException e) {
				BackEnd.logs.update.ERROR("Unable to check for online version number.");
				e.printStackTrace();
			}
    	return update;       

	}
}

