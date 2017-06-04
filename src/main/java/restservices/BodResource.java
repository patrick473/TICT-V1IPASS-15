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

import dao.BodDAO;
import model.Bod;
@Path("/bod")
public class BodResource {
	BodDAO bdao = new BodDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	@GET
	@RolesAllowed({"gebruiker","verkoper"})
	@Produces("application/json")
	public String getBoden(){
		for (Bod b: bdao.selectAll()){
			
			job.add("bodBedrag", b.getBodBedrag());
			job.add("bodID",b.getBodID());
			job.add("bodTijd", b.getBodTijd().getTime());
			job.add("gebruiker", b.getGebruiker() );
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}





		@GET
		@RolesAllowed({"gebruiker","verkoper"})
		@Path("/{code}")
		@Produces("application/json")
		public String getBodByID(@PathParam("code") int id){
			Bod b = bdao.findByCode(id);

			job.add("bodBedrag", b.getBodBedrag());
			job.add("bodID",b.getBodID());
			job.add("bodTijd", b.getBodTijd().getTime());
			job.add("gebruiker", b.getGebruiker() );
			
			jab.add(job);
			JsonArray array = jab.build();
			return array.toString();
			
		}
	
		
		@GET
		@RolesAllowed({"gebruiker","verkoper"})
		@Path("/voorwerp/{voorwerpnummer}")
		@Produces("application/json")
		public String getBodenBijVoorwerp(@PathParam("voorwerp")int voorwerpnummer){
			for (Bod b: bdao.findByVoorwerp(voorwerpnummer)){
				
				job.add("bodBedrag", b.getBodBedrag());
				job.add("bodID",b.getBodID());
				job.add("bodTijd", b.getBodTijd().getTime());
				job.add("gebruiker", b.getGebruiker() );
				
				jab.add(job);
			}
			JsonArray array = jab.build();
			return array.toString();
			
		}
}
