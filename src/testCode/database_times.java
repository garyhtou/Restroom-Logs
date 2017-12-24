package testCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import mainProgram.config;
import mainProgram.database;
import mainProgram.logs;

public class database_times {
	public static void main(String[] args) {
		entry(11111);
	}
	
	public static void entry(int studentID) {
		String firstName = (String) database.pullStudentName(studentID).get(0);
        String lastName = (String) database.pullStudentName(studentID).get(1);
        
        //Getting current sys time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        
		//accessing DB
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			logs.updateLogsERROR("Can not access UcanaccessDriver");
			System.err.println("Can not access UcanaccessDriver");
			e.printStackTrace();
		}
		
		
		//establishing conneciton to DB
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:ucanaccess://"+config.LogsDBPath);
		} catch (SQLException e) {
			logs.updateLogsERROR("Can not access Logs Database at " + config.LogsDBPath);
			System.err.println("Can not access Logs Database at " + config.LogsDBPath);
			e.printStackTrace();
		}
		
		//creating statement
		Statement s = null;
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			logs.updateLogsERROR("Can not create Ucanaccess SQL Statement");
			System.err.println("Can not create Ucanaccess SQL Statement");
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		
		

		
		boolean signingOut = false; //Check if in our out
			int entryRowNum = 0;
			String timeOut = "";
	        //creating query/statement
	        String q = "SELECT ID, TimeIn FROM " + config.LogsDBTableName + " WHERE StudentID = " + studentID + " ORDER BY ID DESC";
			try {
				rs = s.executeQuery(q);
			} catch (SQLException e) {
				logs.updateLogsERROR("Unable execute query, \"" + q + "\"");
				System.err.println("Unable execute query, \"" + q + "\"");
				e.printStackTrace();
			}
			
			//geting info
			try {
				rs.next();
				entryRowNum = rs.getInt(1);
				timeOut = rs.getString(2);
			} catch (SQLException e) {
				logs.updateLogsERROR("Unable to get entryRowNum");
				System.err.println("Unable to get entryRowNum");
				e.printStackTrace();
			}
			
			//changing boolean
			//FIXME: timeOut is ALWAYS NULL 
			if(timeOut == null) { //incorrect way to check if it's null, giving an error -- I think i fixed it (Michael)
				signingOut = false;
			}
			else if(timeOut.isEmpty()) {
				logs.updateLogsERROR("Was unable to access TimeOut value in DB, assuming that Studenting is signing out");
				System.err.println("Was unable to access TimeOut value in DB, assuming that Studenting is signing out");
			}
			else {
				signingOut = true;
			}
		
		
		
		if(signingOut) { //CURRENTLY SIGNING OUT
	        
	        String q2 = "INSERT INTO "+config.LogsDBTableName+" ([StudentID], [FirstName], [LastName], [TimeOut], [TimeIn]) VALUES (?, ?, ?, ?, ?)";
			
	        //setting statement
	        PreparedStatement st = null;
			try {
				st = conn.prepareStatement (q2);
			} catch (SQLException e) {
				logs.updateLogsERROR("Can not create Ucanaccess SQL Statment with \"" + q2 + "\". Statment may be invalid");
				System.err.println("Can not create Ucanaccess SQL Statment with \"" + q2 + "\". Statment may be invalid");
				e.printStackTrace();
			}
			
			//setting values in statement
			try {
				st.setInt(1, studentID); //Student ID
				st.setString(2, firstName); //Student's First Name
				st.setString(3, lastName); //Student's Last Name
				st.setString(4, sdf.format(cal.getTime())); //Current time, for Time Out
				st.setString(5, null); //null, for Time In. This will remain null until signed out by student, manually signed out by teacher, or signed out by system
			} catch (SQLException e) {
				logs.updateLogsERROR("unable to set values in Ucanaccess SQL Statment");
				System.err.println("unable to set values in Ucanaccess SQL Statment");;
				e.printStackTrace();
			}
			
			//Committing changes to DB
			try {
				st.executeUpdate();
				logs.updateLogs(firstName + " " + lastName + " has Signed Out");
			} catch (SQLException e) {
				logs.updateLogsERROR("Unable to executeUpdate Values in Ucanaccess SQL Statment");
				System.err.println("Unable to executeUpdate Values in Ucanaccess SQL Statment");
				e.printStackTrace();
			}
		}
		else { //CURRENTLY SIGNING IN
			String q2 = "UPDATE "+config.LogsDBTableName+" ([TimeIn]) VALUES (?) WHERE ID = " + entryRowNum;
			
			String ex = "UPDATE ["+config.LogsDBTableName+"] SET ([TimeIn]) = "+"'test'" +"  WHERE id = "+entryRowNum; // The correct way to format an UPDATE query; the 'test' field is what you want to put into the column, so in our case the In Time. - Michael
			
			//creating statement
			PreparedStatement st = null;
			try {
				st = conn.prepareStatement (q2);
			} catch (SQLException e) {
				logs.updateLogsERROR("Unable execute query, \"" + q2 + "\"");
				System.err.println("Unable execute query, \"" + q2 + "\"");
				e.printStackTrace();
			}
			
			try {
				st.setString(1, sdf.format(cal.getTime()));
				st.executeUpdate();
				logs.updateLogs(firstName + " " + lastName + " has Signed in");
			} catch (SQLException e) {
				logs.updateLogsERROR("Unable to Update In Time");
				System.err.println("Unable to Update In Time");
				e.printStackTrace();
			}
		}
	}
}
