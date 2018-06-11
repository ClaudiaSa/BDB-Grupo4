package SQLRelated;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultCompound extends ComputeSQLQuery {
	/*
	 * This class is defined for a query that returns a Compound as a result.
	 */

	public QueryResultCompound() {
		super();
	}

	@Override
	public void PrintResults(ResultSet rs) throws SQLException {
		// Reads the result from the query and prints it
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String source = rs.getString("source");
		int parent_id = rs.getInt("parent_id");
		String chebi_accession = rs.getString("chebi_accession");
		String status = rs.getString("status");
		String definition = rs.getString("definition");
		int star = rs.getInt("star");
		String modified_on = rs.getString("modified_on");
		String created_by = rs.getString("created_by");

		System.out.println("Id: " + id);
		System.out.println("Name:" + name);
		System.out.println("Source: " + source);
		System.out.println("Parent id: " + parent_id);
		System.out.println("Chebi Accession: " + chebi_accession);
		System.out.println("Status: " + status);
		System.out.println("Definition: " + definition);
		System.out.println("Star: " + star);
		System.out.println("Modified on: " + modified_on);
		System.out.println("Created by: " + created_by);
		System.out.println();

	}

}
