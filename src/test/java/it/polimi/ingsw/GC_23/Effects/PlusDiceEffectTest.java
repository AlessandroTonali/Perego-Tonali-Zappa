package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 09/07/2017.
 */
public class PlusDiceEffectTest {
    @Test
    public void chooseSale() throws Exception {
        ArrayList<SingleCost> sales  = new ArrayList<>();
        sales.add(new SingleCost(new ResourcesSet(0,1,0,0,1,0,1)));
        PlusDiceEffect plusDiceEffectColor = new PlusDiceEffect(2, "dice_color", null, sales);

        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        assertEquals(sales.get(0), plusDiceEffectColor.chooseSale(player));
    }

    @Test
    public void activeEffect() throws Exception {
        ArrayList<SingleCost> sales  = new ArrayList<>();
        sales.add(new SingleCost(new ResourcesSet(0,1,0,0,1,0,1)));
        PlusDiceEffect plusDiceEffectColor = new PlusDiceEffect(2, "dice_color", null, sales);
        PlusDiceEffect plusDiceEffectNeutral = new PlusDiceEffect(2, "dice_neutral", null,sales);

        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(50,1,5,1,1,50,1));
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        plusDiceEffectColor.activeEffect(player);
        assertEquals(2, player.getFamilyMembers()[0].getValue());
        assertEquals(2, player.getFamilyMembers()[1].getValue());
        assertEquals(2, player.getFamilyMembers()[2].getValue());

        plusDiceEffectNeutral.activeEffect(player);
        assertEquals(2, player.getFamilyMembers()[3].getValue());
    }

}