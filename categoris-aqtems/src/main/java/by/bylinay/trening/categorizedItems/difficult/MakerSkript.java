package by.bylinay.trening.categorizedItems.difficult;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

import by.bylinay.trening.categorizedItems.ConnectorAndStatement;



public class MakerSkript {
	private static String quóeryCauntCategory = "SELECT count(*) FROM category";
	private static String quóeryCauntItem = "SELECT count(*) FROM item";
	private static String quóeryVolumeCategory = "select id from category where name_ = ";
	private static String quóeryVolumeItem = "select id from item where name_ = ";
	
	public static int getCauntCategoty () throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(quóeryCauntCategory);
		int caunt = 0;
		while (rs.next()) {
			caunt = rs.getInt(1);
		}
		System.out.println(caunt);
		return caunt;
	}
	
	public static int getCauntItem () throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(quóeryCauntItem);
		int caunt = 0;
		while (rs.next()) {
			caunt = rs.getInt(1);
		}
		System.out.println(caunt);
		return caunt;
	}
	

	
	private static String makeSkriptForGetIdCategory ( String name) {
		return (quóeryVolumeCategory +  "'" + name+ "'" + ";" );
	}
	
	public static int getIdCategory ( String name) throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery( makeSkriptForGetIdCategory (  name));
		int id = 0;
		while (rs.next()) {
		 id = rs.getInt(1);
		}
		return id;
		}

	private static String makeSkriptForGetIdItem ( String name) {
		return (quóeryVolumeItem +  "'" + name+ "'" + ";" );
	}
	
	public static int getIdItem ( String name) throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery( makeSkriptForGetIdItem ( name));
		int id = 0;
		while (rs.next()) {
		 id = rs.getInt(1);
		}
		return id;}
	public static void reinit() throws SQLException, FileNotFoundException {

		File file = new File("resources\\categorisItems.sql");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);
		s.useDelimiter("/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;");

		Statement statement = null;

		try {
			statement = (Statement) ConnectorAndStatement.makeConnectionFndStatement();
			while (s.hasNext()) {
				String line = s.next().trim();

				if (!line.isEmpty())
					statement.execute(line);
			}
		} finally {
			if (statement != null)
				statement.close();
		}

		//System.out.println("done create");
	}
}

