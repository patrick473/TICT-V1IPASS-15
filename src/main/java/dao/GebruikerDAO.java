package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import model.Gebruiker;

public class GebruikerDAO extends BaseDAO{
	private ArrayList<Gebruiker> selectGebruikers(String query){
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
	
		gebruikers.add(gebruiker);
	}
		}
	catch(SQLException e){
		e.printStackTrace();
	}
		return gebruikers;
	}

public ArrayList<Gebruiker> selectAll() {
	return selectGebruikers("SELECT * FROM gebruiker");
}

public Gebruiker findByCode(int id) {
	return selectGebruikers("SELECT * FROM Gebruiker WHERE Gebruikersid = " + id + "").get(0);
}

public Gebruiker update(Gebruiker b) {
	try (Connection con = super.connect()) {
		PreparedStatement stmt = con.prepareStatement(
				"update gebruiker "+
"set gebruikersnaam='"+b.getGebruikersNaam()+"',tussenvoegsel='"+b.getTussenVoegsel()+"',achternaam='"+b.getAchterNaam()+"',"
+ "adres='"+b.getAdres()+"',postcode='"+b.getPostCode()+"',plaatsnaam='"+b.getPlaatsNaam()+"',land='"+b.getLand()+"',geboortedag=?,"
+ "email='"+b.getEmail()+"',telefoonnummer="+b.getTelefoonNummer()+",kanVerkopen=?,banknummer='"+b.getBankNummer()+"' where gebruikersid="+b.getGebruikersID()+"");
		
		stmt.setDate(1, b.getGeboorteDag());
		
		if(b.getKanVerkopen() == true){
			stmt.setInt(2, 1);
		}
		else{
			stmt.setInt(2, 0);
		}
		stmt.executeUpdate();
		
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(b.getGebruikersID());
}
public Gebruiker updatePassword (Gebruiker b){
	try (Connection con = super.connect()) {
		PreparedStatement stmt = con.prepareStatement(
				"update gebruiker "+
"set set wachtwoord ='"+b.getWachtWoord()+"' where gebruikersid="+b.getGebruikersID()+"");
		
		stmt.setDate(1, b.getGeboorteDag());
		
		if(b.getKanVerkopen() == true){
			stmt.setInt(2, 1);
		}
		else{
			stmt.setInt(2, 0);
		}
		stmt.executeUpdate();
		
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(b.getGebruikersID());
	
}


public Gebruiker insert(Gebruiker b) {
	try (Connection con = super.connect()) {
		PreparedStatement stmt = con.prepareStatement(
				"insert into gebruiker"
				+ " values(gebruiker_sequence.nextval,'"+b.getGebruikersNaam()+"','"+b.getVoorNaam()+"'"
						+ ",'"+b.getTussenVoegsel()+"','"+b.getAchterNaam()+"','"+b.getAdres()+"'"
								+ ",'"+b.getPostCode()+"','"+b.getPlaatsNaam()+"','"+b.getLand()+"',?,"
										+ "'"+b.getEmail()+"',"+b.getTelefoonNummer()+",'"+b.getWachtWoord()+""
												+ "',?,'"+b.getBankNummer()+"')");
		
		stmt.setDate(1, b.getGeboorteDag());
		if(b.getKanVerkopen() == true){
			stmt.setInt(2, 1);
		}
		else{
			stmt.setInt(2, 0);
		}
		stmt.executeUpdate();
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(b.getGebruikersID());
}
}