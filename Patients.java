import java.util.*;
public class Patients {
	private String title;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int phone;
	private int patientID;
	private String houseNumber;
	private String postCode;
	private String healthPlan;
	private int checkUpsHad;
	private int hygienesHad;
	private int repairsHad;
	private HealthcarePlan plan;
	public Patients (String t, String fN, String lN, Date bD, int phone, int pID, String houseNum, String postC, String healthP, int cuh, int hh, int rh, Address a, HealthcarePlan h){
		title = title;
		firstName = fN;
		lastName = lN;
		birthDate = bD;
		phone = phone;
		patientID = pID;
	  houseNumber = houseNum;
		postCode = postC;
		healthPlan = healthP;
		checkUpsHad = cuh;
		hygienesHad = hh;
		repairsHad = rh;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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

}
