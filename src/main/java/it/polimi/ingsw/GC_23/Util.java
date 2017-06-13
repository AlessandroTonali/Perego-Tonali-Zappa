package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Alessandro on 13/06/2017.
 */
public class Util {

    public ArrayList<? extends Card> shuffleCard(ArrayList<Card> cards) {
        ArrayList<Card>  cardsShuffeled= new ArrayList<>();
        ArrayList<Card> cardsFirstPeriod = new ArrayList<>();
        ArrayList<Card> cardsSecondPeriod = new ArrayList<>();
        ArrayList<Card> cardsThirdPeriod = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getPeriod() == 1) {
                cardsFirstPeriod.add(cards.get(i));
            }
            if (cards.get(i).getPeriod() == 2) {
                cardsSecondPeriod.add(cards.get(i));
            }
            if (cards.get(i).getPeriod() == 3){
                cardsThirdPeriod.add(cards.get(i));
            }
        }

        Collections.shuffle(cardsFirstPeriod, new Random());
        Collections.shuffle(cardsSecondPeriod, new Random());
        Collections.shuffle(cardsThirdPeriod, new Random());

        for (int i = 0; i < cardsFirstPeriod.size(); i++) {
            cardsShuffeled.add(cardsFirstPeriod.get(i));
        }
        for (int i = 0; i < cardsSecondPeriod.size(); i++) {
            cardsShuffeled.add(cardsSecondPeriod.get(i));
        }
        for (int i = 0; i < cardsThirdPeriod.size(); i++) {
            cardsShuffeled.add(cardsThirdPeriod.get(i));
        }

        return cardsShuffeled;
    }
}
