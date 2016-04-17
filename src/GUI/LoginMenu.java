package GUI;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class LoginMenu extends smallJFrame
{
	JTextField userName;
	JPasswordField password;
	JButton loginButton;
	
	LoginMenu()
	{
		super("Login", 400, 140);
		
		// set contentPane to BorderLayout
		setLayout(new BorderLayout());
		
		// methods to add content to the contentPane
		createCenterPanel();
	}
	
	/**
	 * Create centerPanel and it's contents
	 */
	private void createCenterPanel()
	{
		// instantiate centerPanel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new MigLayout());
		
		// add user name elements
		centerPanel.add(new JLabel("User name:"));
		centerPanel.add(userName = new JTextField(25), "wrap");
		
		// add password elements
		centerPanel.add(new JLabel("Password:"));
		centerPanel.add(password = new JPasswordField(25), "wrap");
		
		// add login button
		centerPanel.add(loginButton = new JButton("Login"), "skip");
		
		// add centerPanel to the contentPane
		add(centerPanel, BorderLayout.CENTER);
		
		return;
		
	}// end createCenterPanel
	
}
