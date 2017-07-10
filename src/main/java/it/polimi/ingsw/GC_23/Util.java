package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Alessandro on 13/06/2017.
 */
public class Util {

    /**
     * method for shuffle the card in base of your period
     * @param cards the array list of card that you want to shuffle
     * @return
     */
    public static ArrayList<? extends Card> shuffleCard(ArrayList<Card> cards) {
        ArrayList<Card>  cardsShuffeled= new ArrayList<>();
        ArrayList<Card> cardsFirstPeriod = new ArrayList<>();
        ArrayList<Card> cardsSecondPeriod = new ArrayList<>();
        ArrayList<Card> cardsThirdPeriod = new ArrayList<>();
        for (Card card : cards) {
            if (card.getPeriod() == 1) {
                cardsFirstPeriod.add(card);
            }
            if (card.getPeriod() == 2) {
                cardsSecondPeriod.add(card);
            }
            if (card.getPeriod() == 3) {
                cardsThirdPeriod.add(card);
            }
        }

        Collections.shuffle(cardsFirstPeriod, new Random());
        Collections.shuffle(cardsSecondPeriod, new Random());
        Collections.shuffle(cardsThirdPeriod, new Random());

        cardsShuffeled.addAll(cardsFirstPeriod);
        cardsShuffeled.addAll(cardsSecondPeriod);
        cardsShuffeled.addAll(cardsThirdPeriod);

        return cardsShuffeled;
    }

    /**
     * method to assign the string of the color to the enumeration card color
     * @param cardColorString the string of the color
     * @return the object CardColor corresponding to the string in input
     */
    public static CardColor parseCardColor(String cardColorString) {
        CardColor cardColor = null;
        if ("green".equals(cardColorString)) {
            cardColor = CardColor.GREEN;
        }
        if ("yellow".equals(cardColorString)) {
            cardColor = CardColor.YELLOW;
        }
        if ("blue".equals(cardColorString)) {
            cardColor = CardColor.BLUE;
        }
        if ("purple".equals(cardColorString)) {
            cardColor = CardColor.PURPLE;
        }

        return cardColor;
    }
}
