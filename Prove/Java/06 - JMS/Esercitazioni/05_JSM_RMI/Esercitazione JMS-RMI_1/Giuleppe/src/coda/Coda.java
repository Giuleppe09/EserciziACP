package coda;

public class Coda implements ICoda{

    private int[] coda;
    private int size;
    private int head;
    private int tail;
    private int occ;

    public Coda(int size){
        this.size = size;
        this.coda = new int[this.size];
        this.head = this.tail = this.occ=0;
    }

    @Override
    public void deposito(int val) {
        coda[head%size]=val;
        head++;
        occ++;
        System.out.println("[Coda] E' stato inserito il valore: "+val+".");
    }

    @Override
    public int preleva() {
        int val = coda[tail%size];
        tail++;
        occ--;

        System.out.println("[Coda] E' stato prelevato il valore: "+val);

        return val;
    }

    @Override
    public boolean empty() {
       return occ == 0;
    }

    @Override
    public boolean full() {
        return occ == size;
    }

    @Override
    public int getSize() {
        return this.size;
    }
    
}
