package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;

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
    }

    public void resetFamilyMember(){
        this.familyMember = new FamilyMember(this);
    }
}
