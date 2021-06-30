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
import by.bylinay.trening.categorizedItems.Entity;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class DatabaseInitializer {

	private String billetForInsert1 = "INSERT INTO ";
	private String billetForInsert2 = " VALUES";
	private String billetForChec1 = "SELECT name_ FROM ";
	private String billetForChec2 = " WHERE name_  IN (";
	
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

	
	public void makeCatygory(String nameFormat, int color, int count) throws SQLException {
		List<String> names = createListName(nameFormat, count);
		Category category = new CategoryImpl(nameFormat,color);
		List<String> namesfromDatabase = getNamesfromDatabase(getSkriptForChekName(getNamesForCheсk(names),category));
		if (!namesfromDatabase.isEmpty()) {
			throw new IllegalArgumentException(
					"Unable to create an object, this a unique name already exists " + namesfromDatabase);
		}
		createAndSaveCategory(names, color);
	}

	private List<String> createListName(String nameFormat, int count) {
		List<String> names = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			String name = (String.format(nameFormat + i));
			names.add(name);
		}
		return names;
	}
	
	private List<String> getNamesfromDatabase(String skript) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skript);
		List<String> names = new ArrayList<String>();
		while (rs.next()) {
			String nameRs = rs.getString(1);
			names.add(nameRs);
		}
		// System.out.println(names);
		return names;
	}
	

	private String getSkriptForChekName(String name, Entity entity) {
		return billetForChec1 + entity.getTableName() + billetForChec2 + "'" + name + ");";	
	}

	private String getNamesForCheсk(List<String> names) throws SQLException {
		String allnames = null;
		allnames = names.get(0) + "'";
		// for (String name : names) {
		for (int i = 1; i < names.size(); i++) {
			String name = names.get(i);
			allnames = allnames + "," + "'" + name + "'";
		}
		return allnames;
	}
		
	private void createAndSaveCategory(List<String> names, int color) throws SQLException {
		for (Category category : createCategorys(names, color)) {
			saveCategory(category);
		}
	}
	
	private List<Category> createCategorys(List<String> names, int color) {
		List<Category> categorys = new ArrayList<>();
		names.forEach(b -> {
			Category category = new CategoryImpl(b, color);
			categorys.add(category);
		});
		return categorys;
	}
	
	private int saveCategory(Category catrgory) throws SQLException {
		PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru()
				.prepareStatement((getScriptForSave(catrgory)), Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
		}
		return generatedKey;
	}
	
	private String getScriptForSave(Entity entity) {
		return (billetForInsert1 + entity.getTableName() + " (" + geTtitlesColumns(entity) + ")"
				+ billetForInsert2 + " (" + "'" + (String) entity.getColumnsValue()[0]+ "'" + ","
				+ (int) entity.getColumnsValue()[1] + "," + "'" + (String) entity.getColumnsValue()[2] + "'" + ")" + ";");
	}
	
	private String geTtitlesColumns(Entity entity) {
		return String.join(", ", entity.getNamesColumns());
	}
	
	public void makeItem(String nameFormat, Category catrgory, int count) throws SQLException {
		List<String> names = createListName(nameFormat, count);
		Item item = new SimpleItem(nameFormat, catrgory.getId());
		List<String> namesAfterChek = getNamesfromDatabase(getSkriptForChekName(getNamesForCheсk(names), item));
		if (!namesAfterChek.isEmpty()) {
			throw new IllegalArgumentException(
					"\"Unable to create an object, this a unique name already exists" + namesAfterChek);
		}
		createAndSaveItem(names, catrgory);
	}

	private void createAndSaveItem(List<String> names, Category catrgory) throws SQLException {
		for (Item item : createItem(names, catrgory)) {
			SaveItem(item);
		}
	}
		
	private List<Item> createItem(List<String> names, Category catrgory) {
		List<Item> items = new ArrayList<>();
		names.forEach(b -> {
			Item item = new SimpleItem(b, catrgory.getId());
			items.add(item);
		});
		return items;
	}
		
	private void SaveItem(Item item) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(getScriptForSave(item));
	}
	
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		reinit();
		DatabaseInitializer hh = new DatabaseInitializer();
		hh.makeCatygory("raccoon", 2, 8);
		Category category = new CategoryImpl(2, "raccoon", 2);
		//Item item = new SimpleItem("kkk", 5, category);
		hh.makeItem("bbb", category, 8);

//
		
		//System.out.println(hh.getScriptForSave(item));
		System.out.println(hh.getScriptForSave(category));
		System.out.println(hh.getSkriptForChekName("jjj",category));
       //  hh.SaveItem5();



	}

}
