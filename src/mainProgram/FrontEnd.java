package mainProgram;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.commons.lang.time.StopWatch;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Front End part of the Restroom Logs Program (i.e GUI)
 * <div></div>
 *  Copyright (C) 2018  Gary Tou and Michael Schwamborn
 *  <div></div>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * <div></div>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * <div></div>
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/.
 * 
 *
 */
public class FrontEnd extends BackEnd{
	
	public static JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		create();
	}
	
	public static void create() {
	//temp splash screen
		tempSplash.start();
		
	//Window
		frame(); //set up main settings of the frame
	//TimeCheck
		TimeListener.time();
		
	//Menu Bar
		window.menuBar.create();
		window.menuBar.file.create();
		window.menuBar.file.exit.create();
		window.menuBar.file.preferences.create();
		window.menuBar.file.help.create();
		window.menuBar.log.create();
		window.menuBar.log.logsTxt.create();
		window.menuBar.log.customPDF.create();
		window.menuBar.log.reports.create();
		
		content.majorRL.create();
		
	//Major Right
		content.majorRL.right.create();
		content.majorRL.right.table.create();
		content.majorRL.right.table.titleBar.create();
		content.majorRL.right.table.titleBar.title.create();
		content.majorRL.right.table.titleBar.clearButton.create();
		content.majorRL.right.table.tablePane.create();
		content.majorRL.right.table.tablePane.tableContent.create();
		
	//Major Left
		content.majorRL.left.create();
		content.majorRL.left.statsScan.create();
		content.majorRL.left.statsScan.scanAndMessages.create();		
		content.majorRL.left.statsScan.scanAndMessages.scan.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.field.create();	
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.create();
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.timerCreate();
		content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.create();
		content.majorRL.left.statsScan.stats.create();
		content.majorRL.left.statsScan.stats.banner.create();
		content.majorRL.left.statsScan.stats.information.create();
		content.majorRL.left.statsScan.stats.information.teacherName.create();
		content.majorRL.left.statsScan.stats.information.otherInfo.create();
	
		
	//final changes
		
		
		content.majorRL.setDivLoc(); //must be done after frame is set visible //FIXME: NOT WORKING
		

		
	//OTA	
		if(config.checkForUpdates()) {
			int i = JOptionPane.showConfirmDialog(null, "There is an available update. Would you wish to open the Updater?", "Available Update", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
			    try {
					Process proc = Runtime.getRuntime().exec("java -jar Updater.jar");
					System.exit(0);
				} catch (IOException e) {
					BackEnd.logs.update.ERROR("Unable to run Updater");
					e.printStackTrace();
				}
			}

		}
		
		BackEnd.create();
		
		content.majorRL.right.table.tablePane.tableContent.update();
		frame.setVisible(true);
		tempSplash.dispose();
	}
	
	public static void frame() {		
		//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project
    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


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
        	JPanel warn = new JPanel();
        	warn.add(new JLabel("Inital Setup has NOT ran before!"));
        	warn.add(new JLabel("Program will close and open Inital Setup"));
        	JOptionPane.showMessageDialog(null, warn,"Warning! Inital Setup Has Not Ran", JOptionPane.ERROR_MESSAGE);
        	
		    try {
				Process proc = Runtime.getRuntime().exec("java -jar InitialSetup.jar");
				System.exit(0);
			} catch (IOException e) {
				BackEnd.logs.update.ERROR("Unable to Run/Open Initial Setup");
				e.printStackTrace();
			}
        	
        	/*//STEP 2
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
        	BackEnd.logs.update.StartUp("Start Up Complete\n-----");*/
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
					static JMenuItem fileExit = new JMenuItem("Exit", fileExitIcon); //creates dropdown item "Exit" and it's icon to hte File dropdown
					public static void create() {
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
					static JMenuItem filePreferences = new JMenuItem("Preferences", filePreferencesIcon);
				    static boolean daily;
					static JTextArea log;
					static String path;
					static String OS = System.getProperty("os.name").toLowerCase();
					static String activeHours = "";
					public static void create() {
						fileMenu.add(filePreferences);
						filePreferences.setMnemonic(KeyEvent.VK_P);
					    filePreferences.setToolTipText("Preferences");
						filePreferences.addActionListener((ActionEvent preferencesButtonEvent) -> {
							BackEnd.logs.update.Logs("Preferences Opened");
							   daily  =config.getDailyEmails();
							   activeHours = config.endOfActiveHours.toString();
							content();
				        });
					}
					public static void content() {
						//JTabbedPane
						JTabbedPane tabbedPane = new JTabbedPane();
						tabbedPane.setPreferredSize(new Dimension((int) (screenWidth/1.3), (int) (screenHeight/1.3)));
												
					//TABS
					//General
						preferences general = new preferences(tabbedPane, "General", null, "General Settings");
							/*general.addWithFont(new JLabel("Message Center"));
								JTextField otherInfoField = new JTextField();
								otherInfoField.setText(
										FrontEnd.content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.otherMessages.getText());
								otherInfoField.setEditable(true);
								otherInfoField.addKeyListener(new KeyListener() {
									public void keyPressed(KeyEvent arg0) {}
									public void keyTyped(KeyEvent arg0) {}
									public void keyReleased(KeyEvent arg0) {
										content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.update(otherInfoField.getText());
									}
								});
								general.add(otherInfoField);
								
								JPanel scannerDelayPane = new JPanel(new BorderLayout());
								JLabel titleScannerDelay = new JLabel("Scanner Re-Read Delay");
								scannerDelayPane.add(titleScannerDelay, BorderLayout.LINE_START);
								JTextField scannerDelayField = new JTextField();
								scannerDelayField.setText(Long.toString(config.ScannerReReadDelay));
								scannerDelayField.addKeyListener(new KeyListener() {
									public void keyPressed(KeyEvent arg0) {}
									public void keyReleased(KeyEvent e) {}
									public void keyTyped(KeyEvent e) {
										config.ScannerReReadDelay = Long.parseLong(scannerDelayField.getText());
									}
								});
								scannerDelayPane.add(scannerDelayField, BorderLayout.LINE_END);
								general.add(scannerDelayPane);
								
							general.add(new JSeparator());*/
							JPanel generalPref = new JPanel(new GridLayout(0,1));
							
							JPanel messageCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
							JTextField infomation = new JTextField();
							messageCenter.add(new JLabel("Message Center ",SwingConstants.LEFT));
							infomation.setPreferredSize(new Dimension(750,100));
							infomation.setText(FrontEnd.content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.otherMessages.getText());//TODO:Make it grab from TXT, make new var
							infomation.setEditable(true);
							messageCenter.add(infomation);
							
							generalPref.add(messageCenter);
							
							JPanel scanDelay = new JPanel(new FlowLayout(FlowLayout.LEFT));
							JTextField scanDelayField = new JTextField();
							
							general.add(generalPref);
					//Logs
						preferences logs = new preferences(tabbedPane, "Logs", null, "Logs Settings");
							JPanel logspref = new JPanel(new GridLayout(0,1));
							JPanel pdfLogs = new JPanel(new FlowLayout(FlowLayout.LEFT));
							JTextArea clearLogPdfText = new JTextArea();
								clearLogPdfText.setOpaque(false);
								clearLogPdfText.setEditable(false);
								clearLogPdfText.setText("Clear PDF Log file");
								clearLogPdfText.setFont(RL.preferencesText);
								pdfLogs.add(clearLogPdfText);
							JButton clearLogPdfButton = new JButton("Clear PDF");
								clearLogPdfButton.setToolTipText("Caution, this will clear all student entry and exit logs");
								clearLogPdfButton.addActionListener((ActionEvent preferencesLogsClearButtonEvent) -> {
									int choice = JOptionPane.showConfirmDialog(logs, "Are you sure you want to clear the PDF Log file?\nThis will clear all student entry and exit logs");
									if(choice == JOptionPane.YES_OPTION) {
										BackEnd.email.PDF.CreateBlankPDF();
										BackEnd.logs.update.System("LogsPDF Cleared");
										JOptionPane.showMessageDialog(logs, "PDF Log has been cleared");
									}
								});
								pdfLogs.add(clearLogPdfButton);
								logspref.add(pdfLogs);
								JPanel txtLogs = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JTextArea clearLogTxtText = new JTextArea();
								clearLogTxtText.setOpaque(false);
								clearLogTxtText.setEditable(false);
								clearLogTxtText.setText("Clear TXT Log file");
								clearLogTxtText.setFont(RL.preferencesText);
								txtLogs.add(clearLogTxtText);
								JButton clearLogTxtButton = new JButton("Clear TXT");
								clearLogTxtButton.setToolTipText("Caution, this will clear all System Text logs");
								clearLogTxtButton.addActionListener((ActionEvent preferencesLogsClearButtonEvent) -> {
									int choice = JOptionPane.showConfirmDialog(logs, "Are you sure you want to clear the TXT Log file?\nThis will clear all System Text logs");
									if(choice == JOptionPane.YES_OPTION) {
										BackEnd.logs.create();
										BackEnd.logs.update.StartUp("Logs Txt Cleared");
										JOptionPane.showMessageDialog(logs, "TXT Log has been cleared");
									}
								});
								txtLogs.add(clearLogTxtButton);
								logspref.add(txtLogs);
								logs.add(logspref);
						
					//Font
						preferences font = new preferences(tabbedPane, "Font", null, "Change Font Sizes");
						//SCAN
							scanFontSize scan = new scanFontSize(font, content.majorRL.left.statsScan.scanAndMessages.scan.field.field, 
								"Scan", content.majorRL.left.statsScan.scanAndMessages.scanAndMessages, RL.scan) {};
								JCheckBox scanScrollCheckBox = new JCheckBox("Enable scrolling to change Scan text size. May be glitchy at times");
								scanScrollCheckBox.setSelected(false);
								font.add(scanScrollCheckBox);
								scanScrollCheckBox.addItemListener(new ItemListener() {
									public void itemStateChanged(ItemEvent arg0) {
										preferences.scanFontSize.enableScroll = scanScrollCheckBox.isSelected();
									}
								});
						//OTHER MESSAGES (Message Center)
							fontSize otherMessage = new fontSize(font, "Other Messages", RL.otherMessage, RL.userOtherMessage) {
								public void updateUserFont(Font refFont) {
									RL.userOtherMessage = refFont;
								}
								public void updateJComponent() {
									content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.otherMessages.updateFontSize();
								}
							};
						//TEAHCER NAME
							fontSize teacherName = new fontSize(font, "Teacher Name", RL.TeacherName, RL.userTeacherName) {
								public void updateUserFont(Font refFont) {
									RL.userTeacherName = refFont;
								}
								public void updateJComponent() {
									content.majorRL.left.statsScan.stats.information.teacherName.updateFontSize();
								}
							};
						//OTHER INFO
							fontSize otherInfo = new fontSize(font, "Other Info", RL.otherInfo, RL.userOtherInfo) {
								public void updateUserFont(Font refFont) {
									RL.userOtherInfo = refFont;
								}
								public void updateJComponent() {
									content.majorRL.left.statsScan.stats.information.otherInfo.updateFontSize();
								}
							};
						//
							
							
							preferences database = new preferences(tabbedPane,"Database",null,"Database Settings");
								JPanel dbPanel = new JPanel(new GridLayout(0,1));
								
								JPanel dbPref1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
								log = new JTextArea(5,40);
			    		        log.setMargin(new Insets(5,5,0,5));
			    		        log.setEditable(false);
			    		         path = config.StudentDBPath.toString();
			    		        log.setText("Current Path: "+config.StudentDBPath.toString());
			    		        JScrollPane logScrollPane = new JScrollPane(log);
								JFileChooser fc = new JFileChooser();
			    		        JButton openButton = new JButton("Open a File...",
		                                 null);
						        openButton.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent arg0) {
										int returnVal = fc.showOpenDialog(null);

							            if (returnVal == JFileChooser.APPROVE_OPTION) {
							            	log.setText("");
							                File file = fc.getSelectedFile();
							                //This is where a real application would open the file.
							                log.append("Selected New: " + file.getName() + "." + "\n");
							                //log.append("Path: " + file.getPath() + newline);
							                log.append("Path New: " + file.getAbsolutePath() + "\n");
							                path = file.getAbsolutePath();
							            } else {
							            	log.setText("");
							                log.append("Cancelled by user." + "\n");
						    		        log.append("Current Path: "+config.StudentDBPath.toString());

							            }
							            log.setCaretPosition(log.getDocument().getLength());
									}
						        });
						        FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Access Database", "accdb");
						        fc.setFileFilter(filter);
						        dbPref1.add(new JLabel("Student Database Path: "));
						        dbPref1.add(openButton);
						        dbPref1.add(logScrollPane, BorderLayout.CENTER);
						        
						        dbPanel.add(dbPref1);
						        
						        JPanel dbPref2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JTextField dbTableName = new JTextField();
								dbPref2.add(new JLabel("Student Database Table Name: ",SwingConstants.LEFT));
								dbTableName.setPreferredSize(new Dimension(100,30));
								dbTableName.setText(config.StudentDBTableName.toString());
								dbTableName.setEditable(true);
								dbPref2.add(dbTableName);
								
								dbPanel.add(dbPref2);
								
								JPanel applyPanelD = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								JButton applyButtonD = new JButton();
								JLabel confirmD  = new JLabel();
								confirmD.setForeground(Color.GREEN);
								applyButtonD.setText("Apply Changes");
								//applyButton.setVisible(true);
								applyButtonD.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								    	config.StudentDBPath.setValue(path);
								    	config.StudentDBTableName.setValue((dbTableName.getText()));
								    	confirmD.setText("Changes Applied");
								    	 Timer timer = new Timer(2000, new  ActionListener() {
								    		    public void actionPerformed(ActionEvent e) {
								    		    	confirmD.setText("");
								    			}
								    		}); 
								    	    timer.start();
									}
								}); 
								
								applyPanelD.add(confirmD);
								applyPanelD.add(applyButtonD);
								dbPanel.add(applyPanelD);
						        
						        database.add(dbPanel);
							
							preferences teacher = new preferences(tabbedPane, "Teacher", null, "Teacher Settings");
								JPanel teacherPref = new JPanel(new GridLayout(0,1));
								
								JPanel teacherPrefP1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JTextField teacherNamePref = new JTextField();
								teacherPrefP1.add(new JLabel("Name: ",SwingConstants.LEFT));
								teacherNamePref.setPreferredSize(new Dimension(100,30));
								teacherNamePref.setText(config.getTeacherName());
								teacherNamePref.setEditable(true);
								teacherPrefP1.add(teacherNamePref);
								
								teacherPref.add(teacherPrefP1);
								
								JPanel teacherPrefP2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JTextField teacherEmailPref = new JTextField();
								teacherPrefP2.add(new JLabel("Email: ",SwingConstants.LEFT));
								teacherEmailPref.setPreferredSize(new Dimension(175,30));
								teacherEmailPref.setText(config.teacherEmail.toString());
								teacherEmailPref.setEditable(true);
								teacherPrefP2.add(teacherEmailPref);
								
								teacherPref.add(teacherPrefP2);
								
								JPanel teacherPrefP3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JCheckBox teacherDailyEmailPref = new JCheckBox();
								teacherPrefP3.add(new JLabel("Daily Log Emails: ",SwingConstants.LEFT));
								teacherDailyEmailPref.setSelected(daily);
								teacherDailyEmailPref.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								    	if(teacherDailyEmailPref.isSelected()) {
								    		config.setDailyEmails(true);
										}
										if(!teacherDailyEmailPref.isSelected()) {
											 config.setDailyEmails(false);
										}								   
									}
								}); 
								
								teacherPrefP3.add(teacherDailyEmailPref);
								
								teacherPref.add(teacherPrefP3);
								
								JPanel timeSpin = new JPanel(new FlowLayout(FlowLayout.LEFT));
								JLabel timeLabel = new JLabel("End of Active Hours: ");
								TimePickerSettings timeSettings = new TimePickerSettings();
				    	        timeSettings.setAllowKeyboardEditing(false);
				    	         TimePicker timePicker = new TimePicker(timeSettings);
				    	         timePicker.setText(activeHours);
								timeSpin.add(timeLabel);
								timeSpin.add(timePicker);
								teacherPref.add(timeSpin);
								
								JPanel applyPanelT = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								JButton applyButtonT = new JButton();
								JLabel confirmT  = new JLabel();
								confirmT.setForeground(Color.GREEN);
								applyButtonT.setText("Apply Changes");
								//applyButton.setVisible(true);
								applyButtonT.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								    	config.setTeachername(teacherNamePref.getText());
								    	config.teacherEmail.setValue((teacherEmailPref.getText()));
								    	config.setActiveHours(timePicker);
								    	content.majorRL.left.statsScan.stats.information.teacherName.update();
								    	confirmT.setText("Changes Applied");
								    	 Timer timer = new Timer(2000, new  ActionListener() {
								    		    public void actionPerformed(ActionEvent e) {
								    		    	confirmT.setText("");
								    			}
								    		}); 
								    	    timer.start();
									}
								}); 
								
								applyPanelT.add(confirmT);
								applyPanelT.add(applyButtonT);
								teacherPref.add(applyPanelT);
								teacher.add(teacherPref);
						
							
					//Wifi
						preferences wifi = new preferences(tabbedPane, "Wifi", null, "Wifi information");
						JTextArea wifiField  =new JTextArea();
						wifiField.setEditable(false);
						wifiField.setBackground(null);
						String content = "";
						try { 
						 if (isWindows()) {
								Runtime rt = Runtime.getRuntime();
								Process proc;
								
									proc = rt.exec("ipconfig /all");
								BufferedReader stdInput = new BufferedReader(new 
								     InputStreamReader(proc.getInputStream()));

								BufferedReader stdError = new BufferedReader(new 
								     InputStreamReader(proc.getErrorStream()));

								// read the output from the command
								String s = null;
								while ((s = stdInput.readLine()) != null) {
								    content+=s+"\n";
								}
								while ((s = stdError.readLine()) != null) {
								    content+=s+"\n";
								}
								wifiField.setText(content);
							} else if (isMac() || isUnix()) {
								Runtime rt = Runtime.getRuntime();
								Process proc;
								
									proc = rt.exec("ifconfig");
								BufferedReader stdInput = new BufferedReader(new 
								     InputStreamReader(proc.getInputStream()));

								BufferedReader stdError = new BufferedReader(new 
								     InputStreamReader(proc.getErrorStream()));

								// read the output from the command
								String s = null;
								while ((s = stdInput.readLine()) != null) {
								    content+=s+"\n";
								}
								while ((s = stdError.readLine()) != null) {
								    content+=s+"\n";
								}
								wifiField.setText(content);
							} else {
								content  = "Your Operating System is not supported for the Wifi Information Feature.";
								wifiField.setText(content);
							}
							} catch (IOException e1) {
								BackEnd.logs.update.ERROR("Unable to Read from Internal Command Prompt/Terminal");
								e1.printStackTrace();
							}
						wifi.add(wifiField);
								
					//About
						preferences about = new preferences(tabbedPane, "About", null, "About this program");
							JLabel verNum = new JLabel("<html><strong>Version Number: </strong>" + config.VersionNumber + "</html>");
							about.addWithFont(verNum);
							JButton update = new JButton("Check for Updates");
							update.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									
									JPanel confirm = new JPanel();
									confirm.add(new JLabel("Are you sure you want to exit the program and open the Updater?"));
									int result = JOptionPane.showConfirmDialog(null, confirm, "Are you sure?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
									if(result==JOptionPane.YES_OPTION) {
					    			   try {
										Process proc = Runtime.getRuntime().exec("java -jar Updater.jar");
					    				   System.exit(0);
					    			   } catch (IOException e) {
					    				   e.printStackTrace();
					    			   }

									}
								}
								
							});
							about.add(update);
							about.add(new JSeparator());
							about.addWithFont(new JLabel("Program created by Gary Tou and Michael Schwamborn \u00a9 2018"));
							JButton licenceView = new JButton("View License");
							licenceView.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									license();
									
								}
								public void license() {
									String fileContent;
									JPanel pane = new JPanel(new BorderLayout());
									JScrollPane scrollPane = new JScrollPane();
									JTextArea logTextArea = new JTextArea(25,35);
									try {
										fileContent = new Scanner(new File(licensePath)).useDelimiter("\\Z").next();

										Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
										double screenWidth = screenSize.getWidth();
										double screenHeight = screenSize.getHeight();
										
										int displayWidth = (int) (screenWidth/2);
										int displayHeight = (int) (screenHeight/1.5);
										
									    logTextArea.setText(fileContent);
									    logTextArea.setEditable(false);
									    logTextArea.setLineWrap(true);
									    logTextArea.setWrapStyleWord(true);
									    logTextArea.setMargin(new Insets(10,10,10,10));
									    logTextArea.setCaretPosition(0);
									    
									    
									    
									    scrollPane.setViewportView(logTextArea);
									    pane.add(scrollPane, BorderLayout.CENTER);
									    
										
										
									    JOptionPane optionPane = new JOptionPane();
									    	optionPane.setSize(displayWidth, displayHeight);
									    	optionPane.showMessageDialog(null, pane, "License", JOptionPane.INFORMATION_MESSAGE);
									    
									} catch (FileNotFoundException e) {
										BackEnd.logs.update.Logs("License Closed");
										e.printStackTrace();
										JOptionPane.showMessageDialog(null,
											    "Can not open License file.", //message
											    "License File Error", //title
											    JOptionPane.ERROR_MESSAGE);
									}
								}
								
							});
					
							about.add(licenceView);
							
						JOptionPane.showMessageDialog(null, tabbedPane, "Preferences", JOptionPane.INFORMATION_MESSAGE, filePreferencesIcon);
							
					}
					public static boolean isWindows() {

						return (OS.indexOf("win") >= 0);

					}

					public static boolean isMac() {

						return (OS.indexOf("mac") >= 0);

					}

					public static boolean isUnix() {

						return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

					}

					public static boolean isSolaris() {

						return (OS.indexOf("sunos") >= 0);

					}

					
					/**
					 * Creates a new Tab in Preferences
					 * @param tabbedPane JTabbedPane used for holding these tabs
					 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
					 * @param icon Icon next to tab name
					 * @param toolTip tool tip for user's info
					 */
					public preferences(JTabbedPane tabbedPane, String nameOfTab, Icon icon, String toolTip) {
						JPanel newTab = new JPanel(new BorderLayout());
						tabbedPane.addTab(nameOfTab, icon, newTab, toolTip);
						
						JPanel mainPane = new JPanel(new BorderLayout());
						
						JScrollPane scrollPane = new JScrollPane();
							scrollPane.setViewportView(mainPane);
							scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
							scrollPane.getVerticalScrollBar().setUnitIncrement(12);
							newTab.add(scrollPane, BorderLayout.CENTER);
												
						JTextArea Title = new JTextArea();
							Title.setFont(RL.preferencesTitle);
							Title.setOpaque(false);
							Title.setEditable(false);
							Title.setAlignmentX(JTextArea.CENTER_ALIGNMENT); //FIXME: Not working
							Title.setBorder(new CompoundBorder(
								BorderFactory.createEmptyBorder(10, 10, 15, 10),
								BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)
							));
							Title.setText(nameOfTab);
						
						mainPane.add(Title, BorderLayout.PAGE_START);
						mainPane.add(this, BorderLayout.CENTER);
						
						this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
						this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
						this.setFont(RL.preferencesText);
					}
					public void addWithFont(Component comp) {
						comp.setFont(RL.preferencesText);
						this.add(comp);
					}
					public static abstract class scanFontSize extends JPanel {
						static boolean enableScroll = false;
						
						/**
						 * outdated params
						 * user changed font sizes<br><br>
						 * Note: all JComponent that have user changed sizes must have a <strong>updateFontSize()</strong> method.
						 * <br>
						 * Ex.<br><code>
						 * public static void updateFontSize() {<br>
							    teacherName.setFont(RL.userTeacherName);<Br>
							}</code>
						 * @param pane wich this JPanel to the parent JPael
						 * @param name name of font (Teacher Name)
						 * @param defaultFont orginal font
						 */
						public scanFontSize(JComponent pane, JTextField field, String name, JSplitPane parentSplitPane, Font defaultFont) {
							int defaultSize = defaultFont.getSize();
							int minDivider = (int) ((config.screenWidth + config.screenHeight) / config.minFontSizeDivider);
							int maxDivider = (int) ((config.screenWidth + config.screenHeight) / config.maxFontSizeDivider);
							
							JSlider slider = new JSlider(minDivider, maxDivider, defaultSize);
							JLabel title = new JLabel(name + ": " + slider.getValue());
							title.setFont(defaultFont);
							JButton reset = new JButton("Reset");
							
							reset.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									title.setText(name + ": " + defaultFont.getSize());
									RL.userScan = defaultFont;
									RL.userScan = defaultFont;
									RL.userScan = RL.scan; //used to be updateUserFont(Font)
									slider.setValue(defaultFont.getSize());
									field.setFont(RL.userScan); //used to be updateTextSize();
									parentSplitPane.setDividerLocation(-1);
								}
							});
							slider.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent arg0) {
									config.scanFontSize = slider.getValue();
									title.setText(name + ": " + (int) config.scanFontSize);
									RL.userScan = defaultFont.deriveFont(config.scanFontSize);
									title.setFont(RL.userScan);
									parentSplitPane.setDividerLocation(-1);
									field.setFont(RL.userScan); //used to be updateTextSize();
									}
							});						
							
							
							title.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
							slider.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 15));
							
							this.setLayout(new GridBagLayout());
							GridBagConstraints titleC = new GridBagConstraints();
								titleC.gridx = 0;
								titleC.fill = GridBagConstraints.HORIZONTAL;
								titleC.weightx = (double) 1.0;
							GridBagConstraints sliderPaneC = new GridBagConstraints();
								sliderPaneC.gridx = 1;
							
							JPanel sliderPane = new JPanel(new BorderLayout());
								sliderPane.add(slider, BorderLayout.LINE_START);
								sliderPane.add(reset, BorderLayout.LINE_END);
								sliderPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
								
							this.add(title, titleC);
							this.add(sliderPane, sliderPaneC);
							
							pane.add(this);
						}
					}
					public static abstract class fontSize extends JPanel{
						Font refFont;
						/**
						 * user changed font sizes<br><br>
						 * Note: all JComponent that have user changed sizes must have a <strong>updateFontSize()</strong> method.
						 * <br>
						 * Ex.<br><code>
						 * public static void updateFontSize() {<br>
							    teacherName.setFont(RL.userTeacherName);<Br>
							}</code>
						 * @param pane wich this JPanel to the parent JPael
						 * @param name name of font (Teacher Name)
						 * @param defaultFont orginal font
						 */
						public fontSize(JComponent pane, String name, Font defaultFont, Font newFont) {
							refFont = newFont;
							int defaultSize = defaultFont.getSize();
							int minDivider = (int) ((config.screenWidth + config.screenHeight) / config.minFontSizeDivider);
							int maxDivider = (int) ((config.screenWidth + config.screenHeight) / config.maxFontSizeDivider);
							
							JSlider slider = new JSlider(minDivider, maxDivider, defaultSize);
							JLabel title = new JLabel(name + ": " + slider.getValue());
							title.setFont(defaultFont);
							slider.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent arg0) { //TODO WORK AREA
									title.setText(name + ": " + slider.getValue());
									refFont = defaultFont.deriveFont(defaultFont.getStyle(), slider.getValue());
									updateUserFont(refFont);
									title.setFont(refFont);
									updateJComponent();
									}
							});
							JButton reset = new JButton("Reset");
							reset.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									title.setText(name + ": " + defaultFont.getSize());
									refFont = defaultFont;
									updateUserFont(defaultFont);
									title.setFont(defaultFont);
									slider.setValue(defaultFont.getSize());
									updateJComponent();
								}
							});
							content.majorRL.left.statsScan.scanAndMessages.scan.field.field.addMouseWheelListener(new MouseWheelListener() {
								public void mouseWheelMoved(MouseWheelEvent mwe) {
									slider.setValue((int) config.scanFontSize);
								}
							});
							
							title.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
							slider.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 15));
							
							this.setLayout(new GridBagLayout());
							GridBagConstraints titleC = new GridBagConstraints();
								titleC.gridx = 0;
								titleC.fill = GridBagConstraints.HORIZONTAL;
								titleC.weightx = (double) 1.0;
							GridBagConstraints sliderPaneC = new GridBagConstraints();
								sliderPaneC.gridx = 1;
							
							JPanel sliderPane = new JPanel(new BorderLayout());
								sliderPane.add(slider, BorderLayout.LINE_START);
								sliderPane.add(reset, BorderLayout.LINE_END);
								sliderPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
								
							this.add(title, titleC);
							this.add(sliderPane, sliderPaneC);
							
							pane.add(this);
						}
						/**
						 * <strong>IMPORTANT: </strong> set refFont to user font.<br>
						 * Ex. RL.userTeacherName = refFont;
						 * @param refFont the new font with user changed size
						 */
						public abstract void updateUserFont(Font refFont);
						/**
						 * <strong>IMPORTANT: </strong> calls JComponent's updateFontSize method to user font.<br>
						 * Ex. content.majorRL.left.statsScan.stats.information.teacherName.updateFontSize();
						 */
						public abstract void updateJComponent();
					}
				}
				public static class help {
					static JMenuItem fileExit = new JMenuItem("Help", helpIcon); 
					public static void create() {
						fileMenu.add(fileExit);
				        fileExit.setMnemonic(KeyEvent.VK_H); //short cut for exit
				        fileExit.setToolTipText("Open Online Documentation");
				        
				        fileExit.addActionListener((ActionEvent exitButtonEvent) -> { //When clicked, exit
				        	BackEnd.logs.update.System("Opened Online Documentaion");
				        	URI uri;
							try {
								uri = new URI("https://www.rl.coding2kids.com/docs");
								 if (Desktop.isDesktopSupported()) {
							          try {
							            Desktop.getDesktop().browse(uri);
							          } catch (IOException e) { /* TODO: error handling */ }
							        } else { /* TODO: error handling */ }
							} catch (URISyntaxException e1) {
								e1.printStackTrace();
							}
				        });
					}
				}
			}
			public static class log {
				static JMenu logMenu = new JMenu("Log");
				public static void create() {
					menuBar.add(logMenu);
					logMenu.setMnemonic(KeyEvent.VK_L);
				}
				public static class logsTxt {
					static JMenuItem logTxt = new JMenuItem("Logs.txt", logsTxtIcon);
					public static void create() {
						logMenu.add(logTxt);
						logTxt.setMnemonic(KeyEvent.VK_T);
						logTxt.addActionListener((ActionEvent logButtonEvent) -> {
							BackEnd.logs.update.Logs("LogTxt Opened");
							content();
						});
					}
					public static void content() {
						String fileContent;
						JPanel pane = new JPanel(new BorderLayout());
						JScrollPane scrollPane = new JScrollPane();
						JTextArea logTextArea = new JTextArea(25,35);
						try {
							fileContent = new Scanner(new File(LogsPath)).useDelimiter("\\Z").next();

							Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
							double screenWidth = screenSize.getWidth();
							double screenHeight = screenSize.getHeight();
							
							int displayWidth = (int) (screenWidth/2);
							int displayHeight = (int) (screenHeight/1.5);
							
						    logTextArea.setText(fileContent);
						    logTextArea.setEditable(false);
						    logTextArea.setLineWrap(true);
						    logTextArea.setWrapStyleWord(true);
						    logTextArea.setMargin(new Insets(10,10,10,10));
						    logTextArea.setCaretPosition(0);
						    
						    JButton email = new JButton("Email");
						    	email.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
						    			//TODO: call something
						    			//temp:
						    			//JDialog temp = new JDialog(frame, "call something there to email");
						    			//temp.setVisible(true);
						    			System.out.print("BUTTON");
										config.RlGPFO = config.getRlGPFO();
						    			BackEnd.email.sendTXT();
						    			JOptionPane.showMessageDialog(frame, new JLabel(
						    					"Email Sent to: " + config.teacherEmail), "Emailed!", JOptionPane.PLAIN_MESSAGE);
									}
						    	});
						    
						    scrollPane.setViewportView(logTextArea);
						    pane.add(scrollPane, BorderLayout.CENTER);
						    pane.add(email, BorderLayout.PAGE_END);
						    
							
							
						    JOptionPane optionPane = new JOptionPane();
						    	optionPane.setSize(displayWidth, displayHeight);
						    	optionPane.showMessageDialog(logTxt, pane);
						    
						} catch (FileNotFoundException e) {
							BackEnd.logs.update.Logs("Logs.txt Closed");
							e.printStackTrace();
							JOptionPane.showMessageDialog(logTxt,
								    "Can not open Log file.", //message
								    "Log File Error", //title
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				/**
				 * send and delete tables (PDFs)
				 */
				public static class customPDF {
					static JMenuItem custPDF = new JMenuItem("Send Custom PDFs", PDFIcon);
					public static void create() {
						logMenu.add(custPDF);
						custPDF.setMnemonic(KeyEvent.VK_P);
						custPDF.addActionListener((ActionEvent logButtonEvent) -> {
							BackEnd.logs.update.Logs("Send Custom PDFs Opened");
							content();
						});
					}
					//these are here so that the delete listener can access and update;
					static ArrayList<table> tables = new ArrayList<table>(); //tables and buttonTables are parallel arrayLists
					static ArrayList<JCheckBox> tableCheckBoxes = new ArrayList<JCheckBox>();
					static JPanel checkBoxPane = new JPanel();
					static JOptionPane dialog = new JOptionPane();
					public static void content() {
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						scroll.getVerticalScrollBar().setUnitIncrement(config.JSCROLLPANE_NORMAL_SCROLL_SPEED);
						JPanel pane = new JPanel(new BorderLayout());
						scroll.setViewportView(pane);
						
						JLabel title = new JLabel("Select days to Email or Delete");
						title.setFont(RL.preferencesTitle);
						pane.add(title, BorderLayout.PAGE_START);
						
						checkBoxPane.setLayout(new BoxLayout(checkBoxPane, BoxLayout.Y_AXIS));
						
						updateCheckBoxes();
						
						pane.add(checkBoxPane, BorderLayout.CENTER);
						
						JPanel buttonPane = new JPanel();
						buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
						JButton emailButton = new JButton("Email");
						emailButton.setFont(RL.preferencesText);
						buttonPane.add(emailButton);
						JButton deleteButton = new JButton("Delete");
						deleteButton.setFont(RL.preferencesText);
						buttonPane.add(deleteButton);
						pane.add(buttonPane, BorderLayout.LINE_END);
						
						emailButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ArrayList<String>selectedTables = getSelectedDBTableNames();
								BackEnd.email.PDF.updatePDF(selectedTables);
								config.RlGPFO = config.getRlGPFO();
								BackEnd.email.sendPDF(config.PdfLogPath, "Restroom Logs PDF: Multi-day " + config.getDate()+".pdf", selectedTables);								
								updateCheckBoxes(); //this will reflect any db table changes (shouldn't be any) and uncheck all checkboxes
								
							}
						});
						deleteButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ArrayList<String> checkTables = getSelectedDBTableNames();
								for(int i = 0; i < checkTables.size(); i++) {
									BackEnd.database.Log.table.delete(checkTables.get(i));
								}
								BackEnd.database.Log.createTodayTable(); //this method checks if it already exists
								updateCheckBoxes(); 
								
								FrontEnd.content.majorRL.right.table.tablePane.tableContent.update();
								JOptionPane pane = new JOptionPane();
								JLabel message = new JLabel("Date Deletion Completed");
								pane.showMessageDialog(frame, message, "Delete Dates", JOptionPane.PLAIN_MESSAGE);
							}
						});
						dialog.setPreferredSize(new Dimension((int)config.screenWidth/2, (int)config.screenHeight/3));
						scroll.setPreferredSize(dialog.getPreferredSize());
						dialog.showMessageDialog(frame, scroll, "Send/Delete Data", JOptionPane.PLAIN_MESSAGE);
						dialog.setSize(dialog.getPreferredSize());
					}
					private static void updateCheckBoxes() {
						//clean
						checkBoxPane.removeAll();
						
						tables = new ArrayList<table>(); //tables and tableCheckBoxes are parallel arrayLists
						tableCheckBoxes = new ArrayList<JCheckBox>();
						
						String[] DBTables = BackEnd.database.Log.getAllTableName();
						for(int i = 0; i < DBTables.length; i++) {
							tables.add(new table(DBTables[i]));
							JCheckBox checkBox = new JCheckBox();
							tableCheckBoxes.add(checkBox);
							checkBox.setText(tables.get(i).getDate());
							checkBox.setFont(RL.preferencesText);
							checkBoxPane.add(checkBox);
						}
						
						//TODO: Sort tableCheckBoxes array of JCheckBox's text by recent
						//sort by recent
						/*int n = arr.length;
				        for (int i=1; i<n; ++i)
				        {
				            int key = arr[i];
				            int j = i-1;
				 
				             Move elements of arr[0..i-1], that are
				               greater than key, to one position ahead
				               of their current position 
				            while (j>=0 && arr[j] > key)
				            {
				                arr[j+1] = arr[j];
				                j = j-1;
				            }
				            arr[j+1] = key;
				        }*/
						
				        //add to pane
						for(JCheckBox c : tableCheckBoxes) {
							checkBoxPane.add(c);
						}
						//update graphics
						checkBoxPane.validate();
						checkBoxPane.repaint();
					}
					private static ArrayList<String> getSelectedDBTableNames(){
						ArrayList<String> checkTables = new ArrayList<String>();
						for(int i = 0; i < tables.size(); i++) {
							if(tableCheckBoxes.get(i).isSelected()) {
								checkTables.add(tables.get(i).getDBTableName());
							}
						}
						return checkTables;
					}
					public static class table {
						private int month;
						private int day;
						private int year;
						
						String DBTableName;
						public table(String DBTableName) {
							this.DBTableName = DBTableName;
							process();
						}
						private void process() {
							String heading = "Logs";
							String rawDate = this.DBTableName.substring(heading.length()); //from of dd/MM/yy
							System.out.println("rawDate: " + rawDate);
							this.day = Integer.parseInt(rawDate.substring(0, 2));
							this.month = Integer.parseInt(rawDate.substring(2, 4));
							this.year = Integer.parseInt(rawDate.substring(4, 8));
							System.out.println(this.getDate());
						}
						public String getDate() {
							final String divider = "/";
							return this.month + divider + this.day + divider + this.year;
						}
						public String getDBTableName() {
							return this.DBTableName;
						}
					}
				}
				
				public static class reports {
					static JMenuItem reports = new JMenuItem("Reports", config.reportsIcon);
					static JOptionPane optionPane;
					public static void create() {
						logMenu.add(reports);
						reports.setMnemonic(KeyEvent.VK_R);
						reports.addActionListener((ActionEvent logButtonEvent) -> {
							BackEnd.logs.update.Logs("Reports Opened");
							content();
						});
					}
					public static void paint() {
						//table.setVisible(true);
						table.repaint();
					}
					static JTable table = new JTable();
					static JTextField searchField;
					static JLabel fieldMessage;
					static JLabel stats;
					static JPanel center;
					public static void content() {
						JPanel panel = new JPanel(new BorderLayout());
						JScrollPane scroll = new JScrollPane();
						scroll.setViewportView(panel);
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				    	scroll.setPreferredSize(
				    			new Dimension((int)(config.screenWidth/1.3), (int)(config.screenHeight/1.2)));
						
						JLabel title = new JLabel("Reports");
						title.setFont(RL.preferencesTitle);
						panel.add(title, BorderLayout.PAGE_START);
						
						center = new JPanel(new BorderLayout());
						panel.add(center, BorderLayout.CENTER);
						JPanel search = new JPanel(new BorderLayout());
						center.add(search, BorderLayout.PAGE_START);
						
						//search field
						searchField = new JTextField();
						search.add(searchField, BorderLayout.CENTER);
						searchField.addKeyListener(new KeyListener() {
							public void keyTyped(KeyEvent e) {}
							public void keyReleased(KeyEvent e) {}
							public void keyPressed(KeyEvent e) {
								if(e.getKeyChar() == KeyEvent.VK_ENTER) {
									update();
								}
							}
						});
						
						//enter button
						JButton enter = new JButton("Enter");
						search.add(enter, BorderLayout.LINE_END);
						
						//field message
						fieldMessage = new JLabel("Enter a Student ID  Ex. " + (int)(Math.random()*1000000));
						search.add(fieldMessage, BorderLayout.PAGE_END);
						
						//table
						table = new JTable();
						updateCellSize();
						center.add(table, BorderLayout.CENTER);
						
						//stats
						stats = new JLabel();
						stats.setFocusable(false);
						stats.setFont(stats.getFont().deriveFont(stats.getFont().getSize()+10f));
						stats.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
						
						center.add(stats, BorderLayout.PAGE_END);
						
						enter.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								update();
							}
						});
						
						optionPane = new JOptionPane(scroll, JOptionPane.PLAIN_MESSAGE);
				    	JOptionPane.showOptionDialog(reports, scroll, "Reports", JOptionPane.DEFAULT_OPTION, 
				    			JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					}
					public static void update() {
						int studentID = -1;
						try {
							studentID = Integer.parseInt(searchField.getText());
							searchField.setText("");
						} catch (NumberFormatException e2) {
							fieldMessage.setText("Please only enter integers");
						}
						if(BackEnd.database.Student.pullStudentName.firstName(studentID) != null) {
							ArrayList<Object> arr = BackEnd.database.Student.getStudentInfo(studentID);
							
							//Name
							String firstName = ((String[]) arr.get(0))[0];
							String lastName = ((String[]) arr.get(0))[1];
							
							//table
							JTable newTable = (JTable) arr.get(1);
							
							//stats
							String numOfExits = ((String[]) arr.get(2))[0];
							String avgDuration = ((String[]) arr.get(2))[1];
							String realisticAvgDuration = ((String[]) arr.get(2))[2];
							
							fieldMessage.setText(firstName + " " + lastName);
							center.remove(table);
							table = newTable;
							table.setFont(RL.tableText);
							table.setFillsViewportHeight(true);
							table.setDefaultEditor(Object.class, null); //make table uneditable
							table.setIntercellSpacing(new Dimension(5, 0));
							table.setEnabled(false);
							center.add(table, BorderLayout.CENTER);
							stats.setText(
									"<html>Number of Exits: " + numOfExits + "<br>" +
									"Average Duration: " + avgDuration + "<br>" +
									"Realistic Avg. Duration (Excluding durations > 20 min.): " + realisticAvgDuration+"</html>");
							
						} else {
							fieldMessage.setText("\"" + studentID + "\" not found");
						}
						paint();
					}
					public static void updateCellSize() {
						//can only be ran after data is added
						for (int row = 0; row < table.getRowCount(); row++) {
							int rowHeight = table.getRowHeight();
					        for (int column = 0; column < table.getColumnCount(); column++) {
					            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
					            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
					        }
					        table.setRowHeight(row, rowHeight);
					    }
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
			public static void setDivLoc() {
				majorRL.setDividerLocation((double) 0.6);
			}
			public static class left {
				static JPanel left = new JPanel(new BorderLayout());
				public static void create() {
					majorRL.setLeftComponent(left);
				}
				public static class statsScan {
					static JPanel statsScan = new JPanel(new GridBagLayout());
					public static void create() {
						left.add(statsScan, BorderLayout.CENTER);
						
					}
					public static class stats {
						static JPanel stats = new JPanel(new BorderLayout());
						public static void create() {
							GridBagConstraints statsC = new GridBagConstraints();
								statsC.gridy = 0;
								statsC.gridx = 0;
								statsC.weightx = 1.0;
								statsC.weighty = 0.0;
								statsC.fill = GridBagConstraints.HORIZONTAL;
							statsScan.add(stats, statsC);
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
							static JPanel information = new JPanel();
							public static void create() {
								stats.add(information, BorderLayout.CENTER);
								information.setLayout(new BoxLayout(information, BoxLayout.PAGE_AXIS));
								information.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
							}
							public static class teacherName {
								static JLabel teacherName = new JLabel();
								public static void create() {
									update();
									information.add(teacherName);
									updateFontSize();
								}
								public static void updateFontSize() {
									teacherName.setFont(RL.userTeacherName);
								}
								public static void update() {
									String tempTeacherName = config.getTeacherName();
									String message = tempTeacherName;
									teacherName.setText(message);
								}
							}
							public static class otherInfo {
								static JLabel otherInfo = new JLabel();
								public static void create() {
									update("Welcome to the Restroom Log Program");
									updateFontSize();
									information.add(otherInfo);
								}
								public static void updateFontSize() {
									otherInfo.setFont(RL.userOtherInfo);
								}
								public static void update(String info) {
									otherInfo.setText(info);
								}
							}
						}
					}
					public static class scanAndMessages {
						static JSplitPane scanAndMessages = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
						public static void create() {
							scanAndMessages.setDividerSize(0);
							GridBagConstraints scanAndMessagesC = new GridBagConstraints();
								scanAndMessagesC.gridx = 0;
								scanAndMessagesC.gridy = 1;
								scanAndMessagesC.weightx = 1.0;
								scanAndMessagesC.weighty = 1.0;
								scanAndMessagesC.fill = GridBagConstraints.BOTH;
							statsScan.add(scanAndMessages, scanAndMessagesC);
						}
						public static class scan {
							static JPanel scan = new JPanel(new GridBagLayout());
							public static void create() {
								scanAndMessages.setTopComponent(scan);
							}
							public static class field {
								static JTextField field = new JTextField();
								public static void create() {
							    	field.setEditable(true);
							    	field.setBackground(Color.WHITE);
							    	field.setFont(RL.userScan);
							    	field.setOpaque(true);
							    	
							    	GridBagConstraints  c = new GridBagConstraints ();
							    	c.weightx = 0.9;
							    	c.weighty = 0.9;
							    	c.fill = GridBagConstraints.BOTH;
							    	
							    	scan.add(field, c);
							    	
							    	StopWatch delay = new StopWatch();
							    	delay.start();
							    	
							    	//SCANNING
							    	field.addKeyListener(new KeyListener() {
							            @Override
							            public void keyTyped(KeyEvent e) {}
	
							            @Override
							            public void keyReleased(KeyEvent e) {}
	
							            @Override
							            public void keyPressed(KeyEvent escan) {
							            	
							            	String input = "";
							            	if((escan.getKeyChar() == KeyEvent.VK_ENTER) && (delay.getTime() >= config.ScannerReReadDelay)) {
							            		input = field.getText();
							            		EventQueue.invokeLater(() -> {
							            			field.setText("");
							                    });
							            		if(mainProgram.BackEnd.database.Student.pullStudentName.containsOnlyNumbers(input) && !input.isEmpty()) {
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
							                    	
							                    	delay.reset();
							                    	delay.start();
							            		}
							            		else if(!input.isEmpty()){
							            			messageCenter.scanEntryMessage.integer(input);
							            		}
							            	}
							            	content.majorRL.right.table.tablePane.tableContent.update();
							            }
							        });
							    	
							    	field.addMouseWheelListener(new MouseWheelListener() {
										public void mouseWheelMoved(MouseWheelEvent mwe) {
											if(window.menuBar.file.preferences.scanFontSize.enableScroll){
												double clicks = mwe.getPreciseWheelRotation();
												double change = clicks * config.SCAN_FONT_SIZE_SCROLL_SPEED;
												double tempSize = config.scanFontSize + change;
												tempSize = Math.min(tempSize, config.maxFontSize);
												config.scanFontSize = (float) Math.max(tempSize, config.minFontSize);
												RL.userScan = RL.scan.deriveFont(config.scanFontSize);
												field.setFont(RL.userScan);
												field.setSize(field.getPreferredSize());
												scanAndMessages.setDividerLocation(-1);
							    			}
										}
									});
							    	
							    	java.util.Timer timer = new java.util.Timer(); //field always requests focus
							    	timer.schedule(new TimerTask() {
										public void run() {
											if(!field.isFocusOwner()) {
												field.requestFocusInWindow();
											}
										}
							    		
							    		
							    	}, 0, 1);
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
										message.setText(config.defaultOtherInfo);
										message.setHorizontalAlignment(SwingConstants.CENTER);
										message.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
										message.setFont(RL.scanMessage);
										
										GridBagConstraints c = new GridBagConstraints();
										c.gridx = 0;
										c.gridy = 0;
										c.fill = GridBagConstraints.HORIZONTAL;
										messageCenter.add(message, c);
									}
									public static void successfulSignIn(String firstLastName) {
										String outputMessage = firstLastName + " has signed in";
										message.setForeground(RL.ForestGreen);
										message.setText(outputMessage);
										BackEnd.logs.update.Logs(outputMessage);
										defaultAfter5();
									}
									public static void successfulSignOut(String firstLastName) {
										String outputMessage = firstLastName + " has signed out";
										message.setForeground(RL.ForestGreen);
										message.setText(outputMessage);
										BackEnd.logs.update.Logs(outputMessage);
										defaultAfter5();
									}
									public static void unsuccessful(int studentID) {
										message.setForeground(Color.RED);
										message.setText("Invalid Student ID number: " + studentID);
										BackEnd.logs.update.Logs("Could not find " + studentID + " in Student Data Base");
										defaultAfter5();
									}
									public static void integer(String input) {
										message.setForeground(Color.RED);
										message.setText("Please only enter numbers");
										BackEnd.logs.update.Logs(input + " is not an integer");
										defaultAfter5();
									}
									public static void manualSignIn() {
										message.setForeground(Color.BLACK);
										message.setText("All student have been manually signed in");
										
									}
									private static Timer timer;
									public static void timerCreate() {
										timer = new Timer(5000, null);
										timer.setInitialDelay(5000);
										timer.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												message.setForeground(Color.BLACK);
							    		    	message.setText(defaultOtherInfo);
											}
							    	    });
									}
									public static void defaultAfter5() {
										timer.restart();
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
										otherMessages.setText(config.defaultOtherMessage);
										otherMessages.setEditable(false);
										otherMessages.setFont(RL.otherMessage);
										otherMessages.setOpaque(false);
										otherMessages.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
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
									public static void updateFontSize() {
										otherMessages.setFont(RL.userOtherMessage);
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
								title.setText("Students Signed Out");
								title.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
								title.setFont(RL.tableTitle);
								titleBar.add(title, BorderLayout.LINE_START);
							}
						}
						public static class clearButton {
							static JButton clear = new JButton();
							public static void create() {
								clear.setText("Clear");
								clear.setFont(RL.tableClearButton);
								clear.setToolTipText("Signed all students back in");
								
								clear.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e) {
										if(BackEnd.database.Log.table.signAllIn()) {
											content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.manualSignIn();
											JOptionPane.showMessageDialog(frame, "Successfully signed in all students.", "Restroom Logs", JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(frame, "An Interal Error occured.", "Restroom Logs Error", JOptionPane.ERROR_MESSAGE);
										}
										content.majorRL.right.table.tablePane.tableContent.update();
									}
								});
								
								titleBar.add(clear, BorderLayout.LINE_END);
							}
						}
					}
					public static class tablePane {
						static JScrollPane tablePane = new JScrollPane();
						public static void create() {
							table.add(tablePane, BorderLayout.CENTER);
							tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							tablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							
							java.util.Timer timer = new java.util.Timer();
							timer.schedule(new TimerTask() {
								public void run() {
									tableContent.update();
								}
							}, 0, 15*1000);
						}
						public static class tableContent {
							static JTable tableContent = new JTable();	
							public static void create() {							
								String[] columnNames = {"First Name", "Last Name", "Time Out", "Duration (Min.)"};
								
								ArrayList<String[]> manSignedOutNames = new ArrayList<String[]>();
						        
								try {
							        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
									Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
									Statement s;
									s = conn.createStatement();
									
									ResultSet rs;
									rs = s.executeQuery("SELECT [FirstName], [LastName], [TimeOut] FROM ["+LogsDBTableName+"] WHERE [TimeIn] = '" + stillSignedOut + "'");
									
									while(rs.next()) {
										String[] tempEntry = new String[columnNames.length];
										tempEntry[0] = rs.getString(1); //first name
										tempEntry[1] = rs.getString(2); //last name
										tempEntry[2] = rs.getString(3); //time out
										
										Calendar currentCal = Calendar.getInstance();
										Date currentDate = currentCal.getTime();
										long currentMill = currentCal.getTimeInMillis();
										
										DateFormat  formatter = new SimpleDateFormat("hh:mm:ss a");
										Date outTemp = null;
										try {
											outTemp = formatter.parse(tempEntry[2]);
											outTemp.setDate(currentDate.getDate());
											outTemp.setMonth(currentDate.getMonth());
											outTemp.setYear(currentDate.getYear());
										} catch (ParseException e) {
											e.printStackTrace();
										}
										long out = outTemp.getTime();
										
										int total = (int) TimeUnit.MILLISECONDS.toMinutes(currentMill - out);
										tempEntry[3] = Integer.toString(total);
										
										//calc totalTimeOut
										//tempEntry[3] = currentTime - tempEntry[2].toTime();
										
										manSignedOutNames.add(tempEntry);
									}
						        } catch(ClassNotFoundException e) {
									BackEnd.logs.update.ERROR("Can not find JDBC class");
									e.printStackTrace();
								}
								catch(SQLException e){
									BackEnd.logs.update.ERROR("Could not access Database");
								}
								
						        int numOfEntries = manSignedOutNames.size();
								String[][] data = new String[numOfEntries][columnNames.length];
								
								for(int i = 0; i < manSignedOutNames.size(); i++) {
									for(int k = 0; k < columnNames.length; k++) {
										data[i][k] = manSignedOutNames.get(i)[k];
									}
								}
								
								tableContent = new JTable(data, columnNames);
								tableContent.setFont(RL.tableText);
								tableContent.setFillsViewportHeight(true);
								tableContent.setDefaultEditor(Object.class, null); //make table uneditable
								tableContent.setIntercellSpacing(new Dimension(5, 0));
								updateCellSize();
								tablePane.setViewportView(tableContent);
							}
							public static void update() {
								create();
							}
							public static void updateCellSize() {
								//can only be ran after data is added
								for (int row = 0; row < tableContent.getRowCount(); row++) {
									int rowHeight = tableContent.getRowHeight();
							        for (int column = 0; column < tableContent.getColumnCount(); column++) {
							            Component comp = tableContent.prepareRenderer(tableContent.getCellRenderer(row, column), row, column);
							            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
							        }
							        tableContent.setRowHeight(row, rowHeight);
							    }
							}
						}
					}
				}
			}
		}
		
	}
	
	public static class TimeListener implements Runnable{
		public static void time(){
			LocalDateTime localNow = LocalDateTime.now();
	        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
	        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
	        ZonedDateTime zonedNext5;
	        int hour = config.getActiveHoursHOUR();
	        int minute = config.getActiveHoursMINUTE();
	        zonedNext5 = zonedNow.withHour(hour).withMinute(minute).withSecond(0);
	        if(zonedNow.compareTo(zonedNext5) > 0)
	            zonedNext5 = zonedNext5.plusDays(1);

	        Duration duration = Duration.between(zonedNow, zonedNext5);
	        long initalDelay = duration.getSeconds();

	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
	        scheduler.scheduleAtFixedRate(new TimeListener(), initalDelay,
	                                      24*60*60, TimeUnit.SECONDS);
		}

		@Override
		public void run() {
			var.updateAll();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E");
    		LocalDateTime now = LocalDateTime.now();
    		String TimeAndData = dtf.format(now) ;
    		if(!TimeAndData.equals("Sat") && !TimeAndData.equals("Sun")) {
				if(BackEnd.database.Log.table.signAllIn()) {
					content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.manualSignIn();
					//JOptionPane.showMessageDialog(frame, "Successfully signed in all students.", "Restroom Logs", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "An Interal Error occured.", "Restroom Logs Error", JOptionPane.ERROR_MESSAGE);
				}
				content.majorRL.right.table.tablePane.tableContent.update();
				BackEnd.email.PDF.updatePDF();
				config.RlGPFO = config.getRlGPFO();
				if(config.getDailyEmails()) {
					BackEnd.email.sendPDF(config.PdfLogPath, "Restroom Logs PDF: " + config.getDate()+".pdf");
				}
    		}
		}
		
	}
	
	public static class tempSplash {
		private static JFrame splashFrame;
		public static void start() {
			splashFrame = new JFrame();
			
			ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project
			splashFrame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
	    	
	        //WINDOW SETTINGS
			splashFrame.setTitle("Restroom Logs");
			splashFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

			splashFrame.setUndecorated(true);
			splashFrame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
			splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
			content();
			show();
		}
		public static void show() {
			splashFrame.setVisible(true);
		}
		public static void hide() {
			splashFrame.setVisible(false);
		}
		public static void dispose() {
			splashFrame.dispose();
		}
		private static void content() {
			JPanel pane = new JPanel(new BorderLayout());
			pane.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
			splashFrame.getContentPane().add(pane);
			splashFrame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
			
			JLabel img;
			ImageIcon icon = new ImageIcon("assets/logos/RestroomLogsSplashscreen.png", "Splash Screen");
			img = new JLabel("", icon, JLabel.CENTER);
			
			pane.add(img, BorderLayout.CENTER);
		}
	}
	
}
