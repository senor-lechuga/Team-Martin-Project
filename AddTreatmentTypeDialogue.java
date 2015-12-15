import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddTreatmentTypeDialogue extends JFrame {
	
	private JButton createBtn, cancelBtn;
	private JTextField nameField;
	private JSpinner costField;

	public AddTreatmentTypeDialogue ()
	{
		//Set up the panel
		super("Add new treatment type");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TODO: Get rid of this, just make it close the window.
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(3,1));

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout()/* new GridLayout(2,1) */);
		namePanel.add(new JLabel("Treatment Name:"));
		nameField = new JTextField(30);
		namePanel.add(nameField);
		
		JPanel costPanel = new JPanel();
		costPanel.setLayout(new FlowLayout()/* GridLayout(1,2) */);
		costPanel.add(new JLabel("Treatment Cost:"));
		costField = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 0.01));//TODO: Note the aritary large upper value.
		costPanel.add(costField);

		JPanel bottomButtons = new JPanel();
		bottomButtons.setLayout(new FlowLayout()/* new GridLayout(1,2) */);
		createBtn = new JButton("Create");
		cancelBtn = new JButton("Cancel");	
		bottomButtons.add(createBtn);
		bottomButtons.add(cancelBtn);

		// Add all elements to the panel
		pane.add(namePanel);
		pane.add(costPanel);
		pane.add(bottomButtons);
		this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

		// Add function listeners
		cancelBtn.addActionListener(cancelListener);
		createBtn.addActionListener(createListener);
		this.setVisible( true );
		setResizable(false);
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
			if(nameField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a name for this treatment.", "Treatment name missing", JOptionPane.WARNING_MESSAGE);
			else
			{
				//Perform SQL stuff here to create the new entry
				System.out.println("Add a new treatment type with these details:");
				System.out.println("|Name: " + nameField.getText() + "\n|Cost: " + costField.getValue());
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
		AddTreatmentTypeDialogue d = new AddTreatmentTypeDialogue();
	}
}
