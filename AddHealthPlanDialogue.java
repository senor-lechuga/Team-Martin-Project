import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;

public class AddHealthPlanDialogue extends JFrame {

	private JButton createBtn, cancelBtn;
	private JTextField planNameField;
	private JSpinner checkupCount, hygienesCount, repairsCount, costCount;
	private String name;
	private int checkup,hygiene,repairs;
	private double cost;
	private AddPatientDialogue patientCreator;
	private SqlHandler handler;

	public AddHealthPlanDialogue (SqlHandler h)
	{
		super("Add new Health Plan");
		//Set up the panel	
		handler = h;

		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(6,1));

		JPanel planName = new JPanel();
		planName.setLayout(new FlowLayout());
		planName.add(new JLabel("Plan Name:"));
		planNameField = new JTextField(30);
		planName.add(planNameField);

		SpinnerModel sm = new SpinnerNumberModel(0, 0, 365, 1);
		JPanel checkUps = new JPanel();
		checkUps.setLayout(new FlowLayout());
		checkUps.add(new JLabel("Number of checkup visits:"));
		checkupCount = new JSpinner(new SpinnerNumberModel(0, 0, 365, 1));
		checkUps.add(checkupCount);

		JPanel hygienes = new JPanel();
		hygienes.setLayout(new FlowLayout());
		hygienes.add(new JLabel("Number of hygiene visits:"));
		hygienesCount = new JSpinner(new SpinnerNumberModel(0, 0, 365, 1));
		hygienes.add(hygienesCount);

		JPanel repairs = new JPanel();
		repairs.setLayout(new FlowLayout());
		repairs.add(new JLabel("Number of repair visits:"));
		repairsCount = new JSpinner(new SpinnerNumberModel(0, 0, 365, 1));
		repairs.add(repairsCount);

		JPanel monthlyCost = new JPanel();
		monthlyCost.setLayout(new FlowLayout());
		monthlyCost.add(new JLabel("Monthly cost of plan:"));
		costCount = new JSpinner(new SpinnerNumberModel(0,0,1000,0.01));
		monthlyCost.add(costCount);

		JPanel bottomButtons = new JPanel();
		bottomButtons.setLayout(new FlowLayout()/* new GridLayout(1,2)*/);
		createBtn = new JButton("Create");
		cancelBtn = new JButton("Cancel");
		bottomButtons.add(createBtn);
		bottomButtons.add(cancelBtn);

		// Add all elements to the panel
		pane.add(planName);
		pane.add(checkUps);
		pane.add(hygienes);
		pane.add(repairs);
		pane.add(monthlyCost);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		this.setVisible( true );
		setResizable(false);
	}
	
	public void addPatientReference(AddPatientDialogue p)
	{
		patientCreator = p;
	}

	private ActionListener cancelListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Close the window
			closeWindow();
			//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	};
	private ActionListener createListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Perform pre-evaluation here to make sure entered fields are correct
			if(planNameField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a name for this healthcare plan.", "Plan name missing", JOptionPane.WARNING_MESSAGE);
			else
			{
				//Perform SQL stuff here to create the new entry
				/*System.out.println("Add a new healthcare plan with these details:");
				System.out.println("|Name: " + planNameField.getText() + "\n|# of Checkups: " + checkupCount.getValue() + "\n|# of Hygiene checks: " + hygienesCount.getValue() + "\n|# of repairs: " + repairsCount.getValue() + "\n|Monthly cost: " + costCount.getText());*/
				//Creates Healthplan object
				name = planNameField.getText();
				checkup = Integer.valueOf((Integer)checkupCount.getValue());
				hygiene = Integer.valueOf((Integer)hygienesCount.getValue());
				repairs = Integer.valueOf((Integer)repairsCount.getValue());
				cost = Double.valueOf((Double)costCount.getValue());
				HealthcarePlan plan = new HealthcarePlan(name,checkup,hygiene,repairs,cost);
				try{
					handler.addHealthcarePlan(plan);
					if(patientCreator != null)
						patientCreator.setupHealthPlanBox();
				}catch(SQLException ex){
					System.out.println("An error occured:");
					ex.printStackTrace();
				}
				//Finally, close the window:
				closeWindow();
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
		AddHealthPlanDialogue d = new AddHealthPlanDialogue(null);
	}
}
