package by.bylinay.trening.categorizedItems;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class Launcher {
	

	private static DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
	//	databaseInitializer.reinit();
		Connector c = new Connector();
		Lkk g = new Lkk();
		g.executeScript( (Connection) c.connectionForDatabaseCategcorizedItemstru());
		
		//printCatygory();
		// printItem();
		// printItemFull();
	}

	public static void printCatygory() throws ClassNotFoundException, SQLException {
		 CategoryService allCategory = new CategoryService();
		for (Category category : allCategory.getAll()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", category.getId(), category.getName(), category.getColor(),
					category.getDate());
		}

	}

	static  void printItem() throws ClassNotFoundException, SQLException {
		ItemService Allitem = new ItemService();
		for (Item item : Allitem.getAllFull()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", item.getId(), item.getName(), item.catygoryId(),
					item.getDate());
		}
	}

	public static void printItemFull() throws ClassNotFoundException, SQLException {
		ItemService Allitem = new ItemService();
		for (Item item : Allitem.getAllFull()) {
			Category category = item.getCategory();
			System.out.printf(" %d.  %10s %6d. %12s  %10d.  %10s %6d. %12s \n ", item.getId(), item.getName(),
					item.catygoryId(), item.getDate(), category.getId(), category.getName(), category.getColor(),
					category.getDate());

		}
	}

}


