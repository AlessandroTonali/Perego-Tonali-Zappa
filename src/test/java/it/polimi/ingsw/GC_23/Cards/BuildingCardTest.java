package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.CardOfPlayer;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.RED;
import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class BuildingCardTest {
    @Test
    public void getCost() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setCost() throws Exception {
        assertTrue(true);
    }

    @Test
    public void checkTakeable() throws Exception {
        Player player = new Player(RED, null);
        ResourcesSet res1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet res2 = new ResourcesSet(2,2,3,4,5,6,7);
        ResourcesSet res3 = new ResourcesSet(4,2,3,4,0,6,7);
        player.setResources(res2);
        SingleCost cost1 = new SingleCost(res1);
        SingleCost cost2 = new SingleCost(res3);
        BuildingCard card1 = new BuildingCard(1, CardColor.YELLOW, null, null, null, cost1);
        BuildingCard card2 = new BuildingCard(1, CardColor.GREEN, null, null, null, cost1);
        BuildingCard card3 = new BuildingCard(1, CardColor.PURPLE, null, null, null,cost1);
        BuildingCard card4 = new BuildingCard(1, CardColor.PURPLE, null, null, null, cost2);
        CardOfPlayer cardOfPlayer = new CardOfPlayer();
        player.setCardOfPlayer(cardOfPlayer);
        player.getCardOfPlayer().setCard(card1);
        player.getCardOfPlayer().setCard(card2);
        player.getCardOfPlayer().setCard(card1);
        assertEquals(true, card3.checkTakeable(player));
        player.getCardOfPlayer().setCard(card2);
        player.getCardOfPlayer().setCard(card1);
        player.getCardOfPlayer().setCard(card2);
        assertEquals(false, card3.checkTakeable(player));
        assertEquals(false, card4.checkTakeable(player));
    }

}