package testCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainProgram.config;
import mainProgram.logs;

public class test_findRow {
	public static void main(String[] args) {
		//establishing conneciton to DB
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:ucanaccess://"+config.LogsDBPath);
		} catch (SQLException e) {
			logs.updateLogsERROR("Can not access Logs Database at " + config.LogsDBPath);
			System.err.println("Can not access Logs Database at " + config.LogsDBPath);
			e.printStackTrace();
		}
		int studentID = 12345;
		String findRow = "SELECT [ID] FROM" + config.LogsDBTableName + " WHERE " + "StudentID=" + studentID + " ORDER BY ID DESC";
		/*String q2 = "UPDATE "+config.LogsDBTableName+" ([TimeIn]) VALUES (?) WHERE ID = " + entryRowNum;
		
		String ex = "UPDATE ["+config.LogsDBTableName+"] SET ([TimeIn]) = "+"'test'" +"  WHERE id = "+entryRowNum; // The correct way to format an UPDATE query; the 'test' field is what you want to put into the column, so in our case the In Time. - Michael
		*/
		//creating statement
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement (findRow);
		} catch (SQLException e) {
			logs.updateLogsERROR("Unable execute query, \"" + findRow + "\"");
			System.err.println("Unable execute query, \"" + findRow + "\"");
			e.printStackTrace();
		}
		
		try {
			
			st.setString(1, "hi");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
