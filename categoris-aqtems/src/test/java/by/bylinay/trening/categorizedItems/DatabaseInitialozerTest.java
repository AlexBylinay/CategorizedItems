package by.bylinay.trening.categorizedItems;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;



class DatabaseInitialozerTest {
	private DatabaseInitializer databaseInitializer;
	private ConnectorAndStatement statement;
	Launcher check;
	@SuppressWarnings("static-access")
	@Test
	void test() throws SQLException, ClassNotFoundException, FileNotFoundException {
		Connector c = new Connector();
		Lkk g = new Lkk();
		g.executeScript( (Connection) c.connectionForDatabaseCategcorizedItemstru());
	//	databaseInitializer.reinit();
		statement.makeConnectionFndStatement().executeQuery("SELECT  * FROM category, item ");
		//check.printCatygory();
		check.printItemFull();
		// statement.executeQuery("raccoons ");

	}
}