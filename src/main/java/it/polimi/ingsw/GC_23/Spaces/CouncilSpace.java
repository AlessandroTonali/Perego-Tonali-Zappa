package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class CouncilSpace extends ActionSpace {
    private static int orderCounter;
    private ArrayList<Player> playerOrder;
    private final ArrayList<AbsEffect> effects;

    public CouncilSpace(ArrayList<AbsEffect> effects){
        super(1);
        this.effects = effects;
        orderCounter = 0;
        this.playerOrder= new ArrayList<Player>();
    }



    public void setFamilyMember(FamilyMember familyMember){
        this.playerOrder.add(familyMember.getPlayer());
        orderCounter++;
        Player player = familyMember.getPlayer();
        FamilyMember[] members = familyMember.getPlayer().getFamilyMembers();
        int i = 0;
        for(FamilyMember m : members) {
            if(m != null && m == familyMember) {
                members[i] = null;
                break;
            }
            i++;
        }
    }

    public ArrayList<AbsEffect> getEffects() {
        return effects;
    }

    public boolean checkFamiliar(PlayerColor playerColor){
        for(int i = 0; i<playerOrder.size(); i++) {
            if (playerOrder.get(i).getPlayerColor() == playerColor) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayerOrder() {
        return playerOrder;
    }

    @Override
    public void resetFamilyMember(){


    }
}
