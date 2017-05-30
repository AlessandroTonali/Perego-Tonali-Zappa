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
        TerritoryCard card1 = new TerritoryCard(1, CardColor.YELLOW, null, null, null);
        CardOfPlayer cardOfPlayer = new CardOfPlayer();
        player.setCardOfPlayer(cardOfPlayer);
        assertEquals(true, card1.checkTakeable(player));
        player.getCardOfPlayer().setCard(card1);
        cardOfPlayer.setCard(card1);
        cardOfPlayer.setCard(card1);
        cardOfPlayer.setCard(card1);
        assertEquals(false, card1.checkTakeable(player));
    }

}