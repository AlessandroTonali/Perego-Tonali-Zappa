package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class TowerController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private Player player;
    private Tower tower;

    private boolean checkBusy(){
        //TODO
        return false;
    }

    private boolean checkFamiliar(TowerSpace space){
    //TODO
        return true;

    }

    private boolean checkAffordable(TowerSpace space){
        //TODO da definire diversamente per le carte territorio
        return true;
    }

    public boolean isLegal(){
        return true;
    }

    //numero carte
    private boolean isTakeable(){
        //TODO
        return true;
    }
}
