package mainProgram;

/*
 * @author: Achintha Gunasekara
 * @date: 14.09.2015
 */

import java.awt.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.StopWatch;

public class SplashScreenManager {
	public static void main(String[] args) {
		runSplash();
	}
	
	static boolean ranBefore = true;
	//static StopWatch stopWatch = new StopWatch();
	
	public static void init(boolean ranBeforeInput) {
		if(ranBeforeInput) {
			ranBefore = true;
		}
		else {
			ranBefore = false;
		}
	}

    
    public SplashScreenManager() {}
    static void renderSplashFrame(Graphics2D graphic, String message) {
    	Color LAVENDER_GRAY = new Color(0xC5C6C7); //custom color
    	
        graphic.setComposite(AlphaComposite.Clear);
        graphic.fillRect(120,140,200,40);
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
        
        final SplashScreen splash = SplashScreen.getSplashScreen();
        
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        
        Graphics2D graphic = splash.createGraphics();
        if (graphic == null) {
            System.out.println("g is null");
            return;
        }
        //stopWatch.start();
        if(ranBefore) {
        	//STEP 1
        	renderSplashFrame(graphic, "Checking for updates");
        	splash.update();
        	//CHECK FOR UPDATES
        	//Call Action in another thread
        	
        	//STEP 2
        	renderSplashFrame(graphic, "Starting program");
        	splash.update();
        	Front_End.createWindow(); //TODO: NEEDS TO BE CALLED IN ANOTHER THREAD, i think
        	
        	//ENDING, VIEW ATLEAST 3 SECS
        	/*stopWatch.suspend();
        	int stopWatchSec = Integer.parseInt(stopWatch.toString().substring(5, 7));
        	int stopWatchMill = Integer.parseInt(stopWatch.toString().substring(8, 11));

        	stopWatch.stop();
        	stopWatch.reset();
        	if(stopWatchSec < 03) {
        		System.out.println("in if");
        		int timeToWait = 3000 - ((stopWatchSec * 1000) + stopWatchMill);
        		System.out.println("time to wait " + timeToWait);
        		try {
        			System.out.println("in try sleep");
					Thread.sleep(timeToWait);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("in catch sleep");
				}
        		System.out.println("after try block");
        	}*/
        	

        	
        	
        }
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
        	renderSplashFrame(graphic, "Creating Logs");
        	splash.update();
        	//TODO: check if data base exists
        	//Call Action in another thread
        	
        	//STEP 4
        	renderSplashFrame(graphic, "Creating CopyLogs");
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

            	//ENDING, VIEW ATLEAST 3 SECS, NEEDS TO BE UDPATED: COPY FROM THIS IF STATEMENT 
            	/*stopWatch.suspend();
            	int stopWatchSec = Integer.parseInt(stopWatch.toString().substring(5, 7));
            	int stopWatchMill = Integer.parseInt(stopWatch.toString().substring(8, 11));

            	stopWatch.stop();
            	stopWatch.reset();
            	if(stopWatchSec < 03) {
            		System.out.println("in if");
            		int timeToWait = 3000 - ((stopWatchSec * 1000) + stopWatchMill);
            		System.out.println("time to wait " + timeToWait);
            		try {
            			System.out.println("in try sleep");
    					Thread.sleep(timeToWait);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    					System.out.println("in catch sleep");
    				}
            		System.out.println("after try block");
            	}*/
        	
        	
        }
        
        //FIXME: WAIT ATLEAST 3 SECS BEFORE OPENING PROGRAM
        
        /*for(int i=0; i<100; i++) {
            renderSplashFrame(graphic, i);
            splash.update();
            try {
                Thread.sleep(500);
            }
            catch(InterruptedException e) {
                
                System.out.println(e.getMessage());
            }
        }*/
        splash.close();
    }
}
}