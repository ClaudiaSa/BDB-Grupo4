package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xquery.XQException;

import SQLRelated.QueryResultCompound;
import SQLRelated.QueryResultStructures;
import XMLRelated.XQueryFromLocalFiles;
import SQLRelated.ComputeSQLQuery;
/*
 *This class is a main where queries are created and where the time it takes to different database managers to query it
 */

public class TimeComparing {
	// Defining user static values
	private static String PostgreSQLUser = "postgres";
	private static String PostgreSQLPass = "mainadmin";
	private static String MySQLUser = "root";
	private static String MySQLPass = "admin";
	// defining database static values
	private static String PGSQLChebiIndexCon = "jdbc:postgresql://localhost:5432/chebi";
	private static String PGSQLChebiNoIndexCon = "jdbc:postgresql://localhost:5432/noindexchebi";
	private static String MySQLCon = "jdbc:mysql://localhost:3306/CHEBI";

	public static void main(String args[]) {
		// Create connections
		Connection mySQLConnection = null;
		Connection indexPGSQLConnection = null;
		Connection noIndexPGSQLConnection = null;
		// try to open connections and with them open make the queries
		try {
			// Importing driver
			Class.forName("org.postgresql.Driver");
			// Initializing connections
			indexPGSQLConnection = DriverManager.getConnection(PGSQLChebiIndexCon, PostgreSQLUser, PostgreSQLPass);
			noIndexPGSQLConnection = DriverManager.getConnection(PGSQLChebiNoIndexCon, PostgreSQLUser, PostgreSQLPass);

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			mySQLConnection = DriverManager.getConnection(MySQLCon, MySQLUser, MySQLPass);

			indexPGSQLConnection.setAutoCommit(false);
			noIndexPGSQLConnection.setAutoCommit(false);

			System.out.println("Opened databases successfully");
			// Creating a list of SQL database gestor connections to iterate over them the
			// same query later on
			List<Connection> connections = new ArrayList<>();
			connections.add(indexPGSQLConnection);
			connections.add(noIndexPGSQLConnection);
			connections.add(mySQLConnection);

			// Making the queries result
			QueryResultCompound compoundsResult = new QueryResultCompound();
			QueryResultStructures structureResult = new QueryResultStructures();

			// Defining the actual queries
			String querySonsFromParent = "SELECT * FROM COMPOUNDS WHERE PARENT_ID = 27732;";
			String parentsSonsNoCertainDimStructure = "select * from Compounds WHERE Compounds.id = ANY (\r\n"
					+ "	select Distinct parent_id from Compounds where id =ANY(\r\n"
					+ "											select Distinct compound_id from Structures where dimension <> '1D'\r\n"
					+ "											)\r\n" + "	)";
			String nameCompound = "SELECT C.* FROM names N INNER JOIN compounds C ON N.compound_id = C.id WHERE N.name = 'Cyanure' ;";
			String compounFromChemicalID = "SELECT C.* FROM chemical_data D INNER JOIN compounds C ON D.compound_id = C.id WHERE D.chemical_data =  'C6H7N' ;";
			String structureFromCompound = "SELECT S.* FROM compounds C INNER JOIN structures S ON S.compound_id = C.id WHERE C.id = 34565 ;";

			// Creating queries list depending on its result
			List<String> compoundResultQuery = new ArrayList<>();
			compoundResultQuery.add(querySonsFromParent);
			compoundResultQuery.add(parentsSonsNoCertainDimStructure);
			compoundResultQuery.add(nameCompound);
			compoundResultQuery.add(compounFromChemicalID);

			List<String> structureResultQuery = new ArrayList<>();
			structureResultQuery.add(structureFromCompound);

			// Creating a map that has a key element of the result a query gives and a list
			// of queries as a list of strings
			Map<ComputeSQLQuery, List<String>> sqlQueriesInfo = new HashMap();

			sqlQueriesInfo.put(compoundsResult, compoundResultQuery);
			sqlQueriesInfo.put(structureResult, structureResultQuery);

			// Defining the name of the file where the XQuery is contained
			String XQueryOne = "resources/consultaone.xquery";
			String XQueryTwo = "resources/consultatwo.xquery";
			String XQueryThree = "resources/consultathree.xquery";
			String XQueryFour = "resources/consultafour.xquery";

			// Adding every XQuery file to be executed to a list to run them all
			List<String> XQueries = new ArrayList<>();

			XQueries.add(XQueryOne);
			XQueries.add(XQueryTwo);
			XQueries.add(XQueryThree);
			XQueries.add(XQueryFour);

			// Taking time of all queries, first SQL and then XML

			CommputeQueriesOnAll(sqlQueriesInfo, connections);
			ComputeXMLQueries(XQueries);

			// Ending Connections
			indexPGSQLConnection.close();
			noIndexPGSQLConnection.close();
			mySQLConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void CommputeQueriesOnAll(Map<ComputeSQLQuery, List<String>> sqlQueriesInfo,
			List<Connection> connections) throws SQLException {
		// This method computes all queries of the list of strings from the map
		// sqlQueriesInfo o all of the query results
		// on its key from the map
		for (ComputeSQLQuery compQuery : sqlQueriesInfo.keySet()) {
			for (Connection con : connections) {
				for (String query : sqlQueriesInfo.get(compQuery)) {
					System.out.println("\n=====================\n" + "Results from query:\n\n" + query
							+ "\n\n On connection: " + con.getCatalog());
					long timeToQuery = compQuery.ComputeQuery(con, query);
					System.out.println("Takes " + timeToQuery + "ns or " + timeToQuery / 1000000 + "ms to finish"
							+ "\n=====================\n");
				}
			}
		}
	}

	public static void ComputeXMLQueries(List<String> queries) throws FileNotFoundException, XQException {
		// This method computes all XQueries on the given list of strings that are file
		// names where the XQuery is located
		XQueryFromLocalFiles xmlQuery = new XQueryFromLocalFiles();
		for (String query : queries) {
			List<String> queryLines = xmlQueryToString(query);

			System.out.println("\n=====================\n" + "Results from XQuery:\n\n");
			for (String lin : queryLines) {
				System.out.println(lin);
			}
			System.out.println("\n");
			long timeToQuery = xmlQuery.ComputeQuery(query);
			System.out.println("Takes " + timeToQuery + "ns or " + timeToQuery / 1000000 + "ms or "
					+ timeToQuery / 1000000000 + "s to finish" + "\n=====================\n");
		}

	}

	private static List<String> xmlQueryToString(String fileName) throws FileNotFoundException {
		// This method helps printing the content of the XQuery file
		List<String> res = new ArrayList<>();
		try {
			File xQuery = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(xQuery));
			String line;
			while ((line = reader.readLine()) != null) {
				res.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
}
