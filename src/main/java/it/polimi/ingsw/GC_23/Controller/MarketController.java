package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.MarketSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class MarketController extends PlaceFamilyMember {

    private FamilyMember familyMember;
    private Player player;
    private MarketSpace marketSpace;

    private boolean checkBusy(){
        if(marketSpace.isBusy())
            return true;
        else
            return false;
    }

    //da controllare: is busy, (può contenere solo un familiare), familiare con valore >=1
    public boolean isLegal(){
        if(!(this.checkBusy())&&(familyMember.getValue()>=1))
            return true;
        else
            return false;
    }

    //ottieni bonus immediato
    @Override
    public void makeAction(){
        marketSpace.setFamilyMember(familyMember);
        /*
        TODO: richiama ActiveEffectController usando player.getBonusTile().getBenefitsEffect()
         */

    }
}
