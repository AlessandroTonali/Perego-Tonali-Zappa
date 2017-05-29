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
public class VentureCardTest {
    @Test
    public void checkTakeable() throws Exception {
        Player player = new Player(RED, null);
        ResourcesSet res1 = new ResourcesSet(1,2,3,4,5,6,7);
        ResourcesSet res2 = new ResourcesSet(2,2,3,4,5,6,7);
        ResourcesSet res3 = new ResourcesSet(4,2,3,4,0,6,7);
        SingleCost cost1 = new SingleCost(res1);
        SingleCost cost2 = new SingleCost(res3);
        player.setResources(res2);
        VentureCard card1 = new VentureCard(1, CardColor.YELLOW, null, null, cost1);
        VentureCard card2 = new VentureCard(1, CardColor.GREEN, null, null, cost1);
        VentureCard card3 = new VentureCard(1, CardColor.PURPLE, null, null, cost1);
        VentureCard card4 = new VentureCard(1, CardColor.PURPLE, null, null, cost2);
        ArrayList<VentureCard> cards = new ArrayList<VentureCard>();
        cards.add(0, card1);
        cards.add(1, card2);
        cards.add(2, card1);
        CardOfPlayer cardOfPlayer = new CardOfPlayer(null, null, null, cards);
        player.setCardOfPlayer(cardOfPlayer);
        assertEquals(true, card3.checkTakeable(player));
        cards.add(3,card2);
        cards.add(4,card1);
        cards.add(5,card2);
        assertEquals(false, card3.checkTakeable(player));
        assertEquals(false, card4.checkTakeable(player));
    }

}