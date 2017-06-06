package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Player;

/**
 * Created by Alessandro on 06/06/2017.
 */
public class NewPlayCardEffect extends AbsEffect {

    private NewPlayColor towerColor;
    private int diceValue;
    private DiscountEffect discount;


    public NewPlayCardEffect(int diceValue, NewPlayColor towerColor, DiscountEffect discount) {
        this.diceValue = diceValue;
        this.discount = discount;
        this.towerColor = towerColor;
    }
    @Override
    public void activeEffect(Player player) {
        //TODO: giocata (nella tower) senza mettere il family member, chiamerà isLegal di NewPlay e il suo makeMove
    }

    @Override
    public int getTypeEffect() {
        return 0;
    }
}

