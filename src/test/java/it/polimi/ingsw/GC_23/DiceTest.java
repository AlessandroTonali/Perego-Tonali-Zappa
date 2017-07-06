package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Enumerations.DiceColor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alessandro on 06/07/2017.
 */
public class DiceTest {
    @Test
    public void getDiceColor() throws Exception {
        Dice dice = new Dice(DiceColor.BLACK);
        assertEquals(DiceColor.BLACK, dice.getDiceColor());
    }

}