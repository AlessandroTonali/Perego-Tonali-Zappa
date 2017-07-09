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
public class EndGameEffectTest {
    @Test
    public void activeEffect() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 0);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        player.setResources(new ResourcesSet(50,1,5,1,1,50,1));


        ArrayList<SingleCost> costs = new ArrayList<>();
        costs.add(new SingleCost(new ResourcesSet(1,1,1,1,1,1,1)));
        BuildingCard buildingCard = new BuildingCard(1, CardColor.YELLOW, null, null, null, costs);
        buildingCard.setCostSelected(costs.get(0));

        player.getCardOfPlayer().setCard(buildingCard);


        EndGameEffect endGameEffect1 = new EndGameEffect(1);
        EndGameEffect endGameEffect2 = new EndGameEffect(2);
        EndGameEffect endGameEffect3 = new EndGameEffect(3);
        EndGameEffect endGameEffect4 = new EndGameEffect(4);

        endGameEffect1.activeEffect(player);
        assertEquals(40, player.getResources().getVictoryPoints());

        endGameEffect2.activeEffect(player);
        assertEquals(35, player.getResources().getVictoryPoints());

        endGameEffect3.activeEffect(player);
        assertEquals(33, player.getResources().getVictoryPoints());

        endGameEffect4.activeEffect(player);
        assertEquals(29, player.getResources().getVictoryPoints());


    }

}