import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddHealthPlanDialogue extends JFrame {

	private JButton createBtn, cancelBtn;
	private JTextField planNameField,costCount;
	private JSpinner checkupCount, hygienesCount, repairsCount;
	private String name;
	private int checkup,hygiene,repairs;
	private double cost;
	private HealthcarePlan plan=null;

	public AddHealthPlanDialogue ()
	{
		//Set up the panel
		//super("Add new Health Plan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TODO: Get rid of this, just make it close the window.

		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(6,1));

		JPanel planName = new JPanel();
		planName.setLayout(new FlowLayout()/* new GridLayout(2,1)*/);
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
		costCount = new JTextField(5);
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
	}

	public HealthcarePlan getPlan(){
		return plan;
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
//			String escapedPlanName = planName.getText();  Apparently escaping is not needed when using prepared statements, as I think we are?
			if(planNameField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a name for this healthcare plan.", "Plan name missing", JOptionPane.WARNING_MESSAGE);
			else
			{
				//Perform SQL stuff here to create the new entry
				System.out.println("Add a new healthcare plan with these details:");
				System.out.println("|Name: " + planNameField.getText() + "\n|# of Checkups: " + checkupCount.getValue() + "\n|# of Hygiene checks: " + hygienesCount.getValue() + "\n|# of repairs: " + repairsCount.getValue() + "\n|Monthly cost: " + costCount.getText());
				//Creates Healthplan object
				name = planNameField.getText();
				checkup = Integer.valueOf((Integer)checkupCount.getValue());
				hygiene = Integer.valueOf((Integer)hygienesCount.getValue());
				repairs = Integer.valueOf((Integer)repairsCount.getValue());
				cost = Double.parseDouble(costCount.getText());
				plan = new HealthcarePlan(name,checkup,hygiene,repairs,cost);
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
		AddHealthPlanDialogue d = new AddHealthPlanDialogue();
		SqlHandler h = new SqlHandler();
		h.addHealcarePlan(d.getPlan());
	}
}
