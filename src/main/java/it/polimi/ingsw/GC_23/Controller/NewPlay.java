package it.polimi.ingsw.GC_23.Controller;

/**
 * Created by jesss on 26/05/17.
 */
public class NewPlay implements Controller {

    //non controlla se c'è un altro familiare dello stesso colore ma fa checkOtherFamiliar
    @Override
    public boolean isLegal() {
        return false;
    }

    //non setta il familyMember
    @Override
    public void makeAction() {

    }
}
