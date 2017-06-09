package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.BonusTile;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

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
        this.playerOrder= new ArrayList<FamilyMember>(0);
    }

    public ProductionSpace(boolean completePlay){
        super(1);
        isBusyFirst = false;
        orderCounter = 0;
        this.completePlay = completePlay;
        this.playerOrder= new ArrayList<FamilyMember>(0);
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.isBusyFirst = true;
        this.getPlayerOrder().add(familyMember);
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
        for(FamilyMember f: this.getPlayerOrder()){
            f= new FamilyMember(this);
            orderCounter=0;
            isBusyFirst=false;
        }
    }
}
