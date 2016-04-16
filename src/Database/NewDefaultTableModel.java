package Database;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GUI.settingsJPanel;

public class NewDefaultTableModel extends DefaultTableModel
{
	private String enf_ID, opened_Date, opened_by, date_of_error, status, due_date, url, username, password, tableName;
	private String user_ID, user_Name, department, access_Level_ID, job_title, email;
	private boolean successFlag = false;
	ResultSet rs;
	static Connection connection = null;
	Statement stat;
	String sDbLocation = null;
	String dbLocation;
	
	public NewDefaultTableModel(String location)
	{
		dbLocation = location;
	}
	

	/**
	 * Opens the database connection
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public Connection openConnection()
	{
		//chose to do try catches because it makes the method easier to call in other classes
		
		String sJdbc = "jdbc:sqlite";
		String url = sJdbc + ":" + dbLocation;
		
		// debugging
		//System.out.println(url);
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			
			stat = connection.createStatement();
			
			System.out.println("Connected using NewDefaultTableModel.");
			
			return connection;
			

		} catch(Exception e) 
		{
			//JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}
	
	/**
	 * Closes the database connection
	 * @throws SQLException 
	 */
	private void closeConnection()
	{
		// Close the connection
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Get the record Data
	 * @param query
	 * @param output
	 * @param outputTable
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void getErrors(DefaultTableModel output, JTable outputTable)
			throws SQLException, ClassNotFoundException, FileNotFoundException, IOException 
	{
		
		//open the database connection
		openConnection();
		
		try {
			
			//String query = "SELECT ENF_ID,Opened_Date,Opened_By,Date_of_Error,Status,Due_Date FROM Errors";
			//PreparedStatement pst = connection.prepareStatement(query);
			//pst.setString(0, enf_ID);
			//pst.setString(1, password);
			
			//rs = pst.executeQuery();
			rs = stat.executeQuery("SELECT ENF_ID,Opened_Date,Opened_By,Date_of_Error,Status,Due_Date FROM Errors");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error connecting to errorData, check path settings.",
					"Error Connection to database.",
					JOptionPane.ERROR_MESSAGE);
		}

		// Iterate through the result and print the student names
		while (rs.next())
		{
			//get all record information
			enf_ID = rs.getString("ENF_ID");
			opened_Date = rs.getString("Opened_Date");
			opened_by = rs.getString("Opened_By");
			date_of_error = rs.getString("Date_of_Error");
			status = rs.getString("Status");
			due_date = rs.getString("Due_Date");
		   
			//out put it to the table
			output = (DefaultTableModel) outputTable.getModel();
			output.addRow(new Object[]{enf_ID, opened_Date, opened_by, date_of_error, status, due_date});
		   
		}// end while
			
		// Close the connection
		closeConnection();
		
	}// end getErrors
	
	public void getUsers(DefaultTableModel output, JTable outputTable)
			throws SQLException, ClassNotFoundException, FileNotFoundException, IOException 
	{
		
		//open the database connection
		openConnection();
		
		try {
			output.setRowCount(0);
			rs = stat.executeQuery("SELECT * FROM User");
			

		// Iterate through the result and print the student names
		while (rs.next())
		{
			//get all record information
			user_ID = rs.getString("User_ID");
			user_Name = rs.getString("User_Name");
			department = rs.getString("Department");
			access_Level_ID = rs.getString("Access_Level_ID");
			job_title = rs.getString("Job_Title");
			email = rs.getString("Email");
		   
			//out put it to the table
			output = (DefaultTableModel) outputTable.getModel();
			output.addRow(new Object[]{user_ID, user_Name, department, access_Level_ID, job_title, email});
		   
		}// end while
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error connecting to userData, check path settings.",
					"Error Connection to database.",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			// Close the connection
			closeConnection();
		}
		
	}// end getUsers
	
	
	
			
	/**
	 * Updates the database with all table changes
	 * @param output
	 * @param outputTable
	 */
	public void updateDatabase(JTable outputTable)
	{
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to confirm these update(s)?", "Confirmation", JOptionPane.WARNING_MESSAGE);
		
		if (confirm == JOptionPane.YES_OPTION)
		{
				
			//open the database connection
			openConnection();
			
			for (int i = 0; i < outputTable.getRowCount(); i++)
			{
			
				try {
					
					//declare and instantiate a statement
					Statement statement = connection.createStatement();
					
					//create update statement 
					String updateSQL = "UPDATE " + tableName + " SET " + 
							"name='" + outputTable.getModel().getValueAt(i,  1) + "', " + /*partName - varchar*/
							"description='" + outputTable.getModel().getValueAt(i,  2) + "', " + /*partDescription - varchar*/
							"price=" + outputTable.getModel().getValueAt(i,  3) +  /*partPrice - int*/
							" WHERE partNumber=" + outputTable.getModel().getValueAt(i,  0); /*partNumber - int*/
							
					//run update
					statement.execute(updateSQL);
					successFlag = true;
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error in SQL Statement.\n\nCheck edited cells for illegal values.", "Error", JOptionPane.ERROR_MESSAGE);
					successFlag = false;
				}// end try-catch
				
				finally {
					// Close the connection
					closeConnection();
				}
				
			}// end for
			
			if (successFlag == true)
			{
				JOptionPane.showMessageDialog(null, "Row(s) Updated Successfully!", "Update Success", JFrame.EXIT_ON_CLOSE);
				successFlag = false;
			}// end if
			
		}
		
		if (confirm == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
		
		if (confirm == JOptionPane.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
		
				
	}// end updateDatabase
	
	/**
	 * 
	 * @param enf
	 */
	public void editRecord(String enf, settingsJPanel settingsPanel)
	{
		//open the database connection
		openConnection();
		
		try {
			
			String query = "SELECT ENF_ID,Opened_Date,Opened_By,Date_of_Error,Status,Due_Date FROM Errors where ENF_ID=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(0, enf);
			rs = pst.executeQuery();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error connecting to errorData, check path settings.",
					"Error Connection to database.",
					JOptionPane.ERROR_MESSAGE);
		}

		// Iterate through the result and print the student names
		try {
			while (rs.next())
			{
				//get all record information
				enf_ID = rs.getString("ENF_ID");
				opened_Date = rs.getString("Opened_Date");
				opened_by = rs.getString("Opened_By");
				date_of_error = rs.getString("Date_of_Error");
				status = rs.getString("Status");
				due_date = rs.getString("Due_Date");
			   
				//out put it to the table
				//output = (DefaultTableModel) outputTable.getModel();
				//.addRow(new Object[]{enf_ID, opened_Date, opened_by, date_of_error, status, due_date});
			   
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end while
			
		// Close the connection
		closeConnection();
	}
	
	
	/**
	 * Deletes a record from the database
	 * @param outputTable
	 */
	public void deleteDatabase(JTable outputTable)
	{
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to confirm this delete?", "Confirmation", JOptionPane.WARNING_MESSAGE);
		
		if (confirm == JOptionPane.YES_OPTION)
		{
		
			//open the database connection
			openConnection();
			
			for (int i = 0; i < outputTable.getRowCount(); i++)
			{
				
				try {
					
					//declare and instantiate a statement
					Statement statement = connection.createStatement();
					
					//create update statement 
					String deleteSQL = "DELETE " + tableName + " SET " + 
							"name='" + outputTable.getModel().getValueAt(i,  1) + "', " + /*partName - varchar*/
							"description='" + outputTable.getModel().getValueAt(i,  2) + "', " + /*partDescription - varchar*/
							"price=" + outputTable.getModel().getValueAt(i,  3) +  /*partPrice - int*/
							" WHERE partNumber=" + outputTable.getModel().getValueAt(i,  0); /*partNumber - int*/
							
					//run update
					statement.execute(deleteSQL);
					successFlag = true;
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error in SQL Statement.\n\nCheck edited cells for illegal values.", "Error", JOptionPane.ERROR_MESSAGE);
					successFlag = false;
				}// end try-catch
				
				finally {
					// Close the connection
					closeConnection();
				}
				
			}// end for
			
			if (successFlag == true)
			{
				JOptionPane.showMessageDialog(null, "Row(s) Deleted Successfully!", "Delete Success", JFrame.EXIT_ON_CLOSE);
				successFlag = false;
			}// end if
			
		}		
		
		if (confirm == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
		
		if (confirm == JOptionPane.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
	}// end updateDatabase
	
	
	
	
	/**
	 * adds a record to the database
	 * @param sku
	 * @param artistName
	 * @param albumTitle
	 * @param albumYear
	 * @param genre
	 * @param stockQuanity
	 */
	public void addDatabase(String sku, String artistName, String albumTitle, String albumYear, String genre, String stockQuanity)
	{
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to confirm this add?", "Confirmation", JOptionPane.WARNING_MESSAGE);
		
		if (confirm == JOptionPane.YES_OPTION)
		{
			
			//open the database connection
			openConnection();
			
			try {
				
				Statement statement = connection.createStatement();
				
				String sql = "INSERT INTO "+ tableName +
		                   " VALUES (" + 
		                   Integer.parseInt(sku) +
		                   ",'" + artistName + /* artistName  - varChar*/
		                   "','" + albumTitle + /* albumTitle - varChar */
		                   "'," + Integer.parseInt(albumYear) + /* albumYear - int*/
		                   ",'" + genre + /* genre  - varChar*/
		                   "'," + Integer.parseInt(stockQuanity) +")"; /*stockQuantity - int*/
				
				//run query
				statement.executeUpdate(sql);
				successFlag = true;
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error in SQL Statement..", "Error", JOptionPane.ERROR_MESSAGE);
				successFlag = false;
			}
			finally {
				// Close the connection
				closeConnection();
			}
			
			if (successFlag == true)
			{
				JOptionPane.showMessageDialog(null, "Row(s) Added Successfully!", "Add Success", JFrame.EXIT_ON_CLOSE);
				successFlag = false;
			}// end if
		
		
		}		
		
		if (confirm == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
		
		if (confirm == JOptionPane.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Action Cancelled!");
		}
	}
}
