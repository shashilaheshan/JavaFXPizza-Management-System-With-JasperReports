
package LoginMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Heshan
 */
public class HomePageAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */@FXML
    private ImageView btnEmployeeMain;

    @FXML
    private ImageView btnItemMain;

    @FXML
    private ImageView btnCustomerMain;

    @FXML
    private ImageView btnAccountMain;

    @FXML
    private ImageView btnOrderMain;

    @FXML
    private ImageView btnReportMain;

    @FXML
    private ImageView btnWebMain;

    @FXML
    private ImageView btnStaticsMain;
    @FXML
    private ImageView btnLogout;
    
Stage stage=new Stage();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnLogout.setOnMouseClicked((MouseEvent e)->{
        System.exit(1);
    
    });
//Methods for Opening Other windows in system
        btnEmployeeMain.setOnMouseClicked((MouseEvent e)->{
            try {AnchorPane root
                =FXMLLoader.load(getClass().getResource("EmployeeMain.fxml"));
            Scene sc=new Scene(root);
                stage.setScene(sc);
               stage.initStyle(StageStyle.UNIFIED);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    });
        btnOrderMain.setOnMouseClicked((MouseEvent e)->{
            try {
                AnchorPane root=FXMLLoader.load(getClass().getResource("OrderController.fxml"));
                 Scene sc=new Scene(root);
                // stage.initStyle(StageStyle.UTILITY);
                 stage.setScene(sc);
                stage.show();
    
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    });
        btnWebMain.setOnMouseClicked((MouseEvent e)->{
            try {
                AnchorPane root=FXMLLoader.load(getClass().getResource("Settings.fxml"));
                Scene sc=new Scene(root);
                //stage.initStyle(StageStyle.UTILITY);
                stage.setScene(sc);
                stage.show();
    
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    });
        btnItemMain.setOnMouseClicked((MouseEvent e)->{
            try {
                AnchorPane root=FXMLLoader.load(getClass().getResource("ItemMain.fxml"));
                Scene sc=new Scene(root);
                //stage.initStyle(StageStyle.UTILITY);
                stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
        btnReportMain.setOnMouseClicked((MouseEvent e)->{
            try {
                
                AnchorPane root=FXMLLoader.load(getClass().getResource("Reports.fxml"));
                Scene sc=new Scene(root);
                 stage.setScene(sc);
                 //stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
        btnStaticsMain.setOnMouseClicked((MouseEvent e)->{
            try {
               String url_open ="https://itprojectnibm.000webhostapp.com";
java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    });
        btnAccountMain.setOnMouseClicked((MouseEvent e)->{
            try {
                AnchorPane root=FXMLLoader.load(getClass().getResource("TransactionPanel.fxml"));
                Scene sc=new Scene(root);
                 stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
         btnCustomerMain.setOnMouseClicked((MouseEvent e)->{
            try {
                AnchorPane root=FXMLLoader.load(getClass().getResource("MemberMain.fxml"));
                 Scene sc=new Scene(root);
                 //stage.initStyle(StageStyle.UTILITY);
                 stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
    }  
    
    @FXML
    public void GetLoginPage(ActionEvent event) throws IOException{
        Stage stage=new Stage();
    AnchorPane root=FXMLLoader.load(getClass().getResource("Login.fxml"));
    //stage.initStyle(StageStyle.UTILITY);
    Scene sc=new Scene(root);
    stage.setScene(sc);
   
    stage.show();
    
    
    
    
    
    }

   
}
