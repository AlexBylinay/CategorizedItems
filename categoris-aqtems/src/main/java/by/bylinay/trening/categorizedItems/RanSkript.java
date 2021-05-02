package by.bylinay.trening.categorizedItems;


import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;


public class RanSkript {
	
static String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
static String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
static String chekItem = "SELECT name_ FROM item WHERE name_ = ";
static String billetItem = "INSERT INTO item ( name_, category_id, date_) VALUES";
static String billit = "USE categoriz; ";
static String billitItem = "USE item; ";
 private static Map <String, Integer> CategoryNames = new HashMap<String, Integer>();
 static Map <String, Integer> NameIdCategory = new HashMap<String, Integer>();
static Map <Integer, Category> idsCategory = new HashMap <Integer, Category>();
static List<Category> allCategorys = new ArrayList<Category>();



	public static  String  skriptForCategory( Category catrgory) {
		return ( billetCatygory + "("+ "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + ","  + "'" + (catrgory.getDate())+ "'"  + ")" + ";");
	}
	public static  String  skriptForItem(Item item, Category catrgory) {
		return ( billetItem + "("+ "'" + item.getName() + "'" + "," + catrgory.getId() + ","  + "'" + (item.getDate())+ "'"  + ")" + ";");
	}
	public static  String  skriptForItemWhithId(Item item, int catrgoryId) {
		return ( billetItem + "("+ "'" + item.getName() + "'" + "," + catrgoryId + ","  + "'" + (item.getDate())+ "'"  + ")" + ";");
	}
	
	public static  String  skriptForChekCategory(String name) {
		return ( chekCategory +"'"+ ( name +"'" + ";"));
	}
	
	
	
	public static  String  skriptForChekItem(String name) {
		return ( chekItem +"'"+ ( name +"'" + ";"));
	}
	
	
	public static void addCatygory(Category catrgory) throws ClassNotFoundException, SQLException {
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
	
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItem(item, catrgory));
	}

	public static void addItemItemWhithId(Item item) throws ClassNotFoundException, SQLException {
		
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItemWhithId(item, item.getcatygoryId()));
	}
	
	public static String chekCategory(String name) throws ClassNotFoundException, SQLException {	
     ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery( skriptForChekCategory( name));
     String nameQuery = null;
     while (rs.next()) {
    	 nameQuery = rs.getString(1);
			
	}
		return nameQuery;
	}
	
	
	public static String chekItem(String name) throws ClassNotFoundException, SQLException {	
	     ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery( skriptForChekItem( name));
	     String nameQuery = null;
	     while (rs.next()) {
	    	 nameQuery = rs.getString(1);
				
		}
			return nameQuery;
		}
	
	
	
	
public static void chekBe( String nime) throws ClassNotFoundException, SQLException {
		
		//ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForItemWhithId(item, item.getcatygoryId()));
	}
	
	
	
	public static void makeCatygory (String name, int color, int count ) throws ClassNotFoundException, SQLException  {
	 String originalName = name;
	if(chekCategory(name) != null)
	{
		System.out.println("This Category name already exists");
		}
	else {	
		for (int i = 0; i < count; i++) {
			if (i ==0) {
				
		Category catrgory  = new CategoryImpl (name, color);
			addCatygory(catrgory);
			}
			else {	
			name = originalName + String.valueOf(i+1); 
			if( chekCategory(name) != null) {
				System.out.println("This Category name already exists");}
			else {
		Category catrgory  = new CategoryImpl (name, color);
			addCatygory(catrgory);
			
			}
	}
		}
	}}
	public static void makeItem (String name, int count) throws ClassNotFoundException, SQLException  {
		String originalName = name;
		if(chekItem(name) != null)
		{
			System.out.println("This Item name already exists");
			}
		else {	
		 List<Integer> values = new ArrayList<Integer>(NameIdCategory.values());
	
			if (NameIdCategory.size() == 0) {
				System.out.println("Categorys didn't create");
			}
			else {	
		 
		 
		if (NameIdCategory.size() < count) {
			System.out.println("This Item name already exists");
		}
		else {	
			
		for (int i = 0; i < count; i++) {
			if (i ==0) {name = originalName; 	
	 		Item item  = new SimpleItem (name, values.get(i));
			addItemItemWhithId(item);
			}
			else {	
				name = originalName + String.valueOf(i+1);
				if( chekItem(name) != null) {
					System.out.println("Error enter Item name");}
				else {
		 		
				
 		Item item  = new SimpleItem (name, values.get(i));
		addItemItemWhithId(item);
	
		}
		
		}}
			}
		}}
	}
	public static void createCatygory(String name, int color, int volume ) throws ClassNotFoundException, SQLException {
		Category catrgory  = new CategoryImpl (name, color);
		for (int i = 0; i < volume; i++) {
			addCatygory(catrgory);
			
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
		makeCatygory ("hhmmxcccm", 2, 4);
		makeItem("gnhgvvvmjg", 4);
		
		
	}
}

