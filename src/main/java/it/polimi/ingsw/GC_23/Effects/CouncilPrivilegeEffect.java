package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect extends AbsEffect{
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;
    boolean isDifferent = true;


    public CouncilPrivilegeEffect(BenefitsEffect[] benefits, int numberOfPrivileges) {
        this.benefits = benefits;
        this.numberOfPrivileges = numberOfPrivileges;
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

    }
}
