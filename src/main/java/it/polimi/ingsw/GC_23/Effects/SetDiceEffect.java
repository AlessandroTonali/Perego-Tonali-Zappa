package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.FamilyMember;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;

/**
 * Created by Alessandro on 05/07/2017.
 */
public class SetDiceEffect extends PermanentEffect{
    private String type;
    private int value;

    /**
     * set the value of dice or set of dices at a specific value
     * @param value of dice to set
     * @param type of effect
     */
    public SetDiceEffect(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }


    /**
     *
     * @param player that want to active the effect
     * @throws IOException
     */
    @Override
    public void activeEffect(Player player) throws IOException {
        FamilyMember[] familyMembers = player.getFamilyMembers();
        for (int i = 0; i < familyMembers.length; i++) {
            FamilyMember familyMember = familyMembers[i];
            if ("dice_neutral".equals(type) && familyMember.getFamilyColor() == FamilyColor.NEUTRAL) {
                familyMember.setValue(value);
            }
            if ("dice_color".equals(type) && familyMember.getFamilyColor() != FamilyColor.NEUTRAL) {
                familyMember.setValue(value);
            }
        }

    }
}
