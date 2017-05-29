package it.polimi.ingsw.GC_23.Cards;

import it.polimi.ingsw.GC_23.CardOfPlayer;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.RED;
import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class TerritoryCardTest {
    @Test
    public void checkTakeable() throws Exception {
        Player player = new Player(RED, null);
        ResourcesSet res1 = new ResourcesSet(0,0,2,0,0,0,0);
        player.setResources(res1);
        TerritoryCard card1 = new TerritoryCard(1, CardColor.YELLOW, null, null);
        ArrayList<TerritoryCard> cards = new ArrayList<TerritoryCard>();
        cards.add(0, card1);
        cards.add(1, card1);
        cards.add(2, card1);
        CardOfPlayer cardOfPlayer = new CardOfPlayer(cards, null, null, null);
        player.setCardOfPlayer(cardOfPlayer);
        assertEquals(true, card1.checkTakeable(player));
        cards.add(3, card1);
        assertEquals(false, card1.checkTakeable(player));
        //TODO: Sbagliato

    }

}