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
	
	BufferedReader br = new BufferedReader(new FileReader("config/DoNotTouch.txt"));
	String line = br.readLine();
    int lineNumber = 0;
    while(line != null) {
        lineNumber++;
        int i = line.indexOf(date1);
        if(i != -1) {
            int s = i + date1.length();
            int e = line.length();
            System.out.println(date1 + " found in line " + lineNumber  + " at index " + i + ", extract text from " + s + " to " + e);
            String extractedText = line.substring(s, e);
            String[] extractedTextParts = extractedText.trim().split("\\s+");
            for(String part : extractedTextParts) {
                if(isTime(part)) {
                    System.out.println("    '" + part + "'");   
                }
            }
        }
        line = br.readLine();
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
		//-splash:assets/RestroomLogsSplashscreen.png"
		
		SplashScreenManager.init(ranBefore); //IF IT HAS RAN BEFORE
	}
	

}
