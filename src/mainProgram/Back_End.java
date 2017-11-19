package mainProgram;


import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import net.ucanaccess.*;

import org.apache.commons.io.*; //used to copy PDF

//http://www.java2s.com/Code/Jar/i/Downloaditext502jar.htm
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Back_End {
	
	
	//FIXME: try using a .dbf instead of accdb
	
	
	
	
	
	
//FOLLOWING 3 METHODS ARE FOR INIT FOR CLEARING LOGS----------------
	
//CREATE PDF FOR INIT
	public static void createPDF() { //create blank PDF and Logs.txt for when the program initially starts up
		//Create PDF
		Document document = new Document();
		try {
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("data/LogsPDF.pdf"));
			document.open();
			document.add(new Paragraph("Restroom Logs  - PDF Logs"));
			document.add(new Paragraph(""));
			document.add(new Paragraph("This table shows student name with entry and exit time"));
			document.add(new Paragraph(""));
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
		File source = new File("data\\LogsPDF.pdf");
		File dest = new File("data\\ViewLogsPDF.pdf");
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
	public static void updatePDF(/*String firstName, String lastName, int timeOut, int timeIn*/) { //Michael, you will need to learn how to edit cells that have already been added, ask me why at school.
		Document document = new Document();
		
		try {
			//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("data/LogsPDF.pdf"));
			PdfWriter writerCopy =PdfWriter.getInstance(document, new FileOutputStream("data/ViewLogsPDF.pdf"));
			//needs to open the document
			document.open();
			//Adds a new paragraph to the pdf
			document.add(new Paragraph("Restroom Logs  - PDF Logs"));
			document.add(new Paragraph(""));
			document.add(new Paragraph("This table shows student name with entry and exit time"));
			document.add(new Paragraph(""));
			//created a new table with 3 columns to add to the pdf
			PdfPTable table= new PdfPTable(3);
			//sets the width percentage
			table.setWidthPercentage(105);
			//padding
			table.setSpacingBefore(1f);
			table.setSpacingAfter(11f);
			//width for each column
			float[] columnWidth= {3f,0.5f,2f};
			table.setWidths(columnWidth);
			//sets variables for each cell
			PdfPCell c1=new PdfPCell(new Paragraph("Column1"));
			PdfPCell c2=new PdfPCell(new Paragraph("Column2"));
			PdfPCell c3=new PdfPCell(new Paragraph("Column3"));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			//every 3 cells is a new row
			table.addCell("1");
			table.addCell("2");
			table.addCell("3");
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
//UPDATE LOGS
	public static void updateLogs(String data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = dtf.format(now) + "  |  " + data + "\n";

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

			bw.write(TimeAndData);

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
//GET DATA BASE INFO
	public static void getDBData() throws SQLException, ClassNotFoundException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess:\\data\\TestDB.accdb");
			 System.out.println("1");

		Statement s;
		 System.out.println("1");

			s = conn.createStatement();
			 System.out.println("1");

		ResultSet rs;
		 System.out.println("1");

			rs = s.executeQuery("SELECT [FirstName] FROM [Test]");
			 System.out.println("1");

			 System.out.println(rs.getString(1));
			 System.out.println("1");

		while (rs.next()) {
		    System.out.println(rs.getString(1));
			 System.out.println("1");

		}

	}
	
	
//MAIN METHOD
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//only call a method here for testing, making to remove it after testing
		//updatePDF();
		//getDBData();
		//createLogs();
		//updateLogs("call Back_End.updateLogs(\"YOUR MESSAGE\") to add to this log");
		
		
	}
}

