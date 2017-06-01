package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class MarketSpace extends ActionSpace {
    public MarketSpace(){
        super(1);

    }

    public boolean checkValue(FamilyMember familyMember) {
        return familyMember.getValue() >= 1;
    }



}
