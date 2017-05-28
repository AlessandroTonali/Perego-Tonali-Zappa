package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class ProductionSpace extends ActionSpace {
    private static boolean isBusyFirst;
    private static int orderCounter;
    private FamilyMember[] playerOrder;

    public ProductionSpace(int value){
        super(value);
        this.isBusyFirst = false;
        orderCounter = 0;
    }

    public FamilyMember[] getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(FamilyMember[] playerOrder) {
        this.playerOrder = playerOrder;
    }

    public boolean checkValue(FamilyMember familyMember){
        if(isBusyFirst) {
            return (familyMember.getValue() - 3) < 1;
        }
        else {
            return (familyMember.getValue()) < 1;

        }

    }
}
