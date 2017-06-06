package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

import java.util.ArrayList;

/**
 * Created by jesss on 23/05/17.
 */
public class ProductionController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    public ProductionController(FamilyMember familyMember, ProductionSpace productionSpace){
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;
        if( hasSense()) {
            if(isLegal()){
                System.out.println("success");
                makeAction();
            }
        }
    }

    //Da controllare: no familiari dello stesso colore (si neutro)
    public boolean isLegal() {
        if (!(productionSpace.checkBusy())&&(familyMember.getValue()>=1) && !productionSpace.checkFamiliar(familyMember)){
            return true;
        }
        else
            return false;
    }

    public boolean hasSense() {
        return this.productionSpace.checkValue(familyMember);
    }

    //TODO: attiva anche gli effetti permanenti delle carte edificio in possesso con valore <= a quello dell'azione
    @Override
    public void makeAction(){
        this.familyMember.getPlayer().getBonusTile().getProductionEffect().activeEffect(this.familyMember.getPlayer());
        productionSpace.setFamilyMember(familyMember);
    }

}
