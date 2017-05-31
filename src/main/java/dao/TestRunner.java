package dao;

import java.sql.SQLException;

public class TestRunner {

	public static void main(String[] args) throws SQLException {
		BaseDAO bdao = new BaseDAO();
		
		bdao.connect();

	}

}
