package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

	static List<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {

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

	private static Item toItem(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int catygoryId = rs.getInt(3);
		Date date = rs.getDate(4);
		Item item = new SimpleItem(id, name, catygoryId, date);

		return item;

	}

}
