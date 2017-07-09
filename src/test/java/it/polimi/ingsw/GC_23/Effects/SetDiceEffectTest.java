package it.polimi.ingsw.GC_23.Effects;

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
public class SetDiceEffectTest {
    @Test
    public void activeEffect() throws Exception {
        SetDiceEffect setDiceEffectColor = new SetDiceEffect(5, "dice_color");
        SetDiceEffect setDiceEffectNeutral = new SetDiceEffect(5, "dice_neutral");

        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);

        setDiceEffectColor.activeEffect(player);
        assertEquals(5, player.getFamilyMembers()[0].getValue());
        assertEquals(5, player.getFamilyMembers()[1].getValue());
        assertEquals(5, player.getFamilyMembers()[2].getValue());
        setDiceEffectNeutral.activeEffect(player);
        assertEquals(5, player.getFamilyMembers()[3].getValue());

    }

}