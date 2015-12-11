import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Patient {
	private String title;
	private String firstName;
	private String lastName;
	private java.util.Date birthDate;
	private String phone;
	private int patientID;
	private String houseNumber;
	private String postCode;
	private String healthPlan;
	private int checkUpsHad;
	private int hygienesHad;
	private int repairsHad;
	private HealthcarePlan plan;
	public Patient (String t, String fN, String lN, java.util.Date bD, int phone, String houseNum, String postC, String healthP, Address a, HealthcarePlan h){
		title = title;
		firstName = fN;
		lastName = lN;
		birthDate = bD;
		phone = phone;
		healthPlan = healthP;
		checkUpsHad = 0;
		hygienesHad = 0;
		repairsHad = 0;
		houseNumber = a.getHouseNumber();
		postCode = a.getPostCode();
		plan = h;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getHealthPlan() {
		return healthPlan;
	}
	public void setHealthPlan(String healthPlan) {
		this.healthPlan = healthPlan;
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
