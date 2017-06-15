package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
	
import model.Voorwerp;

public class VoorwerpDAO extends BaseDAO{
		private ArrayList<Voorwerp> selectVoorwerpen(String query){
			ArrayList<Voorwerp> voorwerpenlijst = new ArrayList<Voorwerp>();
			try(Connection con = super.connect()){
				Statement stmt = con.createStatement();
		ResultSet dbrs = stmt.executeQuery(query);
		
		while (dbrs.next()){
			int voorwerpNummer = dbrs.getInt("voorwerpnummer");
			String titel = dbrs.getString("titel");
			String beschrijving  = dbrs.getString("beschrijving");
			double startprijs = dbrs.getDouble("startprijs");
			String betalingswijze  = dbrs.getString("betalingswijze");
			Timestamp begintijd = dbrs.getTimestamp("begintijd");
			Timestamp eindtijd = dbrs.getTimestamp("eindtijd");
			double verzendkosten = dbrs.getDouble("verzendkosten");
			String verzendinstructie = dbrs.getString("verzendinstructie");
			int verkoper = dbrs.getInt("verkoper");
			int koper = dbrs.getInt("koper");
			boolean veilingGesloten = dbrs.getBoolean("veilinggesloten");
		
			int verkoopprijs = dbrs.getInt("verkoopprijs");
			int rubriek = dbrs.getInt("rubriek");
			Voorwerp voorwerp = new Voorwerp(voorwerpNummer,titel,beschrijving,startprijs,betalingswijze,begintijd,eindtijd,verzendkosten,verzendinstructie,verkoper,koper,veilingGesloten,verkoopprijs,rubriek);
			voorwerpenlijst.add(voorwerp);
		}
			}
		catch(SQLException e){
			e.printStackTrace();
		}
			return voorwerpenlijst;
		}

	public ArrayList<Voorwerp> selectAll() {
		return selectVoorwerpen("SELECT * FROM voorwerp where veilinggesloten = false");
	}
	public ArrayList<Voorwerp> selectAllWhereNotUser(int verkoper) {
		return selectVoorwerpen("SELECT * FROM voorwerp where veilinggesloten = false and verkoper != "+verkoper+"");
	}

	public Voorwerp findByCode(int id) {
		return selectVoorwerpen("SELECT * FROM voorwerp WHERE voorwerpnummer = " + id + "").get(0);
	}
	public ArrayList<Voorwerp> findByRubriek(int rubriek) {
		return selectVoorwerpen("SELECT * FROM voorwerp WHERE rubriek = " + rubriek + " and veilinggesloten = false");
	}
	public ArrayList<Voorwerp> findByUser(int verkoper) {
		return selectVoorwerpen("SELECT * FROM voorwerp WHERE verkoper= " + verkoper + " ");
	}

	public Voorwerp update(Voorwerp b) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"update voorwerp "+
	"set eindtijd=? ,koper="+b.getKoper()+",veilingGesloten= true,"
	+ "verkoopprijs="+b.getVerkoopprijs()+"where voorwerpnummer="+b.getVoorwerpNummer()+"");
			
			stmt.setTimestamp(1, b.getEindTijd());
		
			stmt.executeUpdate();
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return findByCode(b.getVoorwerpNummer());
	}


	public Voorwerp insert(Voorwerp b) {
		try (Connection con = super.connect()) {
			
		
				PreparedStatement stmt = con.prepareStatement(
						"insert into voorwerp"
						+ " values(nextval('voorwerp_sequence'),'"+b.getTitel()+"','"+b.getBeschrijving()+"'"
								+ ","+b.getStartPrijs()+",'"+b.getBetalingswijze()+"',?"
										+ ",null,"+b.getVerzendkosten()+",'"+b.getVerzendinstructie()+"',"+b.getVerkoper()+","
												+ ""+b.getKoper()+",false,"+b.getVerkoopprijs()+""
														+ ","+b.getRubriek()+")");
				
				stmt.setTimestamp(1,b.getBeginTijd());

				stmt.executeUpdate();
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return b;
	}

	public boolean delete(int id){
		
		try(Connection con = super.connect()){
			Statement stmt = con.createStatement();
			
			String query = "delete from bod where voorwerp= "+id+"";
			stmt.executeUpdate(query);
			 query = "delete from voorwerp  where voorwerpnummer="+id+"";
			stmt.executeUpdate(query);
			return true;}
			catch (SQLException sqle){
				sqle.printStackTrace();
				return false;
				
			}
	}
	
	}

