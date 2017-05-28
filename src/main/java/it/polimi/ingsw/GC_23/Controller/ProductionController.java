package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

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
            makeAction();
        }
    }

    //Da controllare: è il suo turno, il posto non è occupato, no familiari dello stesso colore (si neutro),
    // valore maggiore di 1, se non è il primo malus di -3
    public boolean isLegal() {
        if (!(productionSpace.checkBusy())&&(familyMember.getValue()>=1)){
            return true;
        }
        else
            return false;
    }

    public boolean hasSense() {
        return this.productionSpace.checkValue(familyMember);
    }
    //attiva bonus personale (bonus tile) + effetti permanenti delle carte edificio in possesso con valore <= a quello dell'azione
    //TODO: distringuere tra bonus produzione e raccolto?
    @Override
    public void makeAction(){
        this.familyMember.getPlayer().getBonusTile().getBenefitsEffect().activeEffect(this.familyMember.getPlayer());
        productionSpace.setFamilyMember(familyMember);
    }
}
