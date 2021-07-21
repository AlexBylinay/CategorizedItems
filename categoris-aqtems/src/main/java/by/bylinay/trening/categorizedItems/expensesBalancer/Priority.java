package by.bylinay.trening.categorizedItems.expensesBalancer;

public class Priority {
	double persent;
	long allAmoun;

	public Priority(double persent, long allAmount) {
		this.persent = persent;
		this.allAmoun = allAmount;
	}

	public Priority() {

	}

	public double getPersent() {
		return persent*100;
	}

	public void setPersent(double persent) {
		this.persent = persent;
	}

	public long getAllAmount() {
		return allAmoun;
	}

	public void setAllAmount(long allAmount) {
		this.allAmoun = allAmount;
	}

	public double getAmountMany() {
		return (double) allAmoun / 100;
	}
}
