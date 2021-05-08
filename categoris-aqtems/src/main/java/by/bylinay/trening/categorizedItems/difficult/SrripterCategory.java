package by.bylinay.trening.categorizedItems.difficult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;
import by.bylinay.trening.categorizedItems.Connector;
import by.bylinay.trening.categorizedItems.ConnectorAndStatement;


public class SrripterCategory {

	private  String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
	private  String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
	private String delete = "DELETE FROM CATEGORY WHERE name_ = ";
			
//	private Map<String, Integer> NameIdCategory = new HashMap<String, Integer>();
	// private  List<Category> allCategorys = new ArrayList<Category>();

	public static void reinit() throws SQLException, FileNotFoundException {
		FileInputStream in;
		in = new FileInputStream("resources\\categorisItems.sql");
		// in = new FileInputStream("resources\\kanigoryTru.sql");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		Statement statement = null;

		try {
			statement = ConnectorAndStatement.makeConnectionFndStatement();
			while (s.hasNext()) {
				String line = s.next().trim();

				if (!line.isEmpty())
					statement.execute(line);
			}
		} finally {
			if (statement != null)
				statement.close();
		}

		System.out.println("done create");
	}

	private  String skriptForCategory(Category catrgory) {
		return (billetCatygory + "(" + "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + "," + "'"
				+ (catrgory.getDate()) + "'" + ")" + ";" + ";");
	}

	

	private  String skriptForChekCategory(String name) {
		return (chekCategory + "'" + (name + "'" + ";"));
	}


	private  String skriptForDeleteCategory(String name) {
		
		return (delete + "'" + (name + "'" + ";"));
	}
	
	
	private void addCatygory2(Category catrgory) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForCategory(catrgory)+ "jnjn");
	
	}
	
	
	private int addCatygory(Category catrgory) throws SQLException {
		PreparedStatement ps = (PreparedStatement) Connector.connectionForDatabaseCategcorizedItemstru()
				.prepareStatement((skriptForCategory(catrgory)), Statement.RETURN_GENERATED_KEYS);
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
			
	}
return generatedKey;
	}


	private  String chekAutputCategory(String name) throws  SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);

		}
		return nameQuery;
	}
	
	private  void deleteCategory(String name) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForDeleteCategory(name));
		
	}
	

	public void makeCategory(String name, int color, int count) throws SQLException {
		String originalName = name;
	
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				fulling(name, color);

			}
				name = originalName + (toNum(i));
				fulling(name, color);
			}
		}
	

	private  int toNum(int index) {
		return index + 1;
	}
	
	private void fulling (String name, int color) throws SQLException {
	try { 
		Category catrgory = new CategoryImpl(name, color);
		addCatygory2(catrgory);
		}  catch  (SQLException e) { 
		    System.out.println("This category  with name ="  + " " + name  + " " + "already exists");
            deleteCategory(name);
            Category catrgory = new CategoryImpl(name, color);
            addCatygory(catrgory);
            System.out.println("was create new category with name =" + name);
        } 

}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		reinit();
		SrripterCategory hh = new SrripterCategory();
		hh.makeCategory("raccoon", 2, 8);
		//hh.makeCatygory("cat", 2, 9);
	
	}
}