package mainProgram;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang.time.StopWatch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class FrontEnd extends BackEnd{
	
	public static JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		//window.menuBar.preferences.create();
		
		create();
	}
	
	public static void create() {
	//Window
		frame(); //set up main settings of the frame
		
	//Menu Bar
		window.menuBar.create();
		window.menuBar.file.create();
		window.menuBar.file.exit.create();
		window.menuBar.file.preferences.create();
		
		content.majorRL.create();
	//Major Left
		content.majorRL.left.create();
		content.majorRL.left.statsScan.create();
		content.majorRL.left.statsScan.scanAndMessages.create();		
		content.majorRL.left.statsScan.scanAndMessages.scan.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.field.create();	
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.create();
		content.majorRL.left.statsScan.stats.create();
		content.majorRL.left.statsScan.stats.banner.create();
		content.majorRL.left.statsScan.stats.information.create();
		
	//Major Right
		content.majorRL.right.create();
		content.majorRL.right.table.create();
		content.majorRL.right.table.titleBar.create();
		content.majorRL.right.table.titleBar.title.create();
		content.majorRL.right.table.titleBar.clearButton.create();
		content.majorRL.right.table.tableContent.create();
		
		
		
	//final changes
		frame.setVisible(true);
		
		
		content.majorRL.majorRL.setDividerLocation((double)1);
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
	    	FrontEnd.create();
	    }
	}
	public static class window {
		public static class menuBar {
			static JMenuBar menuBar = new JMenuBar();
			public static void create() {
				frame.setJMenuBar(menuBar);
			}
			public static class file {
				static JMenu fileMenu = new JMenu("File"); 
				public static void create() {
					menuBar.add(fileMenu);
			        fileMenu.setMnemonic(KeyEvent.VK_F);
				}
				public static class exit {
					public static void create() {
						JMenuItem fileExit = new JMenuItem("Exit", fileExitIcon); //creates dropdown item "Exit" and it's icon to hte File dropdown
						fileMenu.add(fileExit);
				        fileExit.setMnemonic(KeyEvent.VK_E); //short cut for exit
				        fileExit.setToolTipText("Exit application");
				        
				        fileExit.addActionListener((ActionEvent exitButtonEvent) -> { //When clicked, exit
				        	BackEnd.logs.update.System("EXIT");
				        	BackEnd.logs.write("----------");
				            System.exit(0);
				        });
					}
				}
				public static class preferences extends JPanel{
					static JMenuItem filePreferences = new JMenuItem("Preferences, filePreferencesIcon");
					public static void create() {
						fileMenu.add(filePreferences);
						filePreferences.setMnemonic(KeyEvent.VK_P);
					    filePreferences.setToolTipText("Preferences");
						filePreferences.addActionListener((ActionEvent exitButtonEvent) -> {
							BackEnd.logs.update.Logs("Preferences Opened");
							content();
				        });
					}
					public static void content() {
						//JTabbedPane
						JTabbedPane tabbedPane = new JTabbedPane();
						int displayWidth = (int) (screenWidth/1.3);
						int displayHeight = (int) (screenHeight/1.3);
						Dimension displayDimension = new Dimension(displayWidth, displayHeight); 
						tabbedPane.setPreferredSize(displayDimension);
						
						String versNum = config.VersionNumber;
						Font f = tabbedPane.getFont();
						Font f2 = new Font(f.getFontName(), Font.BOLD, f.getSize()+15);
						
						//tabs
						preferences general = new preferences(tabbedPane, "General", null, "General Settings");
					
						preferences logs = new preferences(tabbedPane, "Logs", null, "Logs Settings");
							JTextArea clearLogText = new JTextArea();
								clearLogText.setOpaque(false);
								clearLogText.setEditable(false);
								clearLogText.setText("Clear PDF Log file");
								logs.add(clearLogText);
							JButton clearLogTextbutton = new JButton("Clear PDF");
								clearLogTextbutton.setToolTipText("Caution, this will clear all student entry and exit logs");
								clearLogTextbutton.addActionListener((ActionEvent preferencesLogsClearButtonEvent) -> {
									int choice = JOptionPane.showConfirmDialog(logs, "Are you sure you want to clear the PDF Log file?\nThis will clear all student entry and exit logs");
									if(choice == JOptionPane.YES_OPTION) {
										BackEnd.email.PDF.CreateBlankPDF();
										BackEnd.logs.update.System("LogsPDF Cleared");
										JOptionPane.showMessageDialog(logs, "PDF Log has been cleared");
									}
								});
							
						preferences about = new preferences(tabbedPane, "About", null, "About this program");
							
						
					
						
						JOptionPane.showMessageDialog(null, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
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
						textArea.setFont(RL.Kollektif);
						textArea.setOpaque(false);
						textArea.setEditable(false);
						textArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
						textArea.setText(nameOfTab);
						this.add(textArea);
						tabbedPane.addTab(nameOfTab, icon, this, toolTip);
					}
				}
			}

			
		}
		
	}
	
	public static class content {
		public static class majorRL {
			static JSplitPane majorRL = new JSplitPane();
			public static void create() {
				frame.add(majorRL);
			}
			public static class left {
				static JPanel left = new JPanel(new BorderLayout());
				public static void create() {
					majorRL.setLeftComponent(left);
				}
				public static class statsScan {
					static JPanel statsScan = new JPanel(new BorderLayout());
					public static void create() {
						left.add(statsScan, BorderLayout.PAGE_START);
						
					}
					public static class stats {
						static JPanel stats = new JPanel(new BorderLayout());
						public static void create() {
							statsScan.add(stats, BorderLayout.PAGE_START);
						}
						public static class banner {
							static JLabel banner = new JLabel();
							public static void create() {
								ImageIcon RestroomLogsLogoWideBanner = new ImageIcon("assets/logos/RestroomLogsWideBanner.png");
							    //find aspect ratios
							    double orginalHeight = RestroomLogsLogoWideBanner.getIconHeight();
							    double orginalWidth = RestroomLogsLogoWideBanner.getIconWidth();
							    double aspectRatio = orginalWidth/orginalHeight;
							    //find new dimensions
								final double HEIGHT_ASPECT_RATIO_MULTIPLIER = 0.15;
							    int newHeight = (int) (screenHeight * HEIGHT_ASPECT_RATIO_MULTIPLIER);
							    int newWidth = (int) (newHeight * aspectRatio);
							    //resize image
							    Image img = RestroomLogsLogoWideBanner.getImage() ;  
							    Image newimg = img.getScaledInstance( newWidth, newHeight,  java.awt.Image.SCALE_SMOOTH ) ;  
							    ImageIcon RestroomLogsLogoWideBannerResized = new ImageIcon( newimg );
							    banner.setIcon(RestroomLogsLogoWideBannerResized);
							    GridBagConstraints bannerConstraints = new GridBagConstraints();
							    bannerConstraints.gridx = 1;
							    bannerConstraints.gridy = 1;
							    bannerConstraints.weightx = 0.0;
							    bannerConstraints.weighty = 1.0;
							    bannerConstraints.anchor = GridBagConstraints.WEST;
							    bannerConstraints.fill = GridBagConstraints.VERTICAL;
								
								stats.add(banner, BorderLayout.LINE_START);
							}
						}
						public static class information {
							static JLabel information = new JLabel();
							public static void create() {
								//todo:
								String message = "we need to make a var for teacher name and other info that will go here";
								
								stats.add(information, BorderLayout.LINE_END);
							}
							public static void update(String teacherName) { //todo: add more info
								
							}
						}
					}
					public static class scanAndMessages {
						static JSplitPane scanAndMessages = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
						public static void create() {
							statsScan.add(scanAndMessages);
						}
						public static class scan {
							static JPanel scan = new JPanel(new GridBagLayout());
							public static void create() {
								scanAndMessages.setTopComponent(scan);
							}
							public static class field {
								public static void create() {
									JTextField field = new JTextField();
							    	field.setEditable(true);
							    	field.setBackground(Color.WHITE);
							    	//field.setMinimumSize(new Dimension(220, 220));
							    	field.setOpaque(true);
							    	
							    	GridBagConstraints  c = new GridBagConstraints ();
							    	c.weightx = 0.9;
							    	c.weighty = 0.9;
							    	c.fill = GridBagConstraints.BOTH;
							    	
							    	scan.add(field, c);
							    	
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
							            		input = field.getText();
							            		EventQueue.invokeLater(() -> {
							            			field.setText("");
							                    });
							            		if(mainProgram.BackEnd.database.Student.pullStudentName.containsOnlyNumbers(input)) {
							            			int intInput = Integer.parseInt(input);
							            			
							            			String FirstName = BackEnd.database.Student.pullStudentName.firstName(intInput);
							                    	String LastName = BackEnd.database.Student.pullStudentName.lastName(intInput);
							                    	String FirstLastName = BackEnd.database.Student.pullStudentName.bothNames(intInput);
							                    	
							                    	if((FirstName != null) && (LastName != null)) {
							                    		boolean signingIn = BackEnd.database.Log.checkIfOut(intInput);
							                    		
							                        	//adding to DB
						                        		BackEnd.database.Log.add.entry(intInput, FirstName, LastName, signingIn);
						                        		
							                        	//Showing scan status in messages
							                        	if(!signingIn) {
							                        		messageCenter.scanEntryMessage.successfulSignOut(FirstLastName);
							                        	}
							                        	else {
							                        		messageCenter.scanEntryMessage.successfulSignIn(FirstLastName);
							                        	}
							                    	} else {
							                    		messageCenter.scanEntryMessage.unsuccessful(intInput);
							                    	}
												
							            		}
							            		else {
							            			messageCenter.scanEntryMessage.integer(input);
							            		}
							            	}
							            }
							        });
								}
							}
							public static class messageCenter {
								static JPanel messageCenter = new JPanel(new GridBagLayout());
								public static void create() {
									scanAndMessages.setBottomComponent(messageCenter);
								}
								public static class scanEntryMessage {
									static JLabel message = new JLabel();
									public static void create() {
										message.setText("Welcome to the Restroom Logs Program!");
										message.setHorizontalAlignment(SwingConstants.CENTER);
										GridBagConstraints c = new GridBagConstraints();
										c.gridx = 0;
										c.gridy = 0;
										c.fill = GridBagConstraints.HORIZONTAL;
										messageCenter.add(message, c);
									}
									public static void successfulSignIn(String firstLastName) {
										String outputMessage = firstLastName + " has signed in";
										message.setForeground(Color.GREEN);
										message.setText(outputMessage);
										BackEnd.logs.update.Logs(outputMessage);
									}
									public static void successfulSignOut(String firstLastName) {
										String outputMessage = firstLastName + " has signed out";
										message.setForeground(Color.GREEN);
										message.setText(outputMessage);
										BackEnd.logs.update.Logs(outputMessage);
									}
									public static void unsuccessful(int studentID) {
										message.setForeground(Color.RED);
										message.setText("Invalid Student ID number: " + studentID);
										BackEnd.logs.update.Logs("Could not find " + studentID + " in Student Data Base");
									}
									public static void integer(String input) {
										message.setForeground(Color.RED);
										message.setText("Please only enter numbers");
										BackEnd.logs.update.Logs(input + " is not an integer");
									}
									public static void manualSignIn() {
										message.setForeground(Color.BLACK);
										message.setText("All student have been manually signed in");
									}
								}
								public static class separator {
									public static void create() {
										GridBagConstraints c = new GridBagConstraints();
										c.gridx = 0;
										c.gridy = 1;
										JSeparator sep = new JSeparator(); //FIXME: I don't think the JSeperator is visible... i can't see it
										messageCenter.add(sep, c);
									}
								}
								public static class otherMessages {
									static JTextArea otherMessages = new JTextArea();
									public static void create() {
										otherMessages.setText("OTHER MESSAGES HERE!!!");
										GridBagConstraints c = new GridBagConstraints();
										c.gridx = 0;
										c.gridy = 2;
										c.weightx = 1;
										c.weighty = 1;
										c.fill = GridBagConstraints.BOTH;
										messageCenter.add(otherMessages, c);
									}
									public static void update(String message) {
										otherMessages.setText(message);
									}
								}
							}
						}
					}//scan and messages
				}
			}
			public static class right {
				static JPanel right = new JPanel(new BorderLayout());
				public static void create() {
					majorRL.setRightComponent(right);
				}
				public static class table {
					static JPanel table = new JPanel(new BorderLayout());
					public static void create() {
						right.add(table, BorderLayout.CENTER);
					}
					public static class titleBar {
						static JPanel titleBar = new JPanel(new BorderLayout());
						public static void create() {
							table.add(titleBar, BorderLayout.PAGE_START);
						}
						public static class title {
							static JLabel title = new JLabel();
							public static void create() {
								title.setText("Student Signed Out");
								titleBar.add(title, BorderLayout.LINE_START);
							}
						}
						public static class clearButton {
							static JButton clear = new JButton();
							public static void create() {
								clear.setText("clear");
								clear.setToolTipText("Signed all students back in");
								
								clear.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e) {
										if(BackEnd.database.Log.table.signAllIn()) {
											content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.manualSignIn();
											JOptionPane.showMessageDialog(frame, "Successfully signed in all students.", "Restroom Logs", JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(frame, "An Interal Error occured.", "Restroom Logs Error", JOptionPane.ERROR_MESSAGE);
										}
									}
								});
								
								titleBar.add(clear, BorderLayout.LINE_END);
							}
						}
					}
					public static class tableContent {
						static JTable tableContent = new JTable();
						public static void create() {
							
						//THIS IS A PALCE HOLDER
							String[] columnNames = {"First Name",
			                        "Last Name",
			                        "Time Out"};
							Object[][] data = {
							    {"Kathy", "Smith", "10:50"},
							    {"John", "Doe", "10:50"},
							    {"Sue", "Black", "10:50"},
							    {"Jane", "White", "10:50"},
							    {"Joe", "Brown", "10:50"}
							};
							tableContent = new JTable(data, columnNames);
						//ENDING PLACE HOLDER
							
							//tableContent.
							
							
							
							
							table.add(tableContent);
						}
					}
				}
			}
		}
		
	}
	
}
