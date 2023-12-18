package coda;

public interface ICoda{
    
    public void inserisci(String cmd);
    public String preleva();

    public boolean empty();
    public boolean full();
    public int getSize();

    
}
