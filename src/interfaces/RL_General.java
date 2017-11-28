package interfaces;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * 
 * @author gary
 *
 */
public interface RL_General {
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
	
	
	
	
	
	
	
}
