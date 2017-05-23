package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by jesss on 21/05/17.
 */
public class DiscountEffect {
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
}
