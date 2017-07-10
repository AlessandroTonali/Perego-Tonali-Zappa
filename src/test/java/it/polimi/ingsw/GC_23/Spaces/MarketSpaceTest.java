package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 09/07/2017.
 */
public class MarketSpaceTest {
    @Test
    public void checkValue() throws Exception {
        MarketSpace marketSpace = new MarketSpace(ParseJson.getParseJson().getMarketEffect().get(0));
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        assertTrue(marketSpace.checkValue(familyMember));
    }

    @Test
    public void checkBeforeActivablePermanentEffect() throws Exception {
    }

    @Test
    public void checkAfterActivablePermanentEffect() throws Exception {
    }

}