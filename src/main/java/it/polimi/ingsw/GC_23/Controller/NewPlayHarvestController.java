package it.polimi.ingsw.GC_23.Controller;


import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;

import java.io.IOException;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayHarvestController implements Controller {

    private FamilyMember familyMember;
    private HarvestSpace harvestSpace;

    public NewPlayHarvestController(FamilyMember familyMember, HarvestSpace harvestSpace) throws IOException {
        this.familyMember = familyMember;
        this.harvestSpace = harvestSpace;

        if (isLegal()) {
            makeAction();
            familyMember.getPlayer().getUserHandler().messageToUser("Effect new play harvest done");
        } else {
            familyMember.getPlayer().getUserHandler().messageToUser("Impossible to do new play harvest");
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal;

        if (harvestSpace.checkValue(familyMember) && !harvestSpace.checkFamiliar(familyMember)) {
            legal = true;
        } else {
            legal = false;
        }

        return legal;
    }

    @Override
    public void makeAction() throws IOException {
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
            familyMember.getPlayer().getUserHandler().messageToUser("effect");
        }
        familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(familyMember.getPlayer());
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
            familyMember.getPlayer().getUserHandler().messageToUser("effectended");
        }
        //TODO

    }
}
