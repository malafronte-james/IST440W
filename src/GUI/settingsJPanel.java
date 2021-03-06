package GUI;

/**
 * Settings Panel Components
 * Version 1.0.0
 * 
 * @author jmalafronte
 * settingsJPanel.java
 * 
 */

import java.awt.*;
import javax.swing.*;
import Properties.settingsHandler;
import net.miginfocom.swing.MigLayout;

public class settingsJPanel extends JPanel
{
	JButton settingsSaveButton, chooseDatabaseFile, choosePDFTemplateLocation, choosePDFOutputLocation;
	JTextField txtDatabasePath, txtPdfPath, txtPdfOutputPath;
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
    	
    	
    	// PDF Template Path
    	centerPanel.add(new JLabel("PDF Template Path"));
    	txtPdfPath = new JTextField(10);
    	txtPdfPath.setText(settings.getPdfPath());
    	centerPanel.add(txtPdfPath, "pushx, growx");
    	
    	choosePDFTemplateLocation = new JButton("Choose");
    	centerPanel.add(choosePDFTemplateLocation, "wrap");
    	
    	// PDF Output Path
    	centerPanel.add(new JLabel("PDF Output Path"));
    	txtPdfOutputPath = new JTextField(10);
    	txtPdfOutputPath.setText(settings.getPdfOutPath());
    	centerPanel.add(txtPdfOutputPath, "pushx, growx");
    	
    	choosePDFOutputLocation = new JButton("Choose");
    	centerPanel.add(choosePDFOutputLocation, "wrap");
    	
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
