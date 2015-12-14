import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Dentistry extends JFrame {
	private JButton createUserBtn, displayCalendarBtn;
	private SqlHandler handler = null;

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
		this.addWindowListener(quitListener);
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(2,1));

		createUserBtn = new JButton("Register a new patient");
		displayCalendarBtn = new JButton("Display practice calendar");
		
		
		// Add all elements to the panel
		pane.add(displayCalendarBtn);
		pane.add(createUserBtn);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		createUserBtn.addActionListener(startUserCreation);
		displayCalendarBtn.addActionListener(displayCalendar);
		this.setVisible( true );
	}

	private ActionListener startUserCreation = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			AddPatientDialogue d = new AddPatientDialogue(handler);
		}
	};
	private ActionListener displayCalendar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//CalendarChoice d = new CalendarChoice();
			UserCalendars calendar = new UserCalendars(handler);
	        	calendar.setVisible(true);
		}
	};

	private WindowListener quitListener = new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
			//int warning = JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Quit", JOptionPane.YES_NO_OPTION,
								   //JOptionPane.QUESTION_MESSAGE, null, null, null); 
			//if(warning == 0)
			//{
				handler.closeConnection();
				System.exit(0);
			//}
		}
	};
	private void closeWindow()
	{
		handler.closeConnection();
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args)
	{
		Dentistry d = new Dentistry();
	}
}
