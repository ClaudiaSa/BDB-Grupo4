package BDBG4.JavaImplementationDBSG;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultStructures extends QueryTemplate {

	int id, compound_id;
	String structure, type, dimension;

	public QueryResultStructures(Connection con, String query) {
		super(con, query);
	}

	public void ComputeResults(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.compound_id = rs.getInt("compound_id");
		this.structure = rs.getString("structure");
		this.type = rs.getString("type");
		this.dimension = rs.getString("dimension");

		System.out.println("Id: " + id + "\n" + "Compound id:" + compound_id + "\n" + "Structure: " + structure + "\n"
				+ "Type: " + type + "\n" + "Dimension: " + dimension + "\n");
	}
}
