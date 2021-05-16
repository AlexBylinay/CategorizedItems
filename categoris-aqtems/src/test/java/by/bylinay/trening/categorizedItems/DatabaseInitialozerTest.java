package by.bylinay.trening.categorizedItems;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.GiverTimeOfCreate;
import by.bylinay.trening.categorizedItems.difficult.MakerSkript;
import by.bylinay.trening.categorizedItems.difficult.SrripterCategory;



class DatabaseInitialozerTest {
	DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
	SrripterCategory makeCategory = new SrripterCategory();
    int cauntCategory = 4;
    int cauntItem = 3;
    String nameCatrgory = "raccoon";
    String nameItem = "warior";
    int chekCaunt = 0;
	 Statement statement = null;
	 
	 
	 @BeforeAll
	  public static void setUp() {
		 System.out.println("Start testing....... ");
		
	 }
	 
	 @BeforeEach
	 public  void setUp1() throws  SQLException, FileNotFoundException {
		  databaseInitializer.reinit();
	 System.out.println("Database ready to testing");
		  
	 }
	 @AfterAll
	    public static  void afterMethod() {
	        System.out.println("Code executes after each test method");
	    }
	 
	@Test
	void test() throws SQLException, ClassNotFoundException, FileNotFoundException {
		String timecreateExistingBase;
		String timecreateCreatingBase;
		boolean chek = checkingAvailabilityDatabase();
		if (chek == true) {
			System.out.println("database was existed");
			timecreateExistingBase = GiverTimeOfCreate.givTime();
			DatabaseInitializer.reinit();
			timecreateCreatingBase = GiverTimeOfCreate.givTime();
			assertNotSame(timecreateExistingBase, timecreateCreatingBase);
			System.out.printf(" %s \n  %s \n %s  \n %s \n", "time create old:", timecreateExistingBase,
					"time create new:", timecreateCreatingBase);
		}

		else {
			System.out.println("database didn't create");
			DatabaseInitializer.reinit();
			timecreateCreatingBase = GiverTimeOfCreate.givTime();
			assertNotNull(timecreateCreatingBase);
			System.out.printf("time create: %s", timecreateCreatingBase);
		}
	}

	
	public  boolean checkingAvailabilityDatabase() throws SQLException {
		boolean i = true;
		
		try {
			statement = ConnectorAndStatement.makeConnectionFndStatement();
			
		} catch (SQLException e) {
			i = false;
		}
		return i;
	}
	@Test
	void testAvailabilityDatabase() throws SQLException {
		assertTrue( checkingAvailabilityDatabase());
		 System.out.println("Database is available");
	}
	
	
   

	
	 @Test
	    void testFullingDatabase ()  throws ClassNotFoundException, SQLException, FileNotFoundException{
		 makeCategory.reinit();
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
	   
	
