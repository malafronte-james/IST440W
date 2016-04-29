package GUI;

import java.awt.*;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.*;

import net.miginfocom.swing.*;

public class editErrorJPanel extends JPanel
{
	JButton saveButton, backButton;
	public JTextField txtSapUserName, txtEnfID, txtOpenedDate, txtProcess, txtLocationAffected, txtDateOfError, txtSkuNumber, txtQty, txtOpenedBy;
	public JComboBox cmbDepartment, cmbShift, cmbStatus;
	public JTextArea txtNotes;
	
	/**
	 * @param txtSapUserName the txtSapUserName to set
	 */
	public void setTxtSapUserName(String txtSapUserName) {
		this.txtSapUserName.setText(txtSapUserName);
		
	}

	/**
	 * @param txtEnfID the txtEnfID to set
	 */
	public void setTxtEnfID(String txtEnfID) {
		this.txtEnfID.setText(txtEnfID);
	}

	/**
	 * @param txtOpenedDate the txtOpenedDate to set
	 */
	public void setTxtOpenedDate(String txtOpenedDate) {
		this.txtOpenedDate.setText(txtOpenedDate);
	}

	/**
	 * @param txtLocationAffected the txtLocationAffected to set
	 */
	public void setTxtLocationAffected(String txtLocationAffected) {
		this.txtLocationAffected.setText(txtLocationAffected);
	}

	/**
	 * @param txtDateOfError the txtDateOfError to set
	 */
	public void setTxtDateOfError(String txtDateOfError) {
		this.txtDateOfError.setText(txtDateOfError);
	}

	/**
	 * @param txtSkuNumber the txtSkuNumber to set
	 */
	public void setTxtSkuNumber(String txtSkuNumber) {
		this.txtSkuNumber.setText(txtSkuNumber);
	}

	/**
	 * @param txtQty the txtQty to set
	 */
	public void setTxtQty(String txtQty) {
		this.txtQty.setText(txtQty);
	}

	/**
	 * @param cmbDepartment the cmbDepartment to set
	 */
	public void setCmbDepartment(String cmbDepartment) {
		//this.cmbDepartment = cmbDepartment;
	}

	/**
	 * @param cmbShift the cmbShift to set
	 */
	public void setCmbShift(String cmbShift) {
		//this.cmbShift = cmbShift;
	}

	/**
	 * @param cmbStatus the cmbStatus to set
	 */
	public void setCmbStatus(String cmbStatus) {
		//this.cmbStatus = cmbStatus;
	}

	editErrorJPanel()
	{
		super();
		this.setLayout(new BorderLayout());
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
		
	    ImageIcon titleImage = new ImageIcon(this.getClass().getResource("Images/editError.jpg"));
	    
		//instantiate northPanel
		JPanel northPanel = new JPanel();
		
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
    			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	// ENF ID
    	centerPanel.add(new JLabel("ENF ID"));
    	txtEnfID = new JTextField(4);
    	centerPanel.add(txtEnfID, "wrap, pushx");
    	txtEnfID.setEditable(false);
    	
    	// SAP user name
    	centerPanel.add(new JLabel("SAP User Name"));
    	txtSapUserName = new JTextField(10);
    	centerPanel.add(txtSapUserName, "wrap, pushx, growx");
    	
    	// Department
    	centerPanel.add(new JLabel("Department"));
    	cmbDepartment = new JComboBox(new String[] {"1"});
    	centerPanel.add(cmbDepartment, "wrap, pushx, growx");
    	
    	// Shift
    	centerPanel.add(new JLabel("Shift"));
    	cmbShift = new JComboBox(new String[] { "1", "2", "3", "W", "W3"});    	
    	centerPanel.add(cmbShift, "wrap");
    	
    	// opened date
    	centerPanel.add(new JLabel("Opened Date"));
    	UtilDateModel model = new UtilDateModel();
    	Properties p = new Properties();
    	p.put("text.today", "Today");
    	p.put("text.month", "Month");
    	p.put("text.year", "Year");
    	JDatePanelImpl openedDatePanel = new JDatePanelImpl(model, p);
    	JDatePickerImpl openedDatePicker = new JDatePickerImpl(openedDatePanel, new DateComponentFormatter());
    	centerPanel.add(openedDatePicker, "wrap, pushx");
    	
    	// opened by
    	centerPanel.add(new JLabel("Opened By"));
    	centerPanel.add(txtOpenedBy = new JTextField(5), "wrap, pushx, growx");
    	
    	// location affected
    	centerPanel.add(new JLabel("Location Affected"));
    	centerPanel.add(txtLocationAffected = new JTextField(5), "wrap, pushx, growx");
    	
    	// date of error
    	centerPanel.add(new JLabel("Date of Error"));
    	UtilDateModel errorDateModel = new UtilDateModel();
    	Properties errorDpProperties = new Properties();
    	errorDpProperties.put("text.today", "Today");
    	errorDpProperties.put("text.month", "Month");
    	errorDpProperties.put("text.year", "Year");
    	JDatePanelImpl errorDatePanel = new JDatePanelImpl(model, errorDpProperties);
    	JDatePickerImpl errorDatePicker = new JDatePickerImpl(errorDatePanel, new DateComponentFormatter());
    	centerPanel.add(errorDatePicker, "wrap, pushx");
    	
    	// Status
    	centerPanel.add(new JLabel("Status"));
    	cmbStatus = new JComboBox(new String[] {"Active","Resolved"});
    	centerPanel.add(cmbStatus, "wrap");
    	
    	// Sku Number
    	centerPanel.add(new JLabel("Sku Number"));
    	txtSkuNumber = new JTextField(8);
    	centerPanel.add(txtSkuNumber, "wrap");
    	
    	// Qty
    	centerPanel.add(new JLabel("Quantity"));
    	txtQty = new JTextField(4);
    	centerPanel.add(txtQty, "wrap");
    	
    	// Process or Location
    	centerPanel.add(new JLabel("Process or Location"));
    	centerPanel.add(txtProcess = new JTextField(5), "wrap, pushx, growx");
    	
    	// Notes
    	centerPanel.add(new JLabel("Notes"));
    	centerPanel.add(txtNotes = new JTextArea(5,5), "wrap, pushx, growx");
    	
    	// Attachments
    	centerPanel.add(new JLabel("Attachments"), "wrap");
    	
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

 	 saveButton = new JButton("Save");
 	 southPanel.add(saveButton);
 	 
 	 backButton = new JButton("Back");
 	 southPanel.add(backButton);
 	 
 	 //add southPanel to the JPanel
 	 this.add("South", southPanel);
		      	
		return;
    }// end buildSouthPanel
    

}
