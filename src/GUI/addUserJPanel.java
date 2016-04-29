package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class addUserJPanel extends JPanel
{
	JButton addUserSaveButton;
	JTextField txtSapUserName, txtAdminID, txtJobTitle, txtEmail;
	JComboBox cmbDepartment, cmbAccessLevel;
	public JPasswordField txtPassword, txtConfirmPassword;
	
	addUserJPanel()
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
		//instantiate northPanel
		JPanel northPanel = new JPanel();
		
		//instantiate the title label
	    ImageIcon titleImage = new ImageIcon(this.getClass().getResource("Images/addUser.jpg"));
	    
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
    	
    	// Admin ID
    	centerPanel.add(new JLabel("User ID"));
    	txtAdminID = new JTextField(4);
    	centerPanel.add(txtAdminID, "wrap, pushx");
    	txtAdminID.setEditable(false);
    	
    	// SAPuserName
    	centerPanel.add(new JLabel("SAP username"));
    	txtSapUserName = new JTextField(10);
    	centerPanel.add(txtSapUserName, "wrap, pushx, growx");    	
    	
    	// Password
    	centerPanel.add(new JLabel("Password"));
    	txtPassword = new JPasswordField(10);
    	centerPanel.add(txtPassword, "wrap, pushx, growx");
    	
    	// Confirm Password
    	centerPanel.add(new JLabel("Confirm Password"));
    	txtConfirmPassword = new JPasswordField(10);
    	centerPanel.add(txtConfirmPassword, "wrap, pushx, growx");
    	
    	// E-Mail Address
    	centerPanel.add(new JLabel("E-Mail Address"));
    	txtEmail = new JTextField(10);
    	centerPanel.add(txtEmail, "wrap, pushx, growx");
    	
    	// Department
    	centerPanel.add(new JLabel("Department"));
    	cmbDepartment = new JComboBox( new String[] {"Dot Com", "QA", "Receiving","Replenishment","Retail Picking","Distro","Shipping"});
    	centerPanel.add(cmbDepartment, "wrap, pushx, growx");
    	
    	// Job Title
    	centerPanel.add(new JLabel("Job Title"));
    	txtJobTitle = new JTextField(10);
    	centerPanel.add(txtJobTitle, "wrap, pushx, growx");
    	
    	// AccessLevel
    	centerPanel.add(new JLabel("Access Level"));
    	cmbAccessLevel = new JComboBox( new String[] {"Administrator", "Developer"});
    	centerPanel.add(cmbAccessLevel, "wrap, pushx, growx");
    	
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

 	 addUserSaveButton = new JButton("Save");
 	 southPanel.add(addUserSaveButton);
 	 
 	 //add southPanel to the JPanel
 	 this.add("South", southPanel);
		      	
		return;
    }// end buildSouthPanel
	
}
