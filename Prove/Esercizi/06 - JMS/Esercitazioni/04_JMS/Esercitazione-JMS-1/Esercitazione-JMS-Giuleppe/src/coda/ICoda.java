package coda;

public interface ICoda{
    public void inserisci(int id_articolo);
    public int preleva();

    public int getSize();
    public boolean empty();
    public boolean full();
}