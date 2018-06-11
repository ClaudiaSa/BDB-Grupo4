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
	public String getResults(ResultSet rs) throws SQLException {
		// Reads the result from the query 
		String res = "";
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

		
		res=res+("Id: " + id+"\n");
		res=res+("Name:" + name+"\n");
		res=res+("Source: " + source+"\n");
		res=res+("Parent id: " + parent_id+"\n");
		res=res+("Chebi Accession: " + chebi_accession+"\n");
		res=res+("Status: " + status+"\n");
		res=res+("Definition: " + definition+"\n");
		res=res+("Star: " + star+"\n");
		res=res+("Modified on: " + modified_on+"\n");
		res=res+("Created by: " + created_by+"\n");
		
		return res;
	}

}
