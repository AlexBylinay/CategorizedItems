package by.bylinay.trening.categorizedItems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class ScriptCollector {
	public static String  skript() {
    
    // INSERT INTO category (id, name_, color, date_)  VALUES 
	//( 'cat',  2, '2007-10-23'),
    int limit = AnimalsEnum.getAnimals().size();
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String text = date.format(formatter);
    String color = String.valueOf( generateNumber());
    String tipe = AnimalsEnum.getTypString(generateNumber());
    
    String script = ("("+ "'" +  tipe + "'"+ "," +  color  + "," + "'" + text + "'"+ ")"+",");
	return script;

	}
	
	public static String  skriptEnd() {
	    
	    // INSERT INTO category (id, name_, color, date_)  VALUES 
		//( 'cat',  2, '2007-10-23'),
	    int limit = AnimalsEnum.getAnimals().size();
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String text = date.format(formatter);
	    String color = String.valueOf( generateNumber());
	    String tipe = AnimalsEnum.getTypString(generateNumber());
	    
	    String script = ("("+ "'" + tipe + "'" + "," + color + ","  + "'" + text + "'"+ ")" + ";");
		return script;

		}
    public static int generateNumber() {
		return (int) (1 + Math.random() * AnimalsEnum.getAnimals().size());
		
		
	
	}
            
}