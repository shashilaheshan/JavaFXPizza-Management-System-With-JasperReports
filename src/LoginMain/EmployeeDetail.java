
package LoginMain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Heshan
 */
public class EmployeeDetail {
    private  final IntegerProperty  nic;
    private  final StringProperty fname;
    private  final StringProperty lname;
    private  final StringProperty mobile;
    private  final StringProperty email;
    private  final StringProperty address;
    private  final StringProperty devision;
    public EmployeeDetail(int nic,String fname,String lname,String mobile,String email,String address,String devision)
    {
    this.nic=new SimpleIntegerProperty(nic);
    this.fname=new SimpleStringProperty(fname);
    this.lname=new SimpleStringProperty(lname);;
    this.mobile=new SimpleStringProperty(mobile);;
    this.email=new SimpleStringProperty(email);;
    this.address=new SimpleStringProperty(address);;
    this.devision=new SimpleStringProperty(devision);;
    
    }

    public int getNic()
    {
        return nic.get();
    }
    
    public String getFname(){
    
    return fname.get();}
   
    public String getLname(){
    
    return lname.get();}
    public String getMobile(){
    
    return mobile.get();}
    public String getEmail(){
    
    return email.get();}
    public String getadress(){
    
    return address.get();}
    public String getdevision(){
    
    return devision.get();}
    
   public void setNic(int value){
   nic.set(value);
   
   }
   public void setFname(String value){
   fname.set(value);
   
   }
   public void setLname(String value){
   lname.set(value);
   
   }
   public void setMobile(String value){
   mobile.set(value);
   
   }
   public void setAddress(String value){
   address.set(value);
   
   }
   public void setEmail(String value){
   email.set(value);
   
   }
   public void setDevision(String value){
   devision.set(value);
   
   }
   public StringProperty fnameProperty(){
   return fname;
   
   }
   public IntegerProperty nicProperty(){
   return nic;
   
   }
   public StringProperty lnameProperty(){
   return lname;
   
   }
   public StringProperty mobileProperty(){
   return mobile;
   
   }
   public StringProperty emailProperty(){
   return email;
   
   }
   public StringProperty addressProperty(){
   return address;
   
   }
   public StringProperty devisionProperty(){
   return devision;
   
   }
   
    
}
