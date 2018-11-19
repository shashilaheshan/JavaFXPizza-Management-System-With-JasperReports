/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;


import Connector.DbConnection;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author root
 */
public class EmployeeMainController implements Initializable {
  
    @FXML
    private JFXTextField empid;

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtTp;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtDev;
    @FXML
    private JFXTextField txtFname1;

    @FXML
    private JFXTextField txtLName1;

    @FXML
    private JFXTextField txtNic1;

    @FXML
    private JFXTextField txtAddress1;

    @FXML
    private JFXTextField txtTp1;

    @FXML
    private JFXTextField txtMail1;

    @FXML
    private JFXTextField txtDev1;
    @FXML
    private JFXTextField empNameShift;

    @FXML
    private Button btnAdd;
      //@FXML
    //private AutoCompleteTextField<?> txtEmpIDAuto;
   
    @FXML
    private TableView<EmployeeDetail> tableEmployee=new TableView<>();

    @FXML
    private TableColumn<EmployeeDetail, Integer> columnic;
    @FXML
    private TableColumn<EmployeeDetail, String> columFName;

    @FXML
    private TableColumn<EmployeeDetail, String> columLName;

    @FXML
    private TableColumn<EmployeeDetail, String> columMobile;

    @FXML
    private TableColumn<EmployeeDetail, String> columEmail;

    @FXML
    private TableColumn<EmployeeDetail, String> columAdress;

    @FXML
    private TableColumn<EmployeeDetail, String> columDevision;
    @FXML
    private Button btnLoad1;
    @FXML
    private JFXTextField txtSal;
   

   private ObservableList<EmployeeDetail>data;
   private DbConnection con;
   private ObservableList<Integer> option=FXCollections.observableArrayList();
   @FXML
    private JFXComboBox<Integer> cmbNicnew=new JFXComboBox(option);
    @FXML
    private JFXComboBox<Integer> empNICShift=new JFXComboBox(option);
    
   @FXML
    private Label lblEmployeeId;
    @FXML
    private JFXComboBox<String> empBranch=new JFXComboBox<>();
   @FXML
    private DatePicker empShiftDate;
   
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empBranch.getItems().addAll(
            "Kurunegala",
            "Kandy",
            "Colombo",
            "Anuradhapura",
            "Jaffna"
            
        );
        // TODO
        con=new DbConnection();
        cmbNicnew.setEditable(true);
        try {
            
            AddEmpIdtoLabel();
            populate();
            Delete();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        empNICShift.setOnAction(((event) -> {
        int id1=empNICShift.getSelectionModel().getSelectedItem();
        String sql="select Fname from employees where Nic="+id1+"";
           try {
                Connection conn=con.connect();
                Statement st=conn.createStatement();
                
                //PreparedStatement pst=conn.prepareStatement(sql);
                //pst.setInt(1,(Integer)cmbNicnew.getSelectionModel().getSelectedItem());
                ResultSet rs=st.executeQuery(sql);
                while(rs.next()){
                    
                empNameShift.setText(rs.getString("Fname"));
                
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
                
            
        }));
        
        cmbNicnew.setOnAction((event) -> {
            
          String sql="select * from employees where Nic="+cmbNicnew.getSelectionModel().getSelectedItem()+"";
            try {
                Connection conn=con.connect();
                Statement st=conn.createStatement();
                
                PreparedStatement pst=conn.prepareStatement(sql);
                //pst.setInt(1,(Integer)cmbNicnew.getSelectionModel().getSelectedItem());
                ResultSet rs=st.executeQuery(sql);
                while(rs.next()){
                    int nic=rs.getInt("Nic");
                    String nic2=Integer.toString(nic);
                txtNic1.setText(nic2);
                txtFname1.setText(rs.getString("Fname"));
                txtLName1.setText(rs.getString("Lname"));
                txtTp1.setText(rs.getString("Mobile"));
                txtAddress1.setText(rs.getString("address"));
                txtMail1.setText(rs.getString("Email"));
                txtDev1.setText(rs.getString("devision"));
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
            }

});
        
    }  
    @FXML
    void Delete() {
        try {
            Connection conn=con.connect();
            String sql="select Nic from employees ";
   PreparedStatement pst=conn.prepareStatement(sql);
   ResultSet rs=pst.executeQuery();
     while(rs.next()){
     option.add(rs.getInt(1));
     
     
     }
     rs.close();
     pst.close();
     cmbNicnew.setItems(option);
     empNICShift.setItems(option);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    @FXML
    void Delete2(ActionEvent event) throws  SQLException{
        try {
            Connection conn=con.connect();
            String sql="delete from employees where Nic="+txtNic1.getText()+" ";
            Statement st=conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
   public void AddEmpIdtoLabel() throws SQLException{
   Connection conn=con.connect();
   String sql="select count(Nic) from employees;";
   ResultSet rs=conn.createStatement().executeQuery(sql);
   while(rs.next()){
       int a=rs.getInt(1)+1;
   lblEmployeeId.setText(Integer.toString(a));
   
   }
   
   }
   @FXML
   void Update(ActionEvent event) throws SQLException{
   Connection conn=con.connect();
   Statement st=conn.createStatement();
                 String nic=txtNic1.getText();
                 String fname= txtFname1.getText();
                 String lname= txtLName1.getText();
                 String tp=txtTp1.getText();
                 String address=txtAddress1.getText();
                 String mail=txtMail1.getText();
                 String dev=txtDev1.getText();
                 String sqlUpdate="update employees set Fname='"+fname+"',Lname='"+lname+"',Mobile='"+tp+"',Email='"+mail+"',address='"+address+"',devision='"+dev+"' where Nic='"+nic+"'";
              st.executeUpdate(sqlUpdate);
              populate();
              
   }
   @FXML
   void AddShift(ActionEvent event) throws SQLException {
   Connection conn=con.connect();
  try{
      
      int id=empNICShift.getSelectionModel().getSelectedItem();
  
  String name=empNameShift.getText();
  String branch=empBranch.getSelectionModel().getSelectedItem().toString();
  String date=empShiftDate.getValue().toString();
  String sqlShift="insert into employeeShift(nic,name,branch,date)values("+id+",'"+name+"','"+branch+"','"+date+"')";
  Statement st=conn.createStatement();
  st.executeUpdate(sqlShift);
  String sqlDeleteEMpl="delete from employees where Nic='"+txtNic1.getText()+"'";
  Statement st1=conn.createStatement();
  st1.executeUpdate(sqlDeleteEMpl);
  
  }catch(SQLException ex){
      System.err.println(ex);
  
  } 
   
   
   
   }
   @FXML
   void Clear(ActionEvent event){
   empNameShift.setText("");
   empBranch.valueProperty().set(null);
   empNICShift.valueProperty().set(null);
   empShiftDate.valueProperty().set(null);
   
   }
    @FXML
    void Add(ActionEvent event) throws SQLException {
   Connection conn=con.connect();
    String fName=txtFname.getText();
   String lName=txtLName.getText();
   String address=txtAddress.getText();
   String devision=txtDev.getText();
   String tpno=txtTp.getText();
   String email=txtMail.getText();
   String salary=txtSal.getText();
   
   int ss=Integer.parseInt(txtNic.getText());
   float sal=Float.parseFloat(salary);
   Statement st=conn.createStatement();
   
   String sql="insert into employees(Nic,Fname,Lname,Mobile,Email,address,"
           + "devision,salary)values('"+ss+"','"+fName+"','"+lName+"','"+tpno+"','"+email+"','"+address+"','"+devision+"',"+sal+")";
   st.executeUpdate(sql);
   System.out.println("data Addedd Sucessfully");
    AddEmpIdtoLabel();
    }

    @FXML
    void Exit(ActionEvent event) {

    }

    @FXML
    void populate() throws SQLException {
        String sql="select * from employees";
         try{Connection conn=con.connect();
         data=FXCollections.observableArrayList();
         ResultSet rs=conn.createStatement().executeQuery(sql);
         while(rs.next()){
         data.add(new EmployeeDetail(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
         
         }
         }
         catch(Exception ex){
             System.out.print(ex);
         }
         columnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
         columFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
         columLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
         columMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
         columEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         columAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
         columDevision.setCellValueFactory(new PropertyValueFactory<>("devision"));
        tableEmployee.setItems(null);
         tableEmployee.setItems(data);
         
    }
    
}
