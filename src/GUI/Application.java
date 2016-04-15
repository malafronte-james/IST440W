package GUI;

/**
 * James Malafronte
 * Create JFrame and run the application through main
 * Version 1.0.0
 * 
 * Application.java
 * 
 */

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Application extends JFrame
{
	
    //errorJPanel e = new errorJPanel();
    
	public Application()
	{
		super("ENF Database");
		
 		//set look and feel to nimbus
 		try 
 		{
 			   for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
 			    {
 			        if ("Nimbus".equals(info.getName())) 
 			        {
 			        	//set to nimbus look and feel
 			            UIManager.setLookAndFeel(info.getClassName());
 			            
 			            //break from the try/catch
 			            break;
 			        }
 			    }
 		    
 		} 
		
 		catch (Exception e) 
 		{
 			
 		}// end try/catch
 		
	     // sizes frame width, height
	     setSize(800, 650);
	     
	     //disable resize
	     setResizable(false);
	  
	     setVisible(true);
	     
	}
	
	
	/**
	 * Main Method
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
 		//set look and feel to nimbus
 		try 
 		{
 			   for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
 			    {
 			        if ("Nimbus".equals(info.getName())) 
 			        {
 			        	//set to nimbus look and feel
 			            UIManager.setLookAndFeel(info.getClassName());
 			            
 			            //break from the try/catch
 			            break;
 			        }
 			    }
 		    
 		} 
		
 		catch (Exception e) 
 		{
 			
 		}// end try/catch
 		
		EventHandler app = new EventHandler();
		
		//SQLiteConnector con = new SQLiteConnector();
		
	}// end main
		
}
