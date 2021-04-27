package by.bylinay.trening.categorizedItems;


import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.mysql.jdbc.PreparedStatement;


public class RanSkript {
	
static String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
static String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
static String billit = "USE categoriz; ";
static String billitItem = "USE item; ";
	public static  String  skriptForCatygory(String name, int colorCod) {
		return ( billetCatygory + "("+ "'" + name + "'" + "," + colorCod + ","  + "'" + (LocalDate.now().format(formatter))+ "'"  + ")" + ";");
	}
	public static  String  skriptForItem(String name, int catygoryId) {
		return ( billetItem + "("+ "'" + name + "'" + "," + catygoryId + ","  + "'" + (LocalDate.now().format(formatter))+ "'"  + ")" + ";");
	}
	
	
	
	public static void createCatygory(String name, int colorCod) throws ClassNotFoundException, SQLException {
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(billit);
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForCatygory(name, colorCod));
        PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru().prepareStatement((skriptForCatygory(name, colorCod)), Statement.RETURN_GENERATED_KEYS);
	
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
		    generatedKey = rs.getInt(1);
		}
		
	}
	
	public static void createItem(String name, int catygoryId) throws ClassNotFoundException, SQLException {
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(billitItem);
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(name, catygoryId));
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
		
		createCatygory("raccoon1", 21);
		createItem("ggggg1", 5);
	}
}

