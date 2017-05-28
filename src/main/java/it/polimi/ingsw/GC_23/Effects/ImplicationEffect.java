package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect extends AbsEffect {
    private SingleCost[] requirements;
    private BenefitsEffect[] givings;
    private DiscountEffect discount;

    public ImplicationEffect(SingleCost[] requirements, BenefitsEffect[] givings) {
        this.requirements = requirements;
        this.givings = givings;
    }

    public void setRequirements(SingleCost[] requirements) {
        this.requirements = requirements;
    }

    public void setGivings(BenefitsEffect[] givings) {
        this.givings = givings;
    }

    public SingleCost[] getRequirements() {
        return requirements;
    }

    public BenefitsEffect[] getGivings() {
        return givings;
    }

    public ImplicationEffect chooseImplication(ImplicationEffect[] possibileImplications){
        //TODO: richiesta al giocatore della scelta
        return null;
    }

    @Override
    public void activeEffect(Player player) {

    }
}
