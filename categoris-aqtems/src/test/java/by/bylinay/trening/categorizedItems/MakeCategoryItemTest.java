package by.bylinay.trening.categorizedItems;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.MakerSkript;

class MakeCategoryItemTest {
	 
    DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
    int cauntCategory = 4;
    int cauntItem = 3;
    String nameCatrgory = "raccoon";
    String nameItem = "warior";
    int chekCaunt = 0;
    
    
   
    
    @Test
    void testFullingDatabase ()  throws ClassNotFoundException, SQLException, FileNotFoundException{
    databaseInitializer.reinit();
    chekCaunt = MakerSkript.getCauntCategoty ();
    assertEquals(0, chekCaunt);
    }
    
    
    @Test
    void testMakeCatygory ()  throws ClassNotFoundException, SQLException{
    databaseInitializer.makeCatygory(nameCatrgory, 2, cauntCategory);
    
    
     chekCaunt = MakerSkript.getCauntCategoty ();
     assertEquals(cauntCategory, chekCaunt);
     int idCategory = MakerSkript.getIdCategory(nameCatrgory + (cauntCategory));
        assertEquals(idCategory, cauntCategory);
        System.out.println(idCategory);
        System.out.println(cauntCategory);
    }
     
     /*
      * @Test
    void testMakeItem() throws ClassNotFoundException, SQLException{
     databaseInitializer.makeItem(nameItem, cauntItem);
     chekCaunt = MakerSkript.getCauntItem();
    assertEquals(cauntItem, chekCaunt);
    int idItem = MakerSkript.getIdItem(nameItem + String.valueOf(cauntItem));
    assertEquals(idItem, cauntItem);
    System.out.println(idCategory);
    System.out.println(cauntCategory);
    System.out.println(idItem);
    System.out.println(cauntItem);
}*/
}
