package by.bylinay.trening.categorizedItems.difficult;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import by.bylinay.trening.categorizedItems.ConnectorAndStatement;



public class MakerSkript {
	private static String qu�eryCauntCategory = "SELECT count(*) FROM category";
	private static String qu�eryCauntItem = "SELECT count(*) FROM item";
	private static String qu�eryVolumeCategory = "select id from category where name_ = ";
	private static String qu�eryVolumeItem = "select id from item where name_ = ";
	
	public static int getCauntCategoty () throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(qu�eryCauntCategory);
		int caunt = 0;
		while (rs.next()) {
			caunt = rs.getInt(1);
		}
		System.out.println(caunt);
		return caunt;
	}
	
	public static int getCauntItem () throws ClassNotFoundException, SQLException {
		ResultSet rs = ConnectorAndStatement.makeConnectionFndStatement().executeQuery(qu�eryCauntItem);
		int caunt = 0;
		while (rs.next()) {
			caunt = rs.getInt(1);
		}
		System.out.println(caunt);
		return caunt;
	}
	

	
	private static String makeSkriptForGetIdCategory ( String name) {
		return (qu�eryVolumeCategory +  "'" + name+ "'" + ";" );
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
		return (qu�eryVolumeItem +  "'" + name+ "'" + ";" );
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
}

