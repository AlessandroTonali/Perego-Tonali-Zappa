package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.CardOfPlayer;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import javafx.scene.effect.Effect;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class BuildingCardTest {
    @Test
    public void checkTakeable() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        player.setResources(new ResourcesSet(10,10,10,10,10,10,10));
        ArrayList<AbsEffect> effects = new ArrayList<>();
        AbsEffect effect = ParseJson.getParseJson().getEffectMap().get(10);
        effects.add(effect);
        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet(0,10,1,0,0,0,0)));
        BuildingCard buildingCard = new BuildingCard(1, CardColor.YELLOW,"test", effects, effects,costs);

        assertTrue(buildingCard.checkTakeable(player));
        player.setResources(new ResourcesSet(12,0,10,0,0,0,0));
        assertFalse(buildingCard.checkTakeable(player));
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
        BuildingCard buildingCard = new BuildingCard(1, CardColor.YELLOW,"test", effects, effects,costs);

        player.setCardOfPlayer(new CardOfPlayer(territoryCards,characterCards,buildingCards,ventureCards));

        buildingCard.addCardOfPlayer(player);
        assertTrue(player.getCardOfPlayer().getBuildingCards().size() == 1);
    }

}