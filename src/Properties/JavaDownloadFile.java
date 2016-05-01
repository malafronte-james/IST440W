package Properties;

//https://github.com/itcuties/Java-Download-File
/**
 * Downloads database from website
 * Version 1.0.0
 * 
 * @author jmalafronte
 * 
 * retrieved from:
 * https://github.com/itcuties/Java-Download-File
 * 
 * JavaDownloadFile.java
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;
 
public class JavaDownloadFile {
 
    public JavaDownloadFile(String path) {
        try {
            download("http://projectjm.com/UltaBeauty.sqlite", path);
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * Method downloads file from URL to a given directory.
     * @param fileURL   -   file URL to download
     * @param destinationDirectory  - directory to download file to
     * @throws IOException
     */
    private void download(String fileURL, String destinationDirectory) throws IOException {
    	
    	File theDir = new File(destinationDirectory);

    	// if the directory does not exist, create it
    	if (!theDir.exists()) {
    	    System.out.println("creating directory: " + destinationDirectory);
    	    boolean result = false;

    	    try{
    	        theDir.mkdir();
    	        result = true;
    	    } 
    	    catch(SecurityException se){
    	        
    	    }        
    	    if(result) {    
    	        System.out.println("DIR created");  
    	    }
    	}
    	
        // File name that is being downloaded
        String downloadedFileName = fileURL.substring(fileURL.lastIndexOf("/")+1);
         
        // Open connection to the file
        URL url = new URL(fileURL);
        InputStream is = url.openStream();
        // Stream to the destionation file
        FileOutputStream fos = new FileOutputStream(destinationDirectory + "/" + downloadedFileName);
  
        // Read bytes from URL to the local file
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
         
        System.out.print("Downloading " + downloadedFileName);
        while ((bytesRead = is.read(buffer)) != -1) {
            //System.out.print(".");  // Progress bar :)
            fos.write(buffer,0,bytesRead);
        }
		JOptionPane.showMessageDialog(null,
				"Database Downloaded to C:\\temp\\",
				"Download Complete",
				JOptionPane.ERROR_MESSAGE);
		
        System.out.println("done!");
  
        // Close destination stream
        fos.close();
        // Close URL stream
        is.close();
    }   
}