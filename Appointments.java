import java.util.*;
public class Appointments {
	private int patientID;
	public Date date;
	private Date startTime, endTime;
	private String partner;
	private boolean paid;
	public Appointments (int id,Date dates,Date sTime,Date eTime, String partners, boolean p) {
		patientID = id;
		date = dates;
		startTime = sTime;
		endTime = eTime;
		partner = partners;
		paid = p;
	}
}
