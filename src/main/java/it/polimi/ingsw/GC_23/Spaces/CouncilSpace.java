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



    /**
     * show on abstract class
     */
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

    /**
     * check if already present a family member of the player
     * @param playerColor color of player that want to put a familiar in council space
     * @return true if familiar of the player is already present,
     * false if familiar of the player isn't present
     */
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

    /**
     * show on abstract class
     */
    @Override
    public void resetFamilyMember(){

        playerOrder = new ArrayList<>();


    }

    /**
     * show on abstract class
     */
    @Override
    public void checkBeforeActivablePermanentEffect(FamilyMember familyMember) {

    }

    /**
     * show on abstract class
     */
    @Override
    public void checkAfterActivablePermanentEffect(FamilyMember familyMember) {

    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for(Player p : playerOrder) {
            stringBuilder.append( "position: " + i + "--" + p.toString());

        }
        return String.valueOf(stringBuilder);
    }
}
