package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Effects.PlusDiceEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
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
 * Created by Alessandro on 08/07/2017.
 */
public class TowerSpaceTest {
    @Test
    public void resetCard() throws Exception {
    }

    @Test
    public void checkBeforeActivablePermanentEffect() throws Exception {
        ArrayList<SingleCost> sales = new ArrayList<>();
        sales.add(new SingleCost(new ResourcesSet(0,-1,0,0,0,0,0)));
        PlusDiceEffect plusDiceEffect = new PlusDiceEffect(2, "tower", null, sales);
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.getPermanentEffects().add(plusDiceEffect);

        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet(0,1,0,0,0,0,0)));
        CharacterCard card = new CharacterCard(1, CardColor.BLUE, "a",  null, null, costs);
        TowerSpace towerSpace = new TowerSpace(card,(BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[0],
                0);

        towerSpace.checkBeforeActivablePermanentEffect(player.getFamilyMembers()[0]);

        int cardCost = card.getCost().get(0).getResources().getGold();

        assertEquals(0, cardCost);
    }

    @Test
    public void checkAfterActivablePermanentEffect() throws Exception {
    }

}