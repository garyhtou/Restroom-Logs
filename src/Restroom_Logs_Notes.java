
/*
INFOMATION:

This is a project by Gary and Michael.
STATUS: Barely Started
//test Front-end
 */



public class Restroom_Logs_Notes {
	/*
	--== IDEA ==--
			Use a Raspberry Pi with Raspbian to program a Bathroom log using barcodes.
			
			Language: Java
				We must figure out how to run java in Raspbian.
				
			Sign in/out method:
				Use barcode or allow students to type name?
					Find barcode scanner
					Which barcode should we use? I THINK WE SHOULD USE CODE128
					Code128 uses ASCII up to 128 Chars. i think, please double check
						https://www.scandit.com/types-barcodes-choosing-right-barcode/
						Make sure our barcode scanner can read that type of barcode
	
			Features:
				Reset sign out list at the end of every period.
				If time exceeds 5 mins, turn on a led and print name and current time since sign out on screen. 
				
			
	--== HOW ==--
			use java swing for graphics
				java swing will also allow us to prevent the program from closing.
					use DO_NOTHING_ON_CLOSE this way the program can't be closed when you hit the closed button.
						Maybe type in a special barcode to close program??
			use multiple dimension arrays to keep track of Name, In, Out
				
				
	--== SUPPLIES NEEDED ==--
			Barcode scanner
			Raspberry Pi
				Micro SD
			Monitor
			Keyboard
			Mice
			
			One printed barcode per student
				Need to write a program to create this doc.
				
				
				
	--== CHANGE LOG ==--
			11/2:
				Started planning how barcode scanning should work.
				
				Found option for making multiple barcodes: https://www.tec-it.com/en/software/barcode-software/office/excel/Default.aspx 
				Con - need to type in every single name for each student and add-in requires payment for full licence but does have free trial option 
				 or this webpage: https://www.ruggedtabletpc.com/barcode-generator --Michael
		
		
		
		
	--== TODO ==--
			11/2:
				Barcode (using code128)
					Get a list of names, somehow format the names (one name per cell in A column).
					PRINT!
	
			11/2:
				NVM, TOO HARD: Find a way to change name and icon of java process (What shows up in Task Manager), default process name is the name of the JVM.  Might have to change the name of the JVM or use a Java executable wrapper.
	
	
	
	
	
	
	
	
	
	
	
	 */
	
	//test
	
}
