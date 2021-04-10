package by.bylinay.trening.categorizedItems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;





public class DatabaseInitializer {
	
	 public static void reinit() throws ClassNotFoundException,
     SQLException {
     
     String aSQLScriptFilePath = "C:\\Users\\Lenovo\\git\\CategorizedItems\\categoris-aqtems\\src\\main\\resources\\kanigoryTru.sql";
  
     
     // Create MySql Connection
  //   Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/raccoons", "root", "kapli123");
     Statement stmt = null;

     try {
         // Initialize object for ScripRunner
         ScriptRunner sr = new ScriptRunner(con);

         // Give the input file to Reader
         Reader reader = new BufferedReader(
                            new FileReader(aSQLScriptFilePath));

         // Exctute script
         sr.runScript(reader);

     } catch (Exception e) {
         System.err.println("Failed to Execute" + aSQLScriptFilePath
                 + " The error is " + e.getMessage());
     }
 }

}
