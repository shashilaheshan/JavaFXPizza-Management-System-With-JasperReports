
package LoginMain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Datadetails {

    private  final SimpleStringProperty  name;
    private  final SimpleIntegerProperty  uprice;
     private  final SimpleIntegerProperty  qty;
      private  final SimpleIntegerProperty  ammount;
    
    
    public Datadetails(String name, int uprice,int qty,int ammount)
    {
        this.ammount=new SimpleIntegerProperty(ammount);
        this.qty=new SimpleIntegerProperty(qty);
        this.uprice=new SimpleIntegerProperty(uprice);
        this.name=new SimpleStringProperty(name);
        
    }
    
    public int getammount()
    {
        return ammount.get();
    }
    public int getqty()
    {
        return qty.get();
    }
    public int getuprice()
    {
        return uprice.get();
    }
    public String getname()
    {
        return name.get();
    }
    
    public void setammount(int value)
    {
        ammount.set(value);
    }
    public void setqty(int value)
    {
        qty.set(value);
    }
    public void setuprice(int value)
    {
        uprice.set(value);
    }
    public void setname(String value)
    {
        name.set(value);
    }
    
    public IntegerProperty ammountProperty(){
   return ammount;
   }
     public IntegerProperty qtyProperty(){
   return qty;
   }
       public IntegerProperty upriceProperty(){
   return uprice;
   }
           public StringProperty nameProperty(){
   return name;
   }
}
