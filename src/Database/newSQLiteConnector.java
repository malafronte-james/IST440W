package Database;

import java.sql.*;

import javax.swing.JOptionPane;

public class newSQLiteConnector {

	static Connection connection = null;
	String sDbLocation = null;
	
	public static Connection connect(String dbLocation)
	{
		String sJdbc = "jdbc:sqlite";
		String sDbLocation = dbLocation;
		String url = sJdbc + ":" + sDbLocation;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(url);
			
			System.out.println("Connected using newSQLiteConnector");
			
			return connection;
		} catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	public void login(String username, String password)
	{
		connect(sDbLocation);
		
		try {
			
			String query = "select * from User where User_Name=? and Password=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(0, username);
			pst.setString(1, password);
			
			ResultSet rs = pst.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
