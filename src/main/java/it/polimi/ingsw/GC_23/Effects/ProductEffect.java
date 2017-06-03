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
    private CardColor cardColor;

    public ProductEffect(SingleCost giving, CardColor cardColor) {
        this.giving = giving;
        this.cardColor = cardColor;
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
        int numberOfCards = 0;
        switch (cardColor) {
            case BLUE:
                numberOfCards = player.getCardOfPlayer().getCharacterCards().size();
                break;
            case GREEN:
                numberOfCards = player.getCardOfPlayer().getTerritoryCards().size();
                break;
            case PURPLE:
                numberOfCards = player.getCardOfPlayer().getVentureCards().size();
                break;
            case YELLOW:
                numberOfCards = player.getCardOfPlayer().getBuildingCards().size();
                break;
        }

        ResourcesSet resource = giving.getResources();
        if(resource.getWood()>0){
                resource.setWood(resource.getWood() * numberOfCards);
        }
        if(resource.getVictoryPoints()>0){
                resource.setVictoryPoints(resource.getVictoryPoints() * numberOfCards);
        }
        if(resource.getStone()>0){
                resource.setStone(resource.getStone() * numberOfCards);
        }
        if(resource.getServants()>0){
            resource.setServants(resource.getServants() * numberOfCards);
        }
        if(resource.getFaithPoints()>0){
            resource.setFaithPoints(resource.getFaithPoints() * numberOfCards);
        }
        if(resource.getGold()>0){
            resource.setGold(resource.getGold() * numberOfCards);
        }
        if(resource.getMilitaryPoints()>0) {
            resource.setMilitaryPoints(resource.getMilitaryPoints() * numberOfCards);
        }
        player.getResources().sum(resource);
    }

    @Override
    public int getTypeEffect() {
        return EffectType.PRODUCT_EFFECT_TYPE;
    }
}
