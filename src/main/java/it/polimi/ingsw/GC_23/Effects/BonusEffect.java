package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Player;

/**
 * Created by Alessandro on 06/06/2017.
 */
public class BonusEffect extends AbsEffect {



    @Override
    public void activeEffect(Player player) {

    }

    @Override
    public int getTypeEffect() {
        return EffectType.BONUS_EFFECT_TYPE;
    }
}
