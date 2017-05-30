package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.GREEN;
import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.RED;
import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class TowerTest {
    @Test
    public void getSpaces() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getDIM() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setDIM() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setSpaces() throws Exception {
        assertTrue(true);
    }

    @Test
    public void checkFamiliarTower() throws Exception {
        Player player = new Player(RED, null);
        Player player2 = new Player(GREEN, null);
        FamilyMember familyMember1 = new FamilyMember(player, FamilyColor.ORANGE, 1);
        FamilyMember familyMember2 = new FamilyMember(player, FamilyColor.BLACK, 1);
        FamilyMember familyMember3 = new FamilyMember(player, FamilyColor.NEUTRAL, 1);
        FamilyMember familyMember4 = new FamilyMember(player, FamilyColor.ORANGE, 1);
        TowerSpace towerSpace1 = new TowerSpace(null, null,2);
        TowerSpace towerSpace2 = new TowerSpace(null, null,3);
        TowerSpace towerSpace3 = new TowerSpace(null, null,5);
        TowerSpace towerSpace4 = new TowerSpace(null, null,1);
        TowerSpace[] towerSpaces = new TowerSpace[4];
        towerSpaces[0] = towerSpace1;
        towerSpaces[1] = towerSpace2;
        towerSpaces[2] = towerSpace3;
        towerSpaces[3] = towerSpace4;
        Tower tower = new Tower(towerSpaces);
        towerSpace1.setFamilyMember(familyMember1);
        towerSpace2.setFamilyMember(familyMember2);

    }

    @Test
    public void checkOtherFamiliar() throws Exception {
        //TODO
    }

}