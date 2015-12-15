import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class EditPlanDialogue extends JFrame implements IPlanEditor {
	
	private JButton createBtn, cancelBtn, addPlanBtn;
	private JComboBox healthcarePlan;
	private SqlHandler handler;
	private Patient patient;

	public EditPlanDialogue (Patient p, SqlHandler h)
	{
		super("Edit Health Plan");
		//Set up the SQL stuff
		handler = h;
		patient = p;

		//Set up the panel
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(4,1));
		JPanel bottomButtons = new JPanel();

		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("Healthcare plan for " + p.getFullName()));
		titlePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JPanel currentPanel = new JPanel();
		currentPanel.add(new JLabel("Currently on: " + p.getHealthcarePlan()));
		currentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JPanel selectorPanel = new JPanel();
		selectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		healthcarePlan = new JComboBox(new String[]{""});
		setupHealthPlanBox();
		healthcarePlan.setSelectedItem(patient.getHealthcarePlan());
		selectorPanel.add(healthcarePlan);
		addPlanBtn = new JButton("Add/Edit");
		selectorPanel.add(addPlanBtn);

		bottomButtons.setLayout(new FlowLayout());
		createBtn = new JButton("Apply");
		cancelBtn = new JButton("Cancel");	
		bottomButtons.add(createBtn);
		bottomButtons.add(cancelBtn);

		// Add all elements to the panel
		pane.add(titlePanel);
		pane.add(currentPanel);
		pane.add(selectorPanel);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		addPlanBtn.addActionListener(planPopup);
		this.setVisible( true );
		setResizable(false);
	}

	public void setupHealthPlanBox()
	{
		HealthcarePlan[] planObjects;
		try {
			planObjects = handler.getAllHealthcarePlans();
		}catch(SQLException e){
			planObjects = new HealthcarePlan[0];
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(planObjects);
		healthcarePlan.setModel(model);
	}


	private ActionListener cancelListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Close the window
			closeWindow();
		}
	};
	private ActionListener createListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			patient.setHealthcarePlan((HealthcarePlan)healthcarePlan.getSelectedItem());
			/*try{
				handler.updatePatient(patient);
			}catch(SQLException ex){	
				JOptionPane.showMessageDialog(null, "Error registering patient.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			closeWindow();*/
		}
	};
	private ActionListener planPopup = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			AddHealthPlanDialogue d = new AddHealthPlanDialogue(handler);
			d.addPlanEditorReference(EditPlanDialogue.this);
		}
	};

	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}
}
