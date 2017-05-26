package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

/**
 * Created by jesss on 23/05/17.
 */
public class ProductionController extends PlaceFamilyMember {
    private FamilyMember familyMember;
    private Player player;
    private ProductionSpace productionSpace;

    private boolean checkBusy(){
        if(productionSpace.checkBusy())
            return true;
        else
            return false;
    }
    //Da controllare: è il suo turno, il posto non è occupato, no familiari dello stesso colore (si neutro),
    // valore maggiore di 1, se non è il primo malus di -3
    public boolean isLegal() {
        if (!(this.checkBusy())&&(familyMember.getValue()>=1)){
            return true;
        }
        else
            return false;
    }
    //attiva bonus personale (bonus tile) + effetti permanenti delle carte edificio in possesso con valore <= a quello dell'azione
    @Override
    public void makeAction(){
        productionSpace.setFamilyMember(familyMember);
        /*
        TODO: richiama ActiveEffectController usando player.getBonusTile().getBenefitsEffect()
         */

    }
}
