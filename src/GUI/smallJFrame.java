package GUI;

/**
 * use this for all small jframes, such as login menus and report manager
 * Version 1.0.0
 * 
 * @author jmalafronte
 * smallJFrame.java
 * 
 */

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class smallJFrame extends JFrame 
{

	protected smallJFrame(String title, int length, int width)
	{
		super(title);
		
	     //this.setIconImage(new ImageIcon(getClass().getResource("Images/windowIcon32.jpg")).getImage());
	     
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
	     setSize(length, width);
	     
	     //disable resize
	     setResizable(false);
	  
	     setVisible(true);
	     
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
