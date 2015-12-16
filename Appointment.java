import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Appointment {
	private Patient patient;
	private java.sql.Date date;
	private java.sql.Time startTime, endTime;
	private String partner;
	private boolean paid;
	private ArrayList<Treatment> treatments;

	public Appointment (Patient patient, java.sql.Date date,java.sql.Time startTime,java.sql.Time endTime, String partner, boolean paid, ArrayList<Treatment> treatments) {
		this.patient = patient;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.partner = partner;
		this.paid = paid;
		this.treatments = treatments;
	}

	public Appointment (Patient patient, java.sql.Date date,java.sql.Time startTime,java.sql.Time endTime, String partner) {
		this(patient,date,startTime,endTime,partner,false, new ArrayList<Treatment>());
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

	public java.sql.Date getDate() {
		return date;
	}
	public String dateToString() {
		return ("00:00:00");
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public java.sql.Time getStartTime() {
		return startTime;
	}
	public void setStartTime(java.sql.Time startTime) {
		this.startTime = startTime;
	}
	public java.sql.Time getEndTime() {
		return endTime;
	}
	public void setEndTime(java.sql.Time endTime) {
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

	public String toString() {
	String str = "";
	str += this.patient + " , ";
	str += this.date + " , ";
	str += this.startTime + " , ";
	str += this.endTime + " , ";
	str += this.partner+ " , ";
	str += this.paid + " , ";
	str += this.treatments;

	return str;
	}

	/*public java.sql.Date formatDate (){
		//SimpleDateFormat dForm = new SimpleDateFormat("dd/MMM/yyyy");
		//Date date = dForm.format(birthDate);
		//java.sql.Date sqlDate = new java.sql.Date(birthDate);
		//return sqlDate;
		return new java.sql.Date(date.getTime());
	}

	public String formatTime(java.sql.Time time){
		SimpleDateFormat dForm = new SimpleDateFormat("HH:mm");
		String formDate = dForm.format(time);
		return formDate;
	}*/

	/*public static void main (String [] args)
						throws SQLException,NullPointerException{
		java.util.Date date = new java.util.Date();
		java.util.Date start = new java.util.Date();
		java.util.Date end = new java.util.Date();

		Patient p = null;
		ArrayList <Treatment> ls = new ArrayList<Treatment>();

		Appointment a = new Appointment(p,date,start,end,"Dentist",false,ls);
		SqlHandler handler = new SqlHandler();
		handler.addAppointment(p);
	}*/
}
