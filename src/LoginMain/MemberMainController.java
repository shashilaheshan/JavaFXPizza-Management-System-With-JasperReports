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
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MemberMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtMem_it;
     @FXML
    private JFXTextField txtMemId;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private JFXTextField txtAdd;
    @FXML
    private JFXTextField empid1;

    @FXML
    private JFXTextField txtFname1;

    @FXML
    private JFXTextField txtLName1;

    @FXML
    private JFXTextField txtNic1;
    @FXML
    private JFXTextField txtAddress;

     @FXML
    private TableView<MemberDetails> tableMember;

    @FXML
    private TableColumn<MemberDetails,Integer> colNum;

    @FXML
    private TableColumn<String, MemberDetails> columFName;

    @FXML
    private TableColumn< MemberDetails,String> columLName;

    @FXML
    private TableColumn<MemberDetails,String> clmAddress;

    @FXML
    private TableColumn<MemberDetails,String> columnDate;


 
       @FXML
    private DatePicker txtDate1;

    @FXML
    private Button btnDelete1;

    @FXML
    private Button btnUp;

    @FXML
    private Button btnRefre;
    
    private ObservableList<Integer> option=FXCollections.observableArrayList();
    
       @FXML
    private JFXComboBox<Integer> cmbNicnew=new JFXComboBox(option);
        private ObservableList<MemberDetails> data;
        Connector.DbConnection obj=new DbConnection();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AddCombo();
         cmbNicnew.setOnAction((event) -> {
                String sql="select * from members where Member_Id=?";
             try {
                java.sql.Connection con=obj.connect();
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1,(Integer)cmbNicnew.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    
                    txtMemId.setText(Integer.toString(rs.getInt("Member_Id")));
                txtFname1.setText(rs.getString("Frist_Name"));
                txtLName1.setText(rs.getString("Last_Name"));
                txtAddress.setText(rs.getString("address"));
                DateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
                Date date = new Date();
                txtDate1.setValue(LocalDate.parse(rs.getString("Date")));
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
    
});
        try {
            populate();
        } catch (SQLException ex) {
            Logger.getLogger(MemberMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public void ClearFields(){
    txtDate.setValue(null);
    txtFname.setText("");
    txtLName.setText("");
    txtMem_it.setText("");
    txtAdd.setText("");
   
    }
    
    public void DeleteData() throws SQLException{
    Connection con=(Connection)obj.connect();
    String sql="DELETE FROM members where Member_Id='"+txtMemId.getText()+"';";
    java.sql.Statement st=con.createStatement();
    st.execute(sql);
     populate();
    
    }
     public void UpdateData() throws SQLException{
    Connection con=(Connection) obj.connect();
    String sql="update members set Frist_Name='"+txtFname1.getText()+"',Last_Name='"+txtLName1.getText()+"',Date='"+txtDate1.getValue().toString()+"',address='"+txtAddress.getText()+"' where item_code='"+txtMemId.getText()+"';";
    java.sql.Statement st=con.createStatement();
   
    st.execute(sql);
     populate();
    }
    public void populate() throws SQLException {
        String sql="select * from members";
         try{
             java.sql.Connection conn=obj.connect();
         data=FXCollections.observableArrayList();
         ResultSet rs=conn.createStatement().executeQuery(sql);
         while(rs.next()){
         data.add(new MemberDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
         
         }
         }
         catch(Exception ex){
             System.out.print(ex);
         }
         colNum.setCellValueFactory(new PropertyValueFactory<>("memId"));
         columFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
         columLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
    
         clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
         columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
         tableMember.setItems(data);
         
    }
   
    void AddCombo(){
    try {
            java.sql.Connection con=obj.connect();
            String sql="select Member_Id from members ";
         PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
     while(rs.next()){
     option.add(rs.getInt(1));
     
     
     }
     rs.close();
     pst.close();
     cmbNicnew.setItems(option);
     
     
        } catch (SQLException ex) {
            Logger.getLogger(MemberMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
    public void Add() throws SQLException{
        try {
           Connection con=(Connection) obj.connect();
         
        int mem_id;
        String fname,lname,address;
        mem_id=Integer.parseInt(txtMem_it.getText());
        fname=txtFname.getText();
        lname=txtLName.getText();
        address=txtAdd.getText();
        //date=txtDate.valueProperty().asString("DD-MM-YY").toString();
         String date=txtDate.getValue().toString();
            
          
            
        //Date dt=new Date(txtDate.getValue().toString());
        
       String sql="insert into members(Member_Id,Frist_Name,Last_Name,Date,address)values("+mem_id+",'"+fname+"','"+lname+"','"+date+"','"+address+"')";
       Statement st=(Statement) con.createStatement();
       st.executeUpdate(sql);
       ClearFields();
   
         
        } catch (Exception e) {
            System.out.println(e);
        }
         finally{
            
        }
        
    }
    
}
