package by.bylinay.trening.categorizedItems;

import java.math.BigDecimal;

public interface Item extends  Entity {

	public int getId();
	public String getName();
	public int getCategoryId();
	public Category getCategory();
	public BigDecimal  getTransactionValue();
	public int  getManeyInСents();

}
