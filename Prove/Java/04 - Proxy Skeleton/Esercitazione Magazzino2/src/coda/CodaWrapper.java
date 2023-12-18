package coda;

public abstract class CodaWrapper implements ICoda {

    protected ICoda coda;

    public CodaWrapper(ICoda c){
        this.coda = c;
    }

    @Override
    public int getSize() {
        return coda.getSize();
    }

    @Override
    public boolean empty() {
        return coda.empty();
    }

    @Override
    public boolean full() {
        return coda.full();
    }
    
}
