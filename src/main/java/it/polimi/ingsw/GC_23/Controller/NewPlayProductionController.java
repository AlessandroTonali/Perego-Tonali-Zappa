package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

import java.util.ArrayList;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayProductionController implements Controller {

    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    public NewPlayProductionController(FamilyMember familyMember, ProductionSpace productionSpace) {
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;

        //TODO mettere nella newPlayProductionEffect
        if (isLegal()) {
            makeAction();
            System.out.println("Effect new play production done");
        } else {
            System.out.println("Impossible to do new play production");
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;

        legal = legal && productionSpace.checkValue(familyMember);

        legal = legal && productionSpace.checkFamiliar(familyMember);

        return legal;
    }

    @Override
    public void makeAction() {
        familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(familyMember.getPlayer());
        ArrayList<TerritoryCard> territoryCards = familyMember.getPlayer().getCardOfPlayer().getTerritoryCards();
        for (int i = 0; i < territoryCards.size(); i++) {
            if (territoryCards.get(i).getProductionValue() >= familyMember.getValue()) {
                ArrayList<AbsEffect> permanentEffects = territoryCards.get(i).getPermanentEffect();
                for (int j = 0; j < permanentEffects.size(); j++) {
                    permanentEffects.get(j).activeEffect(familyMember.getPlayer());
                }
            }
        }

    }
}
