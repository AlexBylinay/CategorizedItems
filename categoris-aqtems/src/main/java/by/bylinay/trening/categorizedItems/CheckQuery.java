package by.bylinay.trening.categorizedItems;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Statement;

public class CheckQuery {

	public static void main(String[] arr)

			throws SQLException {

		String URL = "jdbc:mysql://localhost:3306/categorizedItemsTru";
		String USER = "root";
		String PASSWORD = "kapli123";

		java.sql.Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT  * FROM category  order by id ");

		while (resultSet.next()) {

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			int color = resultSet.getInt(3);
			String date = resultSet.getString(4);
			
			/*
			 * int id2 = resultSet.getInt(5); String name2 = resultSet.getString(6); String
			 * category = resultSet.getString(7); String date2 = resultSet.getString(8);
			 */
			Category category = new CategoryImpl(id, name, color, date);

			// System.out.printf( " %d. %7s %2d. -%s %14d. %s %-10s. %s. \n ", id, name,
			// color, date, id2, name2, category, date2 );
			System.out.println(category.getId());
			System.out.println(category.getName());

		}
		resultSet.close();
		connection.close();
	}
}