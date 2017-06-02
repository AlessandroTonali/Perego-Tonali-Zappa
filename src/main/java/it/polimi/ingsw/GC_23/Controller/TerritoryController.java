package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.BuildingCard;
import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class TerritoryController extends TowerController {

    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private Tower tower;

    public TerritoryController(FamilyMember familyMember, Tower tower) {
        super(familyMember,tower);



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

        //TODO capire come collegare lo spazio torre alla torre

        boolean legal  = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost);

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(familyMember.getPlayer());

        legal = legal && tower.checkFamiliarTower(familyMember);

        return legal;


    }

    public void makeAction() {

        /*if(this.towerSpace.getCard().checkchoose()) {
           Effect effect = this.towerSpace.getCard().getSingleEffect();
           effect.activeEffect(familyMember.getPlayer());
           familyMember.getPlayer().getCardOfPlayer().setCard((TerritoryCard) this.towerSpace.getCard());


        }
        else{
            //TODO c e da fare la scan
        }*/




    }
    //punti militari da controllare
}
