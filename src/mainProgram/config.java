package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class config {
	//The following are universal constants
	public static String StudentDBPath  = "data/TestDB.accdb";
	public static String PdfLogPath  = "data/LogsPDF.pdf";
	public static String PdfLogViewPath  = "data/ViewLogsPDF.pdf";
	public static String DBTableName  = "TestDB";
	public static String LogsPath  = "data\\Logs.txt";
	public static String ConfigFilePath  = "/config/DoNotTouch.txt";
	public static String WebsiteBaseURL  = "http://rl.coding2kids.com/";
	

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
}