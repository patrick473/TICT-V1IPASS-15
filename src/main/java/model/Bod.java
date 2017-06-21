package model;

import java.sql.Timestamp;

public class Bod {
	//attributen
	private Integer bodID;
	private Integer voorwerpID;
	private double bodBedrag;
	private Integer gebruiker;
	private Timestamp bodTijd;
	//constructor voor nieuw bod
	public Bod(Integer voorwerpID, double bodBedrag, Integer gebruiker, Timestamp bodTijd) {
		super();
		this.voorwerpID = voorwerpID;
		this.bodBedrag = bodBedrag;
		this.gebruiker = gebruiker;
		this.bodTijd = bodTijd;
	}
	//constructor voor select bod
	
	public Bod(Integer bodID, Integer voorwerpID, double bodBedrag, Integer gebruiker, Timestamp bodTijd) {
		super();
		this.bodID = bodID;
		this.voorwerpID = voorwerpID;
		this.bodBedrag = bodBedrag;
		this.gebruiker = gebruiker;
		this.bodTijd = bodTijd;
	}
	//getters en setters voor alle attributen
	public Integer getBodID() {
		return bodID;
	}
	public void setBodID(Integer bodID) {
		this.bodID = bodID;
	}
	public Integer getVoorwerpID() {
		return voorwerpID;
	}
	public void setVoorwerpID(Integer voorwerpID) {
		this.voorwerpID = voorwerpID;
	}
	public double getBodBedrag() {
		return bodBedrag;
	}
	public void setBodBedrag(Integer bodBedrag) {
		this.bodBedrag = bodBedrag;
	}
	public Integer getGebruiker() {
		return gebruiker;
	}
	public void setGebruiker(Integer gebruiker) {
		this.gebruiker = gebruiker;
	}
	public Timestamp getBodTijd() {
		return bodTijd;
	}
	public void setBodTijd(Timestamp bodTijd) {
		this.bodTijd = bodTijd;
	}
	//tostring voor testen
	@Override
	public String toString() {
		return "Bod [bodID=" + bodID + ", voorwerpID=" + voorwerpID + ", bodBedrag=" + bodBedrag + ", gebruiker="
				+ gebruiker + ", bodTijd=" + bodTijd + "]";
	}
	
	
}
