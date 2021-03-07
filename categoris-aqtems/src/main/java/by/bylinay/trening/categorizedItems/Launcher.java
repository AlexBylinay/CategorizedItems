package by.bylinay.trening.categorizedItems;

import java.sql.SQLException;


public class Launcher {
	


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		printCatygory();
		 printItem();
		 printItemFull();
	}

	private static void printCatygory() throws ClassNotFoundException, SQLException {
		 CategoryService allCategory = new CategoryService();
		for (Category category : allCategory.getAll()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", category.getId(), category.getName(), category.getColor(),
					category.getDate());
		}

	}

	private static void printItem() throws ClassNotFoundException, SQLException {
		ItemService Allitem = new ItemService();
		for (Item item : Allitem.getAllFull()) {
			System.out.printf(" %d.  %12s %2d. %s \n ", item.getId(), item.getName(), item.catygoryId(),
					item.getDate());
		}
	}

	private static void printItemFull() throws ClassNotFoundException, SQLException {
		ItemService Allitem = new ItemService();
		for (Item item : Allitem.getAllFull()) {
			Category category = item.getCategory();
			System.out.printf(" %d.  %10s %6d. %12s  %10d.  %10s %6d. %12s \n ", item.getId(), item.getName(),
					item.catygoryId(), item.getDate(), category.getId(), category.getName(), category.getColor(),
					category.getDate());

		}
	}

}


