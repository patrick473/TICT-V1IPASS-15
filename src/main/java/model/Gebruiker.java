package model;

import java.sql.Date;

public class Gebruiker {
	//attributen
	private int gebruikersID;
	private String gebruikersNaam;
	private String voorNaam;
	private String achterNaam;
	private String adres ;
	private String postCode;
	private String plaatsNaam;
	private String land;
	private Date geboorteDag;
	private String email;
	private int telefoonNummer;
	private String wachtWoord;
	private Boolean kanVerkopen;
	private String bankNummer;
	
	//constructor wordt niet gebruikt bedoeld voor wachtwoord veranderen
	public Gebruiker(int gebruikersID, String wachtWoord) {
		super();
		this.gebruikersID = gebruikersID;
		this.wachtWoord = wachtWoord;
	}
	
	//constructor om gegevens te updaten
	public Gebruiker(int gebruikersID, String adres, String postCode, String plaatsNaam, String land, String email,
			int telefoonNummer, Boolean kanVerkopen, String bankNummer) {
		super();
		this.gebruikersID = gebruikersID;
		this.adres = adres;
		this.postCode = postCode;
		this.plaatsNaam = plaatsNaam;
		this.land = land;
		this.email = email;
		this.telefoonNummer = telefoonNummer;
		this.kanVerkopen = kanVerkopen;
		this.bankNummer = bankNummer;
	}

	// constructor om nieuwe gebruiker aan te maken bij registratie
	public Gebruiker(String gebruikersNaam, String voorNaam, String achterNaam, String adres,
			String postCode, String plaatsNaam, String land, Date geboorteDag, String email, int telefoonNummer,
			String wachtWoord, Boolean kanVerkopen, String bankNummer) {
		super();
		this.gebruikersNaam = gebruikersNaam;
		this.voorNaam = voorNaam;
		
		this.achterNaam = achterNaam;
		this.adres = adres;
		this.postCode = postCode;
		this.plaatsNaam = plaatsNaam;
		this.land = land;
		this.geboorteDag = geboorteDag;
		this.email = email;
		this.telefoonNummer = telefoonNummer;
		this.wachtWoord = wachtWoord;
		this.kanVerkopen = kanVerkopen;
		this.bankNummer = bankNummer;
	}
	//constructor om gebruiker aan te maken om door te sturen bij select statements bij de dao's
	public Gebruiker(Integer gebruikersID, String gebruikersNaam, String voorNaam, 
			String achterNaam, String adres, String postcCode,String plaatsNaam, String land, Date geboorteDag, String email,
			Integer telefoonNummer, String wachtWoord, Boolean kanVerkopen, String bankNummer) {
		super();
		this.gebruikersID = gebruikersID;
		this.gebruikersNaam = gebruikersNaam;
		this.voorNaam = voorNaam;
		
		this.achterNaam = achterNaam;
		this.adres = adres;
		this.postCode = postcCode;
		this.plaatsNaam = plaatsNaam;
		this.land = land;
		this.geboorteDag = geboorteDag;
		this.email = email;
		this.telefoonNummer = telefoonNummer;
		this.wachtWoord = wachtWoord;
		this.kanVerkopen = kanVerkopen;
		this.bankNummer = bankNummer;
	}
	//getters and setters voor alle attributen
	public int getGebruikersID() {
		return gebruikersID;
	}
	public void setGebruikersID(int gebruikersID) {
		this.gebruikersID = gebruikersID;
	}
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public String getVoorNaam() {
		return voorNaam;
	}
	public void setVoorNaam(String voorNaam) {
		this.voorNaam = voorNaam;
	}
	
	public String getAchterNaam() {
		return achterNaam;
	}
	public void setAchterNaam(String achterNaam) {
		this.achterNaam = achterNaam;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getPlaatsNaam() {
		return plaatsNaam;
	}
	public void setPlaatsNaam(String plaatsNaam) {
		this.plaatsNaam = plaatsNaam;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public Date getGeboorteDag() {
		return geboorteDag;
	}
	public void setGeboorteDag(Date geboorteDag) {
		this.geboorteDag = geboorteDag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefoonNummer() {
		return telefoonNummer;
	}
	public void setTelefoonNummer(int telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}
	public String getWachtWoord() {
		return wachtWoord;
	}
	public void setWachtWoord(String wachtWoord) {
		this.wachtWoord = wachtWoord;
	}
	public Boolean getKanVerkopen() {
		return kanVerkopen;
	}
	public void setKanVerkopen(Boolean kanVerkopen) {
		this.kanVerkopen = kanVerkopen;
	}
	public String getBankNummer() {
		return bankNummer;
	}
	public void setBankNummer(String bankNummer) {
		this.bankNummer = bankNummer;
	}
	//tostring voor testen
	@Override
	public String toString() {
		return "Gebruiker [gebruikersID=" + gebruikersID + ", gebruikersNaam=" + gebruikersNaam + ", voorNaam="
				+ voorNaam + ", achterNaam=" + achterNaam + ", adres=" + adres
				+ ", postCode=" + postCode + ", plaatsNaam=" + plaatsNaam + ", land=" + land + ", geboorteDag="
				+ geboorteDag + ", email=" + email + ", telefoonNummer=" + telefoonNummer + ", wachtWoord=" + wachtWoord
				+ ", kanVerkopen=" + kanVerkopen + ", bankNummer=" + bankNummer + "]";
	}

	
}
