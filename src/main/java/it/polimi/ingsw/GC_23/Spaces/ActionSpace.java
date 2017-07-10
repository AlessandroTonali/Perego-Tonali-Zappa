package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.rmi.RemoteException;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public abstract class ActionSpace {
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

    /**
     * remove the family member by the list of all family member's player
     * @param familyMember
     */
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

    /**
     * remove all family member present in the action space
     */
    public void resetFamilyMember(){
        this.familyMember = null;
    }

    /**
     * check permanent effects of the player before entry in a action and active these if is possible
     * @param familyMember
     * @throws RemoteException
     */
    public abstract void checkBeforeActivablePermanentEffect(FamilyMember familyMember) throws RemoteException;

    /**
     * check permanent effects of the player after entry in a action and active these if is possible
     * @param familyMember
     */
    public abstract void checkAfterActivablePermanentEffect(FamilyMember familyMember);
}
