package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect extends AbsEffect{
    //pergamena a scelta tra: 1 wood + 1 stone, 2 servants, 2 golds, 2 military, 1 faith
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;
    boolean isDifferent;
    private FamilyMember familyMember;


    public CouncilPrivilegeEffect(BenefitsEffect[] benefits, int numberOfPrivileges, boolean isDifferent) {
        this.benefits = benefits;
        this.numberOfPrivileges = numberOfPrivileges;
        this.isDifferent = isDifferent;
    }

    public void setBenefits(BenefitsEffect[] benefits) {
        this.benefits = benefits;
    }

    public void setNumberOfPrivileges(int numberOfPrivileges) {
        this.numberOfPrivileges = numberOfPrivileges;
    }

    public int getNumberOfPrivileges() {
        return numberOfPrivileges;
    }

    public BenefitsEffect[] getBenefits() {
        return benefits;
    }

    public BenefitsEffect[] chooseCouncilPrivilege(BenefitsEffect[] possibileBenefits){
        if(isDifferent){
            //TODO: se devono essere differenti più array di benefits effect, se possono essere uguali anche uno
            //TODO: interazione con il giocatore per la scelta
            return null;
        }
        return null;
    }

    @Override
    public void activeEffect(Player player) {
        BenefitsEffect[] chosenEffect = chooseCouncilPrivilege(benefits);
        for(BenefitsEffect e: chosenEffect){
            e.activeEffect(familyMember.getPlayer());
        }

    }

}
