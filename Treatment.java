public class Treatment {
	private String treatmentType;
	private double cost;
	private String paymentType;
	public Treatment (String treatment,double c, String p) {
		treatmentType = treatment;
		cost = c;
		paymentType = p;
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
	
	public String getPaymentType(){
		return paymentType;
	}
	
	public void setPaymentType(String paymentType){
		this.paymentType = paymentType;
	}
	
	public String toString() {
		return (treatmentType + ": " + cost + " pounds");
	}
}
