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


/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ProductionSpace extends ActionSpace {
    private static boolean isBusyFirst;
    private static int orderCounter;
    private ArrayList<FamilyMember> playerOrder;
    private boolean completePlay = false;

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

    public boolean checkValue(FamilyMember familyMember){
        if(isBusyFirst) {
            return (familyMember.getValue() - 3) > 1;
        }
        else {
            return (familyMember.getValue()) > 1;

        }
    }

    public boolean checkFamiliar(FamilyMember familyMember){
        if(completePlay == false && this.isBusyFirst == true){
            return true;
        }
        boolean familiarPresence = false;
        if (familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
            for (int i = 0; i < playerOrder.size(); i++) {
                if (playerOrder.get(i).getPlayer().isEquals(familyMember.getPlayer())) {
                    familiarPresence = true;
                }
            }
        }
        return familiarPresence;
    }

    public static boolean getIsBusyFirst() {
        return isBusyFirst;
    }

    public static int getOrderCounter() {
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
                familyMember.setValue(familyMember.getValue() + familyMember.getValue());
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
                        e.printStackTrace();
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
            stringBuilder.append( "position: " + i + "--" + f.toString());

        }
        return String.valueOf(stringBuilder);
    }
}
