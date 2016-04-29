package Utilities;

/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 * 
 * 
 * Edited: JMalafronte
 */
 
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.TextField;
 
public class PDFHandler implements PdfPCellEvent {
 
    /** The resulting PDF. */
    public static final String RESULT1 = "C:\\temp\\template2.pdf";
    /** The resulting PDF. */
    public static final String RESULT2 = "C:\\temp\\ReportView.pdf";
    /** The text field index of a TextField that needs to be added to a cell. */
    protected int tf;
 
    /**
     * Creates a cell event that will add a text field to a cell.
     * @param tf a text field index.
     */
    public PDFHandler(int tf) {
        this.tf = tf;
    }
 
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void enfPdf(String src, String dest, TempData data) throws IOException, DocumentException {
    	
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.setField("ENF_ID", data.getEnfID());
        form.setField("Associate", data.getSapUserName());
        form.setField("Date_Created", data.getDate_Created());
        form.setField("documented_by", data.getDocumented_by());
        form.setField("process", data.getProcess());
        form.setField("article", data.getArticle());
        form.setField("Location", data.getLocation());
        form.setField("quantity", data.getQuantity());
        form.setField("date_of_error", data.getDate_of_error());
        form.setField("notes", data.getNotes());
        //form.setFieldProperty("Date_Created", "fflags", 0, null);
        //form.setFieldProperty("documented_by", "bordercolor", BaseColor.RED, null);
        //form.setFieldProperty("text_3", "clrfflags", TextField.PASSWORD, null);
        //form.setFieldProperty("text_3", "setflags", PdfAnnotation.FLAGS_PRINT, null);
       // form.setFieldProperty("text_4", "textsize", new Float(12), null);
       // form.regenerateField("text_4");
        stamper.close();
        reader.close();
    }
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename) throws DocumentException, IOException {
    	// step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        PdfPCell cell;
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new int[]{ 1, 2 });
 
        table.addCell("Name:");
        cell = new PdfPCell();
        cell.setCellEvent(new PDFHandler(1));
        table.addCell(cell);
 
        table.addCell("Loginname:");
        cell = new PdfPCell();
        cell.setCellEvent(new PDFHandler(2));
        table.addCell(cell);
 
        table.addCell("Password:");
        cell = new PdfPCell();
        cell.setCellEvent(new PDFHandler(3));
        table.addCell(cell);
 
        table.addCell("Reason:");
        cell = new PdfPCell();
        cell.setCellEvent(new PDFHandler(4));
        cell.setFixedHeight(60);
        table.addCell(cell);
 
        document.add(table);
        // step 5
        document.close();
 
    }
 
    /**
     * Creates and adds a text field that will be added to a cell.
     * @see com.itextpdf.text.pdf.PdfPCellEvent#cellLayout(com.itextpdf.text.pdf.PdfPCell,
     *      com.itextpdf.text.Rectangle, com.itextpdf.text.pdf.PdfContentByte[])
     */
    public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
        PdfWriter writer = canvases[0].getPdfWriter();
        TextField text = new TextField(writer, rectangle,
                String.format("text_%s", tf));
        text.setBackgroundColor(new GrayColor(0.75f));
        switch(tf) {
        case 1:
            text.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
            text.setText("Enter your name here...");
            text.setFontSize(0);
            text.setAlignment(Element.ALIGN_CENTER);
            text.setOptions(TextField.REQUIRED);
            break;
        case 2:
            text.setMaxCharacterLength(8);
            text.setOptions(TextField.COMB);
            text.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
            text.setBorderColor(BaseColor.BLUE);
            text.setBorderWidth(2);
            break;
        case 3:
            text.setBorderStyle(PdfBorderDictionary.STYLE_INSET);
            text.setOptions(TextField.PASSWORD);
            text.setVisibility(TextField.VISIBLE_BUT_DOES_NOT_PRINT);
            break;
        case 4:
            text.setBorderStyle(PdfBorderDictionary.STYLE_DASHED);
            text.setBorderColor(BaseColor.RED);
            text.setBorderWidth(2);
            text.setFontSize(8);
            text.setText(
                "Enter the reason why you want to win a free accreditation for the Foobar Film Festival");
            text.setOptions(TextField.MULTILINE | TextField.REQUIRED);
            break;
        }
        try {
            PdfFormField field = text.getTextField();
            if (tf == 3) {
                field.setUserName("Choose a password");
            }
            writer.addAnnotation(field);
        }
        catch(IOException ioe) {
            throw new ExceptionConverter(ioe);
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
}