package dao;

import java.sql.*;

public class BaseDAO {
	//Zorg ter voorbereiding dat je ojdbc.jar download en toevoegt aan je project.
	
	//Aanmaken van de variabelen die je connectie specificeren. In dit geval een gebruiker "harry" met password "harry"
	private static final String DB_DRIV = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://ikvelykswrxmte:41f82a54de5b6c192ca94000a3e2adbd83d9832bf5cb37df07cc4e289f93b113@ec2-79-125-13-42.eu-west-1.compute.amazonaws.com:5432/d96gqtk7c8o7qr";
	private static final String DB_USER = "ikvelykswrxmte";
	private static final String DB_PASS = "41f82a54de5b6c192ca94000a3e2adbd83d9832bf5cb37df07cc4e289f93b113";
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