package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

import java.io.IOException;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayProductionController implements Controller {

    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    public NewPlayProductionController(FamilyMember familyMember, ProductionSpace productionSpace) throws IOException {
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;

        if (isLegal()) {
            makeAction();
            if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("Effect new play production done");
            }
        } else {
            if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("Impossible to do new play production");
            }
        }
    }

    @Override
    public boolean isLegal() {
        boolean legal = true;

        if(productionSpace.checkValue(familyMember) && !productionSpace.checkFamiliar(familyMember)) {
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
