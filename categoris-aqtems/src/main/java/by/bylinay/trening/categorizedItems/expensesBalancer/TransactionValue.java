package by.bylinay.trening.categorizedItems.expensesBalancer;

public class TransactionValue {
	long amount;
	

	public TransactionValue() {
	}

	public TransactionValue(double target) {
		this.amount = (long) (target * 100);
	}

	public TransactionValue(TransactionValue value) {
		this.amount = value.getAmount();
	}


	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public void addDouble(double valoe) {
		this.amount = amount + (long) (valoe * 100);
	}

	public double divide(double value) {
		double result = 0;
		if (value == 0.0) {
			result = amount / (0.1 * 100);
		} else {
			result = amount / (value * 100);
		}
		return result;

	}

	public void add(TransactionValue valoe) {
		this.amount = amount + valoe.getAmount();
	}

}
