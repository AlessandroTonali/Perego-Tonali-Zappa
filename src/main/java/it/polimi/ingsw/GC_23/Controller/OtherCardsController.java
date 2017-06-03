package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Cards.CharacterCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.Resources;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class OtherCardsController extends TowerController {
    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private  Tower tower;

    public void makeAction() {

    }

    public boolean isLegal(){
        return false;
    }

    public OtherCardsController(FamilyMember familyMember, Tower tower) {
        super(familyMember, tower);
    }

    public OtherCardsController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) {
        super(familyMember, tower, towerSpace);
        SingleCost cost = this.towerSpace.getCard().getCost(familyMember.getPlayer());
        if (isLegal(cost)) {
            makeAction(cost);
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    public boolean isLegal(SingleCost cost) {

        if(tower.checkOtherFamiliar()) {
            cost.getResources().sum(new ResourcesSet(0,3,0,0,0,0,0));
        }

        boolean legal = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost.getResources());

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(this.familyMember.getPlayer());

        legal = legal && tower.checkFamiliarTower(familyMember);

        return legal;
    }

    public void makeAction(SingleCost cost) {
        familyMember.getPlayer().getResources().pay(cost.getResources());
        ArrayList<AbsEffect> effects = towerSpace.getCard().getImmediateEffect();
        for(AbsEffect i : effects){
            i.activeEffect(familyMember.getPlayer());
        }
        towerSpace.setFamilyMember(familyMember);
        towerSpace.getCard().addCardOfPlayer(familyMember.getPlayer());
    }


}


