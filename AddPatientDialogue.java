import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddPatientDialogue extends JFrame {
	
	private JButton createBtn, cancelBtn, addAddressBtn;
	private JTextField title, firstName, lastName;
	private JSpinner birthDay, birthMonth, birthYear;
	private JComboBox address, healthcarePlan;
	private SqlHandler handler;

	public AddPatientDialogue (SqlHandler h)
	{
		super("Register a new user");
		//Set up sql handler
		handler = h;

		//Set up the panel	
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(7,1));

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(new JLabel("Title:"));
		title = new JTextField(5);
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
		birthYear = new JSpinner(new SpinnerNumberModel(2015, 1800, 2015, 1));//TODO: Make the upper bound of this spinner to be the current year.
		datePanel.add(birthDay);
		datePanel.add(birthMonth);
		datePanel.add(birthYear);
		birthPanel.add(datePanel);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addressPanel.add(new JLabel("Address:"));
		Address[] addresses;
		try{
			addresses = handler.getAllAddresses();
		}catch(SQLException e){
			addresses = new Address[0];
		}
		System.out.println(addresses);
		String[] places = {"Placetown","Townplace","townsville"};
		address = new JComboBox(places);
		addAddressBtn = new JButton("Add/Edit");
		addressPanel.add(address);
		addressPanel.add(addAddressBtn);
		
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
		pane.add(addressPanel);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		this.setVisible( true );
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
