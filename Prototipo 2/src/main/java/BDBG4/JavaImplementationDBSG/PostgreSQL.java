package BDBG4.JavaImplementationDBSG;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQL {
	public static void main(String args[]) {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chebi", "postgres", "mainadmin");

			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			new QueryResultCompound(c, "select * from Compounds WHERE Compounds.id = ANY (\r\n"
					+ "	select Distinct parent_id from Compounds where id =ANY(\r\n"
					+ "											select Distinct compound_id from Structures where dimension <> '1D'\r\n"
					+ "											)\r\n" + "	)").ControlTime();

			new QueryResultCompound(c, "SELECT * FROM COMPOUNDS WHERE PARENT_ID = 27732;").ControlTime();

			new QueryResultCompound(c,
					"SELECT C.* FROM names N INNER JOIN compounds C ON N.compound_id = C.id WHERE N.name = 'Cyanure' ;")
							.ControlTime();

			new QueryResultCompound(c,
					"SELECT C.* FROM chemical_data D INNER JOIN compounds C ON D.compound_id = C.id WHERE D.chemical_data =  'C6H7N' ;")
							.toString();

			new QueryResultStructures(c,
					"SELECT S.* FROM compounds C INNER JOIN structures S ON S.compound_id = C.id WHERE C.id = 34565 ;")
							.ControlTime();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}
