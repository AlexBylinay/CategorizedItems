package by.bylinay.trening.categorizedItems.difficult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;
import by.bylinay.trening.categorizedItems.Connector;
import by.bylinay.trening.categorizedItems.ConnectorAndStatement;
import by.bylinay.trening.categorizedItems.Item;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class New {

	private  String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
	private  String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
	private  String chekItem = "SELECT name_ FROM item WHERE name_ = ";
	private  String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
	private String delete = "DELETE FROM CATEGORY WHERE name_ = ";
			
	private Map<String, Integer> NameIdCategory = new HashMap<String, Integer>();
	 private  List<Category> allCategorys = new ArrayList<Category>();
	private Map<String, Integer> NameIdItem = new HashMap<String, Integer>();

	public static void reinit() throws SQLException, FileNotFoundException {
		FileInputStream in;
		in = new FileInputStream("resources\\categorisItems.sql");
		// in = new FileInputStream("resources\\kanigoryTru.sql");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		Statement statement = null;

		try {
			statement = ConnectorAndStatement.makeConnectionFndStatement();
			while (s.hasNext()) {
				String line = s.next().trim();

				if (!line.isEmpty())
					statement.execute(line);
			}
		} finally {
			if (statement != null)
				statement.close();
		}

		System.out.println("done create");
	}

	private  String skriptForCategory(Category catrgory) {
		return (billetCatygory + "(" + "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + "," + "'"
				+ (catrgory.getDate()) + "'" + ")" + ";");
	}

	private  String skriptForItem(Item item, Category catrgory) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgory.getId() + "," + "'" + (item.getDate())
				+ "'" + ")" + ";");
	}

	private  String skriptForItemWhithId(Item item, int catrgoryId) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'" + (item.getDate()) + "'"
				+ ")" + ";");
	}

	private  String skriptForChekCategory(String name) {
		return (chekCategory + "'" + (name + "'" + ";"));
	}

	private  String skriptForChekItem(String name) {
		return (chekItem + "'" + (name + "'" + ";"));
	}

	private  String skriptForDeleteCategory(String name) {
		
		return (delete + "'" + (name + "'" + ";"));
	}
	
	
	private void addCatygory(Category catrgory) throws SQLException {
		PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru()
				.prepareStatement((skriptForCategory(catrgory)), Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
			
	}

		NameIdCategory.put(catrgory.getName(), generatedKey);
	}

	private  void addItem(Item item, Category catrgory) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(item, catrgory));
	}

	private void addItemtemWhithId(Item item) throws SQLException {
		PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru()
				.prepareStatement(skriptForItemWhithId(item, item.getcatygoryId()), Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
		}

		NameIdItem.put(item.getName(), generatedKey);
	}

	private  String chekAutputCategory(String name) throws  SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);

		}
		return nameQuery;
	}
	
	private  void deleteCategory(String name) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForDeleteCategory(name));
		
	}
	
	private  String chekAutputItem(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekItem(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);

		}
		return nameQuery;
	}

	public void makeCatygory(String name, int color, int count) throws SQLException {
		String originalName = name;
	
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				try { 
				Category catrgory = new CategoryImpl(name, color);
				addCatygory(catrgory);
				}  catch  (SQLException e) { 
		            System.out.println("This category  with name ="  + " " + name  + " " + "already exists");
		            deleteCategory(name);
		            Category catrgory = new CategoryImpl(name, color);
		            addCatygory(catrgory);
		            System.out.println("was create new category with name =" + name);
		        } 

			}
			
			else {
				name = originalName + (toNum(i));
				
				try { 
					Category catrgory = new CategoryImpl(name, color);
					 allCategorys.add(catrgory);
					addCatygory(catrgory);
					}  catch  (SQLException e) { 
					    System.out.println("This category  with name ="  + " " + name  + " " + "already exists");
			            deleteCategory(name);
			            Category catrgory = new CategoryImpl(name, color);
			            addCatygory(catrgory);
			            allCategorys.add(catrgory);
			            System.out.println("was create new category with name =" + name);
			        } 

			}
		}
	}

	public void makeItem(String name, int count) throws SQLException {
		String originalName = name;
		
			List<Integer> values = new ArrayList<Integer>(NameIdCategory.values());
			if (allCategorys.size() == 0) {
				System.out.println("Categorys didn't create");
			} else {

				if (allCategorys.size() < count) {
					System.out.println("This Item name already exists");
				} else {

					for (int i = 0; i < count; i++) {
						if (i == 0) {
							name = originalName;
							//Item item = new SimpleItem(name, values.get(i));
							 Item item = new SimpleItem(name, allCategorys.get(i).getId());
							addItemtemWhithId(item);
						} else {
							name = originalName + String.valueOf(toNum(i));
							if (chekAutputItem(name) != null) {
								System.out.println("Error enter Item name");
							} else {

								//Item item = new SimpleItem(name, values.get(i));
								 Item item = new SimpleItem(name, allCategorys.get(i).getId());
								addItemtemWhithId(item);

							}

						}
					}
				}
			}
		}
	

	// http://java-online.ru/java-throws.xhtml

	public int getIdCategory(String name) {
		return NameIdCategory.get(name);
	}

	public int getIdItem(String name) {
		return NameIdItem.get(name);
	}

	private static int toNum(int index) {
		return index + 1;
	}
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		//reinit();
		New hh = new New();
		hh.makeCatygory("raccoon", 2, 8);
		//hh.makeCatygory("cat", 2, 9);
		//hh.makeItem("bbb23", 5);
	}
}