package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 09/07/2017.
 */
public class RequirementTest {
    @Test
    public void checkRequirement() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));

        ArrayList<TerritoryCard> territoryCards = ParseJson.getParseJson().getTerritoryCardArrayList();
        ArrayList<CharacterCard> characterCards = ParseJson.getParseJson().getCharacterCardArrayList();
        ArrayList<BuildingCard> buildingCards = ParseJson.getParseJson().getBuildingCardArrayList();
        ArrayList<VentureCard> ventureCards = ParseJson.getParseJson().getVentureCardArrayList();

        for (int i = 0; i < 4; i++) {
            player.getCardOfPlayer().setCard(territoryCards.get(i));
            player.getCardOfPlayer().setCard(characterCards.get(i));
            player.getCardOfPlayer().setCard(buildingCards.get(i));
            player.getCardOfPlayer().setCard(ventureCards.get(i));
        }

        ResourcesSet resourcesSetRequire = new ResourcesSet(1,1,1,1,1,1,1);
        Requirement requirement = new Requirement(5,5,5,5,resourcesSetRequire);
        assertFalse(requirement.checkRequirement(player));
    }

}