package by.bylinay.trening.categorizedItems;

import java.util.Date;

public class CategoryImpl implements Category {
	private int id;
	private String name;
	private int color;
	private Date date;

	public CategoryImpl(int id, String name, int color, Date date) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.date = date;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public Date getDate() {
		return date;
	}

	

}