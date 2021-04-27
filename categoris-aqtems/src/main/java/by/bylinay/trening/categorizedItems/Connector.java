package by.bylinay.trening.categorizedItems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private  final static String DRIVER = "com.mysql.jdbc.Driver";

	public static  Connection connectionForDatabaseCategcorizedItemstru()

			throws SQLException, ClassNotFoundException {

		Class.forName(DRIVER);
		String url = "jdbc:mysql://localhost:3306/categorizedItemstru";
		String user = "root";
		String password = "kapli123";

		java.sql.Connection connection = DriverManager.getConnection(url, user, password);
		return connection;

	}

}
