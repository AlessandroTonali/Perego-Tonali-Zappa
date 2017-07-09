package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;

import java.util.ArrayList;

/**
 * Created by Alessandro on 02/06/2017.
 */
public class ProductEffect extends AbsEffect {
    private SingleCost giving;
    private SingleCost required;
    private CardColor cardColor;
    private boolean isProductResource = false;

    @Override
    public String toString() {
        if(required.equals(new SingleCost(new ResourcesSet()))){
            return "You receive one " + giving.getResources().toString() + "for every: " +
                    cardColor.toString() + "you have";
        }
        return "You receive one " + giving.getResources().toString() + "for every: " +
                required + "you have";
    }

    public ProductEffect(SingleCost giving, CardColor cardColor) {
        this.giving = giving;
        this.cardColor = cardColor;
        this.required = new SingleCost(new ResourcesSet());

    }

    public ProductEffect (SingleCost giving, SingleCost required) {
        this.giving = giving;
        this.required = required;
        isProductResource = true;
    }

    public SingleCost getGiving() {
        return giving;
    }

    public void setGiving(SingleCost giving) {
        this.giving = giving;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public void activeEffect(Player player) {
        int productFactor = 0;
        if (!isProductResource) {

            switch (cardColor) {
                case BLUE:
                    productFactor = player.getCardOfPlayer().getCharacterCards().size();
                    break;
                case GREEN:
                    productFactor = player.getCardOfPlayer().getTerritoryCards().size();
                    break;
                case PURPLE:
                    productFactor = player.getCardOfPlayer().getVentureCards().size();
                    break;
                case YELLOW:
                    productFactor = player.getCardOfPlayer().getBuildingCards().size();
                    break;
            }
        } else {
            if (required.getResources().getMilitaryPoints() != 0) {
                productFactor = player.getResources().getMilitaryPoints() / required.getResources().getMilitaryPoints();
            }
            if (required.getResources().getFaithPoints() != 0) {
                productFactor = player.getResources().getFaithPoints() / required.getResources().getFaithPoints();
            }
            if (required.getResources().getGold() != 0) {
                productFactor = player.getResources().getGold() / required.getResources().getGold();
            }
            if (required.getResources().getServants() != 0) {
                productFactor = player.getResources().getServants() / required.getResources().getServants();
            }
            if (required.getResources().getStone() != 0) {
                productFactor = player.getResources().getStone() / required.getResources().getStone();
            }
            if (required.getResources().getVictoryPoints() != 0) {
                productFactor = player.getResources().getVictoryPoints() / required.getResources().getVictoryPoints();
            }
            if (required.getResources().getWood() != 0) {
                productFactor = player.getResources().getWood() / required.getResources().getWood();
            }

        }


        ResourcesSet givRes = giving.getResources();
        ResourcesSet resource = new ResourcesSet(givRes.getFaithPoints(),givRes.getGold(), givRes.getMilitaryPoints(), givRes.getServants(),givRes.getStone(),givRes.getVictoryPoints(),givRes.getWood());
        if(resource.getWood()>0){
                resource.setWood(resource.getWood() * productFactor);
        }
        if(resource.getVictoryPoints()>0){
                resource.setVictoryPoints(resource.getVictoryPoints() * productFactor);
        }
        if(resource.getStone()>0){
                resource.setStone(resource.getStone() * productFactor);
        }
        if(resource.getServants()>0){
            resource.setServants(resource.getServants() * productFactor);
        }
        if(resource.getFaithPoints()>0){
            resource.setFaithPoints(resource.getFaithPoints() * productFactor);
        }
        if(resource.getGold()>0){
            resource.setGold(resource.getGold() * productFactor);
        }
        if(resource.getMilitaryPoints()>0) {
            resource.setMilitaryPoints(resource.getMilitaryPoints() * productFactor);
        }
        player.getResources().sum(resource, player);
    }
}
