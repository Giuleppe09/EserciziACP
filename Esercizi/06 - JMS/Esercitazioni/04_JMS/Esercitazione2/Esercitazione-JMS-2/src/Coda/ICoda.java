package Coda;

public interface ICoda {

    public void inserisci(String command);
    public String preleva();
    public boolean empty();
    public boolean full();
    public int getSize();

    
}