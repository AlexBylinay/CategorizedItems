package by.bylinay.trening.categorizedItems;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryService {
	static final String FORMAT_FOR_PRINT = " %d.  %12s %2d. %s \n ";
	
	public static List<Category> getAll() throws SQLException, ClassNotFoundException {
		List<Category> allCategory = new ArrayList<Category>();
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery("SELECT  * FROM category ORDER BY id asc");
		while (rs.next()) {
			allCategory.add(toCategory(rs));
		}
		rs.close();
		ConnectorAndStatement.makeConnectionFndStatement().close();
		return allCategory;
	}

	private static Category toCategory(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int color = rs.getInt(3);
		String date = rs.getString(4);
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

}
