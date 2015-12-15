public class HealthcarePlan{
	private String name;
	private int checkups;
	private int hygienes;
	private int repairs;
	private double monthlyCost;
	public HealthcarePlan (String name,int checkups,int hygienes,int repairs, double monthlyCost) {
		this.name = name;
		this.checkups = checkups;
		this.hygienes = hygienes;
		this.repairs = repairs;
		this.monthlyCost = monthlyCost;
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
	public int getHygienes() {
		return hygienes;
	}
	public void setHygienes(int hygiene) {
		this.hygienes = hygienes;
	}
	public int getRepairs() {
		return repairs;
	}
	public void setRepairs(int repair) {
		this.repairs = repairs;
	}
	public double getMonthlyCost() {
		return monthlyCost;
	}
	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
	public String toString() {
		return (this.name);
	}
}
