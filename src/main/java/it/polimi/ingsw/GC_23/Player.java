package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Player {
    private PlayerColor playerColor;
    private ResourcesSet resources;
    private CardOfPlayer cards;
    private BonusTile bonusTile;
    private FamilyMember[] familyMembers;
    private PermanentEffect permanentEffect;

    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;

    }

    public void chooseMove(){
        //TODO
        return;
    }
}
