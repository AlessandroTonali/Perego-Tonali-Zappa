package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;

/**
 * Created by valeriopozzoni on 23/05/17.
 */
public class HarvestController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private HarvestSpace harvestSpace;
    public HarvestController(FamilyMember familyMember, HarvestSpace harvestSpace) {
        this.familyMember = familyMember;
        this.harvestSpace = harvestSpace;
        if(hasSense()){
            makeAction();
        }
    }
    public boolean isLegal(){
        return true;
    }

    public boolean hasSense() {
        return this.harvestSpace.checkValue(familyMember);
    }

}
