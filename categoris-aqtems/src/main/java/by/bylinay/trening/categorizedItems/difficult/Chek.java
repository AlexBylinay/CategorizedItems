package by.bylinay.trening.categorizedItems.difficult;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Statement;

import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;

public class Chek {

	public static void main(String[] arr)

			throws SQLException {

		String URL = "jdbc:mysql://localhost:3306/categorizedItemstru";
		String USER = "root";
		String PASSWORD = "kapli123";

		java.sql.Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT  * FROM category  order by id ");
		//ResultSet resultSet = statement.executeQuery( "ARRAY VARCHAR ( SELECT name_ FROM category WHERE name_ = 'raccoon3'and  'raccoon2');");
		//ResultSet resultSet = statement.executeQuery ("  SELECT name_ FROM category WHERE name_  IN ('raccoon3', 'raccoon2'); ");
		
		
		while (resultSet.next()) {

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			int color = resultSet.getInt(3);
			String date = resultSet.getString(4);
			/*
			 * int id2 = resultSet.getInt(5); String name2 = resultSet.getString(6); String
			 * category = resultSet.getString(7); String date2 = resultSet.getString(8);
			 */
			Category category = new CategoryImpl(id, name, color, date);

			// System.out.printf( " %d. %7s %2d. -%s %14d. %s %-10s. %s. \n ", id, name,
			// color, date, id2, name2, category, date2 );
			System.out.println(category.getId());
			System.out.println(((CategoryImpl) category).getName());
			//System.out.println(category.getColor());
			System.out.println(((CategoryImpl) category).getDate());

		}
		resultSet.close();
		connection.close();
	}
	
	//ResultSet resultSet = statement.executeQuery("SELECT name_ FROM category WHERE name_ = 'raccoon3';");
	
			/*ResultSet resultSet = statement.executeQuery("SELECT name_ FROM category WHERE name_ = 'raccoon2' and 'raccoon3' and 'raccoon1';");
			 ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			
			 int columnCount = rsmd.getColumnCount();
			 
			 String name = rsmd.getColumnCharacterEncoding(1);
			 lis.setName(rs.getString("name"));
				String registeredUserLogin = resultSet.getString("name_");
			 System.out.println(columnCount);*/
			
}