package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class TerritoryController extends TowerController {

    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private Tower tower;

    public TerritoryController(FamilyMember familyMember, Tower tower) {
        super(familyMember,tower);
        if (isLegal()) {
            makeAction();
            System.out.println("succes");
        } else {
            System.out.println("error");
        }
    }


    public void setTowerSpace(TowerSpace towerSpace) {
        this.towerSpace = towerSpace;
    }

    public TerritoryController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) {
        super(familyMember,tower,towerSpace);

        if (isLegal()) {
            makeAction();
            System.out.println("succes");
        } else {
            System.out.println("error");
        }
    }

    public boolean isLegal() {
        ResourcesSet cost = towerSpace.getCard().getCost(this.familyMember.getPlayer()).getResources();

        if(tower.checkOtherFamiliar()) {
            cost.sum(new ResourcesSet(0,3,0,0,0,0,0));
        }

        boolean legal  = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost);

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(familyMember.getPlayer());

        legal = legal && tower.checkFamiliarTower(familyMember);

        return legal;
    }

    public void makeAction() {
        if(tower.checkOtherFamiliar()) {
            familyMember.getPlayer().getResources().pay(new ResourcesSet(0,3,0,0,0,0,0));
        }

        ArrayList<AbsEffect> effects = towerSpace.getCard().getImmediateEffect();
        for(AbsEffect i : effects){
            i.activeEffect(familyMember.getPlayer());
        }
        towerSpace.setFamilyMember(familyMember);
        towerSpace.getCard().addCardOfPlayer(familyMember.getPlayer());

    }

}
