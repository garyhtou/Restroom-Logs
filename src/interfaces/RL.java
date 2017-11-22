package interfaces;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
/**
 * Interface for Restroom Logs<br>
 * <br>
 * This interface includes:<br>
 * • color(String);<br>
 * • color(int);<br>
 * • colorHex(String);<br>
 * • colorHex(int);
 * • JSplitPaneDividerLocation(JFrame frame, JSplitPane splitPane, double percentage)<br>
 * 
 * @author Gary
 */


public interface RL {
	
	
	/**
	 * Easily get Color Object for color string name<br>
	 * Example of use: pane.setBackground(RL.color("ELECTRIC_BLUE"));
	 * @param color Enter a color. Example: "SMOKEY_BLACK", "DARK_GUNMETAL", "LAVENDER_GRAY", "ELECTRIC_BLUE", "KEPPEL"
	 * @return Color Object of color name entered
	 * @exception ColorException
	 */
	public static Color color(String color) { //PRAM COLOR NAME, RETURN COLOR
		switch(color){
		case "SMOKEY_BLACK": 
			Color SMOKEY_BLACK = new Color(0x0B0C10);
			return SMOKEY_BLACK;
		case "DARK_GUNMETAL": 
			Color DARK_GUNMETAL = new Color(0x1F2833);
			return DARK_GUNMETAL;
		case "LAVENDER_GRAY": 
			Color LAVENDER_GRAY = new Color(0xC5C6C7);
			return LAVENDER_GRAY;
		case "ELECTRIC_BLUE": 
			Color ELECTRIC_BLUE = new Color(0x66FCF1);
			return ELECTRIC_BLUE;
		case "KEPPEL": 
			Color KEPPEL = new Color(0x45A29E);
			return KEPPEL;
		default:
			ColorException.StringError(color);
			return null;
		}
	}
	public static String colorHex(String color) { //PRAM COLOR NAME, RETURN COLOR HEX #
		switch(color){
		case "SMOKEY_BLACK": 
			String SMOKEY_BLACK = new String("0B0C10");
			return SMOKEY_BLACK;
		case "DARK_GUNMETAL": 
			String DARK_GUNMETAL = new String("1F2833");
			return DARK_GUNMETAL;
		case "LAVENDER_GRAY": 
			String LAVENDER_GRAY = new String("C5C6C7");
			return LAVENDER_GRAY;
		case "ELECTRIC_BLUE": 
			String ELECTRIC_BLUE = new String("66FCF1");
			return ELECTRIC_BLUE;
		case "KEPPEL": 
			String KEPPEL = new String("45A29E");
			return KEPPEL;
		default:
			ColorException.StringError(color);
			return null;
		}
	}
	public static Color color(int color) { //PRAM INT NAME, RETURN COLOR
		switch(color){
		case 1: 
			Color SMOKEY_BLACK = new Color(0x0B0C10);
			return SMOKEY_BLACK;
		case 2: 
			Color DARK_GUNMETAL = new Color(0x1F2833);
			return DARK_GUNMETAL;
		case 3: 
			Color LAVENDER_GRAY = new Color(0xC5C6C7);
			return LAVENDER_GRAY;
		case 4: 
			Color ELECTRIC_BLUE = new Color(0x66FCF1);
			return ELECTRIC_BLUE;
		case 5: 
			Color KEPPEL = new Color(0x45A29E);
			return KEPPEL;
		default:
			ColorException.intError(color);
			return null;
		}
	}
	public static String colorHex(int color) { //PRAM INT NAME, RETURN COLOR HEX #
		switch(color){
		case 1: 
			String SMOKEY_BLACK = new String("0B0C10");
			return SMOKEY_BLACK;
		case 2: 
			String DARK_GUNMETAL = new String("1F2833");
			return DARK_GUNMETAL;
		case 3: 
			String LAVENDER_GRAY = new String("C5C6C7");
			return LAVENDER_GRAY;
		case 4: 
			String ELECTRIC_BLUE = new String("66FCF1");
			return ELECTRIC_BLUE;
		case 5: 
			String KEPPEL = new String("45A29E");
			return KEPPEL;
		default:
			ColorException.intError(color);
			return null;
		}
	}
	
	/**
	 * An easy way to resize a JSplitPane by percentage
	 * @param frame THe JFrame (the JFrame must be realized. In other words, visible)
	 * @param splitPane The JSplitPane that you would like to resize
	 * @param percentage percentage location of divider. Must be a double between 0 and 1
	 * 
	 * @see .setDividerLocation(double);
	 * 
	 * @author Gary
	 */
	public static void JSplitPaneDividerLocation(JFrame frame, JSplitPane splitPane, double percentage) {
		frame.setVisible(true); //must be a "realized" frame (frame must be visible and packed)
		splitPane.setDividerLocation(percentage);
	}

}//RL INTERFACE ENDING BRACE

/**
 * Color Exceptions for when an invalid color name or int is entered
 * 
 * @author Gary
 *
 */
class ColorException extends Exception{
	ColorException() {}
	ColorException(String str)
	{
	   super(str);
	}
	static void StringError(String color) {
		try {
	       	throw new ColorException("\"" + color + "\" " +  "does not exist in the RL.java interface");
			}
		catch (ColorException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	static void intError(int color) {
		try {
	       	throw new ColorException("\"" + color + "\" " +  "does not exist in the RL.java interface. Try numbers 1-5");
			}
		catch (ColorException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
