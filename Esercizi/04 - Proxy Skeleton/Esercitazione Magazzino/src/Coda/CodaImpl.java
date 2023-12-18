package Coda;
//Implementazione dei metodi senza considerare la sincronizzazione

import Service.ICoda;

public class CodaImpl implements ICoda{
    
    private int data[];
    private int head;
    private int tail;
    private int occ; //Posizioni occupate
    private int size;


    public CodaImpl(int s){ //Gli passo la dimensione.
        size=s;
        head=tail=occ=0;
        data = new int[size];
    }

    public boolean empty(){
        return occ==0;
    }

    public boolean full(){
        return occ==size;
    }

    public void inserisci(int id){

        data[tail%size]=id; //Inserimento in coda
        tail++;
        occ++;
        
    }

    public int preleva(){
        int id;

        id=data[head%size];
        head++;
        occ--;

        return id;
    
    }

}

