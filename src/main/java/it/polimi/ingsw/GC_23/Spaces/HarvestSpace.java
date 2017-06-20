package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.*;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class HarvestSpace extends ActionSpace {
    private static boolean isBusyFirst;
    private static int orderCounter;
    private ArrayList<FamilyMember> familyMembersPresent;
    private boolean completePlay = false;

    public HarvestSpace(){
        super(1);
        this.isBusyFirst = false;
        orderCounter = 0;
        completePlay =true;
        this.familyMembersPresent = new ArrayList<FamilyMember>();
    }

    public HarvestSpace(boolean completePlay){
        super(1);
        this.isBusyFirst = false;
        orderCounter = 0;
        this.completePlay = completePlay;
        this.familyMembersPresent = new ArrayList<FamilyMember>(0);
    }


    public void setFamilyMember(FamilyMember familyMember) {
        this.isBusyFirst = true;
        this.getFamilyMembersPresent().add(familyMember);
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
        player.setFamilyMembers(members);
    }

    public ArrayList<FamilyMember> getFamilyMembersPresent() {
        return familyMembersPresent;
    }

    public boolean checkValue(FamilyMember familyMember){
        if(isBusyFirst) {
            return (familyMember.getValue() - 3) >= 1;
        }
        else {
            return (familyMember.getValue()) >= 1;

        }
    }

    // controlla se sono presenti altri familiari dello stesso player, true --> son presenti, false --> non sono presenti
    public boolean checkFamiliar(FamilyMember familyMember){
        for (int i = 0; i < familyMembersPresent.size(); i++) {
            if (familyMembersPresent.get(i).getFamilyColor() != FamilyColor.NEUTRAL) {
                if (familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
                    if (familyMembersPresent.get(i).getPlayer() == familyMember.getPlayer()) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    public static boolean getIsBusyFirst() {
        return isBusyFirst;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    @Override
    public void resetFamilyMember(){

            this.familyMembersPresent = new ArrayList<>();
            orderCounter=0;
            isBusyFirst=false;
        }


    @Override
    public void checkBeforeActivablePermanentEffect(FamilyMember familyMember) {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof PlusDiceEffect && ((PlusDiceEffect) permanentEffect).getType().equals("harvest")) {
                familyMember.setValue(familyMember.getValue() + familyMember.getValue());
            }
        }
    }

    @Override
    public void checkAfterActivablePermanentEffect(FamilyMember familyMember) {
        ArrayList<PermanentEffect> permanentEffectArrayList = familyMember.getPlayer().getPermanentEffects();

        for (int i = 0; i < permanentEffectArrayList.size(); i++) {
            PermanentEffect permanentEffect = permanentEffectArrayList.get(i);
            if (permanentEffect instanceof HarvestEffect) {
                if (((HarvestEffect) permanentEffect).checkActivable(familyMember.getValue())) {
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
        for(FamilyMember f : familyMembersPresent) {
            if(f == null) continue;
            stringBuilder.append( "position: " + i + "--" + f.toString() + "\n");

        }
        return String.valueOf(stringBuilder);
    }


}

