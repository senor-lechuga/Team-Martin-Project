import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;

public class PatientSelectorDialogue extends JFrame {

	private JButton leaveBtn, editHealthPlanBtn, showBillBtn, payBillBtn;
	private JList patientList;
	private SqlHandler handler;

	public PatientSelectorDialogue (SqlHandler h)
	{
		super("Alter Patient Information");
		//Set up the panel	
		handler = h;

		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		leaveBtn = new JButton("Close");
		pane.add(leaveBtn, BorderLayout.SOUTH);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(1,2));
		Patient[] sqlPatientList;
		try{
			sqlPatientList = handler.getAllPatients();
		}catch(SQLException ex){
			sqlPatientList = new Patient[0];
		}
		patientList = new JList(sqlPatientList);
		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientList.setLayoutOrientation(JList.VERTICAL);
		selectionPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		selectionPanel.add(patientList);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3,1)); // Unsubscribe/subscribe, show outstanding, pay all.
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		editHealthPlanBtn = new JButton("Edit Plan");
		showBillBtn = new JButton("Show Outstanding Bill");
		payBillBtn = new JButton("Mark Bill Paid");
		buttonsPanel.add(editHealthPlanBtn);
		buttonsPanel.add(showBillBtn);
		buttonsPanel.add(payBillBtn);
		selectionPanel.add(buttonsPanel);

		// Add all elements to the panel
		pane.add(selectionPanel, BorderLayout.CENTER);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		leaveBtn.addActionListener(cancelListener);
		editHealthPlanBtn.addActionListener(editPlanListener);
		showBillBtn.addActionListener(showBillListener);
		payBillBtn.addActionListener(payBillListener);
		this.setVisible( true );
		setResizable(false);
	}
	
	private ActionListener cancelListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Close the window
			closeWindow();
		}
	};
	private ActionListener editPlanListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	};
	private ActionListener showBillListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	};
	private ActionListener payBillListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	};

	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args) throws SQLException
	{
		PatientSelectorDialogue d = new PatientSelectorDialogue(null);
	}
}
