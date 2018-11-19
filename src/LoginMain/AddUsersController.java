/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author shashilaheshan
 */
public class AddUsersController implements Initializable {
   Connector.DbConnection obj=new DbConnection();
  @FXML
    private JFXComboBox<Integer> cmbEmployeeNIC=new JFXComboBox<>();

    @FXML
    private JFXTextField txtEmpName;

    @FXML
    private JFXTextField txtUsernameAdd;

    @FXML
    private JFXTextField txtPasswordAdd;

    @FXML
    private JFXComboBox<String> cmbSQ=new JFXComboBox<>();;

    @FXML
    private JFXTextField txtAnswer;

    @FXML
    private JFXComboBox<String> cmbDevision=new JFXComboBox<>();;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXTextField txtUserID;

    @FXML
    private JFXTextField txtSQuiz;

    @FXML
    private JFXTextField txtAnsw;

    @FXML
    private JFXTextField txtDiv;

    @FXML
    private Button btnLoad;

    @FXML
    private JFXComboBox<Integer> cmbUserID=new JFXComboBox<>();

    @FXML
    private Button btnLoad2;

    @FXML
    private Button btnLoad21;

    @FXML
    private TableView<UserDetails> tableItem;

    @FXML
    private TableColumn<UserDetails, ?> userId;

    @FXML
    private TableColumn<?, ?> Username;

    @FXML
    private TableColumn<?, ?> Password;

    @FXML
    private TableColumn<?, ?> SEQuiz;

    @FXML
    private TableColumn<?, ?> Answer;

    @FXML
    private TableColumn<?, ?> itemQty;
    private ObservableList<UserDetails>data;
    DbConnection con=new DbConnection();
  
   private ObservableList<Integer> option=FXCollections.observableArrayList();

   

    @FXML
    void addItem(ActionEvent event) {
// HASH Encryption part
        try{
            String username = txtUsernameAdd.getText();
        String password =txtPasswordAdd.getText();
        MessageDigest md = MessageDigest.getInstance("MD5");//encrypt username and password into md5 hash
            byte[] messageDigest1 = md.digest(username.getBytes());//get byte text of username
            byte[] messageDigest2 = md.digest(password.getBytes());//get byte text of password
            BigInteger number1 = new BigInteger(1, messageDigest1);
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String hashtext1 = number1.toString(16);
             String hashtext2 = number2.toString(16);
            
            while (hashtext1.length() < 32 && hashtext2.length()<32) {
                hashtext1 = "0" + hashtext1;
                hashtext2 ="0"+hashtext2;
            }
            
           Connection conn=obj.connect();
           String sql="INSERT INTO users(u_name, p_word, seq_q, answer, devision,u_name_decoded,p_word_decoded) VALUES ('"+hashtext1+"','"+hashtext2+"','"+cmbSQ.getSelectionModel().getSelectedItem().toString()+"','"+txtAnswer.getText()+"','"+cmbDevision.getSelectionModel().getSelectedItem().toString()+"','"+username+"','"+password+"')";
           Statement st=conn.createStatement();
            st.executeUpdate(sql);
           
            
            
            
        }catch(Exception ex){
            
            System.out.println(ex);
            
        }
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
           AddCombo();
       } catch (IOException ex) {
           Logger.getLogger(AddUsersController.class.getName()).log(Level.SEVERE, null, ex);
       }
        cmbDevision.getItems().addAll(
        "admin",
         "staff"
        
        );
        cmbSQ.getItems().addAll(
        "what is your first name?",
                "what is your pet name?",
                "what is your first email name?",
                "what is your middle name?",
                "what is your father's birthday?",
                "What is your first car name?"
        );
        
                
                
    }    
    @FXML
    public void exitButton(){
    
    System.exit(0);
    }
    
     void AddCombo() throws IOException{
    try {
            Connection conn=obj.connect();
            String sql="select Nic from employees; ";
         PreparedStatement pst=conn.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
     while(rs.next()){
     option.add(rs.getInt("Nic"));
     
     
     }
     rs.close();
     pst.close();
     cmbEmployeeNIC.setItems(option);
     
     
        } catch (SQLException ex) {
            Logger.getLogger(AddUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
}
