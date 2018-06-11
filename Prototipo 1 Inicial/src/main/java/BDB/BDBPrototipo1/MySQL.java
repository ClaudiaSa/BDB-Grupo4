package BDB.BDBPrototipo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
   public static void main(String args[]) {
      Connection c = null;
      try {
    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
    	 c = DriverManager.getConnection("jdbc:mysql://localhost:3306/CHEBI", "root", "admin");
    	 
    	 
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         QuerySonsOfFather(c);
         QueryCompoundName(c);
         QueryChemicalFormula(c);
         QueryStructureFromCompound(c);
         QueryParentSonNotStructureS(c);
         
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }

      System.out.println("Operation done successfully");
     
                            
   }
   
   public static void QuerySonsOfFather(Connection c) throws SQLException {
	   Statement stmt = c.createStatement();
       long init = System.currentTimeMillis();
	   ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPOUNDS WHERE PARENT_ID = 27732;" );
       
	   while ( rs.next() ) {
           int id = rs.getInt("id");
           String  name = rs.getString("name");
           String source= rs.getString("source");
           int parent_id= rs.getInt("parent_id");
           String chebi_accesion=rs.getString("chebi_accession");
           String status= rs.getString("status");
           String definition= rs.getString("definition");
           int star= rs.getInt("star");
           String modified_on=rs.getString("modified_on");
           String created_by= rs.getString("created_by");
           
           System.out.println("Id: "+id);
           System.out.println("Name:"+name);
           System.out.println("Source: "+source);
           System.out.println("Parent id: "+parent_id);
           System.out.println("Chebi Accesion: "+chebi_accesion);
           System.out.println("Status: "+status);
           System.out.println("Definition: "+definition);
           System.out.println("Star: "+star);
           System.out.println("Modified on: "+modified_on);
           System.out.println("Created by: "+created_by);
           System.out.println();
        }
	   long fin = System.currentTimeMillis();
	   rs.close();

       System.out.println("Takes "+ (fin-init)+"ms to complete select");
       
       stmt.close();
   }
    
   
   public static void QueryCompoundName(Connection c) throws SQLException{
	   Statement stmt = c.createStatement();
       long init = System.currentTimeMillis();
	   ResultSet rs = stmt.executeQuery( "SELECT C.* FROM names N INNER JOIN compounds C ON N.compound_id = C.id WHERE N.name = 'Cyanure' ;" );
       
	   while ( rs.next() ) {
           int id = rs.getInt("id");
           String  name = rs.getString("name");
           String source= rs.getString("source");
           int parent_id= rs.getInt("parent_id");
           String chebi_accesion=rs.getString("chebi_accession");
           String status= rs.getString("status");
           String definition= rs.getString("definition");
           int star= rs.getInt("star");
           String modified_on=rs.getString("modified_on");
           String created_by= rs.getString("created_by");
           
           System.out.println("Id: "+id);
           System.out.println("Name:"+name);
           System.out.println("Source: "+source);
           System.out.println("Parent id: "+parent_id);
           System.out.println("Chebi Accesion: "+chebi_accesion);
           System.out.println("Status: "+status);
           System.out.println("Definition: "+definition);
           System.out.println("Star: "+star);
           System.out.println("Modified on: "+modified_on);
           System.out.println("Created by: "+created_by);
           System.out.println();
        }
	   long fin = System.currentTimeMillis();
	   rs.close();

       System.out.println("Takes "+ (fin-init)+"ms to complete select");
       
       stmt.close();

   }
   public static void QueryChemicalFormula(Connection c) throws SQLException{
	   Statement stmt = c.createStatement();
       long init = System.currentTimeMillis();
	   ResultSet rs = stmt.executeQuery( "SELECT C.* FROM chemical_data D INNER JOIN compounds C ON D.compound_id = C.id WHERE D.chemical_data =  ' C6H7N' ;" );
       
	   while ( rs.next() ) {
           int id = rs.getInt("id");
           String  name = rs.getString("name");
           String source= rs.getString("source");
           int parent_id= rs.getInt("parent_id");
           String chebi_accesion=rs.getString("chebi_accession");
           String status= rs.getString("status");
           String definition= rs.getString("definition");
           int star= rs.getInt("star");
           String modified_on=rs.getString("modified_on");
           String created_by= rs.getString("created_by");
           
           System.out.println("Id: "+id);
           System.out.println("Name:"+name);
           System.out.println("Source: "+source);
           System.out.println("Parent id: "+parent_id);
           System.out.println("Chebi Accesion: "+chebi_accesion);
           System.out.println("Status: "+status);
           System.out.println("Definition: "+definition);
           System.out.println("Star: "+star);
           System.out.println("Modified on: "+modified_on);
           System.out.println("Created by: "+created_by);
           System.out.println();
        }
	   long fin = System.currentTimeMillis();
	   rs.close();

       System.out.println("Takes "+ (fin-init)+"ms to complete select");
       
       stmt.close();

   }
   
   public static void QueryStructureFromCompound(Connection c) throws SQLException{
	   Statement stmt = c.createStatement();
       long init = System.currentTimeMillis();
	   ResultSet rs = stmt.executeQuery( "SELECT S.* FROM compounds C INNER JOIN structures S ON S.compound_id = C.id WHERE C.id = 34565 ;" );
       
	   while ( rs.next() ) {
           int id = rs.getInt("id");
           int compound_id = rs.getInt("compound_id");
           String structure = rs.getString("structure");
           String type = rs.getString("type");
           String dimension = rs.getString("dimension");
           
           System.out.println("Id: "+id);
           System.out.println("Compound id:"+compound_id);
           System.out.println("Structure: "+structure);
           System.out.println("Type: "+type);
           System.out.println("Dimension: "+dimension);
           System.out.println();
        }
	   long fin = System.currentTimeMillis();
	   rs.close();

       System.out.println("Takes "+ (fin-init)+"ms to complete select");
       
       stmt.close();

   }
   
   public static void QueryParentSonNotStructureS(Connection c) throws SQLException{
	   Statement stmt = c.createStatement();
       long init = System.currentTimeMillis();
	   ResultSet rs = stmt.executeQuery( "select * from Compounds WHERE Compounds.id = ANY (\r\n" + 
	   		"	select Distinct parent_id from Compounds where id =ANY(\r\n" + 
	   		"											select Distinct compound_id from Structures where dimension <> '1D'\r\n" + 
	   		"											)\r\n" + 
	   		"	)" );
       
	   while ( rs.next() ) {
           int id = rs.getInt("id");
           String  name = rs.getString("name");
           String source= rs.getString("source");
           int parent_id= rs.getInt("parent_id");
           String chebi_accesion=rs.getString("chebi_accession");
           String status= rs.getString("status");
           String definition= rs.getString("definition");
           int star= rs.getInt("star");
           String modified_on=rs.getString("modified_on");
           String created_by= rs.getString("created_by");
           
           System.out.println("Id: "+id);
           System.out.println("Name:"+name);
           System.out.println("Source: "+source);
           System.out.println("Parent id: "+parent_id);
           System.out.println("Chebi Accesion: "+chebi_accesion);
           System.out.println("Status: "+status);
           System.out.println("Definition: "+definition);
           System.out.println("Star: "+star);
           System.out.println("Modified on: "+modified_on);
           System.out.println("Created by: "+created_by);
           System.out.println();
        }
	   long fin = System.currentTimeMillis();
	   rs.close();

       System.out.println("Takes "+ (fin-init)+"ms to complete select");
       
       stmt.close();

   }   
}
