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
public class InvoiceDetailsSummery {
     private  final IntegerProperty  invId;
    private  final IntegerProperty memId;
    private  final IntegerProperty amt;
    private  final IntegerProperty discount;
    private  final IntegerProperty finalamt;
    private  final StringProperty date;
    
    public InvoiceDetailsSummery(int invid,int mid,int amts,int disc,int finalamts,String dates)
    {
    this.invId=new SimpleIntegerProperty(invid);
    
    this.memId=new SimpleIntegerProperty(mid);
    this.amt=new SimpleIntegerProperty(amts);
    this.discount=new SimpleIntegerProperty(disc);
    this.finalamt=new SimpleIntegerProperty(finalamts);
    this.date=new SimpleStringProperty(dates);
    
    }
public int getInVId()
    {
        return invId.get();
    }
    
    public int getMemId(){
    
    return memId.get();}
   
    public int getAmount(){
    
    return amt.get();}
    public int getDiscount(){
    
    return discount.get();}
    public int getFinal(){
    
    return finalamt.get();}
    public String getDate(){
    
    return date.get();}
    
    
   public void setInvId(int value){
   invId.set(value);
   
   }
   public void setMemId(int value){
   memId.set(value);
   
   }
   public void setAmount(int value){
   amt.set(value);
   
   }
   public void setDis(int value){
   discount.set(value);
   
   }
   public void setFinalAmt(int value){
   finalamt.set(value);
   
   }
   public void setDate(String value){
   date.set(value);
   
   }
   
    public IntegerProperty getInvId() {
        return invId;
    }

    public IntegerProperty getMemIds() {
        return memId;
    }

    public IntegerProperty getAmt() {
        return amt;
    }

    public IntegerProperty getDiscounts() {
        return discount;
    }

    public IntegerProperty getFinalamt() {
        return finalamt;
    }

    public StringProperty getDates() {
        return date;
    }

   
}
