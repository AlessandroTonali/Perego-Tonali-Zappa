package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.ResourcesPackage.ResourcesSet;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class Player {
    private PlayerColor playerColor;
    private ResourcesSet resources;
    private CardOfPlayer cards;
    private BonusTile bonusTile;
    private FamilyMembers familyMembers;
    private PermanentEffect permanentEffect;

    private void checkPermanentEffect(PermanentEffect effect) {

    }

    public void makeMove(ActionSpace space) {

    }

    public void discardLeaderCard(LeaderCard card) {

    }

    public void activePermanentEffect(PermanentEffect effect){

    }

    public void increaseFamilyValue(FamilyMember member, int value){

    }
}