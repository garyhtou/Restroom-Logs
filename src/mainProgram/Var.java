package mainProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Constuctor Class to access the {@link config#DoNotTouchFilePath} and return its data
 * <div>
 * Returns {@linkplain Strings} only
 * 
 *
 */
public class Var {
	public static String var;
	public static int loc;
	
	public Var(int location) {
		loc = location;
		get(location);
	}
	public void get(int location) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(config.DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				
				if(location == lineCounter){  
					pathLine = line.substring(line.lastIndexOf(" ")+1);
				}
			}
			
			br.close();
			
			var =  pathLine;
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+config.DoNotTouchFilePath);
			var =  "Error";
		}
	}
	public String toString() {
		get(loc);
		return var;
	}
	public void update() {
		this.get(loc);
	}
	
	/*private String loc; //file path
	private int lineNum; //line number
	private String pre;
	private String pullValue; //value pulled from file
	private String returnValue; //value to be returned (include substring prefix)
	
	private static ArrayList<Var> allVars = new ArrayList<Var>(); //array list to hold all instantiated vars
	
	*//**
	 * constructor for var
	 * @param filePath file path of the file
	 * @param lineNumber line number of the data
	 * @param prefix prefix of the value (EX. databaseFilePath = middle of no where;  "databaseFilePath =" is the prefix
	 *//*
	public Var(String filePath, int lineNumber, String prefix) {
		loc = filePath;
		lineNum = lineNumber;
		pre = prefix;
	}
	*//**
	 * updates the pulled value
	 *//*
	public void update() {
		//pull info from file using path and line number
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(config.DoNotTouchFilePath));
			String line = null, pathLine = null;
			int lineCounter =0;
			while ((line = br.readLine()) != null) {  
				// process the line.  
				lineCounter++;
				
				if(lineNum == lineCounter){  
					pathLine = line.substring(line.lastIndexOf(" ")+1);
				}
			}
			
			br.close();
			
			pullValue =  pathLine;
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			BackEnd.logs.update.ERROR("Unable to open/read:"+config.DoNotTouchFilePath);
			pullValue =  "Error";
		}
		allVars.add(this);
	}
	*//**
	 * updates all pulled values
	 *//*
	public void updateAll() {
		for(Var item : allVars) {
			item.update();
		}
	}
	public String toString() {
		update();
		returnValue = pullValue.substring(pre.trim().length()).trim();
		return returnValue;
	}
	public void setValue(String value) {
		//set lineNum in loc to pre + " " + value
		
		
		
		//then
		update();
	}*/
}
