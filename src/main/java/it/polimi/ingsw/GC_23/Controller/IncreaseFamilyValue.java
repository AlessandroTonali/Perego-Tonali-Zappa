package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

/**
 * Created by jesss on 23/05/17.
 */
public class IncreaseFamilyValue implements Controller {



    public IncreaseFamilyValue(int quantity, Player player, FamilyMember familyMember) {
       if (isLegal()) { makeAction(quantity);}


    }

    @Override


    public boolean isLegal( player, quantity) {
        if (player.getResources().getServants().getQuantity() > quantity) { return true; }
        return false;
    }

    @Override
    public void makeAction(int quantity, Player player, FamilyMember familyMember) {

        //devo fare i set


    }
}
