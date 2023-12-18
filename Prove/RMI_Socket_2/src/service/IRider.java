package service;

import java.io.Serializable;

public interface IRider extends Serializable {
    public void notifyOrder(int id, String address);
}
