package by.bylinay.trening.categorizedItems;


import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;


public class RanSkript {
	
static String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
static String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
static String billit = "USE categoriz; ";
static String billitItem = "USE item; ";
 static Map <String, Integer> CategoryNames = new HashMap<String, Integer>();
 static Map <String, Integer> NameIdCategory = new HashMap<String, Integer>();

static Map <Integer, Category> idsCategory = new HashMap <Integer, Category>();



	public static  String  skriptForCategory( Category catrgory) {
		return ( billetCatygory + "("+ "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + ","  + "'" + (LocalDate.now().format(formatter))+ "'"  + ")" + ";");
	}
	public static  String  skriptForItem(Item item, Category catrgory) {
		return ( billetItem + "("+ "'" + item.getName() + "'" + "," + catrgory.getId() + ","  + "'" + (LocalDate.now().format(formatter))+ "'"  + ")" + ";");
	}
	public static  String  skriptForItemWhithId(Item item, int catrgoryId) {
		return ( billetItem + "("+ "'" + item.getName() + "'" + "," + catrgoryId + ","  + "'" + (LocalDate.now().format(formatter))+ "'"  + ")" + ";");
	}
	
	
	public static void addCatygory(Category catrgory) throws ClassNotFoundException, SQLException {
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForCatygory(name, colorCod));
        PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru().prepareStatement((skriptForCategory(catrgory)), Statement.RETURN_GENERATED_KEYS);
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
		    generatedKey = rs.getInt(1);
		}
		idsCategory.put(generatedKey, catrgory);
		NameIdCategory.put(catrgory.getName(), generatedKey);
	}
	
	public static void addItem(Item item, Category catrgory) throws ClassNotFoundException, SQLException {
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(billitItem);
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(item, catrgory));
	}
	
	public static void addItemItemWhithId(Item item, int catrgoryId) throws ClassNotFoundException, SQLException {
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(billitItem);
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItemWhithId(item, catrgoryId));
	}
	
	public static void makeCatygory (String name, int color ) throws ClassNotFoundException, SQLException  {
		 CategoryNames.put(name, color);
		Category catrgory  = new CategoryImpl (name, color);
			addCatygory(catrgory);
	}
	
	public static void makeItem (String name,  String nameCategory ) throws ClassNotFoundException, SQLException  {
		
		Item item  = new SimpleItem (name, NameIdCategory.get(nameCategory));
		addItemItemWhithId(item, NameIdCategory.get(nameCategory));
	}
	
	public static void createCatygory(String name, int color, int volume ) throws ClassNotFoundException, SQLException {
		
		Category catrgory  = new CategoryImpl (name, color);
		for (int i = 0; i < volume; i++) {
			addCatygory(catrgory);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
		/*Category catrgory1  = new CategoryImpl ("raccoon", 5);
		Category catrgory2  = new CategoryImpl ("geraff", 1);
		Item item1 = new SimpleItem ("Manowar", 3, catrgory1);
		Item item2 = new SimpleItem ("warior", 6,catrgory2 );
		createCatygory(catrgory1);
		createCatygory(catrgory2);
		createItem(item1,catrgory1);
		createItem(item2, catrgory1 );	*/
		//("raccoon", 1, 3);
		//makeCatygory ("cat", 2);
		//makeCatygory ("cat", 2);
		makeCatygory ("cakt2", 2);
		makeItem("jkjk", "cakt2");
		System.out.println(CategoryNames.size());
		
	}
}

