package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
* Created by jesss on 23/05/17.
*/
public class IncreaseFamilyValue implements Controller {

    int quantity;
    FamilyMember familyMember;

    public IncreaseFamilyValue(int quantity, FamilyMember familyMember) {
        this.quantity = quantity;
        this.familyMember = familyMember;
        if (!this.isLegal()) {
            System.out.println("error");
        } else {
            this.makeAction();
            System.out.println("succes");
        }

    }



    public boolean isLegal() {
        ResourcesSet playerRes = this.familyMember.getPlayer().getResources();

        if (playerRes.checkAffordable(new ResourcesSet(0, 0, 0, quantity, 0
                , 0, 0))) {
            return true;

        } else {
            return false;
        }

    }

    @Override
    public void makeAction() {
        familyMember.increaseFamilyValue(quantity);
    }

}