package by.bylinay.trening.categorizedItems;



public interface Item extends  Entity {

	public int getId();
	public String getName();
	public int getCategoryId();
	public Category getCategory();
	public double getTransactionValue();
	public void setTransactionValue(double money);

}
