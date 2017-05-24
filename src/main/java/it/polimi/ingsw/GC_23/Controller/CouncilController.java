package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.CouncilSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class CouncilController extends PlaceFamilyMember {
    CouncilSpace councilSpace;
    FamilyMember familyMember;
    Player player;

    public boolean isLegal(){
        if(familyMember.getValue()>=1 && !councilSpace.isPresent(player.getPlayerColor())){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean checkFamiliar(CouncilSpace space){
        //TODO
        return true;
    }
}
