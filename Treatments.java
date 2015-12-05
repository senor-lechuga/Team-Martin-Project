import java.util.Date;
public class Treatments {
	private String treatmentType;
	private double cost;
	public Treatments (String type,double c) {
		treatmentType = type;
		cost = c;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}