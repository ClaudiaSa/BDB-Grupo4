package XMLRelated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import com.saxonica.xqj.SaxonXQDataSource;


public class XQueryFromLocalFiles{

	/*
	 * This class is prepared to read an XML file and query another file with the
	 * XQuery on it
	 */
	public XQueryFromLocalFiles() {
	}

	public long ComputeQuery(String XQueryFile) throws FileNotFoundException, XQException {
		// Queries over the xml on the root folder the given file
		long res = -1;
		InputStream inputStream = new FileInputStream(new File(XQueryFile));
		XQDataSource ds = new SaxonXQDataSource();
		XQConnection conn = ds.getConnection();
		XQPreparedExpression exp = conn.prepareExpression(inputStream);
		long init = System.nanoTime();
		XQResultSequence result = exp.executeQuery();
		long fin = System.nanoTime();
		res = fin - init;

		String queryResult="";
		while (result.next()) {
			queryResult=queryResult+(result.getItemAsString(null))+"\n";
		}
		return res;
	}
	
	public long ComputeQueryWithoutPrinting(String XQueryFile) throws FileNotFoundException, XQException {
		// Queries over the xml on the root folder the given file
				long res = -1;
				InputStream inputStream = new FileInputStream(new File(XQueryFile));
				XQDataSource ds = new SaxonXQDataSource();
				XQConnection conn = ds.getConnection();
				XQPreparedExpression exp = conn.prepareExpression(inputStream);
				long init = System.nanoTime();
				XQResultSequence result = exp.executeQuery();
				long fin = System.nanoTime();
				res = fin - init;

				return res;
	}

}
