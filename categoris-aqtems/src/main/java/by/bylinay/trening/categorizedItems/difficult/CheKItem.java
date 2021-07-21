package by.bylinay.trening.categorizedItems.difficult;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale.Category;

import com.mysql.jdbc.Statement;

import by.bylinay.trening.categorizedItems.Item;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class CheKItem {


	public static void main(String[] arr)

			throws SQLException {

		String URL = "jdbc:mysql://localhost:3306/categorizedItemstru";
		String USER = "root";
		String PASSWORD = "kapli123";

		java.sql.Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		Statement statement = (Statement) connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT  * FROM item; ");
		
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int catygoryId = rs.getInt(3);
			String date = rs.getString(4);
			
			/*
			 * int id2 = resultSet.getInt(5); String name2 = resultSet.getString(6); String
			 * category = resultSet.getString(7); String date2 = resultSet.getString(8);
			 */
			Item item = new SimpleItem (id, name, catygoryId, date);
		
			// System.out.printf( " %d. %7s %2d. -%s %14d. %s %-10s. %s. \n ", id, name,
			// color, date, id2, name2, category, date2 );
			
			System.out.println(((SimpleItem) item).getName());
			System.out.println(item.getId());
			System.out.println(item.getCategoryId());
		
		}
		
		rs.close();
		connection.close();
	}
}