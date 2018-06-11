package BDBG4.JavaImplementationDBSG;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class ConnectionTest {

	@Test
	public void testConnectionWithMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.getConnection("jdbc:mysql://localhost:3306/chebi", "root", "admin");
	}

	@Test
	public void testConnectionWithPostGre() throws Exception {
		Class.forName("org.postgresql.Driver");
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/chebi", "postgres", "mainadmin");
	}
	
	@Test
	public void testConnectionWithPostGreNoIndex() throws Exception {
		Class.forName("org.postgresql.Driver");
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/noindexchebi", "postgres", "mainadmin");
	}
	
	

}
