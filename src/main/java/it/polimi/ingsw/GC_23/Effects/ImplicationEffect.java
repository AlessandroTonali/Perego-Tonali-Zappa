package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect {
    private SingleCost[] requirements;
    private BenefitsEffect[] givings;

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
}
