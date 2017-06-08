package dao;

import java.sql.*;

public class BaseDAO {
	//Zorg ter voorbereiding dat je ojdbc.jar download en toevoegt aan je project.
	
	//Aanmaken van de variabelen die je connectie specificeren. In dit geval een gebruiker "harry" met password "harry"
	private static final String DB_DRIV = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/ipass";
	private static final String DB_USER = "postgres";
	private static final String DB_PASS = "hunter2";
	private static Connection conn;
	
	// De methode die met JDBC aan de slag gaat moet een SQLException opvangen of gooien
	public Connection connect() throws SQLException{
		//Besluit welke driver je gaat gebruiken voor je verbinding		
		try {
			Class.forName(DB_DRIV).newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println("connection established");
		// Leg de connectie met de database
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return conn;
	}
}