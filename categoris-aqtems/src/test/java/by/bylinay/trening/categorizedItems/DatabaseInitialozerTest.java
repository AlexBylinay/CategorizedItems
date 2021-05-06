package by.bylinay.trening.categorizedItems;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import by.bylinay.trening.categorizedItems.difficult.DatabaseInitializer;
import by.bylinay.trening.categorizedItems.difficult.GiverTimeOfCreate;

class DatabaseInitialozerTest {
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

	public static boolean checkingAvailabilityDatabase() {
		boolean i = true;
		String URL = "jdbc:mysql://localhost:3306/categorizedItemsTru";
		String USER = "root";
		String PASSWORD = "kapli123";
		try {
			DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			i = false;
		}
		return i;
	}

}