package by.bylinay.trening.categorizedItems;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemService {

	static ResultSet resultSet() throws SQLException, ClassNotFoundException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement()
				.executeQuery("SELECT  * FROM item ORDER BY id asc");
		ConnectorAndStatement.makeConnectionFndStatement().close();
		return rs;
	}

	public static List<Item> getAll() throws SQLException, ClassNotFoundException {
		ResultSet rs = ItemService.resultSet();
		List<Item> allItem = new ArrayList<Item>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int catygoryId = rs.getInt(3);
			String date = rs.getString(4);
			Item item = new SimpleItem(id, name, catygoryId, date);
			allItem.add(item);
		}
		rs.close();
		return allItem;

	}

	public static List<Item> getAllFull() throws SQLException, ClassNotFoundException {
		ResultSet rs = ItemService.resultSet();
		List<Item> allFullItem = new ArrayList<Item>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int catygoryId = rs.getInt(3);
			String date = rs.getString(4);
			
			Category category = getCategorysforItem(catygoryId);
			Item item = new SimpleItem(id, name, catygoryId, date, category);
			allFullItem.add(item);
		}
		rs.close();
		return allFullItem;
	}

	public static Category getCategorysforItem(int key) throws SQLException, ClassNotFoundException {
		HashMap<Integer, Category> categorysforItem = CategoryService.toCategoryMap();
		return categorysforItem.get(key);
	}

}
