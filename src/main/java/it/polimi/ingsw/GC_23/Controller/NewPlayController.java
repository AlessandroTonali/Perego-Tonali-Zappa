package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.FamilyMember;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by Alessandro on 19/06/2017.
 */
public class NewPlayController implements Controller {

    FamilyMember familyMember;
    String typeNewAction;
    boolean checkValue;
    boolean checkIsBusy;
    boolean checkFamiliar;
    boolean checkOtherFamiliar;
    boolean placeFamiliar;

    public NewPlayController(FamilyMember familyMember, String typeNewAction, boolean checkValue, boolean checkIsBusy, boolean checkFamiliar, boolean checkOtherFamiliar, boolean placeFamiliar) {
        this.familyMember = familyMember;
        this.typeNewAction = typeNewAction;
        this.checkValue = checkValue;
        this.checkIsBusy = checkIsBusy;
        this.checkFamiliar = checkFamiliar;
        this.checkOtherFamiliar = checkOtherFamiliar;
        this.placeFamiliar = placeFamiliar;

        //TODO fare scelta action space
    }

    @Override
    public boolean isLegal() throws RemoteException {
        return false;
    }

    @Override
    public void makeAction() throws IOException {

    }
}
