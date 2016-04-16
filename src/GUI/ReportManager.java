package GUI;

import java.awt.*;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
    	UtilDateModel toModel = new UtilDateModel();
    	Properties p = new Properties();
    	p.put("text.today", "Today");
    	p.put("text.month", "Month");
    	p.put("text.year", "Year");
    	JDatePanelImpl toDatePanel = new JDatePanelImpl(toModel, p);
    	JDatePickerImpl toDatePicker = new JDatePickerImpl(toDatePanel, new DateComponentFormatter());
		centerPanel.add(toDatePicker);
		
		centerPanel.add(new JLabel("Select Report:"), "gapleft 20" );
		cmbReportSelector = new JComboBox();
		centerPanel.add(cmbReportSelector, "wrap, pushx, growx");
		
		centerPanel.add(new JLabel("From:"), "gapleft 10");
    	UtilDateModel fromModel = new UtilDateModel();
    	JDatePanelImpl fromDatePanel = new JDatePanelImpl(fromModel, p);
    	JDatePickerImpl fromDatePicker = new JDatePickerImpl(fromDatePanel, new DateComponentFormatter());
		centerPanel.add(fromDatePicker);
		
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
