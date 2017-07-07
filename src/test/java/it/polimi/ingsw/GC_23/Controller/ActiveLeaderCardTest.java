package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.LeaderCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.PlayerTimeOut;
import it.polimi.ingsw.GC_23.Requirement;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class ActiveLeaderCardTest {
    @Test
    public void isLegal() throws Exception {
        ResourcesSet resourcesRequireLeader = new ResourcesSet(0,5,5,0,5,0,0);
        ResourcesSet resourcesPlayer = new ResourcesSet(5,5,5,5,5,5,5);
        Requirement requirement = new Requirement(1,0,0,0, resourcesRequireLeader);
        ArrayList<AbsEffect> effects = new ArrayList<>();
        BenefitsEffect effect = new BenefitsEffect(resourcesRequireLeader);
        effects.add(effect);


        LeaderCard leaderCard = new LeaderCard("ariosto", requirement, effects);
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        player.setResources(resourcesPlayer);

        PlayerTimeOut playerTimeOut = new PlayerTimeOut(player);

        ActiveLeaderCard activeLeaderCard = new ActiveLeaderCard(leaderCard, player, playerTimeOut);

        assertTrue(activeLeaderCard.isLegal());
    }

    @Test
    public void makeAction() throws Exception {
    }

}