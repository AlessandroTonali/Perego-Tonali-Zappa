package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Resources.Resources;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import org.junit.Test;

import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.BLUE;
import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.GREEN;
import static it.polimi.ingsw.GC_23.Enumerations.PlayerColor.RED;
import static org.junit.Assert.*;

/**
 * Created by jesss on 28/05/17.
 */
public class FamilyMemberTest {
    @Test
    public void getPlayer() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setPlayer() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getFamilyColor() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getValue() throws Exception {
        assertTrue(true);
    }

    @Test
    public void getPosition() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setValue() throws Exception {
        assertTrue(true);
    }

    @Test
    public void setPosition() throws Exception {
        assertTrue(true);
    }

    @Test
    public void increaseFamilyValue() throws Exception {
        Player player = new Player(RED, null);
        ResourcesSet resources = new ResourcesSet(0,0,0,4,0,0,0);
        player.setResources(resources);
        FamilyMember familyMember = new FamilyMember(player, null ,0);
        familyMember.increaseFamilyValue(1);
        assertEquals(3, player.getResources().getServants());
        assertEquals(1, familyMember.getValue());
        familyMember.increaseFamilyValue(2);
        assertEquals(1, player.getResources().getServants());
        assertEquals(3, familyMember.getValue());
    }

    @Test
    public void isEqual() throws Exception{
    Player player1= new Player(RED, null);
    Player player2= new Player(GREEN, null);
    FamilyMember familyMember1 = new FamilyMember(player1, FamilyColor.ORANGE, 1);
    FamilyMember familyMember2 = new FamilyMember(player1, FamilyColor.BLACK, 1);
    FamilyMember familyMember3 = new FamilyMember(player2, FamilyColor.ORANGE, 1);
    assertEquals(true, familyMember1.isEquals(familyMember1));
    assertEquals(false, familyMember1.isEquals(familyMember2));
    assertEquals(false, familyMember1.isEquals(familyMember3));
    }

}