package restservices;

import java.sql.Timestamp;


import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.BodDAO;
import dao.GebruikerDAO;
import dao.VoorwerpDAO;
import model.Bod;
import model.Gebruiker;
import model.Voorwerp;
@Path("/bod")
public class BodResource {
	BodDAO bdao = new BodDAO();
	GebruikerDAO gdao = new GebruikerDAO();
	VoorwerpDAO vdao = new VoorwerpDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	@GET
	@RolesAllowed({"verkoper","gebruiker"})
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
		@RolesAllowed({"verkoper","gebruiker"})
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
		@RolesAllowed({"verkoper","gebruiker"})
		@Path("/voorwerp/{code}")
		@Produces("application/json")
		public String getBodByVoowerp(@PathParam("code") int id){
			
			for(Bod b : bdao.findByVoorwerp(id)){
				System.out.println(b.getGebruiker());
			Gebruiker g = gdao.findByCode(b.getGebruiker());
			job.add("bodBedrag", b.getBodBedrag());
			job.add("bodID",b.getBodID());
			job.add("bodTijd", b.getBodTijd().getTime());
			job.add("gebruiker", g.getGebruikersNaam() );
			
			jab.add(job);
			}
			JsonArray array = jab.build();
			return array.toString();
			
		}
@GET
@RolesAllowed({"verkoper","gebruiker"})
		@Path("/voorwerp/hoogste/{code}")
		@Produces("application/json")
		public String getBodHighestVoorwerp(@PathParam("code") int id){
			Voorwerp v = vdao.findByCode(id);
			Bod b = bdao.findhighestBodByVoorwerp(id);
			if (b == null){
				return null;
			}
			else{
			Gebruiker g = gdao.findByCode(b.getGebruiker());

			job.add("bodBedrag", b.getBodBedrag());
			job.add("bodID",b.getBodID());
			job.add("bodTijd", b.getBodTijd().getTime());
			job.add("gebruiker", g.getGebruikersNaam());
			
			jab.add(job);
			
			JsonArray array = jab.build();
			return array.toString();
			}
		}
		@POST
		@RolesAllowed({"verkoper","gebruiker"})
		@Path("/{voorwerp}/{bieder}/{bod}")
		@Produces("application/json")
		public String createBod(@PathParam("bod") double bodbedrag,
				@PathParam("voorwerp") int voorwerpnummer,@PathParam("bieder")int bieder){
			Timestamp now = new Timestamp(System.currentTimeMillis());

			Bod b = new Bod(voorwerpnummer,bodbedrag,bieder,now);
			bdao.insert(b);
			return b.toString();
		}
}

