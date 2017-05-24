package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class PlaceFamilyMember implements Controller {

    //TODO bisogna metterla abstract pero bisoga prima implementare le altre non lo faccio senno non builda

    private FamilyMember familyMember;
    private Player player;
    private ActionSpace actionSpace;

    private boolean checkBusy(){
        if(actionSpace.isBusy()){
            return true;
        }
        else {return false;}

    }

    @Override
    public boolean isLegal(){
        return true;
    }

    @Override
    public void makeAction() {
        actionSpace.setFamilyMember(familyMember);

        //TODO bisogna far pagare il giocatore e dargli il premio


    }
}
