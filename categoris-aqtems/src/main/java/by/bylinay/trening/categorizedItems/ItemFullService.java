package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFullService {

	static List<Item> getAllFull(Connection connection) throws SQLException, ClassNotFoundException {

		List<Item> allItem = new ArrayList<Item>();

		java.sql.Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT  * FROM item ORDER BY id asc");
		while (rs.next()) {
			allItem.add(toItem(rs));
		}
		rs.close();
		connection.close();
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
		GetCategorysForItem categoryMake = new GetCategorysForItem();
		Connector connection = new Connector();
		HashMap<Integer, Category> categorysforItem = categoryMake
				.toCategoryMap(connection.connectionForDatabaseCategcorizedItemstru());
		return categorysforItem.get(key);
	}

}
