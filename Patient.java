import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Patient {
	private String title;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String phone;
	private int patientID;
	private String healthPlan;
	private Address address;
	private int checkUpsHad;
	private int hygienesHad;
	private int repairsHad;
	private HealthcarePlan plan;
	public Patient (String title, String firstName, String lastName, Date birthDate, int phone, String healthP, Address address, HealthcarePlan healthPlan){
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	public String getHealthPlan() {
		return healthPlan;
	}
	public void setHealthPlan(String healthPlan) {
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

	public String dateToString (){
		SimpleDateFormat dForm = new SimpleDateFormat("dd/MMM/yyyy");
		String dString = dForm.format(birthDate);
		return dString;
	}
	
}
