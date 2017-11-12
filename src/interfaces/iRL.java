package interfaces;

/**
 * This interface is for our Restroom Log Project
 * @author Gary
 *
 */

public class iRL /*implements RL*/{

	public static String checkTime(String firstName, String lastName, int time, String action /*action = in or out*/) {
		/**
		 * Check the time entered to make sure it's vaild and create a string that will be entered into the .txt Log
		 * @param firstName First name of student
		 * @param lastName Last name of student
		 * @param time The time the student scanned their card.  Valid time Ex. 1020 = 10:10;  2359 = 23:59 
		 * @param action Sign "in" or "out"
		 * @return A String that contains the first and last name with time, date and
		 */
		String data = "";
		int timeCheck = time;
		for(int i = 1; i <= 3; i++) {
			timeCheck/=10;
		}
		String timeFormated = "";
		
		if(action.equals("in")) {
			data = firstName + " " + lastName + " || " + action + " | " + timeFormated;
		}
		return data;
		
	}
	
	
}
