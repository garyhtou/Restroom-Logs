package mainProgram;

import javax.swing.*;

import org.apache.commons.lang.time.StopWatch;

import mainProgram.FrontEnd.Window;

import java.awt.*;

/**
 * I am planning to completely rebuild front end by using
 * <ul>
 * <li>subclasses
 * <li>better naming
 * <li>redo wireframing
 * @author Gary Tou
 *
 */
public class FrontEndNew extends BackEnd /*implements ONE BIG INTERFACES WITH EVERYTHING*/{
	
	private static JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		create();
	}
	
	public static void create() {
		config.checkRanBefore();
		splashScreen.create(); //will wait at least 3 seconds before moving on
		frame(); //set up main settings of the frame
		
		window.stuff;
		content.stuff;
		
		frame.setVisible(true);
		
	}
	
	public static void frame() {		
		//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project
    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);
    	frame.setUndecorated(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static class splashScreen{
		
		private static StopWatch stopWatch = new StopWatch(); //used for 3 sec start
		
		public static void create() {
			BackEnd.logs.update.StartUp("Starting Program");
			BackEnd.logs.update.StartUp("Starting Splash Screen");
			runSplash();
		}
		//public splashScreen() {}
	    private static void renderSplashFrame(Graphics2D graphic, String message) {
	    	Color LAVENDER_GRAY = new Color(0xC5C6C7); //custom color
	    	
	    	graphic.setComposite(AlphaComposite.Clear); //Comment out this line to see where it will paint over
	        graphic.fillRect(170,140,250,40); //default: 120, 140, 200, 40
	        graphic.setPaintMode();
	        graphic.setColor(LAVENDER_GRAY);
	        graphic.drawString(message+"...", 200, 165);
	    }
	    
	    
	    private static void runSplash() {
	    	SplashScreen splash = SplashScreen.getSplashScreen();
	        if (splash == null) {
	            String message = "SplashScreen.getSplashScreen() returned null";
	        	System.out.println(message);
	            BackEnd.logs.update.ERROR(message);
	            return;
	        }
	        
	        
	        Graphics2D graphic = splash.createGraphics();
	        if (graphic == null) {
	            System.out.println("graphic is null");
	            BackEnd.logs.update.ERROR("graphic is null");
	            return;
	        }
	        
	        stopWatch.start();
	//FIXME: move ranbefore vs not ranbefore to different methods and all the two methods inside the if/else
	//STEPS ---------------------------------------
	        if(config.ranBefore) {
	        	//Step 1
	        	renderSplashFrame(graphic, "Checking for updates");
	        	BackEnd.logs.update.StartUp("Checking for updates");
	        	splash.update();
	        	//CHECK FOR UPDATES
	        	//Call Action in another thread
	        	
	        	//START PROGRAM
	        	BackEnd.logs.update.StartUp("Opening Window");
	        	waitThreeStart(splash, graphic); //makes sure you have been on start screen for 3 secs then run program
	        	BackEnd.logs.update.StartUp("Start Up Complete\n-----");
	        }
	//STEP INIT -------------------------------------
	        else { //INIT START UP, HAS NOT RAN BEFORE
	        	//STEP 1
	        	renderSplashFrame(graphic, "NOTICE: Program hasn't ran before!");
	        	BackEnd.logs.update.StartUp("NOTICE: Program hasn't ran before!");
	        	splash.update();
	        	//no need to call anything, this is a notice to use that this program has not ran before
	        	
	        	//STEP 2
	        	renderSplashFrame(graphic, "Checking database");
	        	BackEnd.logs.update.StartUp("Checking database");
	        	splash.update();
	        	//TODO: check if data base exists
	        	//Call Action in another thread
	        	
	        	//STEP 3
	        	renderSplashFrame(graphic, "Creating PDF Logs");
	        	BackEnd.logs.update.StartUp("Creating PDF Logs");
	        	splash.update();
	        	BackEnd.email.PDF.CreateBlankPDF();
	        	//Call Action in another thread
	        	
	        	//changes ranBefore=false to ranBefore=true
	        	ranBeforeToTrue();
	        	
	        	//START PROGRAM
	        	BackEnd.logs.update.StartUp("Opening Window");
	        	waitThreeStart(splash, graphic);
	        	BackEnd.logs.update.StartUp("Start Up Complete\n-----");
	        }
	        splash.close();
	    }
	    
	    /**
	     * this method only waits 3 secounds, don't start the window
	     * @param splash
	     * @param graphic
	     */
	    private static void waitThreeStart(SplashScreen splash, Graphics2D graphic) {
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
	    }
	}
	public class window {
		public void menuBar() {
			
		}
		public class file {
			public class exit {
				public void create() {
					
				}
			}
			
			public class preferences {
				public preferences() { //Constructor  for tabs and titles
					
				}
				public void create() {
					
				}
				public void content(){
					
				}
			}
		}
		
	}
	
	public class content {
		public void pane() {
			
		}
		public void scan() {
			
		}
		public void messages() {
			
		}
		public void table() {
			
		}
		public void stats() {
			
		}
		
	}
	
}
