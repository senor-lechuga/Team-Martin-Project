import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.text.*;

public class AddPatientDialogue extends JFrame implements IAddressEditor, IPlanEditor {
	
	private JButton createBtn, cancelBtn, addAddressBtn, addPlanBtn;
	private JTextField firstName, lastName, phoneNumber;
	private JSpinner birthDay, birthMonth, birthYear;
	private JComboBox address, healthcarePlan, title;
	private SqlHandler handler;

	public AddPatientDialogue (SqlHandler h)
	{
		super("Register a new user");
		//Set up sql handler
		handler = h;

		//Set up the panel	
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(8,1));

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(new JLabel("Title:"));
		title = new JComboBox(new String[]{"Mr", "Mrs", "Ms", "Miss", "Master", "Mx", "Dr", "Prof"});
		titlePanel.add(title);
		
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		firstNamePanel.add(new JLabel("Forename:"));
		firstName = new JTextField(30);
		firstNamePanel.add(firstName);

		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		lastNamePanel.add(new JLabel("Surname:"));
		lastName = new JTextField(30);
		lastNamePanel.add(lastName);

		JPanel birthPanel = new JPanel();
		birthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		birthPanel.add(new JLabel("Birth Date (dd/mm/yyyy):"));
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(1,3));
		birthDay = new JSpinner(new SpinnerNumberModel(1,1,31,1));
		birthMonth = new JSpinner(new SpinnerNumberModel(1,1,12,1));
		java.util.Date currentD = new java.util.Date();
		birthYear = new JSpinner(new SpinnerNumberModel(2015, 1800, currentD.getYear()+1900, 1));
		datePanel.add(birthDay);
		datePanel.add(birthMonth);
		datePanel.add(birthYear);
		birthPanel.add(datePanel);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		phonePanel.add(new JLabel("Phone Number:"));
		phoneNumber = new JTextField(11);
		phonePanel.add(phoneNumber);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addressPanel.add(new JLabel("Address:"));
		address = new JComboBox(new String[]{""});
		setupAddressBox();
		addAddressBtn = new JButton("Add/Edit");
		addressPanel.add(address);
		addressPanel.add(addAddressBtn);

		JPanel planPanel = new JPanel();
		planPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		planPanel.add(new JLabel("Healthcare Plan:"));
		healthcarePlan = new JComboBox(new String[]{""});
		setupHealthPlanBox();
		planPanel.add(healthcarePlan);
		addPlanBtn = new JButton("Add/Edit");
		planPanel.add(addPlanBtn);
		
		JPanel bottomButtons = new JPanel();
		bottomButtons.setLayout(new FlowLayout());
		createBtn = new JButton("Create");
		cancelBtn = new JButton("Cancel");	
		bottomButtons.add(createBtn);
		bottomButtons.add(cancelBtn);

		// Add all elements to the panel
		pane.add(titlePanel);
		pane.add(firstNamePanel);
		pane.add(lastNamePanel);
		pane.add(birthPanel);
		pane.add(phonePanel);
		pane.add(addressPanel);
		pane.add(planPanel);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		addAddressBtn.addActionListener(addressPopup);
		addPlanBtn.addActionListener(planPopup);
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		this.setVisible( true );
		setResizable(false);
	}

	public void setupAddressBox()
	{
		Address[] addresses;
		try{
			addresses = handler.getAllAddresses();
		}catch(SQLException e){
			addresses = new Address[0];
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(addresses);
		address.setModel(model);
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

	private ActionListener addressPopup = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			AddAddressDialogue d = new AddAddressDialogue(handler);
			d.addAddressEditorReference(AddPatientDialogue.this);
		}
	};
	private ActionListener planPopup = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			AddHealthPlanDialogue d = new AddHealthPlanDialogue(handler);
			d.addPlanEditorReference(AddPatientDialogue.this);
		}
	};
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
			String errorMessage = "";
			String sFirstName = firstName.getText().replaceAll("\\s+", "");
			String sLastName = lastName.getText().replaceAll("\\s+", "");
			String sPhoneNumber = phoneNumber.getText().replaceAll("\\s+", "");
			if(sFirstName.equals("") || sLastName.equals(""))
				errorMessage += "\nPlease enter both a first and last name.";
			if(sPhoneNumber.length() > 3 && sPhoneNumber.substring(0,3).equals("+44"))
				sPhoneNumber = "0"+sPhoneNumber.substring(3);
			if(sPhoneNumber.length() != 11)
				errorMessage += "\nThe phone number is not the correct length.";
			else if(!sPhoneNumber.matches("[0-9]+"))
				errorMessage +="\nThe phone number contains non-numbers.";
			String dateString = String.valueOf(birthDay.getValue()) + "-" + String.valueOf(birthMonth.getValue()) + "-" + String.valueOf(birthYear.getValue());
			try{
				DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				date.setLenient(false);
				java.util.Date formattedDate = date.parse(dateString);
				long ageMillis = System.currentTimeMillis() - formattedDate.getTime();
				if(ageMillis <= 0)
					errorMessage += "\nYou are not negative years old.";
				java.util.Date age = new java.util.Date(ageMillis);
				if(age.getYear() - 70 >= 18 && ((HealthcarePlan)healthcarePlan.getSelectedItem()).getName().equals("NHS"))
					errorMessage += "\nYou are not eligible for an NHS free plan.";
			}catch(ParseException ex) {
				errorMessage += "\nPlease enter a valid birth day.";
			}
			if(errorMessage != "")// There is a formatting error
				JOptionPane.showMessageDialog(null, errorMessage.substring(1), "Incorrect Data", JOptionPane.WARNING_MESSAGE);
			else{
				// Make a new patient here
				int intBirthYear = Integer.valueOf(String.valueOf(birthYear.getValue()));
				int intBirthMonth = Integer.valueOf(String.valueOf(birthMonth.getValue()));
				int intBirthDay = Integer.valueOf(String.valueOf(birthDay.getValue()));
				java.sql.Date sqlBirthDate = new java.sql.Date(intBirthYear, intBirthMonth, intBirthDay);
				HealthcarePlan hp = (HealthcarePlan)healthcarePlan.getSelectedItem();
				Address add = (Address)address.getSelectedItem();
				Patient p = new Patient(String.valueOf(title.getSelectedItem()), sFirstName, sLastName, sqlBirthDate, sPhoneNumber, hp, add);
				try {
					handler.addPatient(p);
				}catch(SQLException ex){
					JOptionPane.showMessageDialog(null, "Error registering patient.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				closeWindow();
			}

			
		}
	};

	private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args)
	{
		AddPatientDialogue d = new AddPatientDialogue(null);
	}
}
