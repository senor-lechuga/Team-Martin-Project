public class TreatmentTypes {
	private String treatmentType;
	private double cost;
	public TreatmentTypes (String treatment,double c) {
		treatmentType = treatment;
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