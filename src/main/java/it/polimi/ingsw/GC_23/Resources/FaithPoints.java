package it.polimi.ingsw.GC_23.Resources;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class FaithPoints extends Resources {
    public FaithPoints(int quantity) {
        super(quantity);
    }

    public void reset(){
        this.setQuantity(0);
        //assegna i victory points in Gameplay
    }


}
