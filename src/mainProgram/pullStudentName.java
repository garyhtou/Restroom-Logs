package mainProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class pullStudentName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private static String FirstName = null;
	private static String LastName = null;
	
	/**
	 * Constructor for getting Student Names from ID
	 * @param studentID input scanned Student ID
	 */
	public pullStudentName(int studentID){
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+config.StudentDBPath);
			Statement s;
			s = conn.createStatement();
			
			
			ResultSet rs;
			rs = s.executeQuery("SELECT StudentID FirstName LastName FROM ["+config.StudentDBPath+"]");
			
			rs.next();
			while(!rs.getString(1).equals(studentID)){
				rs.next();
			}

			FirstName = rs.getString(2);
			LastName = rs.getString(3);

		} catch (SQLException | ClassNotFoundException e) {
			logs.updateLogsERROR("Couldnt not access database at  " + config.StudentDBPath +".  Returning null");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return FirstName as a String
	 */
	public static String getFirstName() {
		System.out.println(FirstName);
		return FirstName;
	}
	/**
	 * @return LastName as a String
	 */
	public static String getLastName() {
		return LastName;
	}
	/**
	 * @return First name and last name in a String Array.<br>{FirstName, LastName}
	 */
	public static String[] getBothNames() {
		return new String[] {FirstName, LastName};
	}

	
	
	public static boolean containsOnlyNumbers(String str) {
	    for (int i = 0; i < str.length(); i++) {
	      if (!Character.isDigit(str.charAt(i)))
	        return false;
	    }
	    return true;
	}
}
