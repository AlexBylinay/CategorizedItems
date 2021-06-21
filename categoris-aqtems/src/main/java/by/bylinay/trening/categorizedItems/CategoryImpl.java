package by.bylinay.trening.categorizedItems;


public class CategoryImpl  implements Category {
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
	
	public CategoryImpl( int id, String name, int color) {
		this.id = id;
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

	
	public static String getNameTable () {
		return "category";
	}
	
	
	public static String getNameColomnName () {
		return "name_";
	}
	
	public static String getNameColomnColor () {
		return "color";
	}
	
	public static String getNameColomnDate () {
		return "date_";
	}
	public static String getGeneralInsert () {
		return "category ( name_, color, date_)";
	}
	

}