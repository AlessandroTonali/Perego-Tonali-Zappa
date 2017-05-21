package it.polimi.ingsw.GC_23.ResourcesPackage;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public abstract class Resources {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public abstract void add();
    public abstract void remove();
}
