package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileConfig {
	private String loc; //file path
	private int lineNum; //line number
	private String pre;
	private String pullValue; //value pulled from file
	private String returnValue; //value to be returned (include substring prefix)
	private static ArrayList<FileConfig> allVars = new ArrayList<FileConfig>(); //array list to hold all instantiated vars
	
	/**
	 * constructor for FileConfig
	 * @param filePath file path of the file
	 * @param lineNumber line number of the data
	 * @param prefix prefix of the value (EX. databaseFilePath = middle of no where;  "databaseFilePath =" is the prefix
	 */
	public FileConfig(String filePath, String prefix) {
		loc = filePath;
		pre = prefix.trim() + " = ";
		allVars.add(this);
		update();
	}
	/**
	 * updates the pulled value
	 */
	public void update() {
		try {
			File file = new File(loc);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {  
			   if(line.indexOf(pre) == 0) {
				   pullValue = line;
				   break;
			   }
			}
			br.close();
		} catch (IOException e) {
			BackEnd.logs.update.ERROR("Error while pulling for information from config");
			e.printStackTrace();
		}
	}
	/**
	 * updates all pulled values
	 */
	public static void updateAll() {
		for(FileConfig item : allVars) {
			item.update();
		}
	}
	public String toString() {
		update();
		if(pullValue != null) {
			returnValue = pullValue.substring(pre.trim().length()).trim();
		}
		return returnValue;
	}
	public void setValue(String value) {
		String newLine = pre + value;
		try {
			File file = new File(loc);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String fileContent = "";
			String currentLine = "";
			while ((currentLine = br.readLine()) != null) {
				fileContent += currentLine + "\n";
			}
			update();
			String newContent = fileContent.replace(pullValue, newLine);
			//System.out.println(newContent);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(newContent);
			br.close();
			bw.close();
		}
		catch (IOException e) {
			BackEnd.logs.update.ERROR("Error while writing information to config");
			e.printStackTrace();
		}
		update();
	}
}

