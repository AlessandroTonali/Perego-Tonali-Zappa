package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Creator;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Effects.ProductionEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class ProductionSpaceTest {
    @Test
    public void setFamilyMember() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player.setFamilyMembers(familyMembers);
        familyMember.setPlayer(player);

        ProductionSpace productionSpace = new ProductionSpace(true);
        productionSpace.setFamilyMember(familyMember);
        assertEquals(null, player.getFamilyMembers()[0]);
    }

    @Test
    public void checkValue() throws Exception {
        ProductionSpace productionSpace = new ProductionSpace(true);
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);

        assertTrue(productionSpace.checkValue(familyMember));
    }

    @Test
    public void checkFamiliar() throws Exception {
        Player player1 = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        Player player2 = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player1, FamilyColor.ORANGE, 5);
        FamilyMember familyMember2 = new FamilyMember(player2, FamilyColor.ORANGE, 5);
        familyMember.setPlayer(player1);
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player1.setFamilyMembers(familyMembers);
        familyMembers[0] = familyMember2;
        player2.setFamilyMembers(familyMembers);
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player1.setUserHandler(rmiHandler);
        UserImpl user2 = new UserImpl("s");
        RMIHandler rmiHandler2 = new RMIHandler(user2);
        player2.setUserHandler(rmiHandler2);
        ProductionSpace productionSpace = new ProductionSpace(true);

        productionSpace.setFamilyMember(familyMember2);
        assertFalse(productionSpace.checkFamiliar(familyMember));

        productionSpace.setFamilyMember(familyMember);
        assertTrue(productionSpace.checkFamiliar(familyMember));


    }

    @Test
    public void resetFamilyMember() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);

        ProductionSpace productionSpace = new ProductionSpace(true);
        productionSpace.setFamilyMember(player.getFamilyMembers()[0]);

        productionSpace.resetFamilyMember();

        assertEquals(0, productionSpace.getPlayerOrder().size());
    }

    @Test
    public void checkBeforeActivablePermanentEffect() throws Exception {
        PlusDiceEffect plusDiceEffect = new PlusDiceEffect(2, "production", null, null);
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.getPermanentEffects().add(plusDiceEffect);

        ProductionSpace productionSpace = new ProductionSpace(true);

        productionSpace.checkBeforeActivablePermanentEffect(player.getFamilyMembers()[0]);

        assertEquals(7, player.getFamilyMembers()[0].getValue());
    }

    @Test
    public void checkAfterActivablePermanentEffect() throws Exception {
        ArrayList<AbsEffect> effects = new ArrayList<>();
        effects.add(ParseJson.getParseJson().getEffectMap().get(211));
        ProductionEffect productionEffect = new ProductionEffect(5, effects);
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.getPermanentEffects().add(productionEffect);
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        ProductionSpace productionSpace = new ProductionSpace(true);

        productionSpace.checkAfterActivablePermanentEffect(player.getFamilyMembers()[0]);

        assertEquals(1, player.getResources().getStone());

    }

}