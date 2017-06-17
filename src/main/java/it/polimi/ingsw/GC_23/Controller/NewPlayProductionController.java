package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayProductionController implements Controller {

    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    public NewPlayProductionController(FamilyMember familyMember, ProductionSpace productionSpace) throws IOException {
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;

        //TODO mettere nella newPlayProductionEffect
        if (isLegal()) {
            makeAction();
            familyMember.getPlayer().getUserHandler().messageToUser("Effect new play production done");
        } else {
            familyMember.getPlayer().getUserHandler().messageToUser("Impossible to do new play production");
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
    public void makeAction() throws IOException {
        familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(familyMember.getPlayer());
        //TODO

    }
}
