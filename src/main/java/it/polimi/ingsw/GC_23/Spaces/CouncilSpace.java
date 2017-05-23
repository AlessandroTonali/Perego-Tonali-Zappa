package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.FamilyMember;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class CouncilSpace extends ActionSpace {
    private static int orderCounter;
    private FamilyMember[] playerOrder;
    public CouncilSpace(){
        super(0);
        orderCounter = 0;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    public FamilyMember[] getPlayerOrder() {
        return playerOrder;
    }
}
