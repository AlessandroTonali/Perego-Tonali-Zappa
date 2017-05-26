package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

/**
* Created by jesss on 23/05/17.
*/
/**public class IncreaseFamilyValue implements Controller {

    public boolean IncreaseFamilyValue(int quantity, FamilyMember familyMember) {
        if(!isLegal())
        this.familyMember = familyMember;
        if(isLegal()) {
               makeAction();
              return true;
                    }
       else {
                      return false;
                   }
    }

@Override
    public boolean isLegal(int quantity, FamilyMember familyMember) {
                if (player.getResources().getServants().getQuantity() > quantity) { return true; }
                else {return false;}
            }

@Override
    public void makeAction() {
                int actualQuantity = player.getResources().getServants().getQuantity();
                player.getResources().getServants().setQuantity(actualQuantity - quantity);
                int actualValue = familyMember.getValue();
                familyMember.setValue(actualValue + quantity);

                          }
 }