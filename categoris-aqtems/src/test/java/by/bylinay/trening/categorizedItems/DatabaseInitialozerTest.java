package by.bylinay.trening.categorizedItems;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;



class DatabaseInitialozerTest {
	private DatabaseInitializer databaseInitializer;
	private ConnectorAndStatement statement;
	Launcher check;
	@SuppressWarnings("static-access")
	@Test
	void test() throws SQLException, ClassNotFoundException {

		databaseInitializer.reinit();
		statement.makeConnectionFndStatement().executeQuery("SELECT  * FROM category, item ");
		//check.printCatygory();
		check.printItemFull();
		// statement.executeQuery("raccoons ");

	}
}