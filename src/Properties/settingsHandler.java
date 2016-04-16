package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.swing.JOptionPane;

public class settingsHandler 
{
	String databasePath = null;
	String xmlPath = "";
	Properties settings;
	FileInputStream in;
	FileOutputStream out;
	FileWriter out2;
	String settingsFilename;
	
	public settingsHandler()
	{
		loadSettings();
	}
	
	public String getDatabasePath()
	{
		return databasePath;
	}
	
	/**
	 * 
	 * @param sDbPath
	 * @param sXmlPath
	 */
	public void savePaths(String sDbPath, String sXmlPath)
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
			settings.setProperty("xmlPath", sXmlPath);
			settings.store(out, "Properties");
								
			// save properties to variables
			databasePath = sDbPath;
			xmlPath = sXmlPath;
				
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
	 * 
	 * @return
	 */
	public String getXmlPath()
	{
		return xmlPath;
	}
	
	
	/**
	 * 
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
			xmlPath = settings.getProperty("xmlPath");
			
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
				
				//set the path of the database to the current path of the program
				String xmlpaths = "C:"+ File.separator + "temp"+ File.separator + "xml";
				
				settings.setProperty("databasePath", paths);
				settings.setProperty("xmlPath", xmlpaths);
				settings.store(out, "Properties");
							
				databasePath = paths;

				out.close();
				
				JavaDownloadFile down = new JavaDownloadFile("C:\\temp");

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Error saving new file, please move application to a writable location.",
						"Error saving new File",
						JOptionPane.ERROR_MESSAGE);
				
				System.exit(0);
			}
			
			
			/*JOptionPane.showMessageDialog(null,
					"Error loading " + settingsFilename + "\n\nCreated File in current directory.\n\nEnsure the database file is in the current directory.",
					"Error - File Not Found!",
					JOptionPane.ERROR_MESSAGE);
			*/
			
			JOptionPane.showMessageDialog(null,
					"Error loading " + settingsFilename + "\n\nCreated File in current directory.",
					"Error - File Not Found!",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	/**
	 * 
	 */
	public void writeSettings()
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
			String paths = directory + File.separator + "UltaBeauty.sqlite";
			
			settings.setProperty("databasePath", paths);
			settings.store(out, "Properties");
			
			
			databasePath = paths;
			
			//convert it to bytes
			//byte data[] = paths.getBytes();
			
			//write the bytes to the file
			//out.write(data);
			
			out.close();
			
			JavaDownloadFile down = new JavaDownloadFile("C:\\temp");

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
					"Error saving new file, please move application to a writable location.",
					"Error saving new File",
					JOptionPane.ERROR_MESSAGE);
			
			System.exit(0);
		}
		
		
		/*JOptionPane.showMessageDialog(null,
				"Error loading " + settingsFilename + "\n\nCreated File in current directory.\n\nEnsure the database file is in the current directory.",
				"Error - File Not Found!",
				JOptionPane.ERROR_MESSAGE);
		*/

	}
	
	
}
