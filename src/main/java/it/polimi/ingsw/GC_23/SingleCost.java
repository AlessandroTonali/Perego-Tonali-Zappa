package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.ResourcesPackage.ResourcesSet;

/**
 * Created by jesss on 21/05/17.
 */
public class SingleCost {
    private ResourcesSet resources;

    public SingleCost(ResourcesSet resources) {
        this.resources = resources;
    }

    public ResourcesSet getResources() {
        return resources;
    }

    public void setResources(ResourcesSet resources) {
        this.resources = resources;
    }
}
