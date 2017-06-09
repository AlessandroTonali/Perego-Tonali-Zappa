package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Controller.HarvestController;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.SingleCost;

/**
 * Created by Alessandro on 06/06/2017.
 */
public class NewPlayHarvestEffect extends AbsEffect {

    private int diceValue;

    public NewPlayHarvestEffect(int diceValue) {
        this.diceValue = diceValue;
    }

    @Override
    public void activeEffect(Player player) {
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.NEUTRAL, diceValue);
        new HarvestController(familyMember, player.getView().getHarvestSpace());


    }

    @Override
    public int getTypeEffect() {
        return 0;
    }

    public int getDiceValue() {
        return diceValue;
    }
}
