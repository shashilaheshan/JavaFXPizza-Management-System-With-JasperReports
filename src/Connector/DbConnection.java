
package Connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbConnection {
  
  
   private final String driver="com.mysql.jdbc.Driver";
   Statement st=null;
  
   public Connection connect() throws SQLException{
       try {
           Class.forName(driver);
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza?user=root&password=");
           return con;
       } catch (ClassNotFoundException ex) {
           System.err.println("not connected");
           Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
  
  
   public Statement getStatement(){
   return st;
   }
   
   
}
