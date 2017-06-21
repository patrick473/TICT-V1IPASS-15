package dao;

import java.net.URISyntaxException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import model.Bod;

public class BodDAO extends BaseDAO{
	//wordt gebruikt om alle select statements aan te maken
	private ArrayList<Bod> selectBoden(String query){
		ArrayList<Bod> bodlijst = new ArrayList<Bod>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
	ResultSet dbResultSet = stmt.executeQuery(query);
	//voor elke item in result wordt een object gemaakt en opgeslagen
	while (dbResultSet.next()){
		int bodId = dbResultSet.getInt("bodid");
		int voorwerp = dbResultSet.getInt("voorwerp");
		double bodBedrag = dbResultSet.getDouble("bodbedrag");
		int gebruiker = dbResultSet.getInt("gebruiker");
		Timestamp bodTijd = dbResultSet.getTimestamp("bodtijd");
		Bod bod= new Bod(bodId,voorwerp,bodBedrag,gebruiker,bodTijd);
		bodlijst.add(bod);
	}
	}
	catch(SQLException e){
		e.printStackTrace();
	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		return bodlijst;
}
//selecteert alles returnt een lijst
public ArrayList<Bod> selectAll() {
	return selectBoden("SELECT * FROM bod");
}
//selecteert alleen waar bodid = ? 
public Bod findByCode(int id) {
	return selectBoden("SELECT * FROM bod WHERE bodid = " + id + "").get(0);
}
//selecteert alle biedingen op een voorwerp van hoog naar laag
public ArrayList<Bod> findByVoorwerp(int id) {
	return selectBoden("SELECT * FROM bod WHERE voorwerp = " + id + " order by bodbedrag desc");
}
//selecteert het hoogste bod op een voorwerp
public Bod findhighestBodByVoorwerp(int voorwerp) {
	
	ArrayList<Bod> a= selectBoden("select * from bod where voorwerp ="+voorwerp+" order by bodbedrag desc limit 1");
	if (a.size()>0){
		return a.get(0);
	}
	else{ return null;}
}
//registreren nieuw bod
public Bod insert(Bod b) {
	try (Connection con = super.getConnection()) {
		PreparedStatement stmt = con.prepareStatement(
				"insert into bod"
				+ " values(nextval('bod_sequence'),"+b.getVoorwerpID()+""
						+ ","+b.getBodBedrag()+","+b.getGebruiker()+",?)");
		
		stmt.setTimestamp(1, b.getBodTijd());
		stmt.executeUpdate();
		
		
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return b;
}
}