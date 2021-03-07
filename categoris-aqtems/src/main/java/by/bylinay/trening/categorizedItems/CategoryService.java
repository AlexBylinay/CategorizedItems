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
	
	public static List<Category> getAll() throws SQLException, ClassNotFoundException {
		List<Category> allCategory = new ArrayList<Category>();
		ConnectorAndStatement statement = new ConnectorAndStatement();
		ResultSet rs = statement.makeConnectionFndStatement().executeQuery("SELECT  * FROM category ORDER BY id asc");
		while (rs.next()) {
			allCategory.add(toCategory(rs));
		}
		rs.close();
		statement.makeConnectionFndStatement().close();
		return allCategory;
	}

	private static Category toCategory(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int color = rs.getInt(3);
		Date date = rs.getDate(4);
		return new CategoryImpl(id, name, color, date);
	}

	public static HashMap<Integer, Category> toCategoryMap()
			throws SQLException, ClassNotFoundException {
		List<Category> allCategory = getAll();
		HashMap<Integer, Category> categorysforItem = new HashMap<Integer, Category>();
		for (Category category : allCategory) {
			int key = category.getId();
			categorysforItem.put(key, category);

		}
		return categorysforItem;
	}

	/*public static List<String> outputsToConsoleAllCategoryFromList(List<Category> categorys) {
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
		Connector connection = new Connector();
		return size(outputsToConsoleAllCategoryFromList(getAll()));

	}*/

}
