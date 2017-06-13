package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
 * Created by Alessandro on 13/06/2017.
 */
public class MilitaryCost extends SingleCost {

    private ResourcesSet resourcesRequired;


    public MilitaryCost(ResourcesSet resourcesPay, ResourcesSet resourcesRequired) {
        super(resourcesPay);
        this.resourcesRequired = resourcesRequired;
    }

    public ResourcesSet getResourcesRequired() {
        return resourcesRequired;
    }
}
