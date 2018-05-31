package SQLRelated;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultStructures extends ComputeSQLQuery {

	/*
	 * This class is defined for a query that returns a structure as a result
	 */

	public QueryResultStructures() {
		super();
	}

	@Override
	public void PrintResults(ResultSet rs) throws SQLException {
		// Reads the result from the query and prints it
		int id = rs.getInt("id");
		int compound_id = rs.getInt("compound_id");
		String structure = rs.getString("structure");
		String type = rs.getString("type");
		String dimension = rs.getString("dimension");

		System.out.println("Id: " + id);
		System.out.println("Compound id:" + compound_id);
		System.out.println("Structure: " + structure);
		System.out.println("Type: " + type);
		System.out.println("Dimension: " + dimension);
		System.out.println();

	}

}
