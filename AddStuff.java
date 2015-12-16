import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class AddStuff {
	public static void main(String[] args)
	{
		SqlHandler h;
		try {
			h = new SqlHandler();
			ArrayList<Treatment> treats = new ArrayList<Treatment>();
			treats.add(new Treatment("brush", 100));
			treats.add(new Treatment("brush", 100));
			treats.add(new Treatment("Clean", 50));
			Patient blayze = h.getPatientById(9);
			java.sql.Date date = new java.sql.Date(2013, 6, 12);
			java.sql.Time start = new java.sql.Time(10,0,0);
			java.sql.Time end = new java.sql.Time(11,0,0);
			Appointment a = new Appointment(blayze, date, start, end, "dentist", treats);
			h.addAppointment(a);
		}catch(SQLException e){
			System.out.println("Error: Database connection failed:");
			e.printStackTrace();
			System.out.println("The system will now shut down.");
			System.exit(0);
		}
		
	}
}
