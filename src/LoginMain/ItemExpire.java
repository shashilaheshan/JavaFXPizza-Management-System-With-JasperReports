/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginMain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class ItemExpire{
    public SimpleIntegerProperty itemCode=new SimpleIntegerProperty();
   
    public SimpleIntegerProperty ex_id=new SimpleIntegerProperty();
     public SimpleIntegerProperty itemQty=new SimpleIntegerProperty();
     public SimpleStringProperty exDate=new SimpleStringProperty();

    public Integer getEx_id() {
        return ex_id.get();
    }

    public void setEx_id(SimpleIntegerProperty ex_id) {
        this.ex_id = ex_id;
    }
    

    public Integer getItemCodeEx(){
    return itemCode.get();
    
    }
     
     
        public String getExDate(){
    return exDate.get();
    
    }
    public Integer getItemQtyEx(){
    return itemQty.get();
    
    }

    public void setItemCodeEx(SimpleIntegerProperty itemCode) {
        this.itemCode = itemCode;
    }

   

    public void setExpireDate(SimpleStringProperty exdate) {
        this.exDate = exdate;
    }
    
    public void  setItemQtyEx(SimpleIntegerProperty itemQty) {
        this.itemQty = itemQty;
    }
    
}
