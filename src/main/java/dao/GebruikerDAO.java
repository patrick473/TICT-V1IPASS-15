package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import model.Gebruiker;

public class GebruikerDAO extends BaseDAO{
	//wordt gebruikt voor alle select statements
	private ArrayList<Gebruiker> selectGebruikers(String query){
		ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
	ResultSet dbrs = stmt.executeQuery(query);
	//voor elke result wordt een nieuw object gemaakt
	while (dbrs.next()){
		int gebruikersId = dbrs.getInt("gebruikersid");
		String gebruikersnaam = dbrs.getString("gebruikersnaam");
		String voornaam  = dbrs.getString("voornaam");
		
		String achternaam  = dbrs.getString("achternaam");
		String adres = dbrs.getString("adres");
		String postcode = dbrs.getString("postcode");
		String plaatsnaam = dbrs.getString("plaatsnaam");
		String land = dbrs.getString("land");
		Date geboortedag = dbrs.getDate("geboortedag");
		String email = dbrs.getString("email");
		int telefoonNummer = dbrs.getInt("telefoonnummer");
		String wachtwoord = dbrs.getString("wachtwoord");
		boolean kanVerkopen = dbrs.getBoolean("kanverkopen");
		String bankNummer = dbrs.getString("banknummer");
		
		
		
		Gebruiker gebruiker = new Gebruiker(gebruikersId,gebruikersnaam,voornaam,achternaam,adres,postcode,plaatsnaam,land,geboortedag,email,telefoonNummer,wachtwoord,kanVerkopen,bankNummer);
	
		gebruikers.add(gebruiker);
	}
		}
	catch(SQLException e){
		e.printStackTrace();
	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		return gebruikers;
	}
//selecteert alles
public ArrayList<Gebruiker> selectAll() {
	return selectGebruikers("SELECT * FROM gebruiker");
}
//selecteert op gebruikersid
public Gebruiker findByCode(int id) {
	return selectGebruikers("SELECT * FROM gebruiker WHERE gebruikersid = " + id + "").get(0);
}
//selecteert op gebruikersnaam
public Gebruiker findByUsername(String id) {
	return selectGebruikers("SELECT * FROM gebruiker WHERE gebruikersnaam = '" + id + "'").get(0);
}
//update de gebruikerinfo
public Gebruiker update(Gebruiker b) {
	try (Connection con = super.getConnection()) {
		PreparedStatement stmt = con.prepareStatement(
				"update gebruiker "+
"set "
+ "adres='"+b.getAdres()+"',postcode='"+b.getPostCode()+"',plaatsnaam='"+b.getPlaatsNaam()+"',land='"+b.getLand()+"',"
+ "email='"+b.getEmail()+"',telefoonnummer="+b.getTelefoonNummer()+",kanVerkopen=?,banknummer='"+b.getBankNummer()+"' where gebruikersid="+b.getGebruikersID()+"");
		
		
			stmt.setBoolean(1, b.getKanVerkopen());
		
		stmt.executeUpdate();
		
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return findByCode(b.getGebruikersID());
}
//update password van gebruiker
public Gebruiker updatePassword (Gebruiker b){
	try (Connection con = super.getConnection()) {
		PreparedStatement stmt = con.prepareStatement(
				"update gebruiker "+
"set set wachtwoord ='"+b.getWachtWoord()+"' where gebruikersid="+b.getGebruikersID()+"");
		
		stmt.setDate(1, b.getGeboorteDag());
		
		
			stmt.setBoolean(2, b.getKanVerkopen());
		stmt.executeUpdate();
		
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return findByCode(b.getGebruikersID());
	
}

//nieuwe gebruiker aanmaken
public Gebruiker insert(Gebruiker b) {
	try (Connection con = super.getConnection()) {
		PreparedStatement stmt = con.prepareStatement(
				"insert into gebruiker"
				+ " values(nextval('gebruiker_sequence'),'"+b.getGebruikersNaam()+"','"+b.getVoorNaam()+"'"
						+ ",'"+b.getAchterNaam()+"','"+b.getAdres()+"'"
								+ ",'"+b.getPostCode()+"','"+b.getPlaatsNaam()+"','"+b.getLand()+"',?,"
										+ "'"+b.getEmail()+"',"+b.getTelefoonNummer()+",'"+b.getWachtWoord()+""
												+ "',?,'"+b.getBankNummer()+"')");
		
		stmt.setDate(1, b.getGeboorteDag());
		stmt.setBoolean(2, b.getKanVerkopen());
		stmt.executeUpdate();
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return findByUsername(b.getGebruikersNaam());
}
}