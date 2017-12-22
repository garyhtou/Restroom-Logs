package mainProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class database {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static String pull(String column, int studentID){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.StudentDBPath);
			Statement s;
			s = conn.createStatement();
			
			
			ResultSet rs;
			rs = s.executeQuery("SELECT " + column + " FROM ["+config.StudentDBPath+"]");
			
			//TODO: find if contains studentID and get row
			int row = 5; //change to row of student ID
			rs.next();
			//move to selected row
			for(int i = 0; i < row; i++) {
				rs.next();
			}
			
			return rs.getString(1);

		} catch (SQLException | ClassNotFoundException e) {
			logs.updateLogsERROR("Couldnt not access database at  " + config.StudentDBPath +".  Returning null");
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList pullStudentName(int studentID){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			//accessing driver
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			//establishing connection to DB
			Connection conn = DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.StudentDBPath);
			Statement s;
			s = conn.createStatement();
			
			//creating statement
			ResultSet rs;
			rs = s.executeQuery("SELECT FirstName, LastName FROM "+config.StudentDBTableName+"  WHERE StudentID = " + studentID);
			
			rs.next();
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			
			return list;

		} catch (SQLException | ClassNotFoundException e) {
			logs.updateLogsERROR("Couldnt not access database at  " + config.StudentDBPath +".  Returning null");
			System.err.println("Couldnt not access database at  " + config.StudentDBPath +".  Returning null");
			e.printStackTrace();
			return null;
		}
	}
}
