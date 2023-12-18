package coda;

public class CodaCircolare implements ICoda{
    private String[] codaCircolare;

    private int size;
    
    private int head;
    private int tail;
    
    private int occ;


    public CodaCircolare(int s){
        this.size = s;
        codaCircolare = new String[size];
        occ=head=tail=0;
    }


    @Override
    public void inserisci(String cmd) {
        codaCircolare[head%size]=cmd;
        System.out.println("comando inserito nella coda correttamente");
        occ++;
        head++;
    }


    @Override
    public String preleva() {
        String comando = codaCircolare[tail%size];
        System.out.println("comando prelevato dalla coda correttamente");
        occ--;
        tail++;
        return comando;
    }


    @Override
    public boolean empty() {
        return occ==0;
    }


    @Override
    public boolean full() {
        return occ==size;
    }


    @Override
    public int getSize() {
        return size;
    }   

}
