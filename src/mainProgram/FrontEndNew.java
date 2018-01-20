package mainProgram;

import javax.swing.*;
import java.awt.*;

/**
 * I am planning to completely rebuild front end by using
 * <ul>
 * <li>subclasses
 * <li>better naming
 * <li>redo wireframing
 * </ul>
 * @author Gary Tou
 *
 */
public class FrontEndNew {
	
	private static JFrame frame = new JFrame();
	
	public static void main(String[] args) {
		create();
	}
	
	public static void create() {
		frame();
		
		frame.setVisible(true);
		
	}
	
	public static void frame() {
		frame.setDefaultCloseOperation(arg0);
		
		
	}
	public class splashScreen {
		//TODO: something here
	}
	public class window {
		public void menuBar() {
			
		}
		public class file {
			public class exit {
				public void create() {
					
				}
			}
			
			public class preferences {
				public preferences() { //Constructor  for tabs and titles
					//constructor 
				}
				public void create() {
					
				}
				public void content(){
					
				}
			}
		}
		
	}
	
	public class content {
		public void pane() {
			
		}
		public void scan() {
			
		}
		public void messages() {
			
		}
		public void table() {
			
		}
		public void stats() {
			
		}
		
	}
	
}
