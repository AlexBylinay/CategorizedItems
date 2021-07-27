package by.bylinay.trening.categorizedItems.expensesBalancer;

public class TransactionValue {
	int amount;

	public TransactionValue() {
	}

	public TransactionValue(double target) {
		this.amount =  (int) (target);
	}

	public TransactionValue(TransactionValue value) {
		this.amount = value.getAmount();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void add(TransactionValue valoe) {
		this.amount = amount + valoe.getAmount();
	}
	
		

}
