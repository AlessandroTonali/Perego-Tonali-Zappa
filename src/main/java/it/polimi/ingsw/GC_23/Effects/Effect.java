package it.polimi.ingsw.GC_23.Effects;

/**
 * Created by jesss on 21/05/17.
 */

public class Effect {
    private CouncilPrivilegeEffect councilPrivilegeEffect;
    private BenefitsEffect benefitsEffect;
    private ImplicationEffect implicationEffect;
    private NewPlayEffect newPlayEffect;
    private DiscountEffect discountEffect;

    public Effect(CouncilPrivilegeEffect councilPrivilegeEffect, BenefitsEffect benefitsEffect, ImplicationEffect implicationEffect, NewPlayEffect newPlayEffect, DiscountEffect discountEffect) {
        this.councilPrivilegeEffect = councilPrivilegeEffect;
        this.benefitsEffect = benefitsEffect;
        this.implicationEffect = implicationEffect;
        this.newPlayEffect = newPlayEffect;
        this.discountEffect = discountEffect;
    }

    public void setCouncilPrivilegeEffect(CouncilPrivilegeEffect councilPrivilegeEffect) {
        this.councilPrivilegeEffect = councilPrivilegeEffect;
    }

    public void setBenefitsEffect(BenefitsEffect benefitsEffect) {
        this.benefitsEffect = benefitsEffect;
    }

    public void setImplicationEffect(ImplicationEffect implicationEffect) {
        this.implicationEffect = implicationEffect;
    }

    public void setNewPlayEffect(NewPlayEffect newPlayEffect) {
        this.newPlayEffect = newPlayEffect;
    }

    public void setDiscountEffect(DiscountEffect discountEffect) {this.discountEffect = discountEffect;
    }


    public CouncilPrivilegeEffect getCouncilPrivilegeEffect() {
        return councilPrivilegeEffect;
    }

    public BenefitsEffect getBenefitsEffect() {
        return benefitsEffect;
    }

    public ImplicationEffect getImplicationEffect() {
        return implicationEffect;
    }

    public NewPlayEffect getNewPlayEffect() {
        return newPlayEffect;
    }

    public DiscountEffect getDiscountEffect() {
        return discountEffect;
    }
}
