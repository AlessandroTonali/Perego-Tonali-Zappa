package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
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
public class FlagEffectTest {
    @Test
    public void activeEffect() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        FlagEffect flagEffectVenture = new FlagEffect("venture");
        FlagEffect flagEffectCharacter = new FlagEffect("character");
        FlagEffect flagEffectTerritory = new FlagEffect("territory");
        FlagEffect flagEffectMarket = new FlagEffect("market");
        FlagEffect flagEffectDoubleServant = new FlagEffect("double_servant");
        FlagEffect flagEffectMilitary = new FlagEffect("military_territory");
        FlagEffect flagEffectVatican = new FlagEffect("point_vatican");
        FlagEffect flagEffectSpendTower = new FlagEffect("spend_tower");

        flagEffectVenture.activeEffect(player);
        flagEffectCharacter.activeEffect(player);
        flagEffectTerritory.activeEffect(player);
        flagEffectMarket.activeEffect(player);
        flagEffectDoubleServant.activeEffect(player);
        flagEffectMilitary.activeEffect(player);
        flagEffectVatican.activeEffect(player);
        flagEffectSpendTower.activeEffect(player);

        assertTrue(player.isNotScoreVenture());
        assertTrue(player.isNotScoreCharacter());
        assertTrue(player.isNotScoreTerrytory());
        assertTrue(player.isNotPlayInMarket());
        assertTrue(player.isDoubleServantToIncrease());
        assertTrue(player.isNotCheckMilitaryOnTerritory());
        assertTrue(player.isPointOnVatican());
        assertTrue(player.isNotSpendOnOccupiedTower());

    }

}