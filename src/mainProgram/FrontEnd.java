package mainProgram;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang.time.StopWatch;

import com.teamdev.jxdocument.SinglePagePresentation;

import interfaces.RL_Colors;
import interfaces.RL_Fonts;
import interfaces.RL_General;

public class FrontEnd {

	public static class Splash{
	
		static StopWatch stopWatch = new StopWatch();
		
		public static void init() {
			logs.updateLogsStartUp("Starting Program");
			logs.updateLogsStartUp("Starting Splash Screen");
			runSplash();
		}
		public Splash() {}
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

	public static class Window{
		public static void createWindow() {
	    	EventQueue.invokeLater(() -> {
	        	initUI();
	        });
	    }
	    
	    
	    
		private Window() { //this is a constructor, it calls a method to give the window settings
	        initUI();
	    }
	    private static void initUI() { //window settings
	    	JFrame frame = new JFrame();
	    	
	    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
	    	ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project
	    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
	    	
	        //WINDOW SETTINGS
	    	frame.setTitle("Restroom Logs");
	    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    	frame.setResizable(false);
	    	frame.setUndecorated(true);
	    	frame.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
	        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	        //setAlwaysOnTop(true); //DO NOT USE THIS, THIS WILL PREVENT DIALOG FROM TOPPING THIS
	    	
	    	//MENU BAR
	    	frame.setJMenuBar(MenuBar.createMenuBar());
	    	
	    	//CONTENT (adding content to JFrame)
	    	Window_Content.content(frame);
	    	
	    	frame.setVisible(true);
	    	frame.setAlwaysOnTop(true); //jumps to top
	    	frame.setAlwaysOnTop(false); //allows pop ups to show above frame
	    }
	}

	public static class Window_Content implements RL_Colors, RL_Fonts, RL_General{
		static String WebisteBaseUrl = config.WebsiteHomeURL;
		static JEditorPane messageContent = new JEditorPane();
		
		
	    public static Object content(JFrame frame) {
	    	frame.setVisible(true);
	    	Container pane = frame.getContentPane();
	    	
	    	JSplitPane MajorLeftAndRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    	pane.add(MajorLeftAndRight, BorderLayout.CENTER);
	    	
	    	//-----------
	    	JPanel TitleBarAndScanAndMessage = new JPanel();
	    	TitleBarAndScanAndMessage.setLayout(new GridBagLayout());
	    	MajorLeftAndRight.setLeftComponent(TitleBarAndScanAndMessage);
	    	
	    	//-----------
	    	JPanel TitleBar = new JPanel(new GridBagLayout());
	    	TitleBar.setOpaque(true);
	    	TitleBar.setBackground(Color.WHITE);
	    	GridBagConstraints TitleBarConstraints = new GridBagConstraints();
	    	TitleBarConstraints.gridx = 1;
	    	TitleBarConstraints.gridy = 1;
	    	TitleBarConstraints.weightx = 1.0;
	    	TitleBarConstraints.weighty = 0.0;
	    	TitleBarConstraints.fill = GridBagConstraints.HORIZONTAL;
	    	TitleBarAndScanAndMessage.add(TitleBar, TitleBarConstraints);

	    	//BANNER
	    	JLabel banner = new JLabel();
		    ImageIcon RestroomLogsLogoWideBanner = new ImageIcon("assets/logos/RestroomLogsWideBanner.png");
		    //find aspect ratios
		    double orginalHeight = RestroomLogsLogoWideBanner.getIconHeight();
		    double orginalWidth = RestroomLogsLogoWideBanner.getIconWidth();
		    double aspectRatio = orginalWidth/orginalHeight;
		    //find screen size
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double screenWidth = screenSize.getWidth(); //NOT USED
			double screenHeight = screenSize.getHeight();
		    //find new dimensions
			final double HEIGHT_ASPECT_RATIO_MULTIPLIER = 0.15;
		    int newHeight = (int) (screenHeight * HEIGHT_ASPECT_RATIO_MULTIPLIER);
		    int newWidth = (int) (newHeight * aspectRatio);
		    //resize image
		    Image img = RestroomLogsLogoWideBanner.getImage() ;  
		    Image newimg = img.getScaledInstance( newWidth, newHeight,  java.awt.Image.SCALE_SMOOTH ) ;  
		    ImageIcon RestroomLogsLogoWideBannerResized = new ImageIcon( newimg );
		    banner.setIcon(RestroomLogsLogoWideBannerResized);
		    //banner.setHorizontalAlignment(SwingConstants.LEFT);
		    GridBagConstraints bannerConstraints = new GridBagConstraints();
		    bannerConstraints.gridx = 1;
		    bannerConstraints.gridy = 1;
		    bannerConstraints.weightx = 0.0;
		    bannerConstraints.weighty = 1.0;
		    bannerConstraints.anchor = GridBagConstraints.WEST;
		    bannerConstraints.fill = GridBagConstraints.VERTICAL;
		    
	    	TitleBar.add(banner, bannerConstraints);
		    
		    //TEST------
		    banner.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		    TitleBar.setBorder(BorderFactory.createLineBorder(RL_Colors.color(4)));
		    
	    	//STATS
		    JPanel stats = new JPanel(new BorderLayout());
		    //TEACHER NAME
		    JEditorPane teacherName = new JEditorPane();
		    String teacherNameData = "Mr. Sabo";
		    teacherName.setEditable(false);
		    teacherName.setContentType("text/hteml");
		    teacherName.setText(teacherNameData);
		    teacherName.setFont(new Font("Verdana", Font.BOLD, teacherName.getFont().getSize()+20));
		    //teacherName.setFont(RL_Fonts.statsTeacherNameDynamic(teacherName));
		    teacherName.setForeground(RL_Colors.color("DARK_GUNMETAL"));
		    stats.add(teacherName, BorderLayout.PAGE_START);
		    
	    	//TIME AND DATE
		    //FIXME: PROBLEM, IT'S SLEEPING THE THREAD!!!
		    Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
		    String timeStamp = "Current Date: " + dateFormat.format(date);
		    JEditorPane timeAndDate = new JEditorPane();
		    timeAndDate.setText("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		    timeAndDate.setContentType("text/html");
		    timeAndDate.setEditable(false);
			    ActionListener actionListener = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	Date date = new Date();
			            SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
			            String timeStamp = "Current Date: " + dateFormat.format(date);
			            timeAndDate.setText("<html><p>"+timeStamp+"<p></htlm>");
			            //System.out.println(timeStamp);
			        }
			    };
			Timer timer = new Timer(1000, actionListener);
			timer.start();
			timeAndDate.setBorder(BorderFactory.createLineBorder(RL_Colors.color(4)));
			stats.add(timeAndDate, BorderLayout.CENTER);
			
			//STATS
		    GridBagConstraints statsConstraints = new GridBagConstraints();
		    statsConstraints.gridx = 2;
		    statsConstraints.gridy = 1;
		    statsConstraints.weightx = 1.0;
		    statsConstraints.weighty = 1.0;
		    statsConstraints.fill = GridBagConstraints.VERTICAL;
		    statsConstraints.anchor = GridBagConstraints.WEST;
	    	TitleBar.add(stats, statsConstraints);
	    	
	    	//-----------
	    	JSplitPane ScanAndMessage = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    	GridBagConstraints ScanAndMessageConstraints = new GridBagConstraints();
	    	ScanAndMessageConstraints.gridx = 1;
	    	ScanAndMessageConstraints.gridy = 2;
	    	ScanAndMessageConstraints.weightx = 1.0;
	    	ScanAndMessageConstraints.weighty = 1.0;
	    	ScanAndMessageConstraints.fill = GridBagConstraints.BOTH;
	    	TitleBarAndScanAndMessage.add(ScanAndMessage, ScanAndMessageConstraints);
	    	
	    	//-----------
	    	JPanel scan = new JPanel(new BorderLayout());
	    	scan.setOpaque(true);
	    	scan.setBackground(RL_Colors.color(3));
	    	/*//SCAN TITLE
	    	JLabel scanTitle = new JLabel();
	    	scanTitle.setText("Scan your Student ID card below using the barcode scanner");
	    	scanTitle.setFont(new Font("Serif", Font.BOLD, scanTitle.getFont().getSize() + 10));
	    	scan.add(scanTitle, BorderLayout.PAGE_START);*/
	    	//SCAN FIELD
	    	//TODO: make typeable from focused window
	    	JTextField scanField = new JTextField();
	    	scanField.setEditable(true);
	    	scanField.setToolTipText("Scan your Student ID card");
	    	scanField.setBackground(Color.WHITE);
	    	scanField.setOpaque(true);
	    	
	    	//border
	    	Border scanFieldBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Scan your Student ID card below using the barcode scanner", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, RL_Fonts.AnticSlab, Color.BLACK);
	    	scanField.setBorder(scanFieldBorder);
	    	
	    	//scan font
	    	scanField.setFont(RL_Fonts.AnticSlab.deriveFont(150f)); //MAKE SURE IT'S A FLOAT!!!
	    	
		    	System.out.println("");
		    	System.out.println(scanField.getFont());
		    	System.out.println(scanField.getPreferredSize());
		    	System.out.println(scanField.getSize());
		    	
		    	System.out.println("");
		    	System.out.println(scan.getPreferredSize());
		    	System.out.println(scan.getSize());
	    	
	    	//scanField size
	    	scan.setSize(scan.getPreferredSize());
	    	scan.add(scanField, BorderLayout.PAGE_START);
	    	ScanAndMessage.setTopComponent(scan);
	    	
	    	
	    	
	    	//SCANNING
	    	//text field
	    	scanField.addKeyListener(new KeyListener() {

	            @Override
	            public void keyTyped(KeyEvent e) {}

	            @Override
	            public void keyReleased(KeyEvent e) {}

	            @Override
	            public void keyPressed(KeyEvent escan) {
	            	String input = "";
	            	if(escan.getKeyChar() == KeyEvent.VK_ENTER) {
	            		//SCANING INFO-----------------------------------
	            		input = scanField.getText();
	            		EventQueue.invokeLater(() -> {
	            			scanField.setText("");
	                    });
	            		if(pullStudentName.containsOnlyNumbers(input)) {
	            			int intInput = Integer.parseInt(input);
	            			
	            			pullStudentName names;
							names = new pullStudentName(intInput);
							
							String FirstName = names.getFirstName();
	                    	String LastName = names.getLastName();
	                    	String FirstLastName = names.getBothNames();
	                    	
	                    	if((FirstName != null) && (LastName != null)) {
	                    		//FIXME: 
	                        	boolean inOrOut = true; //CURRENTLY SIGNED OUT??
	                        	//adding to DB
	                        	logs.addEntryToLogDB(intInput, FirstName, LastName, inOrOut);
	                        	
	                        	//Addedin to Logs.txt
	                        	if(inOrOut) {
	                        		String data = FirstName + " " + LastName + " Signed Out";
	                        		updateMessagesSuccessfulSignOut(FirstLastName);
	                        		logs.updateLogs(data);
	                        	}
	                        	else {
	                        		String data = FirstName + " " + LastName + " Signed In";
	                        		updateMessagesSuccessfulSignIn(FirstLastName);
	                        		logs.updateLogs(data);
	                        	}
	                    	}
						
	            		}
	            		else {
	            			logs.updateLogs("\"" + input + "\"  is not an integer");
	            			
	            			//TODO: change popup to message in Message Pane
	            			JTextArea onlyInts = new JTextArea("Please only enter numbers");
	            			updateMessagesInteger(input);
	            		}
	            		
	            	}
	            }
	        });
	    	
	    	
	    	
	    	//-----------
	    	JPanel message = new JPanel(new GridBagLayout());
	    	
	    	JEditorPane messageTitle = new JEditorPane();
	    	messageTitle.setContentType("text/html");
	    	messageTitle.setText("<html><center><h1>MESSAGES</h1></center></html>");
	    	messageTitle.setEditable(false);
	    	messageTitle.setBorder(BorderFactory.createLineBorder(RL_Colors.color(4)));
	    	GridBagConstraints messageTitleConstraints = new GridBagConstraints();
	    	messageTitleConstraints.gridx = 1;
	    	messageTitleConstraints.gridy = 1;
	    	messageTitleConstraints.weightx = 1.0;
	    	messageTitleConstraints.weighty = 0.0;
	    	messageTitleConstraints.fill = GridBagConstraints.BOTH;
	    	message.add(messageTitle, messageTitleConstraints);
	    	
		//TO DISPLAY WEBSITE
	    	/*try {
		    	String url = WebisteBaseUrl+"logs/messages.html";
				messageContent.setPage(url); //HAS NO CSS
			} catch (IOException e) {
				messageContent.setText("<html>Could not load message from "+url);
				e.printStackTrace();
			}*/
	    	messageContent.setEditable(false);
	    	//messageContent.setFont(new Font("Verdana", Font.CENTER_BASELINE, teacherName.getFont().getSize()));
	    	messageContent.setFont(RL_Fonts.AnticSlab.deriveFont(50f));
	    	//messageContent.setAlignmentY(messageContent.CENTER_ALIGNMENT);
	    	messageContent.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	//messageContent
	    	
	    	GridBagConstraints messageContentConstraints = new GridBagConstraints();
	    	messageContentConstraints.gridx = 1;
	    	messageContentConstraints.gridy = 2;
	    	messageContentConstraints.weightx = 1.0;
	    	messageContentConstraints.weighty = 0.5;
	    	messageContentConstraints.fill = GridBagConstraints.BOTH;
	    	message.add(messageContent, messageContentConstraints);
	    	
	    	ScanAndMessage.setBottomComponent(message);
	    	
	    	//----------------
	    	JPanel TableAndTableTitleAndTableClearButton = new JPanel(new GridBagLayout());
	    	MajorLeftAndRight.setRightComponent(TableAndTableTitleAndTableClearButton);
	    	
	    	//TITLE AND BUTTON
	    	JPanel TableTitleAndTableClearButton = new JPanel(new GridBagLayout());
	    	GridBagConstraints TableTitleAndTableClearButtonConstraints = new GridBagConstraints();
	    	TableTitleAndTableClearButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, RL_Colors.color(4)));
	    	TableTitleAndTableClearButtonConstraints.gridx = 1;
	    	TableTitleAndTableClearButtonConstraints.gridy = 1;
	    	TableTitleAndTableClearButtonConstraints.weightx = 1.0;
	    	TableTitleAndTableClearButtonConstraints.weighty = 0.0;
	    	TableTitleAndTableClearButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
	    	TableAndTableTitleAndTableClearButton.add(TableTitleAndTableClearButton, TableTitleAndTableClearButtonConstraints);
	    	
	    	//TABLE
	    	JScrollPane TableScroll = new JScrollPane();
	    	JTable Table = new JTable();
	    	//Table.IDKKKKK
	    	TableScroll.add(Table);
	    	GridBagConstraints TableScrollConstraints = new GridBagConstraints();
	    	TableScrollConstraints.gridx = 1;
	    	TableScrollConstraints.gridy = 2;
	    	TableScrollConstraints.weightx = 1.0;
	    	TableScrollConstraints.weighty = 1.0;
	    	TableScrollConstraints.fill = GridBagConstraints.BOTH;
	    	TableAndTableTitleAndTableClearButton.add(TableScroll, TableScrollConstraints);
	    	
	    	//TABLE TITLE
	    	JTextArea TableTitle = new JTextArea();
	    	TableTitle.setEditable(false);
	    	TableTitle.setText("Signed Out");
	    	Insets TableTitleMargin = new Insets(1, 1, 1, 1);
	    	TableTitle.setMargin(TableTitleMargin);
	    	GridBagConstraints TableTitleConstraints = new GridBagConstraints();
	    	TableTitleConstraints.gridx = 1;
	    	TableTitleConstraints.gridy = 1;
	    	TableTitleConstraints.weightx = 1.0;
	    	TableTitleConstraints.weighty = 0.0;
	    	//TableTitleConstraints.fill = GridBagConstraints.HORIZONTAL;
	    	TableTitleAndTableClearButton.add(TableTitle, TableTitleConstraints);
	    	
	    	//TABLE CLEAR BUTTON
	    	JButton TableClearButton = new JButton("Clear");
	    	Insets TableClearButtonMargin = new Insets(1, 1, 1, 1);
	    	TableClearButton.setMargin(TableClearButtonMargin);
	    	//LARGER PREFERRED SIZE
	    	Dimension TableClearButtonDimension = TableClearButton.getPreferredSize();
	    	double TableClearButtonDimensionHEIGHT = TableClearButtonDimension.getHeight() + 5;
	    	double TableClearButtonDimensionWIDTH = TableClearButtonDimension.getWidth() + 5;
	    	Dimension NEWTableClearButtonDimension = new Dimension((int)TableClearButtonDimensionWIDTH, (int)TableClearButtonDimensionHEIGHT);
	    	TableClearButton.setPreferredSize(NEWTableClearButtonDimension);
	    	//EVENT LISTENER
	    	TableClearButton.addActionListener((ActionEvent ClearButtonEvent) -> { //When clicked, exit
	            /*TODO:CLEAR!!!!*/
	        });
	    	GridBagConstraints TableClearButtonConstraints = new GridBagConstraints();
	    	TableClearButtonConstraints.gridx = 2;
	    	TableClearButtonConstraints.gridy = 1;
	    	TableClearButtonConstraints.weightx = 0.0;
	    	TableClearButtonConstraints.weighty = 0.0;
	    	//TableClearButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
	    	TableTitleAndTableClearButton.add(TableClearButton, TableClearButtonConstraints);
	    	
	    	//DIVIDER LOCATIONS
	    	RL_General.JSplitPaneDividerLocation(frame, MajorLeftAndRight, 0.80);
	    	RL_General.JSplitPaneDividerLocation(frame, ScanAndMessage, 0.15);
	    	
	    	ActionListener focusActionListener = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	scanField.requestFocus();
		        }
		    };
		    Timer scanFieldTimer = new Timer(1000, focusActionListener);
		    scanFieldTimer.start();
	    	
	    	return MajorLeftAndRight;
	    }
	 
	    /**
		 * Adding successful sign out message to Messages
		 * @param FirstAndLastName Student's First and Last Name
		 */
		public static void updateMessagesSuccessfulSignOut(String FirstAndLastName) {
			String message  = FirstAndLastName + " has Signed Out";
			messageContent.setForeground(Color.GREEN);
			messageContent.setText(message);
		}
		/**
		 * Adding successful sign out message to Messages
		 * @param FirstAndLastName Student's First and Last Name
		 */
		public static void updateMessagesSuccessfulSignIn(String FirstAndLastName) {
			String message  = FirstAndLastName + " has Signed In";
			messageContent.setForeground(Color.GREEN);
			messageContent.setText(message);
		}
		/**
		 * Adding can not find Student ID to Messages
		 * @param StudentID StudentID
		 */
		public static void updateMessagesUnsuccessful(int StudentID) {
			String message = "Student ID: " + "\"" + StudentID + "\"" + " could not be found";
			messageContent.setForeground(Color.RED);
			messageContent.setText(message);
		}
		/**
		 * Adding can not find Student ID to Messages
		 * @param FirstAndLastName Student's First and Last Name
		 */
		public static void updateMessagesInteger(String input) {
			messageContent.setForeground(Color.RED);
			messageContent.setText("Please only enter Numbers, \"" + input + "\" contains letters");
		}
		
		
		
	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	JFrame frame = new JFrame();
	                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	                content(frame);
	            }
	        });
	    }
	}   

	public static class MenuBar {
		static String LogsPath = config.LogsPath;
		public static JMenuBar createMenuBar() { //JmenuBar = Menu Bar, JMenu = new dropdown, JMunuItem = item in a dropdown
	    	
	    	//MENUBAR
	        JMenuBar menubar = new JMenuBar();
	        //LOG FILE
	        
	        
	        //ICONS
	        ImageIcon fileExitIcon = new ImageIcon("assets/images/exit.png");
	        ImageIcon dataViewIcon = new ImageIcon("assets/images/view.png");
	        ImageIcon dataLogsIcon = new ImageIcon("assets/images/logs.png");
	        ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
	        
	        //MENU
	        JMenu file = new JMenu("File"); //New dropdown
	        file.setMnemonic(KeyEvent.VK_F); //Short cut for "File" drop down
	        JMenu data = new JMenu("Data"); //New dropdown
	        data.setMnemonic(KeyEvent.VK_D); //Short cut for "File" drop down
	        
	        //MENU ITEM
	        //exit
	        JMenuItem fileExit = new JMenuItem("Exit", fileExitIcon); //creates dropdown item "Exit" and it's icon to hte File dropdown
	        fileExit.setMnemonic(KeyEvent.VK_E); //short cut for exit
	        fileExit.setToolTipText("Exit application");
	        fileExit.addActionListener((ActionEvent exitButtonEvent) -> { //When clicked, exit
	        	logs.updateLogsSystem("EXIT");
	        	logs.writeToLogs("----------");
	            System.exit(0);
	        });
	        //view entries
	        JMenuItem dataView = new JMenuItem("View Entries", dataViewIcon);
	        dataView.setMnemonic(KeyEvent.VK_K);
	        dataView.setToolTipText("View Sign in/out");
	        dataView.addActionListener((ActionEvent dataViewButtonEvent) -> {
	        	logs.updateLogs("ViewLogsPDF Opened");
	        	//CALL BACKEND TO CREATE VIEW PDF (copy from main pdf)
	        	//SHOW VIEW PDF
	        	/*if(Back_End.createViewPDF()) {
	        		JOptionPane.showMessageDialog(dataView,
						    "Can not create ViewLogPDF for viewing.", //message
						    "ViewLogPDF File Error", //title
						    JOptionPane.ERROR_MESSAGE);
	        	}*/
	        	//else {
	        		//FIXME: Use jxdocument to render the ViewPDF
	            	//try {
	            		//JPanel renderedViewPDF = new JPanel();
	            		
	            		//RENDER
	            		File pdf = new File("data/ViewLogsPDF.pdf");
	            		com.teamdev.jxdocument.Document document = new com.teamdev.jxdocument.Document(pdf);
	            		SinglePagePresentation presentation= new SinglePagePresentation(document);
	            		presentation.displayPage(0);
	            		JComponent renderedViewPDF = presentation.getComponent();
	            		
	            		//added to a JDialog
	            		JOptionPane.showMessageDialog(dataView,
	            				renderedViewPDF, //message
	    					    "Logs PDF", //title
	    					    JOptionPane.INFORMATION_MESSAGE);
	            	//}
	            	//catch (){
	            		logs.updateLogsERROR("Error while opening ViewLogPDF");
	            		JOptionPane.showMessageDialog(dataView,
	    				"Can not open ViewLogPDF for viewing.", //message
	    				"ViewLogPDF File Error", //title
	    				JOptionPane.ERROR_MESSAGE);
	            	//}
	        	//}
	        });
	        //log .txt
	        JMenuItem dataLogs = new JMenuItem("Logs", dataLogsIcon);
	        dataLogs.setMnemonic(KeyEvent.VK_K);
	        dataLogs.setToolTipText("View logs");
	        dataLogs.addActionListener((ActionEvent dataLogsButtonEvent) -> {
	        	logs.updateLogs("Logs.txt Opened");
	        	String fileContent;
				try {
					fileContent = new Scanner(new File(LogsPath)).useDelimiter("\\Z").next();
		        	//TEST: JOptionPane.showMessageDialog(dataLogs, fileContent);
					
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double screenWidth = screenSize.getWidth();
					double screenHeight = screenSize.getHeight();
					//JOptionPane.showMessageDialog(dataLogs, screenWidth + " " + screenHeight);
					
					int displayWidth = (int) (screenWidth/15);
					int displayHeight = (int) (screenHeight/20);
					
					JTextArea textArea = new JTextArea(displayHeight, displayWidth);
				      textArea.setText(fileContent);
				      textArea.setEditable(false);
				      textArea.setLineWrap(true);
				      textArea.setWrapStyleWord(true);
				      textArea.setMargin(new Insets(10,10,10,10));
				      textArea.setCaretPosition(0);
				      
				      // wrap a scrollpane around it
				      JScrollPane scrollPane = new JScrollPane(textArea);
				      
				      // display them in a message dialog
				      JOptionPane.showMessageDialog(dataLogs, scrollPane);
					
					
				} catch (FileNotFoundException e) {
					logs.updateLogs("Logs.txt Closed");
					e.printStackTrace();
					JOptionPane.showMessageDialog(dataLogs,
						    "Can not open Log file.", //message
						    "Log File Error", //title
						    JOptionPane.ERROR_MESSAGE);
				}
	        });
	        
	        JMenuItem filePreferences = new JMenuItem("Preferences", filePreferencesIcon);
	        filePreferences.setMnemonic(KeyEvent.VK_P);
	        filePreferences.setToolTipText("Preferences");
	        filePreferences.addActionListener((ActionEvent filePreferencesButonEvent) -> { 
	        	logs.updateLogs("Preferences Opened");
	    		Preferences.preferences();
	        });
	        
	        
	        
	        //DISPLAY, backwards
	        //file
	        file.add(fileExit); //adds the exit menuitem to the "file" menu
	        file.add(filePreferences);
	        
	        menubar.add(file); //adds the file menu to the menubar
	        //data
	        data.add(dataView);
	        data.add(dataLogs);
	        
	        menubar.add(data);
	        
	        return menubar;
	        //setJMenuBar(menubar); //displays menubar
	    }
	}
	
	public static class Preferences {

		public static void main(String[] args) {
			preferences();
		}
		public static void preferences() {
			ImageIcon filePreferencesIcon = new ImageIcon("assets/images/preferences.png");
			
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double screenWidth = screenSize.getWidth();
			double screenHeight = screenSize.getHeight();
			int displayWidth = (int) (screenWidth/1.3);
			int displayHeight = (int) (screenHeight/1.3);
	    	
			JTabbedPane tabbedPane = new JTabbedPane();
			//tabbedPane.setPreferredSize(new Dimension(displayWidth, displayHeight));
			
			ImageIcon blankIcon = new ImageIcon("assets/images/noIcon.png");
			
			Font f = tabbedPane.getFont();
			Font f2 = new Font(f.getFontName(), Font.BOLD, f.getSize()+15);
			
			newPreferencesTab GeneralTab = new newPreferencesTab(tabbedPane, "General", null, "General ToolTip");
			//tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			JPanel GeneralTC = new JPanel();
			GeneralTab.add(GeneralTC);
			
			
	    		//TODO: add time zone change for logs (Back_End.updateLogs)
			
			

			JComponent panel2 = new JPanel();
			panel2.setLayout(new GridLayout(5, 1));
			tabbedPane.addTab("Logs", blankIcon, panel2, "Logs Preferences");
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			/*//TITLE
	    		JTextArea preferencesLogsTitle = new JTextArea();
	    		//textArea Settings
	    		preferencesLogsTitle.setFont(f2);
	    		preferencesLogsTitle.setOpaque(false);
	    		preferencesLogsTitle.setEditable(false);
	    		//CONTENT
	    		preferencesLogsTitle.setText("Logs");
	    		panel2.add(preferencesLogsTitle);*/
			addTitle(panel2, "LOGS");
			
	    	JTextArea preferencesLogsClearText = new JTextArea();
	    	preferencesLogsClearText.setOpaque(false);
	    	preferencesLogsClearText.setEditable(false);
	    	//CONTENT
	    	preferencesLogsClearText.setText("Clear PDF Log file");
	    	panel2.add(preferencesLogsClearText);
	    	
	    	JButton preferencesLogsClearButton = new JButton("Clear PDF"); //creates button named "Quit"
	    	preferencesLogsClearButton.setToolTipText("Caution, this will clear all student entry and exit logs"); //Adds a ToolTipText, this is like hovering of a link, it gives you info
	    	preferencesLogsClearButton.addActionListener((ActionEvent preferencesLogsClearButtonEvent) -> { //event listener, if clicked, system.exit(0);
	           	
	    		int choice = JOptionPane.showConfirmDialog(panel2, "Are you sure you want to clear the PDF Log file?\nThis will clear all student entry and exit logs");
	    		if(choice == JOptionPane.YES_OPTION) {
	    			logs.createPDF();
	    			logs.updateLogsSystem("LogsPDF Cleared");
	    			JOptionPane.showMessageDialog(panel2, "PDF Log has been cleared");
	    		}
	        });
	    	panel2.add(preferencesLogsClearButton);
			//Clear logs (run initStartUp for back_end)
			
			JComponent panel3 = new JPanel();
			tabbedPane.addTab("Email", blankIcon, panel3, "Email Preferences");
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
			addTitle(panel3, "General");
			
			//clear email history

			JComponent panel4 = new JPanel();
			panel4.setPreferredSize(new Dimension(410, 50));
			tabbedPane.addTab("Wifi", blankIcon, panel4, "Wifi Infomation");
			tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
			addTitle(panel4, "General");
			
			
			//using Ethernet
			//turn on and off wifi (airplane mode), see all wifi info (MAC, IP4, IP6, etc.)
			
			JComponent panel5 = new JPanel();
			panel5.setPreferredSize(new Dimension(410, 50));
			tabbedPane.addTab("DataBase", blankIcon, panel5, "Student DataBase Configuration");
			tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
			addTitle(panel5, "General");
			
			//insert database (either remote or file path to local)
			
			//TEST COMP
			JComponent panel6 = new JPanel();
			panel5.setPreferredSize(new Dimension(410, 50));
			tabbedPane.addTab("TEST", blankIcon, panel6, "TESTTESTTEST");
			tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
			addTitle(panel6, "TEST");
			
			
			
			JOptionPane.showMessageDialog(null, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
			

		}
		
		/**
		 * 
		 * @param addToComp JComponent
		 * @param comp JTextArea
		 * @param title String
		 */
		public static void addTitle(JComponent addToComp, String title) {
			JTextArea textArea = new JTextArea();
			//textArea Settings
			textArea.setFont(RL_Fonts.preferencesTitle);
			textArea.setOpaque(false);
			textArea.setEditable(false);
			textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
			textArea.setText(title);
			addToComp.add(textArea);
		}
	}
	
	public static class newPreferencesTab extends JComponent{
		/**
		 * Creates a new Tab in Preferences
		 * @param tabbedPane JTabbedPane used for holding these tabs
		 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
		 * @param icon Icon next to tab name
		 * @param toolTip tool tip for user's info
		 */
		public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab, Icon icon, String toolTip) {
			//TITLE
			JTextArea textArea = new JTextArea();
			textArea.setFont(RL_Fonts.preferencesTitle);
			textArea.setOpaque(true);
			textArea.setEditable(false);
			textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
			textArea.setText(nameOfTab);
			this.add(textArea);
			
			tabbedPane.addTab(nameOfTab, icon, this, toolTip);
		}
		
		/**
		 * Creates a new Tab in Preferences<br>
		 * Tooltip is set to none by default
		 * @param tabbedPane JTabbedPane used for holding these tabs
		 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
		 * @param icon Icon next to tab name
		 */
		public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab, Icon icon) {
			this(tabbedPane, nameOfTab, icon, "");
		}

		/**
		 * Creates a new Tab in Preferences<br>
		 * Tooltip and icon is set to none by default
		 * @param tabbedPane JTabbedPane used for holding these tabs
		 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
		 */
		public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab) {
			this(tabbedPane, nameOfTab, new ImageIcon("assets/images/noIcon.png"), "");
		}
	}	
}
