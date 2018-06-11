
package BDBG4.JavaImplementationDBSG;

import static org.junit.Assert.assertEquals;
import java.sql.*;

import org.junit.Before;
import org.junit.Test;

public class QueryResultTest {

	public Connection c = null;

	@Before
	public void init() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/chebi", "root", "admin");
		c.setAutoCommit(false);
	}

	@Test
	public void testQueryResultCompound() throws SQLException {
		int queryparent = 18357;
		QueryResultCompound query = new QueryResultCompound(c, "SELECT * FROM COMPOUNDS WHERE ID = 1;");
		query.ControlTime();
		assertEquals(query.parent_id, queryparent);
	}
	
	@Test
	public void testQueryResultStructures() throws SQLException {
		int querycompoundid = 76169;
		QueryResultStructures  query = new QueryResultStructures (c, "SELECT * FROM STRUCTURES WHERE ID = 2830752;");
		query.ControlTime();
		assertEquals(query.compound_id, querycompoundid);
	}
	
}
