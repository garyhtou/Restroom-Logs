package oldStuff;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import interfaces.RL_Fonts;

public class newPreferencesTab extends JComponent{
	/**
	 * Creates a new Tab in Preferences
	 * @param tabbedPane JTabbedPane used for holding these tabs
	 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
	 * @param icon Icon next to tab name
	 * @param toolTip tool tip for user's info
	 */
	public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab, Icon icon, String toolTip) {
		//TITLE
		JTextArea textArea = new JTextArea();
		textArea.setFont(RL_Fonts.preferencesTitle);
		textArea.setOpaque(true);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		textArea.setText(nameOfTab);
		this.add(textArea);
		
		tabbedPane.addTab(nameOfTab, icon, this, toolTip);
	}
	
	/**
	 * Creates a new Tab in Preferences<br>
	 * Tooltip is set to none by default
	 * @param tabbedPane JTabbedPane used for holding these tabs
	 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
	 * @param icon Icon next to tab name
	 */
	public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab, Icon icon) {
		this(tabbedPane, nameOfTab, icon, "");
	}

	/**
	 * Creates a new Tab in Preferences<br>
	 * Tooltip and icon is set to none by default
	 * @param tabbedPane JTabbedPane used for holding these tabs
	 * @param nameOfTab Name of this tab (This will show in the tab title and tab name
	 */
	public newPreferencesTab(JTabbedPane tabbedPane, String nameOfTab) {
		this(tabbedPane, nameOfTab, new ImageIcon("assets/images/noIcon.png"), "");
	}
}
