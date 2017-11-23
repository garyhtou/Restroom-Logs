package mainProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    	JPanel TitleBar = new JPanel(new BorderLayout());
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
	    TitleBar.add(banner, BorderLayout.LINE_START);
	    
	    //TEST------
	    banner.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	    TitleBar.setBorder(BorderFactory.createLineBorder(RL.color(4)));
	    
    	//STATS
    	String teacherName = "Mr. Sabo";
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
	    String timeStamp = "Current Date: " + dateFormat.format(date);
	    JLabel stats = new JLabel("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		    ActionListener actionListener = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	Date date = new Date();
		            SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
		            String timeStamp = "Current Date: " + dateFormat.format(date);
		            stats.setText("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		            //System.out.println(timeStamp);
		        }
		    };
		Timer timer = new Timer(1000, actionListener);
		timer.start();
		TitleBar.add(stats, BorderLayout.LINE_END);
    	
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
    	JTextArea scanField = new JTextArea("");
    	scanField.setEditable(true);
    	scan.add(scanField, BorderLayout.CENTER);
    	ScanAndMessage.setTopComponent(scan);
    	
    	//-----------
    	JPanel message = new JPanel();
    	
    	JEditorPane messageTitle = new JEditorPane();
    	messageTitle.setContentType("text/html");
    	messageTitle.setText("<html><h1>MESSAGES</h1></html>");
    	messageTitle.setEditable(false);
    	//messageContent.setBackground(Color.WHITE);
    	//Border b=new LineBorder(Color.black,1);
    	//messageContent.setBorder(b);
    	message.add(messageTitle, BorderLayout.PAGE_START);
    	
    	JEditorPane messageContent = new JEditorPane();
    	String url = "http://rl.coding2kids.com";
    	try {
    		messageContent.setPage(url); //HAS NO CSS
    	}
    	catch (IOException e) {
    		messageContent.setContentType("text/html");
    	 	messageContent.setText("<html>Could not load message from "+url);
    	}
    	messageContent.setEditable(false);
    	message.add(messageContent, BorderLayout.PAGE_END);
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