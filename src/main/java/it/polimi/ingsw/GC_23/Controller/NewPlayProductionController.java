package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Spaces.ProductionSpace;

/**
 * Created by Alessandro on 08/06/2017.
 */
public class NewPlayProductionController implements Controller {

    private FamilyMember familyMember;
    private ProductionSpace productionSpace;

    public NewPlayProductionController(FamilyMember familyMember, ProductionSpace productionSpace) {
        this.familyMember = familyMember;
        this.productionSpace = productionSpace;

        //TODO mettere nella newPlayProductionEffect
        if (isLegal()) {
            makeAction();
            System.out.println("Effect new play production done");
        } else {
            System.out.println("Move not allow");
        }
    }

    @Override
    public boolean isLegal() {
        return false;
    }

    @Override
    public void makeAction() {

    }
}
