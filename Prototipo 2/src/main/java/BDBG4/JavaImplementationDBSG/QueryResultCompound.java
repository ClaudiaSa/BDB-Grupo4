package BDBG4.JavaImplementationDBSG;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryResultCompound extends QueryTemplate {
	int id, parent_id, star;
	String name, source, chebi_accession, status, definition, modified_on, created_by;

	public QueryResultCompound(Connection con, String query) {
		super(con, query);
	}

	public void ComputeResults(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.name = rs.getString("name");
		this.source = rs.getString("source");
		this.parent_id = rs.getInt("parent_id");
		this.chebi_accession = rs.getString("chebi_accession");
		this.status = rs.getString("status");
		this.definition = rs.getString("definition");
		this.star = rs.getInt("star");
		this.modified_on = rs.getString("modified_on");
		this.created_by = rs.getString("created_by");

		System.out.println("Id: " + id + "\n" + "Name:" + name + "\n" + "Source: " + source + "\n" + "Parent id: "
				+ parent_id + "\n" + "Chebi Accession: " + chebi_accession + "\n" + "Status: " + status + "\n"
				+ "Definition: " + definition + "\n" + "Star: " + star + "\n" + "Modified on: " + modified_on + "\n"
				+ "Created by: " + created_by + "\n");
	}
}
