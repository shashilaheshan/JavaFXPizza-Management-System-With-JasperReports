
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Heshan
 */
public class LoginMenuController implements Initializable {
 @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXTextField txtPass;
    @FXML
    private JFXComboBox<String> cmbDevision=new JFXComboBox<>();
    
    @FXML
    private Hyperlink btnForget;

    

    @FXML
    void forgetPage(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) btnForget.getScene().getWindow();
              stage1.hide();
        AnchorPane root=FXMLLoader.load(getClass().getResource("ForgetCredentials.fxml"));
               Stage stage=new Stage();
               Scene sc=new Scene(root);
               //stage.initStyle(StageStyle.UTILITY);
               stage.setScene(sc);
               stage.show();

    }


           DbConnection con=new DbConnection();

   //Login With Encryptions Process
             
        public void Login() throws SQLException, NoSuchAlgorithmException, IOException{
               //String email = txtUser.getText();
              //String password = txtPass.getText();
            if(!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty() && !cmbDevision.selectionModelProperty().get().isEmpty()){
              
              String devision=cmbDevision.getValue();
              
                 String username = txtUser.getText();
        String pass = txtPass.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");//encrypt username and password into md5 hash
            byte[] messageDigest1 = md.digest(username.getBytes());//get byte text of username
            byte[] messageDigest2 = md.digest(pass.getBytes());//get byte text of password
            BigInteger number1 = new BigInteger(1, messageDigest1);
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String hashtext1 = number1.toString(16);
             String hashtext2 = number2.toString(16);
            
            while (hashtext1.length() < 32 && hashtext2.length()<32) {
                hashtext1 = "0" + hashtext1;
                hashtext2 ="0"+hashtext2;
            }//encrypted text
            
        // MySQL database connection
        Connection conn = con.connect();//conenction url and database,table details    
        PreparedStatement pst = conn.prepareStatement("Select u_name,p_word,devision from users where u_name=? and p_word=? and devision=?");//sql s(shashila)tatement
        pst.setString(1, hashtext1);//assigning get parameters from database to encrypted paramets
        pst.setString(2, hashtext2);
        pst.setString(3,devision);
        ResultSet rs = pst.executeQuery();                        
        if(rs.next())  {         
         
             if(devision.equals("admin")){
              
              AnchorPane root=FXMLLoader.load(getClass().getResource("HomePageAdmin.fxml"));
              
               Stage stage=new Stage();
               Scene sc=new Scene(root);
               stage.isIconified();
              stage.initStyle(StageStyle.UTILITY);
              //stage.initStyle(StageStyle.UNIFIED);
               stage.setScene(sc);
               stage.show();
                  }
                  else{ 
                  AnchorPane root=FXMLLoader.load(getClass().getResource("HomePageStaff.fxml"));
               Stage stage=new Stage();
               Scene sc=new Scene(root);
               //stage.initStyle(StageStyle.UTILITY);
               stage.initStyle(StageStyle.DECORATED);
               stage.setScene(sc);
               stage.show();
                 
             
                  } 
            
            System.out.println("WELCOME" +"--"+username);
        }else
                System.err.println(); 
            
            } else{
            OpenErrorProviders obj=new OpenErrorProviders();
            obj.OpenEmptyField();
                
            
            }
                   
              
              }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbDevision.getItems().addAll(
            "admin",
            "staff"
            
        );

    } 
    @FXML
    public void Exit(){
        System.exit(0);
    
    }
    
}
