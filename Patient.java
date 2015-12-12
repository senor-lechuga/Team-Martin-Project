import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Patient {
	private String title;
	private String firstName;
	private String lastName;
	private java.sql.Date birthDate;
	private long phone;
	private int patientID;
	private HealthcarePlan healthPlan;
	private Address address;
	private int checkUpsHad;
	private int hygienesHad;
	private int repairsHad;
	public Patient (String title, String firstName, String lastName, java.sql.Date birthDate, long phone, HealthcarePlan healthPlan, Address address){
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phone = phone;
		this.address = address;
		this.healthPlan = healthPlan;
		checkUpsHad = 0;
		hygienesHad = 0;
		repairsHad = 0;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public java.sql.Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public HealthcarePlan getHealthcarePlan() {
		return healthPlan;
	}
	public void setHealthcarePlan(HealthcarePlan healthPlan) {
		this.healthPlan = healthPlan;
	}
	public Address getAddress(){
		return address;
	}
	public void setAddress(Address address){
		this.address = address;
	}
	public int getCheckUpsHad() {
		return checkUpsHad;
	}
	public void setCheckUpsHad(int checkUpsHad) {
		this.checkUpsHad = checkUpsHad;
	}
	public int getHygienesHad() {
		return hygienesHad;
	}
	public void setHygienesHad(int hygienesHad) {
		this.hygienesHad = hygienesHad;
	}
	public int getRepairsHad() {
		return repairsHad;
	}
	public void setRepairsHad(int repairsHad) {
		this.repairsHad = repairsHad;
	}

	//public java.sql.Date formatDate (){
	//	return new java.sql.Date(birthDate.getTime());
	//}

	/*public static void main (String [] args)
	          throws SQLException,NullPointerException{
	  java.util.Date date = new java.util.Date();
	  Address a = null;
	  HealthcarePlan h = null;
	  Patient p = new Patient("Mr.","Rafael","C",date,2345,"51a",
	                                  "S1 3GF","Basic",a,h);
	  SqlHandler handler = new SqlHandler();
	  handler.addPatient(p);
  }*/
}
