package model;

import java.sql.Timestamp;

public class Voorwerp {
	//attributen
	private Integer voorwerpNummer;
	private String titel;
	private String beschrijving;
	private double startPrijs;
	private String betalingswijze;
	private Timestamp beginTijd;
	private Timestamp eindTijd;
	private double verzendkosten;
	private String verzendinstructie;
	private Integer verkoper;
	private Integer koper;
	private boolean veilingGesloten;
	private double verkoopprijs;
	private Integer rubriek;
	

	
	//constructor gebruikt bij insert dao
	public Voorwerp(String titel, String beschrijving, double startPrijs, String betalingswijze, Timestamp beginTijd,
			double verzendkosten, String verzendinstructie, Integer verkoper, boolean veilingGesloten,
			Integer rubriek) {
		super();
		this.titel = titel;
		this.beschrijving = beschrijving;
		this.startPrijs = startPrijs;
		this.betalingswijze = betalingswijze;
		this.beginTijd = beginTijd;
		this.verzendkosten = verzendkosten;
		this.verzendinstructie = verzendinstructie;
		this.verkoper = verkoper;
		this.veilingGesloten = veilingGesloten;
		this.rubriek = rubriek;
	}
	//constructor gebruikt bij select dao
	public Voorwerp(Integer voorwerpNummer, String titel, String beschrijving, double startPrijs, String betalingswijze,
			Timestamp beginTijd, Timestamp eindTijd, double verzendkosten, String verzendinstructie, Integer verkoper,
			Integer koper, boolean veilingGesloten, double verkoopprijs, Integer rubriek) {
		super();
		this.voorwerpNummer = voorwerpNummer;
		this.titel = titel;
		this.beschrijving = beschrijving;
		this.startPrijs = startPrijs;
		this.betalingswijze = betalingswijze;
		this.beginTijd = beginTijd;
		this.eindTijd = eindTijd;
		this.verzendkosten = verzendkosten;
		this.verzendinstructie = verzendinstructie;
		this.verkoper = verkoper;
		this.koper = koper;
		this.veilingGesloten = veilingGesloten;
		this.verkoopprijs = verkoopprijs;
		this.rubriek = rubriek;
	}
	//getters en setters
	public double getVerkoopprijs() {
		return verkoopprijs;
	}
	public void setVerkoopprijs(double verkoopprijs) {
		this.verkoopprijs = verkoopprijs;
	}
	public Integer getVoorwerpNummer() {
		return voorwerpNummer;
	}
	public void setVoorwerpNummer(Integer voorwerpNummer) {
		this.voorwerpNummer = voorwerpNummer;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public double getStartPrijs() {
		return startPrijs;
	}
	public void setStartPrijs(double startPrijs) {
		this.startPrijs = startPrijs;
	}
	public String getBetalingswijze() {
		return betalingswijze;
	}
	public void setBetalingswijze(String betalingswijze) {
		this.betalingswijze = betalingswijze;
	}
	public Timestamp getBeginTijd() {
		return beginTijd;
	}
	public void setBeginTijd(Timestamp beginTijd) {
		this.beginTijd = beginTijd;
	}
	public Timestamp getEindTijd() {
		return eindTijd;
	}
	public void setEindTijd(Timestamp eindTijd) {
		this.eindTijd = eindTijd;
	}
	public double getVerzendkosten() {
		return verzendkosten;
	}
	public void setVerzendkosten(double verzendkosten) {
		this.verzendkosten = verzendkosten;
	}
	public String getVerzendinstructie() {
		return verzendinstructie;
	}
	public void setVerzendinstructie(String verzendinstructie) {
		this.verzendinstructie = verzendinstructie;
	}
	public Integer getVerkoper() {
		return verkoper;
	}
	public void setVerkoper(Integer verkoper) {
		this.verkoper = verkoper;
	}
	public Integer getKoper() {
		return koper;
	}
	public void setKoper(Integer koper) {
		this.koper = koper;
	}
	public boolean isVeilingGesloten() {
		return veilingGesloten;
	}
	public void setVeilingGesloten(boolean veilingGesloten) {
		this.veilingGesloten = veilingGesloten;
	}
	public Integer getRubriek() {
		return rubriek;
	}
	public void setRubriek(Integer rubriek) {
		this.rubriek = rubriek;
	}
	//tostring
	@Override
	public String toString() {
		return "Voorwerp [voorwerpNummer=" + voorwerpNummer + ", titel=" + titel + ", beschrijving=" + beschrijving
				+ ", startPrijs=" + startPrijs + ", betalingswijze=" + betalingswijze + ", beginTijd=" + beginTijd
				+ ", eindTijd=" + eindTijd + ", verzendkosten=" + verzendkosten + ", verzendinstructie="
				+ verzendinstructie + ", verkoper=" + verkoper + ", koper=" + koper + ", veilingGesloten="
				+ veilingGesloten + ", rubriek=" + rubriek + "]";
	}
	
}
