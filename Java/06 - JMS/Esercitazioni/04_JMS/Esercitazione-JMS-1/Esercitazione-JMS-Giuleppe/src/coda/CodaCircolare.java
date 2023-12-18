package coda;

public class CodaCircolare implements ICoda{
    private int[] codaCircolare=null;
    private int size;
    private int head;
    private int tail;
    private int occ;


    public CodaCircolare(int s){
        this.size = s;
        codaCircolare = new int[s];
        head=tail=occ=0;
    }

    public void inserisci(int id_articolo){
        codaCircolare[head%size]=id_articolo;
        head++;
        occ++;
    }

    public int preleva(){
        int val = codaCircolare[tail%size];
        tail++;
        occ--;
        return val;
    }

    public boolean empty(){
        return occ==0;
    }

    public boolean full(){
        return occ==size;
    }

    public int getSize(){
        return size;
    }



}
