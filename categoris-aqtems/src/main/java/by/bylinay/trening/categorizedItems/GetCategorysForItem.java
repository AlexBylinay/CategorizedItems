package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;


public class GetCategorysForItem {
	
	public static HashMap<Integer, Category> toCategoryMap (Connection connection) throws SQLException {
		
	java.sql.Statement statement = connection.createStatement();
	ResultSet rs = statement.executeQuery("SELECT  * FROM category ORDER BY id asc");
	HashMap<Integer, Category> categorysforItem = new HashMap<Integer, Category>();
	while (rs.next()) {
	
		int id = rs.getInt(1);
		String name = rs.getString(2);
		int color = rs.getInt(3);
		Date date = rs.getDate(4);
		Category category = new CategoryImpl(id, name, color, date);
		categorysforItem.put(id, category);
	}
	rs.close();
	connection.close();
	return categorysforItem;
}

}
