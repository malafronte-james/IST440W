package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import Database.*;
import Properties.settingsHandler;

public class EventHandler implements ActionListener
{

	settingsHandler settings = new settingsHandler();	
	Connection connection = null;
	LoginMenu login;
	ChangePasswordMenu changePassword;
	ReportManager reportManagerMenu;
	CardLayout cd = new CardLayout();
	settingsJPanel settingsPanel = new settingsJPanel();
	errorJPanel errorPanel = new errorJPanel(settings.getDatabasePath());
	addUserJPanel addUserPanel = new addUserJPanel();
	userListJPanel userListPanel = new userListJPanel(settings.getDatabasePath());
	editUserJPanel editUserPanel = new editUserJPanel();
	editErrorJPanel editErrorPanel = new editErrorJPanel();
	mainJFrame frame = new mainJFrame();
	String username;
	
	PreparedStatement pst;
	
	addErrorJPanel addErrorPanel = new addErrorJPanel();
	
	// card panel will act as the "holding" panel so that switching between the 
	// cards is possible
	JPanel cards = new JPanel(cd);
	 
	EventHandler() throws ClassNotFoundException, SQLException
	{	
		//add card panel to the frame
		frame.add(cards);
		
		// add different cards to the panel
		cards.add(errorPanel, "errorPanel");
		cards.add(addErrorPanel, "addErrorPanel");
		cards.add(addUserPanel, "addUserPanel");
		cards.add(userListPanel, "userListPanel");
		cards.add(settingsPanel, "settingsPanel");
		cards.add(editErrorPanel, "editErrorPanel");
		cards.add(editUserPanel, "editUserPanel");
		
		
		
		// show the errorPanel when the application loads
		cd.show(cards, "errorPanel");
		
		//add actionListeners to menu objects
		frame.addItem.addActionListener(this);
		frame.settingsItem.addActionListener(this);
		frame.viewItem.addActionListener(this);
		frame.homeItem.addActionListener(this);
		frame.exitItem.addActionListener(this);
		frame.loginItem.addActionListener(this);
		frame.logoutItem.addActionListener(this);
		frame.changePwItem.addActionListener(this);
		frame.reportItem.addActionListener(this);
		// admin
		frame.adminAddUserItem.addActionListener(this);
		frame.adminDeleteErrorItem.addActionListener(this);
		frame.adminEditErrorItem.addActionListener(this);
		frame.adminEditUserItem.addActionListener(this);
		frame.adminSendReportsItem.addActionListener(this);
		frame.adminUserListItem.addActionListener(this);
		// dev
		frame.devAddUserItem.addActionListener(this);
		frame.devDeleteErrorItem.addActionListener(this);
		frame.devEditErrorItem.addActionListener(this);
		frame.devEditUserItem.addActionListener(this);
		frame.devSendReportsItem.addActionListener(this);
		frame.devSQLItem.addActionListener(this);
		frame.devUserListItem.addActionListener(this);
		frame.refreshErrorsItem.addActionListener(this);
		
		// add actionListeners to errorPanel objects
		errorPanel.addButton.addActionListener(this);
		errorPanel.editButton.addActionListener(this);
		errorPanel.printButton.addActionListener(this);
		
		// add actionListners to settingsPanel objects
		settingsPanel.settingsSaveButton.addActionListener(this);
		settingsPanel.chooseDatabaseFile.addActionListener(this);
		settingsPanel.chooseXmlFile.addActionListener(this);
		
		// userListPanel
		userListPanel.addButton.addActionListener(this);
		
	}// end EventHandler

	private void createLoginMenu()
	{
		login = new LoginMenu();
		login.loginButton.addActionListener(this);
		
		return;
	}
	
	private void createChangePWMenu()
	{
		changePassword = new ChangePasswordMenu();
		changePassword.saveButton.addActionListener(this);
		
		return;
	}
	
	private void createReportManagerMenu()
	{
		reportManagerMenu = new ReportManager();
		
		return;
	}
	
	private void createEditErrorForm(String enfNumber)
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * 
		 * Frame Actions
		 * 
		 */
		if(e.getSource() == frame.addItem || e.getSource() == errorPanel.addButton)
		{
			//switch to addErrorJPanel
			cd.show(cards, "addErrorPanel");

		}// end frame.addItem
		
		
		if(e.getSource() == frame.viewItem)
		{
			
		}// end frame.viewItem
		
		if(e.getSource() == frame.refreshErrorsItem)
		{
			try {
				errorPanel.model.setRowCount(0);
				errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
			} 
			
		}// end frame.refreshErrorsItem
		
		if(e.getSource() == frame.homeItem)
		{
			//switch to errorJPanel
				try {
					errorPanel.model.setRowCount(0);
					errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Error in frame.homeItem actionHandler");
				} finally {
					cd.show(cards, "errorPanel");
				}


		}// end frame.homeItem
		
		if(e.getSource() == frame.loginItem)
		{
			//create LoginMenu
			createLoginMenu();
			 
			 
		}// end frame.loginItem
		
		if(e.getSource() == frame.changePwItem)
		{
			//create createChangePWMenu
			createChangePWMenu();
			 
			 
		}// end frame.changePwItem
		
		if(e.getSource() == frame.reportItem)
		{
			//create createChangePWMenu
			createReportManagerMenu();
			 
			 
		}// end frame.reportItem
		
		if(e.getSource() == frame.exitItem)
		{
		    System.exit(0);
		}// end frame.exitItem
		
		if(e.getSource() == frame.adminEditErrorItem || e.getSource() == frame.devEditErrorItem || e.getSource() == errorPanel.editButton)
		{
			
			createEditErrorForm(JOptionPane.showInputDialog("Enter ENF ID Number to edit:"));

			cd.show(cards, "editErrorPanel");
			
		}// end frame.adminEditItem || frame.devEditItem
		
		if(e.getSource() == errorPanel.printButton)
		{
			
			JOptionPane.showInputDialog("Enter ENF ID Number to print:");

			 JOptionPane.showMessageDialog(null,
					 "Functionality Disabled.",
					 "Functionality Currently Disabled.",
					 JOptionPane.ERROR_MESSAGE);
			
		}// end errorPanel.printButton
		
		
		/*
		 * 
		 * Developer & Admin Menu
		 * 
		 * 
		 */
		if (e.getSource() == frame.adminAddUserItem || e.getSource() == frame.devAddUserItem)
		{
			
			//switch to addUserJPanel
			cd.show(cards, "addUserPanel");
			
		}// end frame.addUserItem
		
		if (e.getSource() == frame.adminEditUserItem || e.getSource() == frame.devEditUserItem)
		{
			JOptionPane.showInputDialog("Enter User ID Number to edit:");
			cd.show(cards, "editUserPanel");
		}
		
		if (e.getSource() == frame.adminDeleteErrorItem || e.getSource() == frame.devDeleteErrorItem)
		{
			JOptionPane.showInputDialog("Enter ENF ID Number to delete:");

			// confirm delete
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this error?", "Delete Error", JOptionPane.YES_NO_OPTION);
			
			// if yes
			if (confirm == JOptionPane.YES_OPTION)
			{
			
				 JOptionPane.showMessageDialog(null,
						 "Error Deleted!",
						 "Deleted!",
						 JOptionPane.INFORMATION_MESSAGE);
				 
				 //delete
			}
			else {
				// cancel
			}
		}
		
		if(e.getSource() == frame.adminUserListItem || e.getSource() == frame.devUserListItem)
		{
			
			try {
				userListPanel.model.getUsers(userListPanel.model, userListPanel.outputTable);
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				System.out.println("Error in userlistItem actionhandler");
			}
			
			cd.show(cards, "userListPanel");
			
		}// end frame.UserListItem
		
		if(e.getSource() == frame.settingsItem)
		{
			cd.show(cards, "settingsPanel");
		}// end frame.settingsItem
		
		
		
		/*
		 * 
		 * Login Panel Actions
		 * 
		 */
		try{
			

			if(e.getSource() == login.loginButton)
			 {		
				connection = newSQLiteConnector.connect(settings.getDatabasePath());
				try {
					
					String query = "select User_Name,Password,Access_Level_ID from User where User_Name=? and Password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, login.userName.getText());
					pst.setString(2, login.password.getText());
					ResultSet rs = pst.executeQuery();

					int count = 0;
					int userLevel = 0;
					
					// cycle through results
					while(rs.next())
					{
						count = count + 1;
						userLevel = Integer.parseInt(rs.getString("Access_Level_ID"));
					}
				
					rs.close();
					pst.close();
					
					// if at least one is returned the password and username are correct
					if (count > 0)
					{

						if (userLevel == 1)
						{
							 frame.menuBar.add(frame.developerMenu);
						}
						else if (userLevel == 2)
						{
							 frame.menuBar.add(frame.adminMenu);
						}
					
						
						 frame.accountMenu.add(frame.changePwItem);
						 frame.accountMenu.add(frame.logoutItem);
						 
						 errorPanel.editButton.setEnabled(true);
						 frame.loginItem.setEnabled(false);
						 
						 username = login.userName.getText();
						 
						 frame.validate();
						 frame.repaint();
						 login.dispose();
						 
						 JOptionPane.showMessageDialog(null,
								 "Logged In!",
								 "Logged In!",
								 JOptionPane.INFORMATION_MESSAGE);
						 
					}
					else
					{
						 JOptionPane.showMessageDialog(null,
								 "Wrong User Name/Password!",
								 "Wrong Credentials",
								 JOptionPane.ERROR_MESSAGE);
					}
					
					
				} catch (SQLException sql) {
					 JOptionPane.showMessageDialog(null,
							 "Currently Unavailable",
							 "Connection Error",
							 JOptionPane.ERROR_MESSAGE);
				}
				 
			 }// end login.loginButton
		
		}
		
		catch(Exception NotInstantiated){
			
		}
		
		
		try{
			
			
			if(e.getSource() == frame.logoutItem)
			 {
				// confirm logout
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				
				// if yes
				if (confirm == JOptionPane.YES_OPTION)
				{
				
					 JOptionPane.showMessageDialog(null,
							 "Logged Out!",
							 "Logged Out!",
							 JOptionPane.INFORMATION_MESSAGE);
					 
					 frame.menuBar.remove(frame.adminMenu);
					 frame.menuBar.remove(frame.developerMenu);
					 frame.accountMenu.remove(frame.changePwItem);
					 frame.accountMenu.remove(frame.logoutItem);				 
					 errorPanel.editButton.setEnabled(false);
					 frame.loginItem.setEnabled(true);
					 
					 frame.validate();
					 frame.repaint();
					 login.dispose();
				}
				else {
					// stay logged in
				}
				 
			 }// end login.loginButton
		
		}
		
		catch(Exception NotInstantiated){
			
		}

		
		/*
		 * 
		 * userListPanel
		 */
		if(e.getSource() == userListPanel.addButton)
		{
			cd.show(cards, "addUserPanel");
			
		}// 
		
		
		try {
			
			if(e.getSource() == changePassword.saveButton)
			{
				connection = newSQLiteConnector.connect(settings.getDatabasePath());
				
				try 
				{
					String query = "select * from User where UserName=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, username);
					ResultSet rs = pst.executeQuery();
	
					int count = 0;
					String password = null;
					
					// cycle through results
					while(rs.next())
					{
						count = count + 1;
						password = rs.getString("Password");
						
					}
					
					if (count > 0)
					{
						// currentpassword is right
						if (changePassword.currentPassword.getText().equals(password))
						{
							//update to new password
							if(changePassword.confirmNewPassword.getText().equals(changePassword.newPassword.getText()))
							{
								//password can be changed
							}
							else
							{
								 JOptionPane.showMessageDialog(null,
										 "New password does not match, Please try again!",
										 "New Password Mismatch",
										 JOptionPane.ERROR_MESSAGE);
							}
						}
						
						// currentpassword is wrong
						else
						{
							 JOptionPane.showMessageDialog(null,
									 "Current Password is Incorrect, Please try again!",
									 "Wrong Password",
									 JOptionPane.ERROR_MESSAGE);
						}
					}
					
					else 
					{
						 JOptionPane.showMessageDialog(null,
								 "Something went wrong when looking for the current password!",
								 "Error",
								 JOptionPane.ERROR_MESSAGE);
					}
					
				} catch(Exception changePW)
				{
					 JOptionPane.showMessageDialog(null,
							 "Currently Unavailable",
							 "Connection Error",
							 JOptionPane.ERROR_MESSAGE);
				}
			}//
		} catch(Exception e2) {
			
		}// end try catch for changePW
		
		/*
		 * Settings Panel
		 */
		if(e.getSource() == settingsPanel.settingsSaveButton)
		{
			settings.savePaths(settingsPanel.txtDatabasePath.getText(), settingsPanel.txtXmlPath.getText());
			settingsPanel.txtDatabasePath.setText(settings.getDatabasePath());
			settingsPanel.txtXmlPath.setText(settings.getXmlPath());
		}
		
		if(e.getSource() == settingsPanel.chooseDatabaseFile)
		{
	        JFileChooser databaseFileChooser = new JFileChooser();
	        int returnValue = databaseFileChooser.showOpenDialog(null);
	        
	        if (returnValue == JFileChooser.APPROVE_OPTION) 
	        {
	          File selectedFile = databaseFileChooser.getSelectedFile();
	          settingsPanel.txtDatabasePath.setText(selectedFile.getAbsolutePath());
	        }
		}
		
		if(e.getSource() == settingsPanel.chooseXmlFile)
		{
	        JFileChooser databaseFileChooser = new JFileChooser();
	        int returnValue = databaseFileChooser.showOpenDialog(null);
	        
	        if (returnValue == JFileChooser.APPROVE_OPTION) 
	        {
	          File selectedFile = databaseFileChooser.getSelectedFile();
	          settingsPanel.txtXmlPath.setText(selectedFile.getAbsolutePath());
	        }
		}
		
	}// end ActionPerformed


}
