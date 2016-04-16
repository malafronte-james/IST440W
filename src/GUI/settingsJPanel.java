package GUI;

import java.awt.*;
import java.io.*;
import java.util.Properties;
import javax.swing.*;

import Properties.settingsHandler;
import net.miginfocom.swing.MigLayout;

public class settingsJPanel extends JPanel
{
	JButton settingsSaveButton, chooseDatabaseFile, chooseXmlFile;
	JTextField txtDatabasePath, txtXmlPath;
	settingsHandler settings;

	
	settingsJPanel()
	{
		super();
		this.setLayout(new BorderLayout());
	
		settings = new settingsHandler();
		settings.loadSettings();
		
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
			
	}
	
	/**
	 * Build the northPanel
	 */
	public void buildNorthPanel()
	{
		//instantiate northPanel
		JPanel northPanel = new JPanel();
		
		//instantiate the title label
	    ImageIcon titleImage = new ImageIcon(this.getClass().getResource("Images/settings.jpg"));
	    
		//instantiate the title label
		JLabel titleLabel = new JLabel(titleImage);
		
		//create new font
		Font titleFont = new Font("Courier", Font.BOLD, 55);
		
		//set titleLabelFont
		titleLabel.setFont(titleFont);
		
		//add the title Label to the northPanel
		titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,20,0,0));
		northPanel.add(titleLabel);
		
		northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//add the norhtPanel to the contentPane
		this.add("North", northPanel);
		
		return;
	}// end buildNorthPanel
	
    /**
     * Build CenterPanel components
     */
    private void buildCenterPanel()
    {   	       	
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout(new MigLayout());
    	JScrollPane centerScroll = new JScrollPane(centerPanel,
    			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
    			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	// Database Path
    	centerPanel.add(new JLabel("Database Path"));
    	txtDatabasePath = new JTextField(30);
    	txtDatabasePath.setText(settings.getDatabasePath());
    	centerPanel.add(txtDatabasePath, "pushx, growx");
    	
    	chooseDatabaseFile = new JButton("Choose");
    	centerPanel.add(chooseDatabaseFile, "wrap");
    	
    	
    	// XML Path
    	centerPanel.add(new JLabel("XML Path"));
    	txtXmlPath = new JTextField(10);
    	txtXmlPath.setText(settings.getXmlPath());
    	centerPanel.add(txtXmlPath, "pushx, growx");
    	
    	chooseXmlFile = new JButton("Choose");
    	centerPanel.add(chooseXmlFile, "wrap");
    	
    	// add centerPanel to the contentPane
    	add(centerScroll, BorderLayout.CENTER);
    	
     	  return;
    }// end buildCenterPanel
    
    /**
     * Build SouthPanel components
     */
    private void buildSouthPanel()
    {
 	 //instantiate all the JPanels
 	 JPanel southPanel = new JPanel();

 	settingsSaveButton = new JButton("Save");
 	 southPanel.add(settingsSaveButton);
 	 
 	 //add southPanel to the JPanel
 	 this.add("South", southPanel);
		      	
		return;
    }// end buildSouthPanel
}
