package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 26/05/17.
 */
public class NewPlayCardController implements Controller {

    private FamilyMember familyMember;
    private Tower tower;
    private TowerSpace towerSpace;
    private SingleCost sale;
    private SingleCost costCard;

    public NewPlayCardController(Tower tower, FamilyMember familyMember, SingleCost sale) throws RemoteException {
        this.familyMember = familyMember;
        this.tower = tower;
        this.sale = sale;
        this.towerSpace = tower.chooseTowerSpace(familyMember.getPlayer());
        costCard = towerSpace.getCard().getCost(familyMember.getPlayer());
        if (sale != null) {
            costCard.getResources().sum(sale.getResources(), familyMember.getPlayer());
        }
    }

    //non controlla se c'è un altro familiare dello stesso colore ma fa checkOtherFamiliar
    @Override
    public boolean isLegal() throws RemoteException {
        if (tower.checkOtherFamiliar()) {
            costCard.getResources().sum(new ResourcesSet(0,3,0,0,0,0,0), familyMember.getPlayer());
        }

        boolean legal = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(costCard.getResources());

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(familyMember.getPlayer());


        return legal;
    }

    //non setta il familyMember
    @Override
    public void makeAction() throws IOException {
        familyMember.getPlayer().getResources().pay(costCard.getResources());
        ArrayList<AbsEffect> effects = towerSpace.getCard().getImmediateEffect();
        for(AbsEffect i : effects){
            i.activeEffect(familyMember.getPlayer());
        }
        towerSpace.getCard().addCardOfPlayer(familyMember.getPlayer());

    }
}
