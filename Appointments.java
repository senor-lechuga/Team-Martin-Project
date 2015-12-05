public class Appointments {
	private int patientID;
	private Date date;
	private Time startTime, endTime;
	private String partner;
	private boolean paid;
	public Appointments (int id,Date dates,Time sTime,Time eTime, String partners, boolean p) {
		patientID = id;
		date = dates;
		startTime = sTime;
		endTime = eTime
		partner = partners;
		paid = p;
	}
