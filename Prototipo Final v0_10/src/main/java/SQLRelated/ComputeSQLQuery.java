package SQLRelated;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class ComputeSQLQuery {
	/*
	 * Template for a query result. Any query result must implement this abstract
	 * class, defining its own printResults depending on its result.
	 */
	public ComputeSQLQuery() {
	}

	public long ComputeQuery(Connection con, String query) throws SQLException {
		/*
		 * This method takes an SQL query and runs it on a connection, taking time and
		 * printing the results calling PrintResults method.
		 */
		long res = -1;
		Statement stmt = con.createStatement();
		long init = System.nanoTime();
		ResultSet queryResults = stmt.executeQuery(query);
		long fin = System.nanoTime();
		res = fin - init;
		String allResults="";
		while (queryResults.next()) {
			allResults=allResults+getResults(queryResults);
		}
		queryResults.close();
		stmt.close();
		return res;
	}

	public long ComputeQueryWithoutPrinting(Connection con, String query) throws SQLException{
		/*
		 * This method takes an SQL query and runs it on a connection, taking time and
		 * printing the results calling PrintResults method.
		 */
		long res = -1;
		Statement stmt = con.createStatement();
		long init = System.nanoTime();
		stmt.executeQuery(query);
		long fin = System.nanoTime();
		res = fin - init;
		String allResults="";
		stmt.close();
		return res;
	}
	
	public abstract String getResults(ResultSet res) throws SQLException;
}
