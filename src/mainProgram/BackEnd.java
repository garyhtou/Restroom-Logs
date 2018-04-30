package mainProgram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import mainProgram.FrontEnd.TimeListener;
import mainProgram.FrontEnd.content;
import mainProgram.FrontEnd.window.menuBar.log.customPDF.table;
/**
 * The Back End of the Restroom Logs Program
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
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 *
 */
public class BackEnd extends config{
	public static void main(String[] args) {
		MAIN.startUp();
		
	}
	public static class logs{
		//CREATE LOGS FOR INIT
		public static void create() {
			BufferedWriter bw = null;
			FileWriter fw = null;
			
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				File file = new File(LogsPath);
				file.createNewFile();
				fw = new FileWriter(file.getAbsoluteFile(), false);
				bw = new BufferedWriter(fw);
				bw.write("-•=•=•=•  LOGS  •=•=•=•-\n");
				bw.write("\tFile created on " + dtf.format(now) + "\n\n");
			}
			catch (IOException e) {

				e.printStackTrace();

			}
			finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}

			//updateLogs("-•=•=•=•  LOGS  •=•=•=•-");
			
		}
		/**
		 * Writes a String to the log file, should normally be called by other functions.
		 * @param data
		 */
		static void write(String data) {
			BufferedWriter bw = null;
			FileWriter fw = null;

			try {
				File file = new File(LogsPath);
				//FILE SHOULD ALREADY EXIST THROUGH initStartUp
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				// true = append file, append means to add, false = overwrite
				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);

				bw.write(data);

			}
			catch (IOException e) {

				e.printStackTrace();
				

			}
			finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}
		}
		/**
		 * Writes a String to the log file, should normally be called by other functions.
		 * @param data
		 */
		public static class update{
			/**
			 * adds a String to the Logs file as a Normal Message.<br>
			 * <strong>Format:</strong> yyyy/mm/dd HH:mm:ss  |    STRING_HERE<br>
			 * <strong> Format Example:</strong> 2017/11/23 16:55:32  |    Hello
			 * @param data The String that will be added to the Logs File
			 */
			public static void Logs(String data) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
	
				String TimeAndData = updateLogsPriority + " " + dtf.format(now) + "  |  " + data + "\n";
				write(TimeAndData);
			}
			
			/**
			 * adds a String to the Logs file as a Start Up.<br>
			 * @param data The String that will be added to the Logs File
			 */
			public static void StartUp(String StartUpData) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				String TimeAndStartUpData = StartUpPriority + " " + dtf.format(now) + "  |  " + "Start Up" + "  |  " + StartUpData + "\n";
				write(TimeAndStartUpData);
			}
			
			/**
			 * adds a String to the Logs file as an Error Message.<br>
			 * @param data The String that will be added to the Logs File
			 */
			public static void ERROR(String ERRORdata) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
	
				String TimeAndData = ErrorPriority + " " + dtf.format(now) + "  |  ERROR  |  "+ ERRORdata + "\n";
	
				write(TimeAndData);
			}
			
			/**
			 * adds a String to the Logs file as an Regular Message.<br>
			 * @param data The String that will be added to the Logs File
			 */
			public static void System(String SystemData) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
	
				String TimeAndData = SystemPriority + " " + dtf.format(now) + "  |  System  |  "+ SystemData + "\n";
	
				write(TimeAndData);
			}
		}
		
	}
	public static class email{
		/**
		 * Adds a Header to the PDF.<br>
		 * <strong>Format:</strong>  LOGO  Restroom Logs DATE<br>
		 */
		public static class HeaderFooter extends PdfPageEventHelper {
			 public static final String IMG1 = "assets/logos/RestroomLogsLogo.png";
		    private PdfTemplate t;
		    private Image total;
		    /**
		     * Initialized certain variables. Called when the document is opened.
		     * 
		     */
		    public void onOpenDocument(PdfWriter writer, Document document) {
		        t = writer.getDirectContent().createTemplate(30, 16);
		        try {
		            total = Image.getInstance(t);
		            total.setRole(PdfName.ARTIFACT);
		        } catch (DocumentException de) {
		            throw new ExceptionConverter(de);
		        }
		    }
		    /**
		     * Called when a page is finished, just before being written to the document. Adds header and footer to every page.<br>
		     *  </br>
		     */
		    @Override  
		    public void onEndPage(PdfWriter writer, Document document) {
		        addHeader(writer);
		        addFooter(writer);
		    }

		    /**
		     * Puts an image into a cell so that it can be placed into another cell.<br>
		     *  </br>
		     *  @param path The path of the image
		     * 
		     */
		    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		        Image img = Image.getInstance(path);
		        PdfPCell cell = new PdfPCell(img, true);
		        cell.setPaddingTop(25);
		        
		        cell.setBorder(Rectangle.BOTTOM);
		        cell.setBorderColor(BaseColor.LIGHT_GRAY);
		        return cell;
		    }
		    /**
		     * Adds the header<br>
		     *  </br>
		     *  
		     * 
		     */
		    private void addHeader(PdfWriter writer){
		        PdfPTable header = new PdfPTable(2);
		        try {
		            // set defaults
		            header.setWidths(new int[]{2, 24});
		            header.setTotalWidth(527);
		            header.setLockedWidth(true);
		            header.getDefaultCell().setFixedHeight(40);
		            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
		            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
		            
		            // add image

		           // header.addCell(logo);
		            header.addCell(createImageCell(IMG1));
		           // header.addCell(" ");

		            // add text
		            PdfPCell text = new PdfPCell();
		            text.setPaddingBottom(15);
		            text.setPaddingTop(20);
		            text.setPaddingLeft(10);
		            text.setBorder(Rectangle.BOTTOM);
		            text.setBorderColor(BaseColor.LIGHT_GRAY);
		            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy");
		    		LocalDateTime now = LocalDateTime.now();
		    		String TimeAndData = " Generated - "+dtf.format(now) ;
		            text.addElement(new Phrase("Restroom Logs PDF Log", new Font(Font.FontFamily.HELVETICA, 12)));
		            text.addElement(new Phrase(TimeAndData, new Font(Font.FontFamily.HELVETICA, 8)));
		            header.addCell(text);

		            // write content
		            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
		        } catch(DocumentException de) {
		            throw new ExceptionConverter(de);
		        } catch (MalformedURLException e) {
		            throw new ExceptionConverter(e);
		        } catch (IOException e) {
		            throw new ExceptionConverter(e);
		        }
		    }
		    /**
		     * Adds the footer<br>
		     *  </br>
		     *  
		     * 
		     */
		    private void addFooter(PdfWriter writer){
		        PdfPTable footer = new PdfPTable(3);
		        try {
		            // set defaults
		            footer.setWidths(new int[]{24, 2, 1});
		            footer.setTotalWidth(527);
		            footer.setLockedWidth(true);
		            footer.getDefaultCell().setFixedHeight(40);
		            footer.getDefaultCell().setBorder(Rectangle.TOP);
		            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

		            // add footer text
		            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		    		LocalDateTime now = LocalDateTime.now();
		    		String TimeAndData = dtf.format(now) ;
		    		
		            footer.addCell(new Phrase("Restroom Logs - Generated "+ TimeAndData , new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

		            // add current page count
		            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

		            // add placeholder for total page count
		            PdfPCell totalPageCount = new PdfPCell(total);
		            totalPageCount.setBorder(Rectangle.TOP);
		            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
		            footer.addCell(totalPageCount);

		            // write page
		            PdfContentByte canvas = writer.getDirectContent();
		            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
		            footer.writeSelectedRows(0, -1, 34, 50, canvas);
		            canvas.endMarkedContentSequence();
		        } catch(DocumentException de) {
		            throw new ExceptionConverter(de);
		        }
		    }
		    /**
		     * Is called before the document is closed. Adds the page number.<br>
		     *  </br>
		     *  
		     * 
		     */
		    public void onCloseDocument(PdfWriter writer, Document document) {
		        int totalLength = String.valueOf(writer.getPageNumber()).length();
		        int totalWidth = totalLength * 5;
		        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
		                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
		                totalWidth, 6, 0);
		    }
		}
		
		public static class PDF extends HeaderFooter{
			/**
			 * Creates a blank PDF
			 */
			public static void CreateBlankPDF() { //create blank PDF and Logs.txt for when the program initially starts up
				//Create PDF
				Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
				try {
					PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(PdfLogPath));
					HeaderFooter event = new HeaderFooter();
			        writer.setPageEvent(event);
					document.open();
					// The random space is needed because it PDF wont write if there isn't anything in the document
					document.add(new Paragraph(" "));
			
					document.close();
					writer.close();
				}
				catch(DocumentException e) {
					e.printStackTrace();
				}
				catch(FileNotFoundException e) {
					logs.update.ERROR(ErrorPriority+"The PDF dosen't exist at its path or it is inaccessible.");
					e.printStackTrace();
				}
				
			}
			/**
			 * Updates an existing PDF using information pulled from the Logs database
			 */
			public static void updatePDF() {
				try {
				Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);

				//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
				PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(PdfLogPath));
				HeaderFooter event = new HeaderFooter();
		        writer.setPageEvent(event);
				//needs to open the document
				document.open();
				//Adds a new paragraph to the pdf
				document.add(new Paragraph(" "));
				//created a new table with 3 columns to add to the pdf
				PdfPTable table= new PdfPTable(5);
				//sets the width percentage
				table.setWidthPercentage(105);
				//padding
				table.setSpacingBefore(1f);
				table.setSpacingAfter(11f);
				//width for each column
				float[] columnWidth= {0.5f,1f,1f,0.5f,0.5f};
				table.setWidths(columnWidth);
				//sets variables for each cell
				PdfPCell c1=new PdfPCell(new Paragraph("Student ID"));
				c1.setBackgroundColor(BaseColor.GRAY);
				PdfPCell c2=new PdfPCell(new Paragraph("First name"));
				c2.setBackgroundColor(BaseColor.GRAY);
				PdfPCell c3=new PdfPCell(new Paragraph("Last Name"));
				c3.setBackgroundColor(BaseColor.GRAY);
				PdfPCell c4=new PdfPCell(new Paragraph("Time Out"));
				c4.setBackgroundColor(BaseColor.GRAY);
				PdfPCell c5=new PdfPCell(new Paragraph("Time In"));
				c5.setBackgroundColor(BaseColor.GRAY);
				table.addCell(c1);
				table.addCell(c2);
				table.addCell(c3);
				table.addCell(c4);
				table.addCell(c5);
				
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				

				Connection conn=DriverManager.getConnection(
			        "jdbc:ucanaccess://"+LogsDBPath);

				Statement s;
					s = conn.createStatement();

				ResultSet rs;
				
				rs = s.executeQuery("SELECT [StudentID], [FirstName], [LastName], [TimeOut], [TimeIn] FROM ["+config.LogsDBTableName+"]");
				rs.next();
				table.addCell(rs.getString(1));
				table.addCell(rs.getString(2));			
				table.addCell(rs.getString(3));
				table.addCell(rs.getString(4));			
				table.addCell(rs.getString(5));			
				while (rs.next()) {
					table.addCell(rs.getString(1));
					table.addCell(rs.getString(2));
					table.addCell(rs.getString(3));
					table.addCell(rs.getString(4));
					table.addCell(rs.getString(5));
					}
					
				
				//adds the table on to the document
				document.add(table);

				//has to end by closing the document
				document.close();
				
				writer.close();
			}
				catch (ClassNotFoundException | SQLException | DocumentException | FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
		}
			/**
			 * Updates an existing PDF using selected tables from Table Array; information pulled from the Logs database
			 * @param selectedTables ArrayList of tables to generated the PDF from
			 */
			public static void updatePDF(ArrayList<String> selectedTables) {
				try {
					Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
					//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
					PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(PdfLogPath));
					HeaderFooter event = new HeaderFooter();
					writer.setPageEvent(event);
					//needs to open the document
					document.open();
					//Adds a new paragraph to the pdf
					//document.add(new Paragraph(" "));
					//created a new table with 3 columns to add to the pdf
					
					for(String Seltables: selectedTables) {
						document.newPage();
						String date  = Seltables.substring(Seltables.indexOf("s")+1);
						String date1  = date.substring(0,2)+"/";
						String date2  = date.substring(2,4)+"/";
						String date3  = date.substring(4);
					document.add(new Paragraph(date2+date1+date3));//Switched because Table name is DD/MM/YYY but we read it as MM/DD/YYYY
					document.add(new Paragraph(" "));

					PdfPTable table= new PdfPTable(5);
					//sets the width percentage
					table.setWidthPercentage(105);
					//padding
					table.setSpacingBefore(1f);
					table.setSpacingAfter(11f);
					//width for each column
					float[] columnWidth= {0.5f,1f,1f,0.5f,0.5f};
					table.setWidths(columnWidth);
					//sets variables for each cell
					PdfPCell c1=new PdfPCell(new Paragraph("Student ID"));
					c1.setBackgroundColor(BaseColor.GRAY);
					PdfPCell c2=new PdfPCell(new Paragraph("First name"));
					c2.setBackgroundColor(BaseColor.GRAY);
					PdfPCell c3=new PdfPCell(new Paragraph("Last Name"));
					c3.setBackgroundColor(BaseColor.GRAY);
					PdfPCell c4=new PdfPCell(new Paragraph("Time Out"));
					c4.setBackgroundColor(BaseColor.GRAY);
					PdfPCell c5=new PdfPCell(new Paragraph("Time In"));
					c5.setBackgroundColor(BaseColor.GRAY);
					table.addCell(c1);
					table.addCell(c2);
					table.addCell(c3);
					table.addCell(c4);
					table.addCell(c5);
					
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection conn=DriverManager.getConnection(
							"jdbc:ucanaccess://"+LogsDBPath);
					Statement s;
					s = conn.createStatement();
					ResultSet rs;
					
					rs = s.executeQuery("SELECT [StudentID], [FirstName], [LastName], [TimeOut], [TimeIn] FROM ["+Seltables+"]");
					rs.next();
					table.addCell(rs.getString(1));
					table.addCell(rs.getString(2));			
					table.addCell(rs.getString(3));
					table.addCell(rs.getString(4));			
					table.addCell(rs.getString(5));			
						while (rs.next()) {
							table.addCell(rs.getString(1));
							table.addCell(rs.getString(2));
							table.addCell(rs.getString(3));
							table.addCell(rs.getString(4));
							table.addCell(rs.getString(5));
						}
						document.add(table);


					
					
					//adds the table on to the document
					//has to end by closing the document
					}
					document.close();
					
					writer.close();
				}
				catch (ClassNotFoundException | SQLException | DocumentException | FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
			}
	}
		
		/*public static class TimeListener implements Runnable{
			public static void time(){
				LocalDateTime localNow = LocalDateTime.now();
		        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
		        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
		        ZonedDateTime zonedNext5 ;
		        zonedNext5 = zonedNow.withHour(15).withMinute(0).withSecond(0);
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
				System.out.println("TIME");
				if(config.dailyEmails)
					send();
				if(BackEnd.database.Log.table.signAllIn()) {
					content.majorRL.left.statsScan.scanAndMessages.scan.messageCenter.scanEntryMessage.manualSignIn();
					JOptionPane.showMessageDialog(frame, "Successfully signed in all students.", "Restroom Logs", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "An Interal Error occured.", "Restroom Logs Error", JOptionPane.ERROR_MESSAGE);
				}
				content.majorRL.right.table.tablePane.tableContent.update();
				//TODO:MOVE TO FRONT END SAME LVL AS WINDOW AND CONTENT
				//TODO:CLEAR FROM LOG DB
				//TODO:SIGN IN ALL STUDENTS
				//TODO:MAKE SURE TO UPDATE TABLE
				
			}
			
		}*/
		public static void sendPDF(String filePath, String emailedFileName) {
			
			
			 // Recipient's email ID needs to be mentioned.
		      String to = config.teacherEmail.toString();
		      String from =config.emailSenderName;
		      // Get system properties
		      Properties properties = System.getProperties();
		      // Setup mail server
		      properties.put("mail.smtp.host", "smtp.gmail.com");
		      properties.put("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.port", "465");
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties,
		  			new javax.mail.Authenticator() {
		  				protected PasswordAuthentication getPasswordAuthentication() {
		  					return new PasswordAuthentication(config.emailSender,config.RlGPFO);
		  				}
		  			});
		      try {
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject(config.emailSubjectPDF);

		         // Create the message part 
		         BodyPart messageBodyPart = new MimeBodyPart();

		         // Fill the message
		         messageBodyPart.setText(config.emailBodyPDF);
		         
		         // Create a multipart message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);

		         // Part two is attachment
		         messageBodyPart = new MimeBodyPart();

		         DataSource source = new FileDataSource(filePath);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(emailedFileName);
		         multipart.addBodyPart(messageBodyPart);

		         // Send the complete message parts
		         message.setContent(multipart);

		         // Send message
		         Transport.send(message);
		         BackEnd.logs.update.System("PDF Email Sent Successfully");
		         File file = new File(filePath);
		         if(file.delete()) {
					BackEnd.logs.update.System("PDF Deleted from System");
		         }
		      } catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
			
		}
		public static void sendPDF(String filePath, String emailedFileName, ArrayList<String> tables) {
			
			
			// Recipient's email ID needs to be mentioned.
			String to = config.teacherEmail.toString();
			String from =config.emailSenderName;
			// Get system properties
			Properties properties = System.getProperties();
			// Setup mail server
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(config.emailSender,config.RlGPFO);
				}
			});
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);
				
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));
				
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				// Set Subject: header field
				message.setSubject(config.emailSubjectPDFMul);
				
				// Create the message part 
				BodyPart messageBodyPart = new MimeBodyPart();
				
				// Fill the message
				String dates = "";
				for(String Seltables: tables) {
					String date  = Seltables.substring(Seltables.indexOf("s")+1);
					String date1  = date.substring(0,2)+"/";
					String date2  = date.substring(2,4)+"/";
					String date3  = date.substring(4);
					dates += date2+date1+date3+", ";
					}
				messageBodyPart.setText(config.emailBodyPDFMul1+dates+config.emailBodyPDFMul2);
				
				// Create a multipart message
				Multipart multipart = new MimeMultipart();
				
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				
				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				
				DataSource source = new FileDataSource(filePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(emailedFileName);
				multipart.addBodyPart(messageBodyPart);
				
				// Send the complete message parts
				message.setContent(multipart);
				
				// Send message
				Transport.send(message);
				BackEnd.logs.update.System("PDF Email Sent Successfully");
				File file = new File(filePath);
				if(file.delete()) {
					BackEnd.logs.update.System("PDF Deleted from System");
				}
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
			
		}
		public static void sendTXT() {
			
			
			// Recipient's email ID needs to be mentioned.
			String to = config.teacherEmail.toString();
			String from =config.emailSenderName;
			String host = "localhost";
			// Get system properties
			Properties properties = System.getProperties();
			// Setup mail server
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");
			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(config.emailSender,config.RlGPFO);
				}
			});
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);
				
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));
				
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				// Set Subject: header field
				message.setSubject(config.emailSubjectTXT);
				
				// Create the message part 
				BodyPart messageBodyPart = new MimeBodyPart();
				
				// Fill the message
				messageBodyPart.setText(config.emailBodyTXT);
				
				// Create a multipart message
				Multipart multipart = new MimeMultipart();
				
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				
				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				String filename = config.LogsPath;
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName("Restroom Logs Program System Logs Text: " + config.getDate());
				multipart.addBodyPart(messageBodyPart);
				
				// Send the complete message parts
				message.setContent(multipart);
				
				// Send message
				Transport.send(message);
		         BackEnd.logs.update.System("TXT Email Sent Successfully");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
			
		}
	}
	public static class database{
		/**
		 * Adds information into databases
		 * @author gar.tou000
		 *
		 */
		public static class Log {
			/**
			 * Adds an entry to the log
			 * @author gar.tou000
			 *
			 */
			public static class add{
				/**
				 * Adds an Entry or Exit to the Logs Database
				 * @param StudentID Student's ID
				 * @param FirstName Student's First Name
				 * @param LastName Student's Last Name
				 * @param signingOut true = signing out, false = signing in
				 */
				public static void entry(int StudentID, String FirstName, String LastName, boolean signingIn) {
					boolean signingOut = !signingIn;
					//Getting current sys time
					Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			        
					//Adding to DB
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
						Statement s;
						s = conn.createStatement();						
						if(signingOut) {
							String q = "INSERT INTO "+LogsDBTableName+" ([StudentID], [FirstName], [LastName], [TimeOut], [TimeIn]) VALUES (?, ?, ?, ?, ?)";
							PreparedStatement st = conn.prepareStatement (q);
							
							st.setInt(1, StudentID);
							st.setString(2, FirstName);
							st.setString(3, LastName);
							st.setString(4, sdf.format(cal.getTime()));
							st.setString(5, stillSignedOut);
							
							
							st.executeUpdate();
						}
						else {
							//getting row # of latest sign out
							int row;
							try {
								Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

								Connection conn2=DriverManager.getConnection(
							        "jdbc:ucanaccess://"+config.LogsDBPath);
								Statement s2;
								s2 = conn2.createStatement();
								
								
								ResultSet rs;
								rs = s2.executeQuery("SELECT [ID] FROM [" + config.LogsDBTableName + "] WHERE " + "[StudentID] = '" + StudentID + "' ORDER BY ID DESC");
								
								rs.next(); //move into table
								
								row = rs.getInt(1);
							} catch (SQLException | ClassNotFoundException e) {
								System.err.println("ERROR");
								e.printStackTrace();
								row = -1;
							}
							//using row # to update field
							if(row == -1) {
								System.err.print(StudentID + " not found in Databse at " + config.LogsDBPath);
							}
							else {
								try {
									Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
								} catch (ClassNotFoundException e) {
									BackEnd.logs.update.ERROR("Can not access UcanaccessDriver");
									System.err.println("Can not access UcanaccessDriver");
									e.printStackTrace();
								}
								
								
								//establishing connection to DB
								Connection conn3 = null;
								try {
									conn3 = DriverManager.getConnection("jdbc:ucanaccess://"+config.LogsDBPath);
								} catch (SQLException e) {
									BackEnd.logs.update.ERROR("Can not access Logs Database at " + config.LogsDBPath);
									System.err.println("Can not access Logs Database at " + config.LogsDBPath);
									e.printStackTrace();
								}
								
								String updateInTime = "UPDATE [" + config.LogsDBTableName + "] SET [TimeIn] = ? WHERE " + "[ID] = '" + row + "'";
								//creating statement
								PreparedStatement st = null;
								try {
									st = conn3.prepareStatement (updateInTime);
								} catch (SQLException e) {
									BackEnd.logs.update.ERROR("Unable execute query, \"" + updateInTime + "\"");
									System.err.println("Unable execute query, \"" + updateInTime + "\"");
									e.printStackTrace();
								}
								
								String currentTime = (new SimpleDateFormat("hh:mm:ss").format(new Date()));
								
								try {
									st.setString(1, currentTime);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
								
								try {
									st.executeUpdate();
									st.close();
								} catch (SQLException e) {
									System.err.println("Unable to Update In Time");
									e.printStackTrace();
								}
							}
							
						}
					}
					catch(ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can not find JDBC class");
						e.printStackTrace();
					}
					catch(SQLException e){
						BackEnd.logs.update.ERROR("Could not access Database");
						
					}
				}
			}
			
			public static class table{ 
				public static boolean signAllIn() {
					Calendar cal = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			        boolean successful = false;
			        ArrayList<String> manSignedOutNames = new ArrayList<String>();
			        
			        //collect names
			        try {
				        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
						Statement s;
						s = conn.createStatement();
						
						ResultSet rs;
						rs = s.executeQuery("SELECT [FirstName], [LastName] FROM ["+LogsDBTableName+"] WHERE [TimeIn] = '" + stillSignedOut + "'");
						
						while(rs.next()) {
							manSignedOutNames.add(rs.getString(1) + " " + rs.getString(2));
						}
			        } catch(ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can not find JDBC class");
						e.printStackTrace();
					}
					catch(SQLException e){
						BackEnd.logs.update.ERROR("Could not access Database");
					}
			        
			        //print to logs
			        if(successful) {
			        	for(int i = 0; i < manSignedOutNames.size(); i++) {
			        		String output = manSignedOutNames.get(i) + " was manually signed out";
			        		BackEnd.logs.update.Logs(output);
			        	}
			        }
			        
			        //chnage in database
			        try {
				        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
						Statement s;
						s = conn.createStatement();
						
						String q = "UPDATE ["+LogsDBTableName+"] SET [TimeIn] = 'Manually Signed Out " + sdf.format(cal.getTime()) + "' WHERE [TimeIn] = '" + stillSignedOut + "'";
						PreparedStatement st = conn.prepareStatement (q);
						
						st.executeUpdate();
						
						successful = true;
			        } catch(ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can not find JDBC class");
						e.printStackTrace();
					}
					catch(SQLException e){
						BackEnd.logs.update.ERROR("Could not access Database");
					}
			        
			        //print to logs
			        if(successful) {
			        	for(int i = 0; i < manSignedOutNames.size(); i++) {
			        		String output = manSignedOutNames.get(i) + " was manually signed out";
			        		BackEnd.logs.update.Logs(output);
			        	}
			        }
			        
			        return successful;
				}
				
				/**
				 * Creates new table at 
				 * {@value config#LogsDBPath} 
				 * 
				 */
				public static void create() {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
					
					Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
					
					Statement s;
					s = conn.createStatement();

					ResultSet rs;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
					LocalDateTime now = LocalDateTime.now();
					
					String date = dtf.format(now);
					String q = "CREATE TABLE Logs"+date+" (ID AUTOINCREMENT PRIMARY KEY , StudentID int, FirstName varchar(255), LastName varchar(255), TimeOut varchar(255), TimeIn varchar(255) )";
					PreparedStatement st = conn.prepareStatement (q);
					st.executeUpdate();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
				/**
				 * Deletes Table with name parameter tableName
				 * @param tableName
				 */
				public static void delete(String tableName) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
						Statement s= conn.createStatement();
						ResultSet rs;
						
						String q = "DROP TABLE "+tableName;
						PreparedStatement st = conn.prepareStatement (q);
						st.executeUpdate();
					} catch (SQLException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			/**
			 * Checks if a student is currently signed in or out
			 * @param studentID the student ID to be checked
			 * @return true or false;
			 */public static boolean checkIfOut(int studentID){
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

					Connection conn=DriverManager.getConnection(
				        "jdbc:ucanaccess://"+config.LogsDBPath);
					Statement s;
					s = conn.createStatement();
								
					ResultSet rs;
					rs = s.executeQuery("SELECT [TimeIn] FROM [" + config.LogsDBTableName + "] WHERE " + "[StudentID] = '" + studentID + "' AND []TimeIn = '" + stillSignedOut + "'");
					rs.next(); //move into table
					String content = rs.getString(1);
					if(content.equals(stillSignedOut)) {
						return true;
					} else {
						return false;
					}
				} catch (SQLException | ClassNotFoundException e) {
					return false;
					
				}
			}
			public static String[] getAllTableName() {
				ArrayList<String> list = new ArrayList<String>();
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				

				Connection conn=DriverManager.getConnection(
			        "jdbc:ucanaccess://"+config.LogsDBPath);
				Statement s;
				s = conn.createStatement();
				
				
				ResultSet rs;
				rs = conn.getMetaData().getTables(null, null, null, null);
				
				while(rs.next()) {
					list.add(rs.getString("TABLE_NAME"));
				}
				
				} catch (ClassNotFoundException e) {
					BackEnd.logs.update.ERROR("Can't find jdbc Driver");
					return null;
				} catch (SQLException e) {
					BackEnd.logs.update.ERROR("Error while getting all table names from Logs database");
					return null;
				}
				
				String[] arrList = list.toArray(new String[0]);
				return arrList;
			}
			public static void createTodayTable() {
				boolean alreadyCreatedTable = false;
				for(int i = 0; i < BackEnd.database.Log.getAllTableName().length; i++) {
					if(BackEnd.database.Log.getAllTableName()[i].equals(config.LogsDBTableName)) {
						alreadyCreatedTable = true;
					}
				}
				if(!alreadyCreatedTable) {
					BackEnd.database.Log.table.create();
				}
			}
		}

		public static class Student{
			public static void add(int studentID, String firstName, String lastName)  {					
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
					
					Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+StudentDBPath);
					
					Statement s;
					s = conn.createStatement();

					ResultSet rs;
					String q = "INSERT INTO "+StudentDBTableName.toString()+" ([StudentID], [FirstName], [LastName]) VALUES (?, ?, ?)";
					PreparedStatement st = conn.prepareStatement (q);
					st.setString(1, Integer.toString(studentID));
					st.setString(2, firstName);
					st.setString(3, lastName);
					st.executeUpdate();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			public static class pullStudentName {
				public static String firstName(int studentID) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					

					Connection conn=DriverManager.getConnection(
				        "jdbc:ucanaccess://"+config.StudentDBPath);
					Statement s;
					s = conn.createStatement();
					
					
					ResultSet rs;
					rs = s.executeQuery("SELECT [StudentID], [FirstName], [LastName] FROM ["+StudentDBTableName.toString()+"] WHERE [StudentID] = '"+ studentID+"'");
					
					rs.next();
					String firstName = rs.getString(2);
					return firstName;
					} catch (ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can't find jdbc Driver");
						return null;
					} catch (SQLException e) {
						BackEnd.logs.update.ERROR("Student ID \"" + studentID + "\" does not exist");
						return null;
					}
				}
				public static String lastName(int studentID) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					

					Connection conn=DriverManager.getConnection(
				        "jdbc:ucanaccess://"+config.StudentDBPath);
					Statement s;
					s = conn.createStatement();
					
					
					ResultSet rs;
					rs = s.executeQuery("SELECT [StudentID], [FirstName], [LastName] FROM ["+StudentDBTableName.toString()+"] WHERE [StudentID] = '"+ studentID+"'");
					
					rs.next();
					String lastName = rs.getString(3);
					return lastName;
					} catch (ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can't find jdbc Driver");
						return null;
					} catch (SQLException e) {
						BackEnd.logs.update.ERROR("Student ID \"" + studentID + "\" does not exist");
						return null;
					}
				}
				public static String bothNames(int studentID) {
					return firstName(studentID) + " " + lastName(studentID);
				}
				
				public static boolean containsOnlyNumbers(String str) {
				    for (int i = 0; i < str.length(); i++) {
				      if (!Character.isDigit(str.charAt(i)))
				        return false;
				    }
				    return true;
				}
			}
			
			/**
			 * @deprecated NOT DONE YET
			 * Return name of student based of Student ID
			 * @param name Name of student
			 * @return student ID number
			 */
			public static int findStudentID(String name) {
				
				
				
				return 0;
			}
			
			/**
			 * get information about student's entries 
			 * @param studentID Student ID
			 * @return {"first name", "last name"}, {table info}, {num of exits, avg duration, realistic avg dur}
			 */
			public static ArrayList<Object> getStudentInfo(int studentID){
				ArrayList<Object> arr = new ArrayList<Object>();
				//student name
				arr.add(new String[] {
						pullStudentName.firstName(studentID), 
						pullStudentName.lastName(studentID)});
				//table
				String[] columnNames = {"Date", "Time Out", "Time In", "Duration (Min.)"};
				ArrayList<String[]> dataArr = new ArrayList<String[]>();
				
				String[] tableNames = BackEnd.database.Log.getAllTableName();
				
				for(String currentTable : tableNames) {
					try {
				        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
						Statement s;
						s = conn.createStatement();
						
						ResultSet rs;
						rs = s.executeQuery("SELECT [TimeOut], [TimeIn] FROM ["+currentTable+"] WHERE [StudentID] = '" + studentID + "'");
						
						while(rs.next()) {
							String[] tempEntry = new String[columnNames.length];
							
							String date  = currentTable.substring(currentTable.indexOf("s")+1);
							String day = date.substring(0,2);
							String month  = date.substring(2,4);
							String year = date.substring(4);
							
							tempEntry[0] = month+"/"+day+"/"+year; //date
							tempEntry[1] = rs.getString(1); //time out
							tempEntry[2] = rs.getString(2); //time in
							
							Calendar currentCal = Calendar.getInstance();
							Date currentDate = currentCal.getTime();
							
							DateFormat  formatter = new SimpleDateFormat("hh:mm:ss a");
							Date outTemp = null;
							try {
								outTemp = formatter.parse(tempEntry[1]);
								outTemp.setDate(Integer.parseInt(day));
								outTemp.setMonth(Integer.parseInt(month));
								outTemp.setYear(Integer.parseInt(year));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							long out = outTemp.getTime();
							
							DateFormat  formatter2 = new SimpleDateFormat("hh:mm:ss");
							Date inTemp = null;
							try {
								inTemp = formatter2.parse(tempEntry[1]);
								inTemp.setDate(Integer.parseInt(day));   
								inTemp.setMonth(Integer.parseInt(month));
								inTemp.setYear(Integer.parseInt(year));  
							} catch (ParseException e) {
								e.printStackTrace();
							}
							long in = inTemp.getTime();
							System.out.println("out: " + out + "\nin:" + in + "\n\n");
							int total = (int) TimeUnit.MILLISECONDS.toMinutes(in - out);
							tempEntry[3] = Integer.toString(total);
							dataArr.add(tempEntry);
						}
			        } catch(ClassNotFoundException e) {
						BackEnd.logs.update.ERROR("Can not find JDBC class");
						e.printStackTrace();
					}
					catch(SQLException e){
						BackEnd.logs.update.ERROR("Could not access Database");
					}
				}
				
for(String[] str : dataArr) {
	System.out.println(Arrays.toString(str));
}
				
				String[][] data = new String[dataArr.size()][columnNames.length];
				for(int i = 0; i < dataArr.size(); i++) {
					for(int k = 0; k < columnNames.length; k++) {
						data[i][k] = dataArr.get(i)[k];
					}
				}
				
				JTable table = new JTable(data, columnNames);
				arr.add(table);
				
				ArrayList<String> statsArr = new ArrayList<String>();
				//num of exits
				statsArr.add(Integer.toString(data.length));
				
				int totalDur = 0;
				int avgDurMin = 0;
				int avgDurSec = 0;
				int realAvgDurMin = 0;
				int realAvgDurSec = 0;
				if(data.length > 0) {
					//average duration
					for(int i = 0; i <data.length; i++) {
						totalDur += Integer.parseInt(data[i][3]);
					}
					double avgDurRaw = totalDur/data.length;
					avgDurMin = (int)(avgDurRaw);
					avgDurSec = (int)((((int)(avgDurRaw*10))%100)/100.0*60);
					
					String avgDurStr = avgDurMin + ":" + avgDurSec;
					statsArr.add(avgDurStr);
					
					//realistic average duration
					int realisticTotalDur = 0;
					int unrealisticEntry = 0;
					for(int i = 0; i <data.length; i++) {
						int entry = Integer.parseInt(data[i][3]);;
						if(entry <= 20) {
							realisticTotalDur += entry;
						} else {
							unrealisticEntry++;
						}
					}
					double realAvgDurRaw = realisticTotalDur/(data.length-unrealisticEntry);
					realAvgDurMin = (int)(realAvgDurRaw);
					realAvgDurSec = (int)((((int)(realAvgDurRaw*10))%100)/100.0*60);
					
					String realAvgDurStr = realAvgDurMin + ":" + realAvgDurSec;
					statsArr.add(realAvgDurStr);
				} else {
					statsArr.add("0"); //avg dur
					statsArr.add("0"); //real avg dur
				}
				
				String[] stats = new String[statsArr.size()];
				for(int i = 0; i < statsArr.size(); i++) {
					stats[i] = statsArr.get(i);
				}
				arr.add(stats);
				return arr;
			}
		}
		public static class clear{
			/**
			 * Clears the log Database<br>Caution, this removes all student entry and exits.
			 * 
			 * @throws ClassNotFoundException
			 * @throws SQLException
			 */
			public static void LogsDB() {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
					Statement s= conn.createStatement();
					ResultSet rs;
					
					String q = "DELETE FROM "+LogsDBTableName;
					PreparedStatement st = conn.prepareStatement (q);
					st.executeUpdate();
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			/**
			 * Clears the log Database with parameter name<br>Caution, this removes all student entry and exits.
			 * @param name
			 * @throws ClassNotFoundException
			 * @throws SQLException
			 */
			public static void LogsDB(String name) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
					Statement s= conn.createStatement();
					ResultSet rs;
					
					String q = "DELETE FROM "+name;
					PreparedStatement st = conn.prepareStatement (q);
					st.executeUpdate();
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			/**
			 * @deprecated THERE SHOULD BE NO NEED TO CLEAR THE STUDENT DB
			 * Clears the student Database<br>Caution, this removes all students.
			 * 
			 * @throws ClassNotFoundException
			 * @throws SQLException
			 */
			public static void StudentDB() {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+StudentDBPath);
					Statement s = conn.createStatement();
					ResultSet rs;
					
					String q = "DELETE FROM "+StudentDBTableName.toString();
					PreparedStatement st = conn.prepareStatement (q);
					st.executeUpdate();
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
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
	        int hour = 0;
	        int minute = 1;
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
			BackEnd.database.Log.table.create();
			config.LogsDBTableName = "Logs" + config.getDate();
			
		}
	}
	public static void create() {
		BackEnd.TimeListener.time(); //start 12am timer
		config.LogsDBTableName = "Logs" + config.getDate();
		BackEnd.database.Log.createTodayTable();
	}
}

