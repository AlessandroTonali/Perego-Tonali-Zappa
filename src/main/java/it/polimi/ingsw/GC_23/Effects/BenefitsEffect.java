package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Player;
import it.polimi.ingsw.GC_23.Resources.ResourcesSet;

import java.rmi.RemoteException;

/**
 * Created by jesss on 21/05/17.
 */
public class BenefitsEffect extends AbsEffect{
    private ResourcesSet resources;

    /**
     * This constructor create an effect that give you a benefit on your resources
     * @param resources
     */
    public BenefitsEffect(ResourcesSet resources) {
        this.resources = resources;
    }

    public ResourcesSet getResources() {
        return resources;
    }

    public void setResources(ResourcesSet resources) {
        this.resources = resources;
    }


    /**
     * Add on player resources the resources of the effect
     * @param player
     * @throws RemoteException
     */
    public void activeEffect(Player player) throws RemoteException {
        if(player.getUserHandler().isGuiInterface()) {
            player.getUserHandler().messageToUser("benefitsEffect");
        }
        player.getResources().sum(this.resources, player);
        player.getUserHandler().messageToUser("Effect activeted! You have received: "+ resources.toString());
    }

    /**
     *
     * @param o
     * @return boolean object that is true if the @param is equals to object of the class or their resources are equals,
     * false if the resources of the two object are different
     */
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
