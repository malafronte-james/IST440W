package GUI;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class ChangePasswordMenu extends smallJFrame
{
	JTextField currentPassword, newPassword, confirmNewPassword;
	JButton saveButton;
	
	ChangePasswordMenu()
	{
		super("Change Password", 400, 170);
		
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
		centerPanel.add(new JLabel("Current Password:"));
		centerPanel.add(currentPassword = new JTextField(25), "wrap");
		
		// add password elements
		centerPanel.add(new JLabel("New Password:"));
		centerPanel.add(newPassword = new JTextField(25), "wrap");
		
		// add password elements
		centerPanel.add(new JLabel("Confirm New Password:"));
		centerPanel.add(confirmNewPassword = new JTextField(25), "wrap");
		
		// add login button
		centerPanel.add(saveButton = new JButton("Save"), "skip");
		
		// add centerPanel to the contentPane
		add(centerPanel, BorderLayout.CENTER);
		
		return;
		
	}// end createCenterPanel
	
}
