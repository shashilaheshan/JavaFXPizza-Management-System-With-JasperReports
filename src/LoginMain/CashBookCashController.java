/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author shashilaheshan
 * 
    
 */

public class CashBookCashController implements Initializable {
@FXML
    private TableView<PaymentDetails> tableCash;

    @FXML
    private TableColumn<PaymentDetails, Integer> paymentId;

    @FXML
    private TableColumn<PaymentDetails, Integer> memberId;

    @FXML
    private TableColumn<PaymentDetails, Integer> amount;

    @FXML
    private TableColumn<PaymentDetails, String> date;

    @FXML
    private Label txtTotal;
    /**
     * Initializes the controller class.
     */
    private ObservableList<PaymentDetails> data;
     Connector.DbConnection obj=new DbConnection();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        populate();
    } catch (SQLException ex) {
        Logger.getLogger(BankCashController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
      public void populate() throws SQLException {
        String sql="select * from cash_book";
        int total=0;
        String sqlTotal="select sum(amount) from cash_book";
         try{
             java.sql.Connection conn=obj.connect();
         data=FXCollections.observableArrayList();
         ResultSet rs=conn.createStatement().executeQuery(sql);
         ResultSet rs2=conn.createStatement().executeQuery(sqlTotal);
         while(rs.next()){
         data.add(new PaymentDetails(rs.getInt(1),rs.getInt("member_id"),rs.getInt(3),rs.getString(4)));
        
         
         }
            while(rs2.next()){
            total+=rs2.getInt(1);
            
            
            }
         }
         catch(Exception ex){
             System.out.print(ex);
         }
         paymentId.setCellValueFactory(new PropertyValueFactory<>("payId"));
         memberId.setCellValueFactory(new PropertyValueFactory<>("mem_Id"));
         amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
         tableCash.setItems(data);
          txtTotal.setText("Cash in Bank : "+total);
         
    }
  
}
