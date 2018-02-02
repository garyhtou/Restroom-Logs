package mainProgram;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang.time.StopWatch;

import interfaces.RL_Fonts;
import mainProgram.BackEnd.database.Student.pullStudentName;
import mainProgram.FrontEnd.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		window.menuBar.preferences.create();
		
		//create();
	}
	
	public static void create() {
		config.checkRanBefore();
		splashScreen.create(); //will wait at least 3 seconds before moving on
		frame(); //set up main settings of the frame
		
		/*window.stuff;
		content.stuff;*/
		
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
		//public static splashScreen() {} //This is needed??? I don't know. it's an empty constructor
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
	        if(config.ranBefore) {
	        	regularStart(splash, graphic);
	        } else {
	        	initStart(splash, graphic);
	        }
	        splash.close();
	    }
	     private static void regularStart(SplashScreen splash, Graphics2D graphic) {
        	//STEP ONE
	    	renderSplashFrame(graphic, "Checking for updates");
	    	BackEnd.logs.update.StartUp("Checking for updates");
        	splash.update();
        	//CHECK FOR UPDATES
        	//Call Action in another thread
        	
        	//START PROGRAM
        	BackEnd.logs.update.StartUp("Opening Window");
        	waitThreeStart(splash, graphic); //makes sure you have been on start screen for 3 secs then run program BackEnd.logs.update.StartUp("Start Up Complete\n-----");
		        
	     }
	     private static void initStart(SplashScreen splash, Graphics2D graphic) {
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
	    
	    
	    /**
	     * this method only waits 3 seconds, don't start the window
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
	public static class window {
		public static class menuBar {
			public static class preferences extends JPanel{
				public static void create() {
					//JTabbedPane
					ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
					ImageIcon Icon = new ImageIcon("assets/images/RestroomLogsLogo.png");
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double screenWidth = screenSize.getWidth();
					double screenHeight = screenSize.getHeight();
					int displayWidth = (int) (screenWidth/1.3);
					int displayHeight = (int) (screenHeight/1.3);
					
					JTabbedPane tabbedPane = new JTabbedPane();
					
					String versNum = config.VersionNumber;
					Font f = tabbedPane.getFont();
					Font f2 = new Font(f.getFontName(), Font.BOLD, f.getSize()+15);
					
					//tabs
					preferences general = new preferences(tabbedPane, "General", null, "General Settings");
					
					
					
					JFrame frame = new JFrame();
					frame.setVisible(true);
					frame.add(tabbedPane);
				}
				
				/**
				 * Creates a new Tab in Preferences
				 * @param tabbedPane JTabbedPane used for holding these tabs
				 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
				 * @param icon Icon next to tab name
				 * @param toolTip tool tip for user's info
				 */
				public preferences(JTabbedPane tabbedPane, String nameOfTab, Icon icon, String toolTip) {
					JTextArea textArea = new JTextArea();
					textArea.setFont(RL_Fonts.Kollektif);
					textArea.setOpaque(false);
					textArea.setEditable(false);
					textArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
					textArea.setText(nameOfTab);
					this.add(textArea);
					tabbedPane.addTab(nameOfTab, icon, this, toolTip);
				}
			}
		}
		public static abstract class file {
			public abstract void create();
			public static class exit extends file {
				public void create() {
					
				}
			}
			
			public static class preferences extends file{
				public preferences() { //Constructor  for tabs and titles
					
				}
				public void create() {
					
				}
				public void content(){
					
				}
			}
		}
		
	}
	
	public static class content {
		public static class majorRL {
			public static void create() {
				JSplitPane majorRL = new JSplitPane();
			}
			public static class left {
				public static class statsScan {
					public static void create() {
						JSplitPane statsScan = new JSplitPane();
						majorRL.create();
					}
					public static class stats {
						public static class banner {
							public static void create() {
								
							}
						}
						public static class information {
							public static void create() {

							}
						}
					}
					public static class scan {
						static JPanel scan = new JPanel();
						public static class field {
							public static void create() {
								JTextField field = new JTextField();
						    	field.setEditable(true);
						    	field.setToolTipText("Scan your Student ID card");
						    	field.setBackground(Color.WHITE);
						    	field.setOpaque(true);
						    	
						    	//SCANNING
						    	field.addKeyListener(new KeyListener() {
						            @Override
						            public void keyTyped(KeyEvent e) {}

						            @Override
						            public void keyReleased(KeyEvent e) {}

						            @Override
						            public void keyPressed(KeyEvent escan) {
						            	String input = "";
						            	if(escan.getKeyChar() == KeyEvent.VK_ENTER) {
						            		//SCANING INFO-----------------------------------
						            		input = field.getText();
						            		EventQueue.invokeLater(() -> {
						            			field.setText("");
						                    });
						            		if(pullStudentName.containsOnlyNumbers(input)) {
						            			int intInput = Integer.parseInt(input);
						            			
						            			pullStudentName names = new pullStudentName(intInput);
												
												String FirstName = names.getFirstName();
						                    	String LastName = names.getLastName();
						                    	String FirstLastName = names.getBothNames();
						                    	
						                    	if((FirstName != null) && (LastName != null)) {
						                    		//FIXME: 
						                        	boolean inOrOut = BackEnd.database.Log.checkIfOut(intInput); //CURRENTLY SIGNED OUT??
						                        	
						                        	//adding to DB
					                        		BackEnd.database.Log.add.entry(intInput, FirstName, LastName, inOrOut);
						                        	
						                        	//Addedin to Logs.txt
						                        	if(!inOrOut) {
						                        		String data = FirstName + " " + LastName + " Signed Out";
						                        		updateMessagesSuccessfulSignOut(FirstLastName);
						                        		BackEnd.logs.update.Logs(data);
						                        	}
						                        	else {
						                        		String data = FirstName + " " + LastName + " Signed In";
						                        		updateMessagesSuccessfulSignIn(FirstLastName);
						                        		BackEnd.logs.update.Logs(data);
						                        	}
						                    	}
											
						            		}
						            		else {
						            			BackEnd.logs.update.Logs("\"" + input + "\"  is not an integer");
						            			
						            			//TODO: change popup to message in Message Pane
						            			JTextArea onlyInts = new JTextArea("Please only enter numbers");
						            			updateMessagesInteger(input);
						            		}
						            		
						            	}
						            }
						        });
							}
						}
						public static class messageCenter {
							public static void create() {
								JTextArea messageCenter = new JTextArea();
								messageCenter.setEditable(false);
							}
						}
					}
				}
			}
			public static class right {
				public static void table() {
					//https://stackoverflow.com/questions/39804819/how-to-display-data-from-ms-access-database-in-a-jtable
					//https://stackoverflow.com/questions/27679867/jtable-how-to-use-rs2xml
				}
			}
		}
		
	}
	
}
