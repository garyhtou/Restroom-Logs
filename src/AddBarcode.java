//https://kbdeveloper.qoppa.com/add-a-barcode-to-a-pdf-document-using-jpdfprocess/
//https://kbdeveloper.qoppa.com/wp-content/uploads/AddBarcode.java_.txt

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfProcess.PDFDocument;
import com.qoppa.pdfProcess.PDFPage;

/**
 * This sample demonstrates how to add barcodes to a page in a PDF document with jPDFProcess.  This can
 * be done in two different ways:
 * 
 * - Add a barcode using a barcode font.  In this case, we would simply add text to the PDF document using
 *   a barcode font, so the document will have both the barcode appearance as well as the barcode contents.
 *   
 * - Add a barcode as an image.  To do this, you will first need to create an image of the barcode using
 *   a third party library (such as Barcode4J) and then the image can be added to the PDF document.
 * 
 * @author Qoppa Software
 *
 */
public class AddBarcode
{
	public static void main(String [] args)
	{
		try
		{
			String barcodeMSG = "0123456789";

			// Create a blank document and add a page
			PDFDocument pdf = new PDFDocument();
			PDFPage newPage = pdf.appendNewPage(8.5 * 72, 11 * 72);
			Graphics2D pageG2 = newPage.createGraphics();

			// Add a barcode using a font
			addFontBarcode(pdf, pageG2, barcodeMSG, 72, 108);

			// Add a barcode using an image
			addImageBarcode(pageG2, barcodeMSG, 72, 216);
			
			// Save the document
			pdf.saveDocument("barcode.pdf");
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
	
	private static void addFontBarcode(PDFDocument pdf, Graphics2D pageG2, String barcodeMSG, int posX, int posY) throws PDFException, FontFormatException, IOException
	{
		// Embed the font
		Font code39Font = pdf.embedFont("free3of9.ttf", Font.TRUETYPE_FONT);
		pageG2.setFont(code39Font.deriveFont(64f));
		pageG2.drawString(barcodeMSG, posX, posY);
	}
	
	private static void addImageBarcode(Graphics2D pageG2, String barcodeMSG, int posX, int posY) throws PDFException
	{
		// This code creates a barcode image using Barcode39 and then adds the image to the page
		Code39Bean code39 = new Code39Bean();
		code39.setModuleWidth(2);
		code39.setBarHeight(50);
		code39.setWideFactor(2);
		BarcodeDimension dim = code39.calcDimensions(barcodeMSG);

		BufferedImage barcodeImage = new BufferedImage((int)dim.getWidth(), (int)dim.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D imageG2 = barcodeImage.createGraphics();
		imageG2.setColor(Color.white);
		imageG2.fillRect(0, 0, barcodeImage.getWidth(), barcodeImage.getHeight());
		imageG2.setColor(Color.black);
		code39.generateBarcode(new Java2DCanvasProvider(imageG2, 0), "0123456789");
		
		// Add the image to the page
		pageG2.drawImage(barcodeImage, posX, posY, null);
	}
}
