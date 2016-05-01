package sql.console;

/**
 * @author jmalafronte
 * Creates the console gui
 * Version 1.0.0
 * 
 * ConsoleFrame.java
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.*;
import Database.NewDefaultTableModel;
import GUI.*;

public class ConsoleFrame extends smallJFrame{

    public JButton runButton;
    public JTable outputTable;
    public NewDefaultTableModel model;
    public String databasePath;
    public JTextArea inputArea;
    public JScrollPane scrolls;
    
	public ConsoleFrame()
	{
		super("SQL Console", 850, 500);
		
		setLayout(new BorderLayout());
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

		inputArea = new JTextArea(5,35);
		northPanel.add(new JLabel("Query Area:"));
		northPanel.add(inputArea);
		
		// run button
		runButton = new JButton("Run Query");
		northPanel.add(runButton);
		
		northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//add the norhtPanel to the contentPane
		add("North", northPanel);
		
		return;
	}// end buildNorthPanel
	
    /**
     * Build CenterPanel components
     * @throws SQLException 
     */
    private void buildCenterPanel()
    {   
    	/*
    	  //instantiate new table model
  		model = new NewDefaultTableModel(databasePath); 
            
             //instantiate JTable with tableModel
          	  outputTable = new JTable(model);
          	  
        	  //set up table with borders and headers
        	  outputTable.setBorder(BorderFactory.createLineBorder(Color.black));
        	  
           	  //add the table to a scroll pane
         	  JScrollPane scrolls = new JScrollPane(outputTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         	  
         	  //set scrollPane color
         	  scrolls.getViewport().setBackground(Color.lightGray);
           
         	  //add scrollPane to JPanel
         	  add("Center", scrolls);
         	  
         	  try {
    			model.getUsers(model, outputTable);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
         	  */
     	  return;
    }// end buildCenterPanel
    
    /**
     * Build SouthPanel components
     */
    private void buildSouthPanel()
    {
 	
		return;
    }// end buildSouthPanel
}
