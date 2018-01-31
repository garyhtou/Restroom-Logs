package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class config {
//The following are universal constants
	//Critical Vars
		public static boolean ranBefore = true;
		public static String VersionNumber = getVersionNumber();
	//Databases
		//Student DB (Not final because file path should be customizable
		public static String StudentDBPath  = getStudentDBPath();
		public static String StudentDBTableName  = getStudentDBTableName();
		//Logs DB
		public final static String LogsDBPath  = "data/LogsDB.accdb";
		public final static String LogsDBTableName  = "Logs";
		//remote DB Credentials (DB contains SecretKey)
		public final static String remoteDBCredFilePath = "data/remoteDB_Cred.txt";
		public final static String remoteDB_User = "username to remote DB";
		public final static String remoteDB_Pass = "password to remove DB";
	//Logs
		//.txt
		public final static String LogsPath  = "data/Logs.txt";
		//PDF
		public final static String PdfLogPath  = "data/LogsPDF.pdf";
		//View PDF
		public final static String PdfLogViewPath  = "data/ViewLogsPDF.pdf";
	//Config files
		public final static String DoNotTouchFilePath  = "config/DoNotTouch.txt";
		public final static String GeneralConfigFilePath = "config/config.txt";
		public final static String mailFromFilePath = "config/mailFrom.txt";
		public final static String mailTemplateFilePath = "config/mailTemplate.html";
		public final static String mailToFilePath = "config/mailTo.txt";
	//Website URLs
		public final static String WebsiteHomeURL  = "http://rl.coding2kids.com/";
		public final static String WebsiteRemoteDBURL = "http://rl.coding2kids.com/";
	//Log .txt Priorities
		public final static String SystemPriority = "**** "; //Out of 5
		public final static String ErrorPriority = "*****"; //Out of 5
		public final static String StartUpPriority = "**   "; //Out of 5
		public final static String updateLogsPriority = "     "; //Out of 5
	//Fonts
		public final static String AnticSlabFilePath = "assets/fonts/AnticSlab-Regular.ttf";
	
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
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

	public static String getVersionNumber() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			 String line = null, versLine = null;
			 int lineCounter =0;
			while ((line = br.readLine()) != null) {  
			   // process the line.  
			   lineCounter++;
	
			   switch(lineCounter){  
			    case 9: 
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
	public static String getStudentDBPath() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				
				switch(lineCounter){  
				case 7: 
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
	public static String getStudentDBTableName() {
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
}