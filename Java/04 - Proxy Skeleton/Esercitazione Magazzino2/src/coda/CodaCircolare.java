package coda;

public class CodaCircolare implements ICoda {

    protected int[] coda;
    private int size;
    private int head;
    private int tail;
    private int occ;

    public CodaCircolare(int s){
        this.size = s;
        head=tail=occ=0;
        this.coda=new int[size];
    }

    public void inserisci(int id){
        coda[head%size] = id;
        head++; 
        occ++;

    }
    public int preleva(){
        int val = coda[tail%size];
        tail++;
        occ--;
        return val;
    }

    public int getSize(){
        return size;
    }
    public boolean empty(){
        return occ==0;
    }
    public boolean full(){
        return occ==size;
    }



}

