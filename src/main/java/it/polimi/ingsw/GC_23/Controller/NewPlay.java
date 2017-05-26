package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 26/05/17.
 */
public class NewPlay implements Controller {
    Player player;
    FamilyMember familyMember;
    TowerSpace towerSpace;

    //non controlla se c'è un altro familiare dello stesso colore ma fa checkOtherFamiliar
    @Override
    public boolean isLegal() {
        //if((towerSpace.checkBusy)&&(towerSpace.checkValue){
        //}
        return false;
    }

    //non setta il familyMember
    @Override
    public void makeAction() {

    }
}
