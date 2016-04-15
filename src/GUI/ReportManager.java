package GUI;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class ReportManager extends smallJFrame
{
	JComboBox cmbReportSelector;
	JTextField toDate, fromDate, sapUserName;
	JButton exportButton, runButton;
	
	ReportManager()
	{
		super("Report Manager",700,150);
		
		setLayout(new BorderLayout());
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
	
	}
	
	private void buildNorthPanel()
	{
		//date
		//to - from
		//report
		//SAP USERNAME
	}
	
	private void buildCenterPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new MigLayout());
		toDate = new JTextField(10);
		fromDate = new JTextField(10);
		
		exportButton = new JButton("Export");
		runButton = new JButton("Run");
		
		centerPanel.add(new JLabel("Date"), "span 1 2");
		centerPanel.add(new JLabel("To:"), "gapleft 10");
		centerPanel.add(toDate);
		
		centerPanel.add(new JLabel("Select Report:"), "gapleft 20" );
		cmbReportSelector = new JComboBox();
		centerPanel.add(cmbReportSelector, "wrap, pushx, growx");
		
		centerPanel.add(new JLabel("From:"), "gapleft 10");
		centerPanel.add(fromDate);
		
		centerPanel.add(new JLabel("SAP User Name:"), "gapleft 20");
		sapUserName = new JTextField(10);
		centerPanel.add(sapUserName, "pushx, growx, wrap");
		
		centerPanel.add(new JLabel(""), "span 3");
		centerPanel.add(exportButton, "gapleft 60");
		centerPanel.add(runButton);
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void buildSouthPanel()
	{

	}
}
