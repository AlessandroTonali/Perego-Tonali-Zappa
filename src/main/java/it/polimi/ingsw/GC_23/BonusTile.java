package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class BonusTile {

    private BenefitsEffect productionEffect;
    private BenefitsEffect harvestEffect;
    private int idBonusTile;

    /**
     *
     * @param productionEffect effect when you activated a production
     * @param harvestEffect effect when you activated a harvest
     */
    public BonusTile(BenefitsEffect productionEffect, BenefitsEffect harvestEffect) {
        this.productionEffect = productionEffect;
        this.harvestEffect = harvestEffect;
    }

    public BenefitsEffect getProductionEffect() {
        return productionEffect;
    }

    public void setProductionEffect(BenefitsEffect productionEffect) {
        this.productionEffect = productionEffect;
    }

    public BenefitsEffect getHarvestEffect() {
        return harvestEffect;
    }

    public void setHarvestEffect(BenefitsEffect harvestEffect) {
        this.harvestEffect = harvestEffect;
    }

    public int getIdBonusTile() {
        return idBonusTile;
    }

    public void setIdBonusTile(int idBonusTile) {
        this.idBonusTile = idBonusTile;
    }
}

