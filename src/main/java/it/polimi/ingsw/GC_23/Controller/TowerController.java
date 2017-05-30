package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 23/05/17.
 */
public abstract class TowerController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private Tower tower;
    private TowerSpace towerSpace;

    public TowerController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) {
        this.familyMember = familyMember;
        this.tower = tower;
        this.towerSpace = towerSpace;
    }

    public abstract boolean isLegal();




    public abstract void makeAction();

    // controlla se il tower space che ci han passato appartiene alla torre
    private boolean checkTowerSpaceInTower() {
        boolean isMember = false;
        TowerSpace[] towerSpaces = tower.getSpaces();
        for (int i = 0; i < towerSpaces.length ; i++) {
            if (towerSpaces[i].getCard().getName().equals(towerSpace.getCard().getName())) {
                isMember = true;
            }
        }
        return isMember;
    }



}
