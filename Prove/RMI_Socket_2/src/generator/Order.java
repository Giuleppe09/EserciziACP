package generator;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int location;
    private String address;

    public Order(int id, int location, String address){
        this.id = id;
        this.location = location;
        this.address = address;
    }


    public int getId(){
        return id;
    }

    public int getLocation(){
        return location;
    }

    public String getAddress(){
        return address;
    }
    
}
