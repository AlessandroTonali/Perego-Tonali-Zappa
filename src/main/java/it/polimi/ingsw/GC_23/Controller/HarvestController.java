package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.Board;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.HarvestSpace;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by jesss on 23/05/17.
 */
public class HarvestController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private HarvestSpace harvestSpace;

    public HarvestController(FamilyMember familyMember, HarvestSpace harvestSpace) throws IOException {
        this.familyMember = familyMember;
        this.harvestSpace = harvestSpace;

        harvestSpace.checkBeforeActivablePermanentEffect(familyMember);
        if( hasSense()) {
            if(isLegal()){
                harvestSpace.checkAfterActivablePermanentEffect(familyMember);
                makeAction();
            }
            else{
                familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
                familyMember.getPlayer().chooseMove(familyMember.getPlayer().getView());
            }
        }
    }

    //Da controllare: no familiari dello stesso colore (si neutro)
    @Override
    public boolean isLegal() {
        boolean legal = true;
        legal = legal && !harvestSpace.checkFamiliar(familyMember);
        System.out.println(legal);
        return legal;
    }

    public boolean hasSense() {
        return this.harvestSpace.checkValue(familyMember);
    }

    //TODO: attiva anche gli effetti permanenti delle carte terriorio in possesso con valore <= a quello dell'azione
    @Override
    public void makeAction() throws RemoteException {
        this.familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(this.familyMember.getPlayer());
        harvestSpace.setFamilyMember(familyMember);
        familyMember.getPlayer().getUserHandler().messageToUser(familyMember.getPlayer().getResources().toString());
    }

}
