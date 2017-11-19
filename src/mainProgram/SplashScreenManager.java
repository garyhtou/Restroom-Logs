package mainProgram;

/*
 * @author: Achintha Gunasekara
 * @date: 14.09.2015
 */

import java.awt.*;

import org.apache.commons.lang.time.StopWatch;

public class SplashScreenManager {
	public static void main(String[] args) {
		runSplash();
		SplashScreen.getSplashScreen();
	}
	
	static boolean ranBefore = true;
	static StopWatch stopWatch = new StopWatch();
	
	public static void init(boolean ranBeforeInput) {
		if(ranBeforeInput) {
			ranBefore = true;
		}
		else {
			ranBefore = false;
		}
		runSplash();
	}

    
    public SplashScreenManager() {}
    static void renderSplashFrame(Graphics2D graphic, String message) {
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
    
    static final SplashScreen splash = SplashScreen.getSplashScreen();
    static Graphics2D graphic = splash.createGraphics();
    public static void runSplash() {
    	//System.out.println(SplashScreen.getSplashScreen());
    	
    	
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        
        
        if (graphic == null) {
            System.out.println("g is null");
            return;
        }
        stopWatch.start();
//STEPS ---------------------------------------
        if(ranBefore) {
        	//Step 1
        	renderSplashFrame(graphic, "Checking for updates");
        	splash.update();
        	//CHECK FOR UPDATES
        	//Call Action in another thread
        	

        	waitThreeStart(); //makes sure you have been on start screen for 3 secs then run program
        }
//STEP INIT -------------------------------------
        else { //INIT START UP, HAS NOT RAN BEFORE
        	//STEP 1
        	renderSplashFrame(graphic, "NOTICE: Program hasn't ran before!");
        	splash.update();
        	//no need to call anything, this is a notice to use that this program has not ran before
        	
        	//STEP 2
        	renderSplashFrame(graphic, "Checking database");
        	splash.update();
        	//TODO: check if data base exists
        	//Call Action in another thread
        	
        	//STEP 3
        	renderSplashFrame(graphic, "Creating PDF Logs");
        	splash.update();
        	Back_End.createPDF();
        	//Call Action in another thread
        	
        	//STEP 4
        	renderSplashFrame(graphic, "Creating CopyPDF Logs");
        	splash.update();
        	//Call Action in another thread
        	if(Back_End.createViewPDF()) { //TODO: NEEDS TO BE CALLED IN ANOTHER THREAD, i think
        		renderSplashFrame(graphic, "ERROR: can't create Copy Logs");
            	splash.update();
            	try {
                    Thread.sleep(15000); //shows message for 15 secs
                }
                catch(InterruptedException e) {
                }

        	}
        	
        	//STEP 5
        	renderSplashFrame(graphic, "Creating Logs");
        	splash.update();
        	//TODO: check if data base exists
        	//Call Action in another thread
        	
        	
        	waitThreeStart();
        }
        splash.close();
    }
    
    public static void waitThreeStart() {
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