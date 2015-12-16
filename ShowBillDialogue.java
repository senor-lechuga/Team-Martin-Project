import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowBillDialogue extends JFrame {

	private JButton leaveBtn, payBtn;
	private JList billList;
	private JLabel totalCostInfo, modifiedCostInfo;
	private Patient patient;
	private SqlHandler handler;
	private double totalCost;

	public ShowBillDialogue (Patient p, SqlHandler h)
	{
		super("Bill Summation");
		handler = h;
		patient = p;

		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		leaveBtn = new JButton("Close");
		buttonPanel.add(leaveBtn);
		pane.add(buttonPanel, BorderLayout.SOUTH);
		//payBtn = new JButton("Mark as paid");

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(new JLabel(p.getFullName()+":"));
		billList = new JList(new String[]{""});
		totalCostInfo = new JLabel("");
		modifiedCostInfo = new JLabel("");
		if (!populateBillList())
			infoPanel.add(new JLabel("No treatments"));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		infoPanel.add(billList);
		infoPanel.add(totalCostInfo);

		pane.add(infoPanel, BorderLayout.CENTER);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		leaveBtn.addActionListener(cancelListener);

		this.setVisible(true);
		setResizable(false);
	}

	private boolean populateBillList ()
	{	
		Appointment[] allAppointments;
		try{
			allAppointments = handler.getAppointmentsByPatientID(patient.getPatientID());
		}catch(SQLException ex) {
			allAppointments = new Appointment[0];
		}
		ArrayList<Treatment> treatments = new ArrayList<Treatment>();
		for (Appointment app:allAppointments)
			if(!app.isPaid())// Only add appointments that have not been paid yet to the bill
				treatments.addAll(app.getTreatments());
		Treatment[] treatmentsArray = new Treatment[treatments.size()];
		treatmentsArray = treatments.toArray(treatmentsArray);
		billList.setListData(treatmentsArray);
		totalCost = 0;
		double modifiedCost = 0;
		HealthPlan patientsPlan = patient.getHealthcarePlan();
		for (Treatment t:treatmentsArray)
		{
			totalCost += t.getCost();
			if(t.getPaymentType().equals("hygiene") && patient.getHygienesHad() < patientsPlan.getHygienes()) {
				patient.setHygienesHad(patient.getHygienesHad() + 1);
			}else if(t.getPaymentType().equals("repair") && patient.getRepairsHad() < patientsPlan.getRepairs()) {
				patient.setRepairsHad(patient.getRepairsHad() + 1);
			}else if(t.getPaymentType().equals("checkup") && patient.getCheckUpsHad() < patientsPlan.getCheckups()) {
				patient.setCheckUpsHad(patient.getCheckUpsHad() + 1);
			}else{
				modifiedCost += t.getCost();
			}
		}
		totalCostInfo.setText("Total: " + totalCost + " pounds");
		modifiedCostInfo.setText("Modified: " + modifiedCost + " pounds");
		if(treatmentsArray.length == 0)
			return false;
		else
			return true;
	}

	private ActionListener cancelListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Close the window
			closeWindow();
		}
	};
	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}
}
