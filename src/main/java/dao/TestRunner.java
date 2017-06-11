package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
public class TestRunner {

	public static void main(String[] args) throws SQLException {
		BaseDAO bdao = new BaseDAO();
		GebruikerDAO gdao = new GebruikerDAO();
		RubriekDAO rdao = new RubriekDAO();
		VoorwerpDAO vdao = new VoorwerpDAO();
		BodDAO bbdao = new BodDAO();
		UserDAO udao = new UserDAO();
		
		bdao.connect();
		System.out.println(udao.findRoleForUsernameAndPassword("patrick473", "wachtwoord"));
	}

}
