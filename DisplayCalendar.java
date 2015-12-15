import java.awt.*;
import java.util.*;
import java.sql.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DisplayCalendar extends JFrame{
  private JButton leftButton,rightButton, exitButton, dentistButton, hygienistButton,bothButton;
  private JTextField weekField;
  private String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
  private java.util.Date today = new java.util.Date();
  private java.util.Date mon,sun;
  private String selectedWeek;
  private JTable table;
  private SqlHandler handler;
  public DisplayCalendar(SqlHandler h) throws SQLException{
    handler = h;
    // Main panel
    Container pane = this.getContentPane();
    pane.setLayout(new BorderLayout());
    pane.setPreferredSize(new Dimension(1000,600));

    // Week selection panl
    JPanel weekSelect = new JPanel(new BorderLayout());
    leftButton = new JButton(" < ");
    rightButton = new JButton(" > ");
    mon = getMonday(today);
    sun = getSunday(mon);
    selectedWeek = weekToString(mon,sun);
    weekField = new JTextField(selectedWeek);
    weekField.setHorizontalAlignment(JTextField.CENTER);
    weekSelect.add(leftButton,BorderLayout.WEST);
    weekSelect.add(rightButton,BorderLayout.EAST);
    weekSelect.add(weekField,BorderLayout.CENTER);

    // Calendar display panel
    JPanel weekDisplay = new JPanel(new BorderLayout());
    weekDisplay.setPreferredSize(new Dimension(950,500));
    table = new JTable();
    table.setModel(new DefaultTableModel(getWeeklyApps(mon,"both"),days));
    table.setPreferredSize(new Dimension(950,500));
    JScrollPane scrollPane = new JScrollPane(table);
    weekDisplay.add(scrollPane,BorderLayout.CENTER);

    // Exit button display button
    JPanel downPanel = new JPanel(new FlowLayout());
    exitButton = new JButton("EXIT");
    dentistButton = new JButton("View Dentis Appointments");
    hygienistButton = new JButton("View Hygienist Appintments");
    bothButton = new JButton("Show All Appointments");
    downPanel.add(bothButton);
    downPanel.add(dentistButton);
    downPanel.add(hygienistButton);
    downPanel.add(exitButton);
    downPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    // Adds everything to main panel
    pane.add(weekSelect,BorderLayout.NORTH);
    pane.add((new JScrollPane(weekDisplay)),BorderLayout.CENTER);
    pane.add(downPanel,BorderLayout.SOUTH);
    this.pack();
		setLocationRelativeTo(null);// Display in the centre of the screen

    //Adds action listeners
    exitButton.addActionListener(exitListener);
    leftButton.addActionListener(previousListener);
    rightButton.addActionListener(nextListener);
    dentistButton.addActionListener(dentistListener);
    hygienistButton.addActionListener(hygienistListener);
    bothButton.addActionListener(bothListener);
    this.setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  // Method to get weekly information
  private String[][] getWeeklyApps (java.util.Date date, String s) throws SQLException{
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    String[] apps = new String[5];
    for(int i=0; i<5;i++){
      java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
        String dayInfo = "";
        Appointment[] sqlApps = handler.getAppointmentsByDay(sqlDate);
        if(sqlApps.length<=0){
          dayInfo = "No Appointment for the day";
        }
        else{
          for(Appointment a : sqlApps){
              String info = "";
              if(s == "Dentist"){
                  if(a.getPartner() == "Dentist"){
                    info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                    info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                    info += "Partner: " + a.getPartner() + "\n";
                    dayInfo += info + "\n";
                  }
              }
              else if(s == "Hygienist"){
                  if(a.getPartner() == "Hygienist"){
                    info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                    info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                    info += "Partner: " + a.getPartner() + "\n";
                    dayInfo += info + "\n";
                  }
              }
              else{
                info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                info += "Partner: " + a.getPartner() + "\n";
                dayInfo += info + "\n";
              }
          }
        }
        apps[i] = dayInfo;
        c.add(Calendar.DATE,1);
    }
    String [][] weekDays = new String [1][5];
    for(int i=0;i<5;i++)
      weekDays[0][i] = apps[i];
    return weekDays;
  }

  // Method that prints the current week
  private String weekToString(java.util.Date startDate,java.util.Date endDate){
    String sDate = "",eDate = "";
    DateFormat formDate = new SimpleDateFormat("yyy-MM-dd");
    sDate = formDate.format(startDate);
    eDate = formDate.format(endDate);
    String selWeek = "Selected Week: " + sDate + "  to  " + eDate;
    return selWeek;
  }

  // Methods that return the current week
  private java.util.Date getMonday(java.util.Date day){
    Calendar c = Calendar.getInstance();
    c.setTime(day);
    c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
    return c.getTime();
  }
  private java.util.Date getSunday(java.util.Date day){
    Calendar c = Calendar.getInstance();
    c.setTime(day);
    c.add(Calendar.DATE, 6);
    return c.getTime();
  }

  // Action Listener methods
  private ActionListener exitListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			closeWindow();
		}
	};

  private ActionListener nextListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
      try{
        Calendar c = Calendar.getInstance();
        c.setTime(mon);
        c.add(Calendar.DATE,7);
        mon = c.getTime();
        c.add(Calendar.DATE,6);
        sun = c.getTime();
        selectedWeek = weekToString(mon,sun);
        weekField.setText(selectedWeek);
        //String[][] apps = getWeeklyApps(mon);
        //for(int i=0;i<5;i++){
        //  String values = apps[0][i];
          table.setModel(new DefaultTableModel(getWeeklyApps(mon,"both"),days));
        //}
      } catch (SQLException s){
          s.printStackTrace();
      }
		}
	};

  private ActionListener previousListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
      try{
        Calendar c = Calendar.getInstance();
        c.setTime(mon);
        c.add(Calendar.DATE,-7);
        mon = c.getTime();
        c.add(Calendar.DATE,6);
        sun = c.getTime();
        selectedWeek = weekToString(mon,sun);
        weekField.setText(selectedWeek);
        //String[][] apps = getWeeklyApps(mon);
        //for(int i=0;i<5;i++){
        //    String values = apps[0][i];
            table.setModel(new DefaultTableModel(getWeeklyApps(mon,"both"),days));
        //}
      } catch (SQLException s){
            s.printStackTrace();
      }
		}
	};

  private ActionListener dentistListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
      try{
        table.setModel(new DefaultTableModel(getWeeklyApps(mon,"Dentist"),days));
      } catch (SQLException s){
            s.printStackTrace();
      }
		}
	};

  private ActionListener hygienistListener = new ActionListener(){
    public void actionPerformed(ActionEvent e){
      try{
        table.setModel(new DefaultTableModel(getWeeklyApps(mon,"Hygienist"),days));
      } catch (SQLException s){
            s.printStackTrace();
      }
    }
  };

  private ActionListener bothListener = new ActionListener(){
    public void actionPerformed(ActionEvent e){
      try{
        table.setModel(new DefaultTableModel(getWeeklyApps(mon,"both"),days));
      } catch (SQLException s){
            s.printStackTrace();
      }
    }
  };

  // Close window method
  private void closeWindow()
	{
		this.setVisible(false);
		this.dispose();
	}

  public static void main(String [] args) throws SQLException, NullPointerException{
    DisplayCalendar d = new DisplayCalendar(null);
  }
}
