package by.bylinay.trening.categorizedItems.difficult;

import java.io.File;

import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

public class DatabaseInitializer {

	private static String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
	private static String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
	private static String chekItem = "SELECT name_ FROM item WHERE name_ = ";
	private static String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
	private  Map<String, Integer> NameIdCategory = new HashMap<String, Integer>();
	//private static List<Category> allCategorys = new ArrayList<Category>();
	private  Map<String, Integer> NameIdItem = new HashMap<String, Integer>();

	public static void reinit() throws SQLException, FileNotFoundException
	{
	//	FileInputStream in;
		//in = new FileInputStream("resources\\categorisItems.sql");
		File file = new File("resources\\categorisItems.sql"); 
		// in = new FileInputStream("resources\\kanigoryTru.sql");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);
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

	private static String skriptForCategory(Category catrgory) {
		return (billetCatygory + "(" + "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + "," + "'"
				+ (catrgory.getDate()) + "'" + ")" + ";");
	}

	private static String skriptForItem(Item item, Category catrgory) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgory.getId() + "," + "'" + (item.getDate())
				+ "'" + ")" + ";");
	}

	private static String skriptForItemWhithId(Item item, int catrgoryId) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'" + (item.getDate()) + "'"
				+ ")" + ";");
	}

	private static String skriptForChekCategory(String name) {
		return (chekCategory + "'" + (name + "'" + ";"));
	}

	private static String skriptForChekItem(String name) {
		return (chekItem + "'" + (name + "'" + ";"));
	}

	private  void addCatygory(Category catrgory) throws  SQLException {
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

	private static void addItem(Item item, Category catrgory) throws  SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(item, catrgory));
	}

	private  void addItemtemWhithId(Item item) throws SQLException {
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

	private static String chekAutputCategory(String name) throws  SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);

		}
		return nameQuery;
	}

	private static String chekAutputItem(String name) throws  SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekItem(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);

		}
		return nameQuery;
	}

	public  void makeCatygory(String name, int color, int count) throws  SQLException {
		String originalName = name;
		//if (chekAutputCategory(name) != null) {
		//	System.out.println("This Category name already exists");
		//} else {
			for (int i = 0; i < count; i++) {
				//if (i == 0) {

					//Category catrgory = new CategoryImpl(name, color);
					// allCategorys.add(catrgory);
					//addCatygory(catrgory);

			//	} else {
					name = originalName +  (toNum(i));
					if (chekAutputCategory(name) != null) {
						System.out.println("This Category name already exists");
					} else {
						Category catrgory = new CategoryImpl(name, color);
						// allCategorys.add(catrgory);
						addCatygory(catrgory);

					}
				}
			}
	//	}
	//}

	public  void makeItem(String name, int count) throws SQLException   {
		String originalName = name;
		if (chekAutputItem(name) != null) {
			System.out.println("This Item name already exists");
		} else {
			List<Integer> values = new ArrayList<Integer>(NameIdCategory.values());
			if (NameIdCategory.size() == 0) {
				System.out.println("Categorys didn't create");
			} else {

				if (NameIdCategory.size() < count) {
					System.out.println("This Item name already exists");
				} else {

					for (int i = 0; i < count; i++) {
						if (i == 0) {
							name = originalName;
							Item item = new SimpleItem(name, values.get(i));
							// Item item = new SimpleItem(name, allCategorys.get(i).getId());
							addItemtemWhithId(item);
						} else {
							name = originalName + (i);
							if (chekAutputItem(name) != null) {
								System.out.println("Error enter Item name");
							} else {

								Item item = new SimpleItem(name, values.get(i));
								// Item item = new SimpleItem(name, allCategorys.get(i).getId());
								addItemtemWhithId(item);

							}

						}
					}
				}
			}
		}
	}

	public  int getIdCategory(String name) {
		return NameIdCategory.get(name);
	}

	public  int getIdItem(String name) {
		return NameIdItem.get(name);
	}
	
	private static int toNum(int index) {
		return index + 1;
	}


	public static void main(String[] args) throws FileNotFoundException, SQLException {
		reinit();
		DatabaseInitializer hh = new DatabaseInitializer();
		//hh.makeCatygory("raccoon", 2, 5);
		//hh.makeCatygory("cat", 2, 5);
		//hh.makeCatygory("giraff", 2, 5);

		//hh.makeItem("bbb", 15);
		// RanSkript.getCauntCategoty ();
		// System.out.println(RanSkript.getIdCategory("raccoon2"));
		// System.out.println(RanSkript.getIdItem("bbb7"));
	}
}
