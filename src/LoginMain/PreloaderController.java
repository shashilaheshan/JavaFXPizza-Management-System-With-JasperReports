
package LoginMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PreloaderController implements Initializable {
 @FXML
    private Pane rootPane;
@FXML
    private Label lblProgress;

    @FXML
    private ProgressBar loadProgress;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       splash n=new splash();
       for(int i=0;i<=100;i++){
        try {
               Thread.sleep(5);
               lblProgress.setText(Integer.toString(i)+"%");
               
           } catch (InterruptedException ex) {
               Logger.getLogger(PreloaderController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        }
    
       n.start();
        
        
        
        
     
    }   
    class splash extends Thread{
    @Override
    public void run(){
    try{
        Thread.sleep(5000);
        
        Platform.runLater(() -> {
            Parent root=null;
            try {
                root = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(PreloaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            rootPane.getScene().getWindow().hide();
            //To change body of generated methods, choose Tools | Templates.
        });
        
        
    }catch(InterruptedException ex){
    System.out.println(ex);
    
    }
    }
    
    }
    
}
