package by.bylinay.trening.categorizedItems;

import java.util.Date;

public class SimpleItem implements Item {

	private int id;
	private String name;
	private int categoryId;
	private Date date;
	private Category category;

	public SimpleItem(int id, String name, int categoryId, Date date) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.date = date;
	}

	public SimpleItem(int id, String name, int categoryId, Date date, Category category) {
		this(id, name, categoryId, date);
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getcatygoryId() {
		return categoryId;
	}

	public Date getDate() {
		return date;
	}

	public Category getCategory() {

		return category;
	}

}