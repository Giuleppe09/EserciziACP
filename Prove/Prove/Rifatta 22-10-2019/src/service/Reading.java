package service;

import java.io.Serializable;

public class Reading implements Serializable{ 
    //In modo che sia possibile passarlo come parametro di un metodo RMI

    private String tipo;
    private int valore;

    public Reading(String type,int val){
        this.tipo = type;
        this.valore = val;
    }

    public String getTipo(){
        return this.tipo;
    }
    public int getValore(){
        return this.valore;
    }

    
}
