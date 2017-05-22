package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;

/**
 * Created by Alessandro on 22/05/2017.
 */
public class Main {

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public void initializeBoard() {
        TowerSpace towerSpace = new TowerSpace(CardColor.GREEN, getCard(1),)

    }

    private Card getCard (int period) {
        switch (period){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return null;
    }
}
