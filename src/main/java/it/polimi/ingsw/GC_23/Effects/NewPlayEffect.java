package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.EffectType;
import it.polimi.ingsw.GC_23.Enumerations.NewPlayColor;
import it.polimi.ingsw.GC_23.Player;

/**
 * Created by jesss on 21/05/17.
 */
public class NewPlayEffect extends AbsEffect{
    private NewPlayColor towerColor;
    private int diceValue;
    private DiscountEffect discount;

    public NewPlayEffect(int diceValue, DiscountEffect discount) {
        this.diceValue = diceValue;
        this.discount = discount;
    }

    public void setTowerColor(NewPlayColor towerColor) {
        this.towerColor = towerColor;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public void setDiscount(DiscountEffect discount) {
        this.discount = discount;
    }

    public NewPlayColor getTowerColor() {
        return towerColor;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public DiscountEffect getDiscount() {
        return discount;
    }

    public void play(){
        //TODO: giocata (nella tower) senza mettere il family member, chiamerà isLegal di NewPlay e il suo makeMove
    }


    public void activeEffect(Player player) {
        //todo

    }

    @Override
    public int getTypeEffect() {
        return EffectType.NEWPLAY_EFFECT_TYPE;
    }
}
