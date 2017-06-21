package model;

public class Rubriek {
	//attributen
	private Integer rubriekNummer;
	private String rubriekNaam;
	private Integer inRubriek;
	//constructor gebruikt voor select statements
	public Rubriek(Integer rubriekNummer, String rubriekNaam, Integer inRubriek) {
		super();
		this.rubriekNummer = rubriekNummer;
		this.rubriekNaam = rubriekNaam;
		this.inRubriek = inRubriek;
	}
	//getters en setters
	public Integer getRubriekNummer() {
		return rubriekNummer;
	}
	public void setRubriekNummer(Integer rubriekNummer) {
		this.rubriekNummer = rubriekNummer;
	}
	public String getRubriekNaam() {
		return rubriekNaam;
	}
	public void setRubriekNaam(String rubriekNaam) {
		this.rubriekNaam = rubriekNaam;
	}
	public Integer getInRubriek() {
		return inRubriek;
	}
	public void setInRubriek(Integer inRubriek) {
		this.inRubriek = inRubriek;
	}
	//tostring voor testen
	@Override
	public String toString() {
		return "Rubriek [rubriekNummer=" + rubriekNummer + ", rubriekNaam=" + rubriekNaam + ", inRubriek=" + inRubriek
				+ "]";
	}
	
}
