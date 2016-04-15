package GUI;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import Database.*;
import Properties.settingsHandler;

public class errorJPanel extends JPanel
{
	//Settings setting = new Settings();
    protected JButton addButton, editButton, printButton;
    JButton backButton = new JButton("Back");
    JTable outputTable;
    NewDefaultTableModel model;
    String databasePath;
	
	errorJPanel(String db)
	{
		super();
		
		databasePath = db;
		
		this.setLayout(new BorderLayout());
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
	}
	
	/**
	 * Build the northPanel
	 */
	public void buildNorthPanel()
	{
	    ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("Images/errorList.jpg"));
		
		//instantiate northPanel
		JPanel northPanel = new JPanel();

		//instantiate the title label
		JLabel titleLabel = new JLabel(imageIcon);
		
		//add the title Label to the northPanel
		northPanel.add(titleLabel);
		
		northPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//add the norhtPanel to the contentPane
		this.add("North", northPanel);
		
		return;
	}// end buildNorthPanel
	
    /**
     * Build CenterPanel components
     * @throws SQLException 
     */
    private void buildCenterPanel()
    {   	       	
  	  //instantiate new table model
		model = new NewDefaultTableModel(databasePath); 
			 /*	@Override
			  
			  	 * Method Override to make skuNumber column uneditable

				public boolean isCellEditable(int row, int col)
				{
					if (col == 0)
					{
						return false;
					}
					
					else
					{
						return true;
					}
					
				}// end isCellEditable
		         	*/
			  	
		         

           
           //instantiate JTable with tableModel
        	  outputTable = new JTable(model);
        	  
        	  //set up table with borders and headers
        	  outputTable.setBorder(BorderFactory.createLineBorder(Color.black));
        	  model.addColumn("ENF ID");
        	  model.addColumn("Opened Date");
        	  model.addColumn("Opened By");
        	  model.addColumn("Date of Error");
        	  model.addColumn("Status");
        	  model.addColumn("Due Date");
        	  
        	  //add the table to a scroll pane
     	  JScrollPane scrolls = new JScrollPane(outputTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     	  
     	  //set scrollPane color
     	  scrolls.getViewport().setBackground(Color.lightGray);
       
     	  //add scrollPane to JPanel
     	  this.add("Center", scrolls);
     	  
     	  try {
			model.getErrors(model, outputTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
     	/* try {
			model.setQuery("SELECT ENF_ID,Opened_Date,Opened_By,Date_of_Error,Status,Due_Date FROM Errors", model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
     	  
     	  return;
    }// end buildCenterPanel
    
    /**
     * Build SouthPanel components
     */
    private void buildSouthPanel()
    {
 	 //instantiate all the JPanels
 	 JPanel southPanel = new JPanel();
 	 JPanel entryPanel = new JPanel();
 	 JPanel buttonPanel = new JPanel();
 	 JPanel logPanel = new JPanel();
 	 
  	 //set background color
	   	 //southPanel.setBackground(BACKGROUND);
	   	 //entryPanel.setBackground(BACKGROUND);
	   	 //logPanel.setBackground(BACKGROUND);
	   	 //buttonPanel.setBackground(BACKGROUND);
 	 
 	 //instantiate and add an action listener to searchButton
	   	 addButton = new JButton("Add");
 	 
 	 //instantiate and add an action listener to deleteButton
 	 	editButton = new JButton("Edit");
 	 	editButton.setEnabled(false);
 	 
 	 //instantiate and add an action listener to updateButton
 	 	printButton = new JButton("Print");
 	 
 	 //set gridLayout
 	 southPanel.setLayout(new GridLayout(1,1));

 	 //add all components to the first gridcell
 	 buttonPanel.add(addButton);
 	 buttonPanel.add(editButton);
 	 buttonPanel.add(printButton);

 	 //add all components to the southPanel
 	 southPanel.add(entryPanel);
 	 southPanel.add(buttonPanel);
 	 southPanel.add(logPanel);
	  	   
 	 //add southPanel to the JPanel
 	 this.add("South", southPanel);
		      	
		return;
    }// end buildSouthPanel
   
}
