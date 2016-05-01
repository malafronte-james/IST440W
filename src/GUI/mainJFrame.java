package GUI;

/**
 * used for all of our jframes that are of normal size </br>
 * implement jmenubar into this
 * 
 * Version 1.0.0
 * 
 * @author jmalafronte
 * mainJFrame.java
 * 
 */

import java.awt.*;
import javax.swing.*;

public class mainJFrame extends JFrame {

    final private Color BACKGROUND = Color.ORANGE; //background color constant
	JMenuBar menuBar = new JMenuBar(); 
	
	JMenu homeMenu; 
	JMenu errorsMenu; 
	JMenu reportsMenu; 
	JMenu accountMenu;
	JMenu adminMenu;
	JMenu developerMenu;
	JMenuItem addItem, homeItem, exitItem, refreshErrorsItem; 
	JMenuItem adminEditUserItem,
			  adminAddUserItem,
			  adminUserListItem,
			  adminEditErrorItem,
			  adminDeleteErrorItem,
			  adminSendReportsItem,
			  settingsItem; 
	JMenuItem devUserListItem,
			  devEditErrorItem,
			  devDeleteErrorItem,
			  devSendReportsItem,
			  devSQLItem,
			  devAddUserItem,
			  devEditUserItem;
	JMenuItem viewItem; 
	JMenuItem loginItem, logoutItem;
	JMenuItem changePwItem;
	JMenuItem reportItem; 
	
	
	mainJFrame()
	{
		super("ENF Database - Version 1.0.0");
			
	     // sizes frame width, height
	     setSize(800, 650);
	     
	     this.setIconImage(new ImageIcon(getClass().getResource("Images/windowIcon32.jpg")).getImage());

	     menuBar(this);
	     
	     //disable resize
	     setResizable(false);
	  
	     setVisible(true);
	     
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Build the northPanel
	 */
	public void buildNorthPanel()
	{

		return;
	}// end buildNorthPanel
	
	/**
	 * Build the centerPanel
	 */
	public void buildCenterPanel()
	{

		return;
		
	}// end buildCenterPanel
	
	/**
	 * Build the southPanel
	 */
	public void buildSouthPanel()
	{
		return;
		
	}// End buildSouthPanel
	
	/**
	 * Add components to the menuBar
	 * @param frame the frame to add the menuBar to
	 */
	public final void menuBar(JFrame frame) {
		//initialize menu bar and add menu items 
		frame.setJMenuBar(menuBar);
		homeMenu = new JMenu("Home"); 
		errorsMenu = new JMenu("Errors"); 
		reportsMenu = new JMenu("Reports"); 
		accountMenu = new JMenu("Account"); 
		adminMenu = new JMenu("Admin");
		developerMenu = new JMenu("Developer");
		
	
		//home menu **shortcut to bring up the error list
		homeItem = new JMenuItem("Error List");
		settingsItem = new JMenuItem("Settings");
		exitItem = new JMenuItem("Exit");
		homeMenu.add(homeItem);
		homeMenu.add(settingsItem);
		homeMenu.add(exitItem);
		
		//admin menu
		adminMenu = new JMenu("Admin");
		adminAddUserItem = new JMenuItem("Add User");
		adminEditUserItem = new JMenuItem("Edit User");
		adminUserListItem = new JMenuItem("User List"); 
		adminEditErrorItem = new JMenuItem("Edit Error"); 
		adminDeleteErrorItem = new JMenuItem("Delete Error"); 
		adminSendReportsItem = new JMenuItem("Send Reports"); 
		adminMenu.add(adminAddUserItem);
		adminMenu.add(adminEditUserItem);
		adminMenu.add(adminUserListItem);
		adminMenu.add(adminEditErrorItem);
		adminMenu.add(adminDeleteErrorItem);
		adminMenu.add(adminSendReportsItem);
		
		//developer menu
		developerMenu = new JMenu("Developer");
		devAddUserItem = new JMenuItem("Add User");
		devEditUserItem = new JMenuItem("Edit User");
		devUserListItem = new JMenuItem("User List"); 
		devEditErrorItem = new JMenuItem("Edit Error"); 
		devDeleteErrorItem = new JMenuItem("Delete Error"); 
		devSendReportsItem = new JMenuItem("Send Reports"); 
		devSQLItem = new JMenuItem("SQL Command");
		developerMenu.add(devAddUserItem);
		developerMenu.add(devEditUserItem);
		developerMenu.add(devUserListItem);
		developerMenu.add(devEditErrorItem);
		developerMenu.add(devDeleteErrorItem);
		developerMenu.add(devSendReportsItem);
		developerMenu.add(devSQLItem);
		
		//reports menu
		reportItem = new JMenuItem("Report Manager"); 
		reportsMenu.add(reportItem);
		
		//errors menu
		addItem = new JMenuItem("Add"); 
		refreshErrorsItem = new JMenuItem("Refresh");
		viewItem = new JMenuItem("View"); 
		errorsMenu.add(addItem);
		errorsMenu.add(refreshErrorsItem);
		errorsMenu.add(viewItem);
		
		//account menu
		loginItem = new JMenuItem("Login");
		changePwItem = new JMenuItem("Change Password");
		logoutItem = new JMenuItem("Logout"); 
		accountMenu.add(loginItem);


		//add all components to menubar
		menuBar.add(homeMenu); 
		menuBar.add(errorsMenu); 
		menuBar.add(reportsMenu);
		menuBar.add(accountMenu); 
		
	}
}
