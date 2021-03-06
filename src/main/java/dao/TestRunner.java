package dao;

import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.*;
public class TestRunner {
//runt alle testen die nodig zijn
	public static void main(String[] args) throws SQLException {
		BaseDAO bdao = new BaseDAO();
		GebruikerDAO gdao = new GebruikerDAO();
		RubriekDAO rdao = new RubriekDAO();
		VoorwerpDAO vdao = new VoorwerpDAO();
		BodDAO bbdao = new BodDAO();
		UserDAO udao = new UserDAO();
		Timestamp now = new Timestamp(System.currentTimeMillis());

		Bod b = new Bod(14,90.00,2,now);
		System.out.println(bbdao.findByCode(1));
		System.out.println(bbdao.insert(b));
		try {
			bdao.getConnection();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(udao.findRoleForUsernameAndPassword("patrick473", "wachtwoord"));
	}

}
