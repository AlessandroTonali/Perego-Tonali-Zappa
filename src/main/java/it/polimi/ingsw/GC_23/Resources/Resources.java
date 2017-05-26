package it.polimi.ingsw.GC_23.Resources;

import it.polimi.ingsw.GC_23.Player;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Resources {
    private int quantity;

    public Resources(int quantity) {
        this.quantity = quantity;
    }

    public synchronized void add(int value, Player p){
        this.quantity= this.quantity + value;
    }

    public synchronized void remove(int value, Player p){
        //i controlli per i valori non possibili sono nella classe Command
        this.quantity=this.quantity - value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(){return "" + quantity;
    }
}