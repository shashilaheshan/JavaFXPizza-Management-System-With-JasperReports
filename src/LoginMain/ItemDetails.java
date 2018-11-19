
package LoginMain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class ItemDetails {
    public SimpleIntegerProperty itemCode=new SimpleIntegerProperty();
    public SimpleStringProperty itemName=new SimpleStringProperty();
    public SimpleIntegerProperty itemROLevel=new SimpleIntegerProperty();
    public SimpleIntegerProperty itemRprice=new SimpleIntegerProperty();
    public SimpleIntegerProperty itemSprice=new SimpleIntegerProperty();
     public SimpleIntegerProperty itemQty=new SimpleIntegerProperty();
    

    public Integer getItemCode(){
    return itemCode.get();
    
    }
     
      public Integer getRoLevel(){
    return itemROLevel.get();
    
    }
       public Integer getrPrice(){
    return itemRprice.get();
    
    }
        public Integer getsPricee(){
    return itemSprice.get();
    
    }public String getName(){
    return itemName.get();
    
    }
    public Integer getItemQty(){
    return itemQty.get();
    
    }

    public void setItemCode(SimpleIntegerProperty itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

    public void setItemROLevel(SimpleIntegerProperty itemROLevel) {
        this.itemROLevel = itemROLevel;
    }

    public void setItemRprice(SimpleIntegerProperty itemRprice) {
        this.itemRprice = itemRprice;
    }

    public void setItemSprice(SimpleIntegerProperty itemSprice) {
        this.itemSprice = itemSprice;
    }
    
    public void  setItemQty(SimpleIntegerProperty itemQty) {
        this.itemQty = itemQty;
    }
    
}
