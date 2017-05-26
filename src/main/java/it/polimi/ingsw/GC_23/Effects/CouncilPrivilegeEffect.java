package it.polimi.ingsw.GC_23.Effects;

/**
 * Created by jesss on 21/05/17.
 */
public class CouncilPrivilegeEffect {
    private BenefitsEffect[] benefits;
    private int numberOfPrivileges;


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

}
