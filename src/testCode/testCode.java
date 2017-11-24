package testCode;

//IMPORTS
//Back End
import java.util.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;
//Front End
import com.itextpdf.text.*;
import com.teamdev.jxdocument.*;
import com.teamdev.jxdocument.SinglePagePresentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/*
-= PURPOSE OF THIS FILE =-
	This file is used for testing random code
 */

public class testCode {
	public static void main(String[] args) {
		rendering_pdf();
	}
	
	/*
	FIXME: LICENSING!!!!
	12:10:35 INFO: OS name: Windows 10
	12:10:35 INFO: JRE version: 1.8 64-bit
	12:10:35 INFO: Retina Display: false
	Exception in thread "main" java.lang.ExceptionInInitializerError
	at testCode.testCode.rendering_pdf(testCode.java:36)
	at testCode.testCode.main(testCode.java:28)
	Caused by: java.lang.RuntimeException: JxDocument license check failed: No valid license found. 
	 */
	public static void rendering_pdf() {
		JFrame frame = new JFrame();
		
		
		File pdf = new File("data/ViewLogsPDF.pdf");
		com.teamdev.jxdocument.Document document = new com.teamdev.jxdocument.Document(pdf);
		SinglePagePresentation presentation= new SinglePagePresentation(document);
		presentation.displayPage(0);
		JComponent renderedViewPDF = presentation.getComponent();
		
		
		frame.add(renderedViewPDF);
		frame.pack();
		frame.setVisible(true);
	}
}
