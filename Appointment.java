import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Appointment {
	private Patient patient;
	private java.util.Date date;
	private java.util.Date startTime, endTime;
	private String partner;
	private boolean paid;
	private String type;
	private ArrayList<Treatment> treatments;

	public Appointment (Patient p, java.util.Date dates,java.util.Date sTime,java.util.Date eTime, String partners, boolean paid, ArrayList<Treatment> ts) {
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

	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
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

	public java.sql.Date formatDate (){
		//SimpleDateFormat dForm = new SimpleDateFormat("dd/MMM/yyyy");
		//Date date = dForm.format(birthDate);
		//java.sql.Date sqlDate = new java.sql.Date(birthDate);
		//return sqlDate;
		return new java.sql.Date(date.getTime());
	}

	public java.sql.Date formatTime(java.util.Date time){
	//	SimpleDateFormat dForm = new SimpleDateFormat("HH:mm:ss");
		//java.util.Date formDate = dForm.parse(time);
		return new java.sql.Date(time.getTime());
	}

	public static void main (String [] args)
						throws SQLException,NullPointerException{
		java.util.Date date = new java.util.Date();
		java.util.Date start = new java.util.Date();
		java.util.Date end = new java.util.Date();

		Patient p = null;
		ArrayList <Treatment> ls = new ArrayList<Treatment>();

		Appointment a = new Appointment(p,date,start,end,"Dentist",false,ls);
		SqlHandler handler = new SqlHandler();
		handler.addAppointment(a);
	}
}
