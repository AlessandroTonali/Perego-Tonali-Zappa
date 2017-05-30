package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Cards.TerritoryCard;
import it.polimi.ingsw.GC_23.Effects.Effect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.Resources;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class OtherCardsController extends TowerController {
    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private  Tower tower;

    public OtherCardsController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) {
        super(familyMember, tower, towerSpace);
        if (isLegal()) {
            makeAction();
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }
@Override
    public boolean isLegal() {
        ResourcesSet cost = towerSpace.getCard().getCost().getResources();

        if(tower.checkOtherFamiliar()) {
            cost.sum(new ResourcesSet(0,3,0,0,0,0,0));
        }



        boolean legal = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost);

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(this.familyMember.getPlayer());

        legal = legal && tower.checkFamiliarTower(familyMember);

        return legal;


    }
@Override

    public void makeAction() {

        if(this.towerSpace.getCard().checkchoose()) {

            //TODO potrebbe avere più costi la situa
            SingleCost cost = this.towerSpace.getCard().getCost();
            familyMember.getPlayer().getResources().pay(cost.getResources());
            Effect effect = this.towerSpace.getCard().getSingleEffect();
            effect.activeEffect(familyMember.getPlayer());
            familyMember.getPlayer().getCardOfPlayer().setCard((TerritoryCard) this.towerSpace.getCard());


        }
        else{
            //TODO c e da fare la scan
        }




    }


}

    //choosecost
    //costo da controllare

