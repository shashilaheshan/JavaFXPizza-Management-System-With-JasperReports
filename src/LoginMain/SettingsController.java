/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shashilaheshan
 */
public class SettingsController implements Initializable {
    @FXML
    private ImageView imgBackup;
    
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       imgBackup.setOnMouseClicked((MouseEvent e)->{
           restoreDB("/Users/shashilaheshan/Desktop/shashila.sql");
                
    
    });
        
        
        // TODO
    }    
     @FXML
             public void restoreDB(String path){
 
  String executeCmd = "/Applications/XAMPP/xamppfiles/bin/mysqldump -uroot  pizza >" + path;
 
  System.out.println(executeCmd);
 
  Process runtimeProcess;
 
  try
  {
   runtimeProcess = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", executeCmd });
 
   int processComplete = runtimeProcess.waitFor();
 
   System.out.println(processComplete);
 
   if(processComplete == 0)
   {
    System.out.println("Restored the Backup !");
   }
   else
   {
    System.out.println("Couldn't Restore the backup !");
   }
  }
  catch(Exception ex)
  {
   ex.printStackTrace();
  }
 
 }
 
}
