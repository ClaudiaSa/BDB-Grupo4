package SQLRelated;

import java.sql.Connection;
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
	public String getResults(ResultSet rs) throws SQLException {
		// Reads the result from the query and prints it
		String res="";
		int id = rs.getInt("id");
		int compound_id = rs.getInt("compound_id");
		String structure = rs.getString("structure");
		String type = rs.getString("type");
		String dimension = rs.getString("dimension");

		res= res+("Id: " + id+"\n");
		res= res+("Compound id:" + compound_id+"\n");
		res= res+("Structure: " + structure+"\n");
		res= res+("Type: " + type+"\n");
		res= res+("Dimension: " + dimension+"\n");
		

		return res;
	}

}
