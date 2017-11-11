package mainProgram;

public class MAIN {
	//DO THREADS IN HERE
	
	public static void main(String[] args) {
		if(/*program has never started up before[maybe store this info in a .txt file]*/false){
			initStartUp();
		}
		else {
			startUp();
		}
	}
	public static void startUp() {
		Front_End.createSplashScreen(); //just do display, nothing happens in background
		Front_End.createWindow();
		
	}
	
	
	
	
	public static void initStartUp() { //while on splashscreen, do init start up
		Front_End.createSplashScreenInitStartUp();
		Back_End.initStartUp();
		Front_End.createWindow();
		
	}

}
