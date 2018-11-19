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
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class GrnController implements Initializable {
Statement st=null;
   PreparedStatement pst=null;
   DbConnection conn = new DbConnection();
   Connection con;
       @FXML
    private TableColumn<DDatadetailGRN, Integer> itemqty;

    @FXML
    private JFXButton btnaddall;

    @FXML
    private Label lblgrnno;

    @FXML
    private JFXButton btnadd;

    @FXML
    private JFXTextField txtqty;

    

    @FXML
    private TableColumn<DDatadetailGRN, String> itemcode;

    @FXML
    private TableView<DDatadetailGRN> grntable;
    
      private ObservableList<DDatadetailGRN> data;
      private ObservableList<Integer> option=FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Integer> cmbItemCode=new JFXComboBox(option);;
    void AddCombo(){
    try {
            Connection con=conn.connect();
            String sql="select item_code from item ";
         PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
     while(rs.next()){
     option.add(rs.getInt(1));
     
     
     }
     rs.close();
     pst.close();
     cmbItemCode.setItems(option);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }

    @FXML
    void add(ActionEvent event) {
       
        try {
            
             {
                 int item_code=(Integer)cmbItemCode.getSelectionModel().getSelectedItem();
                 int qty=Integer.parseInt(txtqty.getText());
                 data.add(new DDatadetailGRN(item_code,qty));
                 grntable.setItems(data);
                 cmbItemCode.valueProperty().set(null);
                 txtqty.setText("");
                
             }
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void addall(ActionEvent event) {
        
        
        String url="jdbc:mysql://localhost:3306/pizza";
       String uname="root";
       String pass="";
       Connection conn;
        try {
            
            
             conn=DriverManager.getConnection(url,uname,pass);
              Statement st3=conn.createStatement();
               DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
               Date date = new Date();
             
              String grnsum="insert into grn_summary(Grn_No,date) values("+lblgrnno.getText()+",'"+dateFormat.format(date)+"')";
              st3.executeUpdate(grnsum);
         for(int i=0;i<data.size();i++)
        {
            int unitPrice=0;
            int total=0;
           int name=data.get(i).getname();
           int qtys=data.get(i).getqty();
            Statement st4=conn.createStatement();
            String sqlGetReceivePrice="select Recieve_price from item where item_code="+name+"";
            
            ResultSet rs=st4.executeQuery(sqlGetReceivePrice);
           while(rs.next()){
           unitPrice=rs.getInt("Recieve_price");
           
           
           }
           total=unitPrice*qtys;
           String grnadditem="insert into grn_details values("+lblgrnno.getText()+",'"+name+"',"+qtys+","+unitPrice+","+total+")";
           st4.executeUpdate(grnadditem);
            Statement st5=conn.createStatement();
           String updatesql="update item set Qty=Qty+"+qtys+" where item_code='"+name+"'";
           st5.executeUpdate(updatesql);
           
           
        }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        

    }
    public void maxnm()
{
     String url="jdbc:mysql://localhost:3306/pizza";
       String uname="root";
       String pass="";
       Connection conn;
       try
       {
            conn=DriverManager.getConnection(url,uname,pass);
              Statement st6=conn.createStatement(); 
           String mqxsql="select max(Grn_No) from grn_summary;";
            ResultSet rst=st6.executeQuery(mqxsql);
             while(rst.next())
             {
               int ino=rst.getInt(1);
                 lblgrnno.setText(Integer.toString(ino+1));
                
             }
            
       }
       catch(Exception ex)
       {
           System.out.println(ex);
       }
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maxnm();
        AddCombo();
         data=FXCollections.observableArrayList();
         itemqty.setCellValueFactory(c ->new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getqty())));
         itemcode.setCellValueFactory(c ->new ReadOnlyStringWrapper(String.valueOf(c.getValue().getname())));
         
         
    }    
    
}
