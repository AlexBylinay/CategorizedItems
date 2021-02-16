package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryService {

	static final String FORMAT_FOR_PRINT = " %d.  %12s %2d. %s \n ";

	public static List<Category> getAll(Connection connection) throws SQLException {
		List<Category> allCategory = new ArrayList<Category>();
		java.sql.Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT  * FROM category ORDER BY id asc");
		while (resultSet.next()) {
			allCategory.add(toCategory(resultSet));
		}
		resultSet.close();
		connection.close();
		return allCategory;

	}

	private static Category toCategory(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int color = rs.getInt(3);
		Date date = rs.getDate(4);
		return new CategoryImpl(id, name, color, date);

	}

	public static HashMap<Integer, Category> toCategoryMap(Connection connection) throws SQLException {
		java.sql.Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT  * FROM category ORDER BY id asc");
		HashMap<Integer, Category> categorysforItem = new HashMap<Integer, Category>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int color = rs.getInt(3);
			Date date = rs.getDate(4);
			Category categoryMap = new CategoryImpl(id, name, color, date);
			categorysforItem.put(id, categoryMap);
		}
		rs.close();
		connection.close();
		return categorysforItem;
	}

	public static List<String> outputsToConsoleAllCategoryFromList(List<Category> categorys) {
		List<String> all = new ArrayList<String>();
		String outpun = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Category category : categorys) {

			outpun = Integer.toString(category.getId()) + category.getName() + Integer.toString(category.getColor())
					+ df.format(category.getDate());
			all.add(outpun);
		}
		return all;
	}

	public static int size(List<String> all) throws ClassNotFoundException, SQLException {
		Connector connection1 = new Connector();
		return size(
				outputsToConsoleAllCategoryFromList(getAll(connection1.connectionForDatabaseCategcorizedItemstru())));

	}

	public static void check(List<Category> categorys, HashMap<Integer, Category> categorysforItem) {
		for (Category category : categorys) {
			int key = category.getId();
			// System.out.println (key);
			Category category2 = categorysforItem.get(key);
			System.out.printf(" %d.  %12s %2d. %s \n ", category2.getId(), category2.getName(), category2.getColor(),
					category.getDate());
		}
	}

}
