
package LoginMain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class DDatadetailGRN{

    private  final SimpleIntegerProperty  name;
     private  final SimpleIntegerProperty  qty;
      
    
    
    public DDatadetailGRN(int name,int qty)
    {
        this.qty=new SimpleIntegerProperty(qty);
        this.name=new SimpleIntegerProperty(name);
    }

    public int getqty()
    {
        return qty.get();
    }
    
    public int getname()
    {
        return name.get();
    }
    
    
    public void setqty(int value)
    {
        qty.set(value);
    }
   
    public void setname(int value)
    {
        name.set(value);
    }
    
    
     public IntegerProperty qtyProperty(){
     return qty;
   }
     
      public IntegerProperty nameProperty(){
       return name;
   }
}
