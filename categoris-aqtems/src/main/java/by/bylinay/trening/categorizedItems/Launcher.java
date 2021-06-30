package by.bylinay.trening.categorizedItems;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Launcher {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
	

		printCatygory();
		System.out.println("|||||||||||||");
		printItem();
	    printItemFull();
	}

	public static void printCatygory() throws ClassNotFoundException, SQLException {
		for (Category category : CategoryService.getAll()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", category.getId(), ((CategoryImpl) category).getName(), category.getColor(),
					((CategoryImpl)category).getDate());
		}
	}

	static void printItem() throws ClassNotFoundException, SQLException {
		for (Item item : ItemService.getAllFull()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", item.getId(), ((SimpleItem) item).getName(), item.getcatygoryId(),
					((SimpleItem) item).getDate());
		}
	}

	public static void printItemFull() throws ClassNotFoundException, SQLException {

		for (Item item : ItemService.getAllFull()) {
			Category category = item.getCategory();
			System.out.printf(" %d.  %10s %6d. %12s  %10d.  %10s %6d. %12s \n ", item.getId(), ((SimpleItem) item).getName(),
					item.getcatygoryId(), ((SimpleItem) item).getDate(), category.getId(), category.getName(), category.getColor(),
					((CategoryImpl)category).getDate());
		}
	}

}
