import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Patient {
	private String title;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int phone;
	private int patientID;
	private String healthPlan;
	private Address address;
	private int checkUpsHad;
	private int hygienesHad;
	private int repairsHad;
	public Patient (String title, String firstName, String lastName, Date birthDate, int phone, String healthPlan, Address address){
		title = title;
		firstName = firstName;
		lastName = lastName;
		birthDate = birthDate;
		phone = phone;
		address = address;
		healthPlan = healthPlan;
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
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getHealthcarePlan() {
		return healthPlan;
	}
	public void setHealthcarePlan(String healthPlan) {
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

	public java.sql.Date formatDate (){
		//SimpleDateFormat dForm = new SimpleDateFormat("dd/MMM/yyyy");
		//Date date = dForm.format(birthDate);
		//java.sql.Date sqlDate = new java.sql.Date(birthDate);
		//return sqlDate;
		return new java.sql.Date(birthDate.getTime());
	}

}
