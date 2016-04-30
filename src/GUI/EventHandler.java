package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Database.*;
import Properties.*;
import Utilities.*;
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
		
		// addErrorPanel
		addErrorPanel.saveButton.addActionListener(this);
		addErrorPanel.backButton.addActionListener(this);
		
		// addUserPanel
		addUserPanel.addUserSaveButton.addActionListener(this);
		
		// editUserPanel
		editUserPanel.editUserBackButton.addActionListener(this);
		editUserPanel.editUserSaveButton.addActionListener(this);
		
		newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
		sql.setDBPath(settings.getDatabasePath());
		
		System.out.println(System.getProperty("user.dir"));
		
	}// end EventHandler

	/**
	 * Create Login Menu
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

	
	@Override
	public void actionPerformed(ActionEvent e) {

/*
 * ================================= Edit Error =====================================================================
 */			
			if(e.getSource() == editErrorPanel.saveButton)
			{
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				
				if(editErrorPanel.txtEnfID.getText().length() > 0 &&
				   editErrorPanel.txtSapUserName.getText().length() > 0 &&
				   editErrorPanel.cmbDepartment.toString().length() > 0 &&
				   editErrorPanel.cmbShift.toString().length() > 0 &&
				   editErrorPanel.txtOpenedDate.getText().length() > 0 &&
				   editErrorPanel.txtProcess.getText().length() > 0 && 
				   editErrorPanel.txtSkuNumber.getText().length() > 0 &&
				   editErrorPanel.txtLocationAffected.getText().length() > 0 &&
				   editErrorPanel.txtQty.getText().length() > 0 &&
				   editErrorPanel.txtDateOfError.getText().length() > 0 &&
				   editErrorPanel.cmbStatus.toString().length() > 0 &&
				   editErrorPanel.txtNotes.getText().length() > 0 &&
				   editErrorPanel.txtOpenedBy.getText().length() > 0)
				{
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
				else {
					 JOptionPane.showMessageDialog(frame,
							 "Missing Information!",
							 "Please fill in all information!",
							 JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
			if(e.getSource() == editErrorPanel.backButton)
			{
				refreshErrorTable();
				cd.show(cards,"errorPanel");
			}
			

/*
 * ================================= End Edit Error =================================================================
 */	
		
		
	
			
			

/*
 * ================================= Edit User = =====================================================================
 */				
			if(e.getSource() == editUserPanel.editUserSaveButton)
			{
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				
				if(editUserPanel.txtSapUserName.getText().length() > 0 &&
				   editUserPanel.txtAdminID.getText().length() > 0 &&
				   editUserPanel.txtJobTitle.getText().length() > 0 && 
				   editUserPanel.txtEmail.getText().length() > 0 &&
				   editUserPanel.cmbAccessLevel.toString().length() > 0 &&
				   editUserPanel.cmbDepartment.toString().length() > 0 &&
				   editUserPanel.txtPassword.getPassword().length > 0 &&
				   editUserPanel.txtConfirmPassword.getPassword().length > 0)
				{
									
							
					String pw = new String(editUserPanel.txtPassword.getPassword());
					String confirmPw = new String(editUserPanel.txtConfirmPassword.getPassword());
					
					if(pw.equals(confirmPw) && pw.length() > 0)
					{
					
						String level;
						
						if(editUserPanel.cmbAccessLevel.getSelectedItem().toString().equals("Administrator"))
						{
							level = "2";
						}
						else {
							level = "1";
						}
						
						sql.updateUser(editUserPanel.txtAdminID.getText(),
										editUserPanel.txtSapUserName.getText(),
										pw,
										editUserPanel.cmbDepartment.getSelectedItem().toString(),
										level,
										editUserPanel.txtJobTitle.getText(),
										editUserPanel.txtEmail.getText());
						
						 JOptionPane.showMessageDialog(frame,
								 "Saved!",
								 "Saved!",
								 JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						 JOptionPane.showMessageDialog(frame,
								 "Passwords do not match",
								 "Passwords do not match! Please try again.",
								 JOptionPane.INFORMATION_MESSAGE);
					}
					
					
				}
				else {
					 JOptionPane.showMessageDialog(frame,
							 "Missing Information!",
							 "Please fill in all information!",
							 JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
			
			if(e.getSource() == editUserPanel.editUserBackButton)
			{
				refreshUserTable();
				cd.show(cards,"errorPanel");
			}			
			
			

/*
 * ================================= End Edit Error ==================================================================
 */				
			
			
			
			
			
		
/*
 * ================================= Frame Menu =====================================================================
 */			
		try{
			
			
			if(e.getSource() == frame.logoutItem)
			 {
				// confirm logout
				int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
				
				// if yes
				if (confirm == JOptionPane.YES_OPTION)
				{
				
					 JOptionPane.showMessageDialog(frame,
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
					 
					 cd.show(cards, "errorPanel");
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
			
			// Departments
			addErrorPanel.cmbDepartment.removeAllItems();
			String[] deptArray = new String[sql.getDepartments().size()];
			sql.getDepartments().toArray(deptArray);
			
				for (String string : deptArray) 
				{
						addErrorPanel.cmbDepartment.addItem(string);
				}

		}// end frame.addItem
		
		
		if(e.getSource() == frame.viewItem)
		{
			try {
				
				String enfID = JOptionPane.showInputDialog("Enter ENF ID Number to view report:");
				TempData data = new TempData();
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				sql.getErrorDataForView(enfID, data);
				
				if (enfID.length() > 0)
				{
					//get the path of the program
					String directory = System.getProperty("user.dir");
					
					
					//set the path of the database to the current path of the program
					//String template = directory + File.separator + "rpt_Error_Notification_template.pdf";
					URL template = getClass().getResource("PDF/enf_template.pdf");
					String completeENF = directory + File.separator + "ENF.pdf";
					
					
					//final String template = "X:\\temp\\rpt_Error_Notification_template.pdf";
				    //final String completeENF = "X:\\temp\\ReportView.pdf";
				    
				    
				    
				    /*
					Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\temp\\Report.pdf"));
					doc.open();
					doc.add(new Paragraph("ENF",FontFactory.getFont(FontFactory.TIMES_BOLD, 18)));
					doc.add(new Paragraph(new Date().toString()));
					
					PdfPTable table = new PdfPTable(1);
					table.addCell("1");
	
					doc.add(table);
					
					doc.close();
					*/
					
				    PDFHandler enfPDF = new PDFHandler(0);
				    // fill in information
				    enfPDF.enfPdf(template.toString(), completeENF, data);
					
					// open file using default file
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ completeENF);
				}
			
			} catch (Exception e1) {
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

			 JOptionPane.showMessageDialog(frame,
					 "Functionality Disabled.",
					 "Functionality Currently Disabled.",
					 JOptionPane.ERROR_MESSAGE);
			
		}// end errorPanel.printButton
		

/*
 * ================================= End Error Panel =====================================================================
 */	
		

		
/*
 * ================================= Add Error Panel =====================================================================
 */			
		
		if(e.getSource() == addErrorPanel.saveButton)
		{
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			
			if(addErrorPanel.txtEnfID.getText().length() > 0 &&
			   addErrorPanel.txtSapUserName.getText().length() > 0 &&
			   addErrorPanel.cmbDepartment.toString().length() > 0 &&
			   addErrorPanel.cmbShift.toString().length() > 0 &&
			   addErrorPanel.model.toString().length() > 0 &&
			   addErrorPanel.txtProcess.getText().length() > 0 && 
			   addErrorPanel.txtSkuNumber.getText().length() > 0 &&
			   addErrorPanel.txtLocationAffected.getText().length() > 0 &&
			   addErrorPanel.txtQty.getText().length() > 0 &&
			   addErrorPanel.errorDateModel.toString().length() > 0 &&
			   addErrorPanel.cmbStatus.toString().length() > 0 &&
			   addErrorPanel.txtNotes.getText().length() > 0 &&
			   addErrorPanel.txtOpenedBy.getText().length() > 0)
			{
				sql.createError(addErrorPanel.txtEnfID.getText(),
						addErrorPanel.txtSapUserName.getText(),
						addErrorPanel.cmbDepartment.toString(),
						addErrorPanel.cmbShift.toString(),
						addErrorPanel.model.toString(),
						addErrorPanel.txtProcess.getText(),
						addErrorPanel.txtSkuNumber.getText(),
						addErrorPanel.txtLocationAffected.getText(),
						addErrorPanel.txtQty.getText(),
						addErrorPanel.errorDateModel.toString(),
						addErrorPanel.cmbStatus.toString(),
						(LocalDate.now().plusDays(7)).toString(),
						addErrorPanel.txtNotes.getText(),
						//attachment,
						addErrorPanel.txtOpenedBy.getText());
			}
			else {
				 JOptionPane.showMessageDialog(frame,
						 "Missing Information!",
						 "Please fill in all information!",
						 JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		if(e.getSource() == addErrorPanel.backButton)
		{
			refreshErrorTable();
			cd.show(cards,"errorPanel");
		}
		
/*
 * ================================= End Add Error Panel =====================================================================
 */	
		
		
		
		
		
/*
 * ================================= Add User Panel =====================================================================
 */	
		if(e.getSource() == addUserPanel.addUserSaveButton)
		{
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			
			if(addUserPanel.txtSapUserName.getText().length() > 0 &&
			   addUserPanel.txtAdminID.getText().length() > 0 &&
			   addUserPanel.txtJobTitle.getText().length() > 0 && 
			   addUserPanel.txtEmail.getText().length() > 0 &&
			   addUserPanel.cmbAccessLevel.toString().length() > 0 &&
			   addUserPanel.cmbDepartment.toString().length() > 0 &&
			   addUserPanel.txtPassword.getPassword().length > 0 &&
			   addUserPanel.txtConfirmPassword.getPassword().length > 0)
			{
				
				String level;
				
				if(addUserPanel.cmbAccessLevel.toString().equals("Administrator"))
				{
					level = "2";
				}
				else {
					level = "1";
				}
				
				String myPass=String.valueOf(addUserPanel.txtPassword);
				
				sql.createUser(addUserPanel.txtAdminID.getText(),
								addUserPanel.txtSapUserName.getText(),
								myPass,
								(String)addUserPanel.cmbDepartment.getSelectedItem(),
								level,
								addUserPanel.txtJobTitle.getText(),
								addUserPanel.txtEmail.getText());
			}
			else {
				 JOptionPane.showMessageDialog(frame,
						 "Missing Information!",
						 "Please fill in all information!",
						 JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
/*
 * ================================= End Add User Panel =====================================================================
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
			
			// Departments
			editErrorPanel.cmbDepartment.removeAllItems();
			String[] deptArray = new String[sql.getDepartments().size()];
			sql.getDepartments().toArray(deptArray);
			
				for (String string : deptArray) 
				{
						editErrorPanel.cmbDepartment.addItem(string);
				}
			
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
			newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
			sql.getUserData(userID, editUserPanel);
			cd.show(cards, "editErrorPanel");
			
			// Departments
			editErrorPanel.cmbDepartment.removeAllItems();
			String[] deptArray = new String[sql.getDepartments().size()];
			sql.getDepartments().toArray(deptArray);
			
				for (String string : deptArray) 
				{
						editUserPanel.cmbDepartment.addItem(string);
				}
			cd.show(cards, "editUserPanel");
		}
		
		if (e.getSource() == frame.adminDeleteErrorItem || e.getSource() == frame.devDeleteErrorItem)
		{
			String enfFID = JOptionPane.showInputDialog("Enter ENF ID Number to delete:");

			// confirm delete
			int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this error?", "Delete Error", JOptionPane.YES_NO_OPTION);
			
			// if yes
			if (confirm == JOptionPane.YES_OPTION)
			{
				newSQLiteConnector sql = new newSQLiteConnector(settings.getDatabasePath());
				sql.deleteError(enfFID);
				JOptionPane.showMessageDialog(frame,
						 "Error Deleted!",
						 "Deleted!",
						 JOptionPane.INFORMATION_MESSAGE);
				
				// refresh table
				try {
					errorPanel.model.setRowCount(0);
					errorPanel.model.getErrors(errorPanel.model, errorPanel.outputTable);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
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
			console.runButton.addActionListener(new ActionListener() 
			{
		         public void actionPerformed(ActionEvent e) 
		         {
			
					if (e.getSource() == console.runButton)
					{
					         	  
				         	  try {
									
						    	  //instantiate new table model
						  		console.model = new NewDefaultTableModel(console.databasePath); 
						            
						             //instantiate JTable with tableModel
						          	  console.outputTable = new JTable(console.model);
						          	  
						        	  //set up table with borders and headers
						        	  console.outputTable.setBorder(BorderFactory.createLineBorder(Color.black));
						        	  
						           	  //add the table to a scroll pane
						         	  console.scrolls = new JScrollPane(console.outputTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						         	  
						         	  //set scrollPane color
						         	  console.scrolls.getViewport().setBackground(Color.lightGray);
						           
						         	  //add scrollPane to JPanel
						         	  console.add("Center", console.scrolls);
				         		  
				         		  
				    			console.model.sqlConsoleQuery(console.model, console.outputTable, console.inputArea.getText());
				    		} catch (Exception e4) {
				    			// TODO Auto-generated catch block
				    			e4.printStackTrace();
				    		}
					}
			
		         }
		    });
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
						 
						 JOptionPane.showMessageDialog(login,
								 "Logged In!",
								 "Logged In!",
								 JOptionPane.INFORMATION_MESSAGE);
						 
					}
					else
					{
						 JOptionPane.showMessageDialog(login,
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
						String pw = new String(changePassword.currentPassword.getPassword());
						if (pw.equals(password))
						{
							//update to new password
							if(Arrays.equals(changePassword.confirmNewPassword.getPassword(), 
									changePassword.newPassword.getPassword()))
							{
								String newPw = new String(changePassword.newPassword.getPassword());
								System.out.println(newPw);					
								sql.changePassword(loggedIn, newPw);
							}
							else
							{
								 JOptionPane.showMessageDialog(frame,
										 "New password does not match, Please try again!",
										 "New Password Mismatch",
										 JOptionPane.ERROR_MESSAGE);
							}
						}
						
						// currentpassword is wrong
						else
						{
							 JOptionPane.showMessageDialog(frame,
									 "Current Password is Incorrect, Please try again!",
									 "Wrong Password",
									 JOptionPane.ERROR_MESSAGE);
						}
					}
					
					else 
					{
						 JOptionPane.showMessageDialog(frame,
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
	        int returnValue = databaseFileChooser.showOpenDialog(frame);
	        
	        if (returnValue == JFileChooser.APPROVE_OPTION) 
	        {
	          File selectedFile = databaseFileChooser.getSelectedFile();
	          settingsPanel.txtDatabasePath.setText(selectedFile.getAbsolutePath());
	        }
		}
		
		if(e.getSource() == settingsPanel.chooseXmlFile)
		{
	        JFileChooser databaseFileChooser = new JFileChooser();
	        int returnValue = databaseFileChooser.showOpenDialog(frame);
	        
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
			JOptionPane.showMessageDialog(frame, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
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
			JOptionPane.showMessageDialog(frame, "Error refreshing data.", "An error has occurred,", JOptionPane.WARNING_MESSAGE);
		} 
	}

}
