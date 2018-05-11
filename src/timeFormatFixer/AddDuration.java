package timeFormatFixer;

import java.sql.*;
import java.util.*;

import mainProgram.BackEnd;
import mainProgram.config;

public class AddDuration {

		static String DBLocation;
		static final String durColName = "Duration";

		public static void main(String[] args) {
			//Set Databse Location
			DBLocation = "W:/git/Restroom-Logs/data/LogsDB.accdb";

			change();
			System.out.println("\n\nDONE!");
		}

		public static void change() {
			String[] tableNames = getAllTableName();
			
			for(String currentTable : tableNames) {
				addColumn(currentTable);
				
				ArrayList<String> durations = new ArrayList<String>();
				
				
				
				
			}
			
			
			
		}
		
		
		public static String[] getAllTableName() {
			ArrayList<String> list = new ArrayList<String>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			

			Connection conn=DriverManager.getConnection(
		        "jdbc:ucanaccess://"+DBLocation);
			Statement s;
			s = conn.createStatement();
			
			
			ResultSet rs;
			rs = conn.getMetaData().getTables(null, null, null, null);
			
			while(rs.next()) {
				list.add(rs.getString("TABLE_NAME"));
			}
			
			} catch (ClassNotFoundException e) {
				BackEnd.logs.update.ERROR("Can't find jdbc Driver");
				return null;
			} catch (SQLException e) {
				BackEnd.logs.update.ERROR("Error while getting all table names from Logs database");
				return null;
			}
			
			String[] arrList = list.toArray(new String[0]);
			return arrList;
		}
		/**
		 * adds a new column to the tableName for duration
		 * @param tableName
		 */
		public static void addColumn(String tableName) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+config.LogsDBPath);
				Statement s2 = conn.createStatement();
	
				String q = "ALTER TABLE [" + tableName + "] ADD \""+ durColName + "\" column-definition";
				PreparedStatement st = conn.prepareStatement (q);
				
				st.executeUpdate();
			} catch (SQLException | ClassNotFoundException e) {
				System.err.println("ERROR");
				e.printStackTrace();
			}
		}
		public static String calcDur(String tableName, int rowNum) {
			
			return "min:sec";
		}
	}


