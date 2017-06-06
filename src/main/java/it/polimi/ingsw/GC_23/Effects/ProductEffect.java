package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.EffectType;
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

    public ProductEffect(SingleCost giving, CardColor cardColor) {
        this.giving = giving;
        this.cardColor = cardColor;
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
            productFactor = player.getResources().getMilitaryPoints() / 2;
        }

        ResourcesSet resource = giving.getResources();
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
        player.getResources().sum(resource);
    }

    @Override
    public int getTypeEffect() {
        return EffectType.PRODUCT_EFFECT_TYPE;
    }
}
