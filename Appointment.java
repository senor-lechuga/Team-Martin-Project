import java.util.Date;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Appointment {
	private Patient patient;
	private Date date;
	private Date startTime, endTime;
	private String partner;
	private boolean paid;
	private String type;
	private ArrayList<Treatment> treatments;

	public Appointment (Patient p, Date dates,Date sTime,Date eTime, String partners, boolean paid, ArrayList<Treatment> ts) {
		patient = p;
		date = dates;
		startTime = sTime;
		endTime = eTime;
		partner = partners;
		paid = paid;
		type = null;
		treatments = ts;
	}
	
	public Patient getPatient(){
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
		
	public ArrayList<Treatment> getTreatments(){
		return treatments;
	}
	
	public void addTreatment(Treatment t){
		this.treatments.add(t);
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

	public void setType(String type){
		this.type = type;
	}

	public String dateToString (){
		SimpleDateFormat dForm = new SimpleDateFormat("dd/MMM/yyyy");
		String dString = dForm.format(date);
		return dString;
	}

	public String timeToString(Date time){
		SimpleDateFormat dForm = new SimpleDateFormat("HH:mm:ss");
		String dString = dForm.format(time);
		return dString;
	}
}
