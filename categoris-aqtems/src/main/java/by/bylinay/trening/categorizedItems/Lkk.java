package by.bylinay.trening.categorizedItems;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;







import com.mysql.jdbc.Connection;

public class Lkk {
	 static void executeScript( )//Connection conn)
	 
			    throws SQLException, FileNotFoundException
			    
			  {
		 
		 Connection conn = (Connection) DriverManager.getConnection(
		         "jdbc:mysql://localhost:3306/raccoons", "root", "kapli123");
 
		 
		 FileInputStream in;
         in = new FileInputStream("resources\\\\kanigoryTru.sql");
			    @SuppressWarnings("resource")
				Scanner s = new Scanner(in);
			    s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

			    Statement st = null;

			    try
			    {
			      st = conn.createStatement();

			      while (s.hasNext())
			      {
			        String line = s.next().trim();

			        if (!line.isEmpty())
			          st.execute(line);
			      }
			    }
			    finally
			    {
			      if (st != null)
			        st.close();
			    }
			 
			
			       System.out.println("done");
			  }
	 }