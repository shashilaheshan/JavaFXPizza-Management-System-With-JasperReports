/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import Connector.DbConnection;
import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class ItemMainController implements Initializable {
   
   
   Statement st=null;
   PreparedStatement pst=null;
   DbConnection conn = new DbConnection();
   Connection con;
   
   @FXML
    private JFXTextField txtICode;

    @FXML
    private JFXTextField txtIName;

    @FXML
    private JFXTextField txtROLevel;

    @FXML
    private JFXTextField txtRPrice;

    @FXML
    private JFXTextField txtSPrice;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtOLevel;

    @FXML
    private JFXTextField txtrPrice;

    @FXML
    private JFXTextField txtsPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtQty2;
   
     @FXML
    private TableView<ItemDetails> tableItem;

    @FXML
    private TableColumn<ItemDetails,Integer> itemCode;

    @FXML
    private TableColumn<ItemDetails,String> itemName;

    @FXML
    private TableColumn<ItemDetails,Integer> itemOLevel;

    @FXML
    private TableColumn<ItemDetails,Integer> itemRprice;
    @FXML
    private TableColumn<ItemDetails, Integer> itemQty;
    private ObservableList<Integer> option=FXCollections.observableArrayList();
     private ObservableList<Integer> optionExpire=FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Integer> cmbItemCode=new JFXComboBox(option);;
    @FXML
    private JFXComboBox<Integer> cmItemCodeExp=new JFXComboBox(option);
      @FXML
    private JFXDatePicker txtDateExpire;

    @FXML
    private JFXTextField txtQtyExpire;

   


   
    @FXML
    private JFXTextField txtiCodeExpire;

    @FXML
    private JFXTextField txtQtyexpire;


    @FXML
    private JFXComboBox<Integer> cmbITcodeExpire=new JFXComboBox(optionExpire);

    @FXML
    private TableView<ItemExpire> tableItem1;

    @FXML
    private TableColumn<ItemExpire, Integer> i_codeExpire;

    @FXML
    private TableColumn<ItemExpire,Integer> qty_expire;

    @FXML
    private TableColumn<ItemExpire, Integer> dateExpire;

    @FXML
    private JFXTextField txtExpireID;
      @FXML
    private TableColumn<ItemExpire, Integer> expire_id;

    @FXML
    private TableColumn<ItemDetails,Integer> itemSprice;
  private ObservableList<ItemDetails> data;
  private ObservableList<ItemExpire> data2;

    @FXML
    void DeleteData(ActionEvent event) throws SQLException {
 DeleteData();
    }
    public void AddExpireItem() throws SQLException{
       // try {
            
            Connection con=conn.connect();
           int it_code=cmItemCodeExp.getSelectionModel().getSelectedItem();
            String qty=txtQtyExpire.getText();
           String date=txtDateExpire.getValue().toString();
            String sql="insert into expire_item(item_code,qty,expire_date,added_date)values("+it_code+","+Integer.parseInt(qty)+",'"+date+"',Now())";
            
            String sql2="update item set qty=qty-"+qty+" where item_code="+it_code+"";
            if(qty!=null){
             st.execute(sql2);}
            Statement st=con.createStatement();
            st.executeUpdate(sql);
           
            
        //} catch (Exception e) {
            //System.out.println(e);
        //}
        //finally{
            con.close();
            
            
        //}
    
    
    }
    public void ClearExpire(){
    
    
    }
public void GetItemCode() throws SQLException{
    try {
        String sql="select max(item_code) from item";
         Connection co=conn.connect();
 
   ResultSet rs=co.createStatement().executeQuery(sql);
   while(rs.next()){
       int a=rs.getInt(1)+1;
   txtICode.setText(Integer.toString(a));
   
   }
   
    
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    
    


}
 public void LoadDataExpire(){
    data2=FXCollections.observableArrayList();
        try{
        con=conn.connect();
        String sql="select * from expire_item;";
        ResultSet rs=con.createStatement().executeQuery(sql);
        while(rs.next()){
        ItemExpire obj=new ItemExpire();
        obj.itemCode.set(rs.getInt("item_code"));
        obj.itemQty.set(rs.getInt("qty"));
        obj.exDate.set(rs.getString("expire_date"));
        obj.ex_id.set(rs.getInt("item_id"));
        
        data2.add(obj);
        }
        tableItem1.setItems(data2);
        
        
        
        
        
        }catch(Exception ex){
            System.out.print(ex);}
    
    }
    public void LoadData(){
    data=FXCollections.observableArrayList();
        try{
        con=conn.connect();
        String sql="select * from item;";
        ResultSet rs=con.createStatement().executeQuery(sql);
        while(rs.next()){
        ItemDetails obj=new ItemDetails();
        obj.itemCode.set(rs.getInt("item_code"));
        obj.itemName.set(rs.getString("Name"));
        obj.itemROLevel.set(rs.getInt("ReoLevel"));
        obj.itemRprice.set(rs.getInt("Recieve_price"));
        obj.itemSprice.set(rs.getInt("Sell_price"));
        obj.itemQty.set(rs.getInt("qty"));
        
        data.add(obj);
        }
        tableItem.setItems(data);
        
        
        
        
        
        }catch(Exception ex){System.out.print(ex);}
    
    }
    public void OpenGRN() throws IOException{
    
    AnchorPane root=FXMLLoader.load(getClass().getResource("Grn.fxml"));
                 Stage stage=new Stage();
            Scene sc=new Scene(root);
                stage.setScene(sc);
                stage.show();
    
    }
    @FXML
    void LoadGrid(ActionEvent event) {
        LoadData();
       
    }

    @FXML
    void UpdateData(ActionEvent event) throws SQLException {
          UpdateData();
        
    }

   
    @FXML
    void ClearField(ActionEvent event) {
      //txtICode.setText("");
      txtIName.setText("");
      txtROLevel.setText("");
      txtRPrice.setText("");
      txtSPrice.setText("");
      txtQty.setText("");
      
    }

    @FXML
    void addItem(ActionEvent event) throws SQLException {
        try{
 int code=Integer.parseInt(txtICode.getText());
 String name=txtIName.getText();
 int reolevel=Integer.parseInt(txtROLevel.getText());
 int rPrice=Integer.parseInt(txtRPrice.getText());
 int sPrice=Integer.parseInt(txtSPrice.getText());
 int qty=Integer.parseInt(txtQty.getText());
 con=conn.connect();
 
 conn.connect();
 
String sql="insert into item values('"+code+"','"+name+"','"+reolevel+"','"+rPrice+"','"+sPrice+"',"+qty+")";
 st=con.createStatement();
st.executeUpdate(sql);
            ClearField(event);
 
        }catch(Exception e){
        System.out.print(e);
        
        }
    }
    void DeleteData() throws SQLException{
    con=conn.connect();
    String sql="DELETE FROM item where item_code='"+txtItemCode.getText()+"';";
    Statement st=con.createStatement();
    st.execute(sql);
     LoadData();
    
    }
    void UpdateData() throws SQLException{
    con=conn.connect();
    String sql="update item set Name='"+txtName.getText()+"',ReoLevel='"+txtOLevel.getText()+"',Recieve_price='"+txtrPrice.getText()+"',Sell_price='"+txtsPrice.getText()+"',qty="+txtQty2.getText()+" where item_code='"+txtItemCode.getText()+"';";
    Statement st=con.createStatement();
   
    st.execute(sql);
     LoadData();
    }
     public void UpdateDataExpire() throws SQLException{
    con=conn.connect();
    String sql="update expire_item set item_code='"+txtiCodeExpire.getText()+"',qty='"+txtQtyexpire.getText()+"',expire_date='"+txtDateExpire.getValue().toString()+"'  where item_id='"+txtExpireID.getText()+"';";
    Statement st=con.createStatement();
   
    st.execute(sql);
     LoadDataExpire();
    }
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
     cmItemCodeExp.setItems(option);
     
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    void AddComboExpire(){
    try {
            Connection con=conn.connect();
            String sql="select item_id from expire_item ";
         PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
     while(rs.next()){
     optionExpire.add(rs.getInt(1));
     
     
     }
     rs.close();
     pst.close();
     cmbITcodeExpire.setItems(optionExpire);
     
     
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       try {
           GetItemCode();
           AddComboExpire();
           LoadDataExpire();
       } catch (SQLException ex) {
           Logger.getLogger(ItemMainController.class.getName()).log(Level.SEVERE, null, ex);
       }
      
         LoadData();
           AddCombo();
        itemCode.setCellValueFactory(b -> new ReadOnlyObjectWrapper(Integer.valueOf(b.getValue().getItemCode())));
        
        itemName.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getName())));
        
        itemOLevel.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getRoLevel())));
        itemRprice.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getrPrice())));
         itemSprice.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getsPricee())));
         itemQty.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getItemQty())));
          i_codeExpire.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getItemCodeEx())));
         dateExpire.setCellValueFactory(c -> new ReadOnlyObjectWrapper(String.valueOf(c.getValue().getExDate())));
         qty_expire.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getItemQtyEx())));
         expire_id.setCellValueFactory(c -> new ReadOnlyObjectWrapper(Integer.valueOf(c.getValue().getEx_id())));
         
       
    
       
        
        // TODO
        tableItem1.setOnMouseClicked(((event) -> {
            String Item_id=tableItem1.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getEx_id().toString();
            String ItemCode=tableItem1.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getItemCodeEx().toString();
            String qtyEx=tableItem1.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getItemQtyEx().toString();
            String dateEx=tableItem1.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getExDate().toString();
            
                    txtExpireID.setText(Item_id);
                txtDateExpire.setValue(LocalDate.parse(dateEx));
                txtiCodeExpire.setText(ItemCode);
                txtQtyexpire.setText(qtyEx);
                
            
                       
            
        }));
        /*tableItem.setOnMouseClicked(((event) -> {
            String Item_id=tableItem.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getEx_id().toString();
            String ItemCode=tableItem.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getItemCodeEx().toString();
            String qtyEx=tableItem.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getItemQtyEx().toString();
            String dateEx=tableItem.getSelectionModel().getTableView().getSelectionModel().getSelectedItem().getExDate().toString();
            
                    txtExpireID.setText(Item_id);
                txtDateExpire.setValue(LocalDate.parse(dateEx));
                txtiCodeExpire.setText(ItemCode);
                txtQtyexpire.setText(qtyEx);
                
            
                       
            
        }));*/
        
          cmbITcodeExpire.setOnAction((event) -> {
                String sql="select * from expire_item where item_id=?";
            try {
                Connection con=conn.connect();
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1,(Integer)cmbITcodeExpire.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    
                    txtExpireID.setText(Integer.toString(rs.getInt("item_id")));
                txtDateExpire.setValue(LocalDate.parse(rs.getString("expire_date")));
                txtiCodeExpire.setText(Integer.toString(rs.getInt("item_code")));
                txtQtyexpire.setText(Integer.toString(rs.getInt("qty")));
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
    
});
     
        
        cmbItemCode.setOnAction((event) -> {
       String sql="select * from item where item_code=?";
            try {
                Connection con=conn.connect();
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1,(Integer)cmbItemCode.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    
                    
                txtItemCode.setText(rs.getString("item_code"));
                txtName.setText(rs.getString("Name"));
                txtOLevel.setText(rs.getString("ReoLevel"));
                txtrPrice.setText(rs.getString("Recieve_price"));
                txtsPrice.setText(rs.getString("Sell_price"));
                 txtQty2.setText(rs.getString("qty"));
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
    
});
     
        
    
}
    }    
    

