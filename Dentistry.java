import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Dentistry extends JFrame {
	
	private JButton createUserBtn, displayCalendarBtn;
	private SqlHandler handler;

	public Dentistry ()
	{
		super("Dentistry");
		//Set up SQL connection
		try {
			handler = new SqlHandler();
		}catch(SQLException e){
			System.out.println("Error: Database connection failed:");
			e.printStackTrace();
			System.out.println("The system will now shut down.");
			System.exit(0);
		}

		//Set up the panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TODO: ???
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(3,1));

		createUserBtn = new JButton("Register a new patient");
		displayCalendarBtn = new JButton("Display practice calendar");
		
		JButton testAddressAdd = new JButton("Display the add address GUI");
		
		// Add all elements to the panel
		pane.add(displayCalendarBtn);
		pane.add(createUserBtn);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		createUserBtn.addActionListener(startUserCreation);
		testAddressAdd.addActionListener(displayAddGUI);
		this.setVisible( true );
	}

	private ActionListener startUserCreation = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
		//	AddPatientDialogue d = new AddPatientDialogue(handler);
		}
	};

	private ActionListener displayAddGUI = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
		//	AddAddressDialogue d = new AddAddressDialogue(handler);
		}
	};
	private void closeWindow()
	{
		try{
			handler.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args)
	{
		Dentistry d = new Dentistry();
	}
}
