import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Dentistry
 * The main program class, this GUI calls all other GUIs and is the container for the main connection to the database
 */

public class Dentistry extends JFrame {

	// Create all buttons so they can be globally accessed within event listeners
	private JButton createUserBtn, displayCalendarBtn, viewPatientInfoBtn, exitBtn, weeklyButton;
	private SqlHandler handler = null;// The handler variable holds a connection to the SQL database

	public Dentistry ()
	{
		// Title the window
		super("Dentistry");
		//Set up SQL connection
		try {
			handler = new SqlHandler();
		}catch(SQLException e){
			// If the SqlHandler could not be made then the connection to the database could not be made, so throw an error:
			System.out.println("Error: Database connection failed:");
			e.printStackTrace();
			System.out.println("The system will now shut down.");
			System.exit(0);
		}

		//Set up the panel elements
		this.addWindowListener(quitListener);

		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(5,1));

		createUserBtn = new JButton("Register a new patient");
		displayCalendarBtn = new JButton("Display practice calendar");
		viewPatientInfoBtn = new JButton("Edit Patient Information");
		weeklyButton = new JButton("View weekly Appointments");
		exitBtn = new JButton("Exit");

		// Add all elements to the panel
		pane.add(displayCalendarBtn);
		pane.add(weeklyButton);
		pane.add(createUserBtn);
		pane.add(viewPatientInfoBtn);
		pane.add(exitBtn);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		createUserBtn.addActionListener(startUserCreation);
		displayCalendarBtn.addActionListener(displayCalendar);
		viewPatientInfoBtn.addActionListener(editPatientInfo);
		weeklyButton.addActionListener(weeklyListener);
		exitBtn.addActionListener(exitSystem);
		
		this.setVisible(true);// Show the window
	}

	// Listeners
	private ActionListener startUserCreation = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			// If the register button is clicked, display the resgister window
			AddPatientDialogue d = new AddPatientDialogue(handler);
		}
	};
	private ActionListener displayCalendar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			// If the display calendar button is clicked, display the calendar
			UserCalendars calendar = new UserCalendars(handler);
	        	calendar.setVisible(true);
		}
	};
	private ActionListener exitSystem = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			// If the exit button is clicked, then make sure the user really does want to close, if so, then close the window
			int warn = JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(warn == 0)
				closeWindow();
		}
	};
	private ActionListener editPatientInfo = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			// If edit patient information is clicked, then display the patient selector dialogue
			PatientSelectorDialogue d = new PatientSelectorDialogue(handler);
		}
	};
	private ActionListener weeklyListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			// If the user wants to view the weekly calendar, create an instance of it
			try{
				DisplayCalendar weeklyCal = new DisplayCalendar(handler);
			}
			catch (SQLException s){
				s.printStackTrace();
			}
		}
	};

	// A window listener to run when the cross is clicked to close the window
	private WindowListener quitListener = new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
			handler.closeConnection();
			System.exit(0);
		}
	};

	// A function to close the window properly, closing the connection then disposing of the GUI and finally exiting the program
	private void closeWindow()
	{
		handler.closeConnection();
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}

	// The main class just creates a new instance of this GUI
	public static void main(String[] args)
	{
		Dentistry d = new Dentistry();
	}
}
