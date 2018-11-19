
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class InvoiceMainController implements Initializable {
   Statement st=null;
   PreparedStatement pst=null;
   DbConnection conn = new DbConnection();
   Connection con;
    int ksd;
    int resqty;
    String inrenname;
      @FXML
    private Label lbltot;

       @FXML
    private JFXTextField txtqty;
 
        @FXML
    private TableView<Datadetails> table;
       
         @FXML
    private TableColumn<Datadetails, Integer> uprice;
       
         @FXML
    private TableColumn<Datadetails, Integer> qty;
        
         @FXML
    private TableColumn<Datadetails, String> name;
         
           @FXML
    private TableColumn<Datadetails, Integer> ammount;
    private ObservableList<Datadetails> data;
    public InvoiceMainController() {
        
        this.table = new TableView<>();
    }
    
      @FXML
    private JFXTextField txtmobile;
      
       @FXML
    private Label lblinvoiceno;
       @FXML
    private JFXButton btnNotify;
     
          private ObservableList<Integer> option=FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Integer> cmbItemCode=new JFXComboBox(option);
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
    void addpay(ActionEvent event) {
    
      String url="jdbc:mysql://localhost:3306/pizza";
       String uname="root";
       String pass="";
       Connection conn;
       int count=0;
       try
       {
              conn=DriverManager.getConnection(url,uname,pass);
              Statement st=conn.createStatement();
              String sql ="select count(Member_Id) from members where Member_Id="+txtmobile.getText()+"";
              ResultSet rss=st.executeQuery(sql);
             while(rss.next())
             {
                 count=rss.getInt(1);
                 System.out.println(count);
                 if(count==0)
                 {
                     int num=Integer.parseInt(txtmobile.getText());
                     FXMLLoader Loaders=new FXMLLoader();
                 Loaders.setLocation(getClass().getResource("Reg.fxml"));
                 try {
                     Loaders.load();
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                
                 RegController dis = Loaders.getController();
                 dis.settexte(num);
                 
              Parent pp = Loaders.getRoot();
              Stage stages=new Stage();
              stages.setScene(new Scene(pp));
              stages.showAndWait();
                     
                 }
                 else
                 {
                    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
                    Date date = new Date();
                     Statement st1=conn.createStatement();
                     int total=Integer.parseInt(lbltot.getText());
                     int discount=0;
                     if(total>1000){
                     discount=20;
                     
                     }else if(total>5000){
                     discount=100;
                     
                     }else if(total>10000){
                     discount=500;
                     
                     }
                     total=total-discount;
                     
                    String sqlpay="insert into invoice_summary values("+lblinvoiceno.getText()+","+txtmobile.getText()+","+lbltot.getText()+","+discount+","+total+",'"+dateFormat.format(date)+"')";
                    st1.executeUpdate(sqlpay);
                    
                    // manage stores
                   
                    int qtys;
                    String name;
                    int unit;
                    int ammount;
                    for(int i=0;i<data.size();i++){
                    Statement st3=conn.createStatement();
                    qtys=data.get(i).getqty();
                    name=data.get(i).getname();
                    unit=data.get(i).getuprice();
                    ammount=data.get(i).getammount();
                    String insertsql="insert into invoice_details values('"+name+"',"+lblinvoiceno.getText()+","+qtys+","+unit+","+ammount+")";
                     Statement st10=conn.createStatement();
                     st10.executeUpdate(insertsql);
                     String updateItem="update item set qty=qty-"+qtys+" where Name='"+name+"'";
                    Statement st20=conn.createStatement();
                    st20.executeUpdate(updateItem);
                    }
                    
                    
                 }
                  int payammount=Integer.parseInt(lbltot.getText());
                 int mnum=Integer.parseInt(txtmobile.getText());
                 int invoice=Integer.parseInt(lblinvoiceno.getText());
                 FXMLLoader Loader=new FXMLLoader();
                 Loader.setLocation(getClass().getResource("Payment.fxml"));
                 try {
                     Loader.load();
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                
                 PaymentController dissplay = Loader.getController();
                 dissplay.setText(mnum, payammount,invoice);
                 
              Parent p = Loader.getRoot();
              Stage stage=new Stage();
             stage.setScene(new Scene(p));
              stage.showAndWait();
             }
            
               
       }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
    }
    
   

    @FXML
    void click(ActionEvent event) {
           int itemcodes=(Integer)cmbItemCode.getSelectionModel().getSelectedItem();
           int qty=Integer.parseInt(txtqty.getText());
           int to=Integer.parseInt(lbltot.getText());
                String url="jdbc:mysql://localhost:3306/pizza";
                String uname="root";
                String pass="";
                Connection conn;
      
       try
       {
             conn=DriverManager.getConnection(url,uname,pass);
              Statement st5=conn.createStatement();
              String sql ="select * from item where item_code='"+itemcodes+"'";
              ResultSet rs=st5.executeQuery(sql);
                while(rs.next())
                {
                 
                    int price=rs.getInt("Sell_price");
                    String name=rs.getString("name");
                     int tot=price*qty;
                     
                    //bltot.setText(Integer.toString(tot));
                    txtqty.setText("");
                   
                     data.add(new Datadetails(name,price,qty,tot));
                  lbltot.setText(Integer.toString(to+tot));
                     
                }
         }
       catch(SQLException ex)
       {
           System.out.println(ex);
       }
        
    }
         public void GetNotification() throws SQLException{
              String sql="select * from item";
              ResultSet rs=conn.connect().createStatement().executeQuery(sql);
               int count=0;
                while(rs.next()){

                        int qty=rs.getInt("qty");
                        int Reo_level=rs.getInt("ReoLevel");

                        if(qty<=Reo_level){

                        count++;

                }
                if(count==0){
                    btnNotify.setText("!");
                }else{
                    btnNotify.setText(Integer.toString(count));
                }


                }
}
         //Select Last(max) invoice number For Invoice Bill
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
           String mqxsql="select max(Invoice_no) from invoice_summary;";
            ResultSet rst=st6.executeQuery(mqxsql);
             while(rst.next())
             {
               int ino=rst.getInt(1);
                 lblinvoiceno.setText(Integer.toString(ino+1));
                
             }
            
       }
       catch(Exception ex)
       {
           System.out.println(ex);
       }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddCombo();
       try {
           GetNotification();
       } catch (SQLException ex) {
           Logger.getLogger(InvoiceMainController.class.getName()).log(Level.SEVERE, null, ex);
       }
       try{
            
         data=FXCollections.observableArrayList();
         
         }
         catch(NumberFormatException ex){
             System.out.print(ex);
         }
         uprice.setCellValueFactory(new PropertyValueFactory<>("uprice"));
         qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
         name.setCellValueFactory(new PropertyValueFactory<>("name"));
         ammount.setCellValueFactory(new PropertyValueFactory<>("ammount"));
         System.out.print("added");
         table.setItems(data);
        
      maxnm();
    } 
    
    
}
