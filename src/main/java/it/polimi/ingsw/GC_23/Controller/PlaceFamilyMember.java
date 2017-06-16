package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by jesss on 23/05/17.
 */
public abstract class PlaceFamilyMember implements Controller {


    private FamilyMember familyMember;
    private Player player;
    private ActionSpace actionSpace;

    private boolean checkBusy(){
        if(actionSpace.checkBusy()){
            return true;
        }
        else {return false;}

    }

    @Override
    public abstract boolean isLegal();

    @Override
    public abstract void makeAction() throws IOException;

}
