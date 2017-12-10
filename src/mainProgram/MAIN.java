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
	//DO THREADS IN HERE
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		startUp();
	}
	
	public static void startUp() {
		//-splash:assets/logos/RestroomLogsSplashscreen.png"
		config.checkRanBefore();
		SplashScreenManager.init();
	}
}
