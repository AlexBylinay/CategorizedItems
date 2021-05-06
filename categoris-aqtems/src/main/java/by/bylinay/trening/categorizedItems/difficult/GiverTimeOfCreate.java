package by.bylinay.trening.categorizedItems.difficult;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiverTimeOfCreate {

	public static String givTime() throws SQLException {
		String URL = "jdbc:mysql://localhost:3306/categorizedItemsTru";
		String USER = "root";
		String PASSWORD = "kapli123";
		java.sql.Connection connection = null;
		String dateAndTime = "0";
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("no connection to the database categorizedItemsTru");
		}

		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(
					"SELECT create_time FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'categorizedItemsTru' AND table_name = 'category' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no connection ");
		}
		while (resultSet.next()) {
			dateAndTime = resultSet.getString(1);
		}
		return dateAndTime;
	}

}