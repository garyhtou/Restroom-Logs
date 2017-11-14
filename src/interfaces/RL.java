package interfaces;

public interface RL {

	public static String checkTime(String firstName, String lastName, int time, String action /*action = in or out*/) {
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
