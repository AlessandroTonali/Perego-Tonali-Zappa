package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Effects.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.CouncilSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class CouncilController extends PlaceFamilyMember {
    CouncilSpace councilSpace;
    FamilyMember familyMember;

    public CouncilController(CouncilSpace councilSpace, FamilyMember familyMember) {
        this.councilSpace = councilSpace;
        this.familyMember = familyMember;
        if(isLegal()){
            makeAction();
        }
    }

    public boolean isLegal(){
        if(familyMember.getValue()>=1 && !councilSpace.isPresent(familyMember.getPlayer().getPlayerColor())){
            return true;
        }
        else {
            return false;
        }
    }

    public void makeAction(){
        BenefitsEffect benefits = new BenefitsEffect(new ResourcesSet(0,1,0,0,0,0,0));
        benefits.activeEffect(familyMember.getPlayer());
        /*scelta della pergamena e assegnamento effetto
        CouncilPrivilegeEffect privilege = new CouncilPrivilegeEffect( benefits, 1);*/
        councilSpace.setFamilyMember(familyMember);
    }

}
