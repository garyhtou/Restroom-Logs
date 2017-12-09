package mainProgram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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

	static String StudentDBPath = config.StudentDBPath; 
	static String LogsDBPath = config.LogsDBPath;
	static String PdfLogPath = config.PdfLogPath; 
	static String PdfLogViewPath = config.PdfLogViewPath; 
	static String DBTableName = config.DBTableName; 
	static String LogsTableName = config.LogsDBTableName; 
	static String LogsPath = config.LogsPath; 
	
	private final static String SystemPriority = config.SystemPriority;
	private final static String ErrorPriority = config.ErrorPriority;
	private final static String StartUpPriority = config.StartUpPriority;
	private final static String updateLogsPriority = config.updateLogsPriority;
//FOLLOWING 3 METHODS ARE FOR INIT FOR CLEARING LOGS----------------
	
	/**
	 * For Testing purposes only <br>
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, DocumentException {
		//To add fields in logs db five times
		/*for(int i =0; i<5;i++)
			addEntryToLogDB();*/
		//need to add intime at where studentid
		addTimeInToLogDB("12345");
		//then update pdf to match the db
		updatePDF();
		//then if needed clear the db
		//clearLogsDB();
	}
//CREATE PDF FOR INIT
	public static void createPDF() { //create blank PDF and Logs.txt for when the program initially starts up
		//Create PDF
		Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);
		try {
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(PdfLogPath));
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
		File source = new File(PdfLogPath);
		File dest = new File(PdfLogViewPath);
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
	


//FOLLOWING METHODS ARE FOR UPDATING PDF OR LOG-----------------------------

	
	/**
	 * @deprecated
	 * <strong>THIS NEEDS TO BE UPDATED TO PULL INTO FROM DB AND PASTE IT TO PDF</strong>
	 * <br>Also, use a try/catch in method instead of throwing exceptions!!
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	public static void updatePDF() throws ClassNotFoundException, SQLException, DocumentException, FileNotFoundException { 
		
		
		Document document = new Document(PageSize.LETTER, 36, 36, 60, 36);

		//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(PdfLogPath));
		PdfWriter writerCopy =PdfWriter.getInstance(document, new FileOutputStream(PdfLogViewPath));
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        writerCopy.setPageEvent(event);
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
		
		writerCopy.close();
		writer.close();
	}
	/**
	 * Adds an Entry or Exit to the Logs Database
	 * @param StudentID Student's ID
	 * @param FirstName Student's First Name
	 * @param LastName Student's Last Name
	 * @param isSignedOut true = signing out, false = signing in
	 */
	public static void addEntryToLogDB(int StudentID, String FirstName, String LastName, boolean isSignedOut) {
		//Getting current sys time
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        
		//Adding to DB
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
			Statement s;
			s = conn.createStatement();
			
			ResultSet rs;
			
			if(isSignedOut) {
				String q = "INSERT INTO "+LogsTableName+" ([StudentID], [FirstName], [LastName], [TimeOut], [TimeIn]) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement st = conn.prepareStatement (q);
	
				st.setString(1, Integer.toString(StudentID));
				st.setString(2, FirstName);
				st.setString(3, LastName);
				st.setString(4, sdf.format(cal.getTime()));
				st.setString(5, "Still Signed Out");
				
				st.executeUpdate();
			}
			else {
				//psuedo
					/*
					 * find last entry
					 * update (NOT ADD NEW ROW) just 5th column
					 */
				String q = "UPDATE "+LogsTableName+" ([TimeIn]) VALUES (?) WHERE StudentID = " + StudentID;
				PreparedStatement st = conn.prepareStatement (q);
				
				st.setString(1, sdf.format(cal.getTime()));
				
				st.executeUpdate();
			}
		}
		catch(ClassNotFoundException e) {
			logs.updateLogsERROR("Can not find JDBC class");
			e.printStackTrace();
		}
		catch(SQLException e){
			logs.updateLogsERROR("Could not access Database");
			
		}
	}
	/**
	 * Adds a new student to the Student Database<BR>
	 * FIXME: NOT DONE
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	//FIXME: NOT DONE
	public static void addStudentToDB() throws ClassNotFoundException, SQLException {
		
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+StudentDBPath);
			
			Statement s;
			s = conn.createStatement();

			ResultSet rs;
			String q = "INSERT INTO "+DBTableName+" ([StudentID], [FirstName], [LastName]) VALUES (?, ?, ?)";
			PreparedStatement st = conn.prepareStatement (q);
			st.setString(1, "12345");
			st.setString(2, "Michael");
			st.setString(3, "Schwamborn");
			st.executeUpdate();
		}
	/**
	 * Clears the log Database<br>Caution, this removes all student entry and exits.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void clearLogsDB() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn=DriverManager.getConnection(
	        "jdbc:ucanaccess://"+LogsDBPath);
	Statement s;
		s = conn.createStatement();
	ResultSet rs;

	String q = "DELETE FROM "+LogsTableName;
	PreparedStatement st = conn.prepareStatement (q);
	st.executeUpdate();
	}
	/**
	 *  Adds the time a student comes back to Logs Database<br>
	 * @param studentID
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addTimeInToLogDB(String studentID) throws ClassNotFoundException, SQLException {
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+LogsDBPath);
		
		Statement s;
		s = conn.createStatement();

		ResultSet rs;
		String Timein = "10:55";
		String q = "UPDATE "+LogsTableName+" SET  [TimeIn]=? WHERE [StudentID]='"+studentID+"'";
		PreparedStatement st = conn.prepareStatement (q);
		st.setString(1, Timein);
		st.executeUpdate();
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
	 * adds a String to the Logs file as a Normal Message.<br>
	 * <strong>Format:</strong> yyyy/mm/dd HH:mm:ss  |    STRING_HERE<br>
	 * <strong> Format Example:</strong> 2017/11/23 16:55:32  |    Hello
	 * @param data The String that will be added to the Logs File
	 */
	public static void updateLogs(String data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = updateLogsPriority + " " + dtf.format(now) + "  |  " + data + "\n";
		writeToLogs(TimeAndData);
	}
	
	/**
	 * adds a String to the Logs file as a Start Up.<br>
	 * @param data The String that will be added to the Logs File
	 */
	public static void updateLogsStartUp(String StartUpData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		String TimeAndStartUpData = StartUpPriority + " " + dtf.format(now) + "  |  " + "Start Up" + "  |  " + StartUpData + "\n";
		writeToLogs(TimeAndStartUpData);
	}
	
	/**
	 * adds a String to the Logs file as an Error Message.<br>
	 * @param data The String that will be added to the Logs File
	 */
	public static void updateLogsERROR(String ERRORdata) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = ErrorPriority + " " + dtf.format(now) + "  |  ERROR  |  "+ ERRORdata + "\n";

		writeToLogs(TimeAndData);
	}
	
	/**
	 * adds a String to the Logs file as an Regular Message.<br>
	 * @param data The String that will be added to the Logs File
	 */
	public static void updateLogsSystem(String SystemData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String TimeAndData = ErrorPriority + " " + dtf.format(now) + "  |  System  |  "+ SystemData + "\n";

		writeToLogs(TimeAndData);
	}
	
}
