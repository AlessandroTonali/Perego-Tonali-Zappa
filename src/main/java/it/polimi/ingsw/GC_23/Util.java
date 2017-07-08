package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Alessandro on 13/06/2017.
 */
public class Util {

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

    public static CardColor parseCardColor(String cardColorString) {
        CardColor cardColor = null;
        if (cardColorString.equals("green")) {
            cardColor = CardColor.GREEN;
        }
        if (cardColorString.equals("yellow")) {
            cardColor = CardColor.YELLOW;
        }
        if (cardColorString.equals("blue")) {
            cardColor = CardColor.BLUE;
        }
        if (cardColorString.equals("purple")) {
            cardColor = CardColor.PURPLE;
        }

        return cardColor;
    }
}
