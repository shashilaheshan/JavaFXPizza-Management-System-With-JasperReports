/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author root
 */
public class ForgetCredentialsController implements Initializable {


    DbConnection conn=new DbConnection();
        ResultSet rs;
           Connection con;
       @FXML
    private JFXTextField txtUser;
     @FXML
    private JFXTextField txtQuestion;
     
      
   
    @FXML
    private JFXTextField txtNewPass;

    @FXML
    private JFXTextField txtAnswer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    public void Updatepass(){
    String answer=txtAnswer.getText();
    String newPAss=txtNewPass.getText();
    try{
    con=conn.connect();
    String sql="select answer from user where answer='"+answer+"';";
    
    Statement st=con.createStatement();
    rs=st.executeQuery(sql);
    while(rs.next()){
        if(rs.getString(1).equals(answer)){
    String sql1="UPDATE user set p_word='"+newPAss+"' where answer='"+rs.getString(1)+"';";
    Statement st1=con.createStatement();
    st1.executeUpdate(sql1);
    System.out.println("password changed");
        }else{
        System.out.println("Answer is incorrect");
        }
    
    }
        
    }catch(Exception ex){
    System.out.println("Error");
        
    }
        
    
    }
    public void Clear(JFXTextField list ){
        for(int i=0;i<list.lengthProperty().get();i++){
            
            
        }
     txtUser.setText("");
     txtQuestion.setText("");
     txtAnswer.setText("");
     txtNewPass.setText("");
    
    }
    public void CheckAvailability() throws SQLException{
        String name=txtUser.getText();
        
    try{
        
            
        
          con=conn.connect();
        
    
       String sql="SELECT u_name from user where u_name='"+txtUser.getText()+"';"; 
      
       Statement st=con.createStatement();
       
       rs=st.executeQuery(sql);
      
       /*if(rs==null){
       System.out.println("No user");
       }*/
       
       while(rs.next()){
       
           String sql1="SELECT seq_q from user where u_name='"+rs.getString(1)+"';";
           Statement st1=con.createStatement();
           ResultSet rst1=st1.executeQuery(sql1);
           while(rst1.next())
           {
           txtQuestion.setText(rst1.getString(1));
           
           
           }
           
           
   
       }
        
       
    }
    catch(SQLException ex){
    System.out.println("Error with connection");
    
    }
    }
}
