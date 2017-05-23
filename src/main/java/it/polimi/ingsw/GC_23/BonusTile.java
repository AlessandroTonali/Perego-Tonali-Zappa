package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class BonusTile {

    private BenefitsEffect benefitsEffect;

    public BonusTile(BenefitsEffect benefitsEffect) {
        this.benefitsEffect = benefitsEffect;
    }

    public BenefitsEffect getBenefitsEffect() {
        return benefitsEffect;
    }

    public void setBenefitsEffect(BenefitsEffect benefitsEffect) {
        this.benefitsEffect = benefitsEffect;

        //TODO benefits effect deve essere final perche non può cambiare

    }
}
