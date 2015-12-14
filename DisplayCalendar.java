import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DisplayCalendar extends JFrame{
  private JButton leftButton,rightButton, exitButton;
  private JTextField currentWeek;
  private String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
  private JTable table;
  public DisplayCalendar(){
    // Main panel
    Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());

    // Week selection panl
    JPanel weekSelect = new JPanel(new BorderLayout());
    leftButton = new JButton(" < ");
    rightButton = new JButton(" > ");
    currentWeek = new JTextField("Current Week");
    weekSelect.add(leftButton,BorderLayout.WEST);
    weekSelect.add(rightButton,BorderLayout.EAST);
    weekSelect.add(currentWeek,BorderLayout.CENTER);

    // Calendar display panel
    JPanel weekDispaly = new JPanel();
    table = new JTable(getWeeklyApps(),days);
    table.setPreferredScrollableViewportSize(new Dimension(500,70));
    table.setFillsViewportHeight(true);
    weekDispaly.add(table,BorderLayout.CENTER);

    // Exit button display button
    JPanel exitPanel = new JPanel(new BorderLayout());
    exitButton = new JButton("EXIT");
    exitPanel.add(exitButton,BorderLayout.CENTER);

    // Adds everything to main panel
    pane.add(weekSelect,BorderLayout.NORTH);
    pane.add(weekDispaly,BorderLayout.CENTER);
    pane.add(exitPanel,BorderLayout.SOUTH);
    this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

    //Adds action listeners
    exitButton.addActionListener(exitListener);
    this.setVisible(true);
  }

  // Method to get weekly information
  private String[][] getWeeklyApps (){
    String monday = "";
    String tuesday = "";
    String wednesday = "";
    String thursday = "";
    String friday = "";
    String [] apps = {monday,tuesday,wednesday,thursday,friday};
    String [][] weekDays = new String [1][5];
    for(int i=0;i<5;i++)
      weekDays[0][i] = apps[i];
    return weekDays;
  }

  // Action Listener methods
  private ActionListener exitListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			closeWindow();
		}
	};

  // Close window method
  private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
    System.exit(0);
	}

  public static void main(String [] args){
    DisplayCalendar d = new DisplayCalendar();
  }
}
