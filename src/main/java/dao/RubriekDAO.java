package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Rubriek;

public class RubriekDAO extends BaseDAO{
		private ArrayList<Rubriek> selectRubrieken(String query){
			ArrayList<Rubriek> rubrieklijst = new ArrayList<Rubriek>();
			try(Connection con = super.connect()){
				Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		
		while (dbResultSet.next()){
			int rubriekNummer = dbResultSet.getInt("rubrieknummer");
			String rubriekNaam = dbResultSet.getString("rubrieknaam");
			int inRubriek = dbResultSet.getInt("inrubriek");
			Rubriek rubriek= new Rubriek(rubriekNummer,rubriekNaam,inRubriek);
			rubrieklijst.add(rubriek);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
			return rubrieklijst;
	}

	public ArrayList<Rubriek> selectAll() {
		return selectRubrieken("SELECT * FROM rubriek");
	}

	public Rubriek findByCode(int id) {
		return selectRubrieken("SELECT * FROM rubriek WHERE rubrieknummer = " + id + "").get(0);
	}
	public ArrayList<Rubriek> selectBovensteRubriek(){
		return selectRubrieken("select * from rubriek where inrubriek is null");
	}
	public ArrayList<Rubriek> selectRubriekenInRubriek(int id){
		return selectRubrieken("select * from rubriek where inrubriek ="+id+"");
	}
}
