import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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
			Patient selected = (Patient)patientList.getSelectedValue();
			if(selected != null)
			{
				EditPlanDialogue d = new EditPlanDialogue(selected,handler);
			}
		}
	};
	private ActionListener showBillListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Patient selected = (Patient)patientList.getSelectedValue();
			if(selected != null)
			{
				ShowBillDialogue d = new ShowBillDialogue(selected, handler);
			}
		}
	};
	private ActionListener payBillListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{	
			Patient selected = (Patient)patientList.getSelectedValue();
			if(selected != null)
			{
				int warn = JOptionPane.showOptionDialog(null, "Are you sure you want to mark this patient's bills as paid?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(warn == 0)
				{
					Appointment[] pulledAppointments;
					try{
						pulledAppointments = handler.getAppointmentsByPatientID(selected.getPatientID());
					}catch(SQLException ex) {
						pulledAppointments = new Appointment[0];
					}
					ArrayList<Appointment> allAppointments = new ArrayList<Appointment>(Arrays.asList(pulledAppointments));
					for (Appointment app:allAppointments)
					{
						if(app.isPaid())// Don't count appointments that are already paid for
						{
							allAppointments.remove(app);
						}
					}

					ArrayList<Treatment> treatments = new ArrayList<Treatment>();
					for (Appointment app:allAppointments)
						treatments.addAll(app.getTreatments());
					Treatment[] treatmentsArray = new Treatment[treatments.size()];
					treatmentsArray = treatments.toArray(treatmentsArray);
					HealthcarePlan selectedsPlan = selected.getHealthcarePlan();
					for (Treatment t:treatmentsArray)
					{
						if(t.getPaymentType().equals("hygiene") && selected.getHygienesHad() < selectedsPlan.getHygienes()) {
							selected.setHygienesHad(selected.getHygienesHad() + 1);
						}else if(t.getPaymentType().equals("repair") && selected.getRepairsHad() < selectedsPlan.getRepairs()) {
							selected.setRepairsHad(selected.getRepairsHad() + 1);
						}else if(t.getPaymentType().equals("checkup") && selected.getCheckUpsHad() < selectedsPlan.getCheckups()) {
							selected.setCheckUpsHad(selected.getCheckUpsHad() + 1);
						}
					}

					try{
						for (Appointment app:allAppointments)
						{
							app.setPaid(true);
							handler.setAppointmentToPaid(app);
						}
						handler.updatePatient(selected);
						JOptionPane.showMessageDialog(null, "Patient payment information updated.");
					}catch(SQLException ex) {
						JOptionPane.showMessageDialog(null, "Error updating payment information", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
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
