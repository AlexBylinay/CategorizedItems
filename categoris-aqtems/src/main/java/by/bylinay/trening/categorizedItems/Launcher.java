package by.bylinay.trening.categorizedItems;

import java.sql.SQLException;

public class Launcher {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Connector connection = new Connector();
		ItemService item = new ItemService();
		CategoryService category = new CategoryService();

		// item.outputsToConsoleAllItensFromList(item.downloadAllItemFromDatabase(connection.connectionForDatabaseCategcorizedItemstru()
		// ));
		// category.outputsToConsoleAllCategoryFromList(category.downloadAllCategotyFromDatabase(connection.connectionForDatabaseCategcorizedItemstru()));

		//

		 category.check(category.getAll(connection.connectionForDatabaseCategcorizedItemstru()),
		 category.toCategoryMap(connection.connectionForDatabaseCategcorizedItemstru()));

		//category.toCategoryMap(connection.connectionForDatabaseCategcorizedItemstru());
		item.outputsToConsoleAllItensFromList(item.getAll(connection.connectionForDatabaseCategcorizedItemstru()));
		 //item.getAll(connection.connectionForDatabaseCategcorizedItemstru());
		 //item.outputsToConsoleAllItensFromList(item.getAll(connection.connectionForDatabaseCategcorizedItemstru()));

		 //(category.outputsToConsoleAllCategoryFromList(category.getAll(connection.connectionForDatabaseCategcorizedItemstru()))).
		// category.size(category.outputsToConsoleAllCategoryFromList(category.getAll(connection.connectionForDatabaseCategcorizedItemstru())));

	}
}
