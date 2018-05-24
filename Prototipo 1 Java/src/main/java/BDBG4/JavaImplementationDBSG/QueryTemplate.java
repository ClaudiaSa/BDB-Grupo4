package BDBG4.JavaImplementationDBSG;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class QueryTemplate {

	private Connection c;
	private String query;

	public QueryTemplate(Connection con, String query) {
		c = con;
		this.query = query;
	}

	public void ControlTime() throws SQLException{
		double res=0;
		double cTime=Math.abs(ComputeQuery(c));
		res=Math.abs(cTime);
		System.out.println("/////Takes "+res+ " time to complete");
		
	}
	
	public long ComputeQuery(Connection con) throws SQLException{
		long res=0;
		Statement stmt = con.createStatement();
		long init = System.currentTimeMillis();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			ComputeResults(rs);
			rs.toString();
		}
		long fin = System.currentTimeMillis();
		rs.close();
		res= init-fin;
		stmt.close();
		return res;
	}
	
	public abstract void ComputeResults(ResultSet rs) throws SQLException;

}
