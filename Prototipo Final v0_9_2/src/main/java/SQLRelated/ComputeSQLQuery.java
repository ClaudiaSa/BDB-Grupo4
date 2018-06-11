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

	public long ComputeQuery(Connection connection, String query) throws SQLException {
		/*
		 * This method takes an SQL query and runs it on a connection, taking time and
		 * printing the results calling PrintResults method.
		 */
		long res = -1;
		Statement stmt = connection.createStatement();
		long init = System.nanoTime();
		ResultSet queryResults = stmt.executeQuery(query);
		long fin = System.nanoTime();
		res = fin - init;
		while (queryResults.next()) {
			PrintResults(queryResults);
		}
		queryResults.close();
		stmt.close();
		return res;
	}

	public abstract void PrintResults(ResultSet res) throws SQLException;
}
