package by.bylinay.trening.categorizedItems;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.GiverTimeOfCreate;



class DatabaseInitialozerTest {
	 Statement statement = null;
	 
	 @BeforeAll
	  public static void setUp() {
		 System.out.println("time create bnnb  n");
		
	 }
	 
	 
	 
	 @BeforeEach
	 public  void setUp1() {
		 System.out.println("tfdhetyjy");
	
	 }
	 @After
	    public void afterMethod() {
	        System.out.println("Code executes after each test method");
	    }
	 
	 @DisplayName("A special test case")
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
	
	
	
	

	   
	
}