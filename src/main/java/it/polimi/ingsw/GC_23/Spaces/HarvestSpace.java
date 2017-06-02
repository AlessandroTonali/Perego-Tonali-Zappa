package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;

import java.util.ArrayList;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class HarvestSpace extends ActionSpace {
    private static boolean isBusyFirst;
    private static int orderCounter;
    private ArrayList<FamilyMember> playerOrder;
    private boolean completePlay = false; //todo: se false partita di due giocatori: un solo spazio

    public HarvestSpace(){
        super(1);
        this.isBusyFirst = false;
        orderCounter = 0;
        this.playerOrder= new ArrayList<FamilyMember>(0);
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.isBusyFirst = true;
        this.getPlayerOrder().add(familyMember);
        orderCounter++;
    }

    public ArrayList<FamilyMember> getPlayerOrder() {
        return playerOrder;
    }

    public boolean checkValue(FamilyMember familyMember){
        if(isBusyFirst) {
            return (familyMember.getValue() - 3) >= 1;
        }
        else {
            return (familyMember.getValue()) >= 1;

        }
    }

    public boolean checkFamiliar(PlayerColor playerColor){
        for(int i = 0; i<playerOrder.size(); i++) {
            if (playerOrder.get(i).getPlayer().getPlayerColor() == playerColor && !(playerOrder.get(i)
                    .getFamilyColor() == FamilyColor.NEUTRAL)) {
                return true;
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
        for(FamilyMember f: this.getPlayerOrder()){
            f= new FamilyMember(this);
            orderCounter=0;
            isBusyFirst=false;
        }
    }
}

