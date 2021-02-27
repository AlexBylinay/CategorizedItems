package by.bylinay.trening.categorizedItems;

import java.sql.SQLException;
import java.util.List;

public class CheñkAndPrint {
	static CategoryService category = new CategoryService();
	static ItemService item = new ItemService();
	static ItemFullService itemFull = new ItemFullService();
	static Connector connection = new Connector();
	static final String FORMAT_FOR_PRINT_FOR_CATEGORY = " %d.  %12s %2d. %s \n ";
	static final String FORMAT_FOR_PRINT_FOR_ITEM = " %d.  %12s %2d. %s \n ";
	static final String FORMAT_FOR_PRINT_FOR_ITEM_FULL = " %d.  %10s %6d. %12s  %10d.  %10s %6d. %12s \n ";

	public static List<Category> checkCatygory() throws ClassNotFoundException, SQLException {
		return category.getAll(connection.connectionForDatabaseCategcorizedItemstru());
	}

	public static void printCatygory() throws ClassNotFoundException, SQLException {
		for (Category category : checkCatygory()) {
			System.out.printf(FORMAT_FOR_PRINT_FOR_CATEGORY, category.getId(), category.getName(), category.getColor(),
					category.getDate());
		}

	}

	public static List<Item> checkItem() throws ClassNotFoundException, SQLException {
		return item.getAll(connection.connectionForDatabaseCategcorizedItemstru());
	}

	public static void printItem() throws ClassNotFoundException, SQLException {
		for (Item item : checkItem()) {
			System.out.printf(FORMAT_FOR_PRINT_FOR_ITEM, item.getId(), item.getName(), item.catygoryId(),
					item.getDate());
		}
	}
	
	public static List<Item> checkItemFull() throws ClassNotFoundException, SQLException {
		return itemFull.getAllFull(connection.connectionForDatabaseCategcorizedItemstru());
	}
	
	public static void printItemFull() throws ClassNotFoundException, SQLException {
		for (Item item : checkItemFull()) {
			Category category = item.getCategory();
			System.out.printf( FORMAT_FOR_PRINT_FOR_ITEM_FULL, item.getId(), item.getName(),
					item.catygoryId(), item.getDate(), category.getId(), category.getName(), category.getColor(),
					category.getDate());
		}

	}
	}
 