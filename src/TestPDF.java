
import com.itextpdf.text.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//http://www.java2s.com/Code/Jar/i/Downloaditext502jar.htm
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPDF {

	public static void main(String[] args) {
		String string = "FirstNameLastName";
		//creates document and assigned it a name
		Document document = new Document();
		
		try {
			//creates a pdf writer with the name "testPDF.pdf" and makes it an outputable file
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("data/testPDF.pdf"));
			PdfWriter writerCopy =PdfWriter.getInstance(document, new FileOutputStream("data/testPDFCopy.pdf"));
			//needs to open the document
			document.open();
			//Adds a new paragraph to the pdf
			document.add(new Paragraph("This is a test of adding a new pharagraph"));
			document.add(new Paragraph("Hello"));
			document.add(new Paragraph(string));
			//created a new table with 3 columns to add to the pdf
			PdfPTable table= new PdfPTable(3);
			//sets the width percentage
			table.setWidthPercentage(105);
			//padding
			table.setSpacingBefore(1f);
			table.setSpacingAfter(11f);
			//width for each column
			float[] columnWidth= {3f,0.5f,2f};
			table.setWidths(columnWidth);
			//sets variables for each cell
			PdfPCell c1=new PdfPCell(new Paragraph("Column1"));
			PdfPCell c2=new PdfPCell(new Paragraph("Column2"));
			PdfPCell c3=new PdfPCell(new Paragraph("Column3"));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			//every 3 cells is a new row
			table.addCell("1");
			table.addCell("2");
			table.addCell("3");
			//adds the table on to the document
			document.add(table);

		
			//has to end by closing the document
			document.close();
			
			writerCopy.close();
			writer.close();
		}
		//Error stuff so the code doesn't break
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
