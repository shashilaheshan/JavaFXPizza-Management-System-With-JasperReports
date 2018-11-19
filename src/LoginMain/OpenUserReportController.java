/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author shashilaheshan
 */
public class OpenUserReportController implements Initializable {
 @FXML
    private JFXComboBox<String> cmbUser=new JFXComboBox();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         cmbUser.getItems().addAll(
            "admin",
            "staff"
            
        );
        
    } 
    public void GenerateReport() throws SQLException{
    
        String type=null;
        String devision=cmbUser.getValue();
        if(devision.equals("admin")){
        type="admin";
        
        
        }else if(devision.equals("staff")){
        type="staff";
        
        
        
        }
         Connection connect = null;
    
				
				try {
                                    
					Class.forName("com.mysql.jdbc.Driver");
					connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza" +
							"?user=root&password=");
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//UserReport.jrxml";
				
					
					// Parameters
					Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					param.put("devision", type);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,param,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
        
    
    
    
    }
    
}
