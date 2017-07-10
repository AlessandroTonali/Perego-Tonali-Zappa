package it.polimi.ingsw.GC_23.Controller;

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
                if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                    familyMember.getPlayer().getUserHandler().messageToUser("YOU ARE NOT ALLOW TO DO THIS MOVE, DO SOMETHING ELSE!");
                }
                throw new IllegalArgumentException();
            }
        }
    }

    //Da controllare: no familiari dello stesso colore (si neutro)
    @Override
    public boolean isLegal() {
        boolean legal;
        if (!harvestSpace.checkFamiliar(familyMember)) {
            legal = true;
        } else {
            legal = false;
        }
        return legal;
    }

    public boolean hasSense() {
        return this.harvestSpace.checkValue(familyMember);
    }


    @Override
    public void makeAction() throws RemoteException {
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
            familyMember.getPlayer().getUserHandler().messageToUser("effect");
        }
        this.familyMember.getPlayer().getBonusTile().getHarvestEffect().activeEffect(this.familyMember.getPlayer());
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
            familyMember.getPlayer().getUserHandler().messageToUser("effectended");
        }
        harvestSpace.setFamilyMember(familyMember);
        if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
            familyMember.getPlayer().getUserHandler().messageToUser(familyMember.getPlayer().getResources().toString());
        }
    }

}
