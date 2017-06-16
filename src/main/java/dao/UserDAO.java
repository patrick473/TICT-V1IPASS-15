package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
	 public String findRoleForUsernameAndPassword(String username, String password) {
	 String role = null;
	 String query = "SELECT kanverkopen FROM gebruiker WHERE gebruikersnaam = ? AND wachtwoord = ?";

	 try (Connection con = super.getConnection()) {

	 PreparedStatement pstmt = con.prepareStatement(query);
	 pstmt.setString(1, username);
	 pstmt.setString(2, password);

	 ResultSet rs = pstmt.executeQuery();
	 if (rs.next()){
		 boolean kanverkopen = rs.getBoolean("kanverkopen");
		 if (kanverkopen == true){
			 role = "verkoper";
		 }
		 else{
			 role = "gebruiker";
		 }
	
	 
	 }
	 } catch (SQLException sqle) {
	 sqle.printStackTrace();
	 } catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 return role;
	 }
	}