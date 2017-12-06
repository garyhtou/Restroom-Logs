package mainProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			rs = s.executeQuery("SELECT FirstName, LastName, TimeOut, TimeIn FROM ["+config.StudentDBPath+"]");
			
			//TODO: find if contains studentID and get row
			int row = 5; //change to row of student ID
			rs.next();
			//move to selected row
			for(int i = 0; i < row; i++) {
				rs.next();
			}
			
			String FirstName = rs.getString(1);
			String LastName = rs.getString(2);
			String TimeOut = rs.getString(3);
			String TimeIn = rs.getString(4);
			
			
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
