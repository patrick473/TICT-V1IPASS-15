package dao;

import java.sql.Date;
import java.sql.SQLException;
import model.*;
public class TestRunner {

	public static void main(String[] args) throws SQLException {
		BaseDAO bdao = new BaseDAO();
		GebruikerDAO gdao = new GebruikerDAO();
		@SuppressWarnings("deprecation")
		Gebruiker g = new Gebruiker(41,"testaccount", "jan","de","achternaam","achterweg 123", "3405AS", "Utrecht", "Nederland", new Date(99, 12, 5), "testemail@gmail.com", 0642214323, "wachtwoord", true, "nl20rabo5692593827");
		bdao.connect();
		
		System.out.println(gdao.selectAll());
		System.out.println(gdao.update(g));
		System.out.println(gdao.selectAll());
		
	}

}
