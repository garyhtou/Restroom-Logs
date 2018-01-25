package testCode;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import mainProgram.BackEnd;

public class test {

	public static void main(String[] args) {
		//BackEnd.email.PDF.CreateBlankPDF();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		int displayWidth = (int) (screenWidth/1.3);
		int displayHeight = (int) (screenHeight/1.3);
    	
		JTabbedPane tabbedPane = new JTabbedPane();
		//JOptionPane.showMessageDialog(null, tabbedPane, "Initial Setup", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showOptionDialog(null, "This is a test of the inital setup" , "Initial Setup", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null );
		

	}

}
