/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author shashilaheshan
 */
public class OrderControllerController implements Initializable {

   @FXML
    private ImageView imgInvoice;

    @FXML
    private JFXTextField txtInId;

    @FXML
    private JFXTextField txtMemId;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtFinalAmt;

    @FXML
    private JFXDatePicker txtDateInvoiced;

    @FXML
    private Button btnLoad1;

    

    @FXML
    private Button btnLoad21;

    @FXML
    private Button btnLoad211;

    @FXML
    private TableView<InvoiceDetailsSummery> tableItem1=new TableView<>();

    @FXML
    private TableColumn<InvoiceDetailsSummery, Integer> invID;

    @FXML
    private TableColumn<InvoiceDetailsSummery, Integer> memId;

    @FXML
    private TableColumn<InvoiceDetailsSummery, Integer> amt;

    @FXML
    private TableColumn<InvoiceDetailsSummery, Integer> discount;

    @FXML
    private TableColumn<InvoiceDetailsSummery, Integer> finalAmt;

    @FXML
    private TableColumn<InvoiceDetailsSummery, String> date;
  private ObservableList<InvoiceDetailsSummery>data;
   private DbConnection con=new DbConnection();
   private ObservableList<Integer> option=FXCollections.observableArrayList();
   @FXML
    private JFXComboBox<Integer> cmbITcodeExpire=new JFXComboBox(option);
    @FXML
    void DeleteData(ActionEvent event) {
try {
            Connection conn=con.connect();
            String sql="delete from Invoice_summary where Nic="+invID.getText()+" ";
            Statement st=conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    @FXML
    void LoadGrid(ActionEvent event) throws SQLException {
     populate();
    }

    @FXML
    void UpdateDataExpire(ActionEvent event) throws SQLException {

   Connection conn=con.connect();
   Statement st=conn.createStatement();
                 
                 String sqlUpdate="update Invoice_summary set Members_Member_Id='"+txtMemId.getText()+"',Amount='"+txtAmount.getText()+"',Discount='"+txtDiscount.getText()+"',Final_amount='"+txtFinalAmt.getText()+"',Date='"+txtDateInvoiced.getValue().toString()+"' where Nic='"+txtInId.getText()+"'";
              st.executeUpdate(sqlUpdate);
              populate();
              
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
           populate();
           AddCombo();
       } catch (SQLException ex) {
           Logger.getLogger(OrderControllerController.class.getName()).log(Level.SEVERE, null, ex);
       }
 imgInvoice.setOnMouseClicked((MouseEvent e)->{
            try {
                Stage stage=new Stage();
                AnchorPane root=FXMLLoader.load(getClass().getResource("InvoiceMain.fxml"));
                Scene sc=new Scene(root);
                 stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OrderControllerController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    
    });
 cmbITcodeExpire.setOnAction((event) -> {
                String sql="select * from invoice_summary where Invoice_no=?";
            try {
                Connection conn=con.connect();
                PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,(Integer)cmbITcodeExpire.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    
                txtInId.setText(Integer.toString(rs.getInt("Invoice_no")));
                txtMemId.setText(Integer.toString(rs.getInt("Members_Member_Id")));
                txtAmount.setText(Integer.toString(rs.getInt("amount")));
                txtDiscount.setText(Integer.toString(rs.getInt("discount")));
                txtFinalAmt.setText(Integer.toString(rs.getInt("Final_amount")));
               
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd-MMM-yy");


           final ZonedDateTime parsed = ZonedDateTime.parse(rs.getString("Date"), formatter);
                txtDateInvoiced.setValue(LocalDate.parse(parsed.toString()));
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderControllerController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
    
});
     
    }    
    
    void populate() throws SQLException {
        String sql="select * from invoice_summary;";
         //try{
             java.sql.Connection conn=con.connect();
         data=FXCollections.observableArrayList();
         ResultSet rs=conn.createStatement().executeQuery(sql);
         while(rs.next()){
         data.add(new InvoiceDetailsSummery(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6)));
         
         }
         //}
         //catch(Exception ex){
           //  System.out.print(ex);
        // }
         invID.setCellValueFactory(new PropertyValueFactory<>("invId"));
         memId.setCellValueFactory(new PropertyValueFactory<>("memId"));
         amt.setCellValueFactory(new PropertyValueFactory<>("amt"));
         discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
         finalAmt.setCellValueFactory(new PropertyValueFactory<>("finalamt"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
         
        tableItem1.setItems(null);
         tableItem1.setItems(data);
         
    }
    void AddCombo(){
    try {
            Connection conn=con.connect();
            String sql="select Invoice_no from invoice_summary; ";
         PreparedStatement pst=conn.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
     while(rs.next()){
     option.add(rs.getInt(1));
     
     
     }
     rs.close();
     pst.close();
     cmbITcodeExpire.setItems(option);
     
     
        } catch (SQLException ex) {
            Logger.getLogger(OrderControllerController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
}
