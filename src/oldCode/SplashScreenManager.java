package oldCode;

import java.awt.*;
import org.apache.commons.lang.time.StopWatch;

import mainProgram.config;

public class SplashScreenManager {
	public static void main(String[] args) {
		config.checkRanBefore();
		init();
		//SplashScreen.getSplashScreen();
	}
	
	static StopWatch stopWatch = new StopWatch();
	
	public static void init() {
		logs.updateLogsStartUp("Starting Program");
		logs.updateLogsStartUp("Starting Splash Screen");
		runSplash();
	}

    
    public SplashScreenManager() {}
    private static void renderSplashFrame(Graphics2D graphic, String message) {
    	Color LAVENDER_GRAY = new Color(0xC5C6C7); //custom color
    	
    	graphic.setComposite(AlphaComposite.Clear); //Comment out this line to see where it will paint over
        graphic.fillRect(170,140,250,40); //120, 140, 200, 40
        graphic.setPaintMode();
        graphic.setColor(LAVENDER_GRAY);
        if(config.ranBefore) {
        	graphic.drawString(message+"...", 200, 165);
        }
        else {
        	graphic.drawString(message+"...", 200, 165);
        }
    }
    
    
    public static void runSplash() {
    	SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            logs.updateLogsERROR("SplashScreen.getSplashScreen() returned null");
            return;
        }
        
        
        Graphics2D graphic = splash.createGraphics();
        if (graphic == null) {
            System.out.println("graphic is null");
            logs.updateLogsERROR("graphic is null");
            return;
        }
        
        stopWatch.start();
//STEPS ---------------------------------------
        if(config.ranBefore) {
        	//Step 1
        	renderSplashFrame(graphic, "Checking for updates");
        	logs.updateLogsStartUp("Checking for updates");
        	splash.update();
        	//CHECK FOR UPDATES
        	//Call Action in another thread
        	
        	//START PROGRAM
        	logs.updateLogsStartUp("Opening Window");
        	waitThreeStart(splash, graphic); //makes sure you have been on start screen for 3 secs then run program
        	logs.updateLogsStartUp("Start Up Complete\n-----");
        }
//STEP INIT -------------------------------------
        else { //INIT START UP, HAS NOT RAN BEFORE
        	//STEP 1
        	renderSplashFrame(graphic, "NOTICE: Program hasn't ran before!");
        	logs.updateLogsStartUp("NOTICE: Program hasn't ran before!");
        	splash.update();
        	//no need to call anything, this is a notice to use that this program has not ran before
        	
        	//STEP 2
        	renderSplashFrame(graphic, "Checking database");
        	logs.updateLogsStartUp("Checking database");
        	splash.update();
        	//TODO: check if data base exists
        	//Call Action in another thread
        	
        	//STEP 3
        	renderSplashFrame(graphic, "Creating PDF Logs");
        	logs.updateLogsStartUp("Creating PDF Logs");
        	splash.update();
        	logs.createPDF();
        	//Call Action in another thread
        	
        	config.ranBefore = true; //TODO: Change in file
        	//START PROGRAM
        	logs.updateLogsStartUp("Opening Window");
        	waitThreeStart(splash, graphic);
        	logs.updateLogsStartUp("Start Up Complete\n-----");
        }
        splash.close();
    }
    
    public static void waitThreeStart(SplashScreen splash, Graphics2D graphic) {
    	stopWatch.suspend();
    	int stopWatchSec = Integer.parseInt(stopWatch.toString().substring(5, 7));
    	int stopWatchMill = Integer.parseInt(stopWatch.toString().substring(8, 11));

    	stopWatch.stop();
    	stopWatch.reset();
    	if(stopWatchSec < 03) {
    		int timeToWait = 3000 - ((stopWatchSec * 1000) + stopWatchMill);
    		try {
				Thread.sleep(timeToWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

    	renderSplashFrame(graphic, "Starting program");
    	splash.update();
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	Window.createWindow(); //TODO: NEEDS TO BE CALLED IN ANOTHER THREAD, i think
    	
    }
}