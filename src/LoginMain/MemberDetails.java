/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author shashilaheshan
 */
public class MemberDetails {
   private  final IntegerProperty  memId;
    private  final StringProperty fname;
    private  final StringProperty lname;
    private  final StringProperty address;
    private final StringProperty date;
    public MemberDetails(int nic,String fname,String lname,String address,String dates)
    {
    this.memId=new SimpleIntegerProperty(nic);
    this.fname=new SimpleStringProperty(fname);
    this.lname=new SimpleStringProperty(lname);
    this.address=new SimpleStringProperty(address);
    this.date=new SimpleStringProperty(dates);
    
    
    }

    public int getMemID()
    {
        return memId.get();
    }
    
    public String getFname(){
    
    return fname.get();
    }
   
    public String getLname(){
    
    return lname.get();
    }
    
   
    public String getadress(){
    
    return address.get();
    }
    public String getDate(){
    
    return date.get();}
    
   public void setMemID(int value){
   memId.set(value);
   
   }
   public void setFname(String value){
   fname.set(value);
   
   }
   public void setLname(String value){
   lname.set(value);
   
   }
   
   public void setAddress(String value){
   address.set(value);
   
   }
   
   public void setDate(String value){
   date.set(value);
   
   }
   public StringProperty fnameProperty(){
   return fname;
   
   }
   public IntegerProperty MemIDProperty(){
   return memId;
   
   }
   public StringProperty lnameProperty(){
   return lname;
   
   }
  
   public StringProperty addressProperty(){
   return address;
   
   }
   public StringProperty dateProperty(){
       
    return  date;
       
   }
   
     
}
