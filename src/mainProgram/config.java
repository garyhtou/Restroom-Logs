package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class config {
	//The following are universal constants
	public static String StudentDBPath  = "data/TestDB.accdb";
	public static String PdfLogPath  = "data/LogsPDF.pdf";
	public static String PdfLogViewPath  = "data/ViewLogsPDF.pdf";
	public static String DBTableName  = "TestDB";
	public static String LogsPath  = "data/Logs.txt";
	public static String ConfigFilePath  = "config/DoNotTouch.txt";
	public static String WebsiteBaseURL  = "http://rl.coding2kids.com/";
	final static String SystemPriority = "**** "; //Out of 5
	final static String ErrorPriority = "*****"; //Out of 5
	final static String StartUpPriority = "**   "; //Out of 5
	final static String updateLogsPriority = "     "; //Out of 5
	public static boolean ranBefore = true;;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param configName name of config with out '='<br><strong>Example:</strong> ranBefore
	 * @return
	 */
	public static String pull(String fileName, String configName) {
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
		}
		catch (IOException e) {
			logs.updateLogsERROR("Error while pulling for information from config");
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
	public static boolean push(String fileName, String configName, String value) {
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
		}
		catch (IOException e) {
			logs.updateLogsERROR("Error while writing information to config");
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
		File file = new File(ConfigFilePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "123";
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
		    			logs.createLogs();
		    			logs.updateLogsStartUp("\n\n\n----------");
			    		logs.updateLogsStartUp("ranBefore found");
			    		logs.updateLogsStartUp("Config.txt ranBefore = " + line);
			    		//TODO, CHANGE FALSE TO TRUE
		    			
		    		}
		    		else if(lineSub.equalsIgnoreCase("true")) {
		    			ranBefore = true;
		    			logs.updateLogsStartUp("\n\n\n----------");
			    		logs.updateLogsStartUp("ranBefore found");
			    		logs.updateLogsStartUp("Config.txt ranBefore = " + line);
		    		}
		    		else {
		    			logs.updateLogsERROR("ranBefore non-valid boolean (" + lineSub + ") at" + ConfigFilePath);
		    			logs.updateLogsERROR("Assuming that program has ran before (ranBefore = true");
		    			ranBefore = true;
		    		}
		    		logs.updateLogsStartUp("ranBefore is now set to: " + ranBefore);
		    	}
		    	break;
		   }
		}    
		br.close(); 
		}
		catch (IOException e) {
			logs.updateLogsERROR("Not able to read file at  "+ConfigFilePath);
			logs.updateLogsERROR("Assuming that program has ran before (ranBefore = true");
			ranBefore = true;
			e.printStackTrace();
		}
	}
}