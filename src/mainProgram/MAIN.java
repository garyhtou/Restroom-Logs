package mainProgram;

public class MAIN {
	//DO THREADS IN HERE
	
	static boolean ranBefore = true;
	
	public static void main(String[] args) {
		//NEVER HAS RAN BEFORE. info maybe store this info in a .txt file
		ranBefore = true; /*GRAB INFO FROM FILE*/
		
		if(!ranBefore){
			initStartUp();
		}
		//START UP AS NORMAL
		else {
			startUp();
		}
	}
	
	public static void startUp() {
		Front_End.createSplashScreen(ranBefore);
	}
	
	
	
	
	public static void initStartUp() { //while on splashscreen, do init start up
		Front_End.createSplashScreen(ranBefore);
		Back_End.initStartUp();
		Front_End.createWindow();
		
	}

}
