package restservices;

import java.sql.Date;
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

public class GebruikerResource {

	
	public String selectAllGebruikers(){
		
		
		return null;
	}
	
	
	public String selectGebruiker(@PathParam("id")int id){
		
		
		return null;
	}
	
	
	public String updateGebruiker(@PathParam("id")int id){
		
		
		return null;
	}
	
	
	public String newGebruiker(@FormParam("gebruikersnaam")String gebruikersnaam,@FormParam("voornaam")String voornaam,
			@FormParam("tussenvoegsel")String tussenvoegsel,@FormParam("achternaam")String achternaam,
			@FormParam("adres")String adres,@FormParam("postcode")String postcode,@FormParam("plaatsnaam")String plaatsnaam,
			@FormParam("land")String land,@FormParam("geboortedag")Date geboortedag,@FormParam("email")String email,
			@FormParam("telefoonnummer")int telefoonnummer,@FormParam("wachtwoord")String wachtwoord,
			@FormParam("kanverkopen")boolean kanverkopen,@FormParam("banknummer")String banknummer){
	
	return null;
	}
}
