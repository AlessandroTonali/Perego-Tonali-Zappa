package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

/**
* Created by jesss on 23/05/17.
*/
public class IncreaseFamilyValue implements Controller {
    int quantity;
    Player player;
    FamilyMember familyMember;

    public boolean IncreaseFamilyValue(int quantity, Player player, FamilyMember familyMember) {
        this.quantity = quantity;
        this.player = player;
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
    public boolean isLegal() {
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