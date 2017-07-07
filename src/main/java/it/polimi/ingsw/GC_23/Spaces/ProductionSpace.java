package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.BonusTile;
import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ProductionSpace extends ActionSpace {
    private boolean isBusyFirst;
    private int orderCounter;
    private ArrayList<FamilyMember> playerOrder;
    private boolean completePlay = false;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ProductionSpace(){
        super(1);
        isBusyFirst = false;
        orderCounter = 0;
        completePlay =true;
        this.playerOrder= new ArrayList<FamilyMember>();
    }

    public ProductionSpace(boolean completePlay){
        super(1);
        isBusyFirst = false;
        orderCounter = 0;
        this.completePlay = completePlay;
        this.playerOrder= new ArrayList<FamilyMember>();
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.isBusyFirst = true;
        this.playerOrder.add(familyMember);
        orderCounter++;
        Player player = familyMember.getPlayer();
        FamilyMember[] members = familyMember.getPlayer().getFamilyMembers();
        int i = 0;
        for(FamilyMember m : members) {
            if(m != null && m == familyMember) {
                members[i] = null;
                break;
            }
        }
        player.setFamilyMembers(members);

            i++;
        }


    public ArrayList<FamilyMember> getPlayerOrder() {
        return playerOrder;
    }

    /**
     *
     * @param familyMember that you want to check the value
     * @return true if family member value is higher than space value
     */
    public boolean checkValue(FamilyMember familyMember){
        if(isBusyFirst) {
            return (familyMember.getValue() - 3) > 1;
        }
        else {
            return (familyMember.getValue()) > 1;

        }
    }

    /**
     * check if there is another family member of the player
     * @param familyMember that want to put in a space
     * @return true if another family member of player is present, false if there isn't another family member of player
     */
    public boolean checkFamiliar(FamilyMember familyMember){
        if(!completePlay && this.isBusyFirst){
            return true;
        }
        boolean familiarPresence = false;
        if (familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
            for (FamilyMember aPlayerOrder : playerOrder) {
                if (aPlayerOrder.getPlayer().isEquals(familyMember.getPlayer())) {
                    familiarPresence = true;
                }
            }
        }
        return familiarPresence;
    }

    public boolean getIsBusyFirst() {
        return isBusyFirst;
    }

    public int getOrderCounter() {
        return orderCounter;
    }

    @Override
    public void resetFamilyMember(){
            playerOrder = new ArrayList<>();

            orderCounter=0;
            isBusyFirst=false;
        }


    @Override
    public void checkBeforeActivablePermanentEffect(FamilyMember familyMember) {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffect).getType().equals("production")) {
                familyMember.setValue(familyMember.getValue() + ((PlusDiceEffect) permanentEffect).getPlusDiceValue());
            }
        }
    }

    @Override
    public void checkAfterActivablePermanentEffect(FamilyMember familyMember) {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof ProductionEffect) {
                if (((ProductionEffect) permanentEffect).checkActivable(familyMember.getValue())) {
                    try {
                        permanentEffect.activeEffect(familyMember.getPlayer());
                    } catch (IOException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;

        for(FamilyMember f : playerOrder) {
            if(f == null) continue;
            stringBuilder.append("position: ").append(i).append("--").append(f.toString());

        }
        return String.valueOf(stringBuilder);
    }
}
