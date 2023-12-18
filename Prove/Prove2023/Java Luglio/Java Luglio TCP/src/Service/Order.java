package Service;

import java.io.Serializable;

public class Order implements Serializable{
    
    private int idOrder;
    private int location;
    private String add;

    public Order(int id,int loc,String address){
        idOrder=id;
        location = loc;
        add = address;
    }
    
    public int getId(){
        return idOrder;
    }
    public String getAddress(){
        return add;
    }
    public int getLocation(){
        return location;
    }
    
}
