package Utilities;

/**
 * Print Handler
 * Version 1.0.0
 * 
 * @author jmalafronte
 * 
 * retrieved from:
 * https://apache.googlesource.com/pdfbox/+/trunk/examples/src/main/java/org/apache/pdfbox/examples/printing/Printing.java?autodive=0
 * 
 * PrintHandler.java
 * 
 */

import java.awt.print.*;
import java.io.*;
import javax.print.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.printing.*;

public class PrintHandler {

	/**
	 * Choose the printer
	 * @return
	 */
	public static PrintService choosePrinter() {
	    PrinterJob printJob = PrinterJob.getPrinterJob();
	    if(printJob.printDialog()) {
	        return printJob.getPrintService();          
	    }
	    else {
	        return null;
	    }
	}
	
	
	/**
	 * Print the document
	 * @param document
	 * @throws IOException
	 * @throws PrinterException
	 */
    private static void print(PDDocument document) throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.print();
    }

    /**
     * 
     * @param fileName
     * @param printer
     * @throws IOException
     * @throws PrinterException
     */
	public static void printPDF(String fileName, PrintService printer)
	        throws IOException, PrinterException {
	    
	    
        PDDocument document = PDDocument.load(new File(fileName));
        
        // choose your printing method:
        print(document); 
	}
	
}
