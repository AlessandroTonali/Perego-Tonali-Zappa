package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.*;
import it.polimi.ingsw.GC_23.Cards.*;
import it.polimi.ingsw.GC_23.Connection.RMIHandler;
import it.polimi.ingsw.GC_23.Connection.SocketHandler;
import it.polimi.ingsw.GC_23.Connection.User;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class ActiveLeaderCardTest {
    @Test
    public void isLegal() throws Exception {
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        ResourcesSet resourceInitial = new ResourcesSet(5,5,5,5,5,5,5);
        player.setResources(resourceInitial);
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 0);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        ArrayList<TerritoryCard> territoryCards = ParseJson.getParseJson().getTerritoryCardArrayList();
        ArrayList<CharacterCard> characterCards = ParseJson.getParseJson().getCharacterCardArrayList();
        ArrayList<BuildingCard> buildingCards = ParseJson.getParseJson().getBuildingCardArrayList();
        ArrayList<VentureCard> ventureCards = ParseJson.getParseJson().getVentureCardArrayList();

        for (int i = 0; i < 3; i++) {
            player.getCardOfPlayer().setCard(territoryCards.get(i));
            player.getCardOfPlayer().setCard(characterCards.get(i));
            player.getCardOfPlayer().setCard(buildingCards.get(i));
            player.getCardOfPlayer().setCard(ventureCards.get(i));
        }

        ResourcesSet resourcesRequireLeader = new ResourcesSet(5,5,5,5,5,5,5);
        Requirement requirement = new Requirement(1,1,1,1, resourcesRequireLeader);
        ArrayList<AbsEffect> effects = new ArrayList<>();
        BenefitsEffect effect = new BenefitsEffect(resourcesRequireLeader);
        effects.add(effect);

        PlayerTimeOut playerTimeOut = new PlayerTimeOut(player);

        LeaderCard leaderCard = new LeaderCard("ariosto", requirement, effects);
        ActiveLeaderCard activeLeaderCard = new ActiveLeaderCard(leaderCard, player, playerTimeOut);

        assertTrue(leaderCard.isActivatedInThisRound());
    }

    @Test
    public void makeAction() throws Exception {
    }

}