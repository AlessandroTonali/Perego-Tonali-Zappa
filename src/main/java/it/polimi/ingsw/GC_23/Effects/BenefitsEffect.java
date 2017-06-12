package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
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


    public void activeEffect(Player player) {
        player.getResources().sum(this.resources);
    }

    @Override
    public int getTypeEffect() {
        return EffectType.BENEFIT_EFFECT_TYPE;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenefitsEffect that = (BenefitsEffect) o;

        return resources != null ? resources.equals(that.resources) : that.resources == null;
    }

    @Override
    public String toString() {
        return resources.toString();
    }
}
