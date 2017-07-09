package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Cards.VentureCard;
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
 * Created by Alessandro on 09/07/2017.
 */
public class ProductEffectTest {
    @Test
    public void activeEffect() throws Exception {
        SingleCost giving = new SingleCost(new ResourcesSet(1,1,1,1,1,1,1));
        ProductEffect productEffectBlue = new ProductEffect(giving, CardColor.BLUE);
        ProductEffect productEffectGreen = new ProductEffect(giving, CardColor.GREEN);
        ProductEffect productEffectPurple = new ProductEffect(giving, CardColor.PURPLE);
        ProductEffect productEffectYellow = new ProductEffect(giving, CardColor.YELLOW);

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

        productEffectBlue.activeEffect(player);
        productEffectGreen.activeEffect(player);
        productEffectPurple.activeEffect(player);
        productEffectYellow.activeEffect(player);

        ResourcesSet resourcesSet = new ResourcesSet(16,16,16,16,16,16,16);

        assertTrue(resourcesSet.equals(player.getResources()));

        SingleCost require = new SingleCost(new ResourcesSet(1,1,1,1,1,1,1));

        ProductEffect productEffect = new ProductEffect(giving, require);
        productEffect.activeEffect(player);
        resourcesSet = new ResourcesSet(32,32,32,32,32,32,32);
        assertTrue(resourcesSet.equals(player.getResources()));


    }

}