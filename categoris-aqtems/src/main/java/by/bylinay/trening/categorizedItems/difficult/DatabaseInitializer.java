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

	private String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
	private String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
	private String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
	private String chekItem = "SELECT name_ FROM item WHERE name_ = ";

	private Map<String, Integer> NameIdCategory = new HashMap<String, Integer>();
	private Map<String, Integer> NameIdItem = new HashMap<String, Integer>();

	public static void reinit() throws SQLException, FileNotFoundException {

		File file = new File("resources\\categorisItems.sql");
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

	private String skriptForCategory(Category catrgory) {
		return (billetCatygory + "(" + "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + "," + "'"
				+ (catrgory.getDate()) + "'" + ")" + ";");
	}

	private String skriptForItem(Item item, Category catrgory) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgory.getId() + "," + "'" + (item.getDate())
				+ "'" + ")" + ";");
	}

	private String skriptForItemWhithId(Item item, int catrgoryId) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'" + (item.getDate()) + "'"
				+ ")" + ";");
	}

	private String skriptForItemWSimple(Item item) {
		return (billetItem + "(" + "'" + item.getName() + "'" + "," + item.getcatygoryId() + "," + "'"
				+ (item.getDate()) + "'" + ")" + ";");
	}

	private String skriptForChekCategory(String name) {
		return (chekCategory + "'" + (name + "'" + ";"));
	}

	private String skriptForChekItem(String name) {
		return (chekItem + "'" + (name + "'" + ";"));
	}

	private int addCategory(Category catrgory) throws SQLException {
		PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru()
				.prepareStatement((skriptForCategory(catrgory)), Statement.RETURN_GENERATED_KEYS);

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);

		}

		return generatedKey;
	}

	private void addItem(Item item, Category catrgory) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(item, catrgory));
	}

	private void addItemSimple(Item item) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItemWSimple(item));
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
	}

	private String getNameForDataDaseCategory(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);
		}
		return nameQuery;
	}

	private String getNameForDataDaseItem(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekItem(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);
		}
		return nameQuery;
	}


	private List<String> chekAutputNameCategory(String nameFormat, int count) throws SQLException {
		List<String> names = new ArrayList<>();
		List<String> namesOutDataBase = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			String name = String.format(nameFormat + i);
			names.add(name);
		}
		names.forEach(b -> {
			try {
				if (getNameForDataDaseCategory(b) != null) {
					namesOutDataBase.add(getNameForDataDaseCategory(b));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return namesOutDataBase;
	}

	
	private List<String> chekAutputNameItem(String nameFormat, int count) throws SQLException {
		List<String> names = new ArrayList<>();
		List<String> namesOutDataBase = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			String name = String.format(nameFormat + i);
			names.add(name);
		}
		names.forEach(b -> {
			try {
				if (getNameForDataDaseItem(b) != null) {
					namesOutDataBase.add(getNameForDataDaseItem(b));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return namesOutDataBase;
	}

	
	
	private boolean chekAutputNameCategory(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);
		}
		return name.equals(nameQuery);
	}
	

	private List<Category> generateCategorys(String nameFormat, int color, int count) {
		List<Category> categorys = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			Category category = new CategoryImpl(String.format(nameFormat + i), color);
			categorys.add(category);
		}
		return categorys;
	}

	
	
	public void makeCatygory(String nameFormat, int color, int count) throws SQLException {
		generateCategorys(nameFormat, color, count).forEach(b -> {
			
			try {
				if (chekAutputNameCategory(b.getName())) {
					throw new IllegalArgumentException("Unable to create an object, this a unique name already exists " + b.getName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		generateCategorys(nameFormat, color, count).forEach(b -> {
			try {
				addCategory(b);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	
	private void makeCatygory2(String nameFormat, int color, int count) throws SQLException {
		if (!chekAutputNameCategory(nameFormat, count).isEmpty()) {
			throw new IllegalArgumentException("Unable to create an object, this a unique name already exists " +chekAutputNameCategory(nameFormat, count));
		}

		generateCategorys(nameFormat, color, count).forEach(b -> {

			try {
				addCategory(b);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	private List<Item> generateItemFromListCatrgory(String nameFormat, List<Category> catrgorys, int count) {
		List<Item> items = new ArrayList<>();
		if (catrgorys.size() < count) {
			System.out.println("insufficient number of Categories in list");
		} else {
			for (int i = 1; i <= count; i++) {
				Item item = new SimpleItem(String.format(nameFormat + i), catrgorys.get( toIndex(i)).getId());
				items.add(item);
			}
		}
		return items;
	}

	private List<Item> generateItem(String nameFormat, Category catrgory, int count) {
		List<Item> items = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			Item item = new SimpleItem(String.format(nameFormat + i), catrgory.getId());
			items.add(item);
		}
		return items;
	}

	public void makeItem(String nameFormat, Category catrgory, int count) throws SQLException {
		if (!chekAutputNameItem(nameFormat, count).isEmpty()) {
			throw new IllegalArgumentException("\"Unable to create an object, this a unique name already exists" + chekAutputNameItem(nameFormat, count));
		}

		generateItem(nameFormat, catrgory, count).forEach(b -> {

			try {
				addItemSimple(b);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	public void makeItemWithList(String nameFormat, List<Category> catrgorys, int count) throws SQLException {
		if (!chekAutputNameItem(nameFormat, count).isEmpty()) {
			throw new IllegalArgumentException("Unable to create an object, this a unique name already exists" + chekAutputNameItem(nameFormat, count));
		}
		generateItemFromListCatrgory(nameFormat, catrgorys, count).forEach(b -> {
			try {
				addItemSimple(b);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public int getIdCategory(String name) {
		return NameIdCategory.get(name);
	}

	public int getIdItem(String name) {
		return NameIdItem.get(name);
	}

	private static int toIndex(int index) {
		return index  - 1;
	}

	public static void main(String[] args) throws FileNotFoundException, SQLException {
		//reinit();
		DatabaseInitializer hh = new DatabaseInitializer();
		//hh.makeCatygory("raccoon", 2, 5);
		// hh.makeCatygory("cat", 2, 5);
		// hh.makeCatygory("giraff", 2, 5);

		hh.makeItemWithList("bbb", hh.generateCategorys("raccoon", 2, 5), 5);
		// RanSkript.getCauntCategoty ();
		// System.out.println(RanSkript.getIdCategory("raccoon2"));
		// System.out.println(RanSkript.getIdItem("bbb7"));
	}
}
