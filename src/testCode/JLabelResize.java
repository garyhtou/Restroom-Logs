package testCode;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JLabelResize {
	public static void main(String[] args) {
		
	}
	public static class content {
		static JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				
		static JLabel label = new JLabel();
		
		static int fontSize = 20;
		
		
		public static void label() {
			
		}
		public static void settings() {
			JPanel buttonPane = new JPanel(new BorderLayout());
				JButton small = new JButton("<");
					small.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fontSize--;
							
						}
					});
				JButton big = new JButton(">");
					big.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fontSize++;
							
						}
					});
					
				buttonPane.add(small, BorderLayout.LINE_START);
				buttonPane.add(big, BorderLayout.LINE_END);
		}
	}
}
