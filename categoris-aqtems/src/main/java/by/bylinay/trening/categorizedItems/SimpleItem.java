package by.bylinay.trening.categorizedItems;

public class SimpleItem  implements Item {

	private static final String[] String = null;
	private int id;
	private String name;
	private int categoryId;
	private String date;
	private Category category;
	private double money;



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
	
	public SimpleItem(int id, String name, int categoryId,  double money) {
		this( name, categoryId);
		this.categoryId = categoryId;
		this.money = money;
	}

	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getDate() {
		return date;
	}

	public Category getCategory() {

		return category;
	}
	
	public String getTableName () {
		return "item";
	}
	public int getSecondValue() {
		return categoryId;	
	}
	
	public double getTransactionValue() {
		return money;
	}

	public void setTransactionValue(double money) {
		this.money = money;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public  String [] getNamesColumns () {
		 String[] columns = {"name_","category_id", "date_"};
		return columns;
		
	}
	
	
	public Object[] getColumnsValue() {
		Object[] columns =  {getName(), getCategoryId(), getDate()};
		return columns;
	
	}
	



}