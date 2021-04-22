package by.bylinay.trening.categorizedItems;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFullService {
	// public static ConnectionAndStatement statement = new Statement();

	static List<Item> getAllFull() throws SQLException, ClassNotFoundException {

		List<Item> allItem = new ArrayList<Item>();

		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement()
				.executeQuery("SELECT  * FROM item ORDER BY id asc");
		while (rs.next()) {
			allItem.add(toItem(rs));
		}
		rs.close();
		ConnectorAndStatement.makeConnectionFndStatement().close();
		return allItem;

	}

	private static Item toItem(ResultSet rs) throws SQLException, ClassNotFoundException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int catygoryId = rs.getInt(3);
		Date date = rs.getDate(4);
		Category category = getCategorysforItem(catygoryId);
		Item item = new SimpleItem(id, name, catygoryId, date, category);

		return item;
	}

	public static Category getCategorysforItem(int key) throws SQLException, ClassNotFoundException {
		HashMap<Integer, Category> categorysforItem = CategoryService.toCategoryMap();
		return categorysforItem.get(key);
	}

}
