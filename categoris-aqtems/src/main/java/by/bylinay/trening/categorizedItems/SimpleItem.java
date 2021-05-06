package by.bylinay.trening.categorizedItems;



public class SimpleItem implements Item {

	private int id;
	private String name;
	private int categoryId;
	private String date;
	private Category category;

	public SimpleItem(int id, String name, int categoryId, String date) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.date = date;
	}
	
	public SimpleItem( String name, int categoryId) {
		this.name = name;
		this.categoryId = categoryId;
		this.date = DataUtil.getDate();
	
	}

	
	public SimpleItem(int id, String name, int categoryId, String date, Category category) {
		this(id, name, categoryId, date);
		this.category = category;
		
	}

	public SimpleItem( String name, int categoryId,  Category category) {
		this( name, categoryId);
		this.category = category;
		this.date = DataUtil.getDate();
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

	public String getDate() {
		return date;
	}

	public Category getCategory() {

		return category;
	}

}