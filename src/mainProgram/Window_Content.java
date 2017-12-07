package mainProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import interfaces.*;
import interfaces.RL_Colors;
import interfaces.RL_Fonts;
import interfaces.RL_General;
 
public class Window_Content implements RL_Colors, RL_Fonts, RL_General{
	static String WebisteBaseUrl = config.WebsiteBaseURL;
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
    	JPanel scan = new JPanel();
    	scan.setOpaque(true);
    	scan.setBackground(RL_Colors.color(3));
    	//SCAN TITLE
    	JLabel scanTitle = new JLabel();
    	scanTitle.setText("Scan your Student ID card below using the barcode scanner");
    	scanTitle.setFont(new Font("Serif", Font.BOLD, scanTitle.getFont().getSize() + 10));
    	scan.add(scanTitle, BorderLayout.PAGE_START);
    	//SCAN FIELD
    	//TODO: make typable from focused window
    	JTextField scanField = new JTextField(10);
    	scanField.setEditable(true);
    	scanField.setToolTipText("Scan your Student ID card");
    	scan.add(scanField, BorderLayout.PAGE_END);
    	ScanAndMessage.setTopComponent(scan);
    	
    	//SCANNING
    	//whole window
    	MajorLeftAndRight.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent escan) {
            	System.out.print(escan.getKeyChar());
            	scanField.setText(scanField.getText() + escan.getKeyChar());
            }
        });
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
            			
            			pullStudentName names = new pullStudentName(intInput);
                		
                		System.out.println(intInput);
                    	String FirstName = names.getFirstName();
                    	String LastName = names.getLastName();
                    	
                    	if((FirstName != null) && (LastName != null)) {
                    		//UDPATES!!1
                        	boolean NotSignOut = true; //CURRENTLY SIGNED OUT??
                        	if(NotSignOut) {
                        		String data = FirstName + " " + LastName + " Signed Out";
                        		logs.updateLogs(data);
                        		try {
                        			logs.updatePDF(/*data*/);
                    			} catch (ClassNotFoundException e) {
                    				e.printStackTrace();
                    			} catch (SQLException e) {
                    				e.printStackTrace();
                    			}
                        	}
                        	else {
                        		String data = FirstName + " " + LastName + " Signed In";
                        		logs.updateLogs(data);
                        		try {
                        			logs.updatePDF(/*data*/);
                    			} catch (ClassNotFoundException e) {
                    				e.printStackTrace();
                    			} catch (SQLException e) {
                    				e.printStackTrace();
                    			}
                        	}
                    	}
            		}
            		else {
            			logs.updateLogs(input + " Is not an integer");
            			
            			JTextArea onlyInts = new JTextArea("Please only enter numbers");
            			JOptionPane.showMessageDialog(null, onlyInts, "Scan Error", JOptionPane.INFORMATION_MESSAGE);
            			
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
    	
    	JEditorPane messageContent = new JEditorPane();
		try {
	    	String url = WebisteBaseUrl+"logs/messages.html";
			messageContent.setPage(url); //HAS NO CSS
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	/*String textFromUrl = "";
    	URL url = null;
    	try {
    		url = new URL("http://rl.coding2kids.com/logs/messages.html");
    	} catch (MalformedURLException e1) {
    		e1.printStackTrace();
    	}
	    try {
	    	if(url !=null) {
			    BufferedReader in = new BufferedReader(
			    new InputStreamReader(
			   	url.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null)
			   		textFromUrl+=inputLine;
				messageContent.setText(textFromUrl);
			    in.close();
	    	}
	    }
	    catch (IOException e) {
	    	messageContent.setContentType("text/html");
	     	messageContent.setText("<html>Could not load message from "+url);
	    }*/
    	messageContent.setEditable(false);
    	messageContent.setFont(new Font("Verdana", Font.PLAIN, teacherName.getFont().getSize()));
    	messageContent.setContentType("text/html");
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
    	
    	//MAJOR LEFT AND RIGHT DIVIDER LOCATION
    	RL_General.JSplitPaneDividerLocation(frame, MajorLeftAndRight, 0.80);
    	
    	ActionListener focusActionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	scanField.requestFocus();
	        }
	    };
	    Timer scanFieldTimer = new Timer(1000, focusActionListener);
	    scanFieldTimer.start();
    	
    	return MajorLeftAndRight;
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame frame = new JFrame();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                content(frame);
                
            }
        });
    }
}