package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.*;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class VentureCardTest {
    @Test
    public void checkTakeable() throws Exception {

        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        ResourcesSet resourcesSet1 = new ResourcesSet(10,10,2,10,10,10,10);
        ResourcesSet resourcesSet2 = new ResourcesSet(0,10,1,0,0,0,0);
        player.setResources(resourcesSet1);
        ArrayList<AbsEffect> effects = new ArrayList<>();
        AbsEffect effect = ParseJson.getParseJson().getEffectMap().get(10);
        effects.add(effect);
        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(resourcesSet2));
        VentureCard ventureCard = new VentureCard(1, CardColor.PURPLE,"test", effects, effects, costs);

        assertTrue(ventureCard.checkTakeable(player));

        MilitaryCost militaryCost = new MilitaryCost(resourcesSet1, resourcesSet2);
        costs = new ArrayList<>();
        costs.add(militaryCost);
        ventureCard.setCost(costs);
        assertTrue(ventureCard.checkTakeable(player));

        for (int i = 0; i < 6; i++) {
            player.getCardOfPlayer().getVentureCards().add(ventureCard);
        }
        assertFalse(ventureCard.checkTakeable(player));

    }

    @Test
    public void addCardOfPlayer() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        ArrayList<TerritoryCard> territoryCards = new ArrayList<>();
        ArrayList<CharacterCard> characterCards = new ArrayList<>();
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        ArrayList<VentureCard> ventureCards = new ArrayList<>();
        ArrayList<AbsEffect> effects = new ArrayList<>();
        AbsEffect effect = ParseJson.getParseJson().getEffectMap().get(10);
        effects.add(effect);
        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet(0,10,1,0,0,0,0)));
        VentureCard ventureCard = new VentureCard(1, CardColor.PURPLE,"test", effects, effects, costs);
        player.setCardOfPlayer(new CardOfPlayer(territoryCards,characterCards,buildingCards,ventureCards));

        ventureCard.addCardOfPlayer(player);
        assertTrue(player.getCardOfPlayer().getVentureCards().size() == 1);
    }

}