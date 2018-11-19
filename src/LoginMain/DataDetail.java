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
 * @author Heshan
 */
public class DataDetail {
    private  final SimpleStringProperty  nic;
    private  final SimpleIntegerProperty  id;
    
    public DataDetail(int id,String nic)
    {
    this.nic=new SimpleStringProperty(nic);
    this.id=new SimpleIntegerProperty(id);
    }

    public String getNic()
    {
        return nic.get();
    }
    public int getId()
    {
        return id.get();
    }
    
    
   public void setNic(String value){
   nic.set(value);
   
   }
   public void setID(int value){
   id.set(value);
   
   }
  
   
  
   public StringProperty nicProperty(){
   return nic;
   }
   public IntegerProperty idProperty(){
   return id;
   }
}
   
  
   
    

