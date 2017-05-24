package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ActionSpace {
    private FamilyMember familyMember;
    private Effect effect;
    private final int value;

    public ActionSpace(int value) {
        this.value = value;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public Effect getEffect() {
        return effect;
    }

    public boolean isBusy(){
        if (this.familyMember == null) {
            return false;
        }
        else {return true;}
    }

    public int getValue() {
        return value;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
