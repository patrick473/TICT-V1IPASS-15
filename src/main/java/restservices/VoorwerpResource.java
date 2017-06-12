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
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Rubriek;
import model.Voorwerp;
import dao.RubriekDAO;
import dao.VoorwerpDAO;


@Path("/voorwerp")
public class VoorwerpResource {
	
	VoorwerpDAO vdao = new VoorwerpDAO();
	RubriekDAO rdao = new RubriekDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	
	@GET

	@Produces("application/json")
	public String selectAll(){
for (Voorwerp v: vdao.selectAll()){
			Rubriek r = rdao.findByCode(v.getRubriek());
			job.add("voorwerpnummer", v.getVoorwerpNummer());
			job.add("titel",v.getTitel());
			job.add("beschrijving", v.getBeschrijving());
			job.add("startprijs", v.getStartPrijs() );
			job.add("betalingswijze", v.getBetalingswijze());
			job.add("begintijd", v.getBeginTijd().getTime());
			job.add("verzendkosten", v.getVerzendkosten());
			job.add("verzendinstructie", v.getVerzendinstructie());
			job.add("verkoper", v.getVerkoper());
			job.add("koper", v.getKoper());
			job.add("veilingGesloten", v.isVeilingGesloten() );
			job.add("verkoopprijs", v.getVerkoopprijs());
			job.add("rubriek", v.getRubriek());
			job.add("rubrieknaam", r.getRubriekNaam());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
	@GET
	
	@Path("/{id}")
	@Produces("application/json")
	public String findByCode(@PathParam("id")int id){
		
		Voorwerp v = vdao.findByCode(id);
		Rubriek r = rdao.findByCode(v.getRubriek());
		job.add("voorwerpnummer", v.getVoorwerpNummer());
		job.add("titel",v.getTitel());
		job.add("beschrijving", v.getBeschrijving());
		job.add("startprijs", v.getStartPrijs() );
		job.add("betalingswijze", v.getBetalingswijze());
		job.add("begintijd", v.getBeginTijd().getTime());
		
		job.add("verzendkosten", v.getVerzendkosten());
		job.add("verzendinstructie", v.getVerzendinstructie());
		job.add("verkoper", v.getVerkoper());
		job.add("koper", v.getKoper());
		job.add("veilingGesloten", v.isVeilingGesloten() );
		job.add("verkoopprijs", v.getVerkoopprijs());
		job.add("rubriek", v.getRubriek());
		job.add("rubrieknaam", r.getRubriekNaam());
		jab.add(job);
	
	JsonArray array = jab.build();
	return array.toString();
		
	}
	@GET
	
	@Path("/rubriek/{rubriek}")
	@Produces("application/json")
	public String findByRubriek(@PathParam("rubriek")int rubriek){
	
		Voorwerp v = vdao.findByRubriek(rubriek);
		job.add("voorwerpnummer", v.getVoorwerpNummer());
		job.add("titel",v.getTitel());
		job.add("beschrijving", v.getBeschrijving());
		job.add("startprijs", v.getStartPrijs() );
		job.add("betalingswijze", v.getBetalingswijze());
		job.add("begintijd", v.getBeginTijd().getTime());
		
		job.add("verzendkosten", v.getVerzendkosten());
		job.add("verzendinstructie", v.getVerzendinstructie());
		job.add("verkoper", v.getVerkoper());
		job.add("koper", v.getKoper());
		job.add("veilingGesloten", v.isVeilingGesloten() );
		job.add("verkoopprijs", v.getVerkoopprijs());
		job.add("rubriek", v.getRubriek());
		jab.add(job);
	
	JsonArray array = jab.build();
	return array.toString();
		
	}
	@POST
	
	@Path("/new")
	@Produces("application/json")
	public String insertVoorwerp(@FormParam("titel") String titel,
			@FormParam("beschrijving") String beschrijving,
			@FormParam("startprijs") double startprijs,
			@FormParam("betalingswijze") String betalingswijze,
			
			@FormParam("verzendkosten") double verzendkosten,
			@FormParam("verzendinstructie") String verzendinstructie ,
			@FormParam("verkoper") int verkoper,
			@FormParam("rubriek")int rubriek){
		Timestamp now = new Timestamp(System.currentTimeMillis());

		Voorwerp v = new Voorwerp(titel,beschrijving,startprijs,betalingswijze,now,verzendkosten,verzendinstructie,verkoper,false,rubriek);
		
		vdao.insert(v);
		return v.toString();
	}
	
	@PUT

	@Path("/end/{id}")
	@Produces("application/json")
	public String endVeiling(@PathParam("id")int id,@FormParam("koper")int koper,
		@FormParam("verkoopprijs")double verkoopprijs){
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		Voorwerp v = new Voorwerp(id,now,koper,true,verkoopprijs);
			vdao.update(v);
		return v.toString();
	}
	@DELETE

	@Produces("application/json")
	@Path("/delete/{id}")
	public String deleteVoorwerp(@PathParam("id")int id){
		try{
		vdao.delete(id);
		return "succes";
		}
		catch(Error e){
			System.out.println(e);
			return "failed";
			
		}
	}
	
	
	
	
}
