package LoginMain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shashilaheshan
 */
public class TransactionPanelController implements Initializable {
 @FXML
    private ImageView imgBank;
   @FXML
    private ImageView imgCash;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    imgBank.setOnMouseClicked((MouseEvent e)->{
            try {
                Stage stage=new Stage();
                AnchorPane root=FXMLLoader.load(getClass().getResource("BankCash.fxml"));
                Scene sc=new Scene(root);
                 stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
    imgCash.setOnMouseClicked((MouseEvent e)->{
            try {
                Stage stage=new Stage();
                AnchorPane root=FXMLLoader.load(getClass().getResource("cashBookCash.fxml"));
                Scene sc=new Scene(root);
                 stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
    }    
    
   
}
