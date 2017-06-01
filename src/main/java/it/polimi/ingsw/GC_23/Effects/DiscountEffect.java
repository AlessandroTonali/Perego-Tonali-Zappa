package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by jesss on 21/05/17.
 */
public class DiscountEffect extends AbsEffect{
    private SingleCost[] discount;

    public DiscountEffect(SingleCost[] discount) {
        this.discount = discount;
    }

    public void setDiscount(SingleCost[] discount) {
        this.discount = discount;
    }

    public SingleCost[] getDiscount() {
        return discount;
    }

    public SingleCost chooseDiscount(SingleCost[] possibileDiscounts){
        //TODO: interazione con il giocatore per la scelta
        return null;
    }


    public void activeEffect(Player player) {
    //TODO
    }
}
