package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ActionSpace;

import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class MarketSpace extends ActionSpace {
    private  final AbsEffect effect;

    public MarketSpace(AbsEffect effect){
        super(1);
        this.effect = effect;



    }

    public AbsEffect getEffect() {
        return effect;
    }

    public boolean checkValue(FamilyMember familyMember) {
        return familyMember.getValue() >= 1;
    }

    public String toString() {
        if (super.getFamilyMember() == null){
            return "family member: empty   effect: " + effect.toString();
        }
        return "family member: " + super.getFamilyMember().toString() + "   effect: " + effect.toString();
    }



}
