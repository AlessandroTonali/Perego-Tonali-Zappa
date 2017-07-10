package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Effects.AbsEffect;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;
import it.polimi.ingsw.GC_23.Spaces.Tower;
import it.polimi.ingsw.GC_23.Spaces.TowerSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class TerritoryController extends TowerController {

    private FamilyMember familyMember;
    private TowerSpace towerSpace;
    private Tower tower;

    /**
     * controller of territory tower
     * @param familyMember with which you do the move
     * @param tower
     * @throws IOException
     */
    public TerritoryController(FamilyMember familyMember, Tower tower) throws IOException {
        super(familyMember, tower);
        this.familyMember = super.getFamilyMember();
        familyMember = this.familyMember;
        this.towerSpace = super.getTowerSpace();
        this.tower = super.getTower();
        towerSpace.checkBeforeActivablePermanentEffect(familyMember);
        if (isLegal()) {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("OK");
            }
            towerSpace.checkAfterActivablePermanentEffect(familyMember);
            makeAction();
        } else {
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("KO");
            }
            if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
            }
            tower.disablePermanentEffect(familyMember);
            throw new IllegalArgumentException();
        }
    }


    public void setTowerSpace(TowerSpace towerSpace) {
        this.towerSpace = towerSpace;
    }

    public TerritoryController(FamilyMember familyMember, Tower tower, TowerSpace towerSpace) throws IOException {
        super(familyMember,tower,towerSpace);

        if (isLegal()) {
            makeAction();
        }
    }

    /**
     * show on interface
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isLegal() throws RemoteException {
        ResourcesSet cost = super.getTowerSpace().getCard().getCost(this.familyMember.getPlayer()).getResources();
        cost.sum(tower.getSale().getResources(), familyMember.getPlayer());

        if(tower.checkOtherFamiliar()) {
            if (!familyMember.getPlayer().isNotSpendOnOccupiedTower()) {
                cost.sum(new ResourcesSet(0, 3, 0, 0, 0, 0, 0), familyMember.getPlayer());
            }
        }

        boolean legal  = true;

        legal = legal && !towerSpace.checkBusy();

        legal = legal && familyMember.getPlayer().getResources().checkAffordable(cost);

        legal = legal && (familyMember.getValue() >= towerSpace.getValue());

        legal = legal && towerSpace.getCard().checkTakeable(familyMember.getPlayer());

        legal = legal && !tower.checkFamiliarTower(familyMember);

        return legal;
    }

    /**
     * show on interface
     * @throws IOException
     */
    @Override
    public void makeAction() throws IOException {
        if(tower.checkOtherFamiliar()) {
            familyMember.getPlayer().getResources().pay(new ResourcesSet(0,3,0,0,0,0,0));
        }

        ArrayList<AbsEffect> effects = towerSpace.getCard().getImmediateEffect();
        for(AbsEffect i : effects){
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
               familyMember.getPlayer().getUserHandler().messageToUser("effect");
            }
            i.activeEffect(familyMember.getPlayer());
            if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
                familyMember.getPlayer().getUserHandler().messageToUser("effectended");
            }
        }
        towerSpace.setFamilyMember(familyMember);

        towerSpace.getCard().addCardOfPlayer(familyMember.getPlayer());

    }

}
