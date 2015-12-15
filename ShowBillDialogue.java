import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowBillDialogue extends JFrame {

	private JButton leaveBtn, payBtn;
	private JList billList;
	private Patient patient;
	private SqlHandler handler;

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

		Appointment[] allAppointments;
		try{
			allAppointments = handler.getAppointmentsByPatientID(patient.getPatientID());
		}catch(SQLException ex) {
			allAppointments = new Appointment[0];
		}
		ArrayList<Treatment> treatments = new ArrayList<Treatment>();
		for (Appointment app:allAppointments)
			treatments.addAll(app.getTreatments());
		if(treatments.size() == 0)
			infoPanel.add(new JLabel("No treatments"));
		billList = new JList(treatments.toArray());
		infoPanel.add(billList);

		pane.add(infoPanel, BorderLayout.CENTER);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		leaveBtn.addActionListener(cancelListener);

		this.setVisible(true);
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
	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}
}
