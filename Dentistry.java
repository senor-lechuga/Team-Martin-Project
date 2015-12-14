import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Dentistry extends JFrame {
	private JButton createUserBtn, displayCalendarBtn, exitBtn;
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
		pane.setLayout(new GridLayout(3,1));

		createUserBtn = new JButton("Register a new patient");
		displayCalendarBtn = new JButton("Display practice calendar");
		exitBtn = new JButton("Exit");
		
		// Add all elements to the panel
		pane.add(displayCalendarBtn);
		pane.add(createUserBtn);
		pane.add(exitBtn);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		createUserBtn.addActionListener(startUserCreation);
		displayCalendarBtn.addActionListener(displayCalendar);
		exitBtn.addActionListener(exitSystem); 
		this.setVisible(true);
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
			UserCalendars calendar = new UserCalendars(handler);
	        	calendar.setVisible(true);
		}
	};
	private ActionListener exitSystem = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int warn = JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); 
			if(warn == 0)
				closeWindow();
		}
	};

	private WindowListener quitListener = new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
			handler.closeConnection();
			System.exit(0);
		}
	};
	private void closeWindow()
	{
		handler.closeConnection();
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Dentistry d = new Dentistry();
	}
}
