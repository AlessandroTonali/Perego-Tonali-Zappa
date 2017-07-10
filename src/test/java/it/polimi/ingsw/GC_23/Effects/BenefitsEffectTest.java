package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Connection.*;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import javafx.print.PageLayout;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 07/07/2017.
 */
public class BenefitsEffectTest {
    @Test
    public void activeEffect() throws Exception {
        BenefitsEffect benefitsEffect = new BenefitsEffect(new ResourcesSet(0,1,0,0,0,0,0));
        Player player = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        player.setResources(new ResourcesSet(0,0,0,0,0,0,0));
        UserImpl user = new UserImpl("s");
        RMIHandler rmiHandler = new RMIHandler(user);
        player.setUserHandler(rmiHandler);

        benefitsEffect.activeEffect(player);

        assertEquals(1, player.getResources().getGold());

    }

    @Test
    public void equals() throws Exception {
        BenefitsEffect benefitsEffect = new BenefitsEffect(new ResourcesSet(0,10,1,0,0,0,0));
        BenefitsEffect benefitsEffect1 = new BenefitsEffect(new ResourcesSet(0,10,1,0,0,0,0));

        assertTrue(benefitsEffect.equals(benefitsEffect1));

    }

}