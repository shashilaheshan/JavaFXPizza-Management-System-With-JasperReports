/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JEditorPane;

/**
 * FXML Controller class
 *
 * @author Heshan
 */
public class HomePageStaffController implements Initializable {
@FXML
    private ImageView btnItemMain;

    @FXML
    private ImageView btnInvoice;

    @FXML
    private ImageView btnWebMain;
     @FXML
    private Label lblNotification;

    /**
     * Initializes the controller class.
     * 
     */
    public void AddNotification(){
    try{
    Connection con;
    DbConnection conn=new DbConnection();
    con=conn.connect();
        
    String sql="SELECT COUNT(Invoice_no) from invoice_summary";
    Statement st=con.createStatement();
    ResultSet rst=st.executeQuery(sql);
    while(rst.next()){
       int count=rst.getInt(1);
       String noti=Integer.toString(count);
       lblNotification.setText(noti);
       
    
    }
        
        
    
    }catch(Exception ex){
    System.out.println(ex);
    
    }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        AddNotification();
         btnItemMain.setOnMouseClicked((MouseEvent e)->{
            try {
                 AnchorPane root=FXMLLoader.load(getClass().getResource("ItemMain.fxml"));
                 Stage stage=new Stage();
            Scene sc=new Scene(root);
                stage.setScene(sc);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });    
    btnInvoice.setOnMouseClicked((MouseEvent e)->{
            try {
                 AnchorPane root=FXMLLoader.load(getClass().getResource("InvoiceMain.fxml"));
                 Stage stage=new Stage();
            Scene sc=new Scene(root);
                stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });    
    btnWebMain.setOnMouseClicked((MouseEvent e)->{
            try {
                 //AnchorPane root=FXMLLoader.load(getClass().getResource("Grn.fxml"));
               String url_open ="https://itprojectnibm.000webhostapp.com";
java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });    
}
}