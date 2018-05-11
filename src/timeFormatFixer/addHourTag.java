package timeFormatFixer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * The Back End of the Restroom Logs Program
 * <div></div>
 *  Copyright (C) 2018  Gary Tou and Michael Schwamborn
 *  <div></div>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * <div></div>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * <div></div>
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *
 */
public class addHourTag{
	static String DBLoc;
	final static String AM = "AM";
	final static String PM = "PM";

	public static void main(String[] args) {
		//Set Databse Location
		DBLoc = "W:/git/Restroom-Logs/data/LogsDB.accdb";

		change();
		System.out.println("\n\nDONE!");
	}

	public static void change() {
		String[] tableNames = getAllTableNames();

		//goes through all tables
		for(String table : tableNames){
			//
			ArrayList<Integer> am = new ArrayList<Integer>();
			ArrayList<String> amText = new ArrayList<String>();
			ArrayList<Integer> pm = new ArrayList<Integer>();
			ArrayList<String> pmText = new ArrayList<String>();

			String[] timeInEntries = getAllEntries(table);

			for(int i = 1; i < timeInEntries.length; i++){
				String firstChar = timeInEntries[i].charAt(0) + timeInEntries[i].charAt(1) + "";
				System.out.println(timeInEntries[i]);
				System.out.println(firstChar); 
				if(!firstChar.equals("Ma")&&!firstChar.equals("St")){
				int firstCharInt = Integer.parseInt(firstChar);
				if(((firstCharInt >= 1) && (firstCharInt <= 5)) || (firstCharInt == 12)){
					pm.add(i);
					pmText.add(timeInEntries[i]);
				} else{
					am.add(i);
					amText.add(timeInEntries[i]);
				}
     }
			}


			int[] amArr = new int[am.size()];
			for(int i = 0; i < am.size(); i++){
				amArr[i] = am.get(i);
			}

			String[] amTextArr = new String[amText.size()];
			for(int i = 0; i < amText.size(); i++){
				amTextArr[i] = amText.get(i);
			}

			int[] pmArr = new int[pm.size()];
			for(int i = 0; i < pm.size(); i++){
				pmArr[i] = pm.get(i);
			}

			String[] pmTextArr = new String[pmText.size()];
			for(int i = 0; i < pmText.size(); i++){
				pmTextArr[i] = pmText.get(i);
			}



			changeTimeIn(AM, amArr, amTextArr, table);
			changeTimeIn(PM, pmArr, pmTextArr, table);
		}


	}

	public static void changeTimeIn(String AMorPM, int[] indexes, String[] values, String tableName){
		String tag;
		if(AMorPM.equals(AM)){
			tag = AM;
		} else if(AMorPM.equals(PM)){
			tag = PM;
		} else{
			tag = "YOU MESSED UP";
			System.err.println(tag);
		}

		for(int i = 0; i < indexes.length; i++){
			int index = indexes[i];
			String value = values[i];
			String newVal = value + " " + tag;

			String updateInTime = "UPDATE [" + tableName + "] SET [TimeIn] = \"" + newVal + "\" WHERE [ID] = \"" + index + "\"";
			//creating statement
			PreparedStatement st = null;

			try {
				Connection conn3=DriverManager.getConnection(
				"jdbc:ucanaccess://"+DBLoc);
				st = conn3.prepareStatement (updateInTime);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}


	public static String[] getAllEntries(String tableName){
		ArrayList<String> timeIn = new ArrayList<String>();
		timeIn.add("FILLER");


		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

		Connection conn=DriverManager.getConnection(
			"jdbc:ucanaccess://"+DBLoc);
			Statement s2;
			s2 = conn.createStatement();
			ResultSet rs;
			rs = s2.executeQuery("SELECT [TimeIn] FROM [" + tableName + "]");

		while(rs.next()) {
			timeIn.add(rs.getString(1));
		}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


		String[] returnList = new String[timeIn.size()];
		for(int i = 0; i < timeIn.size(); i++){
			returnList[i] = timeIn.get(i);
		}
		return returnList;
	}


	public static String[] getAllTableNames(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");


		Connection conn=DriverManager.getConnection(
			"jdbc:ucanaccess://"+DBLoc);
		Statement s;
		s = conn.createStatement();


		ResultSet rs;
		rs = conn.getMetaData().getTables(null, null, null, null);

		while(rs.next()) {
			list.add(rs.getString("TABLE_NAME"));
		}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		String[] arrList = list.toArray(new String[0]);
		return arrList;
	}
}
