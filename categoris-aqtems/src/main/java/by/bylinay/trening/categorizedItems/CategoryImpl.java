package by.bylinay.trening.categorizedItems;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CategoryImpl implements Category {
	private int id;
	private String name;
	private int color;
	private String  date;

	

	public CategoryImpl(int id, String name, int color, String date) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.date = date;
	}
	
	
	public CategoryImpl( String name, int color) {
		this.name = name;
		this.color = color;
		this.date = DataUtil.getDate();
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

	public String getDate() {
		return date;
	}

	

}