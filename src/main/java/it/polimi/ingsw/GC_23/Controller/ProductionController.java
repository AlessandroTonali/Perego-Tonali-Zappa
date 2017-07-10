package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class ProductionController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    /**
     * controller of production space
     * @param familyMember with which you do the move
     * @param productionSpace associated at the controller
     * @throws IOException
     */
    public ProductionController(FamilyMember familyMember, ProductionSpace productionSpace) throws IOException {
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;

        productionSpace.checkBeforeActivablePermanentEffect(familyMember);
        if( hasSense()) {
            if(isLegal()){
                productionSpace.checkAfterActivablePermanentEffect(familyMember);
                makeAction();
            }
            else {
                if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
                    familyMember.getPlayer().getUserHandler().messageToUser("NOT VALID MOVE, TRY ANOTHER ONE!");
                }
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * show on interface
     * @return
     */
    //Da controllare: no familiari dello stesso colore (si neutro)
    public boolean isLegal() {
        if (!(productionSpace.checkBusy())&&(familyMember.getValue()>=1) && !productionSpace.checkFamiliar(familyMember)){
            return true;
        }
        else
            return false;
    }

    /**
     *
     * @return  true if the value of  family member is enough to enter in harvest space,
     * false if not
     */
    public boolean hasSense() {
        return this.productionSpace.checkValue(familyMember);
    }

    /**
     * show on interface
     * @throws RemoteException
     */
    @Override
    public void makeAction() throws RemoteException {
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()) {
            familyMember.getPlayer().getUserHandler().messageToUser("effect");
        }
        this.familyMember.getPlayer().getBonusTile().getProductionEffect().activeEffect(this.familyMember.getPlayer());
        if(familyMember.getPlayer().getUserHandler().isGuiInterface()){
           familyMember.getPlayer().getUserHandler().messageToUser("effectended");
        }
        productionSpace.setFamilyMember(familyMember);
        if(!familyMember.getPlayer().getUserHandler().isGuiInterface()) {
            this.familyMember.getPlayer().getUserHandler().messageToUser(this.familyMember.getPlayer().getResources().toString());
        }
    }

}
