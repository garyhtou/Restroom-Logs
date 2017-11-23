package mainProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import interfaces.RL;
 
public class Front_End_Content implements RL{
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
	    //bannerConstraints.weightx = 0.0;
	    //bannerConstraints.weighty = 0.0;
	    bannerConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	    
    	TitleBar.add(banner, bannerConstraints);
	    
	    //TEST------
	    banner.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	    TitleBar.setBorder(BorderFactory.createLineBorder(RL.color(4)));
	    
    	//STATS
	    JPanel stats = new JPanel(new BorderLayout());
	    //TEACHER NAME
	    JEditorPane teacherName = new JEditorPane();
	    String teacherNameData = "Mr. Sabo";
	    teacherName.setEditable(false);
	    teacherName.setContentType("text/hteml");
	    teacherName.setText(teacherNameData);
	    teacherName.setFont(new Font("Verdana", Font.BOLD, teacherName.getFont().getSize()+20));
	    teacherName.setForeground(RL.color("DARK_GUNMETAL"));
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
		            timeAndDate.setText("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		            //System.out.println(timeStamp);
		        }
		    };
		Timer timer = new Timer(1000, actionListener);
		timer.start();
		timeAndDate.setBorder(BorderFactory.createLineBorder(RL.color(4)));
		stats.add(timeAndDate, BorderLayout.CENTER);
		
		//STATS
	    GridBagConstraints statsConstraints = new GridBagConstraints();
	    statsConstraints.gridx = 2;
	    statsConstraints.gridy = 1;
	    statsConstraints.weightx = 1.0;
	    statsConstraints.weighty = 1.0;
	    statsConstraints.fill = GridBagConstraints.BOTH;
	    bannerConstraints.anchor = GridBagConstraints.CENTER;
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
    	scan.setBackground(RL.color(4));
    	//SCAN TITLE
    	JLabel scanTitle = new JLabel();
    	scanTitle.setText("Scan your Student ID card below using the barcode scanner");
    	scanTitle.setFont(new Font("Serif", Font.BOLD, 12));
    	scan.add(scanTitle, BorderLayout.PAGE_START);
    	//SCAN FIELD
    	JTextField scanField = new JTextField("          ");
    	scanField.setEditable(true);
    	scanField.setToolTipText("Scan your Student ID card");
    	scan.add(scanField, BorderLayout.CENTER);
    	ScanAndMessage.setTopComponent(scan);
    	
    	//-----------
    	JPanel message = new JPanel(new GridBagLayout());
    	
    	JEditorPane messageTitle = new JEditorPane();
    	messageTitle.setContentType("text/html");
    	messageTitle.setText("<html><center><h1>MESSAGES</h1></center></html>");
    	messageTitle.setEditable(false);
    	messageTitle.setBorder(BorderFactory.createLineBorder(RL.color(4)));
    	GridBagConstraints messageTitleConstraints = new GridBagConstraints();
    	messageTitleConstraints.gridx = 1;
    	messageTitleConstraints.gridy = 1;
    	messageTitleConstraints.weightx = 1.0;
    	messageTitleConstraints.weighty = 0.0;
    	messageTitleConstraints.fill = GridBagConstraints.BOTH;
    	message.add(messageTitle, messageTitleConstraints);
    	
    	JEditorPane messageContent = new JEditorPane();

		//messageContent.setPage(url); //HAS NO CSS
    	String textFromUrl = "";
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
	    }
    	messageContent.setEditable(false);
    	messageContent.setFont(new Font("Verdana", Font.PLAIN, teacherName.getFont().getSize()));
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
    	TableTitleAndTableClearButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, RL.color(4)));
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
    	RL.JSplitPaneDividerLocation(frame, MajorLeftAndRight, 0.80);
    	
    	
    	
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