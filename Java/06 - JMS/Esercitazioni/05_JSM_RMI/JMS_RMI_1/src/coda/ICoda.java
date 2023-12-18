package coda;

public interface ICoda {
    public void deposito(int val);
    public int preleva();
    public boolean empty();
    public boolean full();
    public int getSize();
}
