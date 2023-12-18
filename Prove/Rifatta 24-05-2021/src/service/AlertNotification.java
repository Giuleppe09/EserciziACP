package service;

import java.io.Serializable;

public class AlertNotification implements Serializable{
    
    private int componentID;
    private int critically;

    public AlertNotification(int id,int crit){
        this.componentID = id;
        this.critically = crit;
    }

    public int getComponentID(){
        return componentID;
    }
    public int getCritically(){
        return critically;
    }
}
