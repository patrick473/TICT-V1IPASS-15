package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Gebruiker;

public class GebruikerDAO extends BaseDAO{
	private ArrayList<Gebruiker> selectGebruikeren(String query){
		ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
		try(Connection con = super.connect()){
			Statement stmt = con.createStatement();
	ResultSet dbrs = stmt.executeQuery(query);
	
	while (dbrs.next()){
		int gebruikersId = dbrs.getInt("gebruikersid");
		String gebruikersnaam = dbrs.getString("gebruikersnaam");
		String voornaam  = dbrs.getString("voornaam");
		String tussenvoegsel = dbrs.getString("tussenvoegsel");
		String achternaam  = dbrs.getString("achternaam");
		String adres = dbrs.getString("adres");
		String postcode = dbrs.getString("postcode");
		String plaatsnaam = dbrs.getString("plaatsnaam");
		String land = dbrs.getString("land");
		Date geboortedag = dbrs.getDate("geboortedag");
		String email = dbrs.getString("email");
		int telefoonNummer = dbrs.getInt("telefoonnummer");
		String wachtwoord = dbrs.getString("wachtwoord");
		int kv = dbrs.getInt("kanverkopen");
		String bankNummer = dbrs.getString("banknummer");
		
		boolean kanVerkopen = false;
		if (kv == 1){
			kanVerkopen = true;
		}
		else if (kv == 0){
			kanVerkopen = false;
		}
		
		Gebruiker gebruiker = new Gebruiker(gebruikersId,gebruikersnaam,voornaam,tussenvoegsel,achternaam,adres,postcode,plaatsnaam,land,geboortedag,email,telefoonNummer,wachtwoord,kanVerkopen,bankNummer);
	}
		}
	catch(SQLException e){
		e.printStackTrace();
	}
		return gebruikers;
	}

public ArrayList<Gebruiker> selectAll() {
	return selectGebruikeren("SELECT * FROM Gebruiker");
}

public Gebruiker findByCode(int id) {
	return selectGebruikeren("SELECT * FROM Gebruiker WHERE Gebruikerid = " + id + "").get(0);
}

public Gebruiker update(Gebruiker b) {
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
	
	return findByCode(b.getGebruikersID());
}

public boolean delete(Gebruiker b) {
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

public Gebruiker insert(Gebruiker b) {
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
	
	return findByCode(b.getGebruikersID());
}
}