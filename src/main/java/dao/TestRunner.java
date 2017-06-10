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
		
		bdao.connect();
		System.out.println(vdao.selectAll());
		ArrayList<Bod> al = bbdao.findByVoorwerp(14);
		for ( Bod i: al){
			System.out.println(gdao.findByCode(i.getGebruiker()));
		}
		
		
	}

}
