import java.awt.*;
import java.util.*;
import java.sql.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
  private String [][] appointments= new String[24][5];
  private Boolean[][] takenSlot = new Boolean[24][5];
  private JButton[][] appButtons = new JButton[25][5];
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
    JPanel table = new JPanel(new GridLayout(25,5));
    for(int i=0;i<days.length;i++){
        JButton a = new JButton(days[i]);
        table.add(a);
        appButtons[0][i] = a;
        appButtons[0][i].setBorderPainted(false);
        appButtons[0][i].setFocusPainted(false);
    }
    int hour = 9;
    int mins = 0;
    for(int j=1;j<25;j++){
      for(int i=0;i<5;i++){
        String time;
          if(hour<10)
            time = "0" + hour +":"+ mins + "0";
          else
            time = hour +":"+ mins + "0";
          JButton a = new JButton(time);
          table.add(a);
          appButtons[j][i] = a;
          appButtons[j][i].addActionListener(appListener);
          appointments[j-1][i] = "No appointments this day!";
          takenSlot[j-1][i] = false;
      }
      mins += 2;
      if(mins>=6){
        hour++;
        mins = 0;
      }
    }
    getWeeklyApps(getMonday(today),"both");
    weekDisplay.add(table,BorderLayout.CENTER);

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
    String[][] weeklyApps = new String[24][5];
    for(int i=0; i<5;i++){
      java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
        String dayInfo = "";
        Appointment[] sqlApps = handler.getAppointmentsByDay(sqlDate);
        if(sqlApps.length<=0){
          if(s=="both")
            dayInfo = "No Appointments for this slot!";
          else
            dayInfo = "No " + s + " Appointments";
        }
        else{
          for(Appointment a : sqlApps){
              String info = "";
              if(s == "Dentist"){
                  if(a.getPartner() == "Dentist"){
                    info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                    info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                    info += "Partner: " + a.getPartner() + "\n";
                  }
              }
              else if(s == "Hygienist"){
                  if(a.getPartner() == "Hygienist"){
                    info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                    info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                    info += "Partner: " + a.getPartner() + "\n";
                  }
              }
              else{
                info += "Patient: " +a.getPatient().getTitle() + " " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName()+"\n";
                info += "Time: " + a.getStartTime() + "-" + a.getEndTime() + "\n";
                info += "Partner: " + a.getPartner() + "\n";
              }

              String sTime = a.getStartTime().toString();
              sTime = sTime.substring(0,sTime.length()-3);
              int startHour = Integer.valueOf(sTime.substring(0,2));
              int startMinutes = Integer.valueOf(sTime.substring(3));
              String eTime = a.getEndTime().toString();
              eTime = eTime.substring(0,eTime.length()-3);
              int endHour = Integer.valueOf(sTime.substring(0,2));
              int endMinutes = Integer.valueOf(sTime.substring(3));
              while(startHour != endHour && startMinutes == endMinutes){
                for(int j=1;j<25;j++){
                    if(appButtons[j][i].getText().equals(sTime)){
                      if(takenSlot[j-1][i]){
                        weeklyApps[j-1][i] += "\n" + info;
                        appButtons[j][i].setBackground(Color.RED);
                      }
                      else{
                        takenSlot[j-1][i] = true;
                        weeklyApps[j-1][i] = dayInfo;
                        appButtons[j][i].setBackground(Color.RED);
                      }
                    }
                }
                if(startHour<endHour){
                  if(startMinutes < 60)
                    startMinutes += 20;
                  else
                    startHour += 1;
                    startMinutes = 0;
                }
                else{
                  if(startMinutes<endMinutes)
                    startMinutes += 20;
                }
              }
          }
        }
        c.add(Calendar.DATE,1);
    }
    return weeklyApps;
  }

  public void reset(String p){
    for(int i=0;i<24;i++){
      for(int j=0;j<5;j++){
        if(p=="both")
          appointments[i][j]= "No appointments for this slot!";
        else
          appointments[i][j]= "No "+p+" appointments for this slot!";
        takenSlot[i][j] = false;
      }
    }
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
      try {
        Calendar c = Calendar.getInstance();
        c.setTime(mon);
        c.add(Calendar.DATE,7);
        mon = c.getTime();
        c.add(Calendar.DATE,6);
        sun = c.getTime();
        selectedWeek = weekToString(mon,sun);
        weekField.setText(selectedWeek);
        reset("both");
        getWeeklyApps(mon,"both");
      } catch (SQLException s){
        s.printStackTrace();
      }
		}
	};

  private ActionListener previousListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
      try {
        Calendar c = Calendar.getInstance();
        c.setTime(mon);
        c.add(Calendar.DATE,-7);
        mon = c.getTime();
        c.add(Calendar.DATE,6);
        sun = c.getTime();
        selectedWeek = weekToString(mon,sun);
        weekField.setText(selectedWeek);
        reset("both");
        getWeeklyApps(mon,"both");
      } catch (SQLException s){
        s.printStackTrace();
      }

		}
	};

  private ActionListener dentistListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
      try{
        reset("Dentist");
        getWeeklyApps(mon,"Dentist");
      } catch(SQLException s){
        s.printStackTrace();
      }
		}
	};

  private ActionListener hygienistListener = new ActionListener(){
    public void actionPerformed(ActionEvent e){
      try{
        reset("Hygienist");
        getWeeklyApps(mon,"Hygienist");
      } catch(SQLException s){
        s.printStackTrace();
      }
    }
  };

  private ActionListener bothListener = new ActionListener(){
    public void actionPerformed(ActionEvent e){
      try{
        reset("both");
        getWeeklyApps(mon,"both");
      } catch(SQLException s){
        s.printStackTrace();
      }
    }
  };
  private ActionListener appListener = new ActionListener(){
    public void actionPerformed(ActionEvent ae){
      for(int i=0;i<25;i++){
        for(int j=0;j<5;j++){
          if(ae.getSource()==appButtons[i][j]){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,appointments[i][j]);
          }
        }
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
    SqlHandler hand = new SqlHandler();
    DisplayCalendar d = new DisplayCalendar(hand);
  }
}
