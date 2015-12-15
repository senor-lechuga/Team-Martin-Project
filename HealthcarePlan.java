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
	/*
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o != null && o.getClass() == this.getClass())
		{
			HealthcarePlan other = ((HealthcarePlan)o);
			if(other.getName() == this.getName() && other.getMonthlyCost() == this.getMonthlyCost() &&
			   other.getRepairs() == this.getRepairs() && other.getHygienes() == this.getHygienes() &&
			   other.getCheckups() == this.getCheckups())
				return (true);
			else
				return (false);
		}else
			return (false);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		result = prime * result + checkups;
		result = prime * result + hygienes;
		result = prime * result + repairs;
		result = prime * result + (int)(monthlyCost * 10);
		return result;
	}*/
}
