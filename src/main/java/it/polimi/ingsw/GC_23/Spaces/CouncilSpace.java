package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.CouncilPrivilegeEffect;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;

import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class CouncilSpace extends ActionSpace {
    private static int orderCounter;
    private ArrayList<FamilyMember> playerOrder;
    private final ArrayList<AbsEffect> effects;

    public CouncilSpace(ArrayList<AbsEffect> effects){
        super(1);
        this.effects = effects;
        orderCounter = 0;
        this.playerOrder= new ArrayList<FamilyMember>(0);
    }



    public void setFamilyMember(FamilyMember familyMember){
        this.getPlayerOrder().add(familyMember);
        orderCounter++;
    }

    public ArrayList<AbsEffect> getEffects() {
        return effects;
    }

    public boolean checkFamiliar(PlayerColor playerColor){
        for(int i = 0; i<playerOrder.size(); i++) {
            if (playerOrder.get(i).getPlayer().getPlayerColor() == playerColor) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<FamilyMember> getPlayerOrder() {
        return playerOrder;
    }
}
