package testCode;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.FontMetrics;

import javax.swing.*;

public class JLabelResize {
	public static void main(String[] args) {
		create();
		interaction();
	}
	
	static JFrame frame = new JFrame();
		static JSplitPane split = new JSplitPane();
			static JTextField field = new JTextField();
			static JPanel fontPane = new JPanel();
				static JButton small = new JButton();
				static JSlider slider = new JSlider();
				static JButton big = new JButton();
				
	static Font defaultFont = mainProgram.RL.scan;
	static Font currentFont = defaultFont;
	static float fontSize = 20f;
	
	
	public static void updateFontSize() {
		
	}
	
	
	
	
	
	
	
	
	//no need to touch these methods 
	
	public static void create() {
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		frame.add(split);
		
		split.setTopComponent(field);
		split.setBottomComponent(fontPane);
		
		fontPane.setLayout(new BorderLayout());
		fontPane.add(small, BorderLayout.LINE_START);
		fontPane.add(slider, BorderLayout.CENTER);
		fontPane.add(big, BorderLayout.LINE_END);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public static void interaction() {
		small.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSize--;
				updateFontSize();
			}
		});
		big.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fontSize++;
				updateFontSize();
			}
		});
		field.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
