package mainProgram;

public class MAIN {
	//DO THREADS IN HERE
	
	public static void main(String[] args) {
		//NEVER HAS RAN BEFORE. info maybe store this info in a .txt file
		boolean ranBefore = true; /*GRAB INFO FROM FILE*/
		
		if(!ranBefore){
			initStartUp();
		}
		//START UP AS NORMAL
		else {
			startUp();
		}
	}
	
	public static void startUp() {
		//SPLASH
		Front_End.createSplashScreen();
		//WAIT 3 SECS
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//START PROGRAM
		if(/*SPLASH IS STILL OPEN*/true) {
			Front_End.createWindow();
		}
	}
	
	
	
	
	public static void initStartUp() { //while on splashscreen, do init start up
		Front_End.createSplashScreenInitStartUp();
		Back_End.initStartUp();
		Front_End.createWindow();
		
	}

}
