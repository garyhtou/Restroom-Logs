package testCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mainProgram.BackEnd;
import mainProgram.config;
import mainProgram.BackEnd.logs;

public class checkIfInOrOut {
	public static void main(String[] args) {
		//signIn(12345);
		if(checkIfOut(33333))
			System.out.print("The ID is in the db and they are signed out.");
		else
			System.out.print("The ID is not in the db and they are not signed out.");
	}
	public static void signIn(int studentID) {
		int row = getRow(studentID);
		if(row == -1) {
			System.err.print(studentID + " not found in Databse at " + config.LogsDBPath);
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
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:ucanaccess://"+config.LogsDBPath);
			} catch (SQLException e) {
				BackEnd.logs.update.ERROR("Can not access Logs Database at " + config.LogsDBPath);
				System.err.println("Can not access Logs Database at " + config.LogsDBPath);
				e.printStackTrace();
			}
			
			String updateInTime = "UPDATE " + config.LogsDBTableName + " SET TimeIn = ? WHERE " + "ID = " + row;
			/*String q2 = "UPDATE "+config.LogsDBTableName+" ([TimeIn]) VALUES (?) WHERE ID = " + entryRowNum;
			
			String ex = "UPDATE ["+config.LogsDBTableName+"] SET ([TimeIn]) = "+"'test'" +"  WHERE id = "+entryRowNum; // The correct way to format an UPDATE query; the 'test' field is what you want to put into the column, so in our case the In Time. - Michael
			*/
			//creating statement
			PreparedStatement st = null;
			try {
				st = conn.prepareStatement (updateInTime);
			} catch (SQLException e) {
				BackEnd.logs.update.ERROR("Unable execute query, \"" + updateInTime + "\"");
				System.err.println("Unable execute query, \"" + updateInTime + "\"");
				e.printStackTrace();
			}
			
			String currentTime = (new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()));
			
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
	private static int getRow(int studentID) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.LogsDBPath);
			Statement s;
			s = conn.createStatement();
			
			
			ResultSet rs;
			rs = s.executeQuery("SELECT [ID] FROM " + config.LogsDBTableName + " WHERE " + "StudentID=" + studentID + " ORDER BY ID DESC");
			
			rs.next(); //move into table
			
			int row = rs.getInt(1);
			System.out.println(row);
			return row;

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("ERROR");
			e.printStackTrace();
			return -1;
		}
	}

	
	public static boolean checkIfOut(int studentID){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.LogsDBPath);
			Statement s;
			s = conn.createStatement();
			
			
			ResultSet rs;
			rs = s.executeQuery("SELECT [StudentID] FROM " + config.LogsDBTableName + " WHERE " + "StudentID=" + studentID + " AND 	TimeIn='Still Signed Out'");
			
			rs.next(); //move into table
			
			rs.getString(1);
			return true;
			

		} catch (SQLException | ClassNotFoundException e) {
			return false;
			
		}
	}
}