package restservices;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.RubriekDAO;
import model.Rubriek;

@Path("/rubriek")
public class RubriekResource {
	//dao's aanmaken
	
	RubriekDAO rdao = new RubriekDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	
	//handelt GET request voor elke rubriek
	//pad is restservices/rubriek
	@GET

	@Produces("application/json")
	public String getRubrieken(){
		
		for(Rubriek r: rdao.selectAll()){

			job.add("rubrieknummer", r.getRubriekNummer());
			job.add("rubrieknaam", r.getRubriekNaam());
			job.add("inrubriek", r.getInRubriek());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	//handelt GET request voor specifieke rubriek
		//pad is restservices/rubriek/2
	@GET
	
	@Path("/{code}")
	@Produces("application/json")
	public String getRubriek(@PathParam("code")int code){
		Rubriek r = rdao.findByCode(code);
			job.add("rubrieknummer", r.getRubriekNummer());
			job.add("rubrieknaam", r.getRubriekNaam());
			job.add("inrubriek", r.getInRubriek());
			jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
		
	}
	//handelt GET request voor bovenste rubrieken
		//pad is restservices/rubriek/bovenste
	@GET

	@Path("/bovenste")
	@Produces("application/json")
	public String getBovensteRubrieken(){
		
		for(Rubriek r: rdao.selectBovensteRubriek()){

			job.add("rubrieknummer", r.getRubriekNummer());
			job.add("rubrieknaam", r.getRubriekNaam());
			job.add("inrubriek", r.getInRubriek());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
	//handelt GET request voor elke rubriek onder een rubriek
		//pad is restservices/rubriek/onder/2
	@GET
	
	@Path("/onder/{code}")
	@Produces("application/json")
	public String getBovensteRubrieken(@PathParam("code")int code){
		
		for(Rubriek r: rdao.selectRubriekenInRubriek(code)){

			job.add("rubrieknummer", r.getRubriekNummer());
			job.add("rubrieknaam", r.getRubriekNaam());
			job.add("inrubriek", r.getInRubriek());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
}
