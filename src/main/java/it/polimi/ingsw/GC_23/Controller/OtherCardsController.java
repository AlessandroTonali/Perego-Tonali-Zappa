package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.SingleCost;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class OtherCardsController extends TowerController {
    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private  Tower tower;

    @Override
    public void makeAction() {

    }

    @Override
    public boolean isLegal(){
        return false;
    }

    public OtherCardsController(FamilyMember familyMember, Tower tower) throws IOException {
        super(familyMember, tower);
        this.familyMember = super.getFamilyMember();
        familyMember = this.familyMember;
        this.towerSpace = super.getTowerSpace();
        this.tower = super.getTower();
        SingleCost cost = super.getTowerSpace().getCard().getCost(this.familyMember.getPlayer());

        towerSpace.checkBeforeActivablePermanentEffect(this.familyMember);
        if (isLegal(cost)) {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("OK");
            }
            towerSpace.checkAfterActivablePermanentEffect(familyMember);
            makeAction(cost);
        } else {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("KO");
            }
            if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
            }
            throw new IllegalArgumentException();
        }
    }

    public OtherCardsController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) throws IOException {
        super(familyMember, tower, towerSpace);

        super.getTowerSpace().getCard().getCost(familyMember.getPlayer());
        SingleCost cost = super.getTowerSpace().getCard().getCost(familyMember.getPlayer());
        if (isLegal(cost)) {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("OK");
            }
            makeAction(cost);
        } else {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("KO");
            }
        }
    }

    public boolean isLegal(SingleCost cost) throws RemoteException {

        cost.getResources().sum(tower.getSale().getResources(), familyMember.getPlayer());

        if(super.getTower().checkOtherFamiliar()) {
            if (!familyMember.getPlayer().isNotSpendOnOccupiedTower()) {
                cost.getResources().sum(new ResourcesSet(0, 3, 0, 0, 0, 0, 0), familyMember.getPlayer());
            }
        }

        boolean legal = true;

        legal = legal && !super.getTowerSpace().checkBusy();

        legal = legal && super.getFamilyMember().getPlayer().getResources().checkAffordable(cost.getResources());

        legal = legal && (super.getFamilyMember().getValue() >= super.getTowerSpace().getValue());

        legal = legal && super.getTowerSpace().getCard().checkTakeable(super.getFamilyMember().getPlayer());

      legal = legal && !super.getTower().checkFamiliarTower(super.getFamilyMember());

        return legal;
    }

    public void makeAction(SingleCost cost) throws IOException {
        super.getFamilyMember().getPlayer().getResources().pay(cost.getResources());
        ArrayList<AbsEffect> effects = super.getTowerSpace().getCard().getImmediateEffect();
        for(AbsEffect i : effects){
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("effect");
            }
            i.activeEffect(super.getFamilyMember().getPlayer());
        }
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
            familyMember.getPlayer().getUserHandler().messageToUser("effectended");
        }
        super.getTowerSpace().setFamilyMember(super.getFamilyMember());
        super.getTowerSpace().getCard().addCardOfPlayer(super.getFamilyMember().getPlayer());
    }


}


