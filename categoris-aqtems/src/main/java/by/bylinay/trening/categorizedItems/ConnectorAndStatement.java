package by.bylinay.trening.categorizedItems;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorAndStatement {

	public static java.sql.Statement makeConnectionFndStatement() throws SQLException, ClassNotFoundException {

		// Class.forName(DRIVER);
		String url = "jdbc:mysql://localhost:3306/categorizedItemstru";
		String user = "root";
		String password = "kapli123";
		java.sql.Connection connection = DriverManager.getConnection(url, user, password);
		java.sql.Statement statement = (java.sql.Statement) connection.createStatement();

		return statement;
	}

}
