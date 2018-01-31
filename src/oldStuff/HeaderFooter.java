package oldStuff;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfName;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Adds a Header to the PDF.<br>
 * <strong>Format:</strong>  LOGO  Restroom Logs DATE<br>
 */
public class HeaderFooter extends PdfPageEventHelper {
	 public static final String IMG1 = "assets/logos/RestroomLogsLogo.png";
    private PdfTemplate t;
    private Image total;
    /**
     * Initialized certain variables. Called when the document is opened.
     * 
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    /**
     * Called when a page is finished, just before being written to the document. Adds header and footer to every page.<br>
     *  </br>
     */
    @Override  
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        addFooter(writer);
    }

    /**
     * Puts an image into a cell so that it can be placed into another cell.<br>
     *  </br>
     *  @param path The path of the image
     * 
     */
    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setPaddingTop(25);
        
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
    /**
     * Adds the header<br>
     *  </br>
     *  
     * 
     */
    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            
            // add image

           // header.addCell(logo);
            header.addCell(createImageCell(IMG1));
           // header.addCell(" ");

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingTop(20);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy");
    		LocalDateTime now = LocalDateTime.now();
    		String TimeAndData = dtf.format(now) ;
            text.addElement(new Phrase("Restroom Logs", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase(TimeAndData, new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }
    /**
     * Adds the footer<br>
     *  </br>
     *  
     * 
     */
    private void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add footer text
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    		LocalDateTime now = LocalDateTime.now();
    		String TimeAndData = dtf.format(now) ;
    		
            footer.addCell(new Phrase("Restroom Logs "+ TimeAndData , new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    /**
     * Is called before the document is closed. Adds the page number.<br>
     *  </br>
     *  
     * 
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}