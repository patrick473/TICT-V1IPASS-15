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

import model.Bod;
import model.Rubriek;
import model.Voorwerp;
import dao.BodDAO;
import dao.RubriekDAO;
import dao.VoorwerpDAO;


@Path("/voorwerp")
public class VoorwerpResource {
	//dao's worden aangemaakt
	VoorwerpDAO vdao = new VoorwerpDAO();
	RubriekDAO rdao = new RubriekDAO();
	BodDAO bdao = new BodDAO();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	//handelt GET request om alle voorwerpen te selecteren
	//alleen verkopers en gebruikers mogen dit
	//pad is restservices/voorwerp
	@GET
	@RolesAllowed({"verkoper","gebruiker"})
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
	//handelt GET request om een specifiek voorwerp te selecteren
	//alleen verkopers en gebruikers mogen dit
	//pad is bv. restservices/voorwerp/2
	@GET
	@RolesAllowed({"verkoper","gebruiker"})
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
	//handelt GET request om voorwerpen binnen een rubriek te vinden
	//alleen verkopers en gebruikers mogen dit
	//pad is bv. restservices/voorwerp/rubriek/35
	@GET
	@RolesAllowed({"verkoper","gebruiker"})
	@Path("/rubriek/{rubriek}")
	@Produces("application/json")
	public String findByRubriek(@PathParam("rubriek")int rubriek){
	
		for( Voorwerp v : vdao.findByRubriek(rubriek)){
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
		}
	JsonArray array = jab.build();
	return array.toString();
		
	}
	//handelt GET request om actieve voorwerpen te selecteren van een gebruiker
	//alleen verkopers mogen dit
	//pad is bv. restservices/gebruiker/2
@GET
@RolesAllowed("verkoper")
	@Path("/gebruiker/{id}")
	@Produces("application/json")
	public String findByUser(@PathParam("id")int id){
	
		for( Voorwerp v : vdao.findByUser(id)){
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
		}
	JsonArray array = jab.build();
	return array.toString();
		
	}
//handelt GET request om gesloten voorwerpen te selecteren van een gebruiker
	//alleen verkopers mogen dit
	//pad is bv. restservices/gebruiker/gesloten/2
@GET
@RolesAllowed("verkoper")
	@Path("/gebruiker/gesloten/{id}")
	@Produces("application/json")
	public String findByUserGesloten(@PathParam("id")int id){
	
		for( Voorwerp v : vdao.findByUserGesloten(id)){
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
		}
	JsonArray array = jab.build();
	return array.toString();
		
	}

//handelt POST request om een nieuw voorwerp aan te maken
//alleen verkopers mogen dit
//pad is restservices/voorwerp/new
//data moet registerform bevatten
	@POST
	@RolesAllowed("verkoper")
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
		return null;
	}
	//handelt PUT request om een veiling te beeindigen
	//alleen verkopers mogen dit
	//pad is bv. restservices.voorwerp/end/5
	
	@PUT
	@RolesAllowed("verkoper")
	@Path("/end/{id}")
	@Produces("application/json")
	public String endVeiling(@PathParam("id")int id){
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Voorwerp v1 = vdao.findByCode(id);
		Bod b= bdao.findhighestBodByVoorwerp(v1.getVoorwerpNummer());
		v1.setVerkoopprijs(b.getBodBedrag());
		int  koper = b.getGebruiker();
		v1.setKoper(koper);
		v1.setVeilingGesloten(true);
		v1.setEindTijd(now);
		double verkoopprijs = b.getBodBedrag();
		
			vdao.update(v1);
		return null;
	}
	//handelt DELETE request om een veiling te verwijderen
		//alleen verkopers mogen dit
		//pad is bv. restservices.voorwerp/delete/5
	@DELETE
	@RolesAllowed("verkoper")
	@Produces("application/json")
	@Path("/delete/{id}")
	public String deleteVoorwerp(@PathParam("id")int id){
		try{
		vdao.delete(id);
		System.out.println("succes");
		return null;
		}
		catch(Error e){
			System.out.println(e);
			return "failed";
			
		}
	}
	
	
	
	
}
