package it.polimi.ingsw.GC_23;

import it.polimi.ingsw.GC_23.Cards.Card;
import it.polimi.ingsw.GC_23.Effects.BenefitsEffect;
import it.polimi.ingsw.GC_23.Enumerations.CardColor;

/**
 * Created by Alessandro Tonali on 20/05/2017.
 */
public class TowerSpace extends ActionSpace {
    private Card card;
    private BenefitsEffect effect;

    public TowerSpace(Card card, BenefitsEffect effect) {
        this.card = card;
        this.effect = effect;
    }
}
