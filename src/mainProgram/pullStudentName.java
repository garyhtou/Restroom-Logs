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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public pullStudentName(int studentID) throws SQLException, ClassNotFoundException{
		
		ArrayList<String> list = new ArrayList<String>();
		
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

		Connection conn=DriverManager.getConnection(
	        "jdbc:ucanaccess://"+config.StudentDBPath);
		Statement s;
		s = conn.createStatement();
		
		
		ResultSet rs;
		rs = s.executeQuery("SELECT [StudentID], [FirstName], [LastName] FROM ["+config.DBTableName+"]  WHERE [StudentID]='"+ studentID+"'");
		
		rs.next();
		rs.getString(1);
		FirstName = rs.getString(2);
		LastName = rs.getString(3);
	}
	/**
	 * 
	 * @return FirstName as a String
	 */
	public static String getFirstName() {
		//System.out.println(FirstName);
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
	public static String getBothNames() {
		String BothNames = FirstName + " " + LastName;
		return BothNames;
	}

	
	
	public static boolean containsOnlyNumbers(String str) {
	    for (int i = 0; i < str.length(); i++) {
	      if (!Character.isDigit(str.charAt(i)))
	        return false;
	    }
	    return true;
	}
}
