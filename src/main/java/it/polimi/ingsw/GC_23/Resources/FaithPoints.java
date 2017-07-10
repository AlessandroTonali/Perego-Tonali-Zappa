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
        //todo: nel gameplay il giocatore sceglie se resettare i punti fede o tenerli, se resetta deve dare i punti vittoria corrispondenti
    }


}
