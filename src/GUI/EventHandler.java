package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Database.*;
import Properties.settingsHandler;
import sql.console.ConsoleFrame;

public class EventHandler implements ActionListener
{

	settingsHandler settings = new settingsHandler();	
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
	private String loggedIn;

	
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
		
		// editErrorPanel
		editErrorPanel.backButton.addActionListener(this);
		editErrorPanel.saveButton.addActionListener(this);
		
		newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
		sql.setDBPath(settings.getDatabasePath());
		
	}// end EventHandler

	/**
	 * 
	 */
	private void createLoginMenu()
	{
		login = new LoginMenu();
		login.loginButton.addActionListener(this);
		
		return;
	}
	
	/**
	 * 
	 */
	private void createChangePWMenu()
	{
		changePassword = new ChangePasswordMenu();
		changePassword.saveButton.addActionListener(this);
		
		return;
	}
	
	/**
	 * 
	 */
	private void createReportManagerMenu()
	{
		reportManagerMenu = new ReportManager();
		
		return;
	}
	
	/**
	 * 
	 * @param enfNumber
	 */
	private void createEditErrorForm(String enfNumber)
	{
		editErrorPanel.getInfo(enfNumber);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

/*
 * ================================= Edit Error =====================================================================
 */			
			if(e.getSource() == editErrorPanel.saveButton)
			{
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				sql.updateError(editErrorPanel.txtEnfID.getText(),
								editErrorPanel.txtSapUserName.getText(),
								editErrorPanel.cmbDepartment.toString(),
								editErrorPanel.cmbShift.toString(),
								editErrorPanel.txtOpenedDate.getText(),
								editErrorPanel.txtProcess.getText(),
								editErrorPanel.txtSkuNumber.getText(),
								editErrorPanel.txtLocationAffected.getText(),
								editErrorPanel.txtQty.getText(),
								editErrorPanel.txtDateOfError.getText(),
								editErrorPanel.cmbStatus.toString(),
								//editErrorPanel.txtDueDate.getText,
								editErrorPanel.txtNotes.getText(),
								//attachment,
								editErrorPanel.txtOpenedBy.getText());
			}
			
			if(e.getSource() == editErrorPanel.backButton)
			{
				
			}
			

/*
 * ================================= End Edit Error =================================================================
 */	
		
		
		
		
/*
 * ================================= Frame Menu =====================================================================
 */			
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

		if(e.getSource() == frame.addItem || e.getSource() == errorPanel.addButton)
		{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					
			//switch to addErrorJPanel
			cd.show(cards, "addErrorPanel");
			
			 //prefill some data
			//addErrorPanel.txtDateOfError.setText(df.format(new Date()));
			
			// get next ENFID
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			addErrorPanel.txtEnfID.setText(Integer.toString(sql.getNumberOfErrors()+1));

		}// end frame.addItem
		
		
		if(e.getSource() == frame.viewItem)
		{
			try {
				
				Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream("C:\\temp\\Report.pdf"));
				doc.open();
				doc.add(new Paragraph("ENF",FontFactory.getFont(FontFactory.TIMES_BOLD, 18)));
				doc.add(new Paragraph(new Date().toString()));
				
				PdfPTable table = new PdfPTable(1);
				table.addCell("1");

				doc.add(table);
				
				doc.close();
				
				// open file using default file
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\temp\\Report.pdf");
			
			
			} catch (FileNotFoundException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}// end frame.viewItem
		
		if(e.getSource() == frame.refreshErrorsItem)
		{
			refreshErrorTable();
			
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
		
/*
 * ================================= End Frame Menu Actions =====================================================================
 */	
		
		
		
/*
 * ================================= Error Panel ========================================================================
 */	
		
	
		if(e.getSource() == errorPanel.printButton)
		{
			
			JOptionPane.showInputDialog("Enter ENF ID Number to print:");

			 JOptionPane.showMessageDialog(null,
					 "Functionality Disabled.",
					 "Functionality Currently Disabled.",
					 JOptionPane.ERROR_MESSAGE);
			
		}// end errorPanel.printButton

/*
 * ================================= End Error Panel =====================================================================
 */	
		
		
		
		
		
/*
 * ================================= Developer & Admin Menu =====================================================================
 */	
		
		if(e.getSource() == frame.adminEditErrorItem || e.getSource() == frame.devEditErrorItem || e.getSource() == errorPanel.editButton)
		{
			String enfID = JOptionPane.showInputDialog("Enter ENF ID Number to edit:");
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			sql.getErrorData(enfID, editErrorPanel);
			cd.show(cards, "editErrorPanel");
			
		}// end frame.adminEditItem || frame.devEditItem
		
		if (e.getSource() == frame.adminAddUserItem || e.getSource() == frame.devAddUserItem)
		{
			
			//switch to addUserJPanel
			cd.show(cards, "addUserPanel");
			
			// get next UserID
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			addUserPanel.txtAdminID.setText(Integer.toString(sql.getNumberOfUsers()+1));
			
		}// end frame.addUserItem
		
		if (e.getSource() == frame.adminEditUserItem || e.getSource() == frame.devEditUserItem)
		{
			
			String userID = JOptionPane.showInputDialog("Enter User ID Number to edit:");

			cd.show(cards, "editUserPanel");
		}
		
		if (e.getSource() == frame.adminDeleteErrorItem || e.getSource() == frame.devDeleteErrorItem)
		{
			String enfFID = JOptionPane.showInputDialog("Enter ENF ID Number to delete:");

			// confirm delete
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this error?", "Delete Error", JOptionPane.YES_NO_OPTION);
			
			// if yes
			if (confirm == JOptionPane.YES_OPTION)
			{
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				sql.deleteError(enfFID);
				JOptionPane.showMessageDialog(null,
						 "Error Deleted!",
						 "Deleted!",
						 JOptionPane.INFORMATION_MESSAGE);
				
				// refresh table
				try {
					errorPanel.model.setRowCount(0);
					errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
				} 
				 
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
		
		if(e.getSource() == frame.devSQLItem)
		{
			ConsoleFrame console = new ConsoleFrame();
		}
/*
 * ================================= End Developer & Admin Menu =============================================================
 */	
		
		
		
		
		
/*
 * ================================= Login Panel =====================================================================
 */	
		try{
			

			if(e.getSource() == login.loginButton)
			 {		

				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				loggedIn = login.userName.getText();
				int count = 0;
				count = sql.login(login.userName.getText(), login.password.getText());
				System.out.println(count);
				int userLevel = 0;
				userLevel = sql.getUserLevel(loggedIn);
					
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
				 
			 }// end login.loginButton
		
		}
		
		catch(Exception NotInstantiated){
			
		}
/*
 * ================================= End Login Panel =====================================================================
 */			
		
		
		
		
		
/*
 * ================================= User List Panel ======================================================================
 */	
		if(e.getSource() == userListPanel.addButton)
		{
			cd.show(cards, "addUserPanel");
			
		}// 
/*
 * ================================= End User List Panel ==================================================================
 */	
		
		
/*
 * ================================= Change Password ======================================================================
 */			
		try {
			
			if(e.getSource() == changePassword.saveButton)
			{

				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
					String password = sql.getPassword(loggedIn);

					
					if (password.length() > 0)
					{
						// currentpassword is right
						if (changePassword.currentPassword.getText().equals(password))
						{
							//update to new password
							if(changePassword.confirmNewPassword.getText().equals(changePassword.newPassword.getText()))
							{
								System.out.println(changePassword.newPassword.getText());
								System.out.println(loggedIn);
								sql.changePassword(loggedIn, changePassword.newPassword.getText());
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
					
			}//
		} catch(Exception e2) {
			
		}// end try catch for changePW
/*
 * ================================= End Change Password ======================================================================
 */	

		

/*
 * ================================= Settings Panel ============================================================================
 */
		if(e.getSource() == settingsPanel.settingsSaveButton)
		{
			settings.savePaths(settingsPanel.txtDatabasePath.getText(), settingsPanel.txtPdfPath.getText());
			settingsPanel.txtDatabasePath.setText(settings.getDatabasePath());
			settingsPanel.txtPdfPath.setText(settings.getPdfPath());
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
	          settingsPanel.txtPdfPath.setText(selectedFile.getAbsolutePath());
	        }
		}
/*
 * ================================= End Settings Panel ============================================================================
 */
		
	}// end ActionPerformed
	
	/**
	 * refresh the error table data
	 */
	private void refreshErrorTable()
	{
		try {
			errorPanel.model.setRowCount(0);
			errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
		} 
	}

	/**
	 * Refresh the user table data
	 */
	private void refreshUserTable()
	{
		try {
			errorPanel.model.setRowCount(0);
			errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
		} 
	}

}
