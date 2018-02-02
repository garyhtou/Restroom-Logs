package interfaces;

import java.awt.Dimension;
import java.awt.Font.*;
import java.awt.*;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

import mainProgram.BackEnd;
import mainProgram.config;
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
	
	//Load Fonts
	/**
	 * for internal use only
	 * @param path
	 * @return 
	 * @return
	 */
	public static Font loadFont(String path) {
		Font ttfBase = null;
  		Font ttfReal = null;
		try {
			InputStream myStream = new BufferedInputStream(new FileInputStream(path));
			ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
			ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);
			return ttfReal;
		} catch (Exception ex) {
	        ex.printStackTrace();
	        BackEnd.logs.update.ERROR("Font could not be loaded from  " + path);
	        System.err.println("Font could not be loaded from  " + path);
	        return null;
	    }
    }
 
	
	//SET FONT SIZE
	public static Font preferencesTitle = new Font(Font.DIALOG, Font.BOLD, 30);
	public static Font statsTeacherNameFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
	public static Font AnticSlab = RL_Fonts.loadFont(config.AnticSlabFilePath);
	public static Font Kollektif = RL_Fonts.loadFont(config.KollektifFilePath);
	
	
	//DYNAMIC FONT SIZE
	public static Font statsTeacherNameDynamic(JComponent comp) {
		double compHeight = comp.getHeight();
		System.out.println("Font size: " + (compHeight));
		return new Font(Font.DIALOG, Font.BOLD, (int) (compHeight));
	}
}