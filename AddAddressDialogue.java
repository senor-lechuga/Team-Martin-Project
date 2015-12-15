import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddAddressDialogue extends JFrame {
	
	private JButton createBtn, cancelBtn;
	private JTextField number, street, district, city, postCode;
	private SqlHandler handler;
	private IAddressEditor patientCreator;

	public AddAddressDialogue (SqlHandler h)
	{
		super("Add new address");
		//Set up the SQL stuff
		handler = h;

		//Set up the panel
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(6,1));

		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		numberPanel.add(new JLabel("House Number/Name:"));
		number = new JTextField(20);
		numberPanel.add(number);

		JPanel streetPanel = new JPanel();
		streetPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		streetPanel.add(new JLabel("Street:"));
		street = new JTextField(30);
		streetPanel.add(street);
	
		JPanel districtPanel = new JPanel();
		districtPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		districtPanel.add(new JLabel("District:"));
		district = new JTextField(30);
		districtPanel.add(district);

		JPanel cityPanel = new JPanel();
		cityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		cityPanel.add(new JLabel("City:"));
		city = new JTextField(30);
		cityPanel.add(city);

		JPanel postCodePanel = new JPanel();
		postCodePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		postCodePanel.add(new JLabel("Post Code:"));
		postCode = new JTextField(30);
		postCodePanel.add(postCode);


		JPanel bottomButtons = new JPanel();
		bottomButtons.setLayout(new FlowLayout());
		createBtn = new JButton("Create");
		cancelBtn = new JButton("Cancel");	
		bottomButtons.add(createBtn);
		bottomButtons.add(cancelBtn);

		// Add all elements to the panel
		pane.add(numberPanel);
		pane.add(streetPanel);
		pane.add(districtPanel);
		pane.add(cityPanel);
		pane.add(postCodePanel);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		this.setVisible( true );
		setResizable(false);
	}

	public void addAddressEditorReference(IAddressEditor d)
	{
		patientCreator = d;
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
			//Perform pre-evaluation here to make sure entered fields are correct
//			String escapedPlanName = planName.getText();  Apparently escaping is not needed when using prepared statements, as I think we are?
			String sPostCode = postCode.getText().replaceAll("\\s+","");// Strip spaces out of the postCode
			if(number.getText().equals("") || postCode.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter at least a house number and post code.", "Data missing", JOptionPane.WARNING_MESSAGE);
			else
			{
				//Perform SQL stuff here to create the new entry
				
				Address a = new Address(number.getText(), street.getText(), district.getText(), city.getText(), sPostCode);
				try{
					handler.setAddress(a);
					if(patientCreator != null)
						patientCreator.setupAddressBox();
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

	public static void main(String[] args)
	{
		AddAddressDialogue d = new AddAddressDialogue(null);
	}
}
