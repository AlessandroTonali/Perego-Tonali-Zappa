package it.polimi.ingsw.GC_23.Spaces;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.ParseJson;
import it.polimi.ingsw.GC_23.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 08/07/2017.
 */
public class TowerTest {


    @Test
    public void checkFamiliarTower() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTile1());
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 6);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);

        TowerSpace terrytory1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[0],
                0);
        TowerSpace terrytory2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[1],
                3);
        TowerSpace terrytory3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[2],
                5);
        TowerSpace terrytory4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[3],
                7);
        terrytory1.setFamilyMember(player.getFamilyMembers()[0]);
        TowerSpace[] territorySpaces = new TowerSpace[4];
        territorySpaces[0] = terrytory1;
        territorySpaces[1] = terrytory2;
        territorySpaces[2] = terrytory3;
        territorySpaces[3] = terrytory4;
        Tower territoryTower = new Tower(territorySpaces, CardColor.GREEN);

        assertEquals(true, territoryTower.checkFamiliarTower(player.getFamilyMembers()[1]));

        territoryTower.getSpaces()[0].resetFamilyMember();

        assertEquals(false, territoryTower.checkFamiliarTower(player.getFamilyMembers()[1]));


    }

    @Test
    public void checkOtherFamiliar() throws Exception {
        Player player = new Player(PlayerColor.BLUE, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        FamilyMember[] familyMembers = new FamilyMember[4];
        familyMembers[0] = new FamilyMember(player, FamilyColor.ORANGE, 5);
        familyMembers[1] = new FamilyMember(player, FamilyColor.WHITE, 6);
        familyMembers[2] = new FamilyMember(player, FamilyColor.BLACK, 0);
        familyMembers[3] = new FamilyMember(player, FamilyColor.NEUTRAL, 0);
        player.setFamilyMembers(familyMembers);

        Player player1 = new Player(PlayerColor.RED, ParseJson.getParseJson().getBonusTileArrayList().get(0));
        player1.setFamilyMembers(familyMembers);

        TowerSpace terrytory1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[0],
                0);
        TowerSpace terrytory2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[1],
                3);
        TowerSpace terrytory3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[2],
                5);
        TowerSpace terrytory4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[3],
                7);
        terrytory1.setFamilyMember(player.getFamilyMembers()[0]);
        TowerSpace[] territorySpaces = new TowerSpace[4];
        territorySpaces[0] = terrytory1;
        territorySpaces[1] = terrytory2;
        territorySpaces[2] = terrytory3;
        territorySpaces[3] = terrytory4;
        Tower territoryTower = new Tower(territorySpaces, CardColor.GREEN);

        assertEquals(true, territoryTower.checkOtherFamiliar());

        territoryTower.getSpaces()[0].resetFamilyMember();

        assertEquals(false, territoryTower.checkOtherFamiliar());
    }

    @Test
    public void chooseTowerSpace() throws Exception {
    }

    @Test
    public void checkPermanentEffect() throws Exception {

    }

    @Test
    public void disablePermanentEffect() throws Exception {
    }

}