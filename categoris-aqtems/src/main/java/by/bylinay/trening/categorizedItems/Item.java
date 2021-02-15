package by.bylinay.trening.categorizedItems;

import java.util.Date;

public interface Item {

	public int getId();

	public String getName();

	public int catygoryId();

	public Date getDate();

	public Category getCategory();

}
