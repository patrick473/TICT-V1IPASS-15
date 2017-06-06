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
	
	GebruikerDAO gdao = new GebruikerDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	 SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YY");
	@GET
	@RolesAllowed({"gebruiker","verkoper"})
	@Produces("application/json")
	public String selectAllGebruikers(){
for (Gebruiker g: gdao.selectAll()){
			
			job.add("gebruikerID", g.getGebruikersID());
			job.add("gebruikersnaam",g.getGebruikersNaam());
			job.add("voornaam", g.getVoorNaam());
			job.add("tussenvoegsel", g.getTussenVoegsel() );
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
	
	@GET
	@RolesAllowed({"gebruiker","verkoper"})
	@Path("/{id}")
	@Produces("application/json")
	public String selectGebruiker(@PathParam("id")int id){
		Gebruiker g = gdao.findByCode(id);
		job.add("gebruikerID", g.getGebruikersID());
		job.add("gebruikersnaam",g.getGebruikersNaam());
		job.add("voornaam", g.getVoorNaam());
		job.add("tussenvoegsel", g.getTussenVoegsel() );
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
		job.add("banknummer", g.getBankNummer());
		jab.add(job);
	
	JsonArray array = jab.build();
	return array.toString();
		
	}
	
	@PUT
	@RolesAllowed({"gebruiker","verkoper"})
	@Path("/{id}")
	@Produces("application/json")
	public String updateGebruiker(@PathParam("id")int id,@FormParam("gebruikersnaam")String gebruikersnaam,
			@FormParam("voornaam")String voornaam,
			@FormParam("tussenvoegsel")String tussenvoegsel,@FormParam("achternaam")String achternaam,
			@FormParam("adres")String adres,@FormParam("postcode")String postcode,@FormParam("plaatsnaam")String plaatsnaam,
			@FormParam("land")String land,@FormParam("geboortedag")Date geboortedag,@FormParam("email")String email,
			@FormParam("telefoonnummer")int telefoonnummer,
			@FormParam("kanverkopen")boolean kanverkopen,@FormParam("banknummer")String banknummer){
		
		Gebruiker g = new Gebruiker(id,gebruikersnaam,voornaam,tussenvoegsel,achternaam,adres,postcode,
				plaatsnaam,land,geboortedag,email,telefoonnummer,kanverkopen,banknummer);
		gdao.update(g);
		return g.toString();
	}
	@PUT
	@RolesAllowed({"gebruiker","verkoper"})
	@Path("/{id}")
	@Produces("application/json")
	public String updateGebruikerPassword(@PathParam("id")int id,@FormParam("wachtwoord")String wachtwoord){
		Gebruiker g = new Gebruiker(id,wachtwoord);
		gdao.updatePassword(g);
		
		return g.toString();
	}
	@POST
	@RolesAllowed({"gebruiker","verkoper"})
	@Path("/new")
	@Produces("application/json")
	public String newGebruiker(@FormParam("gebruikersnaam")String gebruikersnaam,@FormParam("voornaam")String voornaam,
			@FormParam("tussenvoegsel")String tussenvoegsel,@FormParam("achternaam")String achternaam,
			@FormParam("adres")String adres,@FormParam("postcode")String postcode,@FormParam("plaatsnaam")String plaatsnaam,
			@FormParam("land")String land,@FormParam("geboortedag")Date geboortedag,@FormParam("email")String email,
			@FormParam("telefoonnummer")int telefoonnummer,@FormParam("wachtwoord")String wachtwoord,
			@FormParam("kanverkopen")boolean kanverkopen,@FormParam("banknummer")String banknummer){
	
		Gebruiker g = new Gebruiker(gebruikersnaam,voornaam,tussenvoegsel,achternaam,adres,postcode,
				plaatsnaam,land,geboortedag,email,telefoonnummer,wachtwoord,kanverkopen,banknummer);
		gdao.insert(g);
		
	return g.toString();
	}
}
