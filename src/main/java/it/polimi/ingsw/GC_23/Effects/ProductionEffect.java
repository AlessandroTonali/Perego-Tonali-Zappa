package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Alessandro on 16/06/2017.
 */
public class ProductionEffect extends PermanentEffect {

    private int productionEffect;
    private ArrayList<AbsEffect> effects;
    private boolean isActivable;

    public ProductionEffect(int productionEffect, ArrayList<AbsEffect> effects) {
        this.productionEffect = productionEffect;
        this.effects = effects;
    }

    /**
     * method for checking if the effect is activable or not
     * @param familyValue the value of the family member
     * @return true if the effect is activable, false if not
     */
    public boolean checkActivable(int familyValue) {

        if (productionEffect <= familyValue) {
            isActivable = true;
        } else {
            isActivable = false;
        }

        return isActivable;
    }

    /**
     *
     * @param player that want to active the effect
     * @throws IOException
     */
    @Override
    public void activeEffect(Player player) throws IOException {
        if(player.getUserHandler().isGuiInterface()){
            player.getUserHandler().messageToUser("productionEffect");
        }
        if(isActivable) {
            for (int i = 0; i < effects.size(); i++) {
                effects.get(i).activeEffect(player);
            }
        } else {
            if(!player.getUserHandler().isGuiInterface()) {
                player.getUserHandler().messageToUser("Permanent effect on production isn't activable");
            }
        }
    }

    public int getProductionEffect() {
        return productionEffect;
    }

    public void setProductionEffect(int productionEffect) {
        this.productionEffect = productionEffect;
    }

    public ArrayList<AbsEffect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<AbsEffect> effects) {
        this.effects = effects;
    }
}
