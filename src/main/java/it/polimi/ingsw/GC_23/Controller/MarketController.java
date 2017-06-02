package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class MarketController extends PlaceFamilyMember {

    private FamilyMember familyMember;
    private MarketSpace marketSpace;

    public MarketController(FamilyMember familyMember, MarketSpace marketSpace) {
        this.familyMember = familyMember;
        this.marketSpace = marketSpace;

        if (isLegal()) {
            makeAction();
            System.out.println("succes");
        } else {
            System.out.println("error");
        }
    }

    public boolean isLegal(){
        if(!(this.marketSpace.checkBusy())&&(marketSpace.checkValue(familyMember))) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void makeAction(){
        //TODO marketSpace.getEffect().getBenefitsEffect().activeEffect(familyMember.getPlayer());
        marketSpace.setFamilyMember(familyMember);
    }
}
