package mainProgram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class logs {

//FOLLOWING 3 METHODS ARE FOR INIT FOR CLEARING LOGS----------------
	
//CREATE PDF FOR INIT
	public static void createPDF() { //create blank PDF and Logs.txt for when the program initially starts up
		//Create PDF
		Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
		try {
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("data/LogsPDF.pdf"));
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	        writer.setPageEvent(event);
			document.open();
			// The random space is needed because it PDF wont write if there isn't anything in the document
			document.add(new Paragraph(" "));
			/*document.add(new Paragraph("Restroom Logs  - PDF Logs"));
			document.add(new Paragraph(""));
			document.add(new Paragraph("This table shows student name with entry and exit time"));
			document.add(new Paragraph(""));*/
			document.close();
			writer.close();
		}
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
//CREATE VIEW PDF FOR INIT
	public static boolean createViewPDF() {
		//copy PDF
		boolean error = false; //flag boolean, if true, it will show a dialog
		File source = new File("data/LogsPDF.pdf");
		File dest = new File("data/ViewLogsPDF.pdf");
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		    error = true;
		}
		return error;
	}
//CREATE LOGS FOR INIT
	public static void createLogs() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			File file = new File("data\\Logs.txt");
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
	


//FOLLOWING METHODS ARE FOR UPDATING PDF OR LOG-----------------------------

//UDPATE PDF, call createViewPDF for viewpdf
	public static void updatePDF() throws ClassNotFoundException, SQLException { 
		Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
		
		try {
			//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("data/LogsPDF.pdf"));
			PdfWriter writerCopy =PdfWriter.getInstance(document, new FileOutputStream("data/ViewLogsPDF.pdf"));
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	        writer.setPageEvent(event);
	        writerCopy.setPageEvent(event);
			//needs to open the document
			document.open();
			//Adds a new paragraph to the pdf
			/*document.add(new Paragraph("Restroom Logs  - PDF Logs"));
			document.add(new Paragraph(""));
			document.add(new Paragraph("This table shows student name with entry and exit time"));*/
			document.add(new Paragraph(" "));
			//created a new table with 3 columns to add to the pdf
			PdfPTable table= new PdfPTable(4);
			//sets the width percentage
			table.setWidthPercentage(105);
			//padding
			table.setSpacingBefore(1f);
			table.setSpacingAfter(11f);
			//width for each column
			float[] columnWidth= {1f,1f,2f,0.5f};
			table.setWidths(columnWidth);
			//sets variables for each cell
			PdfPCell c1=new PdfPCell(new Paragraph("First Name"));
			c1.setBackgroundColor(BaseColor.GRAY);
			PdfPCell c2=new PdfPCell(new Paragraph("Last Name"));
			c2.setBackgroundColor(BaseColor.GRAY);
			PdfPCell c3=new PdfPCell(new Paragraph("Time Out"));
			c3.setBackgroundColor(BaseColor.GRAY);
			PdfPCell c4=new PdfPCell(new Paragraph("Time In"));
			c4.setBackgroundColor(BaseColor.GRAY);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			//every 3 cells is a new row
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://data/TestDB.accdb");

		Statement s;
		 

			s = conn.createStatement();

		ResultSet rs;

			rs = s.executeQuery("SELECT FirstName, LastName, TimeOut, TimeIn FROM [PdfLogs]");
			rs.next();
			table.addCell(rs.getString(1));
			table.addCell(rs.getString(2));
			table.addCell(rs.getString(3));
			table.addCell(rs.getString(4));
			while (rs.next()) {
				table.addCell(rs.getString(1));
				table.addCell(rs.getString(2));
				table.addCell(rs.getString(3));
				table.addCell(rs.getString(4));
			    
			    
	
			}
			
			
			for(int i=0;i<3;i++) {
			
			}
			
			//adds the table on to the document
			document.add(table);

		
			//has to end by closing the document
			document.close();
			
			writerCopy.close();
			writer.close();
		}
		//Error stuff so the code doesn't break
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static void addLogInfoToDB() throws ClassNotFoundException, SQLException {
	Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
		
	
			//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://data/TestDB.accdb");

		Statement s;
		 

			s = conn.createStatement();

		ResultSet rs;

			rs = s.executeQuery("SELECT FirstName, LastName, TimeOut, TimeIn FROM [PdfLogs]");
			rs.next();
			rs.getString(1);
			rs.getString(2);
			rs.getString(3);
			rs.getString(4);
			
			while (rs.next()) {
				rs.getString(1);
				rs.getString(2);
				rs.getString(3);
				rs.getString(4);
			}
			    
			    
	
			
			
			
			
			
			
			//adds the table on to the document
			
		
		//Error stuff so the code doesn't break
		
	}
//UPDATE LOGS
	/**
	 * Writes a String to the log file, should normally be called by other functions.
	 * @param data
	 */
	public static void writeToLogs(String data) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File("data\\Logs.txt");
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
	 * adds a String to the Logs file as a Normal Message.<br>
	 * <strong>Format:</strong> yyyy/mm/dd HH:mm:ss  |    STRING_HERE<br>
	 * <strong> Format Example:</strong> 2017/11/23 16:55:32  |    Hello
	 * @param data The String that will be added to the Logs File
	 */
	public static void updateLogs(String data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = dtf.format(now) + "  |  " + data + "\n";
		writeToLogs(TimeAndData);
	}
	
	/**
	 * adds a String to the Logs file as a Start Up.<br>
	 * @param data The String that will be added to the Logs File
	 */
	final static String StartUpPriority = "***"; //Out of 5
	public static void updateLogsStartUp(String StartUpData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		String TimeAndStartUpData = StartUpPriority + dtf.format(now) + "  |  " + "Start Up" + "  |  " + StartUpData + "\n";
		writeToLogs(TimeAndStartUpData);
	}
	
	/**
	 * adds a String to the Logs file as an Error Message.<br>
	 * @param data The String that will be added to the Logs File
	 */
	final static String ErrorPriority = "***"; //Out of 5
	public static void updateLogsERROR(String ERRORdata) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = ErrorPriority + dtf.format(now) + "  |  ERROR  |  "+ ERRORdata + "\n";

		writeToLogs(TimeAndData);
	}
	
}
