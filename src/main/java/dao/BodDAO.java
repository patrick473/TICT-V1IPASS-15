package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Bod;

public class BodDAO extends BaseDAO{
	private ArrayList<Bod> selectBoden(String query){
		ArrayList<Bod> bodlijst = new ArrayList<Bod>();
		try(Connection con = super.connect()){
			Statement stmt = con.createStatement();
	ResultSet dbResultSet = stmt.executeQuery(query);
	
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
	}
		return bodlijst;
}

public ArrayList<Bod> selectAll() {
	return selectBoden("SELECT * FROM bod");
}

public Bod findByCode(int id) {
	return selectBoden("SELECT * FROM bod WHERE bodid = " + id + "").get(0);
}

public Bod update(Bod b) {
	try (Connection con = super.connect()) {
		Statement stmt = con.createStatement();
		String query = "UPDATE medewerkers SET mnr=" + m.getMnr()
				+ ", naam='" + m.getNaam() + "',voorl='"
				+ m.getVoorl() + "', functie='" + m.getFunctie()
				+ "', chef=" + m.getChef() + ", gbdatum=to_date('"+
				m.getGbdatum() +"','DD-Mon-YY'), maandsal="+m.getMaandsal()
				+ ",comm= "+m.getComm()+", afd="+m.getAfd()+", geslacht='"+m.getGeslacht()
				+ "' WHERE mnr = "+ m.getMnr();
		stmt.executeUpdate(query);
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(b.getBodID());
}

public boolean delete(Bod b) {
	try (Connection con = super.connect()) {
		Statement stmt = con.createStatement();
		String query = "update afdelingen "
				+ "set hoofd=null "
				+ "where hoofd="+m.getMnr()+" ";
		stmt.executeUpdate(query);
		query = "DELETE FROM historie WHERE mnr=" + m.getMnr() + "";
		stmt.executeUpdate(query);
		query = "delete from inschrijvingen where cursist="+m.getMnr();
		stmt.executeUpdate(query);
		query ="update uitvoeringen "
				+ "set docent=null "
				+ "where docent= "+m.getMnr()+" ";
		stmt.executeUpdate(query);
		query ="delete from medewerkers where mnr="+m.getMnr()+" ";
		return true;
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		return false;
	}
}

public Bod insert(Bod b) {
	try (Connection con = super.connect()) {
		Statement stmt = con.createStatement();
		String query = "INSERT INTO medewerkers"
				+ "(mnr,naam,voorl,functie,chef,gbdatum,maandsal,comm,afd,geslacht) "
				+ "VALUES (" + m.getMnr()
				+ ", '" + m.getNaam() + "','"
				+ m.getVoorl() + "', '" + m.getFunctie()
				+ "', " + m.getChef() + ", to_date('"+
				m.getGbdatum() +"','DD-Mon-YY'), "+m.getMaandsal()
				+ ", "+m.getComm()+", "+m.getAfd()+", '"+m.getGeslacht()+"')";
		stmt.executeUpdate(query);
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(b.getBodID());
}
}