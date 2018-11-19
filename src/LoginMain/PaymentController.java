/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author llb
 */
public class PaymentController implements Initializable {

     @FXML
    private JFXButton btnenter;

    @FXML
    private JFXRadioButton radcash;

    @FXML
    private Label lblmobile;

    @FXML
    private Label lblammount;

    @FXML
    private JFXRadioButton radcard;
    @FXML
    private Label invoice_id;
    @FXML
    private Label txtPID;
Connector.DbConnection con=new DbConnection();
    @FXML
    void enter(ActionEvent event) throws SQLException {
        String methoud;
       if(radcash.isSelected())
       {
           methoud="Cash Payment";
       }
       else
       {
           methoud="Card Payment";
       }
       String url="jdbc:mysql://localhost:3306/pizza";
       String uname="root";
       String pass="";
       Connection conn;
        DateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
               Date date = new Date();
        try {
             conn=DriverManager.getConnection(url,uname,pass);
             Statement st=conn.createStatement();
             String insertacc="insert into  paymentbook(method,amount,member_id,date)  values('"+methoud+"',"+lblammount.getText()+","+lblmobile.getText()+",'"+dateFormat.format(date)+"')";
             st.executeUpdate(insertacc);
           
             
             if(methoud=="Cash Payment")
             {
             String sql="insert into cash_book(payment_id,member_id,amount,date) values("+txtPID.getText()+","+lblmobile.getText()+","+lblammount.getText()+",'"+dateFormat.format(date)+"') ";
             st.executeUpdate(sql);
             }
             else if(methoud=="Card Payment"){
             
                  String sql="insert into bank_book(payment_id,member_id,amount,date) values("+txtPID.getText()+","+lblmobile.getText()+","+lblammount.getText()+",'"+dateFormat.format(date)+"') ";
                 st.executeUpdate(sql);
             }
           
             
             GetInvoiceReport();
            
             
             
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void GetInvoiceReport() throws IOException, SQLException{
      Connection connect = null;
    
				
				try {
                                    
					Class.forName("com.mysql.jdbc.Driver");
					connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza" +
							"?user=root&password=");
				int a=Integer.parseInt(invoice_id.getText());
                                int amount=0;
                                int mem_id=0;
                                int discount=0;
                                int fin_amount=0;
                                    ResultSet rs=connect.createStatement().executeQuery("select Amount,Discount,Members_Member_Id,Final_amount from invoice_summary where Invoice_no="+a+"");
                                     
                                    while(rs.next()){
                                     
                                         amount=rs.getInt(1);
                                         mem_id=rs.getInt(3);
                                         discount=rs.getInt(2);
                                         fin_amount=rs.getInt(4);
                                         
                                     }
				
				// Application path
				
                                         
					String report = new File(".").getCanonicalPath()+ "//src//LoginMain//Invoice.jrxml";
				
					
					// Parameters
					Map param=new HashMap();
                                        //int ab=Integer.parseInt(txt.getText());
					param.put("invoice_no", a);
                                        param.put("mem_id", mem_id);
                                        param.put("total", amount);
                                        param.put("discount", discount);
					param.put("finalAmount",fin_amount);
                                        System.out.println(mem_id);
					// Report Viewer
					JasperReport ir = JasperCompileManager.compileReport(report);
					JasperPrint ip = JasperFillManager.fillReport(ir,param,connect);
					
					JasperViewer jv=new JasperViewer(ip);
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
    public void getPaymentID() throws SQLException{
    
    Connection conn=con.connect();
   String sql="select count(payment_id) from paymentbook;";
   ResultSet rs=conn.createStatement().executeQuery(sql);
   while(rs.next()){
       int a=rs.getInt(1)+1;
   txtPID.setText(Integer.toString(a));
   
   }
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             // FXMLDocumentController fef=new FXMLDocumentController();
             // int xx=Integer.parseInt(fef.lbltot.getText()) ;
             
             
             //lblmobile.setText(Integer.toString(mob));
             // lblammount.setText(Integer.toString(xx));
             getPaymentID();
         } catch (SQLException ex) {
             Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }   
    
    public void setText(int mob,int ammount,int invoice_no)
{
    this.lblmobile.setText(Integer.toString(mob));
    this.lblammount.setText(Integer.toString(ammount));
    this.invoice_id.setText(Integer.toString(invoice_no));
    
}
    
}

