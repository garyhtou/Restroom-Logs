package mainProgram;

import java.util.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;

public class Preferences {

	public static void main(String[] args) {
		preferences();
	}
	public static void preferences() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		ImageIcon icon1 = new ImageIcon("images/middle.gif");
		ImageIcon icon2 = new ImageIcon("images/middle.gif");
		ImageIcon icon3 = new ImageIcon("images/middle.gif");
		ImageIcon icon4 = new ImageIcon("images/middle.gif");
		
		

		JComponent panel1 = new JPanel();
		tabbedPane.addTab("Tab 1", icon1, panel1, "Does nothing"); //table title, tab icon, tab content, tab ToolTipText
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = new JPanel();
		tabbedPane.addTab("Tab 2", icon2, panel2, "Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = new JPanel();
		tabbedPane.addTab("Tab 3", icon3, panel3, "Still does nothing");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Tab 4", icon4, panel4, "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

	}
	
	

}
