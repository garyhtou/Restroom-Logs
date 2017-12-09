package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
/**
 * An interface for Fonts that are used in this program
 * @author gary
 *
 */
public interface RL_Fonts {
	//Screen Dimensions for dynamic font size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	public static Font preferencesTitle = new Font(Font.DIALOG, Font.BOLD, 30);
	public static Font statsTeacherNameFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
	public static Font statsTeacherNameDynamic(JComponent comp) {
		double compHeight = comp.getHeight();
		System.out.println("Font size: " + (compHeight));
		return new Font(Font.DIALOG, Font.BOLD, (int) (compHeight));
	}
}