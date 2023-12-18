package service;

import java.io.Serializable;

public class Order implements Serializable {
    private int idOrdine;
    private int location;
    private String address = null;

    public Order(int id,int l,String ad){
        this.idOrdine = id;
        this.location = l;
        this.address=ad;
    }

    public int getIdOrder(){
        return idOrdine;
    }
    public int getLocation(){
        return location;
    }
    public String getAddress(){
        return address;
    }
    
}
