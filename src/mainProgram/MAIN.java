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
		ranBefore = true; /*GRAB INFO FROM FILE*/
		
		startUp();
	}
	
	public static void startUp() {
			//https://www.java-forums.org/awt-swing/29971-splashscreen-getsplashscreen-returned-null.html
		//Front_End.createSplashScreen(ranBefore);
		//String args[] = {"-splash:assets/RestroomLogsSplashscreen.png"};
		//SplashScreenManager.main(null);
		//SplashScreenManager.runSplash();
		Front_End.createWindow();
	}
	

}
