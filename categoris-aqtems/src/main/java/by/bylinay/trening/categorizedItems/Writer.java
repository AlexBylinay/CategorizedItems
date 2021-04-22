package by.bylinay.trening.categorizedItems;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
 
public class Writer {
	
   
        public static void main(String[] args) throws FileNotFoundException, SQLException {
        
        	
        	
        	 for   (int i=0; i<6; i++) {
            String filePath = "resources\\kanigory.sql";
            String text = ScriptCollector.skript() ;
 
            try {
                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
            }
            catch (IOException e) {
                System.out.println(e);}
            }
        	 
        	 
        	 String filePath = "resources\\kanigory.sql";
             String text = ScriptCollector.skriptEnd() ;
  
             try {
                 Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
             }
             catch (IOException e) {
                 System.out.println(e);}
        	 
        	 
             
             
        	 
        }
}
