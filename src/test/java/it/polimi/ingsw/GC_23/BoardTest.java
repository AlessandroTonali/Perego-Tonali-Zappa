package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 08/07/2017.
 */
public class BoardTest {
    @Test
    public void towersStringer() throws Exception {
        Board board = new Board(2);
        String s = board.towersStringer();
        assertTrue(s != null);
    }

    @Test
    public void marketStringer() throws Exception {
        Board board = new Board(2);
        String s = board.marketStringer();
        assertTrue(s != null);
    }

    @Test
    public void setCardInTowerSpace() throws Exception {
        TowerSpace terrytory1 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[0],
                0);
        TowerSpace terrytory2 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[1],
                3);
        TowerSpace terrytory3 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[2],
                5);
        TowerSpace terrytory4 = new TowerSpace(null, (BenefitsEffect) ParseJson.getParseJson().getTowerTerritoryEffect()[3],
                7);
        TowerSpace[] territorySpaces = new TowerSpace[4];
        territorySpaces[0] = terrytory1;
        territorySpaces[1] = terrytory2;
        territorySpaces[2] = terrytory3;
        territorySpaces[3] = terrytory4;
        Tower territoryTower = new Tower(territorySpaces, CardColor.GREEN);

        assertFalse(territoryTower.getSpaces()[0].getCard() != null);
        Board board = new Board(2);
        board.setCardInTowerSpace(1,territoryTower);
        board.setCardInTowerSpace(2, territoryTower);
        board.setCardInTowerSpace(3, territoryTower);
        assertTrue(territoryTower.getSpaces()[0].getCard() != null);
        assertTrue(territoryTower.getSpaces()[1].getCard() != null);
        assertTrue(territoryTower.getSpaces()[2].getCard() != null);
        assertTrue(territoryTower.getSpaces()[3].getCard() != null);
    }

    @Test
    public void setCard() throws Exception {
        Board board = new Board(2);

        board.setCard();
        assertTrue(board.getTower(1).getSpaces()[0].getCard() != null);
    }

    @Test
    public void setDices() throws Exception {
        Board board = new Board(2);
        board.setDices();
        assertFalse(board.getDiceBValue() == 0);
        assertFalse(board.getDiceOValue() == 0);
        assertFalse(board.getDiceWValue() == 0);
    }

    @Test
    public void resetCardTowers() throws Exception {
        Board board = new Board(2);
        board.resetCardTowers();
        for (Tower tower : board.getTowers()) {
            TowerSpace[] spaces = tower.getSpaces();
            for (TowerSpace towerSpace : spaces) {
                assertTrue(towerSpace.getCard() == null);
            }
        }

    }

}