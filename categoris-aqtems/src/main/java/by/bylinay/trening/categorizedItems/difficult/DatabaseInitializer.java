package by.bylinay.trening.categorizedItems.difficult;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mysql.jdbc.PreparedStatement;
import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;
import by.bylinay.trening.categorizedItems.Connector;
import by.bylinay.trening.categorizedItems.ConnectorAndStatement;
import by.bylinay.trening.categorizedItems.Item;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class DatabaseInitializer {

	private String billetForInsert1 = "INSERT INTO ";
	private String billetForInsert2 = " VALUES";
	private String chekQueryCategory = " SELECT name_ FROM     category  WHERE name_  IN (";
	private String chekQueryItem = " SELECT name_ FROM item WHERE name_  IN (";

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
		return (billetForInsert1 + CategoryImpl.getGeneralInsert() + billetForInsert2 + " (" + "'" + catrgory.getName()
				+ "'" + "," + catrgory.getColor() + "," + "'" + (catrgory.getDate()) + "'" + ")" + ";");
	}

	private String skriptForItemWSimple(Item item) {
		return (billetForInsert1 + SimpleItem.getGeneralInsert() + billetForInsert2 + "(" + "'" + item.getName() + "'"
				+ "," + item.getcatygoryId() + "," + "'" + (item.getDate()) + "'" + ")" + ";");
	}

	private String getSkriptForChekName(String name) {
		return (chekQueryCategory + "'" + (name + ");"));
	}

	private String getSkriptForChekNameItem(String name) {
		return (chekQueryItem + "'" + (name + ");"));
	}

	private int saveCategory(Category catrgory) throws SQLException {
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

	

	private String getNameForQuery(List<String> names) throws SQLException {
		String allnames = null;
		allnames = names.get(0) + "'";
		// for (String name : names) {
		for (int i = 1; i < names.size(); i++) {
			String name = names.get(i);
			allnames = allnames + "," + "'" + name + "'";
		}
		return allnames;
	}

	/////////////////////////////////////////////////////////////////////////

	private List<String> getNamefromDatabase(String skript) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skript);
		List<String> names = new ArrayList<String>();
		while (rs.next()) {
			String nameRs = rs.getString(1);
			names.add(nameRs);
		}
		// System.out.println(names);
		return names;
	}

	private List<String> createListName(String nameFormat, int count) {
		List<String> names = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			String name = (String.format(nameFormat + i));
			names.add(name);
		}
		return names;
	}

	private List<Category> createCategorys(List<String> names, int color) {
		List<Category> categorys = new ArrayList<>();
		names.forEach(b -> {
			Category category = new CategoryImpl(b, color);
			categorys.add(category);
		});
		return categorys;
	}
	
	private void createAndSaveCategory(List<String> names, int color) throws SQLException {
		for (Category category : createCategorys(names, color)) {
			saveCategory(category);
		}
	}

	public void makeCatygory(String nameFormat, int color, int count) throws SQLException {
		List<String> names = createListName(nameFormat, count);
		List<String> namesAfterChek = getNamefromDatabase(getSkriptForChekName(getNameForQuery(names)));
		if (!namesAfterChek.isEmpty()) {
			throw new IllegalArgumentException(
					"Unable to create an object, this a unique name already exists " + namesAfterChek);
		}
		createAndSaveCategory(names, color);
	}


	
	
	private void SaveItem(Item item) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItemWSimple(item));
	}
	
	private List<Item> createItem(List<String> names, Category catrgory) {
		List<Item> items = new ArrayList<>();
		names.forEach(b -> {
			Item item = new SimpleItem(b, catrgory.getId());
			items.add(item);
		});
		return items;
	}

	private void createAndSaveItem(List<String> names, Category catrgory) throws SQLException {
		for (Item item : createItem(names, catrgory)) {
			SaveItem(item);
		}

	}

	public void makeItem(String nameFormat, Category catrgory, int count) throws SQLException {
		List<String> names = createListName(nameFormat, count);
		List<String> namesAfterChek = getNamefromDatabase(getSkriptForChekNameItem(getNameForQuery(names)));
		if (!namesAfterChek.isEmpty()) {
			throw new IllegalArgumentException(
					"\"Unable to create an object, this a unique name already exists" + namesAfterChek);
		}
		createAndSaveItem(names, catrgory);
	}




	private <T> int determinant(T val) {
		int value = 0;
		Category category = new CategoryImpl(0, null, 0);
		Item item = new SimpleItem(null, 0, null);
		if (val.getClass().equals(category.getClass())) {
			value = 1;
		}
		if (val.getClass().equals(item.getClass())) {
			value = 2;
		}
		return value;
	}

	

	private <T> String Skript(T val) {
		String skript = null;
		Category category = new CategoryImpl(0, null, 0);
		Item item = new SimpleItem(null, 0, null);
		if (val.getClass().equals(category.getClass())) {
			skript = billetForInsert1 + CategoryImpl.getGeneralInsert() + billetForInsert2 + " (" + "'"
					+ ((CategoryImpl) val).getName() + "'" + "," + ((CategoryImpl) val).getColor() + "," + "'"
					+ (((CategoryImpl) val).getDate()) + "'" + ")" + ";";
		}
		if (val.getClass().equals(item.getClass())) {
			skript = (billetForInsert1 + SimpleItem.getGeneralInsert() + billetForInsert2 + "(" + "'"
					+ ((SimpleItem) val).getName() + "'" + "," + ((SimpleItem) val).getcatygoryId() + "," + "'"
					+ (((SimpleItem) val).getDate()) + "'" + ")" + ";");
		}
		return skript;
	}



	public static void main(String[] args) throws SQLException, FileNotFoundException {

		// String i = "SELECT name_ FROM category WHERE name_ = 'raccoon3';";
		 reinit();
		DatabaseInitializer hh = new DatabaseInitializer();

		 hh.makeCatygory("raccoon", 2, 8);

		Category category = new CategoryImpl(2, "raccoon", 2);
		Item item = new SimpleItem("kkk", 5, category);
		System.out.println(hh.Skript(category));
		System.out.println(hh.Skript(item));
		// System.out.println(hh.skriptForItemWSimple(item));

		// System.out.println(hh.determinant(item));
		// System.out.println(hh.determinant(category));
		// System.out.println(hh.determinant("jhjh"));
		// hh.makeItem("bbb", category, 8);

		// System.out.println (hh.skriptForChekCategory2(
		// hh.getIdenticalCategoryNamesfromDatabase2(hh.createListName("raccoon", 8))));
		// hh.getNamefromDataDaseCategory2(hh.skriptForChekCategory2(
		// hh.getIdenticalCategoryNamesfromDatabase2(hh.createListName("raccoon", 8))));
		// hh.getNamefromDataDaseCategory2(i);
		// System.out.println
		// (hh.getNamefromDataDaseCategory2(hh.skriptForChekCategory2(
		// hh.getIdenticalCategoryNamesfromDatabase2(hh.))));
		// System.out.println (hh.getNamefromDataDaseCategory2());
		// hh.getNamefromDatabaseCategory2((hh.getSkriptForChekName(hh.getNamefor(hh.createListName("raccoon",
		// 8)))));

	}

}

/*
 * public void makeCatygory(String nameFormat, int color, int count) throws
 * SQLException { generateCategorys(nameFormat, color, count).forEach(b -> {
 * 
 * try { if (chekAutputNameCategory(b.getName())) { throw new
 * IllegalArgumentException("Unable to create an object, this a unique name already exists "
 * + b.getName()); } } catch (SQLException e) { e.printStackTrace(); } });
 * generateCategorys(nameFormat, color, count).forEach(b -> { try {
 * addCategory(b); } catch (SQLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }); }
 */

/*
 * private List<String> chekAutputNameCategory(String nameFormat, int count)
 * throws SQLException { List<String> names = new ArrayList<>(); List<String>
 * namesOutDataBase = new ArrayList<>(); for (int i = 1; i <= count; i++) {
 * String name = String.format(nameFormat + i); names.add(name); }
 * names.forEach(b -> { try { if (getNameForDataDaseCategory(b) != null) {
 * namesOutDataBase.add(getNameForDataDaseCategory(b)); } } catch (SQLException
 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }); return
 * namesOutDataBase; }
 * 
 * 
 * private List<String> chekAutputNameItem(String nameFormat, int count) throws
 * SQLException { List<String> names = new ArrayList<>(); List<String>
 * namesOutDataBase = new ArrayList<>(); for (int i = 1; i <= count; i++) {
 * String name = String.format(nameFormat + i); names.add(name); }
 * names.forEach(b -> { try { if (getNameForDataDaseItem(b) != null) {
 * namesOutDataBase.add(getNameForDataDaseItem(b)); } } catch (SQLException e) {
 * e.printStackTrace(); } }); return namesOutDataBase; }
 */
/*
 * private void addItem(Item item, Category catrgory) throws SQLException {
 * ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(
 * skriptForItem(item, catrgory)); }
 * 
 * private void addItemtemWhithId(Item item) throws SQLException {
 * PreparedStatement ps = (PreparedStatement)
 * Connector.connectionForDatabaseCategcorizedItemstru()
 * .prepareStatement(skriptForItemWhithId(item, item.getcatygoryId()),
 * Statement.RETURN_GENERATED_KEYS); ps.executeUpdate();
 * 
 * ResultSet rs = ps.getGeneratedKeys(); int generatedKey = 0; if (rs.next()) {
 * generatedKey = rs.getInt(1); } } private String skriptForItem(Item item,
 * Category catrgory) { return (billetItem + "(" + "'" + item.getName() + "'" +
 * "," + catrgory.getId() + "," + "'" + (item.getDate()) + "'" + ")" + ";"); }
 * 
 * private String skriptForItemWhithId(Item item, int catrgoryId) { return
 * (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'"
 * + (item.getDate()) + "'" + ")" + ";"); }
 * 
 * private String skriptForItem(Item item, Category catrgory) { return
 * (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgory.getId() + ","
 * + "'" + (item.getDate()) + "'" + ")" + ";"); }
 * 
 * private String skriptForItemWhithId(Item item, int catrgoryId) { return
 * (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'"
 * + (item.getDate()) + "'" + ")" + ";"); }
 * 
 * private String skriptForItem(Item item, Category catrgory) { return
 * (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgory.getId() + ","
 * + "'" + (item.getDate()) + "'" + ")" + ";"); }
 * 
 * private String skriptForItemWhithId(Item item, int catrgoryId) { return
 * (billetItem + "(" + "'" + item.getName() + "'" + "," + catrgoryId + "," + "'"
 * + (item.getDate()) + "'" + ")" + ";"); } private Map<String, Integer>
 * NameIdCategory = new HashMap<String, Integer>(); private Map<String, Integer>
 * NameIdItem = new HashMap<String, Integer>();
 *
 *
 * private List<String> chekAutputNameItem(List<String> names) throws
 * SQLException {
 * 
 * List<String> namesOutDataBase = new ArrayList<>(); for (String name:names) {
 * if(getNamefromDataDaseItem(name) != null) {
 * namesOutDataBase.add(getNamefromDataDaseItem(name)); }} return
 *
 * namesOutDataBase; }
 * 
 * 
 * private List<Item> generateItemFromListCatrgory(String nameFormat,
 * List<Category> catrgorys, int count) { List<Item> items = new ArrayList<>();
 * if (catrgorys.size() < count) {
 * System.out.println("insufficient number of Categories in list"); } else { for
 * (int i = 1; i <= count; i++) { Item item = new
 * SimpleItem(String.format(nameFormat + i), catrgorys.get(toIndex(i)).getId());
 * items.add(item); } } return items; }
 * 
 * private List<String> getIdenticalCategoryNamesfromDatabase(List<String>
 * names) throws SQLException { List<String> namesOutDataBase = new
 * ArrayList<>(); String allnames = null; for (String name : names) { allnames =
 * allnames + name; }
 * 
 * 
 * for (String name : names) { if (getNamefromDataDaseCategory(name) != null) {
 * namesOutDataBase.add(getNamefromDataDaseCategory(name)); } } return
 * namesOutDataBase; }
 * 
 * 
 * private String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
 * private String chekItem = "SELECT name_ FROM item WHERE name_ = ";
 * 
 * 
 * private String skriptForChekCategory(String name) { return (chekCategory +
 * "'" + (name + "'" + ";")); }
 * 
 * private String skriptForChekItem(String name) { return (chekItem + "'" +
 * (name + "'" + ";")); }
 * 
 * 
 * 
 * private String getNamefromDataDaseCategory(String name) throws SQLException {
 * ResultSet rs =
 * ConnectorAndStatement.makeConnectionFndStatement().executeQuery(
 * skriptForChekCategory(name)); String nameQuery = null; while (rs.next()) {
 * nameQuery = rs.getString(1); } return nameQuery; }
 * 
 * private String getNamefromDataDaseItem(String name) throws SQLException {
 * ResultSet rs =
 * ConnectorAndStatement.makeConnectionFndStatement().executeQuery(
 * skriptForChekItem(name)); String nameQuery = null; while (rs.next()) {
 * nameQuery = rs.getString(1); } return nameQuery; } private List<String>
 * getIdenticalItemNamesfromDatabase(List<String> names) throws SQLException {
 * List<String> namesOutDataBase = new ArrayList<>(); for (String name : names)
 * { if (getNamefromDataDaseItem(name) != null) {
 * namesOutDataBase.add(getNamefromDataDaseItem(name)); } } return
 * namesOutDataBase; }
 * 
 * private int toIndex(int index) { return index - 1; }
 * 
 * /* private boolean chekAutputNameCategory(String name) throws SQLException {
 * ResultSet rs =
 * ConnectorAndStatement.makeConnectionFndStatement().executeQuery(
 * skriptForChekCategory(name)); String nameQuery = null; while (rs.next()) {
 * nameQuery = rs.getString(1); } return name.equals(nameQuery); }
 */
