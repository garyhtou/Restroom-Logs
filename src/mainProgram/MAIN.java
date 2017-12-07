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
	static String ConfigFilePath = config.ConfigFilePath;
	//DO THREADS IN HERE
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*config.checkRanBefore();
		startUp();*/
		logs.updatePDF();
	}
	
	public static void startUp() {
		//-splash:assets/logos/RestroomLogsSplashscreen.png"
		SplashScreenManager.init(config.ranBefore); //IF IT HAS RAN BEFORE
	}
	

}
