package coda;

public interface ICoda {

    public void inserisci(int id);
    public int preleva();
    
    public int getSize();
    public boolean empty();
    public boolean full();

}