package by.bylinay.trening.categorizedItems;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.GiverTimeOfCreate;
import by.bylinay.trening.categorizedItems.difficult.ScriptExecutor;




class DatabaseInitialozerTest {
	
	
	
	 
	 @BeforeAll
	  public static void setUp() {
		 System.out.println("Start testing....... ");
		 
		 
	 }
	 
	 @BeforeEach
	 public  void setUp1() throws  SQLException, FileNotFoundException {
		 ScriptExecutor.clearDatabase();;
	 System.out.println("Database ready to testing");
		  
	 }
	 @AfterAll
	    public static  void afterMethod() {
	        System.out.println("Code executes after each test method");
	    }
	 
	@Test
	public void testCheckingCreationOrReCreationDatabase() throws SQLException, ClassNotFoundException, FileNotFoundException {
		String timecreateExistingBase;
		String timecreateCreatingBase;
		boolean chek = testcheckingAvailabilityDatabase();
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

	
	public  boolean testcheckingAvailabilityDatabase() throws SQLException {
		boolean i = true;
		
		try {
			
			@SuppressWarnings("unused")
			Statement statement = ConnectorAndStatement.makeConnectionFndStatement();
			
		} catch (SQLException e) {
			i = false;
		}
		return i;
	}
	@Test
	public void testAvailabilityDatabase() throws SQLException {
		assertTrue( testcheckingAvailabilityDatabase());
		 System.out.println("Database is available");
	}
	
	
   

	
	 @Test
	 public void testFullingDatabase ()  throws ClassNotFoundException, SQLException, FileNotFoundException{
		 int chekCaunt = 0;
		 DatabaseInitializer.reinit();
	    chekCaunt = ScriptExecutor.getCauntCategoty ();
	    assertEquals(0, chekCaunt);
	    }
	    
	    
	    @Test 
	    public void testMakeCatygory ()  throws ClassNotFoundException, SQLException{
	    	DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
	    	 int chekCaunt = 0;
	    	databaseInitializer.makeCatygory("raccoon", 2,  4);
	     chekCaunt = getCauntCategoty ();
	     assertEquals( 4, chekCaunt);
	     int idCategory = getIdCategory("raccoon" +  4);
	        assertEquals(idCategory,  4);
	        System.out.println(idCategory);
	        System.out.println( 4);
	    }
	    
	    
	    @Test// (expected =  SQL.class)
	    public void tCatygory ()  throws ClassNotFoundException, SQLException{
	    	DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
	    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    		databaseInitializer.makeCatygory("raccoon", 3, 4);
	    		databaseInitializer.makeCatygory("raccoon", 3, 4);
	    	  });
	    }
	    
	     
	    @Test 
	    public void testightMakeItem() throws ClassNotFoundException, SQLException{
	    	DatabaseInitializer databaseInitializer = new DatabaseInitializer ();
	    	 int chekCaunt = 0;
	    	  Category category = new CategoryImpl (1,"raccoon", 2);
	     databaseInitializer.makeItem("warior", category, 3);
	     chekCaunt = ScriptExecutor.getCauntItem();
	    assertEquals(3, chekCaunt);
	    int idItem = getIdItem("warior" + String.valueOf(3));
	   
	    assertEquals(idItem, 3);
	    }
	
	
	    public static int getIdItem ( String name) throws ClassNotFoundException, SQLException {
			ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery( "select id from item where name_ = " +  "'" + (name)+ "'" + ";" );
			int id = 0;
			while (rs.next()) {
			 id = rs.getInt(1);
			}
			return id;}
	    
	    public static int getCauntItem () throws ClassNotFoundException, SQLException {
			ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery("SELECT count(*) FROM item");
			int caunt = 0;
			while (rs.next()) {
				caunt = rs.getInt(1);
			}
			System.out.println(caunt);
			return caunt;
		}

	    public static int getCauntCategoty () throws ClassNotFoundException, SQLException {
			ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery("SELECT count(*) FROM category");
			int caunt = 0;
			while (rs.next()) {
				caunt = rs.getInt(1);
			}
			System.out.println(caunt);
			return caunt;
		}
	    
		public static int getIdCategory ( String name) throws ClassNotFoundException, SQLException {
			ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery("select id from category where name_ = " +  "'" + (name)+ "'" + ";" );
			int id = 0;
			while (rs.next()) {
			 id = rs.getInt(1);
			}
			return id;
			}
	    
}
	   
