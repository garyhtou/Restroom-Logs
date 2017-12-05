/**
 * @author Gary Tou
 * @author Michael Schwamborn
 */

package mainProgram;

import java.io.*;
import java.sql.SQLException;

//import java.nio.file.*;

import org.apache.commons.io.*;

public class MAIN {
	static boolean ranBefore = false;
	//DO THREADS IN HERE
	
	public static void checkRanBefore() {
		try {
		int lineCounter = 0;
		File file = new File("/config/DoNotTouch.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "123";
		while ((line = br.readLine()) != null) {  
		   // process the line.  
		   lineCounter++;
		   System.out.println(lineCounter + " " + line);

		   switch(lineCounter){  
		    case 2:
		    	if(line.contains("ranBefore = ")) {
		    		String lineSub = line.substring(line.lastIndexOf(' ')+1);
		    		logs.updateLogsStartUp("ranBefore found");
		    		logs.updateLogsStartUp("ranBefore = " + line);
		    		logs.updateLogsStartUp("ranBefore is now set to: " + lineSub);
		    		if(lineSub.equalsIgnoreCase("false")){
		    			ranBefore = false;
		    		}
		    		else if(lineSub.equalsIgnoreCase("true")) {
		    			ranBefore = true;
		    		}
		    		else {
		    			logs.updateLogsERROR("ranBefore non-valid boolean at  /config/DoNotTouch.txt");
		    		}
		    	}
		    	break;  
		    case 8:  
		    	break;
		    case 12:  
		    	break;
		   }
		}    
		br.close(); 
		}
		catch (IOException e) {
			logs.updateLogsERROR("Not able to read file at  /config/DoNotTouch.txt");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//logs.updateLogsStartUp("Starting Program");
		//checkRanBefore();
		//startUp();
		//logs.addLogInfoToDB("jjj","kkkk","12345");
	}
	
	public static void startUp() {
		//-splash:assets/logos/RestroomLogsSplashscreen.png"
		logs.updateLogsStartUp("\n\n\n----------");
		SplashScreenManager.init(ranBefore); //IF IT HAS RAN BEFORE
	}
	

}
