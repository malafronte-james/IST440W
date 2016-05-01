package Properties;

/**
 * @author jmalafronte
 * Handles the settings
 * Version 1.0.0
 * 
 * settingsHandler.java
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Properties;
import javax.swing.JOptionPane;

public class settingsHandler 
{
	String databasePath = null;
	String pdfPath = "";
	String pdfOutPath = "";
	Properties settings;
	FileInputStream in;
	FileOutputStream out;
	FileWriter out2;
	String settingsFilename;
	
	/**
	 * Load the settings handler
	 */
	public settingsHandler()
	{
		loadSettings();
	}
	
	/**
	 * 
	 * @return the databasePath
	 */
	public String getDatabasePath()
	{
		return databasePath;
	}
	
	/**
	 * @return the pdfOutPath
	 */
	public String getPdfOutPath() {
		return pdfOutPath;
	}

	/**
	 * @param pdfOutPath the pdfOutPath to set
	 */
	public void setPdfOutPath(String pdfOutPath) {
		this.pdfOutPath = pdfOutPath;
	}

	/**
	 * @param pdfPath the pdfPath to set
	 */
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
	/**
	 * 
	 * @param sDbPath
	 * @param sPdfPath
	 */
	public void savePaths(String sDbPath, String sPdfPath, String sPdfOutputPath)
	{
		try
		{
					
			//persistent set of properties to be loaded from stream for connection
			settings = new Properties();
			
			//create new settings.properties file
			File file = new File("settings.properties");
			
			//get the path of the file
			String currentDirectory = file.getAbsolutePath();
										
			out = new FileOutputStream(currentDirectory);
				
			//get the path of the program
			String directory = System.getProperty("user.dir");
				
			// set properties
			settings.setProperty("databasePath", sDbPath);
			settings.setProperty("pdfTemplatePath", sPdfPath);
			settings.setProperty("pdfOutputPath", sPdfOutputPath);
			settings.store(out, "Properties");
								
			// save properties to variables
			databasePath = sDbPath;
			pdfPath = sPdfPath;
			pdfOutPath = sPdfOutputPath;
				
			out.close();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Error saving new file, please move application to a writable location.",
						"Error saving new File",
						JOptionPane.ERROR_MESSAGE);
				
				System.exit(0);
			}
		
		JOptionPane.showMessageDialog(null,
				"Settings Saved.\n\nPlease restart application for changes to take affect.",
				"Saved Successfully",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/**
	 * get the PDF Path
	 * @return
	 */
	public String getPdfPath()
	{
		return pdfPath;
	}
	
	
	/**
	 * Load the settings
	 */
	public void loadSettings()
	{
		settingsFilename = "settings.properties";
		
		try 
		{
	
			//persistent set of properties to be loaded from stream for connection
			settings = new Properties();
			
			//instantiate a new FileInputStream
			in = new FileInputStream(settingsFilename);
			
			//load the properties File
			settings.load(in);
			
			//assign all the properties to variables
			databasePath = settings.getProperty("databasePath");
			pdfPath = settings.getProperty("pdfTemplatePath");
			pdfOutPath = settings.getProperty("pdfOutputPath");
			
			//close the FileInputStream
			in.close();
			
			
			
		}
		catch (Exception e)
		{
			
			//create new settings.properties file
			File file = new File("settings.properties");
			
			//get the path of the file
			String currentDirectory = file.getAbsolutePath();
			
			try {
							
				out = new FileOutputStream(currentDirectory);
				
				//get the path of the program
				String directory = System.getProperty("user.dir");
								
				//set the path of the database to the current path of the program
				String paths = "C:"+ File.separator + "temp"+ File.separator + "UltaBeauty.sqlite";
				
				File db = new File(paths);
				
				//set the path of the database to the current path of the program
				String pdfPath = "C:"+ File.separator + "temp"+ File.separator + "pdf" + File.separator;
							
				// set properties
				settings.setProperty("databasePath", paths);
				settings.setProperty("pdfTemplatePath", pdfPath);
				settings.setProperty("pdfOutputPath", pdfPath);
				settings.store(out, "Properties");
																
				databasePath = paths;

				out.close();
				
		    	if (!db.exists()) {
		    		String download = "C:"+ File.separator + "temp"+ File.separator;
		    		JavaDownloadFile down = new JavaDownloadFile(download);
		    	}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Error saving new file, please move application to a writable location.",
						"Error saving new File",
						JOptionPane.ERROR_MESSAGE);
				
				System.exit(0);
			}
			
			
			JOptionPane.showMessageDialog(null,
					"Error loading " + settingsFilename + "\n\nCreated File in current directory.",
					"Error - File Not Found!",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
		
	
}
