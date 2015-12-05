import java.util.Date;
public class Appointments {
	private int patientID;
	public Date date;
	private Date startTime, endTime;
	private String partner;
	private boolean paid;
	private double cost;
	private String type;
	public Appointments (int id,Date dates,Date sTime,Date eTime, String partners, boolean p, Treatments t) {
		patientID = id;
		date = dates;
		startTime = sTime;
		endTime = eTime;
		partner = partners;
		paid = p;
		cost = t.getCost();
		type = t.getTreatmentType();
	}
		public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
