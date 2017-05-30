package model;

import java.sql.Date;

public class Gebruiker {

	private Integer gebruikersID;
	private String gebruikersNaam;
	private String voorNaam;
	private String tussenVoegsel;
	private String achterNaam;
	private String adres ;
	private String postcCode;
	private String land;
	private Date geboorteDag;
	private String email;
	private Integer telefoonNummer;
	private String wachtWoord;
	private Boolean kanVerkopen;
	private String bankNummer;
	public Gebruiker(Integer gebruikersID, String gebruikersNaam, String voorNaam, String tussenVoegsel,
			String achterNaam, String adres, String postcCode, String land, Date geboorteDag, String email,
			Integer telefoonNummer, String wachtWoord, Boolean kanVerkopen, String bankNummer) {
		super();
		this.gebruikersID = gebruikersID;
		this.gebruikersNaam = gebruikersNaam;
		this.voorNaam = voorNaam;
		this.tussenVoegsel = tussenVoegsel;
		this.achterNaam = achterNaam;
		this.adres = adres;
		this.postcCode = postcCode;
		this.land = land;
		this.geboorteDag = geboorteDag;
		this.email = email;
		this.telefoonNummer = telefoonNummer;
		this.wachtWoord = wachtWoord;
		this.kanVerkopen = kanVerkopen;
		this.bankNummer = bankNummer;
	}
	public Integer getGebruikersID() {
		return gebruikersID;
	}
	public void setGebruikersID(Integer gebruikersID) {
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
	public String getTussenVoegsel() {
		return tussenVoegsel;
	}
	public void setTussenVoegsel(String tussenVoegsel) {
		this.tussenVoegsel = tussenVoegsel;
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
	public String getPostcCode() {
		return postcCode;
	}
	public void setPostcCode(String postcCode) {
		this.postcCode = postcCode;
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
	public Integer getTelefoonNummer() {
		return telefoonNummer;
	}
	public void setTelefoonNummer(Integer telefoonNummer) {
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
	@Override
	public String toString() {
		return "Gebruiker [gebruikersID=" + gebruikersID + ", gebruikersNaam=" + gebruikersNaam + ", voorNaam="
				+ voorNaam + ", tussenVoegsel=" + tussenVoegsel + ", achterNaam=" + achterNaam + ", adres=" + adres
				+ ", postcCode=" + postcCode + ", land=" + land + ", geboorteDag=" + geboorteDag + ", email=" + email
				+ ", telefoonNummer=" + telefoonNummer + ", wachtWoord=" + wachtWoord + ", kanVerkopen=" + kanVerkopen
				+ ", bankNummer=" + bankNummer + "]";
	}
	
	
}
