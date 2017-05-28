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
    private CardOfPlayer cardOfPlayer;
    private BonusTile bonusTile;
    private FamilyMember[] familyMembers;
    private PermanentEffect permanentEffect;

    public Player(PlayerColor playerColor, BenefitsEffect bonustTile) {
        this.playerColor = playerColor;
        // bisogna usare il design pattern della fabbrica per dare le risorse giuste in base ai player
        this.cardOfPlayer = new CardOfPlayer();
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

    public CardOfPlayer getCardOfPlayer() {
        return cardOfPlayer;
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

    public ResourcesSet sum(ResourcesSet set){
        ResourcesSet result = new ResourcesSet(0,0,0,0,0,0,0);
        result.setFaithPoints(this.getResources().getFaithPoints() + set.getFaithPoints());
        result.setGold(this.getResources().getGold()+ set.getGold());
        result.setMilitaryPoints(this.getResources().getMilitaryPoints()+ set.getMilitaryPoints());
        result.setServants(this.getResources().getServants() + set.getServants());
        result.setStone(this.getResources().getStone()+set.getStone());
        result.setVictoryPoints(this.getResources().getVictoryPoints()+ set.getVictoryPoints());
        result.setWood(this.getResources().getWood()+ set.getWood());
        return result;
    }
}
