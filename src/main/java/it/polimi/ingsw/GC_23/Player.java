package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
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

    public Player(PlayerColor playerColor, BenefitsEffect bonustTile) {
        this.playerColor = playerColor;
        // bisogna usare il design pattern della fabbrica per dare le risorse giuste in base ai player
        this.cards = new CardOfPlayer();
        this.bonusTile = new BonusTile(bonustTile);
        //bisogna decidere come istanziare i family member
        // permanent effect ancora non lo dobbiamo fare

    }

    public void setResources(ResourcesSet resources) {
        this.resources = resources;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public ResourcesSet getResources() {
        return resources;
    }

    public CardOfPlayer getCards() {
        return cards;
    }

    public BonusTile getBonusTile() {
        return bonusTile;
    }

    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void chooseMove(){
        //TODO
        return;
    }
}
