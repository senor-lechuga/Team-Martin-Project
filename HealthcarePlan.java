public class HealthcarePlan {
	private String name;
	private int checkups;
	private int hygiene;
	private int repair;
	private double monthlyCost;
	public HealthcarePlan (String streetName,int checks,int hygienes,int repairs, double cost) {
		streetName = name;
		checks = checkups;
		hygienes = hygiene;
		repairs= repair;
		cost = monthlyCost;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCheckups() {
		return checkups;
	}
	public void setCheckups(int checkups) {
		this.checkups = checkups;
	}
	public int getHygiene() {
		return hygiene;
	}
	public void setHygiene(int hygiene) {
		this.hygiene = hygiene;
	}
	public int getRepair() {
		return repair;
	}
	public void setRepair(int repair) {
		this.repair = repair;
	}
	public double getMonthlyCost() {
		return monthlyCost;
	}
	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
}