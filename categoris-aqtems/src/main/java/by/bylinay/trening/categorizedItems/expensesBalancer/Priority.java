package by.bylinay.trening.categorizedItems.expensesBalancer;
public class Priority {
	double persent;
	int allAmoun;

	public Priority(double persent, int allAmount) {
		this.persent = persent;
		this.allAmoun = allAmount;
	}

	public Priority() {
	}

	public double getPersent() {
		return persent;
	}

	public void setPersent(double persent) {
		this.persent = persent;
	}

	public int getAllAmount() {
		return allAmoun;
	}

	public void setAllAmount(int allAmount) {
		this.allAmoun = allAmount;
	}

	public String getAmountMany() {
				
		return (allAmoun/100 +"," + (allAmoun - allAmoun/100*100)) ;
	}

	public String getDifferent(int manye) {
		int result = manye * 100 - allAmoun;
		return (result/100 +"," + (result - result/100*100)) ;

	}

}
