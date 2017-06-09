package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ActionSpace {
    private FamilyMember familyMember;
    private final int value;

    public ActionSpace (int value) {
        this.value = value;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }


    public boolean checkBusy(){
        if (this.familyMember == null) {
            return false;
        }
        else {return true;}
    }

    public boolean checkValue(){
        if (this.familyMember.getValue()>= value) {
            return true;
        }
        return false;
    }


    public int getValue() {
        return value;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
        Player player = familyMember.getPlayer();
        FamilyMember[] members = this.familyMember.getPlayer().getFamilyMembers();
        int i = 0;
        for(FamilyMember m : members) {
            if(m != null && m == familyMember) {
                members[i] = null;
                break;
            }
            i++;
        }
        player.setFamilyMembers(members);

    }

    public void resetFamilyMember(){
        this.familyMember = new FamilyMember(this);
    }
}
