/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author shashilaheshan
 */
public class OpenErrorProviders {
    
    
    public void OpenEmptyField() throws IOException{
    try{
     AnchorPane root=FXMLLoader.load(getClass().getResource("EmptyError.fxml"));
              
               Stage stage=new Stage();
               Scene sc=new Scene(root);
               stage.isIconified();
               stage.initStyle(StageStyle.UTILITY);
              //stage.initStyle(StageStyle.UNIFIED);
               stage.setScene(sc);
               stage.show();}catch(Exception ex){
               System.err.println(ex);
               
               
               }
    
    }
}
