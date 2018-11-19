/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
public class ReportsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    DbConnection obj=new DbConnection();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    public void GetItemReport() throws IOException, SQLException{
      Connection connect = null;
    
				
				try {
                                    
					Class.forName("com.mysql.jdbc.Driver");
					connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza" +
							"?user=root&password=");
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//itemReport.jrxml";
				
					
					// Parameters
					Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					param.put("item_code", 100);
					
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
  
    
    public  void OpenUSerReport(){
        try {
            Pane root=FXMLLoader.load(getClass().getResource("OpenUserReport.fxml"));
            Scene sc=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(sc);
            stage.show();
            
        } catch (Exception e) {
        }
    
    
    
    
    }
    public void getCustomerReport() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//CustomerReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
     public void getEmployeeReport() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//EmployeeReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
     public void getItemReport() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//itemReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
 public void getPaymentReport() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//PaymentReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }



 public void getInvoiceReports() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//InvoiceReports.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
 public void getExpireItemReports() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//ExpireItemReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
  public void getGRNReport() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//GRNDetails.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
                } catch (IOException ex) {
                   
                }
				
				finally{
                       try {
                           connect.close();
                       } catch (SQLException ex) {
                          
                       }
                                
                                }
    }
   public void getCustomerRepor1t() throws SQLException{
        Connection connect = null;
    
				
				try {
                                    
					//Class.forName("com.mysql.jdbc.Driver");
					connect = obj.connect();
				
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//CustomerReport.jrxml";
				
					
					// Parameters
					//Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					//param.put("item_code", 100);
					
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,null,connect);
					
					JasperViewer jv=new JasperViewer(ip,false);
                                        jv.setVisible(true);
                                       
					
				} catch (JRException e) {
                                    // TODO Auto-generated catch block
                                    System.out.println(e);

				//} catch (ClassNotFoundException ex) {
                    
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
