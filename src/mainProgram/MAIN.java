/**
 * @author Gary Tou
 * @author Michael Schwamborn
 */

package mainProgram;

public class MAIN {
	//DO THREADS IN HERE
	
	static boolean ranBefore = true;
	
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
