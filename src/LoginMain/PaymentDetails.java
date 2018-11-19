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
public class PaymentDetails {
    
    
      private  final IntegerProperty  payId;
    private  final IntegerProperty mem_Id;
    private  final IntegerProperty amount;
    private  final StringProperty date;
    
    public PaymentDetails(int pid,int mid,int amt,String dat)
    {
    this.payId=new SimpleIntegerProperty(pid);
    this.mem_Id=new SimpleIntegerProperty(mid);
    this.amount=new SimpleIntegerProperty(amt);
    this.date=new SimpleStringProperty(dat);
    
    
    
    }

    public int getPayId()
    {
        return payId.get();
    }
    
     public int getMem_Id()
    {
        return mem_Id.get();
    }
    
     public int getAmount()
    {
        return amount.get();
    }
    
    
    
   
    public String getDate(){
    
    return date.get();
    }
   
    
   public void setPId(int value){
   payId.set(value);
   
   }
   public void setMemID(int value){
   mem_Id.set(value);
   
   }
   public void setAmount(int value){
   amount.set(value);
   
   }
   
   public void setDate(String value){
   date.set(value);
   
   }
   
  
   public IntegerProperty pidProperty(){
   return payId;
   
   }
   public IntegerProperty MemIDProperty(){
   return mem_Id;
   
   }
   public IntegerProperty amouProperty(){
   return amount;
   
   }
  
   public StringProperty dateProperty(){
   return date;
   
   }
  
   
}
