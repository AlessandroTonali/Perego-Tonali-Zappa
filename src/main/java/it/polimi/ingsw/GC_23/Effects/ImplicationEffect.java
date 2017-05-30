package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by jesss on 21/05/17.
 */
public class ImplicationEffect extends AbsEffect {
    private ArrayList<SingleCost> requirements;
    private ArrayList<BenefitsEffect> givings;
    private DiscountEffect discount;

    public ImplicationEffect(ArrayList<SingleCost> requirements, ArrayList<BenefitsEffect> givings) {
        this.requirements = requirements;
        this.givings = givings;
    }

    public void setRequirements(ArrayList<SingleCost> requirements) {
        this.requirements = requirements;
    }

    public void setGivings(ArrayList<BenefitsEffect> givings) {
        this.givings = givings;
    }

    public ArrayList<SingleCost> getRequirements() {
        return requirements;
    }

    public ArrayList<BenefitsEffect> getGivings() {
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
