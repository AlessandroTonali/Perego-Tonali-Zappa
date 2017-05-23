package it.polimi.ingsw.GC_23.Controller;

/**
 * Created by jesss on 23/05/17.
 */
public class DiscardLeaderCard implements Controller {

    private boolean isActivable(){
        //TODO
        return true;
    }

    @Override
    public boolean isLegal() {
        return false;
    }

    @Override
    public void makeAction() {

    }
}
