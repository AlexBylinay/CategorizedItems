package by.bylinay.trening.categorizedItems.difficult;


import java.io.File;

import java.io.FileNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;
import by.bylinay.trening.categorizedItems.Connector;
import by.bylinay.trening.categorizedItems.ConnectorAndStatement;
import by.bylinay.trening.categorizedItems.Item;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class SkripterCategory {

	private String billetCatygory = "INSERT INTO category ( name_, color, date_) VALUES";
	private String chekCategory = "SELECT name_ FROM category WHERE name_ = ";
	private String delete = "DELETE FROM CATEGORY WHERE name_ = ";

//	private Map<String, Integer> NameIdCategory = new HashMap<String, Integer>();
	// private List<Category> allCategorys = new ArrayList<Category>();

	public static void reinit() throws SQLException, FileNotFoundException {
		//FileInputStream in;
		//in = new FileInputStream("resources\\categorisItems.sql");
		// in = new FileInputStream("resources\\kanigoryTru.sql");
		File file = new File("resources\\categorisItems.sql"); 
		// in = new FileInputStream("resources\\kanigoryTru.sql");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);
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

	private String skriptForCategory(Category catrgory) {
		return (billetCatygory + "(" + "'" + catrgory.getName() + "'" + "," + catrgory.getColor() + "," + "'"
				+ (catrgory.getDate()) + "'" + ")" + ";" + ";");
	}

	private String skriptForChekCategory(String name) {
		return (chekCategory + "'" + (name + "'" + ";"));
	}

	private String skriptForDeleteCategory(String name) {

		return (delete + "'" + (name + "'" + ";"));
	}

	private void addCategoryWithMistake(Category catrgory) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForCategory(catrgory) + "jnjn");

	}

	private int addCategory(Category catrgory) throws SQLException {
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
	

	private List<String> chekAutputCategory(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		List<String> names = new ArrayList<>();
		String nameQuery = null;
		int i = 0;
		while (rs.next())  {
			i++;
			nameQuery = rs.getString(i);
		}
		 names.add(nameQuery);
		return names;
	}
	
	
	private String chekAutputCategory2(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next())  {
			nameQuery = rs.getString(1);
		}
		return nameQuery;
	}
	

	private List<String> chekAutputCategory22(String nameFormat, int count) throws SQLException {
		List<String> names = new ArrayList<>();
		List<String> namesOutDataBase = new ArrayList<>();
		for (int i = 1; i<= count; i++) {
			String name = String.format(nameFormat + i);
			names.add(name);}
		
		names.forEach(b-> {
			try {
				if (chekAutputCategory2(b) != null) {
				namesOutDataBase.add(chekAutputCategory2(b));
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return namesOutDataBase;
	}
	
	
	private void deleteCategory(String name) throws SQLException {
		ConnectorAndStatement.makeConnectionFndStatement().executeUpdate(skriptForDeleteCategory(name));

	}

	public void makeCategory(String name, int color, int count) throws SQLException {
		String originalName = name;

		for (int i = 0; i < count; i++) {
			if (i == 0) {
				fulling2(name, color);

			}
			name = originalName + (toNum(i));
			fulling2(name, color);
		}
	}

	private int toNum(int index) {
		return index + 1;
	}

	private void fulling(String name, int color) throws SQLException {
		try {
			Category catrgory = new CategoryImpl(name, color);
			addCategory(catrgory);
		} catch (SQLException e) {
			System.out.println("This category  with name =" + " " + name + " " + "already exists");
			deleteCategory(name);
			Category catrgory = new CategoryImpl(name, color);
			addCategory(catrgory);
			System.out.println("was create new category with name =" + name);
		}

	}

	private void fulling2(String name, int color) throws SQLException {
		if (chekAutputCategory(name) != null) {
			try {
				throw new CustomSQLException("This Category name already exists");
			} catch (SQLException e) {
				System.out.println("This category  with name =" + " " + name + " " + "already exists");
				deleteCategory(name);
				Category catrgory = new CategoryImpl(name, color);
				addCategory(catrgory);
				System.out.println("was create new category with name =" + name);
			}

		} else {
			Category catrgory = new CategoryImpl(name, color);
			addCategory(catrgory);
		}
	}

	public void makeCategory2(String name, int color, int count) throws SQLException {
		String originalName = name;

		if (chekAutputCategory(name) != null) {
			throw new CustomSQLException("This Category name already exists");

		}
		for (int i = 0; i < count; i++) {
			if (i == 0) {

				Category catrgory = new CategoryImpl(name, color);
				// allCategorys.add(catrgory);
				addCategory(catrgory);
			}
			name = originalName + (toNum(i));
			if (chekAutputCategory(name) != null) {

			}
			throw new CustomSQLException("This Category name already exists");
		}
		Category catrgory = new CategoryImpl(name, color);
		// allCategorys.add(catrgory);
		addCategory(catrgory);

	}
	
	
	private List<Category> generateCategorys (String nameFormat, int color, int count){
		List<Category> categorys = new ArrayList<>();
		
		for (int i = 1; i<= count; i++) {
	Category category = new CategoryImpl(String.format(nameFormat + i), color);
		categorys.add(category);}
		
		return categorys;	
		
	}
	
	private boolean chekAutputName(String name) throws SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(skriptForChekCategory(name));
		String nameQuery = null;
		while (rs.next()) {
			nameQuery = rs.getString(1);
		}
	 return name.equals(nameQuery);
		
	}
		
	
	
	
	
	
	
	private void makeCategoryList(String nameFormat, int color, int count) {
		generateCategorys(nameFormat,color,count).forEach(b->  {
			try {
			if (chekAutputName(b.getName())) {
				throw new IllegalArgumentException("dtbnnyy");
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		generateCategorys(nameFormat,color,count).forEach(b->  {
		
				try {
					addCategory(b);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		});
	}
	
	
	
	
	private void makeCategoryList2(String nameFormat, int color, int count) throws SQLException {
		
			if (!chekAutputCategory22(nameFormat,count).isEmpty()) {
				throw new IllegalArgumentException("tg7uglkgjhgjhgjhgvygyg");
			}
			
		
		generateCategorys(nameFormat,color,count).forEach(b->  {
		
				try {
					addCategory(b);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		});
	}
	
	
	
	/*public void makeItem(String name, int count) throws SQLException {
		String originalName = name;
		if (getNameForDataDaseItem(name) != null) {
			System.out.println("This Item name already exists");
		} else {
			List<Integer> values = new ArrayList<Integer>(NameIdCategory.values());
			if (NameIdCategory.size() == 0) {
				System.out.println("Categorys didn't create");
			} else {

				if (NameIdCategory.size() < count) {
					System.out.println("This Item name already exists");
				} else {

					for (int i = 0; i < count; i++) {
						if (i == 0) {
							name = originalName;
							Item item = new SimpleItem(name, values.get(i));
							// Item item = new SimpleItem(name, allCategorys.get(i).getId());
							addItemtemWhithId(item);
						} else {
							name = originalName + (i);
							if (getNameForDataDaseItem(name) != null) {
								System.out.println("Error enter Item name");
							} else {

								Item item = new SimpleItem(name, values.get(i));
								// Item item = new SimpleItem(name, allCategorys.get(i).getId());
								addItemtemWhithId(item);

							}

						}
					}
				}
			}
		}
	}

	public int getIdCategory(String name) {
		return NameIdCategory.get(name);
	}

	public int getIdItem(String name) {
		return NameIdItem.get(name);
	}*/
	
	
	
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		//reinit();
		SkripterCategory hh = new SkripterCategory();
		//hh.makeCategory("raccoon", 2, 8);
		//hh.generateCategorys("cat", 3, 4).forEach(b-> System.out.print(b.getName()));
		
		hh.chekAutputCategory22("cat", 4).forEach(b-> System.out.print(b));
	
		
		/*hh.generateCategorys("cat", 3, 4).forEach(b->{
		try {
				System.out.print(hh.chekAutputCategory(b.getName()));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
	
		//hh.makeCategoryList("cat", 3, 4);
		hh.makeCategoryList2("cat1", 3, 4);
		
		/*hh.generateCategorys("cat", 3, 4).forEach(b-> {
			try {
				System.out.print(hh.chekAutputName(b.getName()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
		//hh.makeCategoryList("cat", 3, 4);
	
	
	
	}
	
	
}