/**
 * @author Gary Tou
 * @author Michael Schwamborn
 */

package mainProgram;

import java.io.*;

//import java.nio.file.*;

import org.apache.commons.io.*;

public class MAIN {
	//DO THREADS IN HERE
	static boolean ranBefore = false;
	
	int lineCounter = 0;
	File file = new File("/config/DoNotTouch.txt");
	BufferedReader br = new BufferedReader(new FileReader(file));  
	String line = "";
	while ((line = br.readLine()) != null) {  
	   // process the line.  
	   lineCounter++;

	   switch(lineCounter){  
	    case 2:
	    	if(line.contains(""))
	       break;  
	    case 8:  
	       \\ do your code for line no 8  
	       break;  
	    case 12:  
	       \\ do your code for line no 12  
	       break;  
	   }  
	}    
	br.close(); 
	
	public static void main(String[] args) {
		//NEVER HAS RAN BEFORE. info maybe store this info in a .txt file
		if(true /*RAN BEFORE*/ /*GRAB INFO FROM FILE*/) {
			ranBefore = true; 
		}
		else {
			ranBefore = false;
		}
		
		startUp();
	}
	
	public static void startUp() {
		//-splash:assets/logos/RestroomLogsSplashscreen.png"
		
		SplashScreenManager.init(ranBefore); //IF IT HAS RAN BEFORE
	}
	

}
