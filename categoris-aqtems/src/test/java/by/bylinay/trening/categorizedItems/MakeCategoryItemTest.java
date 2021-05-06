package by.bylinay.trening.categorizedItems;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.MakerSkript;

class MakeCategoryItemTest {

	
	@Test
	void test()  throws ClassNotFoundException, SQLException, FileNotFoundException {
		DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
		int cauntCategory = 4;
		int cauntItem = 3;
		String nameCatrgory = "raccoon";
		String nameItem = "warior";
		int chekCaunt = 0;
		
		DatabaseInitializer.reinit();
		chekCaunt = MakerSkript.getCauntCategoty ();
		assertEquals(0, chekCaunt);
		databaseInitializer.makeCatygory(nameCatrgory, 2, cauntCategory);
		 chekCaunt = MakerSkript.getCauntCategoty ();
		 assertEquals(cauntCategory, chekCaunt);
		 databaseInitializer.makeItem(nameItem, cauntItem);
		 chekCaunt = MakerSkript.getCauntItem();
		assertEquals(cauntItem, chekCaunt);
		int idCategory = MakerSkript.getIdCategory(nameCatrgory + String.valueOf(cauntCategory));
		assertEquals(idCategory, cauntCategory);
		int idItem = MakerSkript.getIdItem(nameItem + String.valueOf(cauntItem));
		assertEquals(idItem, cauntItem);
		System.out.println(idCategory);
		System.out.println(cauntCategory);
		System.out.println(idItem);
		System.out.println(cauntItem);
	}

}
