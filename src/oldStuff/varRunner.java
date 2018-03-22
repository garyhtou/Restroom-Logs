package oldStuff;

import java.util.Scanner;

import mainProgram.var;

public class varRunner {

	public static void main(String[] args) {
		normalExample();
		//updateExample();
		//updateAllExample();
		//setValueExample();
	}
	public static void normalExample() {
		var teacherName = new var("config/DoNotTouch.txt", "teacherName");
		System.out.println(teacherName);
	}
	public static void updateExample() {
		var teacherName = new var("config/DoNotTouch.txt", "teacherName");
		System.out.println(teacherName);
		
		//Manually change the file
		
		hitEnterToContinue();
		
		teacherName.update();
		System.out.println(teacherName);
	}
	public static void updateAllExample() {
		//from DoNotTouch.txt
		var teacherName = new var("config/DoNotTouch.txt", "teacherName");
		System.out.println(teacherName);
		var dailyEmails = new var("config/DoNotTouch.txt", "dailyEmails");
		System.out.println(dailyEmails);
		var studentDBPath = new var("config/DoNotTouch.txt", "studentDBPath");
		System.out.println(studentDBPath);
		var studentDBTableName = new var("config/DoNotTouch.txt", "studentDBTableName");
		System.out.println(studentDBTableName);
		
		//from config.txt
		var wifi = new var("config/config.txt", "wifi");
		System.out.println(wifi);
		
		
		//Manually change the file!!!!!!!!!!!!!!
		
		hitEnterToContinue();
		
		teacherName.updateAll(); //the update all method can be called by any "var". they all do the same thing.
		
		System.out.println(teacherName);
		System.out.println(dailyEmails);
		System.out.println(studentDBPath);
		System.out.println(studentDBTableName);
		System.out.println(wifi);
		
	}
	public static void setValueExample() {
		var teacherName = new var("config/DoNotTouch.txt", "teacherName");
		
		System.out.println("Before: " + teacherName);
		
		teacherName.setValue("NEW TEACHER");
		
		System.out.println("After: " + teacherName);
		
		//teacherName.setValue("Sabo"); //sets it back to normal
	}
	
	
//helper methods for this runner
	private static void hitEnterToContinue() {
		System.out.print("---Hit ENTER after changing values in file. (MAKE SURE TO SAVE :)");
		new Scanner(System.in).nextLine(); //hit enter
	}
}
