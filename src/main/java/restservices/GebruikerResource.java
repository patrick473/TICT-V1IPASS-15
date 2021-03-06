package restservices;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Gebruiker;
import dao.GebruikerDAO;
@Path("/gebruiker")
public class GebruikerResource {
	//DAO's worden aangemaakt
	GebruikerDAO gdao = new GebruikerDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	//formaat van datum
	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
	 
	 //handelt GET request om alle gebruikers te selecteren
	 //pad is restservices/gebruiker
	@GET
	//voor testen is dit opengelaten zie dit als @RolesAllowed({"verkoper","gebruiker"})
	@Produces("application/json")
	public String selectAllGebruikers(){
for (Gebruiker g: gdao.selectAll()){
			
			job.add("gebruikerID", g.getGebruikersID());
			job.add("gebruikersnaam",g.getGebruikersNaam());
			job.add("voornaam", g.getVoorNaam());
			job.add("achternaam", g.getAchterNaam());
			job.add("achternaam", g.getAchterNaam());
			job.add("adres", g.getAdres());
			job.add("postcode", g.getPostCode());
			job.add("plaatsnaam", g.getPlaatsNaam());
			job.add("land", g.getLand());
			job.add("geboortedag", sdf.format(g.getGeboorteDag()));
			job.add("email", g.getEmail());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
	//handelt GET request om een specifieke gebruiker te selecteren
	//alleen verkoper en gebruiker mogen dit
	//pad is bv restservices/gebruiker/1
	@GET
	@RolesAllowed({"verkoper","gebruiker"})
	@Path("/{id}")
	@Produces("application/json")
	public String selectGebruiker(@PathParam("id")int id){
		Gebruiker g = gdao.findByCode(id);
		job.add("gebruikerID", g.getGebruikersID());
		job.add("gebruikersnaam",g.getGebruikersNaam());
		job.add("voornaam", g.getVoorNaam());
	
		job.add("achternaam", g.getAchterNaam());
		job.add("achternaam", g.getAchterNaam());
		job.add("adres", g.getAdres());
		job.add("postcode", g.getPostCode());
		job.add("plaatsnaam", g.getPlaatsNaam());
		job.add("land", g.getLand());
		job.add("geboortedag", sdf.format(g.getGeboorteDag()));
		job.add("email", g.getEmail());
		job.add("telefoonnummer",g.getTelefoonNummer());
		job.add("kanverkopen", g.getKanVerkopen());
		if(g.getBankNummer() != null){
			job.add("banknummer", g.getBankNummer());
			}
		jab.add(job);
	
	JsonArray array = jab.build();
	return array.toString();
		
	}
//handelt GET request om een gebruiker op username te selecteren
// alleen verkoper en gebruiker mogen dit
// pad is bv. restservices/gebruiker/username/3
@GET
	@RolesAllowed({"verkoper","gebruiker"})
	@Path("/username/{id}")
	@Produces("application/json")
	public String selectGebruikerByUsername(@PathParam("id")String id){
		Gebruiker g = gdao.findByUsername(id);
		job.add("gebruikerID", g.getGebruikersID());
		job.add("gebruikersnaam",g.getGebruikersNaam());
		job.add("voornaam", g.getVoorNaam());
	
		job.add("achternaam", g.getAchterNaam());
		job.add("achternaam", g.getAchterNaam());
		job.add("adres", g.getAdres());
		job.add("postcode", g.getPostCode());
		job.add("plaatsnaam", g.getPlaatsNaam());
		job.add("land", g.getLand());
		job.add("geboortedag", sdf.format(g.getGeboorteDag()));
		job.add("email", g.getEmail());
		job.add("telefoonnummer",g.getTelefoonNummer());
		job.add("kanverkopen", g.getKanVerkopen());
		if(g.getBankNummer() != null){
		job.add("banknummer", g.getBankNummer());
		}
		jab.add(job);
	
	JsonArray array = jab.build();
	return array.toString();
		
	}
//handelt PUT request om gebruiker te updaten
//alleen verkoper en gebruikers mogen dit
// pad is bv restservices/gebruiker/2/true
//data moet een updateform bevatten
	@PUT
	@RolesAllowed({"verkoper","gebruiker"})
	@Path("/{id}/{kanverkopen}")
	@Produces("application/json")
	public String updateGebruiker(@PathParam("id")int id,
		
			@FormParam("adres")String adres,@FormParam("postcode")String postcode,@FormParam("plaatsnaam")String plaatsnaam,
			@FormParam("land")String land,@FormParam("email")String email,
			@FormParam("telefoonnummer")int telefoonnummer,
			@PathParam("kanverkopen")boolean kanverkopen,@FormParam("banknummer")String banknummer){
		
		Gebruiker g = new Gebruiker(id,adres,postcode,
				plaatsnaam,land,email,telefoonnummer,kanverkopen,banknummer);
		gdao.update(g);
		return null;
	}
	//handelt PUT request om wachtwoord te updaten
	//alleen verkoper en gebruikers mogen dit
	// pad is bv restservices/gebruiker/new/2
	//data moet een passwordform bevatten
	//wordt niet gebruikt
	@PUT
	
	@Path("/password/{id}")
	@Produces("application/json")
	public String updateGebruikerPassword(@PathParam("id")int id,@FormParam("wachtwoord")String wachtwoord){
		Gebruiker g = new Gebruiker(id,wachtwoord);
		gdao.updatePassword(g);
		
		return null;
	}
	//handelt POST request om gebruiker te maken

	// pad is bv restservices/gebruiker/true
	//data moet een registerform bevatten
	@POST

	@Path("/new/{kanverkopen}")
	@Produces("application/json")
	public String newGebruiker(@FormParam("gebruikersnaam")String gebruikersnaam,@FormParam("voornaam")String voornaam,
			@FormParam("achternaam")String achternaam,
			@FormParam("adres")String adres,@FormParam("postcode")String postcode,@FormParam("plaatsnaam")String plaatsnaam,
			@FormParam("land")String land,@FormParam("geboortedag")Date geboortedag,@FormParam("email")String email,
			@FormParam("telefoonnummer")int telefoonnummer,@FormParam("wachtwoord")String wachtwoord,
			@PathParam("kanverkopen")boolean kanverkopen,@FormParam("banknummer")String banknummer){
	try{
		Gebruiker g = new Gebruiker(gebruikersnaam,voornaam,achternaam,adres,postcode,
				plaatsnaam,land,geboortedag,email,telefoonnummer,wachtwoord,kanverkopen,banknummer);
		gdao.insert(g);
	}
	catch(Error e){
		System.out.println(e);
	}
	return null;
	}
}
