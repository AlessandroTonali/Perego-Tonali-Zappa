package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class HarvestController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private HarvestSpace harvestSpace;

    public HarvestController(FamilyMember familyMember, HarvestSpace harvestSpace) {
        this.familyMember = familyMember;
        this.harvestSpace = harvestSpace;
        if( hasSense()) {
            if(isLegal()){
                makeAction();
            }
        }
    }

    //Da controllare: no familiari dello stesso colore (si neutro)
    public boolean isLegal() {
        if (!(harvestSpace.checkBusy()) && (familyMember.getValue() >= 1)) {
            return true;
        }
        return false;
    }

    public boolean hasSense() {
        return this.harvestSpace.checkValue(familyMember);
    }

    //attiva anche gli effetti permanenti delle carte terriorio in possesso con valore <= a quello dell'azione
    @Override
    public void makeAction(){
        this.familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(this.familyMember.getPlayer());
        harvestSpace.setFamilyMember(familyMember);
    }

}
