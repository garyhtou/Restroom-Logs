package mainProgram;

/*
 * @author: Achintha Gunasekara
 * @date: 14.09.2015
 */

import java.awt.*;
import org.apache.commons.lang.time.StopWatch;

public class SplashScreenManager {
	public static void main(String[] args) {
		init(false);
		//SplashScreen.getSplashScreen();
	}
	
	static boolean ranBefore = true;
	static StopWatch stopWatch = new StopWatch();
	
	public static void init(boolean ranBeforeInput) {
		Back_End.updateLogsStartUp("Starting Program");
		Back_End.updateLogsStartUp("Starting Splash Screen");
		if(ranBeforeInput) {
			ranBefore = true;
		}
		else {
			ranBefore = false;
		}
		runSplash();
	}

    
    public SplashScreenManager() {}
    private static void renderSplashFrame(Graphics2D graphic, String message) {
    	Color LAVENDER_GRAY = new Color(0xC5C6C7); //custom color
    	
    	graphic.setComposite(AlphaComposite.Clear); //Comment out this line to see where it will paint over
        graphic.fillRect(170,140,250,40); //120, 140, 200, 40
        graphic.setPaintMode();
        graphic.setColor(LAVENDER_GRAY);
        if(ranBefore) {
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
            Back_End.updateLogsERROR("SplashScreen.getSplashScreen() returned null");
            return;
        }
        
        
        Graphics2D graphic = splash.createGraphics();
        if (graphic == null) {
            System.out.println("graphic is null");
            Back_End.updateLogsERROR("graphic is null");
            return;
        }
        
        stopWatch.start();
//STEPS ---------------------------------------
        if(ranBefore) {
        	//Step 1
        	renderSplashFrame(graphic, "Checking for updates");
        	Back_End.updateLogsStartUp("Checking for updates");
        	splash.update();
        	//CHECK FOR UPDATES
        	//Call Action in another thread
        	
        	//START PROGRAM
        	Back_End.updateLogsStartUp("Opening Window");
        	waitThreeStart(splash, graphic); //makes sure you have been on start screen for 3 secs then run program
        	Back_End.updateLogsStartUp("Start Up Complete\n\n-----\n\n");
        }
//STEP INIT -------------------------------------
        else { //INIT START UP, HAS NOT RAN BEFORE
        	//STEP 1
        	renderSplashFrame(graphic, "NOTICE: Program hasn't ran before!");
        	Back_End.updateLogsStartUp("NOTICE: Program hasn't ran before!");
        	splash.update();
        	//no need to call anything, this is a notice to use that this program has not ran before
        	
        	//STEP 2
        	renderSplashFrame(graphic, "Checking database");
        	Back_End.updateLogsStartUp("Checking database");
        	splash.update();
        	//TODO: check if data base exists
        	//Call Action in another thread
        	
        	//STEP 3
        	renderSplashFrame(graphic, "Creating PDF Logs");
        	Back_End.updateLogsStartUp("Creating PDF Logs");
        	splash.update();
        	Back_End.createPDF();
        	//Call Action in another thread
        	
        	//STEP 5
        	renderSplashFrame(graphic, "Creating Logs");
        	Back_End.updateLogsStartUp("Creating Logs");
        	splash.update();
        	//Logs have already been create, this is for display onlyu :)
        	//Call Action in another thread
        	
        	ranBefore = true; //TODO: Change in file
        	//START PROGRAM
        	Back_End.updateLogsStartUp("Opening Window");
        	waitThreeStart(splash, graphic);
        	Back_End.updateLogsStartUp("Start Up Complete\n\n-----\n\n");
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
    	Front_End.createWindow(); //TODO: NEEDS TO BE CALLED IN ANOTHER THREAD, i think
    	
    }
}