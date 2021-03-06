package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Rubriek;

public class RubriekDAO extends BaseDAO{
	//selecteert rubrieken
		private ArrayList<Rubriek> selectRubrieken(String query){
			ArrayList<Rubriek> rubrieklijst = new ArrayList<Rubriek>();
			try(Connection con = super.getConnection()){
				Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		//voor elke result wordt nieuw object aangemaakt
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
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			return rubrieklijst;
	}
 //selecteert alles
	public ArrayList<Rubriek> selectAll() {
		return selectRubrieken("SELECT * FROM rubriek");
	}
//selecteert op rubriekid
	public Rubriek findByCode(int id) {
		return selectRubrieken("SELECT * FROM rubriek WHERE rubrieknummer = " + id + "").get(0);
	}
	//selecteert de bovenste niveau van rubrieken
	public ArrayList<Rubriek> selectBovensteRubriek(){
		return selectRubrieken("select * from rubriek where inrubriek is null");
	}
	//selecteert alle rubrieken onder de aangegeven rubriek
	public ArrayList<Rubriek> selectRubriekenInRubriek(int id){
		return selectRubrieken("select * from rubriek where inrubriek ="+id+"");
	}
}
