package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemService {
	static List<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
		List<Item> allItem = new ArrayList<Item>();
		Category category = new CategoryImpl(0, null, 0, null);
		Connector connection1 = new Connector();
		CategoryService category1 = new CategoryService();
		java.sql.Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT  * FROM item ORDER BY id asc");
		HashMap<Integer, Category> categorysforItem = new HashMap<Integer, Category>();
		categorysforItem = category1.toCategoryMap(connection1.connectionForDatabaseCategcorizedItemstru());
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			int catygoryId = resultSet.getInt(3);
			Date date = resultSet.getDate(4);
			category = categorysforItem.get(catygoryId);
			Item itemm = new SimpleItem(id, name, catygoryId, date, category);
			allItem.add(itemm);

			// System.out.println (idCategory);
			// System.out.println (category.getId());
			// System.out.println (category.getName());

		}
		resultSet.close();
		connection.close();
		return allItem;
	}

	static void outputsToConsoleAllItensFromList(List<Item> allItem) {
		for (Item item : allItem) {
			Category category = item.getCategory();
			System.out.printf(" %d.  %10s %6d. %12s  %10d.  %10s %6d. %12s \n ", item.getId(), item.getName(),
					item.catygoryId(), item.getDate(), category.getId(), category.getName(), category.getColor(),
					category.getDate());
		}

	}

}
