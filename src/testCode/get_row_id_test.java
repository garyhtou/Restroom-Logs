package testCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mainProgram.config;



public class get_row_id_test {
	
	public static void main(String[] args) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.LogsDBPath);
			Statement s;
			s = conn.createStatement();
			
			
			String q = "UPDATE ["+config.LogsDBTableName+"] ([TimeIn]) VALUES (?) WHERE SELECT MAX(12345) FROM ["+config.LogsDBTableName+"]  WHERE id = 4";
			String k = "UPDATE ["+config.LogsDBTableName+"] ([TimeIn]) VALUES (?) WHERE (SELECT id  FROM ["+config.LogsDBTableName+"])";
			PreparedStatement st = conn.prepareStatement(k);
			
			st.setString(1, "test");
			
			st.executeUpdate();
			
			

		} catch (SQLException | ClassNotFoundException e) {
			//logs.updateLogsERROR("Couldnt not access database at  " + config.StudentDBPath +".  Returning null");
			e.printStackTrace();
		}
	}
	
	

}

