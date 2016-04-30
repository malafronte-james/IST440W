package Database;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI.*;
import Utilities.TempData;

public class newSQLiteConnector {

	private Connection connection = null;
	private String sDbLocation;
	
	public newSQLiteConnector(String dbLocation)
	{
		sDbLocation = dbLocation;
					
	}
	
	/**
	 * 
	 * @return 
	 * @throws SQLException
	 */
    public void connect() throws SQLException {
    	
		String sJdbc = "jdbc:sqlite";
		String url = sJdbc + ":" + sDbLocation;
		
        if (connection != null // if the connection exists
             && !connection.isClosed() // and has not been closed 
             && connection.isValid(0)) { // and appears to be functioning (with a test timeout of 0ms)
             return; // skip connection creation
        }

        // create the connection
        connection = DriverManager.getConnection(url);    
		System.out.println("Connected using Connector.");
		
		return;
		
    }
	
	/**
	 * Update user's password
	 * @param username
	 * @param password
	 */
	public void changePassword(String username, String password)
	{
		
		try {
			
			connect();
			
			String query = "UPDATE User SET Password =? WHERE User_Name=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, password);
			pst.setString(2, username);
			
			int rs = pst.executeUpdate();
			
			 JOptionPane.showMessageDialog(null,
					 "Password Changed Successfully!",
					 "Password Changed",
					 JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
		

	}
	
	/**
	 * Get password for user to check if login is correct
	 * @param username
	 * @param password
	 */
	public int login(String username, String password)
	{
		int count = 0;
		
		try {
			
			connect();
			
			String query = "select User_Name,Password from User where User_Name=? and Password=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();

			// cycle through results
			while(rs.next())
			{
				System.out.println("Connected using Connector.");
				count = count + 1;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			 JOptionPane.showMessageDialog(null,
					 "Login Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
			 
				return count;
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}

		return count;
	}
	
	/**
	 * 
	 * @param username
	 * @return userlevel
	 */
	public int getUserLevel(String username)
	{
		int userLevel = 0;
		
		try {
			
			connect();
			
			String query = "select User_Name,Access_Level_ID from User where User_Name=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				//count = count + 1;
				userLevel = Integer.parseInt(rs.getString("Access_Level_ID"));
			}
			
		} catch (Exception e){
			 JOptionPane.showMessageDialog(null,
					 "Login Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
				return userLevel;
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
		
		return userLevel;

	}
	
	/**
	 * 
	 * @return
	 */
	public String getPassword(String username)
	{

		String password = null;
		try 
		{
			
			connect();
			
			String query = "SELECT * FROM User WHERE User_Name=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				//count = count + 1;
				password = rs.getString("Password");
				
			}
			
		      
		} catch (Exception changePW) {
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
			 
			 changePW.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
			
		return password;
		
	}
	
	/**
	 * 
	 */
	public ArrayList<String> getUsers()
	{
		ArrayList<String> list = new ArrayList();
		
		try {
			
			connect();
			
			String query = "SELECT User_Name FROM User;";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				list.add(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
		
		return(list);
		
	}
	
	/**
	 * 
	 */
	public ArrayList<String> getDepartments()
	{
		ArrayList<String> list = new ArrayList();
		
		try {
			
			connect();
			
			String query = "SELECT Department FROM Department;";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				list.add(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
		
		return(list);
	}
	
	/**
	 * Insert into the error table a new error
	 */
	public void createError(//String Associate_First_Name,
							//String Associate_Last_Name,
							String ENF_ID,
							String SAP_Username,
							String department,
							String shift,
							String opened_date,
							String location_Being_Audited,
							String article_Number,
							String location_Affected,
							String quantity,
							String date_Of_Error,
							String status,
							String due_date,
							String notes,
							//String attachment,
							String openedBy)
	{
		
		try {
			
			int enfID = Integer.parseInt(ENF_ID);
			int qty = Integer.parseInt(quantity);
			
			
			connect();
			
			String query = "INSERT INTO Errors (ENF_ID,"
					+ "Associate_First_Name,"
					+ "Associate_Last_Name,"
					+ "SAP_Username,"
					+ "Department,"
					+ "Shift,"
					+ "Opened_Date,"
					+ "Location_Being_Audited,"
					+ "Article_Number,"
					+ "Location_Affected,"
					+ "Quantity,"
					+ "Date_of_Error,"
					+ "Status,"
					+ "Due_Date,"
					+ "Notes,"
					+ "Attachment,"
					+ "Opened_By)"
					+ "VALUES ("
					+ "?," //ENF_ID
					+ "?," //Associate_First_Name
					+ "?," //Associate_Last_Name
					+ "?," //SAP_Username
					+ "?," //Department
					+ "?," //Shift
					+ "?," //Opened_Date
					+ "?," //Location_Being_Audited
					+ "?," //Article_Number
					+ "?," //Location_Affected
					+ "?," //Quantity
					+ "?," //Date_of_Error
					+ "?," //Status
					+ "?," //Due_Date
					+ "?," //Notes
					+ "?," //Attachment
					+ "?);"; //Opened_By
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, enfID);
			pst.setString(2, "null");
			pst.setString(3, "null");
			pst.setString(4, SAP_Username);
			pst.setString(5, department);
			pst.setString(6, shift);
			pst.setString(7, opened_date);
			pst.setString(8, location_Being_Audited);
			pst.setString(9, article_Number);
			pst.setString(10, location_Affected);
			pst.setInt(11, qty);
			pst.setString(12, date_Of_Error);
			pst.setString(13, status);
			pst.setString(14, due_date);
			pst.setString(15, notes);
			pst.setString(16, "null");
			pst.setString(17, openedBy);
			
			pst.executeUpdate();

			
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * Delete a record from Errors using the ENF ID
	 */
	public void deleteError(String enfID)
	{
		
		try {
			
			connect();
			
			String query = "DELETE FROM Errors WHERE ENF_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, enfID);
			
			int rs = pst.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * Update an Error
	 */
	public void updateError(String ENF_ID,
			//String Associate_First_Name,
			//String Associate_Last_Name,
			String SAP_Username,
			String department,
			String shift,
			String opened_date,
			String location_Being_Audited,
			String article_Number,
			String location_Affected,
			String quantity,
			String date_Of_Error,
			String status,
			//String due_date,
			String notes,
			//String attachment,
			String openedBy)
	{
		
		try {
			int enfID = Integer.parseInt(ENF_ID);
			int qty = Integer.parseInt(quantity);
			
			connect();
			
			String query = "UPDATE Errors SET "
					//+ "Associate_First_Name = '?'," //Associate_First_Name
					//+ "Associate_Last_Name = '?'," // Associate_Last_Name
					+ "SAP_Username = '?'," // SAP_Username
					+ "Department = '?'," // department
					+ "Shift = '?'," // shift
					+ "Opened_Date = '?'," // opened_date
					+ "Location_Being_Audited = '?'," // location_Being_Audited
					+ "Article_Number = '?'," // article_Number
					+ "Location_Affected = '?'," // location_Affected
					+ "Quantity = ?," // quantity
					+ "Date_of_Error = '?'," // date_Of_Error
					+ "Status = '?'," // status
					//+ "Due_Date = '?'," // due_date
					+ "Notes = '?'," // notes
					//+ "Attachment = '?'," // attachment
					+ "Opened_By) = '?'," // openedBy
				    + "WHERE ENF_ID = ?;"; // ENF_ID
			PreparedStatement pst = connection.prepareStatement(query);
			//pst.setString(1, Associate_First_Name);
			//pst.setString(2, Associate_Last_Name);
			pst.setString(1, SAP_Username);
			pst.setString(2, department);
			pst.setString(3, shift);
			pst.setString(4, opened_date);
			pst.setString(5, location_Being_Audited);
			pst.setString(6, article_Number);
			pst.setString(7, location_Affected);
			pst.setInt(8, qty);
			pst.setString(9, date_Of_Error);
			pst.setString(10, status);
			//pst.setString(11, due_date);
			pst.setString(11, notes);
			//pst.setString(13, attachment);
			pst.setString(12, openedBy);
			pst.setInt(13, enfID);
			
			
			int rs = pst.executeUpdate();
			
			
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * Create a new user
	 */
	public void createUser(String userID,
			   			   String username,
			   			   String password,
			   			   String department,
			   			   String accessLevel,
			   			   String jobTitle,
			   			   String email)
	{
		

			try {
				int intUserID = Integer.parseInt(userID);
				int intAccessLevel = Integer.parseInt(accessLevel);
				
				System.out.println(intAccessLevel);
				connect();
				
				String query = "INSERT INTO User ("
						+ "User_ID,"
						+ "User_Name,"
						+ "Password,"
						+ "Department,"
						+ "Access_Level_ID,"
						+ "Job_Title,"
						+ "Email "
						+ ") VALUES "
						+ "(?,?,?,?,?,?,?);";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setInt(1, intUserID);
				pst.setString(2, username);
				pst.setString(3, password);
				pst.setString(4, department);
				pst.setInt(5, intAccessLevel);
				pst.setString(6, jobTitle);
				pst.setString(7, email);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * Delete a user
	 */
	public void deleteUser(String userID)
	{
		
		try {
			
			connect();
			
			String query = "DELETE FROM User WHERE User_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, userID);
			
			int rs = pst.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * 
	 */
	public void updateUser(String userID,
						   String username,
						   String password,
						   String department,
						   String accessLevel,
						   String jobTitle,
						   String email)
	{
		int intUserID = Integer.parseInt(userID);
		int intAccessLevel = Integer.parseInt(accessLevel);
		
		try {
			
			connect();
			
			String query = "UPDATE User SET "
					+ "User_Name=?,"
					+ "Password=?,"
					+ "Department=?,"
					+ "Access_Level_ID=?,"
					+ "Job_Title=?,"
					+ "Email=?"
					+ "WHERE User_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, department);
			pst.setInt(4, intAccessLevel);
			pst.setString(5, jobTitle);
			pst.setString(6, email);
			pst.setInt(7, intUserID);
			
			int rs = pst.executeUpdate();

			
		} catch (SQLException e) {

			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
			 
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}

	/**
	 * 
	 * @param databasePath
	 */
	public void setDBPath(String databasePath) {
		
		sDbLocation = databasePath;
		
	}
	
	public int getNumberOfErrors()
	{
		int count = 0;
		try 
		{
			
			connect();
			
			String query = "SELECT * FROM Errors;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				count = count + 1;
				
			}
			
		      
		} catch (Exception changePW) {
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
			 
			 changePW.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
			
		return count;
	}
	
	public int getNumberOfUsers()
	{
		int count = 0;
		try 
		{
			
			connect();
			
			String query = "SELECT * FROM User;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				count = count + 1;
				
			}
			
		      
		} catch (Exception changePW) {
			 JOptionPane.showMessageDialog(null,
					 "Currently Unavailable",
					 "Connection Error",
					 JOptionPane.ERROR_MESSAGE);
			 
			 changePW.printStackTrace();
		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
			
		return count;
	}
	
	/**
	 * 
	 * @param enfID
	 * @param panel
	 */
	public void getErrorData(String enfID, editErrorJPanel panel)
	{
		editErrorJPanel p = panel;
		try {
			
			connect();
			
			String query = "select * from Errors where ENF_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, enfID);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				 p.setTxtEnfID(rs.getString("ENF_ID"));
				 //p.settxrs.getString("Associate_First_Name");
				 //last_Name = rs.getString("Associate_Last_Name");
				 p.setTxtSapUserName(rs.getString("SAP_Username"));
				 //rs.getString("Department");
				 //shift = rs.getString("Shift");
				 //openedDate = rs.getString("Opened_Date");
				 //Location_Being_Audited = rs.getString("Location_Being_Audited");
				 p.setTxtSkuNumber(rs.getString("Article_Number"));
				 p.setTxtLocationAffected(rs.getString("Location_Affected"));
				 p.setTxtQty(rs.getString("Quantity"));
				 p.setTxtDateOfError(rs.getString("Date_of_Error"));
				 //rs.getString("Status");
				 //rs.getString("Due_Date");
				 p.txtNotes.setText(rs.getString("Notes"));
				 //attachment = rs.getString("Attachment");
				 p.txtOpenedBy.setText(rs.getString("Opened_By"));
				 
				 
				 
			}
			
		} catch (Exception e){

		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	public void getErrorDataForView(String enfID, TempData data)
	{
		try {
			
			connect();
			
			String query = "select * from Errors where ENF_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, enfID);
			ResultSet rs = pst.executeQuery();
			
			// cycle through results
			while(rs.next())
			{
				 data.setEnfID(rs.getString("ENF_ID"));
				 data.setSapUserName(rs.getString("SAP_Username"));
				 //rs.getString("Department");
				 //shift = rs.getString("Shift");
				 data.setDate_Created(rs.getString("Opened_Date"));
				 data.setProcess(rs.getString("Location_Being_Audited"));
				 data.setArticle(rs.getString("Article_Number"));
				 data.setLocation(rs.getString("Location_Affected"));
				 data.setQuantity(rs.getString("Quantity"));
				 data.setDate_of_error(rs.getString("Date_of_Error"));
				 data.setNotes(rs.getString("Notes"));
				 data.setDocumented_by(rs.getString("Opened_By"));
				 
				 
				 
			}
			
		} catch (Exception e){

		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
	
	/**
	 * 
	 * @param userID
	 * @param panel
	 */
	public void getUserData(String userID, editUserJPanel panel)
	{
		editUserJPanel p = panel;
		try {
			
			connect();
			
			String query = "select * from User where User_ID=?;";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, userID);
			ResultSet rs = pst.executeQuery();
			int accessLevel;
			String department;
			
			// cycle through results
			while(rs.next())
			{
				 p.txtSapUserName.setText(rs.getString("User_Name"));
				 p.txtAdminID.setText(rs.getString("User_ID"));
				 p.txtJobTitle.setText(rs.getString("Job_Title"));
				 p.txtEmail.setText(rs.getString("Email"));
				 accessLevel= rs.getInt("Access_Level_ID");
				 department = rs.getString("Department");
				 p.txtPassword.setText(rs.getString("Password"));
				 p.txtConfirmPassword.setText(rs.getString("Password")); 
				 
				 if(accessLevel == 2)
				 {
					 p.cmbAccessLevel.setSelectedIndex(0);
				 }
				 else {
					 p.cmbAccessLevel.setSelectedIndex(1);
				 }

				 
				 switch (department) {
					 case "Dot Com":
						 p.cmbDepartment.setSelectedIndex(0);
						 break;
					 case "QA":
						 p.cmbDepartment.setSelectedIndex(1);
						 break;
					 case "Receiving":
						 p.cmbDepartment.setSelectedIndex(2);
						 break;
					 case "Replenishment":
						 p.cmbDepartment.setSelectedIndex(3);
						 break;
					 case "Retail Picking":
						 p.cmbDepartment.setSelectedIndex(4);
						 break;
					 case "Distro":
						 p.cmbDepartment.setSelectedIndex(5);
						 break;
					 case "Shipping":
						 p.cmbDepartment.setSelectedIndex(6);
						 break;
				 }
				 
			}
			
		} catch (Exception e){

		}finally {
            try {
            	connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.toString());
            }
		}
	}
}
