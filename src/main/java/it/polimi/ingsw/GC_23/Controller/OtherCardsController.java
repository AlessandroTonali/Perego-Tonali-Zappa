package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.Resources;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class OtherCardsController extends TowerController {
    private FamilyMember familyMember;
    private TowerSpace towerSpace;

    public OtherCardsController(FamilyMember familyMember, TowerSpace towerSpace) {
        this.familyMember = familyMember;
        this.towerSpace = towerSpace;
        if (isLegal()) {
            makeAction();
            System.out.println("succes");
        } else {
            System.out.println("error");
        }
    }
@Override
    public boolean isLegal() {
        ResourcesSet cost = towerSpace.getCard().getCost().getResources();

        //TODO capire come collegare lo spazio torre alla torre

        boolean legal = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost);

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        return legal;


    }
@Override
    public void makeAction() {

    }
}

    //choosecost
    //costo da controllare

