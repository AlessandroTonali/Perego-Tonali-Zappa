package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayHarvestController implements Controller {

    private FamilyMember familyMember;
    private HarvestSpace harvestSpace;

    public NewPlayHarvestController(FamilyMember familyMember, HarvestSpace harvestSpace) {
        this.familyMember = familyMember;
        this.harvestSpace = harvestSpace;

        //TODO mettere nella newPlayHarvestEffect
        if (isLegal()) {
            makeAction();
            System.out.println("Effect new play harvest done");
        } else {
            System.out.println("Impossible to do new play harvest");
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;

        legal = legal && harvestSpace.checkValue(familyMember);

        legal = legal && !harvestSpace.checkFamiliar(familyMember);

        return legal;
    }

    @Override
    public void makeAction() {
        familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(familyMember.getPlayer());
        ArrayList<BuildingCard> buildingCards = familyMember.getPlayer().getCardOfPlayer().getBuildingCards();
        for (int i = 0; i < buildingCards.size(); i++) {
            if (buildingCards.get(i).getHarvestValue() >= familyMember.getValue()) {
                ArrayList<AbsEffect> permanentEffects = buildingCards.get(i).getPermanentEffect();
                for (int j = 0; j < 0; j++) {
                    permanentEffects.get(j).activeEffect(familyMember.getPlayer());
                }
            }
        }

    }
}
