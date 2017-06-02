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
        resource.setFaithPoints(resource.getFaithPoints() * numberOfCards);
        resource.setGold(resource.getGold() * numberOfCards);
        resource.setMilitaryPoints(resource.getMilitaryPoints() * numberOfCards);
        resource.setServants(resource.getServants() * numberOfCards);
        resource.setStone(resource.getStone() * numberOfCards);
        resource.setVictoryPoints(resource.getVictoryPoints() * numberOfCards);
        resource.setWood(resource.getWood() * numberOfCards);

        player.getResources().sum(resource);

    }

    @Override
    public int getTypeEffect() {
        return EffectType.PRODUCT_EFFECT_TYPE;
    }
}
