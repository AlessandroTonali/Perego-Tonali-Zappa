package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

/**
 * Created by jesss on 21/05/17.
 */
public class BenefitsEffect extends AbsEffect{
    private ResourcesSet resources;

    public BenefitsEffect(ResourcesSet resources) {
        this.resources = resources;
    }

    public ResourcesSet getResources() {
        return resources;
    }

    public void setResources(ResourcesSet resources) {
        this.resources = resources;
    }

    @Override
    public void activeEffect(Player player) {
        player.getResources().sum(this.resources);
    }
}
