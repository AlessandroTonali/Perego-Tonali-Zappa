package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Effects.SetDiceEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 08/07/2017.
 */
public class FamilyMemberTest {
    @Test
    public void checkPermanentEffect() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        FamilyMember familyMemberNeutral = new FamilyMember(player, FamilyColor.NEUTRAL, 5);
        SetDiceEffect setDiceEffect = new SetDiceEffect(7, "dice_neutral");
        PlusDiceEffect plusDiceEffect = new PlusDiceEffect(2, "dice_color", null, null);
        PlusDiceEffect plusDiceEffect1 = new PlusDiceEffect(3, "dice_neutral", null, null);

        player.getPermanentEffects().add(setDiceEffect);
        player.getPermanentEffects().add(plusDiceEffect);
        FamilyMember[] familyMembers = new FamilyMember[2];
        familyMembers[0] = familyMember;
        familyMembers[1] = familyMemberNeutral;
        player.setFamilyMembers(familyMembers);

        assertEquals(7, familyMember.checkPermanentEffect(familyMember.getValue()));
        assertEquals(7, familyMemberNeutral.checkPermanentEffect(familyMemberNeutral.getValue()));
        player.getPermanentEffects().add(plusDiceEffect1);
        assertEquals(10, familyMemberNeutral.checkPermanentEffect(familyMemberNeutral.getValue()));
    }

    @Test
    public void increaseFamilyValue() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        player.setResources(new ResourcesSet(0,0,0,2,0,0,0));
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player.setFamilyMembers(familyMembers);
        familyMember.increaseFamilyValue(2);

        assertEquals(7, familyMember.getValue());
        assertEquals(0, player.getResources().getServants());

    }

    @Test
    public void isEquals() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember familyMember = new FamilyMember(player, FamilyColor.ORANGE, 5);
        player.setResources(new ResourcesSet(0,0,0,2,0,0,0));
        FamilyMember[] familyMembers = new FamilyMember[1];
        familyMembers[0] = familyMember;
        player.setFamilyMembers(familyMembers);

        assertEquals(true,familyMember.isEquals(familyMember));

    }

}