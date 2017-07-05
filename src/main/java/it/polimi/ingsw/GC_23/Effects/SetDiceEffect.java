package it.polimi.ingsw.GC_23.Effects;

import it.polimi.ingsw.GC_23.Enumerations.FamilyColor;
import it.polimi.ingsw.GC_23.Player;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by Alessandro on 05/07/2017.
 */
public class SetDiceEffect extends PermanentEffect{
    private String type;
    private int value;

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


    @Override
    public void activeEffect(Player player) throws IOException {

    }
}
