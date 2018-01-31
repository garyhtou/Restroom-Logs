package oldStuff;


import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
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
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;



public class PdfDataManager extends PdfPageEventHelper {
	
//GET DATA BASE INFO *NOT IN USE*
	public static String getDBData(int column, boolean repeat) throws SQLException, ClassNotFoundException {
		String data = "";
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://data/TestDB.accdb");

		Statement s;
		 

			s = conn.createStatement();

		ResultSet rs;

			rs = s.executeQuery("SELECT StudentId, FirstName, LastName FROM [Test]");
			rs.next();
			if(!repeat &&column>1) {
				
				 data = rs.getString(column).substring(5,rs.getString(column).indexOf("/")-1);
				
			}
			if(column==1)
				data = rs.getString(column);
			
		if(repeat) {
			while (rs.next()) {
			    data=rs.getString(1);
			    System.out.println(rs.getString(2));
			    System.out.println(rs.getString(3));
			    
	
			}
		}
		return data;
	}
	
	
//MAIN METHOD
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
		//only call a method here for testing, making to remove it after testing
		logs.updatePDF();
		//getDBData();
		//createLogs();
		//createPDF();
		//addHeader();
		//updateLogs("hi");
		
		
	}
}