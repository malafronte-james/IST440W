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
	
	
	public void sqlConsoleQuery(DefaultTableModel output, JTable outputTable, String query)
			throws SQLException, ClassNotFoundException, FileNotFoundException, IOException 
	{
		
		//open the database connection
		openConnection();
		
		try {
			
			rs = stat.executeQuery(query);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Please check your SQL syntax.",
					"Error with SQL.",
					JOptionPane.ERROR_MESSAGE);
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				String name = rsmd.getColumnName(i);
				output.addColumn(name);
			}
		
		// Iterate through the result and print the student names
		while (rs.next())
		{
			Object[] o = new Object[rsmd.getColumnCount()];
			//get all record information
			for (int i = 0; i < rsmd.getColumnCount(); i++)
			{
				o[i] = rs.getObject(i);
			}
			
			//out put it to the table
			output = (DefaultTableModel) outputTable.getModel();
			output.addRow(o);
			
		}// end while
			
		// Close the connection
		closeConnection();
		
	}// end getErrors
	

}
